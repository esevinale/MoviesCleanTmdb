package com.esevinale.movieguidetmdb.data.repository;

import android.util.Log;

import com.esevinale.movieguidetmdb.data.entity.mapper.MovieMapper;
import com.esevinale.movieguidetmdb.data.repository.datasource.movie.MovieDataStoreFactory;
import com.esevinale.movieguidetmdb.domain.entity.Movie;
import com.esevinale.movieguidetmdb.domain.repository.MovieRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;

public class MovieDataRepository implements MovieRepository {

    private final MovieDataStoreFactory movieDataStoreFactory;
    private final MovieMapper movieMapper;

    @Inject
    MovieDataRepository(MovieDataStoreFactory movieDataStoreFactory, MovieMapper movieMapper) {
        this.movieDataStoreFactory = movieDataStoreFactory;
        this.movieMapper = movieMapper;
    }

    @Override
    public Flowable<List<Movie>> nowPlayingMovies(int page) {
        return movieDataStoreFactory.create().nowPlayingMovies(page).map(movieMapper::transformList);
    }

    @Override
    public Flowable<List<Movie>> popularMovies(int page) {
        return movieDataStoreFactory.create().popularMovies(page).map(movieMapper::transformList);
    }

    @Override
    public Flowable<List<Movie>> topRatedMovies(int page) {
        return movieDataStoreFactory.create().topRatedMovies(page).map(movieMapper::transformList);
    }

    @Override
    public Flowable<List<Movie>> upcomingMovies(int page) {
        return movieDataStoreFactory.create().upcomingMovies(page).map(movieMapper::transformList);
    }
}
