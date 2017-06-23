package com.etouse.skinswitch.app;

import android.app.Application;

import cn.feng.skin.manager.loader.SkinManager;

/**
 * Created by Administrator on 2017/6/23.
 */

public class SkinApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SkinManager.getInstance().init(this);
        SkinManager.getInstance().load();
    }
}
