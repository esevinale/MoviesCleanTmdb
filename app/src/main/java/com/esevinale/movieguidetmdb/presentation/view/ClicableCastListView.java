package com.esevinale.movieguidetmdb.presentation.view;

import android.view.View;

import com.esevinale.movieguidetmdb.presentation.model.MovieModel;
import com.esevinale.movieguidetmdb.presentation.model.details.CastModel;

public interface ClicableCastListView extends CastListView {
    void onCastClicked(CastModel castModel, View view);
}
