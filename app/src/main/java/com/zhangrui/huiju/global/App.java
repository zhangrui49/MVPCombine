package com.zhangrui.huiju.global;

import android.app.Application;

/**
 * DESC:
 * Created by zhangrui on 2016/11/3.
 */

public class App extends Application {

    private static Application sApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
    }

    public static Application getInstance() {
        return sApplication;
    }

}
