package com.zhangrui.huiju.mvp.presenter;

import com.zhangrui.huiju.base.BasePresenter;
import com.zhangrui.huiju.mvp.model.Gank;
import com.zhangrui.huiju.mvp.view.GankView;
import com.zhangrui.huiju.net.Api;
import com.zhangrui.huiju.net.ApiCallBack;
import com.zhangrui.huiju.net.NetClient;

/**
 * DESC:
 * Created by zhangrui on 2016/11/7.
 */

public class GankPresenter extends BasePresenter<GankView> {
    private Api.GankApi mGankApi;

    public GankPresenter(GankView mvpView) {
        super(mvpView);
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
                mView.dismissProgress();
            }

            @Override
            public void onFailure(String msg) {
                mView.showError(msg);
            }
        });

    }
}
