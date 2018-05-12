package com.esevinale.movieguidetmdb.data.repository.datasource.movie;

import android.content.Context;

import com.esevinale.movieguidetmdb.data.entity.movies.MovieEntity;
import com.esevinale.movieguidetmdb.data.entity.movies.MovieResultDTO;
import com.esevinale.movieguidetmdb.data.repository.datasource.JsonUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import io.reactivex.Flowable;

import static com.esevinale.movieguidetmdb.data.repository.datasource.JsonUtils.inputStreamToString;

@SuppressWarnings("unchecked")
public class MovieFakeDataStore implements MovieDataStore {

    private Context mContext;
    private static final String MOVIE_FILE = "movie_list.json";

    MovieFakeDataStore(Context context) {
        this.mContext = context;
    }

    @Override
    public Flowable<List<MovieEntity>> nowPlayingMovies(int page) {
        return flowableFromJson(MOVIE_FILE, mContext);
    }

    @Override
    public Flowable<List<MovieEntity>> popularMovies(int page) {
        return flowableFromJson(MOVIE_FILE, mContext);
    }

    @Override
    public Flowable<List<MovieEntity>> topRatedMovies(int page) {
        return flowableFromJson(MOVIE_FILE, mContext);
    }

    @Override
    public Flowable<List<MovieEntity>> upcomingMovies(int page) {
        return flowableFromJson(MOVIE_FILE, mContext);
    }

    private Flowable<List<MovieEntity>> flowableFromJson(String fileName, Context context) {
        try {
            String myJson = inputStreamToString(context.getAssets().open(fileName));
            MovieResultDTO moviesRes = new Gson().fromJson(myJson, MovieResultDTO.class);
            return Flowable.fromArray(moviesRes.getResults());
        } catch (IOException e) {
            return Flowable.error(new IOException());
        }
    }
}
