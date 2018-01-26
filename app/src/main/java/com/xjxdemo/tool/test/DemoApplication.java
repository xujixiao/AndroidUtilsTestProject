package com.xjxdemo.tool.test;

import com.alibaba.android.arouter.launcher.ARouter;
import com.base.lib.BaseApplication;

/**
 * Created by 11073 on 2018/1/23.
 */

public class DemoApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        initArouter();
    }

    private void initArouter() {
        if (BuildConfig.DEBUG) {
            ARouter.openDebug();
            ARouter.openLog();
        }
        ARouter.init(this);
    }
}
