package com.esevinale.movieguidetmdb.data.repository.datasource.search;

import android.content.Context;

import com.esevinale.movieguidetmdb.data.repository.datasource.trailer.TrailerDataStore;
import com.esevinale.movieguidetmdb.data.repository.datasource.trailer.TrailerFakeDataStore;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SearchDataStoreFactory {

    private Context mContext;

    @Inject
    SearchDataStoreFactory(Context context) {
        this.mContext = context;
    }

    public SearchDataStore create() {
        return new SearchFakeDataStore(mContext);
    }
}
