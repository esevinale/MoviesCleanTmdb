package com.esevinale.movieguidetmdb.presentation.internal.di.components;

import com.esevinale.movieguidetmdb.presentation.internal.di.PerActivity;
import com.esevinale.movieguidetmdb.presentation.internal.di.modules.ActivityModule;
import com.esevinale.movieguidetmdb.presentation.presenter.MovieNowPlayingPresenter;

import dagger.Subcomponent;

@PerActivity
@Subcomponent (modules = ActivityModule.class)
public interface ActivityComponent {
    MovieNowPlayingPresenter provide();
}
