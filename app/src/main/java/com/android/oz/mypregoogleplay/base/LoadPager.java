package com.android.oz.mypregoogleplay.base;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

import com.android.oz.mypregoogleplay.R;
import com.android.oz.mypregoogleplay.utils.UIUtils;

/**
 * ①页面控制
 * ②触发绑定数据
 */
public class LoadPager extends FrameLayout {

    private View mEmptyView;
    private View mSuccessView;
    private View mLoadingView;
    private View mErrorView;

    /**
     * 显示空视图的状态码
     **/
    private static final int STATE_EMPTY = 0;
    /**
     * 显示加载视图的状态码
     **/
    private static final int STATE_LOADING = 0;
    /**
     * 显示成功图的状态码
     **/
    private static final int STATE_SUCCESS = 0;
    /**
     * 显示失败视图的状态码
     **/
    private static final int STATE_ERROR = 0;

    /**
     * 当前的状态,默认显示Loading
     **/
    private int mCurrentState = STATE_LOADING;

    public LoadPager(Context context) {
        super(context);
        initCommonView();
    }

    private void initCommonView() {
        mEmptyView = View.inflate(UIUtils.getContext(), R.layout.pager_empty, null);
        // 别忘记添加视图到FrameLayout中 !!!!!
        this.addView(mEmptyView);
        mErrorView = View.inflate(UIUtils.getContext(), R.layout.pager_error, null);
        this.addView(mErrorView);
        mLoadingView = View.inflate(UIUtils.getContext(), R.layout.pager_loading, null);
        this.addView(mLoadingView);

        /**刷新视图显示状态**/
        refreshViewState();
    }

    private void refreshViewState() {
        // 将三个视图隐藏

    }


}
