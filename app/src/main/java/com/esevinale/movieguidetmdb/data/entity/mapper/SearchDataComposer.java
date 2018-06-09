package com.esevinale.movieguidetmdb.data.entity.mapper;

import com.esevinale.movieguidetmdb.data.entity.movies.MovieEntity;
import com.esevinale.movieguidetmdb.data.entity.people.PeopleEntity;
import com.esevinale.movieguidetmdb.data.entity.tvShow.TvShowEntity;
import com.esevinale.movieguidetmdb.domain.entity.SearchDataDomainModel;

import java.util.List;

import javax.inject.Inject;

public class SearchDataComposer {

    private final MovieMapper mMovieMapper;
    private final PeopleMapper mPeopleMapper;
    private final TvShowMapper mTvShowMapper;

    @Inject
    public SearchDataComposer(MovieMapper mMovieMapper, PeopleMapper mPeopleMapper, TvShowMapper mTvShowMapper) {
        this.mMovieMapper = mMovieMapper;
        this.mPeopleMapper = mPeopleMapper;
        this.mTvShowMapper = mTvShowMapper;
    }

    public SearchDataDomainModel composeToModel(List<MovieEntity> movieEntityList, List<PeopleEntity> peopleEntityList, List<TvShowEntity> tvShowEntityList) {
        SearchDataDomainModel searchDataDomainModel = new SearchDataDomainModel();
        searchDataDomainModel.setMovieList(mMovieMapper.transformList(movieEntityList));
        searchDataDomainModel.setPeopleList(mPeopleMapper.transformList(peopleEntityList));
        searchDataDomainModel.setTvShowList(mTvShowMapper.transformList(tvShowEntityList));
        return searchDataDomainModel;
    }
}
