package com.esevinale.movieguidetmdb.presentation.mapper;

import com.esevinale.movieguidetmdb.domain.entity.Movie;
import com.esevinale.movieguidetmdb.presentation.internal.di.scopes.PerActivity;
import com.esevinale.movieguidetmdb.presentation.model.MovieModel;
import com.esevinale.movieguidetmdb.presentation.model.MyInteger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

@PerActivity
public class MovieModelDataMapper {

    @Inject
    public MovieModelDataMapper() {
    }

    public MovieModel transform(Movie movie) {
        if (movie == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        return new MovieModel(movie.getVoteCount(), movie.getId(), movie.getVideo(), movie.getVoteAverage(), movie.getTitle(), movie.getPopularity(),
                movie.getPosterPath(), movie.getOriginalLanguage(), movie.getOriginalTitle(), movie.getBackdropPath(), movie.getAdult(),
                movie.getOverview(), movie.getReleaseDate());
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
