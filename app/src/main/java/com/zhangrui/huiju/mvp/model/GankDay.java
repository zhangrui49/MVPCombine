package com.zhangrui.huiju.mvp.model;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * DESC:
 * Created by zhangrui on 2016/11/7.
 */

public class GankDay extends SectionEntity<GankData> {

    public GankDay(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public GankDay(GankData dayData) {
        super(dayData);
    }


}
