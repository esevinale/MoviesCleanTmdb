package com.esevinale.movieguidetmdb.data.repository.datasource.search;

import android.content.Context;

import com.esevinale.movieguidetmdb.data.entity.movies.MovieEntity;
import com.esevinale.movieguidetmdb.data.entity.movies.MovieResultDTO;
import com.esevinale.movieguidetmdb.data.entity.people.PeopleEntity;
import com.esevinale.movieguidetmdb.data.entity.search.MovieSearchEntity;
import com.esevinale.movieguidetmdb.data.entity.search.PeopleSearchEntity;
import com.esevinale.movieguidetmdb.data.entity.search.TvShowSearchEntity;
import com.esevinale.movieguidetmdb.data.entity.trailer.TrailerEntity;
import com.esevinale.movieguidetmdb.data.entity.trailer.TrailerResultDTO;
import com.esevinale.movieguidetmdb.data.entity.tvShow.TvShowEntity;
import com.esevinale.movieguidetmdb.data.repository.datasource.trailer.TrailerDataStore;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import io.reactivex.Flowable;

import static com.esevinale.movieguidetmdb.data.repository.datasource.JsonUtils.inputStreamToString;

@SuppressWarnings("unchecked")
public class SearchFakeDataStore implements SearchDataStore {

    private Context mContext;
    private static final String SEARCH_MOVIE_FILE = "movie_list.json";
    private static final String SEARCH_TV_FILE = "tv_list.json";
    private static final String SEARCH_PEOPLE_FILE = "person_list.json";

    public SearchFakeDataStore(Context context) {
        this.mContext = context;
    }

    @Override
    public Flowable<List<TvShowEntity>> tvSearch(String query) {
        return flowableTvsFromJson(SEARCH_TV_FILE, mContext);
    }

    @Override
    public Flowable<List<MovieEntity>> movieSearch(String query) {
        return flowableMoviesFromJson(SEARCH_MOVIE_FILE, mContext);
    }

    @Override
    public Flowable<List<PeopleEntity>> peopleSearch(String query) {
        return flowablePeopleFromJson(SEARCH_PEOPLE_FILE, mContext);
    }

    private Flowable<List<MovieEntity>> flowableMoviesFromJson(String fileName, Context context) {
        try {
            String myJson = inputStreamToString(context.getAssets().open(fileName));
            MovieSearchEntity movieRes = new Gson().fromJson(myJson, MovieSearchEntity.class);
            return Flowable.fromArray(movieRes.getResults());
        } catch (IOException e) {
            return Flowable.error(new IOException());
        }
    }

    private Flowable<List<TvShowEntity>> flowableTvsFromJson(String fileName, Context context) {
        try {
            String myJson = inputStreamToString(context.getAssets().open(fileName));
            TvShowSearchEntity tvShowSearchEntity = new Gson().fromJson(myJson, TvShowSearchEntity.class);
            return Flowable.fromArray(tvShowSearchEntity.getResults());
        } catch (IOException e) {
            return Flowable.error(new IOException());
        }
    }

    private Flowable<List<PeopleEntity>> flowablePeopleFromJson(String fileName, Context context) {
        try {
            String myJson = inputStreamToString(context.getAssets().open(fileName));
            PeopleSearchEntity peopleSearchEntity = new Gson().fromJson(myJson, PeopleSearchEntity.class);
            return Flowable.fromArray(peopleSearchEntity.getResults());
        } catch (IOException e) {
            return Flowable.error(new IOException());
        }
    }
}
