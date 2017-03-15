package com.zhangrui.aipai.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.zhangrui.aipai.R;
import com.zhangrui.aipai.activity.StoryActivity;
import com.zhangrui.aipai.adapter.ZhihuAdapter;
import com.zhangrui.aipai.base.BaseMvpFragment;
import com.zhangrui.aipai.mvp.model.Zhihu;
import com.zhangrui.aipai.mvp.model.ZhihuDaily;
import com.zhangrui.aipai.mvp.presenter.ZhihuPresenter;
import com.zhangrui.aipai.mvp.view.ZhihuView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * DESC:
 * Created by zhangrui on 2016/11/10.
 */

public class ZhihuFragment extends BaseMvpFragment<ZhihuPresenter> implements ZhihuView {


    @Bind(R.id.recycle_zhihu)
    RecyclerView mRecyclerview;


    private List<ZhihuDaily> mZhihuDailies;
    private ZhihuAdapter mAdapter;
    private String date = "0";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_zhihu, container, false);
        }
        return mRootView;
    }

    @Override
    public void initView() {
        mZhihuDailies = new ArrayList<>();
        mAdapter = new ZhihuAdapter(mZhihuDailies);
        mRecyclerview.addOnItemTouchListener(new OnItemClickListener() {

            @Override
            public void SimpleOnItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), StoryActivity.class);
                intent.putExtra("id", mZhihuDailies.get(position).getId());
                getActivity().startActivity(intent);
            }
        });
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mRecyclerview.post(new Runnable() {
                    @Override
                    public void run() {
                        getZhihu(date);
                    }

                });
            }
        });
        mRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerview.setAdapter(mAdapter);
        getZhihu();
    }


    @Override
    public ZhihuPresenter setPresenter() {
        return new ZhihuPresenter(this);
    }


    @Override
    public void updateZhihu(Zhihu zhihu) {
//        mZhihuDailies.addAll(zhihu.getStories());
        date = zhihu.getDate();
        mAdapter.addData(zhihu.getStories());
    }

    private void getZhihu() {
        mPresenter.getZhihu();
    }

    private void getZhihu(String date) {
        mPresenter.getZhihu(date);
    }
}
