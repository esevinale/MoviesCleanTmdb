package com.esevinale.movieguidetmdb.presentation.view.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.ProgressBar;

import com.esevinale.movieguidetmdb.R;
import com.esevinale.movieguidetmdb.presentation.AndroidApp;
import com.esevinale.movieguidetmdb.presentation.view.adapters.SectionsPagerAdapter;

import javax.inject.Inject;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.progress_bar)
    protected ProgressBar mProgressBar;
    @BindView(R.id.main_wrapper)
    ViewPager mViewPager;
    @BindView(R.id.tabs)
    TabLayout tabLayout;
    @Inject
    SectionsPagerAdapter mSectionsPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApp.getApplicationComponent().inject(this);
        initToolbarLayout();
    }

    private void initToolbarLayout() {
        setSupportActionBar(toolbar);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager.setOffscreenPageLimit(4);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_main;
    }

    public ProgressBar getProgressBar() {
        return mProgressBar;
    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
