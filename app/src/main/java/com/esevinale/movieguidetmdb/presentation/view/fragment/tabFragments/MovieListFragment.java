package com.esevinale.movieguidetmdb.presentation.view.fragment.tabFragments;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
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
    private MovieListAdapter mMovieListAdapter;
    private View mSelectedView;

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
        mMovieListAdapter = new MovieListAdapter(this);
        mRecyclerView.setAdapter(mMovieListAdapter);
    }

    private void setUpRecyclerView() {
        MyGridLayoutManager myGridLayoutManager = new MyGridLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(myGridLayoutManager);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (myGridLayoutManager.isOnNextPagePosition())
                    getPresenter().loadNextMovies((mMovieListAdapter.getItemCount() / 20) + 1);
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
        Bundle bundle = null;
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View v = mSelectedView.findViewById(R.id.movie_poster);
            v.setTransitionName(getMainActivity().getString(R.string.transaction_name));
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getMainActivity(), v, getMainActivity().getString(R.string.transaction_name));
            bundle = options.toBundle();
        }
        Intent intent = new Intent(getMainActivity(), MovieDetailsActivity.class);
        intent.putExtra(Constants.MOVIE_MODEL, movieModel);
        if (bundle == null)
            startActivity(intent);
        else
            startActivity(intent, bundle);
    }

    private void setUpSwipeToRefreshLayout() {
        mSwipe.setOnRefreshListener(this::loadRefresh);
        mSwipe.setColorSchemeResources(R.color.colorAccent);
        mProgressBar = getMainActivity().getProgressBar();
    }

    private void loadRefresh() {
        getPresenter().loadRefresh();
    }

    @Override
    public void renderMovieList(List<MovieModel> movieModelList) {
        mMovieListAdapter.setMovies(movieModelList);
    }

    @Override
    public void addMoviesToList(List<MovieModel> movieModelList) {
        mMovieListAdapter.addMovies(movieModelList);
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
        return getMainActivity().getApplicationContext();
    }

    @Override
    public void onMovieClicked(MovieModel movie, View view) {
        mSelectedView = view;
        getPresenter().onMovieClicked(movie);
    }

    private MainActivity getMainActivity() {
        return (MainActivity) getActivity();
    }
}
