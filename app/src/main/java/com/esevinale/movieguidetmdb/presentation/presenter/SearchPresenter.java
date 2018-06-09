package com.esevinale.movieguidetmdb.presentation.presenter;

import android.support.annotation.NonNull;

import com.esevinale.movieguidetmdb.R;
import com.esevinale.movieguidetmdb.domain.entity.Movie;
import com.esevinale.movieguidetmdb.domain.entity.SearchDataDomainModel;
import com.esevinale.movieguidetmdb.domain.entity.TvShow;
import com.esevinale.movieguidetmdb.domain.entity.people.People;
import com.esevinale.movieguidetmdb.domain.interactor.DefaultSubscriber;
import com.esevinale.movieguidetmdb.domain.interactor.GetSearchData;
import com.esevinale.movieguidetmdb.presentation.internal.di.scopes.PerActivity;
import com.esevinale.movieguidetmdb.presentation.mapper.MovieModelMapper;
import com.esevinale.movieguidetmdb.presentation.mapper.PeopleModelMapper;
import com.esevinale.movieguidetmdb.presentation.mapper.TvShowModelMapper;
import com.esevinale.movieguidetmdb.presentation.model.MovieModel;
import com.esevinale.movieguidetmdb.presentation.model.PeopleModel;
import com.esevinale.movieguidetmdb.presentation.model.TvShowModel;
import com.esevinale.movieguidetmdb.presentation.model.image.PosterModel;
import com.esevinale.movieguidetmdb.presentation.view.utils.EspressoIdlingResource;
import com.esevinale.movieguidetmdb.presentation.view.views.SearchView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

@PerActivity
public class SearchPresenter implements Presenter {

    private SearchView mSearchView;

    private final GetSearchData mGetSearchData;
    private final MovieModelMapper mMovieModelMapper;
    private final TvShowModelMapper mTvModelMapper;
    private final PeopleModelMapper mPeopleModelMapper;

    @Inject
    SearchPresenter(GetSearchData mGetSearchData, MovieModelMapper mMovieModelMapper, TvShowModelMapper mTvModelMapper, PeopleModelMapper mPeopleModelMapper) {
        this.mGetSearchData = mGetSearchData;
        this.mMovieModelMapper = mMovieModelMapper;
        this.mTvModelMapper = mTvModelMapper;
        this.mPeopleModelMapper = mPeopleModelMapper;
    }

    public void setView(@NonNull SearchView searchView) {
        this.mSearchView = searchView;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        this.mSearchView = null;
        this.mGetSearchData.dispose();
    }

    public void searchTextTyped(String text) {
        mGetSearchData.clear();
        if (text.isEmpty()) {
            mSearchView.hideAll();
            mSearchView.hideLoading();
            return;
        }
        EspressoIdlingResource.increment();
        this.showViewLoading();
        this.mGetSearchData.execute(new SearchSubscriber(), text);
    }

    private void showViewLoading() {
        this.mSearchView.showLoading();
    }

    private void hideViewLoading() {
        this.mSearchView.hideLoading();
    }

    private void showErrorMessage() {
        this.mSearchView.showError(this.mSearchView.context().getString(R.string.default_error));
    }

    private void showMovieSearchResult(List<Movie> movieList) {
        final List<MovieModel> movieModel = mMovieModelMapper.transform(movieList);
        this.mSearchView.renderMovieList(movieModel);
    }

    private void showTvSearchResult(List<TvShow> tvShowList) {
        final List<TvShowModel> tvShowModels = mTvModelMapper.transform(tvShowList);
        this.mSearchView.renderTvShowLost(tvShowModels);
    }

    private void showPeopleearchResult(List<People> peopleList) {
        final List<PeopleModel> peopleModels = mPeopleModelMapper.transform(peopleList);
        this.mSearchView.renderPeopleList(peopleModels);
    }

    public void onMovieClicked(MovieModel movieModel) {
        this.mSearchView.startMovieActivity(movieModel);
    }

    public void onTvClicked(TvShowModel tvShowModel) {
        this.mSearchView.startTvActivity(tvShowModel);
    }

    public void onPersonClicked(PeopleModel peopleModel) {
        this.mSearchView.startPersonActivity(peopleModel);
    }
    private final class SearchSubscriber extends DefaultSubscriber<SearchDataDomainModel> {

        @Override
        public void onComplete() {
            SearchPresenter.this.hideViewLoading();
            EspressoIdlingResource.decrement();
        }

        @Override
        public void onError(Throwable e) {
            SearchPresenter.this.hideViewLoading();
            SearchPresenter.this.showErrorMessage();
        }

        @Override
        public void onNext(SearchDataDomainModel searchDataDomainModel) {
            if (searchDataDomainModel.getMovieList().isEmpty() && searchDataDomainModel.getPeopleList().isEmpty() && searchDataDomainModel.getTvShowList().isEmpty()) {
                mSearchView.showEmptyLayout();
                return;
            }
            mSearchView.hideEmptyLayout();
            SearchPresenter.this.showMovieSearchResult(searchDataDomainModel.getMovieList());
            SearchPresenter.this.showTvSearchResult(searchDataDomainModel.getTvShowList());
            SearchPresenter.this.showPeopleearchResult(searchDataDomainModel.getPeopleList());
        }
    }
}
