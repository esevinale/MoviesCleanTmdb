package com.esevinale.movieguidetmdb.data.net;

import com.esevinale.movieguidetmdb.data.entity.MovieResDatesDTO;
import com.esevinale.movieguidetmdb.data.entity.MovieResultDTO;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieService {
    @GET("3/movie/upcoming?language=en-US")
    Flowable<MovieResDatesDTO> getMovieUpcoming(@Query("page") int page);

    @GET("3/movie/top_rated?language=en-US")
    Flowable<MovieResultDTO> getMovieTopRated(@Query("page") int page);

    @GET("3/movie/popular?language=en-US")
    Flowable<MovieResultDTO> getMoviePopular(@Query("page") int page);

    @GET("3/movie/now_playing?language=en-US")
    Flowable<MovieResDatesDTO> getMovieNowPlaying(@Query("page") int page);
}
