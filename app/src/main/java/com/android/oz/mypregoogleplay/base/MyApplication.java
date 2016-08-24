package com.android.oz.mypregoogleplay.base;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * @time 2016/8/19  10:14
 * @desc app 运行时最先运行的类,可以做一些全局初始化的方法或者常量
 */
public class MyApplication extends Application {


    private static Context mContext;
    private static  Handler mHandler;
    private  static long mMainThreadId;


    //获得全局上下文,注意在公司开发一定要写成静态
    public static Context getContext(){
        return  mContext;
    }

    //得到全局主线程中handler
    public  static Handler getHandler(){
        return  mHandler;
    }

    public  static long getMainThredId(){
        return  mMainThreadId;
    }


    /**------是老母子方法,也即是所有方法之前运行,可以初始化-----**/
    @Override
    public void onCreate() {

        /**------全局上下文-----**/
        mContext = getApplicationContext();
        /**------主线程handler-----**/
        mHandler = new Handler();
        /**------主线程的id-----**/
        mMainThreadId = android.os.Process.myTid();

        /**
         * Process.myTid(); 线程id
           Process.myUid(); 用户id
           Process.myPid(); 进程id
         */


        super.onCreate();
    }
}
