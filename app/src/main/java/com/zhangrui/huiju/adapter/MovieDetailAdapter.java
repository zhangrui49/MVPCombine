package com.zhangrui.huiju.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhangrui.huiju.R;
import com.zhangrui.huiju.mvp.model.MovieDetail;

import java.util.List;

/**
 * DESC:
 * Created by zhangrui on 2017/3/23.
 */

public class MovieDetailAdapter extends BaseQuickAdapter<MovieDetail.CastsBean> {

    public MovieDetailAdapter(List<MovieDetail.CastsBean> data) {
        super(R.layout.item_cast, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, MovieDetail.CastsBean castsBean) {
        Glide.with(mContext).load(castsBean.getAvatars().getMedium()).into((ImageView) baseViewHolder.getView(R.id.cast_head));
        baseViewHolder.setText(R.id.cast_name, castsBean.getName());

    }
}
