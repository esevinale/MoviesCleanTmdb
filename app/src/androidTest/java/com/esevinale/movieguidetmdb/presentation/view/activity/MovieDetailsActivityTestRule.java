package com.esevinale.movieguidetmdb.presentation.view.activity;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;

import com.esevinale.movieguidetmdb.presentation.model.MovieModel;
import com.esevinale.movieguidetmdb.presentation.view.utils.Constants;

public class MovieDetailsActivityTestRule extends ActivityTestRule<MovieDetailsActivity> {

    MovieDetailsActivityTestRule(Class<MovieDetailsActivity> activityClass) {
        super(activityClass);
    }

    @Override
    protected Intent getActivityIntent() {
        Intent intent = new Intent();
        MovieModel movieModel = new MovieModel(269149, "(Intent) Zootopia", "sM33SANp9z6rXW8Itn7NnG1GOEs.jpg",
                "\\/mhdeE1yShHTaDbJVdWyTlzFvNkr.jpg");
        intent.putExtra(Constants.MOVIE_MODEL, movieModel);
        return intent;
    }
}
