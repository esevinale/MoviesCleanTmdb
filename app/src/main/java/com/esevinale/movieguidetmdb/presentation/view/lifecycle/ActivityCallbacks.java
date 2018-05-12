package com.esevinale.movieguidetmdb.presentation.view.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ActivityCallbacks implements Application.ActivityLifecycleCallbacks {

    private final Map<Activity, Unbinder> mUnbinders = new HashMap<>();

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        for (Annotation a : activity.getClass().getAnnotations())
            if (a instanceof ActivityLayout) {
                activity.setContentView(layoutId(activity));
            }
        mUnbinders.put(activity, ButterKnife.bind(activity));
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        mUnbinders.remove(activity).unbind();
    }

//    private int layoutId(Activity activity) {
//        return ((ActivityLayout) activity).getLayoutId();
//    }

    private int layoutId(Activity activity) {
        return activity.getClass()
                .getAnnotation(ActivityLayout.class)
                .getLayoutId();
    }
}