package com.zhangrui.huiju.mvp.presenter;

import com.zhangrui.huiju.base.BasePresenter;
import com.zhangrui.huiju.mvp.model.Movie;
import com.zhangrui.huiju.mvp.view.MovieView;
import com.zhangrui.huiju.net.Api;
import com.zhangrui.huiju.net.ApiCallBack;
import com.zhangrui.huiju.net.NetClient;

import java.util.HashMap;

/**
 * DESC:
 * Created by zhangrui on 2016/11/3.
 */

public class MoviePresenter extends BasePresenter<MovieView> {

    private Api.DouBanApi mDouBanApi;

    public MoviePresenter(MovieView movieView) {
        super(movieView);
        mDouBanApi = NetClient.getInstance().getDoubanRetrofit().create(Api.DouBanApi.class);
    }

    public void getMovies(String type, HashMap<String,Object> map) {

        mView.showProgress();
        addSubscription(mDouBanApi.getMovies(type,map), new ApiCallBack<Movie>() {
            @Override
            public void onSuccess(Movie models) {
                mView.getMovie(models);
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
