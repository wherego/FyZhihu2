package com.fengy.zhihu;

import android.app.Application;

import com.fengy.zhihu.util.AppContextUtil;

/**
 * Created by fengyun on 2017/4/9.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AppContextUtil.init(this);
    }
}
