package com.kaellah.switchappkotlin.dependency.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

import com.kaellah.switchappkotlin.BuildConfig;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module()
public class AppModule {

    private final Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Resources providesResources() {
        return application.getResources();
    }

    @Provides
    @Singleton
    Context providesContext() {
        return application.getApplicationContext();
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences() {
        return application.getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE);
    }

//    @Provides
//    @AuthToken
//    String provideUserToken(SharedPreferences prefs) {
//        return prefs.getString(Preference.AUTH_TOKEN, "");
//    }
//
//    @Provides
//    @UserId
//    String provideUserId(SharedPreferences prefs) {
//        return prefs.getString(Preference.USER_ID, "");
//    }
//
//    @Provides
//    @PaymentToken
//    String providePaymentToken(SharedPreferences prefs) {
//        return prefs.getString(Preference.AUTH_TOKEN_PAYMENT, "");
//    }
//
//
//    @Provides
//    @Attachments
//    @Singleton
//    ArrayList<String> attachments() {return new ArrayList<>();}
//
//
//    @Qualifier
//    @Retention(RetentionPolicy.SOURCE)
//    public @interface AuthToken {
//    }
//
//    @Qualifier
//    @Retention(RetentionPolicy.SOURCE)
//    public @interface PaymentToken {
//    }
//
//    @Qualifier
//    @Retention(RetentionPolicy.SOURCE)
//    public @interface UserId {
//    }
//
//    @Qualifier
//    @Retention(RetentionPolicy.SOURCE)
//    public @interface Attachments {
//    }
}
