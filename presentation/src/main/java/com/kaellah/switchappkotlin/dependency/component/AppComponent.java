package com.kaellah.switchappkotlin.dependency.component;


import com.kaellah.switchappkotlin.BaseApplication;
import com.kaellah.switchappkotlin.dependency.module.ActivityModule;
import com.kaellah.switchappkotlin.dependency.module.ApiModule;
import com.kaellah.switchappkotlin.dependency.module.AppModule;
import com.kaellah.switchappkotlin.dependency.module.DataModule;
import com.kaellah.switchappkotlin.dependency.module.DatabaseModule;
import com.kaellah.switchappkotlin.dependency.module.FragmentModule;
import com.kaellah.switchappkotlin.dependency.module.NetworkModule;
import com.kaellah.switchappkotlin.dependency.module.ViewModelModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        AndroidSupportInjectionModule.class,
        AppModule.class,
        ApiModule.class,
        NetworkModule.class,
        DataModule.class,
        DatabaseModule.class,
        ViewModelModule.class,
        ActivityModule.class,
        FragmentModule.class})
public interface AppComponent extends AndroidInjector<BaseApplication> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<BaseApplication> {

        abstract Builder appModule(AppModule module);

        @Override
        public abstract AppComponent build();

        @Override
        public void seedInstance(BaseApplication instance) {
            appModule(new AppModule(instance));
        }
    }

}
