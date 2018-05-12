package com.esevinale.movieguidetmdb.presentation.view.activity;

import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ProgressBar;

import com.esevinale.movieguidetmdb.R;
import com.esevinale.movieguidetmdb.presentation.model.MovieModel;
import com.esevinale.movieguidetmdb.presentation.view.lifecycle.ActivityLayout;
import com.esevinale.movieguidetmdb.presentation.view.fragment.MovieDetailsFragment;
import com.esevinale.movieguidetmdb.presentation.view.utils.Constants;
import com.esevinale.movieguidetmdb.presentation.view.utils.EspressoIdlingResource;

import javax.inject.Inject;

import butterknife.BindView;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

@ActivityLayout(getLayoutId = R.layout.activity_movie_details)
public class MovieDetailsActivity extends AppCompatActivity implements HasSupportFragmentInjector {

    private MovieModel mMovie;

    @BindView(R.id.progress_bar)
    protected ProgressBar mProgressBar;

    @Inject
    DispatchingAndroidInjector<Fragment> mFragmentInjector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);

        initializeActivity(savedInstanceState);
    }

    private void initializeActivity(Bundle savedInstanceState) {
        if (savedInstanceState == null)
            mMovie = getIntent().getParcelableExtra(Constants.MOVIE_MODEL);
        else
            mMovie = savedInstanceState.getParcelable(Constants.MOVIE_MODEL);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.details_wrapper, MovieDetailsFragment.getInstance(mMovie))
                .commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (outState != null) {
            outState.putParcelable(Constants.MOVIE_MODEL, mMovie);
        }
        super.onSaveInstanceState(outState);
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
        return mFragmentInjector;
    }

    public ProgressBar getProgressBar() {
        return mProgressBar;
    }

    @VisibleForTesting
    public IdlingResource getCountingIdlingResource() {
        return EspressoIdlingResource.getIdlingResource();
    }
}
