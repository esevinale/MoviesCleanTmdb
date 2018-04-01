package com.esevinale.movieguidetmdb.presentation.internal.di.components;

import com.esevinale.movieguidetmdb.presentation.internal.di.PerActivity;
import com.esevinale.movieguidetmdb.presentation.internal.di.modules.ActivityModule;
import com.esevinale.movieguidetmdb.presentation.view.fragment.tabFragments.PopularMovieListFragment;
import com.esevinale.movieguidetmdb.presentation.view.fragment.tabFragments.TopRatedMovieListFragment;
import com.esevinale.movieguidetmdb.presentation.view.fragment.tabFragments.UpcomingMovieListFragment;
import com.esevinale.movieguidetmdb.presentation.view.fragment.tabFragments.NowPlayingMovieListFragment;

import dagger.Subcomponent;

@PerActivity
@Subcomponent (modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(NowPlayingMovieListFragment fragment);
    void inject(UpcomingMovieListFragment fragment);
    void inject(TopRatedMovieListFragment fragment);
    void inject(PopularMovieListFragment fragment);
}
