package com.esevinale.movieguidetmdb.domain.entity.details;

import com.esevinale.movieguidetmdb.domain.entity.Trailer;

import java.util.List;

public class MovieDetailsDomainModel {
    private MovieDetails movieDetails;
    private List<Trailer> trailers;
    private List<Cast> cast;

    public MovieDetailsDomainModel() {
    }

    public MovieDetailsDomainModel(MovieDetails movieDetails, List<Trailer> trailers, List<Cast> cast) {
        this.movieDetails = movieDetails;
        this.trailers = trailers;
        this.cast = cast;
    }

    public MovieDetails getMovieDetails() {
        return movieDetails;
    }

    public void setMovieDetails(MovieDetails movieDetails) {
        this.movieDetails = movieDetails;
    }

    public List<Trailer> getTrailers() {
        return trailers;
    }

    public void setTrailers(List<Trailer> trailers) {
        this.trailers = trailers;
    }

    public List<Cast> getCast() {
        return cast;
    }

    public void setCast(List<Cast> cast) {
        this.cast = cast;
    }
}
