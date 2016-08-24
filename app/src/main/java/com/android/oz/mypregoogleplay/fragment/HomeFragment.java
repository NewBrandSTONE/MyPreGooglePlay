package com.android.oz.mypregoogleplay.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.oz.mypregoogleplay.utils.UIUtils;


/**
 * @author 王维波
 * @time 2016/8/19  14:38
 * @desc ${TODD}
 */
public class HomeFragment extends android.support.v4.app.Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView tv=new TextView(UIUtils.getContext());
        tv.setText(this.getClass().getSimpleName());
        tv.setGravity(Gravity.CENTER);
        return tv;
    }
}
