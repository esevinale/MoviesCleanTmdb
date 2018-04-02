package com.esevinale.movieguidetmdb.presentation.presenter.movieListPresenters;

import com.esevinale.movieguidetmdb.domain.interactor.GetPopularMovieList;
import com.esevinale.movieguidetmdb.presentation.internal.di.scopes.PerActivity;
import com.esevinale.movieguidetmdb.presentation.mapper.MovieModelDataMapper;

import javax.inject.Inject;

@PerActivity
public class MoviePopularPresenter extends MovieListPresenter {
    @Inject
    public MoviePopularPresenter(GetPopularMovieList getMovieList, MovieModelDataMapper movieModelDataMapper) {
        super(getMovieList, movieModelDataMapper);
    }
}
