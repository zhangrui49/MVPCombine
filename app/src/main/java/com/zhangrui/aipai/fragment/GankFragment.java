package com.zhangrui.aipai.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhangrui.aipai.R;
import com.zhangrui.aipai.adapter.FragmentAdapter;
import com.zhangrui.aipai.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * DESC:
 * Created by zhangrui on 2016/11/7.
 */

public class GankFragment extends BaseFragment {

    @Bind(R.id.tab)
    TabLayout mTab;
    @Bind(R.id.view_pager)
    ViewPager mViewPager;
    private final String[] TITLES = new String[]{"每日", "福利",
            "iOS", "Android", "休息视频", "拓展资源", "前端"};

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
            bundle.putString("type", TITLES[i]);
            bundle.putString("title", TITLES[i]);

            if (TITLES[i].equals("每日")) {
                GankImgFragment imgFragment = new GankImgFragment();
                imgFragment.setArguments(bundle);
                mFragments.add(imgFragment);
            }else {
                GankTabFragment tabFragment = new GankTabFragment();
                tabFragment.setArguments(bundle);
                mFragments.add(tabFragment);
            }
        }
        FragmentAdapter adapter =
                new FragmentAdapter(getChildFragmentManager(), mFragments, TITLES);

        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(1);
        mTab.setupWithViewPager(mViewPager);
    }
}
