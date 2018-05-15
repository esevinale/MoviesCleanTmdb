package com.esevinale.movieguidetmdb.data.repository.datasource.credits;

import com.esevinale.movieguidetmdb.data.entity.movieDetails.CreditsEntity;

import io.reactivex.Flowable;

public interface CreditsDataStore {
    Flowable<CreditsEntity> movieCredits(int id);
}
