package com.caowj.basic;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.caowj.basic.helper.FastActivityManager;

/**
 * Created by huangyin on 2018-05-25
 */
public abstract class FastBaseApplication extends Application {

    // 此变量application不能转成FastBaseApplication，因为如果集成到第三方应用中，第三方应用
    // 的Application未继承Lego的Application，此变量就不是FastBaseApplication的实例
    static Application application;

    public static Application getApplication() {
        return application;
    }

    /**
     * 注册activity的生命周期监听
     */
    private static void registerActivityLifecycleCallbacks(Application application) {
        application.registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                // 加入Activity统一管理
                FastActivityManager.addActivity(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                // Activity管理栈中移除
                FastActivityManager.removeActivity(activity);
            }
        });
    }

    @Override
    public void onCreate() {
        super.onCreate();
        FastBaseApplication.application = this;

        // 注册acitivity生命周期监听，以维护比较全的activity栈
        registerActivityLifecycleCallbacks(application);
    }
}

