package com.zhangrui.aipai.mvp.view;

import com.zhangrui.aipai.base.BaseView;
import com.zhangrui.aipai.viewmodel.Story;

/**
 * DESC:
 * Created by zhangrui on 2016/11/9.
 */

public interface StoryView extends BaseView{
    void getStory(Story story);
}
