package com.android.oz.mypregoogleplay.fragment;

import android.os.SystemClock;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.android.oz.mypregoogleplay.base.BaseFragment;
import com.android.oz.mypregoogleplay.base.LoadPager;
import com.android.oz.mypregoogleplay.utils.UIUtils;

import java.util.Random;


/**
 * @time 2016/8/19  14:40
 * @desc ${TODD}
 */
public class RecommondFragment extends BaseFragment {
    @Override
    public View initSuccessView() {
        TextView textView = new TextView(UIUtils.getContext());
        textView.setText("RecommondFragment");
        textView.setGravity(Gravity.CENTER);
        return textView;
    }

    @Override
    public LoadPager.RefreshState initData() {
        SystemClock.sleep(2000);
        // 模拟随机数显示不同状态的视图

        Random random = new Random();
        int index = random.nextInt(3);
        LoadPager.RefreshState[] indexs = {LoadPager.RefreshState.SUCCESS, LoadPager.RefreshState.ERROR, LoadPager.RefreshState.EMPTY, LoadPager.RefreshState.LOAD};
        return indexs[index];
    }
}
