package com.zhangrui.aipai.mvp.presenter;

import com.zhangrui.aipai.base.BasePresenter;
import com.zhangrui.aipai.mvp.view.StoryView;
import com.zhangrui.aipai.net.Api;
import com.zhangrui.aipai.net.ApiCallBack;
import com.zhangrui.aipai.net.NetClient;
import com.zhangrui.aipai.viewmodel.Story;

/**
 * DESC:
 * Created by zhangrui on 2016/11/9.
 */

public class StoryPresenter extends BasePresenter<StoryView> {

    private Api.ZhihuApi mZhihuApi;

    public StoryPresenter(StoryView view) {
        super(view);
        mZhihuApi = NetClient.getInstance().getZhihuRetrofit().create(Api.ZhihuApi.class);
    }

    public void getStory(String id) {
        mView.showProgress();
        addSubscription(mZhihuApi.getZhihuStory(id), new ApiCallBack<Story>() {
            @Override
            public void onSuccess(Story model) {
                mView.getStory(model);
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
