package com.esevinale.movieguidetmdb.presentation.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.esevinale.movieguidetmdb.R;
import com.esevinale.movieguidetmdb.presentation.model.MovieModel;
import com.esevinale.movieguidetmdb.presentation.model.PeopleModel;
import com.esevinale.movieguidetmdb.presentation.model.TvShowModel;
import com.esevinale.movieguidetmdb.presentation.presenter.SearchPresenter;
import com.esevinale.movieguidetmdb.presentation.view.adapters.SearchMovieListAdapter;
import com.esevinale.movieguidetmdb.presentation.view.adapters.SearchPeopleListAdapter;
import com.esevinale.movieguidetmdb.presentation.view.adapters.SearchTvShowListAdapter;
import com.esevinale.movieguidetmdb.presentation.view.views.ClickableSearchView;
import com.esevinale.movieguidetmdb.presentation.view.views.SearchView;
import com.esevinale.movieguidetmdb.presentation.view.activity.SearchActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import dagger.android.support.AndroidSupportInjection;

public class SearchFragment extends BaseFragment implements SearchView, ClickableSearchView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_search)
    EditText mEtSearch;
    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;
    @BindView(R.id.rv_search_movies)
    RecyclerView mRecyclerViewMovies;
    @BindView(R.id.rv_search_tvshows)
    RecyclerView mRecyclerViewTv;
    @BindView(R.id.rv_search_people)
    RecyclerView mRecyclerViewPeople;
    @BindView(R.id.search_movie_section)
    LinearLayout mMovieSection;
    @BindView(R.id.search_tvshow_section)
    LinearLayout mTvSection;
    @BindView(R.id.search_people_section)
    LinearLayout mPeopleSection;
    @BindView(android.R.id.empty)
    LinearLayout mEmptySection;
    @BindView(R.id.tv_search_notfound)
    TextView mTvSearchNotFound;


    @Inject
    SearchPresenter mSearchPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidSupportInjection.inject(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mSearchPresenter.setView(this);
        View view = super.onCreateView(inflater, container, savedInstanceState);
        setUpActionBar();
        setUpSearch();
        return view;
    }

    private void setUpSearch() {
        mEtSearch.requestFocus();
        mEtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mSearchPresenter.searchTextTyped(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void setUpActionBar() {
        getSearchActivity().setSupportActionBar(toolbar);
        ActionBar actionBar = getSearchActivity().getSupportActionBar();
        if (actionBar == null)
            return;
        getSearchActivity().getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void showLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        showToastMessage(message);
    }

    @Override
    public Context context() {
        return getSearchActivity().getApplicationContext();
    }

    @Override
    protected int getMainContentLayout() {
        return R.layout.fragment_search;
    }

    private SearchActivity getSearchActivity() {
        return (SearchActivity) getActivity();
    }

    @Override
    public void renderMovieList(List<MovieModel> movieModelList) {
        if (movieModelList.isEmpty()) {
            mMovieSection.setVisibility(View.GONE);
            return;
        }
        mMovieSection.setVisibility(View.VISIBLE);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getSearchActivity(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerViewMovies.setLayoutManager(mLayoutManager);

        SearchMovieListAdapter movieListAdapter = new SearchMovieListAdapter(this);
        mRecyclerViewMovies.setAdapter(movieListAdapter);

        movieListAdapter.setMovies(movieModelList);
    }

    @Override
    public void renderTvShowLost(List<TvShowModel> tvShowModelList) {
        if (tvShowModelList.isEmpty()) {
            mTvSection.setVisibility(View.GONE);
            return;
        }
        mTvSection.setVisibility(View.VISIBLE);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getSearchActivity(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerViewTv.setLayoutManager(mLayoutManager);

        SearchTvShowListAdapter tvShowListAdapter = new SearchTvShowListAdapter(this);
        mRecyclerViewTv.setAdapter(tvShowListAdapter);

        tvShowListAdapter.setTvShows(tvShowModelList);
    }

    @Override
    public void renderPeopleList(List<PeopleModel> peopleModelList) {
        if (peopleModelList.isEmpty()) {
            mPeopleSection.setVisibility(View.GONE);
            return;
        }
        mPeopleSection.setVisibility(View.VISIBLE);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getSearchActivity(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerViewPeople.setLayoutManager(mLayoutManager);

        SearchPeopleListAdapter peopleListAdapter = new SearchPeopleListAdapter(this);
        mRecyclerViewPeople.setAdapter(peopleListAdapter);

        peopleListAdapter.setPeople(peopleModelList);
    }

    @Override
    public void hideAll() {
        hideDataSections();
        hideEmptyLayout();
    }

    @Override
    public void showEmptyLayout() {
        hideDataSections();
        mEmptySection.setVisibility(View.VISIBLE);
    }

    private void hideDataSections() {
        mPeopleSection.setVisibility(View.GONE);
        mMovieSection.setVisibility(View.GONE);
        mTvSection.setVisibility(View.GONE);
    }

    @Override
    public void hideEmptyLayout() {
        mEmptySection.setVisibility(View.GONE);
    }

    @Override
    public void onResume() {
        super.onResume();
        mSearchPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mSearchPresenter.pause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mSearchPresenter.destroy();
    }

    @Override
    public void onTvClicked(TvShowModel tvShowModel, View view) {

    }

    @Override
    public void onMovieClicked(MovieModel movieModel, View view) {

    }

    @Override
    public void onPeopleClicked(PeopleModel peopleModel, View view) {

    }
}
