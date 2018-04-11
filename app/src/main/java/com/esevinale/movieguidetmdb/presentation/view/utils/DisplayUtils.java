package com.esevinale.movieguidetmdb.presentation.view.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

public class DisplayUtils {

//    private final static int gridRateSize = 300;

    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (wm == null)
            return 800;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public static int getSpanCount(Context context) {
        int width = getScreenWidth(context);
        if (width > 1500)
            return 3;
        else
            return 2;
    }
}
