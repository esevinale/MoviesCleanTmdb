package com.esevinale.movieguidetmdb.presentation.view.activity;

import android.os.SystemClock;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import com.esevinale.movieguidetmdb.R;
import com.esevinale.movieguidetmdb.data.entity.movies.MovieEntity;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.realm.Realm;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayingAtLeast;
import static android.support.test.espresso.matcher.ViewMatchers.isFocusable;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() {
        Intents.init();
        IdlingRegistry.getInstance().register(mActivityTestRule.getActivity().getCountingIdlingResource());
        SystemClock.sleep(100);
    }

    @Test
    public void swipePage() {
        onView(withId(R.id.main_wrapper)).check(matches(isDisplayed()));
        onView(allOf(isDisplayed(), withId(R.id.movie_swipe))).perform(withCustomConstraints(swipeLeft(), isDisplayingAtLeast(85)));
        SystemClock.sleep(300);
        onView(allOf(isDisplayed(), withId(R.id.movie_swipe))).perform(withCustomConstraints(swipeLeft(), isDisplayingAtLeast(85)));
        SystemClock.sleep(300);
        onView(allOf(isDisplayed(), withId(R.id.movie_swipe))).perform(withCustomConstraints(swipeLeft(), isDisplayingAtLeast(85)));
        SystemClock.sleep(300);
        onView(allOf(isDisplayed(), withId(R.id.movie_swipe))).perform(withCustomConstraints(swipeRight(), isDisplayingAtLeast(85)));
    }

    @Test
    public void onMovieClick() {
        onView(allOf(isDisplayed(), withId(R.id.rv_movie))).perform(actionOnItemAtPosition(10, click()));
        Intents.intended(hasComponent(MovieDetailsActivity.class.getName()));
    }

    @Test
    public void swapAndClickMovie() {
        SystemClock.sleep(100);
        onView(allOf(isDisplayed(), withId(R.id.movie_swipe))).perform(withCustomConstraints(swipeLeft(), isDisplayingAtLeast(85)));
        SystemClock.sleep(300);
        onView(allOf(isDisplayed(), withId(R.id.movie_swipe))).perform(withCustomConstraints(swipeLeft(), isDisplayingAtLeast(85)));
        SystemClock.sleep(300);
        onView(allOf(isDisplayed(), withId(R.id.rv_movie))).perform(actionOnItemAtPosition(10, click()));
        Intents.intended(hasComponent(MovieDetailsActivity.class.getName()));
    }

    @Test
    public void checkTabLayoutDisplayed() {
        onView(withId(R.id.tabs))
                .perform(click())
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkRecyclerInit() {
        onView(allOf(isDisplayed(), withId(R.id.rv_movie))).check(matches(allOf(
                isDisplayed(),
                withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE),
                isFocusable()
        )));
    }

    @Test
    public void testScrollRecyclerView() {
        onView(allOf(isDisplayed(), withId(R.id.rv_movie)))
                .perform(scrollToPosition(15))
                .perform(scrollToPosition(8))
                .perform(scrollToPosition(1))
                .perform(scrollToPosition(2))
                .perform(scrollToPosition(10))
                .perform(scrollToPosition(19));
    }

    @Test
    public void realmTest() {
        SystemClock.sleep(500);
        int savedCount = Realm.getDefaultInstance()
                .where(MovieEntity.class)
                .findAll()
                .size();
        assertEquals(80, savedCount);
    }


    @After
    public void tearDown() {
        Intents.release();
        IdlingRegistry.getInstance().unregister(mActivityTestRule.getActivity().getCountingIdlingResource());
        Realm.getDefaultInstance().executeTransaction(realm -> realm.deleteAll());
    }

    //Method to handle "at least 90 percent of the view's area is displayed to the user" error.
    public static ViewAction withCustomConstraints(final ViewAction action, final Matcher<View> constraints) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return constraints;
            }

            @Override
            public String getDescription() {
                return action.getDescription();
            }

            @Override
            public void perform(UiController uiController, View view) {
                action.perform(uiController, view);
            }
        };
    }
}