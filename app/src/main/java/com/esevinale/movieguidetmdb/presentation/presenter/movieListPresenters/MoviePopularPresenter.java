package com.esevinale.movieguidetmdb.presentation.presenter.movieListPresenters;

import com.esevinale.movieguidetmdb.domain.interactor.GetPopularMovieList;
import com.esevinale.movieguidetmdb.presentation.internal.di.scopes.PerActivity;
import com.esevinale.movieguidetmdb.presentation.mapper.MovieModelMapper;

import javax.inject.Inject;

@PerActivity
public class MoviePopularPresenter extends MovieListPresenter {
    @Inject
    MoviePopularPresenter(GetPopularMovieList getMovieList, MovieModelMapper movieModelDataMapper) {
        super(getMovieList, movieModelDataMapper);
    }
}
