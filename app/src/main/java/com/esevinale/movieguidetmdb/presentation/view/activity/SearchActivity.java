package com.esevinale.movieguidetmdb.presentation.view.activity;

import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.esevinale.movieguidetmdb.R;
import com.esevinale.movieguidetmdb.presentation.view.fragment.MovieDetailsFragment;
import com.esevinale.movieguidetmdb.presentation.view.fragment.SearchFragment;
import com.esevinale.movieguidetmdb.presentation.view.lifecycle.ActivityLayout;
import com.esevinale.movieguidetmdb.presentation.view.utils.EspressoIdlingResource;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;


@ActivityLayout(getLayoutId = R.layout.activity_search)
public class SearchActivity extends AppCompatActivity implements HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> mFragmentInjector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);

        initializeActivity();
    }

    private void initializeActivity() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.details_wrapper, new SearchFragment())
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return mFragmentInjector;
    }

    @VisibleForTesting
    public IdlingResource getCountingIdlingResource() {
        return EspressoIdlingResource.getIdlingResource();
    }
}
