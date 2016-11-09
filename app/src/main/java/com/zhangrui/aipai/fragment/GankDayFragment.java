package com.zhangrui.aipai.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhangrui.aipai.R;
import com.zhangrui.aipai.adapter.GankDayDataAdapter;
import com.zhangrui.aipai.base.BaseMvpFragment;
import com.zhangrui.aipai.mvp.model.GankData;
import com.zhangrui.aipai.mvp.model.GankDay;
import com.zhangrui.aipai.mvp.model.GankDayData;
import com.zhangrui.aipai.mvp.presenter.GankDayDataPresenter;
import com.zhangrui.aipai.mvp.view.GankDayView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;

/**
 * DESC:
 * Created by zhangrui on 2016/11/7.
 */

public class GankDayFragment extends BaseMvpFragment<GankDayDataPresenter> implements GankDayView {

    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerview;
    private List<GankDay> mGankDayList;
    private GankDayDataAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_gank_tab, container, false);
        }
        return mRootView;
    }

    @Override
    public void initView() {
        mGankDayList = new ArrayList<>();
        mAdapter = new GankDayDataAdapter(R.layout.item_gank_data, R.layout.header, mGankDayList);
        mAdapter.openLoadAnimation();
        mRecyclerview.setAdapter(mAdapter);
        getGankData();
    }

    @Override
    public GankDayDataPresenter setPresenter() {
        return new GankDayDataPresenter(this);
    }

    @Override
    public void updateDayData(GankDayData dayData) {
        HashMap<String, List<GankData>> map = dayData.generateResult();
        GankDay day;
        for (String s : map.keySet()) {
            day = new GankDay(true, s);
            mGankDayList.add(day);
            List<GankData> datas = map.get(s);
            for (GankData data : datas) {
                mGankDayList.add(new GankDay(data));
            }
        }
        mAdapter.addData(mGankDayList);
    }

    private void getGankData() {
        mPresenter.getGank(2016, 11, 7);
    }
}
