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
public abstract class LoadPager extends FrameLayout {

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
    private static final int STATE_LOADING = 1;
    /**
     * 显示成功图的状态码
     **/
    private static final int STATE_SUCCESS = 2;
    /**
     * 显示失败视图的状态码
     **/
    private static final int STATE_ERROR = 3;

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

    /**
     * 任何应用其实都只有4中页面类型
     * ①加载页面
     * ②错误页面
     * ③空页面
     * ④成功页面
     * <p/>
     * 1-3三种页面一个应用基本上是固定的
     * 但是每一个Framgnet对应的④成功页面就不一样
     * 进入应用的时候显示①,其他页面需要加载数据之后才能确定显示哪个
     */
    public void refreshViewState() {
        // 将三个视图隐藏
        mErrorView.setVisibility(mCurrentState == STATE_ERROR ? VISIBLE : GONE);
        mLoadingView.setVisibility(mCurrentState == STATE_LOADING ? VISIBLE : GONE);
        mEmptyView.setVisibility(mCurrentState == STATE_EMPTY ? VISIBLE : GONE);

        if (mSuccessView == null && mCurrentState == STATE_SUCCESS) {
            // 加载成功视图
            mSuccessView = initSuccessView();
            // 别忘记添加View
            this.addView(mSuccessView);
        }

        if (mSuccessView != null) {
            // 控制显示隐藏
            mSuccessView.setVisibility(mCurrentState == STATE_SUCCESS ? VISIBLE : GONE);
        }
    }

    /**
     * @desc 加载成功的视图
     **/
    protected abstract View initSuccessView();


    /**
     * ①触发加载    进入页面开始加载/点击某一个按钮的时候开始加载
     * ②异步加载数据 -->显示加载视图
     * ③处理加载结果
     * ④成功 --> 显示成功视图
     * ⑤失败
     * ⑥数据为空 --> 显示空的视图
     * ⑦数据加载失败 --> 显示加载失败的视图
     */
    public void triggleData() {
        // 无论如何都要在子线程中加载数据
        new Thread(new LoadTask()).start();
    }

    class LoadTask implements Runnable {

        @Override
        public void run() {
            // 真正在子线程中加载数据
            RefreshState state = initData();

            // 将加载数据的状态放到当前状态中
            mCurrentState = state.getState();
            UIUtils.postTaskSafely(new Runnable() {
                @Override
                public void run() {
                    // 根据状态重新刷新ui
                    refreshViewState();
                }
            });
        }
    }

    /**
     * @desc 加载数据的方法, 父类不知道子类如何加载数据, 所以子类必须实现
     * @called 加载数据的时候被调用
     **/
    public abstract RefreshState initData();


    /**
     * 控制initData返回的数据,只能是三个视图,一个成功,一个失败,一个空,正在加载的视图已经默认显示了
     **/
    public enum RefreshState {

        LOAD(STATE_LOADING), SUCCESS(STATE_SUCCESS), EMPTY(STATE_EMPTY), ERROR(STATE_ERROR);

        int state;

        public int getState() {
            return state;
        }

        RefreshState(int state) {
            this.state = state;
        }

    }
}
