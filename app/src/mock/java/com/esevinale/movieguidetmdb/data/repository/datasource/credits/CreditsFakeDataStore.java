package com.esevinale.movieguidetmdb.data.repository.datasource.credits;

import android.content.Context;

import com.esevinale.movieguidetmdb.data.entity.movieDetails.CreditsEntity;
import com.google.gson.Gson;

import java.io.IOException;

import io.reactivex.Flowable;

import static com.esevinale.movieguidetmdb.data.repository.datasource.JsonUtils.inputStreamToString;

public class CreditsFakeDataStore implements CreditsDataStore {

    private Context mContext;
    private static final String MOVIE_DETAILS_FILE = "movie_credits.json";


    public CreditsFakeDataStore(Context context) {
        this.mContext = context;
    }

    @Override
    public Flowable<CreditsEntity> movieCredits(int id) {
        return flowableFromJson(MOVIE_DETAILS_FILE, mContext);
    }

    private Flowable<CreditsEntity> flowableFromJson(String fileName, Context context) {
        try {
            String myJson = inputStreamToString(context.getAssets().open(fileName));
            return Flowable.just(new Gson().fromJson(myJson, CreditsEntity.class));
        } catch (IOException e) {
            return Flowable.error(new IOException());
        }
    }
}
