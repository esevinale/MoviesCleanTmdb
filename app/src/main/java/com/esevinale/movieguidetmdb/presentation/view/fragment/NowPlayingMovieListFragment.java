package com.esevinale.movieguidetmdb.presentation.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.esevinale.movieguidetmdb.presentation.AndroidApp;
import com.esevinale.movieguidetmdb.presentation.model.MovieModel;
import com.esevinale.movieguidetmdb.presentation.presenter.MovieListPresenter;
import com.esevinale.movieguidetmdb.presentation.presenter.MovieNowPlayingPresenter;

import javax.inject.Inject;

public class NowPlayingMovieListFragment extends MovieListFragment {

    @Inject
    MovieNowPlayingPresenter presenter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        presenter = AndroidApp.getApplicationComponent().create().provide();
        super.onViewCreated(view, savedInstanceState);
        presenter.setView(this);
        presenter.initialize();
    }

    @Override
    protected MovieListPresenter getPresenter() {
        return presenter;
    }
}
