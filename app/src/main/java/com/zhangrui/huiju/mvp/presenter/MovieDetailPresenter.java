package com.zhangrui.huiju.mvp.presenter;

import com.zhangrui.huiju.base.BasePresenter;
import com.zhangrui.huiju.mvp.model.MovieDetail;
import com.zhangrui.huiju.mvp.view.MovieDetailView;
import com.zhangrui.huiju.net.Api;
import com.zhangrui.huiju.net.ApiCallBack;
import com.zhangrui.huiju.net.NetClient;

/**
 * DESC:
 * Created by zhangrui on 2016/11/3.
 */

public class MovieDetailPresenter extends BasePresenter<MovieDetailView> {

    private Api.DouBanApi mDouBanApi;

    public MovieDetailPresenter(MovieDetailView movieView) {
        super(movieView);
        mDouBanApi = NetClient.getInstance().getDoubanRetrofit().create(Api.DouBanApi.class);
    }

    public void getMovie(String id) {

        mView.showProgress();
        addSubscription(mDouBanApi.getMovieDetail(id), new ApiCallBack<MovieDetail>() {
            @Override
            public void onSuccess(MovieDetail models) {
                mView.getMovieDetail(models);
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
