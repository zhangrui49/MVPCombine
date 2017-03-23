package com.zhangrui.huiju.mvp.presenter;

import com.zhangrui.huiju.base.BasePresenter;
import com.zhangrui.huiju.mvp.view.StoryView;
import com.zhangrui.huiju.net.Api;
import com.zhangrui.huiju.net.ApiCallBack;
import com.zhangrui.huiju.net.NetClient;
import com.zhangrui.huiju.viewmodel.Story;

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
