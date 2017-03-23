package com.zhangrui.huiju.mvp.presenter;

import com.zhangrui.huiju.base.BasePresenter;
import com.zhangrui.huiju.mvp.model.Video;
import com.zhangrui.huiju.mvp.view.VideoView;
import com.zhangrui.huiju.net.Api;
import com.zhangrui.huiju.net.ApiCallBack;
import com.zhangrui.huiju.net.NetClient;

import java.util.HashMap;
import java.util.List;

/**
 * DESC:
 * Created by zhangrui on 2016/11/3.
 */

public class VideoPresenter extends BasePresenter<VideoView> {

    private Api.AiPaiApi mAiPaiApi;

    public VideoPresenter(VideoView videoView) {
        super(videoView);
        mAiPaiApi = NetClient.getInstance().getVideoRetrofit().create(Api.AiPaiApi.class);
    }

    public void getVideoList(HashMap<String, Object> map) {

        mView.showProgress();
        addSubscription(mAiPaiApi.getVideos(map), new ApiCallBack<List<Video>>() {
            @Override
            public void onSuccess(List<Video> models) {
                mView.updateVideoList(models);
            }

            @Override
            public void onFinish() {
                mView.dissmissProgress();
            }

            @Override
            public void onFailure(String msg) {
                mView.showError(msg);
            }
        });

    }

}
