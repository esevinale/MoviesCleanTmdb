package com.esevinale.movieguidetmdb.presentation.mapper;

import com.esevinale.movieguidetmdb.data.entity.mapper.MovieDetailsMapper;
import com.esevinale.movieguidetmdb.data.entity.movieDetails.GenreEntity;
import com.esevinale.movieguidetmdb.data.entity.movieDetails.MovieDetailsEntity;
import com.esevinale.movieguidetmdb.data.entity.movieDetails.ProductionCompanyEntity;
import com.esevinale.movieguidetmdb.domain.entity.details.Genre;
import com.esevinale.movieguidetmdb.domain.entity.details.MovieDetails;
import com.esevinale.movieguidetmdb.domain.entity.details.ProductionCompany;
import com.esevinale.movieguidetmdb.presentation.model.details.GenreModel;
import com.esevinale.movieguidetmdb.presentation.model.details.MovieDetailsModel;
import com.esevinale.movieguidetmdb.presentation.model.details.ProductionCompanyModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MovieDetailsDataMapper {
    @Inject
    public MovieDetailsDataMapper() {
    }

    public GenreModel transformGenre(Genre genre) {
        return new GenreModel(genre.getId(), genre.getName());
    }

    public ProductionCompanyModel transformCompany(ProductionCompany productionCompany) {
        return new ProductionCompanyModel(productionCompany.getId(), productionCompany.getLogoPath(), productionCompany.getName(), productionCompany.getOriginCountry());
    }

    public MovieDetailsModel transformDetails(MovieDetails movieDetails) {
        List<GenreModel> genres = new ArrayList<>();
        List<ProductionCompanyModel> productionCompanies = new ArrayList<>();
        for (Genre genre : movieDetails.getGenres()) {
            if (genre != null)
                genres.add(transformGenre(genre));
        }
        for (ProductionCompany productionCompany : movieDetails.getProductionCompanies()) {
            if (productionCompany != null)
                productionCompanies.add(transformCompany(productionCompany));
        }
        return new MovieDetailsModel(movieDetails.getAdult(), movieDetails.getBudget(), genres, movieDetails.getId(), movieDetails.getImdbId(), movieDetails.getOriginalLanguage(),
                movieDetails.getOriginalTitle(), movieDetails.getOverview(), movieDetails.getPopularity(), productionCompanies, movieDetails.getReleaseDate(), movieDetails.getRevenue(),
                movieDetails.getRuntime(), movieDetails.getStatus(), movieDetails.getTagline(), movieDetails.getTitle(), movieDetails.getVoteAverage(), movieDetails.getVoteCount(), movieDetails.getBackdropPath(), movieDetails.getHomepage());
    }
}
