package com.esevinale.movieguidetmdb.presentation.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.util.Log;
import android.view.View;

import com.esevinale.movieguidetmdb.R;
import com.esevinale.movieguidetmdb.presentation.model.MovieModel;
import com.esevinale.movieguidetmdb.presentation.presenter.MovieListPresenter;
import com.esevinale.movieguidetmdb.presentation.view.MovieListView;
import com.esevinale.movieguidetmdb.presentation.view.adapters.MovieListAdapter;
import com.esevinale.movieguidetmdb.presentation.view.utils.MyGridLayoutManager;

import java.util.List;

import butterknife.BindView;

public abstract class MovieListFragment extends BaseFragment implements MovieListView {

    @BindView(R.id.rv_movie)
    RecyclerView mRecyclerView;
    private MovieListAdapter movieListAdapter;

        @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpSwipeToRefreshLayout();
        setUpRecyclerView();
        setUpAdapter();
        setRetainInstance(true);
    }

    private void setUpAdapter() {
        movieListAdapter = new MovieListAdapter(this);
        mRecyclerView.setAdapter(movieListAdapter);
    }

    private void setUpRecyclerView() {
        MyGridLayoutManager myGridLayoutManager = new MyGridLayoutManager(getActivity(), 2);
        mRecyclerView.setLayoutManager(myGridLayoutManager);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (myGridLayoutManager.isOnNextPagePosition())
                    getPresenter().loadNextMovies((movieListAdapter.getItemCount() / 20) + 1);
            }
        });

        ((SimpleItemAnimator) mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
    }

    @Override
    public void viewMovie(MovieModel movieModel) {

    }

    private void setUpSwipeToRefreshLayout() {
        mSwipe.setOnRefreshListener(this::loadRefresh);
        mSwipe.setColorSchemeResources(R.color.colorAccent);
        mProgressBar = getMainctivity().getProgressBar();
    }

    private void loadRefresh() {
        getPresenter().loadRefresh();
    }

    @Override
    public void renderMovieList(List<MovieModel> movieModelList) {
        movieListAdapter.setMovies(movieModelList);
    }

    @Override
    public void addMoviesToList(List<MovieModel> movieModelList) {
        movieListAdapter.addMovies(movieModelList);
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
    public void showRefreshing() {
        mSwipe.setRefreshing(true);
    }

    @Override
    public void hideRefreshing() {
        mSwipe.setRefreshing(false);
    }

    @Override
    public void showError(String message) {
        showToastMessage(message);
    }

    protected abstract MovieListPresenter getPresenter();

    @Override
    public Context context() {
        return getMainctivity().getApplicationContext();
    }
}
