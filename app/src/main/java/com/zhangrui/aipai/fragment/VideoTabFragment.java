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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.zhangrui.aipai.R;
import com.zhangrui.aipai.activity.WebActivity;
import com.zhangrui.aipai.adapter.VideoTabAdapter;
import com.zhangrui.aipai.base.BaseMvpFragment;
import com.zhangrui.aipai.mvp.model.Video;
import com.zhangrui.aipai.mvp.presenter.VideoPresenter;
import com.zhangrui.aipai.mvp.view.VideoView;
import com.zhangrui.aipai.widget.CircleImageView;

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
    @Bind(R.id.header_image)
    CircleImageView mHeaderImage;
    @Bind(R.id.header_text)
    TextView mHeaderText;
    @Bind(R.id.header_action)
    ImageView mHeaderAction;
    @Bind(R.id.header_layout)
    LinearLayout mHeaderLayout;
    private int page = 1;
    private final int PAGE_SIZE = 10;
    private List<Video> mVideoList;
    private VideoTabAdapter mAdapter;
    private int index;
    private int mSuspensionHeight;
    private LinearLayoutManager mLinearLayoutManager;
    private int mCurrentPosition=0;
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
        mLinearLayoutManager=new LinearLayoutManager(getActivity());
        mRecyclerview.setLayoutManager(mLinearLayoutManager);
        mRecyclerview.addOnItemTouchListener(new OnItemClickListener() {

            @Override
            public void SimpleOnItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), WebActivity.class);
                intent.putExtra("url", mVideoList.get(position).getUrl());
                getActivity().startActivity(intent);
            }
        });

        mRecyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                mSuspensionHeight = mHeaderLayout.getHeight();
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //找到列表第二个可见的View
                View view = mLinearLayoutManager.findViewByPosition(mCurrentPosition + 1);
                if (view == null) return;
                //判断View的top值是否小于悬浮条的高度
                if (view.getTop() <= mSuspensionHeight) {
                    //被顶掉的效果
                    mHeaderLayout.setY(-(mSuspensionHeight - view.getTop()));
                } else {
                    mHeaderLayout.setY(0);
                }

                //判断是否需要更新悬浮条
                if (mCurrentPosition != mLinearLayoutManager.findFirstVisibleItemPosition()) {
                    mCurrentPosition = mLinearLayoutManager.findFirstVisibleItemPosition();
                    mHeaderLayout.setY(0);
                    //更新悬浮条
                    updateSuspensionBar();
                }
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
        ImageView refresh=new ImageView(getActivity());
        refresh.setImageResource(R.drawable.refresh);
        mAdapter.setEmptyView(refresh);
        mRecyclerview.setAdapter(mAdapter);
//        mRecyclerview.addItemDecoration(new RecyclerViewDivider(
//                getActivity(), LinearLayoutManager.VERTICAL));
        getVideoList("");
    }

    private void updateSuspensionBar() {
        Glide.with(getActivity()).load(mVideoList.get(mCurrentPosition).getAvatar()).into(mHeaderImage);
        mHeaderText.setText(mVideoList.get(mCurrentPosition).getScreen_name());
    }

    @Override
    public VideoPresenter setPresenter() {
        return new VideoPresenter(this);
    }

    @Override
    public void updateVideoList(List<Video> videos) {
        // mVideoList.addAll(videos);
        mAdapter.addData(videos);
        updateSuspensionBar();
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
