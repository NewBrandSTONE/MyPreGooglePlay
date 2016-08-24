package com.android.oz.mypregoogleplay.factory;

import android.support.v4.app.Fragment;

import com.android.oz.mypregoogleplay.fragment.AppFragment;
import com.android.oz.mypregoogleplay.fragment.CatoryFragment;
import com.android.oz.mypregoogleplay.fragment.GameFragment;
import com.android.oz.mypregoogleplay.fragment.HomeFragment;
import com.android.oz.mypregoogleplay.fragment.HotFragment;
import com.android.oz.mypregoogleplay.fragment.RecommondFragment;
import com.android.oz.mypregoogleplay.fragment.SubjectFragment;


/**
 * @author 王维波
 * @time 2016/8/19  11:54
 * @desc 是一个产生Fragment工厂内
 */
public class FragmentFactory {

    private static final int FRAGMENT_HOME = 0;//首页的fragment
    private static final int FRAGMENT_APP = 1;//应用
    private static final int FRAGMENT_GAME = 2;//游戏
    private static final int FRAGMENT_SUBJECT = 3;//专题
    private static final int FRAGMENT_RECOMMOND = 4;//推荐
    private static final int FRAGMENT_CATORY = 5;//分类
    private static final int FRAGMENT_HOT = 6;//排行

    public static Fragment createFragment(int position) {
        Fragment fragment = null;
        switch (position) {
            case FRAGMENT_HOME:
                fragment = new HomeFragment();
                break;
            case FRAGMENT_APP:
                fragment = new AppFragment();
                break;
            case FRAGMENT_GAME:
                fragment=new GameFragment();
                break;
            case FRAGMENT_SUBJECT:
                fragment=new SubjectFragment();
                break;
            case FRAGMENT_RECOMMOND:
                fragment=new RecommondFragment();
                break;
            case FRAGMENT_CATORY:
                fragment=new CatoryFragment();
                break;
            case FRAGMENT_HOT:
                fragment=new HotFragment();
                break;
        }

        return fragment;
    }
}
