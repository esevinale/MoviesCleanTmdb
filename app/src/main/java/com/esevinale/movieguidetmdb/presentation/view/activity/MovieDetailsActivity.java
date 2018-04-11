package com.esevinale.movieguidetmdb.presentation.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;

import com.esevinale.movieguidetmdb.R;
import com.esevinale.movieguidetmdb.presentation.model.MovieModel;
import com.esevinale.movieguidetmdb.presentation.view.fragment.MovieDetailsFragment;
import com.esevinale.movieguidetmdb.presentation.view.utils.Constants;

import javax.inject.Inject;

import butterknife.BindView;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class MovieDetailsActivity extends BaseActivity implements HasSupportFragmentInjector {

    private MovieModel movie;

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentInjector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);

        initializeActivity(savedInstanceState);
    }

    private void initializeActivity(Bundle savedInstanceState) {
        if (savedInstanceState == null)
            movie = getIntent().getParcelableExtra(Constants.MOVIE_MODEL);
        else
            movie = savedInstanceState.getParcelable(Constants.MOVIE_MODEL);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.details_wrapper, MovieDetailsFragment.getInstance(movie))
                .commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (outState != null) {
            outState.putParcelable(Constants.MOVIE_MODEL, movie);
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
