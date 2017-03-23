package com.zhangrui.huiju.mvp.presenter;

import com.zhangrui.huiju.base.BasePresenter;
import com.zhangrui.huiju.mvp.model.GankDayData;
import com.zhangrui.huiju.mvp.view.GankDayView;
import com.zhangrui.huiju.net.Api;
import com.zhangrui.huiju.net.ApiCallBack;
import com.zhangrui.huiju.net.NetClient;

/**
 * DESC:
 * Created by zhangrui on 2016/11/7.
 */

public class GankDayDataPresenter extends BasePresenter<GankDayView> {

    private Api.GankApi mGankApi;

    public GankDayDataPresenter(GankDayView mvpView) {
        super(mvpView);
        mGankApi = NetClient.getInstance().getGankRetrofit().create(Api.GankApi.class);
    }

    public void getGank(int year, int month, int day) {

        mView.showProgress();
        addSubscription(mGankApi.getGankDayData(year, month, day), new ApiCallBack<GankDayData>() {
            @Override
            public void onSuccess(GankDayData model) {
                mView.updateDayData(model);
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
