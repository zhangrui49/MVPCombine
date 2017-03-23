package com.zhangrui.huiju.base;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * DESC:
 * Created by zhangrui on 2016/11/3.
 */

public abstract class BasePresenter<V> {

    public V mView;

    private CompositeSubscription mCompositeSubscription;


    public BasePresenter(V view) {
        mView = view;
    }

    public void detachView() {
        this.mView = null;
        onUnsubscribe();
    }

    public void onUnsubscribe() {
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }


    public void addSubscription(Observable observable, Subscriber subscriber) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }
}
