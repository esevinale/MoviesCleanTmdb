package com.esevinale.movieguidetmdb.presentation.view.utils;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;

public class MyGridLayoutManager extends GridLayoutManager {

    public MyGridLayoutManager(Context context) {
        super(context, DisplayUtils.getSpanCount(context));
    }

    public boolean isOnNextPagePosition() {
        int visibleItemCount = getChildCount();
        int totalItemCount = getItemCount();
        int pastVisibleItems = findFirstVisibleItemPosition();
        return (visibleItemCount + pastVisibleItems) >= totalItemCount-4;
    }
}
