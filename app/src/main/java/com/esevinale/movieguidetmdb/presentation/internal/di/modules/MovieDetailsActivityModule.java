package com.esevinale.movieguidetmdb.presentation.internal.di.modules;

import com.esevinale.movieguidetmdb.data.repository.MovieDetailsDataRepository;
import com.esevinale.movieguidetmdb.data.repository.datasource.credits.CreditsCloudDataStore;
import com.esevinale.movieguidetmdb.data.repository.datasource.credits.CreditsDataStore;
import com.esevinale.movieguidetmdb.data.repository.datasource.details.MovieDetailsCouldDataStore;
import com.esevinale.movieguidetmdb.data.repository.datasource.details.MovieDetailsDataStore;
import com.esevinale.movieguidetmdb.data.repository.datasource.trailer.TrailerCouldDataStore;
import com.esevinale.movieguidetmdb.data.repository.datasource.trailer.TrailerDataStore;
import com.esevinale.movieguidetmdb.domain.repository.MovieDetailsRepository;
import com.esevinale.movieguidetmdb.presentation.internal.di.scopes.PerActivity;
import com.esevinale.movieguidetmdb.presentation.internal.di.scopes.PerFragment;
import com.esevinale.movieguidetmdb.presentation.view.fragment.MovieDetailsFragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface MovieDetailsActivityModule {

    @PerActivity
    @Binds
    MovieDetailsRepository detailsRepository(MovieDetailsDataRepository movieDetailsDataRepository);

    @PerActivity
    @Binds
    MovieDetailsDataStore detailsDataStore(MovieDetailsCouldDataStore movieDetailsCouldDataStore);

    @PerActivity
    @Binds
    TrailerDataStore trailerDataStore(TrailerCouldDataStore trailerCouldDataStore);

    @PerActivity
    @Binds
    CreditsDataStore creditsDataStore(CreditsCloudDataStore creditsCloudDataStore);

    @PerFragment
    @ContributesAndroidInjector(modules = {DetailsFragmentModule.class})
    MovieDetailsFragment detailsFragment();
}
