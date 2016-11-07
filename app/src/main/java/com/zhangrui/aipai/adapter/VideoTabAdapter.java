package com.zhangrui.aipai.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhangrui.aipai.R;
import com.zhangrui.aipai.mvp.model.Video;

import java.util.List;

/**
 * DESC:
 * Created by zhangrui on 2016/11/4.
 */

public class VideoTabAdapter extends BaseQuickAdapter<Video> {

    public VideoTabAdapter(List<Video> data) {
        super(R.layout.item_video_tab, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Video video) {
        baseViewHolder.setText(R.id.name,video.getScreen_name());
        baseViewHolder.setText(R.id.play_count,video.getPlays_count()+"");
        baseViewHolder.setText(R.id.like_count,video.getLikes_count()+"");
        baseViewHolder.setText(R.id.comment_count,video.getComments_count()+"");
        baseViewHolder.setText(R.id.caption,video.getCaption()+"");
        Glide.with(mContext).load(video.getAvatar()).into((ImageView) baseViewHolder.getView(R.id.head));
        Glide.with(mContext).load(video.getCover_pic()).into((ImageView) baseViewHolder.getView(R.id.cover));
    }
}
