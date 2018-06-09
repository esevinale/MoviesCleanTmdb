package com.esevinale.movieguidetmdb.presentation.internal.di.modules.searchModules;

import com.esevinale.movieguidetmdb.data.repository.SearchDataRepository;
import com.esevinale.movieguidetmdb.data.repository.datasource.search.SearchCloudDataStore;
import com.esevinale.movieguidetmdb.data.repository.datasource.search.SearchDataStore;
import com.esevinale.movieguidetmdb.domain.repository.SearchRepository;
import com.esevinale.movieguidetmdb.presentation.internal.di.scopes.PerActivity;
import com.esevinale.movieguidetmdb.presentation.internal.di.scopes.PerFragment;
import com.esevinale.movieguidetmdb.presentation.view.fragment.SearchFragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface SearchActivityModule {

    @PerActivity
    @Binds
    SearchRepository searchRepository(SearchDataRepository searchDataRepository);

    @PerActivity
    @Binds
    SearchDataStore searchDataStore(SearchCloudDataStore searchCloudDataStore);

    @PerFragment
    @ContributesAndroidInjector(modules = {SearchFragmentModule.class})
    SearchFragment searchFragment();
}
