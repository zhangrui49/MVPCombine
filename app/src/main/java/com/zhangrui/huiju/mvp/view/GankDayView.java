package com.zhangrui.huiju.mvp.view;

import com.zhangrui.huiju.base.BaseView;
import com.zhangrui.huiju.mvp.model.GankDayData;

/**
 * DESC:
 * Created by zhangrui on 2016/11/7.
 */

public interface GankDayView extends BaseView {
    public void updateDayData(GankDayData dayData);
}
