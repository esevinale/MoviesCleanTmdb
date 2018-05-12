package com.esevinale.movieguidetmdb.data.repository.datasource.trailer;

import android.content.Context;

import com.esevinale.movieguidetmdb.data.entity.trailer.TrailerEntity;
import com.esevinale.movieguidetmdb.data.entity.trailer.TrailerResultDTO;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import io.reactivex.Flowable;

import static com.esevinale.movieguidetmdb.data.repository.datasource.JsonUtils.inputStreamToString;

@SuppressWarnings("unchecked")
public class TrailerFakeDataStore implements TrailerDataStore {

    private Context mContext;
    private static final String MOVIE_TRAILER_FILE = "movie_trailers.json";

    public TrailerFakeDataStore(Context context) {
        this.mContext = context;
    }

    @Override
    public Flowable<List<TrailerEntity>> movieTrailers(int id) {
        return flowableFromJson(MOVIE_TRAILER_FILE, mContext);
    }

    private Flowable<List<TrailerEntity>> flowableFromJson(String fileName, Context context) {
        try {
            String myJson = inputStreamToString(context.getAssets().open(fileName));
            TrailerResultDTO trailerRes = new Gson().fromJson(myJson, TrailerResultDTO.class);
            return Flowable.fromArray(trailerRes.getResults());
        } catch (IOException e) {
            return Flowable.error(new IOException());
        }
    }
}
