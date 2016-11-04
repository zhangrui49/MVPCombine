package com.zhangrui.aipai.base;

import android.os.Bundle;
import android.view.View;

/**
 * DESC:
 * Created by zhangrui on 2016/11/4.
 */

public abstract class BaseMvpFragment<P extends BasePresenter> extends BaseFragment {
    protected P mPresenter;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mPresenter = setPresenter();
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    public abstract P setPresenter();
}
