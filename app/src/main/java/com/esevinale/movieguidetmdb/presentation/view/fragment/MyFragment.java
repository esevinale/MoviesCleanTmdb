package com.esevinale.movieguidetmdb.presentation.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.esevinale.movieguidetmdb.R;
import com.esevinale.movieguidetmdb.presentation.presenter.MovieListPresenter;
import com.esevinale.movieguidetmdb.presentation.presenter.MovieNowPlayingPresenter;

public class MyFragment extends MovieListFragment {
    @Override
    protected MovieListPresenter getPresenter() {
        return null;
    }
}
