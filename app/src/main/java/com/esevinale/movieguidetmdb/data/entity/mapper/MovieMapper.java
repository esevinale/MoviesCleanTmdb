package com.esevinale.movieguidetmdb.data.entity.mapper;

import com.esevinale.movieguidetmdb.data.entity.movies.MovieEntity;
import com.esevinale.movieguidetmdb.domain.entity.Movie;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MovieMapper {
    @Inject
    MovieMapper() {
    }

    public Movie transform(MovieEntity movieEntity) {
        Movie movie = new Movie();
        movie.setVoteCount(movieEntity.getVoteCount());
        movie.setId(movieEntity.getId());
        movie.setVideo(movieEntity.getVideo());
        movie.setVoteAverage(movieEntity.getVoteAverage());
        movie.setTitle(movieEntity.getTitle());
        movie.setPopularity(movieEntity.getPopularity());
        movie.setPosterPath(movieEntity.getPosterPath());
        movie.setOriginalLanguage(movieEntity.getOriginalLanguage());
        movie.setOriginalTitle(movieEntity.getOriginalTitle());
        movie.setGenreIds(movieEntity.getGenreIds());
        movie.setBackdropPath(movieEntity.getBackdropPath());
        movie.setAdult(movieEntity.getAdult());
        movie.setOverview(movieEntity.getOverview());
        movie.setReleaseDate(movieEntity.getReleaseDate());
        return movie;
    }

    public List<Movie> transformList(List<MovieEntity> movieEntities) {
        List<Movie> movies = new ArrayList<>();
        for (MovieEntity movieEntity : movieEntities) {
            if (movieEntity != null) {
                movies.add(transform(movieEntity));
            }
        }
        return movies;
    }
}
