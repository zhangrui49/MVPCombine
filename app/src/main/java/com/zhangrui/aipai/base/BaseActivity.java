package com.zhangrui.aipai.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.zhangrui.aipai.R;

import butterknife.ButterKnife;

/**
 * DESC:
 * Created by zhangrui on 2016/11/3.
 */

public abstract class BaseActivity extends AppCompatActivity {
    public Activity mActivity;
    public Fragment mFragment;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
        mActivity = this;
        initView();
    }

    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        super.onDestroy();
    }

    public void replaceFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (mFragment.isAdded()) {
            ft.show(mFragment);
        } else {
            ft.replace(R.id.main_content, mFragment);
        }
        ft.commitAllowingStateLoss();
    }

    public void startActivity(Class target) {
        startActivity(new Intent(this, target));
    }

    public abstract void initView();

    public Toolbar setTitle(String title) {
        Toolbar toolbar = (Toolbar) mActivity.findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        return toolbar;
    }
}
