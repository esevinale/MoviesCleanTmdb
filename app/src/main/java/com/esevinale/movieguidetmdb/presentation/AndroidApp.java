package com.esevinale.movieguidetmdb.presentation;

import android.app.Application;

import com.esevinale.movieguidetmdb.BuildConfig;
import com.esevinale.movieguidetmdb.presentation.internal.di.components.AppComponent;
import com.esevinale.movieguidetmdb.presentation.internal.di.components.DaggerAppComponent;
import com.esevinale.movieguidetmdb.presentation.internal.di.modules.AppModule;
import com.squareup.leakcanary.LeakCanary;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class AndroidApp extends Application{

    private static AppComponent applicationComponent;

    @Override public void onCreate() {
        super.onCreate();
        this.initializeInjector();
        this.initializeLeakDetection();
        this.initRealm();
    }

    private void initializeInjector() {
        applicationComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static AppComponent getApplicationComponent() {
        return applicationComponent;
    }

    private void initializeLeakDetection() {
        if (BuildConfig.DEBUG) {
            LeakCanary.install(this);
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
}