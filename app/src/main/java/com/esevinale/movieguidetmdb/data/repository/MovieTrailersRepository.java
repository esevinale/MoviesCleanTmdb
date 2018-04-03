package com.esevinale.movieguidetmdb.data.repository;

import com.esevinale.movieguidetmdb.data.entity.mapper.TrailerMapper;
import com.esevinale.movieguidetmdb.data.repository.datasource.trailer.TrailerCouldDataStore;
import com.esevinale.movieguidetmdb.data.repository.datasource.trailer.TrailerDataStore;
import com.esevinale.movieguidetmdb.domain.entity.Trailer;
import com.esevinale.movieguidetmdb.domain.repository.TrailerRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;

public class MovieTrailersRepository implements TrailerRepository {

    private final TrailerMapper movieMapper;
    private final TrailerDataStore trailerDataStore;

    @Inject
    MovieTrailersRepository(TrailerMapper movieMapper, TrailerCouldDataStore trailerDataStore) {
        this.movieMapper = movieMapper;
        this.trailerDataStore = trailerDataStore;
    }

    @Override
    public Flowable<List<Trailer>> trailers(int id) {
        return trailerDataStore.movieTrailers(id).map(movieMapper::transformList);
    }
}
