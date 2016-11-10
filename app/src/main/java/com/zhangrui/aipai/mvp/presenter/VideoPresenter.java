package com.zhangrui.aipai.mvp.presenter;

import com.zhangrui.aipai.base.BasePresenter;
import com.zhangrui.aipai.mvp.model.Video;
import com.zhangrui.aipai.mvp.view.VideoView;
import com.zhangrui.aipai.net.Api;
import com.zhangrui.aipai.net.ApiCallBack;
import com.zhangrui.aipai.net.NetClient;

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
