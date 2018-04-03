package com.esevinale.movieguidetmdb.data.cache;

import com.esevinale.movieguidetmdb.data.entity.movies.MovieEntity;
import com.esevinale.movieguidetmdb.data.entity.movies.MovieTypes;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.realm.Realm;
import io.realm.RealmResults;

public class MovieCacheImpl implements MovieCache {

    private static final long EXPIRATION_TIME = 60 * 10 * 1000;

    @Inject
    MovieCacheImpl() {
    }

    public boolean isExpired() {
        //not implemented
        return false;
    }

    @Override
    public boolean isCached() {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(MovieEntity.class).findAll() != null && realm.where(MovieEntity.class).findAll().size() > 0;
    }

    @Override
    public Flowable<List<MovieEntity>> get(int page, MovieTypes movieType) {
        return Flowable.fromCallable(() -> {
            Realm realm = Realm.getDefaultInstance();
            RealmResults<MovieEntity> list = realm.where(MovieEntity.class).equalTo("type", movieType.toString()).findAll();

            return realm.copyFromRealm(list).subList((page - 1) * 20, page * 20);
        });
    }

    @Override
    public void put(List<MovieEntity> movieEntities) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> {
            realm.where(MovieEntity.class).equalTo("type", movieEntities.get(0).getType()).findAll().deleteAllFromRealm();
            realm1.insert(movieEntities);
        });
    }
}
