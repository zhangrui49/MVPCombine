package com.zhangrui.aipai.net;

import rx.Subscriber;

/**
 * DESC:
 * Created by zhangrui on 2016/11/2.
 */

public abstract class ApiCallBack<T> extends Subscriber<T> {

    public abstract void onSuccess(T model);

    public abstract void onFinish();

    public abstract void onFailure(String msg);

    @Override
    public void onError(Throwable e) {

        onFailure(e.getMessage());
    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    @Override
    public void onCompleted() {
        onFinish();
    }
}
