package com.zhangrui.aipai.fragment;

import com.zhangrui.aipai.base.BaseMvpFragment;
import com.zhangrui.aipai.mvp.model.GankDayData;
import com.zhangrui.aipai.mvp.presenter.GankDayDataPresenter;
import com.zhangrui.aipai.mvp.view.GankDayView;

/**
 * DESC:
 * Created by zhangrui on 2016/11/7.
 */

public class GankDayFragment  extends BaseMvpFragment<GankDayDataPresenter> implements GankDayView {


    @Override
    public void initView() {

    }

    @Override
    public GankDayDataPresenter setPresenter() {
        return new GankDayDataPresenter(this);
    }

    @Override
    public void updateDayData(GankDayData dayData) {

    }
}
