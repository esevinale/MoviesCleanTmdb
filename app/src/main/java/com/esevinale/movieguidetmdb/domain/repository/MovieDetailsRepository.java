package com.esevinale.movieguidetmdb.domain.repository;

import com.esevinale.movieguidetmdb.domain.entity.details.MovieDetailsDomainModel;

import io.reactivex.Flowable;

public interface MovieDetailsRepository {
    Flowable<MovieDetailsDomainModel> movieDetails(int id);
}
