package com.zhangrui.aipai.fragment;

import com.zhangrui.aipai.base.BaseMvpFragment;
import com.zhangrui.aipai.mvp.model.Gank;
import com.zhangrui.aipai.mvp.presenter.GankPresenter;
import com.zhangrui.aipai.mvp.view.GankView;

/**
 * DESC:
 * Created by zhangrui on 2016/11/7.
 */

public class GankImgFragment extends BaseMvpFragment<GankPresenter> implements GankView {





    @Override
    public void initView() {

    }

    @Override
    public GankPresenter setPresenter() {
        return new GankPresenter(this);
    }

    @Override
    public void updateGankData(Gank gank) {

    }
}
