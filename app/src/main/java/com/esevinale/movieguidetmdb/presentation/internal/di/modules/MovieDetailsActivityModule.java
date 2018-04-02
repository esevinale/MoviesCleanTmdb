package com.esevinale.movieguidetmdb.presentation.internal.di.modules;

import com.esevinale.movieguidetmdb.data.repository.MovieDetailsDataRepository;
import com.esevinale.movieguidetmdb.data.repository.MovieImagesRepository;
import com.esevinale.movieguidetmdb.data.repository.MovieTrailersRepository;
import com.esevinale.movieguidetmdb.data.repository.datasource.details.MovieDetailsCouldDataStore;
import com.esevinale.movieguidetmdb.data.repository.datasource.details.MovieDetailsDataStore;
import com.esevinale.movieguidetmdb.data.repository.datasource.image.ImageDataStore;
import com.esevinale.movieguidetmdb.data.repository.datasource.image.ImagesCouldDataStore;
import com.esevinale.movieguidetmdb.data.repository.datasource.movie.MovieDataStore;
import com.esevinale.movieguidetmdb.data.repository.datasource.trailer.TrailerCouldDataStore;
import com.esevinale.movieguidetmdb.data.repository.datasource.trailer.TrailerDataStore;
import com.esevinale.movieguidetmdb.domain.repository.ImageRepository;
import com.esevinale.movieguidetmdb.domain.repository.MovieDetailsRepository;
import com.esevinale.movieguidetmdb.domain.repository.TrailerRepository;
import com.esevinale.movieguidetmdb.presentation.internal.di.scopes.PerActivity;
import com.esevinale.movieguidetmdb.presentation.internal.di.scopes.PerFragment;
import com.esevinale.movieguidetmdb.presentation.view.fragment.MovieDetailsFragment;
import com.esevinale.movieguidetmdb.presentation.view.fragment.tabFragments.NowPlayingMovieListFragment;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface MovieDetailsActivityModule {

    @PerActivity
    @Binds
    TrailerRepository trailerRepository(MovieTrailersRepository movieTrailersRepository);

    @PerActivity
    @Binds
    ImageRepository imageRepository(MovieImagesRepository movieImagesRepository);

    @PerActivity
    @Binds
    MovieDetailsRepository detailsRepository(MovieDetailsDataRepository movieDetailsDataRepository);

    @PerActivity
    @Binds
    ImageDataStore imagesDataStore(ImagesCouldDataStore imagesCouldDataStore);

    @PerActivity
    @Binds
    MovieDetailsDataStore detailsDataStore(MovieDetailsCouldDataStore movieDetailsCouldDataStore);

    @PerActivity
    @Binds
    TrailerDataStore trailerDataStore(TrailerCouldDataStore trailerCouldDataStore);

    @PerFragment
    @ContributesAndroidInjector(modules = {DetailsFragmentModule.class})
    MovieDetailsFragment detailsFragment();
}
