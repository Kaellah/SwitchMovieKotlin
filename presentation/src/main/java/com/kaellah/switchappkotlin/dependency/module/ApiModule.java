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
}
