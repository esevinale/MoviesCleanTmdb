package com.esevinale.movieguidetmdb.domain.repository;

import com.esevinale.movieguidetmdb.domain.entity.details.MovieDetails;

import io.reactivex.Flowable;

public interface MovieDetailsRepository {
    Flowable<MovieDetails> movieDetails(int id);
}
