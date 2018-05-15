package com.esevinale.movieguidetmdb.data.entity.mapper;

import com.esevinale.movieguidetmdb.data.entity.movieDetails.CreditsEntity;
import com.esevinale.movieguidetmdb.data.entity.movieDetails.MovieDetailsEntity;
import com.esevinale.movieguidetmdb.data.entity.trailer.TrailerEntity;
import com.esevinale.movieguidetmdb.domain.entity.details.MovieDetailsDomainModel;

import java.util.List;

import javax.inject.Inject;

public class MovieDetailsDataComposer {

    private final MovieDetailsMapper mMovieDetailsMapper;
    private final TrailerMapper mTrailerMapper;
    private final MovieCreditsMapper mMovieCreditsMapper;

    @Inject
    public MovieDetailsDataComposer(MovieDetailsMapper mMovieDetailsMapper, TrailerMapper mTrailerMapper, MovieCreditsMapper mMovieCreditsMapper) {
        this.mMovieDetailsMapper = mMovieDetailsMapper;
        this.mTrailerMapper = mTrailerMapper;
        this.mMovieCreditsMapper = mMovieCreditsMapper;
    }

    public MovieDetailsDomainModel composeToModel(MovieDetailsEntity movieDetailsEntity, List<TrailerEntity> trailerEntities, CreditsEntity creditsEntity) {
        MovieDetailsDomainModel detailsDomainModel = new MovieDetailsDomainModel();
        detailsDomainModel.setMovieDetails(mMovieDetailsMapper.transformDetails(movieDetailsEntity));
        detailsDomainModel.setTrailers(mTrailerMapper.transformList(trailerEntities));
        detailsDomainModel.setCast(mMovieCreditsMapper.transformList(creditsEntity.getCast()));
        return detailsDomainModel;
    }
}
