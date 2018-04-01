package com.esevinale.movieguidetmdb.data.repository.datasource;

import com.esevinale.movieguidetmdb.data.cache.MovieCache;
import com.esevinale.movieguidetmdb.data.entity.MovieEntity;
import com.esevinale.movieguidetmdb.data.entity.MovieTypes;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;

public class MovieLocalDataStore implements MovieDataStore {

    private final MovieCache movieCache;

    public MovieLocalDataStore(MovieCache movieCache) {
        this.movieCache = movieCache;
    }


    @Override
    public Flowable<List<MovieEntity>> nowPlayingMovies(int page) {
        return movieCache.get(page, MovieTypes.NowPlaying);
    }

    @Override
    public Flowable<List<MovieEntity>> popularMovies(int page) {
        return movieCache.get(page, MovieTypes.Popular);
    }

    @Override
    public Flowable<List<MovieEntity>> topRatedMovies(int page) {
        return movieCache.get(page, MovieTypes.TopRated);
    }

    @Override
    public Flowable<List<MovieEntity>> upcomingMovies(int page) {
        return movieCache.get(page, MovieTypes.Upcoming);
    }
}
