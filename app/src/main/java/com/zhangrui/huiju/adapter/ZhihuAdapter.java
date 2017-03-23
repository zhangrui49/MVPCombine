package com.zhangrui.huiju.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhangrui.huiju.R;
import com.zhangrui.huiju.mvp.model.ZhihuDaily;

import java.util.List;

/**
 * DESC:
 * Created by zhangrui on 2016/11/10.
 */

public class ZhihuAdapter extends BaseQuickAdapter<ZhihuDaily> {

    public ZhihuAdapter(List<ZhihuDaily> data) {
        super(R.layout.item_zhihu, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ZhihuDaily zhihuDaily) {
        baseViewHolder.setText(R.id.text,zhihuDaily.getTitle());
        Glide.with(mContext).load(zhihuDaily.getImages()[0]).into((ImageView) baseViewHolder.getView(R.id.image));
    }
}
