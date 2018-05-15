package com.esevinale.movieguidetmdb.data.repository;

import com.esevinale.movieguidetmdb.data.entity.mapper.MovieDetailsDataComposer;
import com.esevinale.movieguidetmdb.data.repository.datasource.credits.CreditsDataStoreFactory;
import com.esevinale.movieguidetmdb.data.repository.datasource.details.MovieDetailsDataStoreFactory;
import com.esevinale.movieguidetmdb.data.repository.datasource.trailer.TrailerDataStoreFactory;
import com.esevinale.movieguidetmdb.domain.entity.details.MovieDetailsDomainModel;
import com.esevinale.movieguidetmdb.domain.repository.MovieDetailsRepository;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class MovieDetailsDataRepository implements MovieDetailsRepository {

    private final MovieDetailsDataComposer mMovieDetailsComposer;
    private final MovieDetailsDataStoreFactory mDetailsStoreFactory;
    private final TrailerDataStoreFactory mTrailerStoreFactory;
    private final CreditsDataStoreFactory mCreditsStoreFactory;

    @Inject
    public MovieDetailsDataRepository(MovieDetailsDataComposer mMovieDetailsComposer, MovieDetailsDataStoreFactory mDetailsStoreFactory, TrailerDataStoreFactory mTrailerStoreFactory, CreditsDataStoreFactory mCreditsStoreFactory) {
        this.mMovieDetailsComposer = mMovieDetailsComposer;
        this.mDetailsStoreFactory = mDetailsStoreFactory;
        this.mTrailerStoreFactory = mTrailerStoreFactory;
        this.mCreditsStoreFactory = mCreditsStoreFactory;
    }

    @Override
    public Flowable<MovieDetailsDomainModel> movieDetails(int id) {
        return Flowable.zip(mDetailsStoreFactory.create().movieDetails(id),
                mTrailerStoreFactory.create().movieTrailers(id),
                mCreditsStoreFactory.create().movieCredits(id),
                mMovieDetailsComposer::composeToModel);
    }
}
