package com.esevinale.movieguidetmdb.presentation.internal.di.modules.movieListModules;

import com.esevinale.movieguidetmdb.presentation.internal.di.scopes.PerFragment;
import com.esevinale.movieguidetmdb.presentation.view.fragment.tabFragments.NowPlayingMovieListFragment;
import com.esevinale.movieguidetmdb.presentation.view.fragment.tabFragments.PopularMovieListFragment;
import com.esevinale.movieguidetmdb.presentation.view.fragment.tabFragments.TopRatedMovieListFragment;
import com.esevinale.movieguidetmdb.presentation.view.fragment.tabFragments.UpcomingMovieListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface MovieListActivityModule {

    @PerFragment
    @ContributesAndroidInjector(modules = {NowPlayingModule.class})
    NowPlayingMovieListFragment nowPlayingFragment();

    @PerFragment
    @ContributesAndroidInjector(modules = {PopularModule.class})
    PopularMovieListFragment popularFragment();

    @PerFragment
    @ContributesAndroidInjector(modules = {TopratedModule.class})
    TopRatedMovieListFragment topRatedFragment();

    @PerFragment
    @ContributesAndroidInjector(modules = {UpcomingModule.class})
    UpcomingMovieListFragment upcomingFragment();
}
