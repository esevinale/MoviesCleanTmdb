package com.esevinale.movieguidetmdb.presentation;

import android.app.Activity;
import android.app.Application;

import com.esevinale.movieguidetmdb.BuildConfig;
import com.esevinale.movieguidetmdb.presentation.internal.di.components.DaggerAppComponent;
import com.esevinale.movieguidetmdb.presentation.view.lifecycle.ActivityCallbacks;
import com.squareup.leakcanary.LeakCanary;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import timber.log.Timber;

public class AndroidApp extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> mDispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        this.initializeInjector();
        this.initializeDebugLibs();
        this.initRealm();
        registerActivityLifecycleCallbacks(new ActivityCallbacks());
    }

    private void initializeInjector() {
        DaggerAppComponent
                .builder()
                .context(this)
                .build()
                .inject(this);
    }

    private void initializeDebugLibs() {
        if (BuildConfig.DEBUG) {
            LeakCanary.install(this);
            Timber.plant(new Timber.DebugTree());
        }
    }

    private void initRealm() {
        Realm.init(this);
        RealmConfiguration realmConfiguration;
        if (BuildConfig.DEBUG)
            realmConfiguration = new RealmConfiguration
                    .Builder()
                    .inMemory()
                    .name("test")
                    .deleteRealmIfMigrationNeeded()
                    .build();
        else
            realmConfiguration = new RealmConfiguration
                    .Builder()
                    .deleteRealmIfMigrationNeeded()
                    .build();

        Realm.setDefaultConfiguration(realmConfiguration);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return mDispatchingAndroidInjector;
    }
}