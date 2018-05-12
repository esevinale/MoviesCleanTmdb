package com.esevinale.movieguidetmdb.presentation.view.activity;

import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.esevinale.movieguidetmdb.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
public class MovieDetailsActivityTest {
    @Rule
    public ActivityTestRule<MovieDetailsActivity> mActivityTestRule
            = new MovieDetailsActivityTestRule(MovieDetailsActivity.class);


    @Before
    public void setUp() throws Exception {
        IdlingRegistry.getInstance().register(mActivityTestRule.getActivity().getCountingIdlingResource());
    }

    @Test
    public void collapsingToolbarTest() throws Exception {
        onView(withId(R.id.collapsing_toolbar)).check(matches(isDisplayed()));
        onView(withId(R.id.movie_backdrop)).check(matches(withContentDescription(R.string.backdrop)));
        onView(withId(R.id.movie_backdrop)).check(matches(allOf(isDisplayed(), withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE))));
        onView(withId(R.id.movie_poster_collapse)).check(matches(allOf(isDisplayed(), withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE))));
        onView(withId(R.id.movie_poster_collapse)).check(matches(withContentDescription(R.string.movie_poster)));
        onView(withId(R.id.movie_name_collaps)).check(matches(withText("Zootopia")));
        onView(withId(R.id.movie_year)).check(matches(withText("2016")));
        onView(withId(R.id.movie_time)).check(matches(withText("1 h 48 min")));
        onView(withId(R.id.movie_genres)).check(matches(withText("Animation, Adventure, Family, Comedy")));
        onView(withId(R.id.collapsing_toolbar)).perform(click(), swipeUp());
    }

    @Test
    public void informationSectionText() throws Exception {
        onView(withId(R.id.tv_budget)).check(matches(withText("$150 000 000")));
        onView(withId(R.id.tv_revenue)).check(matches(withText("$1 023 784 195")));
    }

    @Test
    public void overviewSectionTest() throws Exception {
        onView(withId(R.id.movie_overview_content)).check(matches(isDisplayed()));
    }

    @Test
    public void siteCompaniesSectionTest() throws Exception {
        onView(withId(R.id.sites)).check(matches(withText("http://movies.disney.com/zootopia")));
        onView(withId(R.id.companies)).check(matches(withText("Walt Disney Animation Studios, Walt Disney Pictures")));
    }

    @Test
    public void trailersSectionTest() throws Exception {
        onView(withId(R.id.trailers_section)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(mActivityTestRule.getActivity().getCountingIdlingResource());
    }
}