package com.esevinale.movieguidetmdb.data.repository.datasource;

import com.esevinale.movieguidetmdb.data.entity.MovieEntity;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;

public interface MovieDataStore {
    Flowable<List<MovieEntity>> nowPlayingMovies(int page);
    Flowable<List<MovieEntity>> popularMovies(int page);
    Flowable<List<MovieEntity>> topRatedMovies(int page);
    Flowable<List<MovieEntity>> upcomingMovies(int page);
}
