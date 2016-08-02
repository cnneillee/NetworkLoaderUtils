package com.neil.networkloaderutils;

import android.app.Application;

import com.neil.imageloaderutils.Initialize;

/**
 * 作者：Neil on 2016/8/2 15:57.
 * 邮箱：cn.neillee@gmail.com
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Initialize.initializeFresco(this);
        Initialize.initializeUniversial(this);
        Initialize.initializeVolley(this);
    }
}
