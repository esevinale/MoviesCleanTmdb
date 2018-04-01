package com.esevinale.movieguidetmdb.presentation.internal.di.modules;

import android.app.Activity;

import com.esevinale.movieguidetmdb.domain.interactor.GetNowPlayingMovieList;
import com.esevinale.movieguidetmdb.presentation.internal.di.PerActivity;
import com.esevinale.movieguidetmdb.presentation.mapper.MovieModelDataMapper;
import com.esevinale.movieguidetmdb.presentation.presenter.MovieNowPlayingPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
    @Provides
    @PerActivity
    MovieNowPlayingPresenter presenter(GetNowPlayingMovieList getMovieList, MovieModelDataMapper movieModelDataMapper) {
        return new MovieNowPlayingPresenter(getMovieList, movieModelDataMapper);
    }
}
