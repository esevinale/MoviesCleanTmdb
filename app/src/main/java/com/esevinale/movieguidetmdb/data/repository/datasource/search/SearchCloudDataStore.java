package com.esevinale.movieguidetmdb.data.repository.datasource.search;

import com.esevinale.movieguidetmdb.data.entity.movies.MovieEntity;
import com.esevinale.movieguidetmdb.data.entity.people.PeopleEntity;
import com.esevinale.movieguidetmdb.data.entity.search.MovieSearchEntity;
import com.esevinale.movieguidetmdb.data.entity.search.PeopleSearchEntity;
import com.esevinale.movieguidetmdb.data.entity.search.TvShowSearchEntity;
import com.esevinale.movieguidetmdb.data.entity.tvShow.TvShowEntity;
import com.esevinale.movieguidetmdb.data.net.MovieService;

import java.util.List;

import io.reactivex.Flowable;

public class SearchCloudDataStore implements SearchDataStore {

    private final MovieService mService;

    SearchCloudDataStore(MovieService service) {
        this.mService = service;
    }

    @Override
    public Flowable<List<TvShowEntity>> tvSearch(String query) {
        return mService.getTvShowSearch(query).map(TvShowSearchEntity::getResults);
    }

    @Override
    public Flowable<List<MovieEntity>> movieSearch(String query) {
        return mService.getMovieSearch(query).map(MovieSearchEntity::getResults);
    }

    @Override
    public Flowable<List<PeopleEntity>> peopleSearch(String query) {
        return mService.getPeopleSearch(query).map(PeopleSearchEntity::getResults);
    }
}
