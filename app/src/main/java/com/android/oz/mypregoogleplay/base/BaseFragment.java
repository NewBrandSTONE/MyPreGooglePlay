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
public class BaseFragment extends Fragment {

    /**
     * 页面控制器
     **/
    public LoadPager mLoadPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return new LoadPager(UIUtils.getContext());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


}
