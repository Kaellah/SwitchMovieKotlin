package com.kaellah.switchappkotlin.dependency.module

import android.content.Context
import com.kaellah.data.dao.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideMovies(context: Context) = AppDatabase.init(context)

}