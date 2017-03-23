package com.zhangrui.huiju.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhangrui.huiju.R;
import com.zhangrui.huiju.adapter.FragmentAdapter;
import com.zhangrui.huiju.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * DESC:
 * Created by zhangrui on 2016/11/7.
 */

public class MovieFragment extends BaseFragment {

    @Bind(R.id.tab)
    TabLayout mTab;
    @Bind(R.id.view_pager)
    ViewPager mViewPager;
    private final String[] TITLES = new String[]{"top250",
            "最近热播", "即将上映"};
    private final String[] TYPES = new String[]{"top250", "in_theaters", "coming_soon"
    };

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_video, container, false);
        }
        return mRootView;
    }

    @Override
    public void initView() {
        List<Fragment> mFragments = new ArrayList<>();

        for (int i = 0; i < TITLES.length; i++) {
            Bundle bundle = new Bundle();
            bundle.putString("type", TYPES[i]);
            bundle.putString("title", TITLES[i]);
//
//            if (TITLES[i].equals("每日")) {
//                GankDayFragment imgFragment = new GankDayFragment();
//                imgFragment.setArguments(bundle);
//                mFragments.add(imgFragment);
//            }else {
            MovieTabFragment tabFragment = new MovieTabFragment();
            tabFragment.setArguments(bundle);
            mFragments.add(tabFragment);
            // }
        }
        FragmentAdapter adapter =
                new FragmentAdapter(getChildFragmentManager(), mFragments, TITLES);

        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(1);
        mTab.setupWithViewPager(mViewPager);
        mTab.setTabMode(TabLayout.MODE_FIXED);
        mTab.setTabGravity(TabLayout.GRAVITY_FILL);
    }
}
