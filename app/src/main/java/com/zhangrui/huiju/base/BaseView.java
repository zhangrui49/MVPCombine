package com.zhangrui.huiju.base;

/**
 * DESC:
 * Created by zhangrui on 2016/11/3.
 */

public interface BaseView {

    void showProgress();

    void dismissProgress();

    void showError(CharSequence msg);

}
