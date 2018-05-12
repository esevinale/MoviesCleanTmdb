package com.esevinale.movieguidetmdb.data.repository.datasource.trailer;

import com.esevinale.movieguidetmdb.data.entity.trailer.TrailerEntity;
import com.esevinale.movieguidetmdb.data.entity.trailer.TrailerResultDTO;
import com.esevinale.movieguidetmdb.data.net.MovieService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class TrailerCouldDataStore implements TrailerDataStore {
    private final MovieService mService;

    TrailerCouldDataStore(MovieService service) {
        this.mService = service;
    }

    @Override
    public Flowable<List<TrailerEntity>> movieTrailers(int id) {
        return mService.getMovieTrailers(id).map(TrailerResultDTO::getResults);
    }
}
