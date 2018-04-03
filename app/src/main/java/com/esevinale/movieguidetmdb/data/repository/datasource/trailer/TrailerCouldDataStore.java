package com.esevinale.movieguidetmdb.data.repository.datasource.trailer;

import com.esevinale.movieguidetmdb.data.entity.trailer.TrailerEntity;
import com.esevinale.movieguidetmdb.data.entity.trailer.TrailerResultDTO;
import com.esevinale.movieguidetmdb.data.net.MovieService;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;

public class TrailerCouldDataStore implements TrailerDataStore {
    private final MovieService service;

    @Inject
    TrailerCouldDataStore(MovieService service) {
        this.service = service;
    }

    @Override
    public Flowable<List<TrailerEntity>> movieTrailers(int id) {
        return service.getMovieTrailers(id).map(TrailerResultDTO::getResults);
    }
}
