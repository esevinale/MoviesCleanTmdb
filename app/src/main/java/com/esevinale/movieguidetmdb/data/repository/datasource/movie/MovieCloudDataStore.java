package com.esevinale.movieguidetmdb.data.repository.datasource.movie;

import com.esevinale.movieguidetmdb.data.cache.MovieCache;
import com.esevinale.movieguidetmdb.data.entity.movies.MovieEntity;
import com.esevinale.movieguidetmdb.data.entity.movies.MovieResDatesDTO;
import com.esevinale.movieguidetmdb.data.entity.movies.MovieResultDTO;
import com.esevinale.movieguidetmdb.data.entity.movies.MovieTypes;
import com.esevinale.movieguidetmdb.data.entity.mapper.MovieTypeMapper;
import com.esevinale.movieguidetmdb.data.net.MovieService;

import java.util.List;

import io.reactivex.Flowable;

public class MovieCloudDataStore implements MovieDataStore {
    private final MovieCache mMovieCache;
    private final MovieService mService;
    private final MovieTypeMapper mTypeMapper;

    MovieCloudDataStore(MovieCache movieCache, MovieService service, MovieTypeMapper typeMapper) {
        this.mMovieCache = movieCache;
        this.mService = service;
        this.mTypeMapper = typeMapper;
    }

    @Override
    public Flowable<List<MovieEntity>> nowPlayingMovies(int page) {
        return mService.getMovieNowPlaying(page).map(MovieResDatesDTO::getResults).map(movieEntities -> mTypeMapper.transformList(movieEntities, MovieTypes.NowPlaying)).doOnNext(mMovieCache::put);
    }

    @Override
    public Flowable<List<MovieEntity>> popularMovies(int page) {
        return mService.getMoviePopular(page).map(MovieResultDTO::getResults).map(movieEntities -> mTypeMapper.transformList(movieEntities, MovieTypes.Popular)).doOnNext(mMovieCache::put);
    }

    @Override
    public Flowable<List<MovieEntity>> topRatedMovies(int page) {
        return mService.getMovieTopRated(page).map(MovieResultDTO::getResults).map(movieEntities -> mTypeMapper.transformList(movieEntities, MovieTypes.TopRated)).doOnNext(mMovieCache::put);
    }

    @Override
    public Flowable<List<MovieEntity>> upcomingMovies(int page) {
        return mService.getMovieUpcoming(page).map(MovieResDatesDTO::getResults).map(movieEntities -> mTypeMapper.transformList(movieEntities, MovieTypes.Upcoming)).doOnNext(mMovieCache::put);
    }
}
