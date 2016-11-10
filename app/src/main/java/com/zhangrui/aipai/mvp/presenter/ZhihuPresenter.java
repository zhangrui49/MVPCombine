package com.zhangrui.aipai.mvp.presenter;

import com.zhangrui.aipai.base.BasePresenter;
import com.zhangrui.aipai.mvp.model.Zhihu;
import com.zhangrui.aipai.mvp.view.ZhihuView;
import com.zhangrui.aipai.net.Api;
import com.zhangrui.aipai.net.ApiCallBack;
import com.zhangrui.aipai.net.NetClient;

/**
 * DESC:
 * Created by zhangrui on 2016/11/10.
 */

public class ZhihuPresenter extends BasePresenter<ZhihuView> {

    private Api.ZhihuApi mZhihuApi;

    public ZhihuPresenter(ZhihuView view) {
        super(view);
        mZhihuApi = NetClient.getInstance().getZhihuRetrofit().create(Api.ZhihuApi.class);
    }

    public void getZhihu() {
        mView.showProgress();
        addSubscription(mZhihuApi.getLastZhihuDaily(), new ApiCallBack<Zhihu>() {
            @Override
            public void onSuccess(Zhihu model) {
                mView.updateZhihu(model);
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
    public void getZhihu(String date) {
        mView.showProgress();
        addSubscription(mZhihuApi.getZhihuDaily(date), new ApiCallBack<Zhihu>() {
            @Override
            public void onSuccess(Zhihu model) {
                mView.updateZhihu(model);
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
