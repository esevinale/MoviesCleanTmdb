package com.esevinale.movieguidetmdb.domain.repository;

import com.esevinale.movieguidetmdb.domain.entity.Movie;

import java.util.List;

import io.reactivex.Flowable;

public interface MovieRepository {
    Flowable<List<Movie>> nowPlayingMovies(int page);
    Flowable<List<Movie>> popularMovies(int page);
    Flowable<List<Movie>> topRatedMovies(int page);
    Flowable<List<Movie>> upcomingMovies(int page);
}
