package com.kaellah.switchappkotlin.dependency.module;

import com.kaellah.data.api.MoviesService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * @since 10/11/17
 */
@Module
public class ApiModule {

    @Singleton
    @Provides
    MoviesService provideMoviesService(Retrofit retrofit) {
        return retrofit.create(MoviesService.class);
    }

//    @Singleton
//    @Provides
//    SocialService provideSocialService(Retrofit retrofit) {
//        return retrofit.create(SocialService.class);
//    }
//
//    @Singleton
//    @Provides
//    ChatService provideChatService(Retrofit retrofit) {
//        return retrofit.create(ChatService.class);
//    }
//
//    @Singleton
//    @Provides
//    UploadService provideUploadService(Retrofit retrofit) {
//        return retrofit.create(UploadService.class);
//    }
//
//    @Singleton
//    @Provides
//    UserService provideUserService(Retrofit retrofit) {
//        return retrofit.create(UserService.class);
//    }
//
//    @Singleton
//    @Provides
//    WalletService provideWalletService(Retrofit retrofit) {
//        return retrofit.create(WalletService.class);
//    }
}
