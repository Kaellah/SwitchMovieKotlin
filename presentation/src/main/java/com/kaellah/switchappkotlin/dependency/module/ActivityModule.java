package com.kaellah.switchappkotlin.dependency.module;

import com.kaellah.switchappkotlin.ui.movies.MoviesActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * @since 12/19/17
 */

@Module()
public abstract class ActivityModule {

    @ContributesAndroidInjector()
    abstract MoviesActivity contributeMoviesActivity();
}
