package com.esevinale.movieguidetmdb.data.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ConnectionChecker {
    private Context mContext;

    @Inject
    public ConnectionChecker(Context context) {
        this.mContext = context;
    }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm != null ? cm.getActiveNetworkInfo() : null;
        return ((networkInfo != null && networkInfo.isConnectedOrConnecting()));
    }
}
