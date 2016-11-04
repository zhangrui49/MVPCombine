package com.zhangrui.aipai.mvp.view;

import com.zhangrui.aipai.base.BaseView;
import com.zhangrui.aipai.mvp.model.Video;

import java.util.List;

/**
 * DESC:
 * Created by zhangrui on 2016/11/3.
 */

public interface VideoView extends BaseView {
    void updateVideoList(List<Video> videos);
}
