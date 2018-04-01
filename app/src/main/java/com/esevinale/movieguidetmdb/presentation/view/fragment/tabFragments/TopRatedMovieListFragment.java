package com.esevinale.movieguidetmdb.presentation.view.fragment.tabFragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.esevinale.movieguidetmdb.presentation.AndroidApp;
import com.esevinale.movieguidetmdb.presentation.presenter.MovieListPresenter;
import com.esevinale.movieguidetmdb.presentation.presenter.MovieTopRatedPresenter;
import com.esevinale.movieguidetmdb.presentation.presenter.MovieUpcomingPresenter;
import com.esevinale.movieguidetmdb.presentation.view.fragment.MovieListFragment;

import javax.inject.Inject;

public class TopRatedMovieListFragment extends MovieListFragment {

    @Inject
    MovieTopRatedPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApp.getApplicationComponent().create().inject(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        presenter.setView(this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected MovieListPresenter getPresenter() {
        return presenter;
    }


}
