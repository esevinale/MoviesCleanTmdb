package com.esevinale.movieguidetmdb.presentation.mapper;

import com.esevinale.movieguidetmdb.domain.entity.Movie;
import com.esevinale.movieguidetmdb.presentation.internal.di.scopes.PerActivity;
import com.esevinale.movieguidetmdb.presentation.model.MovieModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

@PerActivity
public class MovieModelMapper {

    @Inject
    public MovieModelMapper() {
    }

    public MovieModel transform(Movie movie) {
        if (movie == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        return new MovieModel(movie.getId(), movie.getTitle(), movie.getPosterPath(), movie.getBackdropPath());
    }

    public List<MovieModel> transform(List<Movie> movieCollection) {
        List<MovieModel> userModelsCollection;
        if (movieCollection != null && !movieCollection.isEmpty()) {
            userModelsCollection = new ArrayList<>();
            for (Movie movie : movieCollection) {
                userModelsCollection.add(transform(movie));
            }
        } else {
            userModelsCollection = Collections.emptyList();
        }

        return userModelsCollection;
    }
}
