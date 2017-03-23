package com.zhangrui.huiju.base;

import android.os.Bundle;

/**
 * DESC:
 * Created by zhangrui on 2016/11/9.
 */

public abstract  class BaseMvpActivity<P extends BasePresenter> extends BaseActivity {
    protected P mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mPresenter = setPresenter();
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    public abstract P setPresenter();

}
