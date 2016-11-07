package com.zhangrui.aipai.adapter;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhangrui.aipai.R;
import com.zhangrui.aipai.mvp.model.GankData;
import com.zhangrui.aipai.mvp.model.GankDay;

import java.util.List;

/**
 * DESC:
 * Created by zhangrui on 2016/11/7.
 */

public class GankDayDataAdapter extends BaseSectionQuickAdapter<GankDay> {

    public GankDayDataAdapter(int layoutResId, int sectionHeadResId, List data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GankDay item) {
        GankData data=item.t;
        helper.setText(R.id.desc, data.getDesc());
        helper.setText(R.id.time, data.getPublishedAt());
        helper.setText(R.id.who, data.getWho());
    }

    @Override
    protected void convertHead(BaseViewHolder helper, final GankDay item) {
        helper.setText(R.id.header, item.header);
    }
}
