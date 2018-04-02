package com.esevinale.movieguidetmdb.presentation.internal.di.components;

import android.content.Context;

import com.esevinale.movieguidetmdb.presentation.AndroidApp;
import com.esevinale.movieguidetmdb.presentation.internal.di.modules.AppModule;
import com.esevinale.movieguidetmdb.presentation.internal.di.modules.NetModule;
import com.esevinale.movieguidetmdb.presentation.view.activity.BaseActivity;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder context(Context context);
        AppComponent build();
    }

    void inject(AndroidApp application);
}