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
 * Created by zhangrui on 2016/11/4.
 */

public class VideoFragment extends BaseFragment {

    @Bind(R.id.tab)
    TabLayout mTab;
    @Bind(R.id.view_pager)
    ViewPager mViewPager;
    private final String[] TITLES = new String[]{
            "热门", "搞笑", "逗比", "明星名人", "男神", "女神", "音乐", "舞蹈", "旅行", "美食", "美妆时尚", "涨姿势", "宝宝", "萌宠乐园", "二次元"
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
            VideoTabFragment videoListFragment = new VideoTabFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("index", i);
            bundle.putString("title",TITLES[i]);
            videoListFragment.setArguments(bundle);
            mFragments.add(videoListFragment);
        }
        FragmentAdapter adapter =
                new FragmentAdapter(getChildFragmentManager(), mFragments, TITLES);

        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(1);
        mTab.setupWithViewPager(mViewPager);
    }
}
