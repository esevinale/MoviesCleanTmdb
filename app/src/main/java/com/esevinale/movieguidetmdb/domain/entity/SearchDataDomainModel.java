package com.esevinale.movieguidetmdb.domain.entity;

import com.esevinale.movieguidetmdb.domain.entity.people.People;

import java.util.List;

import io.reactivex.Flowable;

public class SearchDataDomainModel {
    private List<Movie> movieList;
    private List<People> peopleList;
    private List<TvShow> tvShowList;

    public SearchDataDomainModel(List<Movie> movieList, List<People> peopleList, List<TvShow> tvShowList) {
        this.movieList = movieList;
        this.peopleList = peopleList;
        this.tvShowList = tvShowList;
    }

    public SearchDataDomainModel() {
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }

    public List<People> getPeopleList() {
        return peopleList;
    }

    public void setPeopleList(List<People> peopleList) {
        this.peopleList = peopleList;
    }

    public List<TvShow> getTvShowList() {
        return tvShowList;
    }

    public void setTvShowList(List<TvShow> tvShowList) {
        this.tvShowList = tvShowList;
    }
}
