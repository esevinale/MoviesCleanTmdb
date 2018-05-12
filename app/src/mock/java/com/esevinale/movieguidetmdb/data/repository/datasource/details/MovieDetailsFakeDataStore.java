package com.esevinale.movieguidetmdb.data.repository.datasource.details;

import android.content.Context;

import com.esevinale.movieguidetmdb.data.entity.movieDetails.MovieDetailsEntity;
import com.google.gson.Gson;

import java.io.IOException;

import io.reactivex.Flowable;

import static com.esevinale.movieguidetmdb.data.repository.datasource.JsonUtils.inputStreamToString;

public class MovieDetailsFakeDataStore implements MovieDetailsDataStore {

    private Context mContext;
    private static final String MOVIE_DETAILS_FILE = "movie_details.json";


    public MovieDetailsFakeDataStore(Context context) {
        this.mContext = context;
    }

    @Override
    public Flowable<MovieDetailsEntity> movieDetails(int id) {
        return flowableFromJson(MOVIE_DETAILS_FILE, mContext);
    }

    private Flowable<MovieDetailsEntity> flowableFromJson(String fileName, Context context) {
        try {
            String myJson = inputStreamToString(context.getAssets().open(fileName));
            return Flowable.just(new Gson().fromJson(myJson, MovieDetailsEntity.class));
        } catch (IOException e) {
            return Flowable.error(new IOException());
        }
    }
}
