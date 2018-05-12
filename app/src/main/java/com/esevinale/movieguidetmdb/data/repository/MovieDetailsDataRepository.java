package com.esevinale.movieguidetmdb.data.repository;

import com.esevinale.movieguidetmdb.data.entity.mapper.MovieDetailsMapper;
import com.esevinale.movieguidetmdb.data.repository.datasource.details.MovieDetailsDataStore;
import com.esevinale.movieguidetmdb.data.repository.datasource.details.MovieDetailsDataStoreFactory;
import com.esevinale.movieguidetmdb.domain.entity.details.MovieDetails;
import com.esevinale.movieguidetmdb.domain.repository.MovieDetailsRepository;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class MovieDetailsDataRepository implements MovieDetailsRepository {

    private final MovieDetailsMapper mDetailsMapper;
    private final MovieDetailsDataStoreFactory mDetailsDataStore;

    @Inject
    MovieDetailsDataRepository(MovieDetailsMapper detailsMapper, MovieDetailsDataStoreFactory detailsDataStore) {
        this.mDetailsMapper = detailsMapper;
        this.mDetailsDataStore = detailsDataStore;
    }

    @Override
    public Flowable<MovieDetails> movieDetails(int id) {
        return mDetailsDataStore.create().movieDetails(id).map(mDetailsMapper::transformDetails);
    }
}
