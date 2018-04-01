package com.esevinale.movieguidetmdb.data.net;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.esevinale.movieguidetmdb.data.net.ApiConstants.CONNECT_TIMEOUT;

public class ApiConnection {

    public static MovieService movieService(Retrofit retrofit) {
        return retrofit.create(MovieService.class);
    }

    public static Retrofit getRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit
                .Builder()
                .baseUrl(ApiConstants.BASE_TMDB_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    public static OkHttpClient getOkHttpClient(ApiKeyInterceptor requestInterceptor) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new okhttp3.OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                .addInterceptor(loggingInterceptor)
                .addInterceptor(requestInterceptor)
                .build();
    }


}
