package com.esevinale.movieguidetmdb.data.repository.datasource.movie;

import com.esevinale.movieguidetmdb.data.cache.MovieCache;
import com.esevinale.movieguidetmdb.data.entity.movies.MovieEntity;
import com.esevinale.movieguidetmdb.data.entity.movies.MovieTypes;

import java.util.List;

import io.reactivex.Flowable;

public class MovieLocalDataStore implements MovieDataStore {

    private final MovieCache mMovieCache;

    MovieLocalDataStore(MovieCache movieCache) {
        this.mMovieCache = movieCache;
    }


    @Override
    public Flowable<List<MovieEntity>> nowPlayingMovies(int page) {
        return mMovieCache.get(page, MovieTypes.NowPlaying);
    }

    @Override
    public Flowable<List<MovieEntity>> popularMovies(int page) {
        return mMovieCache.get(page, MovieTypes.Popular);
    }

    @Override
    public Flowable<List<MovieEntity>> topRatedMovies(int page) {
        return mMovieCache.get(page, MovieTypes.TopRated);
    }

    @Override
    public Flowable<List<MovieEntity>> upcomingMovies(int page) {
        return mMovieCache.get(page, MovieTypes.Upcoming);
    }
}
