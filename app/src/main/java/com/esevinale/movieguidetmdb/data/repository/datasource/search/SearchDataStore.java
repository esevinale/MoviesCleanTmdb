package com.esevinale.movieguidetmdb.data.repository.datasource.search;

import com.esevinale.movieguidetmdb.data.entity.movies.MovieEntity;
import com.esevinale.movieguidetmdb.data.entity.people.PeopleEntity;
import com.esevinale.movieguidetmdb.data.entity.tvShow.TvShowEntity;

import java.util.List;

import io.reactivex.Flowable;

public interface SearchDataStore {
    Flowable<List<TvShowEntity>> tvSearch(String query);
    Flowable<List<MovieEntity>> movieSearch(String query);
    Flowable<List<PeopleEntity>> peopleSearch(String query);
}
