package com.esevinale.movieguidetmdb.presentation.internal.di.modules;

import com.esevinale.movieguidetmdb.data.net.ApiConnection;
import com.esevinale.movieguidetmdb.data.net.ApiKeyInterceptor;
import com.esevinale.movieguidetmdb.data.net.MovieService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

@Module
public class NetModule {
    @Provides
    @Singleton
    MovieService provideMovieService(Retrofit retrofit) {
        return ApiConnection.movieService(retrofit);
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient client) {
        return ApiConnection.getRetrofit(client);
    }

    @Provides
    @Singleton
    OkHttpClient provideClient(ApiKeyInterceptor interceptor) {
        return ApiConnection.getOkHttpClient(interceptor);
    }
}
