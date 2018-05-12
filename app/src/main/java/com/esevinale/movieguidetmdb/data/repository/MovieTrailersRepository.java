package com.esevinale.movieguidetmdb.data.repository;

import com.esevinale.movieguidetmdb.data.entity.mapper.TrailerMapper;
import com.esevinale.movieguidetmdb.data.repository.datasource.trailer.TrailerCouldDataStore;
import com.esevinale.movieguidetmdb.data.repository.datasource.trailer.TrailerDataStore;
import com.esevinale.movieguidetmdb.data.repository.datasource.trailer.TrailerDataStoreFactory;
import com.esevinale.movieguidetmdb.domain.entity.Trailer;
import com.esevinale.movieguidetmdb.domain.repository.TrailerRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class MovieTrailersRepository implements TrailerRepository {

    private final TrailerMapper mTrailerMapper;
    private final TrailerDataStoreFactory mTrailerDataStore;

    @Inject
    MovieTrailersRepository(TrailerMapper movieMapper, TrailerDataStoreFactory trailerDataStore) {
        this.mTrailerMapper = movieMapper;
        this.mTrailerDataStore = trailerDataStore;
    }

    @Override
    public Flowable<List<Trailer>> trailers(int id) {
        return mTrailerDataStore.create().movieTrailers(id).map(mTrailerMapper::transformList);
    }
}
