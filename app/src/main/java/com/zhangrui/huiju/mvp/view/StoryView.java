package com.zhangrui.huiju.mvp.view;

import com.zhangrui.huiju.base.BaseView;
import com.zhangrui.huiju.viewmodel.Story;

/**
 * DESC:
 * Created by zhangrui on 2016/11/9.
 */

public interface StoryView extends BaseView{
    void getStory(Story story);
}
