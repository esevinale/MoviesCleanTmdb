package com.esevinale.movieguidetmdb.presentation.internal.di.modules;

import android.content.Context;

import com.esevinale.movieguidetmdb.data.cache.MovieCache;
import com.esevinale.movieguidetmdb.data.cache.MovieCacheImpl;
import com.esevinale.movieguidetmdb.data.executor.WorkExecutor;
import com.esevinale.movieguidetmdb.data.repository.MovieDataRepository;
import com.esevinale.movieguidetmdb.domain.executor.PostExecutionThread;
import com.esevinale.movieguidetmdb.domain.executor.ThreadExecutor;
import com.esevinale.movieguidetmdb.domain.interactor.GetNowPlayingMovieList;
import com.esevinale.movieguidetmdb.domain.interactor.GetUpcomingMovieList;
import com.esevinale.movieguidetmdb.domain.repository.MovieRepository;
import com.esevinale.movieguidetmdb.presentation.AndroidApp;
import com.esevinale.movieguidetmdb.presentation.UIThread;
import com.esevinale.movieguidetmdb.presentation.internal.di.PerActivity;
import com.esevinale.movieguidetmdb.presentation.mapper.MovieModelDataMapper;
import com.esevinale.movieguidetmdb.presentation.presenter.MovieNowPlayingPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private final AndroidApp application;

    public AppModule(AndroidApp application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.application;
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(WorkExecutor workExecutor) {
        return workExecutor;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

    @Provides
    @Singleton
    MovieCache provideMovieCache(MovieCacheImpl movieCache) {
        return movieCache;
    }

    @Provides
    @Singleton
    MovieRepository movieRepository(MovieDataRepository movieDataRepository) {
        return movieDataRepository;
    }
}
