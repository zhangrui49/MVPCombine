package com.zhangrui.huiju.mvp.view;

import com.zhangrui.huiju.base.BaseView;
import com.zhangrui.huiju.mvp.model.Video;

import java.util.List;

/**
 * DESC:
 * Created by zhangrui on 2016/11/3.
 */

public interface VideoView extends BaseView {
    void updateVideoList(List<Video> videos);
}
