package com.esevinale.movieguidetmdb.presentation.internal.di.modules;

import com.esevinale.movieguidetmdb.data.cache.MovieCache;
import com.esevinale.movieguidetmdb.data.cache.MovieCacheImpl;
import com.esevinale.movieguidetmdb.data.executor.WorkExecutor;
import com.esevinale.movieguidetmdb.data.repository.MovieDataRepository;
import com.esevinale.movieguidetmdb.domain.executor.PostExecutionThread;
import com.esevinale.movieguidetmdb.domain.executor.ThreadExecutor;
import com.esevinale.movieguidetmdb.domain.repository.MovieRepository;
import com.esevinale.movieguidetmdb.presentation.UIThread;
import com.esevinale.movieguidetmdb.presentation.internal.di.modules.movieListModules.MovieListActivityModule;
import com.esevinale.movieguidetmdb.presentation.internal.di.modules.searchModules.SearchActivityModule;
import com.esevinale.movieguidetmdb.presentation.internal.di.scopes.PerActivity;
import com.esevinale.movieguidetmdb.presentation.view.activity.MovieDetailsActivity;
import com.esevinale.movieguidetmdb.presentation.view.activity.MovieListActivity;
import com.esevinale.movieguidetmdb.presentation.view.activity.SearchActivity;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Module (includes = {AndroidSupportInjectionModule.class, NetModule.class})
public interface AppModule {


    @PerActivity
    @ContributesAndroidInjector(modules = MovieListActivityModule.class)
    MovieListActivity listActivityInjector();

    @PerActivity
    @ContributesAndroidInjector(modules = MovieDetailsActivityModule.class)
    MovieDetailsActivity detailsActivityInjector();

    @PerActivity
    @ContributesAndroidInjector(modules = SearchActivityModule.class)
    SearchActivity searchActivityInjector();

    @Singleton
    @Binds
    ThreadExecutor provideThreadExecutor(WorkExecutor workExecutor);

    @Singleton
    @Binds
    PostExecutionThread providePostExecutionThread(UIThread uiThread);

    @Singleton
    @Binds
    MovieCache provideMovieCache(MovieCacheImpl movieCache);

    @Singleton
    @Binds
    MovieRepository movieRepository(MovieDataRepository movieDataRepository);
}
