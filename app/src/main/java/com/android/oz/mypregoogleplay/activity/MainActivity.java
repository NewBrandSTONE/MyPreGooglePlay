package com.android.oz.mypregoogleplay.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.ViewTreeObserver;

import com.android.oz.mypregoogleplay.R;
import com.android.oz.mypregoogleplay.base.BaseFragment;
import com.android.oz.mypregoogleplay.factory.FragmentFactory;
import com.android.oz.mypregoogleplay.utils.UIUtils;
import com.astuetz.PagerSlidingTabStrip;

public class MainActivity extends AppCompatActivity {

    private ActionBar mActionBar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private PagerSlidingTabStrip mTabStrip;
    private ViewPager mViewpager;
    private String[] mMainTilte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initActionBar();
        inidata();
    }

    private void inidata() {
        //获得标题数据
        mMainTilte = UIUtils.getStringArr(R.array.main_titles);
        mViewpager.setAdapter(new MainAdapter(getSupportFragmentManager()));
        mTabStrip.setViewPager(mViewpager);
    }


    private void initView() {
        setContentView(R.layout.activity_main);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.main_drawerlayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mTabStrip = (PagerSlidingTabStrip) findViewById(R.id.main_tabs);
        mViewpager = (ViewPager) findViewById(R.id.mian_viewpager);
        // 1 同步DrawerLayout 和ActionBarDrawerToggle 状态
        mToggle.syncState();
        // 2 设置监听
        mDrawerLayout.setDrawerListener(mToggle);

        // 设置Fragment更改事件,当Fragment更改之后再加载数据
        final MyPageChangeListener myPageChangeListener = new MyPageChangeListener();
        mTabStrip.setOnPageChangeListener(myPageChangeListener);
        // 判定界面渲染成功
        // 因为View在渲染的时候非常耗费时间
        mViewpager.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                myPageChangeListener.onPageSelected(0);
                // 记得要把这个监听事件移除
                mViewpager.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });

    }

    class MyPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            // 使用FragmentFactory生产Fragment
            BaseFragment fragment = FragmentFactory.createFragment(position);
            // 加载数据
            fragment.mLoadPager.triggleData();
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // 3打开和关闭的监听
                mToggle.onOptionsItemSelected(item);

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initActionBar() {
        //mActionBar.setSubtitle("SubTitle");// 设置子title部分
        //  mActionBar.setIcon(R.mipmap.ic_launcher);// 设置应用图标
        //  mActionBar.setDisplayShowTitleEnabled(true);// 设置菜单 标题是否可见
        // mActionBar.setDisplayShowHomeEnabled(true);// 设置应用图标是否
        // mActionBar.setDisplayUseLogoEnabled(false);// 设置是否显示Logo优先

        // 获取ActionBar
        mActionBar = getSupportActionBar();
        mActionBar.setTitle("GooglePlaye");// 设置主title部分
        mActionBar.setDisplayHomeAsUpEnabled(true);// 设置back按钮是否可见

    }

    /**
     * FragmentstatePagerAdapter
     */
    class MainAdapter extends FragmentPagerAdapter {

        public MainAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            System.out.println("初始化:" + mMainTilte[position]);
            return FragmentFactory.createFragment(position);
        }

        @Override
        public int getCount() {
            if (mMainTilte != null) {
                return mMainTilte.length;
            }
            return 0;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mMainTilte[position];
        }
    }
}
