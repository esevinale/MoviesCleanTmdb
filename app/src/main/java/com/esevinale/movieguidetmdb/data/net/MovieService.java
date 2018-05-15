package com.esevinale.movieguidetmdb.data.net;

import com.esevinale.movieguidetmdb.data.entity.movieDetails.CreditsEntity;
import com.esevinale.movieguidetmdb.data.entity.movieDetails.MovieDetailsEntity;
import com.esevinale.movieguidetmdb.data.entity.movies.MovieResDatesDTO;
import com.esevinale.movieguidetmdb.data.entity.movies.MovieResultDTO;
import com.esevinale.movieguidetmdb.data.entity.trailer.TrailerResultDTO;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;
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

    @GET("3/movie/{movie_id}/videos")
    Flowable<TrailerResultDTO> getMovieTrailers(@Path("movie_id") int movie_id);

    @GET("3/movie/{movie_id}?language=en-US")
    Flowable<MovieDetailsEntity> getMovieDetails(@Path("movie_id") int movie_id);

    @GET("3/movie/{movie_id}/credits")
    Flowable<CreditsEntity> getCredits(@Path("movie_id") int movie_id);
}
