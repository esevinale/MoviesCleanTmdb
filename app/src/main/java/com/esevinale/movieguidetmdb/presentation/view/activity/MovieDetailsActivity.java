package com.esevinale.movieguidetmdb.presentation.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import com.esevinale.movieguidetmdb.R;
import com.esevinale.movieguidetmdb.presentation.view.fragment.MovieDetailsFragment;
import com.esevinale.movieguidetmdb.presentation.view.utils.Constants;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class MovieDetailsActivity extends BaseActivity  implements HasSupportFragmentInjector {

    private int movieID;

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentInjector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);

        initializeActivity(savedInstanceState);
    }

    private void initializeActivity(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            movieID = getIntent().getIntExtra(Constants.MOVIE_ID, -1);
            getSupportFragmentManager().beginTransaction().replace(R.id.details_wrapper, MovieDetailsFragment.getInstance(movieID)).commit();
        } else {
            movieID = savedInstanceState.getInt(Constants.MOVIE_ID);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (outState != null) {
            outState.putInt(Constants.MOVIE_ID, movieID);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_movie_details;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentInjector;
    }
}
