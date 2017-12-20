package com.kaellah.switchappkotlin;

import android.arch.lifecycle.ProcessLifecycleOwner;
import android.content.Context;

import com.facebook.stetho.Stetho;
import com.jakewharton.threetenabp.AndroidThreeTen;
import com.kaellah.switchappkotlin.dependency.AppInjector;
import com.kaellah.switchappkotlin.dependency.component.DaggerAppComponent;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import io.reactivex.plugins.RxJavaPlugins;
import timber.log.Timber;
import timber.log.Timber.DebugTree;

import static com.facebook.stetho.Stetho.newInitializerBuilder;

/**
 * @since 12/19/17
 */
public class BaseApplication extends DaggerApplication {

    @Inject AppLifecycleObserver appLifecycleObserver;
    AndroidInjector<BaseApplication> androidInjector;

    public static DaggerAppComponent getAndroidInjector(Context context) {
        return (DaggerAppComponent) ((BaseApplication) context.getApplicationContext()).androidInjector;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        configLogs();

        AppInjector.init(this);
        AndroidThreeTen.init(this);
        RxJavaPlugins.setErrorHandler(Timber::e);

        ProcessLifecycleOwner.get().getLifecycle().addObserver(appLifecycleObserver);
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        androidInjector = DaggerAppComponent.builder().create(this);
        return androidInjector;
    }

    private void configLogs() {
        if (BuildConfig.DEBUG) {
            //Stetho config
            Stetho.initialize(newInitializerBuilder(this)
                                      .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                                      .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                                      .build());

            Timber.plant(new DebugTree());
            Logger.addLogAdapter(new AndroidLogAdapter() {
                @Override
                public boolean isLoggable(int priority, String tag) {
                    return BuildConfig.DEBUG;
                }
            });

        } else {
//            Fabric.with(this, new Crashlytics());
//            Timber.plant(new CrashlyticsTree());
        }
    }
}
