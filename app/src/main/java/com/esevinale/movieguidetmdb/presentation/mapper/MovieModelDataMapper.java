package com.esevinale.movieguidetmdb.presentation.mapper;

import android.util.Log;

import com.esevinale.movieguidetmdb.domain.Movie;
import com.esevinale.movieguidetmdb.presentation.internal.di.PerActivity;
import com.esevinale.movieguidetmdb.presentation.model.MovieModel;

import java.util.ArrayList;
import java.util.Collection;
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
                movie.getPosterPath(), movie.getOriginalLanguage(), movie.getOriginalTitle(), movie.getGenreIds(), movie.getBackdropPath(), movie.getAdult(),
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