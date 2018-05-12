package com.esevinale.movieguidetmdb.data.repository;

import com.esevinale.movieguidetmdb.data.entity.mapper.MovieMapper;
import com.esevinale.movieguidetmdb.data.repository.datasource.movie.MovieDataStoreFactory;
import com.esevinale.movieguidetmdb.domain.entity.Movie;
import com.esevinale.movieguidetmdb.domain.repository.MovieRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class MovieDataRepository implements MovieRepository {

    private final MovieDataStoreFactory mMovieDataStoreFactory;
    private final MovieMapper mMovieMapper;

    @Inject
    MovieDataRepository(MovieDataStoreFactory movieDataStoreFactory, MovieMapper movieMapper) {
        this.mMovieDataStoreFactory = movieDataStoreFactory;
        this.mMovieMapper = movieMapper;
    }

    @Override
    public Flowable<List<Movie>> nowPlayingMovies(int page) {
        return mMovieDataStoreFactory.create().nowPlayingMovies(page).map(mMovieMapper::transformList);
    }

    @Override
    public Flowable<List<Movie>> popularMovies(int page) {
        return mMovieDataStoreFactory.create().popularMovies(page).map(mMovieMapper::transformList);
    }

    @Override
    public Flowable<List<Movie>> topRatedMovies(int page) {
        return mMovieDataStoreFactory.create().topRatedMovies(page).map(mMovieMapper::transformList);
    }

    @Override
    public Flowable<List<Movie>> upcomingMovies(int page) {
        return mMovieDataStoreFactory.create().upcomingMovies(page).map(mMovieMapper::transformList);
    }
}
