package com.kaellah.switchappkotlin.dependency.module;


import com.kaellah.switchappkotlin.ui.movies.details.MoviesDetailsFragment;
import com.kaellah.switchappkotlin.ui.movies.list.MoviesListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Class for fragments injecting
 */

@Module()
public abstract class FragmentModule {

    @ContributesAndroidInjector()
    abstract MoviesListFragment contributeMoviesListFragment();

    @ContributesAndroidInjector()
    abstract MoviesDetailsFragment contributeMoviesDetailsFragment();
}
