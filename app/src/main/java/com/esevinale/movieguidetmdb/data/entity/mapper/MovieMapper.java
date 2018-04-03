package com.esevinale.movieguidetmdb.data.entity.mapper;

import com.esevinale.movieguidetmdb.data.entity.movies.MovieEntity;
import com.esevinale.movieguidetmdb.domain.entity.Movie;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MovieMapper {
    @Inject
    MovieMapper() { }

    public Movie transform(MovieEntity movieEntity){
        return new Movie(movieEntity.getVoteCount(), movieEntity.getId(), movieEntity.getVideo(), movieEntity.getVoteAverage(), movieEntity.getTitle(), movieEntity.getPopularity(),
                movieEntity.getPosterPath(), movieEntity.getOriginalLanguage(), movieEntity.getOriginalTitle(), movieEntity.getGenreIds(), movieEntity.getBackdropPath(), movieEntity.getAdult(),
                movieEntity.getOverview(), movieEntity.getReleaseDate());
    }

    public List<Movie> transformList(List<MovieEntity> movieEntities){
        List<Movie> movies = new ArrayList<>();
        for(MovieEntity movieEntity:movieEntities){
            if(movieEntity!=null){
                movies.add(transform(movieEntity));
            }
        }
        return movies;
    }
}
