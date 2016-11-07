package com.zhangrui.aipai.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.zhangrui.aipai.R;
import com.zhangrui.aipai.activity.WebActivity;
import com.zhangrui.aipai.adapter.VideoTabAdapter;
import com.zhangrui.aipai.base.BaseMvpFragment;
import com.zhangrui.aipai.mvp.model.Video;
import com.zhangrui.aipai.mvp.presenter.VideoPresenter;
import com.zhangrui.aipai.mvp.view.VideoView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoTabFragment extends BaseMvpFragment<VideoPresenter> implements VideoView {


    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerview;
    private final String[] IDS = {"1", "13", "64", "16", "31", "19", "62", "63", "3", "59", "27", "5", "18", "6", "193"};
    private int page = 1;
    private final int PAGE_SIZE = 10;
    private List<Video> mVideoList;
    private VideoTabAdapter mAdapter;
    private int index;

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
        index = getArguments().getInt("index", 0);
        mVideoList = new ArrayList<>();
        mAdapter = new VideoTabAdapter(mVideoList);
        mRecyclerview.addOnItemTouchListener(new OnItemClickListener( ){

            @Override
            public void SimpleOnItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), WebActivity.class);
                intent.putExtra("url", mVideoList.get(position).getUrl());
                getActivity().startActivity(intent);
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
                        getVideoList(mVideoList.get(mVideoList.size() - 1).getId());
                    }

                });
            }
        });
        mRecyclerview.setAdapter(mAdapter);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
//        mRecyclerview.addItemDecoration(new RecyclerViewDivider(
//                getActivity(), LinearLayoutManager.VERTICAL));
        getVideoList("");
    }

    @Override
    public VideoPresenter setPresenter() {
        return new VideoPresenter(this);
    }

    @Override
    public void updateVideoList(List<Video> videos) {
       // mVideoList.addAll(videos);
        mAdapter.addData(videos);
    }

    public void getVideoList(String max_id) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", IDS[index]);
        map.put("page", page);
        map.put("count", PAGE_SIZE);
        if (!TextUtils.isEmpty(max_id)) {
            map.put("max_id", max_id);
        } else {
            page = 1;
            mVideoList.clear();
        }
        mPresenter.getVideoList(map);
    }
}
