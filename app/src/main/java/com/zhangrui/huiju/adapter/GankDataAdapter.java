package com.zhangrui.huiju.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhangrui.huiju.R;
import com.zhangrui.huiju.mvp.model.GankData;

import java.util.List;

/**
 * DESC:
 * Created by zhangrui on 2016/11/7.
 */

public class GankDataAdapter extends BaseMultiItemQuickAdapter<GankData> {

    public GankDataAdapter(List data) {
        super(data);
        addItemType(GankData.TEXT, R.layout.item_gank_data);
        addItemType(GankData.IMG, R.layout.item_gank_img);
    }

    @Override
    protected void convert(BaseViewHolder helper, GankData item) {
        switch (helper.getItemViewType()) {
            case GankData.TEXT:
                helper.setText(R.id.desc, item.getDesc());
                helper.setText(R.id.time, item.getPublishedAt());
                helper.setText(R.id.who, item.getWho());
                break;
            case GankData.IMG:
                Glide.with(mContext)
                        .load(item.getUrl())
                        .placeholder(R.drawable.loading)
                        .error(R.drawable.error)
                        .animate(R.anim.alpha_in)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .crossFade()
                        .into((ImageView) helper.getView(R.id.image));
                break;
        }
    }
}
