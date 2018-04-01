package com.esevinale.movieguidetmdb.presentation.view.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentLayout());
        ButterKnife.bind(this);
    }

    @LayoutRes
    public abstract int getContentLayout();
}
