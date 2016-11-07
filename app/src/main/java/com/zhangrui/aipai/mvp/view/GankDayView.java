package com.zhangrui.aipai.mvp.view;

import com.zhangrui.aipai.base.BaseView;
import com.zhangrui.aipai.mvp.model.GankDayData;

/**
 * DESC:
 * Created by zhangrui on 2016/11/7.
 */

public interface GankDayView extends BaseView {
    public void updateDayData(GankDayData dayData);
}
