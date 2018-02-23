package com.kaellah.switchappkotlin.dependency.module;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.github.simonpercic.oklog3.OkLogInterceptor;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kaellah.data.util.Utils;
import com.kaellah.domain.Constants.Headers;
import com.kaellah.switchappkotlin.BuildConfig;

import java.io.File;
import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import okhttp3.logging.HttpLoggingInterceptor.Logger;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;


@Module
public class NetworkModule {

    private static final String TAG = "OkHttp";

    @Provides
    @Singleton
    OkHttpClient provideOkhttp(Context context) {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder()
                .followRedirects(true)
                .followSslRedirects(true)
                .retryOnConnectionFailure(true);

        try {
            clientBuilder.cache(cacheResponse(context.getCacheDir()));
        } catch (Exception e) {
            Timber.tag(TAG).e(e);
        }

        clientBuilder.addNetworkInterceptor(new ResponseCacheInterceptor());
        clientBuilder.addNetworkInterceptor(new OfflineResponseCacheInterceptor());

        clientBuilder.addNetworkInterceptor(chain -> {
            Request request = chain.request();
            return chain.proceed(request);
        });

        if (BuildConfig.DEBUG) {
            OkLogInterceptor okLogInterceptor = OkLogInterceptor
                    .builder()
                    .setBaseUrl("http://oklog.responseecho.com")
                    .setLogInterceptor(url -> {
                        Timber.tag(TAG).d(url);
                        return true;
                    })
                    .withRequestHeaders(true)
                    .withResponseUrl(true)
                    .withRequestHeaders(true)
                    .shortenInfoUrl(true)
                    .build();

            Logger logger = message -> Timber.tag(TAG).d(message);

            clientBuilder.addNetworkInterceptor(okLogInterceptor);
            clientBuilder.addNetworkInterceptor(new StethoInterceptor());
            clientBuilder.addNetworkInterceptor(new HttpLoggingInterceptor(logger).setLevel(Level.BODY));
        }

        return clientBuilder.build();
    }

    @Provides
    static Gson provideGson() {
        GsonBuilder builder = new GsonBuilder();
        builder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return builder.create();
    }

    @Provides
    static Retrofit provideRetrofit(OkHttpClient client, Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .client(client)
                .build();
    }

    private static class ResponseCacheInterceptor implements Interceptor {

        @Override
        public Response intercept(@NonNull Chain chain) throws IOException {
            Request request = chain.request();
            if (Boolean.valueOf(request.header(Headers.APPLY_OFFLINE_CACHE))) {
                Response originalResponse = chain.proceed(request);
                String cacheControl = originalResponse.header("Cache-Control");
                Timber.i("Cache control %s", cacheControl);
                if (cacheControl == null || cacheControl.contains("no-store") || cacheControl.contains("no-cache") ||
                    cacheControl.contains("must-revalidate") || cacheControl.contains("max-age=0")) {
                    return originalResponse.newBuilder()
                            .header("Cache-Control", "public, max-age=" + 1) //in seconds
                            .build();
                } else {
                    return originalResponse;
                }
            } else {
                return chain.proceed(request);
            }
        }
    }

    private static class OfflineResponseCacheInterceptor implements Interceptor {

        @Override
        public Response intercept(@NonNull Chain chain) throws IOException {
            Request request = chain.request();
            if (Boolean.valueOf(request.header(Headers.APPLY_OFFLINE_CACHE))) {
                if (!Utils.isOnline()) {
                    Timber.i("Offline cache applied");
                    request = request.newBuilder()
                            .removeHeader("Pragma")
                            .removeHeader(Headers.APPLY_OFFLINE_CACHE)
                            .header("Cache-Control",
                                    "public, only-if-cached, max-stale=" + 2419200)
                            .build();
                }
            } else
                Timber.i("Offline cache not applied");

            return chain.proceed(request);
        }
    }

    private Cache cacheResponse(File cacheDirectory) throws Exception {
        int cacheSize = 15 * 1024 * 1024; // 10 MiB
        return new Cache(cacheDirectory, cacheSize);
    }
}
