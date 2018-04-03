package com.esevinale.movieguidetmdb.data.repository;

import com.esevinale.movieguidetmdb.data.entity.mapper.MovieDetailsMapper;
import com.esevinale.movieguidetmdb.data.entity.mapper.MovieImagesMapper;
import com.esevinale.movieguidetmdb.data.repository.datasource.details.MovieDetailsDataStore;
import com.esevinale.movieguidetmdb.data.repository.datasource.image.ImageDataStore;
import com.esevinale.movieguidetmdb.domain.entity.details.MovieDetails;
import com.esevinale.movieguidetmdb.domain.entity.image.Images;
import com.esevinale.movieguidetmdb.domain.repository.ImageRepository;
import com.esevinale.movieguidetmdb.domain.repository.MovieDetailsRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;

public class MovieDetailsDataRepository implements MovieDetailsRepository {

    private final MovieDetailsMapper detailsMapper;
    private final MovieDetailsDataStore detailsDataStore;

    @Inject
    MovieDetailsDataRepository(MovieDetailsMapper detailsMapper, MovieDetailsDataStore detailsDataStore) {
        this.detailsMapper = detailsMapper;
        this.detailsDataStore = detailsDataStore;
    }

    @Override
    public Flowable<MovieDetails> movieDetails(int id) {
        return detailsDataStore.movieDetails(id).map(detailsMapper::transformDetails);
    }
}
