package com.esevinale.movieguidetmdb.presentation.view.fragment.tabFragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.esevinale.movieguidetmdb.presentation.AndroidApp;
import com.esevinale.movieguidetmdb.presentation.presenter.movieListPresenters.MovieListPresenter;
import com.esevinale.movieguidetmdb.presentation.presenter.movieListPresenters.MoviePopularPresenter;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class PopularMovieListFragment extends MovieListFragment {

    @Inject
    MoviePopularPresenter mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidSupportInjection.inject(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mPresenter.setView(this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected MovieListPresenter getPresenter() {
        return mPresenter;
    }


}
