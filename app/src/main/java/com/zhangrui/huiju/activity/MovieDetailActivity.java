package com.zhangrui.huiju.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhangrui.huiju.R;
import com.zhangrui.huiju.adapter.MovieDetailAdapter;
import com.zhangrui.huiju.base.BaseMvpActivity;
import com.zhangrui.huiju.mvp.model.MovieDetail;
import com.zhangrui.huiju.mvp.presenter.MovieDetailPresenter;
import com.zhangrui.huiju.mvp.view.MovieDetailView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MovieDetailActivity extends BaseMvpActivity<MovieDetailPresenter> implements MovieDetailView {
    String id;
    @Bind(R.id.image)
    ImageView mImage;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @Bind(R.id.app_bar_layout)
    AppBarLayout mAppBarLayout;
    @Bind(R.id.count)
    TextView mCount;
    @Bind(R.id.down)
    ImageButton mDown;
    @Bind(R.id.casts)
    RecyclerView mCasts;
    @Bind(R.id.summary)
    TextView mSummary;
    @Bind(R.id.nest)
    NestedScrollView mNest;
    @Bind(R.id.fab)
    FloatingActionButton mFab;
    @Bind(R.id.rootLayout)
    CoordinatorLayout mRootLayout;

    private MovieDetailAdapter mAdapter;
    private List<MovieDetail.CastsBean> mCastsBeanList;
    private MovieDetail mMovieDetail;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);
    }


    @Override
    public void initView() {
        id = getIntent().getStringExtra("id");
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_pink_500_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    mImage.setVisibility(View.INVISIBLE);
                } else {
                    mImage.setVisibility(View.VISIBLE);
                }
            }
        });
        getMovieDetail();
    }

    @Override
    public void getMovieDetail(MovieDetail movieDetail) {
        mMovieDetail = movieDetail;
        mCastsBeanList = movieDetail.getCasts();
        mAdapter = new MovieDetailAdapter(mCastsBeanList);
        mCasts.setAdapter(mAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mCasts.setLayoutManager(linearLayoutManager);
        mCasts.setNestedScrollingEnabled(false);
        mSummary.setText("影片简介：" + "\n" + "  "+movieDetail.getSummary());
        mCount.setText("共" + movieDetail.getCollect_count() + "人看过  获得" + movieDetail.getReviews_count() + "条评论");
        mToolbar.setTitle(movieDetail.getTitle());
        setSupportActionBar(mToolbar);
        Glide.with(this).load(movieDetail.getImages().getLarge()).into(mImage);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @Override
    public MovieDetailPresenter setPresenter() {
        return new MovieDetailPresenter(this);
    }

    public void getMovieDetail() {
        mPresenter.getMovie(id);
    }

    @OnClick(R.id.fab)
    public void onClick() {
        if (mMovieDetail != null) {
            showShare("汇聚", mMovieDetail.getTitle(), mMovieDetail.getImages().getSmall(), mMovieDetail.getShare_url());
        }
    }
}
