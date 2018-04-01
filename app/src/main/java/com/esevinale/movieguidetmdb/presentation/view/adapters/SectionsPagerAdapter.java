package com.esevinale.movieguidetmdb.presentation.view.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.esevinale.movieguidetmdb.presentation.view.fragment.MyFragment;
import com.esevinale.movieguidetmdb.presentation.view.fragment.NowPlayingMovieListFragment;

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
            default:
                return new MyFragment();
        }
    }

    @Override
    public int getCount() {
        return SECTIONS_PAGER_FRAGMENT_COUNT;
    }
}