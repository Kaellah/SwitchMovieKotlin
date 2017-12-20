package com.kaellah.switchappkotlin.dependency.module;


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
//
//    @ContributesAndroidInjector()
//    abstract AuthCodeFragment contributeSignUpCodeFragment();
//
//    @ContributesAndroidInjector()
//    abstract SignUpEnterNameFragment contributeSignUpEnterNameFragment();
//
//    @ContributesAndroidInjector()
//    abstract SignUpWelcomeFragment contributeSignUpWelcomeFragment();
//
//    @ContributesAndroidInjector()
//    abstract SignUpContactsFragment contributeSignUpContactsFragment();
//
//    @ContributesAndroidInjector()
//    abstract ProfileFragment contributeProfileFragment();
//
//    @ContributesAndroidInjector()
//    abstract ChatListFragment contributeChatsFragment();
//
//    @ContributesAndroidInjector()
//    abstract DiscoverFragment contributeDiscoverFragment();
//
//    @ContributesAndroidInjector()
//    abstract SocialsFragment contributeSocialsFragment();
//
//    @ContributesAndroidInjector()
//    abstract ActionFragment contributeActionFragment();

}
