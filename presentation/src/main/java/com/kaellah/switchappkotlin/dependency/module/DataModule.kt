package com.kaellah.switchappkotlin.dependency.module

import com.kaellah.data.api.MoviesService
import com.kaellah.data.dao.AppDatabase
import com.kaellah.data.repository.movie.MovieDataRepository
import com.kaellah.domain.repository.movie.MovieRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun provideMoviesRepo(apiService: MoviesService, appDatabase: AppDatabase)
            : MovieRepository = MovieDataRepository(apiService, appDatabase.movieDao())
}
