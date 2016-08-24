package com.android.oz.mypregoogleplay.utils;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Process;

import com.android.oz.mypregoogleplay.base.MyApplication;


/**
 * @time 2016/8/19  10:37
 * @desc ${TODD}
 */
public class UIUtils {

    // 得到上下文
    public static Context getContext() {
        return MyApplication.getContext();
    }

    //得到Resources对象
    public static Resources getResources() {
        return getContext().getResources();
    }

    //得到配置的String信息
    public static String getString(int resId) {
        return getResources().getString(resId);
    }

    //得到配置String数组信息
    public static String[] getStringArr(int resId) {
        return getResources().getStringArray(resId);
    }

    //得到包名
    public static String getPackageName() {
        return getContext().getPackageName();
    }

    /**
     * 获得主线程的id
     **/
    public static long getMainThreadId() {
        return MyApplication.getMainThredId();
    }

    /**
     * 获得主线程的Handler
     **/
    public static Handler getMainHandler() {
        return MyApplication.getHandler();
    }

    /**
     * 安全的执行任务
     * 无论是在子线程中还是在主线程中
     **/
    public static void postTaskSafely(Runnable task) {
        long mainThreadId = getMainThreadId();
        long currentThreadId = Process.myTid();

        if (currentThreadId == mainThreadId) {
            // 说明是在主线程中
            task.run();
        } else {
            // 当前任务是在子线程
            Handler handler = getMainHandler();
            handler.post(task);
        }
    }

}
