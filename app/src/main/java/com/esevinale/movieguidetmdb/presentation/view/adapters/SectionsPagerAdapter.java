package com.esevinale.movieguidetmdb.presentation.view.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.esevinale.movieguidetmdb.presentation.view.fragment.tabFragments.NowPlayingMovieListFragment;
import com.esevinale.movieguidetmdb.presentation.view.fragment.tabFragments.PopularMovieListFragment;
import com.esevinale.movieguidetmdb.presentation.view.fragment.tabFragments.TopRatedMovieListFragment;
import com.esevinale.movieguidetmdb.presentation.view.fragment.tabFragments.UpcomingMovieListFragment;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public static final int SECTIONS_PAGER_FRAGMENT_COUNT = 4;

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new NowPlayingMovieListFragment();
            case 1:
                return new UpcomingMovieListFragment();
            case 2:
                return new TopRatedMovieListFragment();
            case 3:
                return new PopularMovieListFragment();
            default:
                return new NowPlayingMovieListFragment();
        }
    }

    @Override
    public int getCount() {
        return SECTIONS_PAGER_FRAGMENT_COUNT;
    }
}