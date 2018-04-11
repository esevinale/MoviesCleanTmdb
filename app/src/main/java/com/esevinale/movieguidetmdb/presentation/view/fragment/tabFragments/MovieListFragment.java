package com.esevinale.movieguidetmdb.presentation.view.fragment.tabFragments;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.esevinale.movieguidetmdb.R;
import com.esevinale.movieguidetmdb.presentation.model.MovieModel;
import com.esevinale.movieguidetmdb.presentation.presenter.movieListPresenters.MovieListPresenter;
import com.esevinale.movieguidetmdb.presentation.view.ClicableMovieListView;
import com.esevinale.movieguidetmdb.presentation.view.activity.MainActivity;
import com.esevinale.movieguidetmdb.presentation.view.activity.MovieDetailsActivity;
import com.esevinale.movieguidetmdb.presentation.view.adapters.MovieListAdapter;
import com.esevinale.movieguidetmdb.presentation.view.fragment.BaseFragment;
import com.esevinale.movieguidetmdb.presentation.view.utils.Constants;
import com.esevinale.movieguidetmdb.presentation.view.utils.MyGridLayoutManager;

import java.util.List;

import butterknife.BindView;

public abstract class MovieListFragment extends BaseFragment implements ClicableMovieListView {

    @BindView(R.id.movie_swipe)
    SwipeRefreshLayout mSwipe;
    @BindView(R.id.rv_movie)
    RecyclerView mRecyclerView;
    private MovieListAdapter movieListAdapter;
    private View transactionView;

    @Override
    protected int getMainContentLayout() {
        return R.layout.fragment_movie_list;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = super.onCreateView(inflater, container, savedInstanceState);
        setUpRecyclerView();
        setUpSwipeToRefreshLayout();
        setUpAdapter();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState == null)
            getPresenter().initialize();
    }

    private void setUpAdapter() {
        movieListAdapter = new MovieListAdapter(this);
        mRecyclerView.setAdapter(movieListAdapter);
    }

    private void setUpRecyclerView() {
        MyGridLayoutManager myGridLayoutManager = new MyGridLayoutManager(getActivity());
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
    public void onResume() {
        super.onResume();
        getPresenter().resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        getPresenter().pause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getPresenter().destroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mRecyclerView != null)
            mRecyclerView.setAdapter(null);
    }

    @Override
    public void viewMovie(MovieModel movieModel) {
        Intent intent = new Intent(getBaseActivity(), MovieDetailsActivity.class);
        Bundle extras = new Bundle();
        extras.putInt(Constants.MOVIE_ID, movieModel.getId());
        extras.putParcelable(Constants.MOVIE_MODEL, movieModel);
        intent.putExtras(extras);
        startActivity(intent);
    }

    private void setUpSwipeToRefreshLayout() {
        mSwipe.setOnRefreshListener(this::loadRefresh);
        mSwipe.setColorSchemeResources(R.color.colorAccent);
        mProgressBar = getBaseActivity().getProgressBar();
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
        return getBaseActivity().getApplicationContext();
    }

    @Override
    public void onMovieClicked(MovieModel movie) {
        getPresenter().onMovieClicked(movie);
    }
}
