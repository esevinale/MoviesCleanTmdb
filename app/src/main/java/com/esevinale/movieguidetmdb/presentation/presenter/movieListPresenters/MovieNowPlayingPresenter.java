package com.esevinale.movieguidetmdb.presentation.presenter.movieListPresenters;

import com.esevinale.movieguidetmdb.domain.interactor.GetNowPlayingMovieList;
import com.esevinale.movieguidetmdb.presentation.internal.di.scopes.PerActivity;
import com.esevinale.movieguidetmdb.presentation.mapper.MovieModelDataMapper;

import javax.inject.Inject;

@PerActivity
public class MovieNowPlayingPresenter extends MovieListPresenter {
    @Inject
    public MovieNowPlayingPresenter(GetNowPlayingMovieList getMovieList, MovieModelDataMapper movieModelDataMapper) {
        super(getMovieList, movieModelDataMapper);
    }
}
