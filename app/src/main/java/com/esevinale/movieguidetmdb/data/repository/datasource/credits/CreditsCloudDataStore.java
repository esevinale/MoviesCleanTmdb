package com.esevinale.movieguidetmdb.data.repository.datasource.credits;

import com.esevinale.movieguidetmdb.data.entity.movieDetails.CreditsEntity;
import com.esevinale.movieguidetmdb.data.net.MovieService;

import io.reactivex.Flowable;

public class CreditsCloudDataStore implements CreditsDataStore {

    private final MovieService mService;

    CreditsCloudDataStore(MovieService service) {
        this.mService = service;
    }

    @Override
    public Flowable<CreditsEntity> movieCredits(int id) {
        return mService.getCredits(id);
    }
}
