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
    private final MovieCache movieCache;
    private final MovieService service;
    private final MovieTypeMapper typeMapper;

    MovieCloudDataStore(MovieCache movieCache, MovieService service, MovieTypeMapper typeMapper) {
        this.movieCache = movieCache;
        this.service = service;
        this.typeMapper = typeMapper;
    }

    @Override
    public Flowable<List<MovieEntity>> nowPlayingMovies(int page) {
        return service.getMovieNowPlaying(page).map(MovieResDatesDTO::getResults).map(movieEntities -> typeMapper.transformList(movieEntities, MovieTypes.NowPlaying)).doOnNext(movieCache::put);
    }

    @Override
    public Flowable<List<MovieEntity>> popularMovies(int page) {
        return service.getMoviePopular(page).map(MovieResultDTO::getResults).map(movieEntities -> typeMapper.transformList(movieEntities, MovieTypes.Popular)).doOnNext(movieCache::put);
    }

    @Override
    public Flowable<List<MovieEntity>> topRatedMovies(int page) {
        return service.getMovieTopRated(page).map(MovieResultDTO::getResults).map(movieEntities -> typeMapper.transformList(movieEntities, MovieTypes.TopRated)).doOnNext(movieCache::put);
    }

    @Override
    public Flowable<List<MovieEntity>> upcomingMovies(int page) {
        return service.getMovieUpcoming(page).map(MovieResDatesDTO::getResults).map(movieEntities -> typeMapper.transformList(movieEntities, MovieTypes.Upcoming)).doOnNext(movieCache::put);
    }
}
