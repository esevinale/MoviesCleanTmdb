package com.esevinale.movieguidetmdb.presentation.internal.di.modules;

import com.esevinale.movieguidetmdb.domain.interactor.GetNowPlayingMovieList;
import com.esevinale.movieguidetmdb.domain.interactor.GetPopularMovieList;
import com.esevinale.movieguidetmdb.domain.interactor.GetTopRatedMovieList;
import com.esevinale.movieguidetmdb.domain.interactor.GetUpcomingMovieList;
import com.esevinale.movieguidetmdb.presentation.internal.di.PerActivity;
import com.esevinale.movieguidetmdb.presentation.mapper.MovieModelDataMapper;
import com.esevinale.movieguidetmdb.presentation.presenter.MovieNowPlayingPresenter;
import com.esevinale.movieguidetmdb.presentation.presenter.MoviePopularPresenter;
import com.esevinale.movieguidetmdb.presentation.presenter.MovieTopRatedPresenter;
import com.esevinale.movieguidetmdb.presentation.presenter.MovieUpcomingPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
    @Provides
    @PerActivity
    MovieNowPlayingPresenter provideNowPlayingPresenter(GetNowPlayingMovieList getMovieList, MovieModelDataMapper movieModelDataMapper) {
        return new MovieNowPlayingPresenter(getMovieList, movieModelDataMapper);
    }

    @Provides
    @PerActivity
    MoviePopularPresenter providePopularPresenter(GetPopularMovieList getMovieList, MovieModelDataMapper movieModelDataMapper) {
        return new MoviePopularPresenter(getMovieList, movieModelDataMapper);
    }

    @Provides
    @PerActivity
    MovieUpcomingPresenter provideUpcomingPresenter(GetUpcomingMovieList getMovieList, MovieModelDataMapper movieModelDataMapper) {
        return new MovieUpcomingPresenter(getMovieList, movieModelDataMapper);
    }

    @Provides
    @PerActivity
    MovieTopRatedPresenter provideTopRatedPresenter(GetTopRatedMovieList getMovieList, MovieModelDataMapper movieModelDataMapper) {
        return new MovieTopRatedPresenter(getMovieList, movieModelDataMapper);
    }
}
