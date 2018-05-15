package com.esevinale.movieguidetmdb.presentation.presenter.movieListPresenters;

import com.esevinale.movieguidetmdb.domain.interactor.GetNowPlayingMovieList;
import com.esevinale.movieguidetmdb.presentation.internal.di.scopes.PerActivity;
import com.esevinale.movieguidetmdb.presentation.mapper.MovieModelMapper;

import javax.inject.Inject;

@PerActivity
public class MovieNowPlayingPresenter extends MovieListPresenter {
    @Inject
    MovieNowPlayingPresenter(GetNowPlayingMovieList getMovieList, MovieModelMapper movieModelDataMapper) {
        super(getMovieList, movieModelDataMapper);
    }
}
