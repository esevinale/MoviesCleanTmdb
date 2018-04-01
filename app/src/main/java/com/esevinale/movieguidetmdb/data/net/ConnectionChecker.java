package com.esevinale.movieguidetmdb.data.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.net.HttpURLConnection;
import java.net.URL;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.realm.internal.Util;

@Singleton
public class ConnectionChecker {
    private Context context;

    @Inject
    public ConnectionChecker(Context context) {
        this.context = context;
    }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm != null ? cm.getActiveNetworkInfo() : null;
        return ((networkInfo != null && networkInfo.isConnectedOrConnecting()) || Util.isEmulator());
    }
}
