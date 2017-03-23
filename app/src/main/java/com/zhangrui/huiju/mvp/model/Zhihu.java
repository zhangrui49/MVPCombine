package com.zhangrui.huiju.mvp.model;

import java.util.ArrayList;

/**
 * DESC:
 * Created by zhangrui on 2016/11/9.
 */

public class Zhihu {

    private String date;
    private ArrayList<ZhihuDaily> mZhihuDailys;
    private ArrayList<ZhihuDaily> stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<ZhihuDaily> getZhihuDailys() {
        return mZhihuDailys;
    }

    public void setZhihuDailys(ArrayList<ZhihuDaily> ZhihuDailys) {
        this.mZhihuDailys = ZhihuDailys;
    }

    public ArrayList<ZhihuDaily> getStories() {
        return stories;
    }

    public void setStories(ArrayList<ZhihuDaily> stories) {
        this.stories = stories;
    }
}
