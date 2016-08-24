package com.android.oz.mypregoogleplay.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.oz.mypregoogleplay.utils.UIUtils;

/**
 * Created by jonesleborn on 16/8/24.
 */
public abstract class BaseFragment extends Fragment {

    /**
     * 页面控制器
     **/
    public LoadPager mLoadPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 因为LoadPager方法是抽象的类,所以必须要实现抽象方法
        mLoadPager = new LoadPager(UIUtils.getContext()) {
            // 但是BaseFragment也不知道该如何实现这两个方法
            // 所以BaseFragment也将这两个方法抽象
            @Override
            protected View initSuccessView() {
                return BaseFragment.this.initSuccessView();
            }

            @Override
            public RefreshState initData() {
                return BaseFragment.this.initData();
            }
        };

        // 在这里调用加载数据的方法
        mLoadPager.triggleData();

        return mLoadPager;
    }

    public abstract View initSuccessView();

    public abstract LoadPager.RefreshState initData();
}
