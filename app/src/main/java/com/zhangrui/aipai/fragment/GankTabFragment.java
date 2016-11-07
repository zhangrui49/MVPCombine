package com.zhangrui.aipai.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.zhangrui.aipai.R;
import com.zhangrui.aipai.adapter.GankDataAdapter;
import com.zhangrui.aipai.base.BaseMvpFragment;
import com.zhangrui.aipai.mvp.model.Gank;
import com.zhangrui.aipai.mvp.model.GankData;
import com.zhangrui.aipai.mvp.presenter.GankPresenter;
import com.zhangrui.aipai.mvp.view.GankView;
import com.zhangrui.aipai.widget.RecyclerViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * DESC:
 * Created by zhangrui on 2016/11/7.
 */

public class GankTabFragment extends BaseMvpFragment<GankPresenter> implements GankView {
    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerview;

    private int page = 1;
    private final int PAGE_SIZE = 10;
    private List<GankData> mGankDataList;
    private GankDataAdapter mAdapter;
    private String type;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_video_tab, container, false);
        }
        return mRootView;
    }

    @Override
    public void initView() {
        type = getArguments().getString("type");
        mGankDataList = new ArrayList<>();
        mAdapter = new GankDataAdapter(mGankDataList);
        mRecyclerview.addOnItemTouchListener(new OnItemClickListener() {

            @Override
            public void SimpleOnItemClick(BaseQuickAdapter adapter, View view, int position) {
//                Intent intent = new Intent(getActivity(), WebActivity.class);
//                intent.putExtra("url", mVideoList.get(position).getUrl());
//                getActivity().startActivity(intent);
            }
        });
        mAdapter.openLoadAnimation();
        mAdapter.openLoadMore(PAGE_SIZE);
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mRecyclerview.post(new Runnable() {
                    @Override
                    public void run() {
                        page++;
                        getGankData();
                    }

                });
            }
        });
        mRecyclerview.setAdapter(mAdapter);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerview.addItemDecoration(new RecyclerViewDivider(
                getActivity(), LinearLayoutManager.VERTICAL,10, Color.YELLOW));
        getGankData();
    }

    @Override
    public GankPresenter setPresenter() {
        return new GankPresenter(this);
    }

    @Override
    public void updateGankData(Gank gank) {
        mAdapter.addData(gank.getResults());
    }

    private void getGankData() {
        mPresenter.getGank(type, PAGE_SIZE, page);
    }
}
