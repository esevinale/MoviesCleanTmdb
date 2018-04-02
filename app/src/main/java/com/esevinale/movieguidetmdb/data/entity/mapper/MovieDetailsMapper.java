package com.esevinale.movieguidetmdb.data.entity.mapper;

import com.esevinale.movieguidetmdb.data.entity.movieDetails.GenreEntity;
import com.esevinale.movieguidetmdb.data.entity.movieDetails.MovieDetailsEntity;
import com.esevinale.movieguidetmdb.data.entity.movieDetails.ProductionCompanyEntity;
import com.esevinale.movieguidetmdb.domain.entity.details.Genre;
import com.esevinale.movieguidetmdb.domain.entity.details.MovieDetails;
import com.esevinale.movieguidetmdb.domain.entity.details.ProductionCompany;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MovieDetailsMapper {
    @Inject
    public MovieDetailsMapper() {
    }

    public Genre transformGenre(GenreEntity genreEntity) {
        return new Genre(genreEntity.getId(), genreEntity.getName());
    }

    public ProductionCompany transformCompany(ProductionCompanyEntity productionCompanyEntity) {
        return new ProductionCompany(productionCompanyEntity.getId(), productionCompanyEntity.getLogoPath(), productionCompanyEntity.getName(), productionCompanyEntity.getOriginCountry());
    }

    public MovieDetails transformDetails(MovieDetailsEntity movieDetailsEntity) {
        List<Genre> genres = new ArrayList<>();
        List<ProductionCompany> productionCompanies = new ArrayList<>();
        for (GenreEntity genre : movieDetailsEntity.getGenres()) {
            if (genre != null)
                genres.add(transformGenre(genre));
        }
        for (ProductionCompanyEntity productionCompanyEntity : movieDetailsEntity.getProductionCompanies()) {
            if (productionCompanyEntity != null)
                productionCompanies.add(transformCompany(productionCompanyEntity));
        }
        return new MovieDetails(movieDetailsEntity.getAdult(), movieDetailsEntity.getBudget(), genres, movieDetailsEntity.getId(), movieDetailsEntity.getImdbId(), movieDetailsEntity.getOriginalLanguage(),
                movieDetailsEntity.getOriginalTitle(), movieDetailsEntity.getOverview(), movieDetailsEntity.getPopularity(), productionCompanies, movieDetailsEntity.getReleaseDate(), movieDetailsEntity.getRevenue(),
                movieDetailsEntity.getRuntime(), movieDetailsEntity.getStatus(), movieDetailsEntity.getTagline(), movieDetailsEntity.getTitle(), movieDetailsEntity.getVoteAverage(), movieDetailsEntity.getVoteCount(),
                movieDetailsEntity.getBackdropPath(), movieDetailsEntity.getHomepage());

    }
}
