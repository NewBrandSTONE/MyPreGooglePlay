package com.android.oz.mypregoogleplay.utils;

import android.content.Context;
import android.content.res.Resources;

import com.android.oz.mypregoogleplay.base.MyApplication;


/**
 * @author 王维波
 * @time 2016/8/19  10:37
 * @desc ${TODD}
 */
public class UIUtils {

    // 得到上下文
    public static Context getContext(){
        return MyApplication.getContext();
    }

    //得到Resources对象
    public static Resources getResources(){
        return getContext().getResources();
    }

    //得到配置的String信息
    public  static String getString(int resId){
        return  getResources().getString(resId);
    }

    //得到配置String数组信息
    public  static String[] getStringArr(int resId){
        return getResources().getStringArray(resId);
    }

    //得到包名
    public static String getPackageName() {
        return getContext().getPackageName();
    }






}
