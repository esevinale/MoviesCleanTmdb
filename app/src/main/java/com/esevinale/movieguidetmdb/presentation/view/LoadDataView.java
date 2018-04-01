package com.esevinale.movieguidetmdb.presentation.view;

import android.content.Context;

public interface LoadDataView {

    void showLoading();
    void hideLoading();

    void showRefreshing();
    void hideRefreshing();

    void showError(String message);

    Context context();
}