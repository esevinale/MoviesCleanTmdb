package com.esevinale.movieguidetmdb.presentation.view.views;

import android.content.Context;

public interface LoadDataView {

    void showLoading();
    void hideLoading();

    void showError(String message);

    Context context();
}