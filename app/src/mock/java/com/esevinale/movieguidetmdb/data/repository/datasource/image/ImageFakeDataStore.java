package com.esevinale.movieguidetmdb.data.repository.datasource.image;

import android.content.Context;

import com.esevinale.movieguidetmdb.data.entity.images.ImagesEntity;
import com.google.gson.Gson;

import java.io.IOException;

import io.reactivex.Flowable;

import static com.esevinale.movieguidetmdb.data.repository.datasource.JsonUtils.inputStreamToString;

public class ImageFakeDataStore implements ImageDataStore {

    private Context mContext;
    private static final String MOVIE_IMAGES_FILE = "movie_images.json";

    ImageFakeDataStore(Context context) {
        this.mContext = context;
    }

    @Override
    public Flowable<ImagesEntity> movieImages(int id) {
        return flowableFromJson(MOVIE_IMAGES_FILE, mContext);
    }

    private Flowable<ImagesEntity> flowableFromJson(String fileName, Context context) {
        try {
            String myJson = inputStreamToString(context.getAssets().open(fileName));
            return Flowable.just(new Gson().fromJson(myJson, ImagesEntity.class));
        } catch (IOException e) {
            return Flowable.error(new IOException());
        }
    }
}
