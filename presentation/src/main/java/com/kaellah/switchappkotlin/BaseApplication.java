package com.kaellah.switchappkotlin;

import android.arch.lifecycle.ProcessLifecycleOwner;
import android.content.Context;

import com.jakewharton.threetenabp.AndroidThreeTen;
import com.kaellah.switchappkotlin.dependency.AppInjector;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import io.reactivex.plugins.RxJavaPlugins;
import timber.log.Timber;

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
}
