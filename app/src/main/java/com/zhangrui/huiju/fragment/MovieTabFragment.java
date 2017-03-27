package com.zhangrui.huiju.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.zhangrui.huiju.R;
import com.zhangrui.huiju.activity.MovieDetailActivity;
import com.zhangrui.huiju.adapter.MovieAdapter;
import com.zhangrui.huiju.base.BaseMvpFragment;
import com.zhangrui.huiju.mvp.model.Movie;
import com.zhangrui.huiju.mvp.model.MovieDetail;
import com.zhangrui.huiju.mvp.presenter.MoviePresenter;
import com.zhangrui.huiju.mvp.view.MovieView;
import com.zhangrui.huiju.widget.HidingScrollListener;
import com.zhangrui.huiju.widget.RecyclerViewDivider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;

/**
 * DESC:
 * Created by zhangrui on 2016/11/7.
 */

public class MovieTabFragment extends BaseMvpFragment<MoviePresenter> implements MovieView {
    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerview;

    private int page = 0;
    private final int PAGE_SIZE = 10;
    private List<MovieDetail> mMovieDataList;
    private MovieAdapter mAdapter;
    private String type;

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
        type = getArguments().getString("type");
        mMovieDataList = new ArrayList<>();
        mAdapter = new MovieAdapter(mMovieDataList);
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
                        page += PAGE_SIZE;
                        getMovies();
                    }

                });
            }
        });
        mRecyclerview.addOnItemTouchListener(new OnItemClickListener() {

            @Override
            public void SimpleOnItemClick(BaseQuickAdapter adapter, View view, int position) {

//                Intent intent = new Intent(getActivity(), WebActivity.class);
//                intent.putExtra("url", mMovieDataList.get(position).getAlt());
//                getActivity().startActivity(intent);
                Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
                intent.putExtra("id", mMovieDataList.get(position).getId());
                getActivity().startActivity(intent);

            }
        });
        mRecyclerview.setAdapter(mAdapter);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerview.addItemDecoration(new RecyclerViewDivider(
                getActivity(), LinearLayoutManager.VERTICAL, 10, Color.YELLOW));
        getMovies();
        mRecyclerview.addOnScrollListener(new HidingScrollListener() {
            @Override
            public void onHide() {

            }
            @Override
            public void onShow() {

            }
        });
    }

    @Override
    public MoviePresenter setPresenter() {
        return new MoviePresenter(this);
    }

    @Override
    public void getMovie(Movie movie) {
        mAdapter.addData(movie.getSubjects());
    }

    private void getMovies() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("count", PAGE_SIZE);
        map.put("start", page);
        mPresenter.getMovies(type, map);
    }

//    private void hideViews() {
//        mToolbar.animate().translationY(-mToolbar.getHeight()).setInterpolator(new AccelerateInterpolator(2));
//        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) mFabButton.getLayoutParams();
//        int fabBottomMargin = lp.bottomMargin;
//        mFabButton.animate().translationY(mFabButton.getHeight()+fabBottomMargin).setInterpolator(new AccelerateInterpolator(2)).start();
//    }
//
//    private void showViews() {
//        mToolbar.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));
//        mFabButton.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2)).start();
//    }
}
