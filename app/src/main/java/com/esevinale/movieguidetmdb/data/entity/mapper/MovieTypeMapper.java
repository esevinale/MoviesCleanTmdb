package com.esevinale.movieguidetmdb.data.entity.mapper;

import com.esevinale.movieguidetmdb.data.entity.movies.MovieEntity;
import com.esevinale.movieguidetmdb.data.entity.movies.MovieTypes;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MovieTypeMapper {
    @Inject
    MovieTypeMapper() { }

    public MovieEntity transform(MovieEntity movieEntity, MovieTypes movieType){
        movieEntity.setType(movieType.toString());
        return movieEntity;
    }

    public List<MovieEntity> transformList(List<MovieEntity> movieEntities, MovieTypes movieType){
        List<MovieEntity> movies = new ArrayList<>();
        for(MovieEntity movieEntity:movieEntities){
            if(movieEntity!=null){
                movies.add(transform(movieEntity, movieType));
            }
        }
        return movies;
    }
}
