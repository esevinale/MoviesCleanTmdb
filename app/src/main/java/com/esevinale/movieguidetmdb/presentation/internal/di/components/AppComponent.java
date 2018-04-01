package com.esevinale.movieguidetmdb.presentation.internal.di.components;

import android.content.Context;

import com.esevinale.movieguidetmdb.presentation.internal.di.modules.AppModule;
import com.esevinale.movieguidetmdb.presentation.internal.di.modules.NetModule;
import com.esevinale.movieguidetmdb.presentation.presenter.MovieNowPlayingPresenter;
import com.esevinale.movieguidetmdb.presentation.view.activity.BaseActivity;
import com.esevinale.movieguidetmdb.presentation.view.fragment.BaseFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface AppComponent {

    void inject(BaseActivity baseActivity);

    Context context();

    ActivityComponent create();
}