package com.zhangrui.aipai.mvp.presenter;

import com.zhangrui.aipai.base.BasePresenter;
import com.zhangrui.aipai.mvp.model.Gank;
import com.zhangrui.aipai.mvp.view.GankView;
import com.zhangrui.aipai.net.Api;
import com.zhangrui.aipai.net.ApiCallBack;
import com.zhangrui.aipai.net.NetClient;

/**
 * DESC:
 * Created by zhangrui on 2016/11/7.
 */

public class GankPresenter extends BasePresenter<GankView> {
    private Api.GankApi mGankApi;

    public GankPresenter(GankView mvpView) {
        attachView(mvpView);
        mGankApi = NetClient.getInstance().getGankRetrofit().create(Api.GankApi.class);
    }

    public void getGank(String type,int pageSize,int page) {

        mView.showProgress();
        addSubscription(mGankApi.getGankData(type,pageSize,page), new ApiCallBack<Gank>() {
            @Override
            public void onSuccess(Gank model) {
                mView.updateGankData(model);
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
