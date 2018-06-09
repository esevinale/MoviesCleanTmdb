package com.esevinale.movieguidetmdb.presentation.view.views;

import android.view.View;

import com.esevinale.movieguidetmdb.presentation.model.details.CastModel;

public interface ClicableCastListView extends CastListView {
    void onCastClicked(CastModel castModel, View view);
}
