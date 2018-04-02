package com.esevinale.movieguidetmdb.data.repository.datasource.movie;


import com.esevinale.movieguidetmdb.data.entity.movies.MovieEntity;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;

public interface MovieDataStore {
    Flowable<List<MovieEntity>> nowPlayingMovies(int page);
    Flowable<List<MovieEntity>> popularMovies(int page);
    Flowable<List<MovieEntity>> topRatedMovies(int page);
    Flowable<List<MovieEntity>> upcomingMovies(int page);
}
