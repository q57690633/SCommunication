package com.huxin.communication.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.huxin.communication.R;
import com.huxin.communication.base.AppManager;
import com.huxin.communication.base.BaseActivity;
import com.huxin.communication.listener.FragmentBackListener;
import com.huxin.communication.ui.fragment.AssortmentFragment;
import com.huxin.communication.ui.fragment.HomeFragment;
import com.huxin.communication.ui.fragment.InformationFragment;
import com.huxin.communication.ui.fragment.ShopCarFragment;
import com.huxin.communication.ui.fragment.UsersFragment;
import com.huxin.communication.widgets.tab.TabLayout;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements TabLayout.OnTabClickListener, TabLayout.OnPreClickListener {
    private ArrayList<MyOnTouchListener> onTouchListeners = new ArrayList<MyOnTouchListener>(
            10);
    private Fragment mFragmentHome;
    private FragmentBackListener backListener;
    private boolean isInterception = false;
    private TabLayout mTabLayout;
    private Fragment mCurrentFragment;

    private String strCurrentFragmentName;
    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initViews() {
//        setToolbarCenterMode("互信", 0);

        mTabLayout = (TabLayout) findViewById(R.id.main_tabLayout);
//        mTabLayout.setBackgroundResource(R.color.white);
        mTabLayout.setTabLabelColor(R.color.tabDef, R.color.blue);
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        initTab();
        setFrgment();
    }

    public FragmentBackListener getBackListener() {
        return backListener;
    }

    public void setBackListener(FragmentBackListener backListener) {
        this.backListener = backListener;
    }

    public boolean isInterception() {
        return isInterception;
    }

    public void setInterception(boolean isInterception) {
        this.isInterception = isInterception;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        for (MyOnTouchListener listener : onTouchListeners) {
            listener.onTouch(ev);
        }
        return super.dispatchTouchEvent(ev);
    }

    public void registerMyOnTouchListener(MyOnTouchListener myOnTouchListener) {
        onTouchListeners.add(myOnTouchListener);
    }

    public void unregisterMyOnTouchListener(MyOnTouchListener myOnTouchListener) {
        onTouchListeners.remove(myOnTouchListener);
    }


    public interface MyOnTouchListener {
        public boolean onTouch(MotionEvent ev);
    }

    private void initTab() {
        ArrayList<TabLayout.Tab> tabs = new ArrayList<>();
        TabLayout.Tab tabHome = new TabLayout.Tab(R.drawable.selector_tab_home, R.string.main_tab_home, HomeFragment.class);
        TabLayout.Tab tabAssortment = new TabLayout.Tab(R.drawable.selector_tab_phone, R.string.main_tab_Assortment, AssortmentFragment.class);
        TabLayout.Tab tabInformation = new TabLayout.Tab(R.drawable.selector_tab_release, R.string.main_tab_Information, InformationFragment.class);
        TabLayout.Tab tabShopCar = new TabLayout.Tab(R.drawable.selector_tab_member, R.string.main_tab_ShopCar, ShopCarFragment.class);
        TabLayout.Tab tabUsers = new TabLayout.Tab(R.drawable.selector_tab_my, R.string.main_tab_Users, UsersFragment.class);

        tabs.add(tabHome);
        tabs.add(tabAssortment);
        tabs.add(tabInformation);
        tabs.add(tabShopCar);
        tabs.add(tabUsers);


        mTabLayout.setUpData(tabs, this, this::onPreClick);
        mTabLayout.setCurrentTab(0);
    }

//    public void setRefreshCameraCallBack(RefreshCameraCallBack cameraCallBack){
//        mRefreshCameraCallBack = cameraCallBack;
//    }

    private void initData(){
//        if (PreferenceUtil.)
    }

    @Override
    public void onTabClick(TabLayout.Tab tag) {
        strCurrentFragmentName = tag.getFragment().getSimpleName();

        setToolbarCenter(tag.getLabelResId(), R.menu.menu_main);

        try {
            Fragment fragmentByTag = getSupportFragmentManager().findFragmentByTag(tag.getFragment().getSimpleName());
            if (fragmentByTag == null) {
                fragmentByTag = (Fragment) tag.getFragment().newInstance();
                if (mCurrentFragment == null) {
                    getSupportFragmentManager().beginTransaction()
//                            .attach(fragmentByTag).commitAllowingStateLoss();
                            .add(R.id.main_container, fragmentByTag, fragmentByTag.getClass().getSimpleName()).commitAllowingStateLoss();
                } else {
                    getSupportFragmentManager().beginTransaction()
//                            .hide(mCurrentFragment)
                            .detach(mCurrentFragment)
//                            .attach(fragmentByTag).commitAllowingStateLoss();
                            .add(R.id.main_container, fragmentByTag, fragmentByTag.getClass().getSimpleName()).commitAllowingStateLoss();
                }
            } else {
                if (mCurrentFragment != null) {
                    getSupportFragmentManager().beginTransaction()
                            .detach(mCurrentFragment)
                            .attach(fragmentByTag).commitAllowingStateLoss();
//                            .hide(mCurrentFragment)
//                            .show(fragmentByTag).commitAllowingStateLoss();
                } else {
                    getSupportFragmentManager().beginTransaction()
//                            .show(fragmentByTag).commitAllowingStateLoss();
                            .attach(fragmentByTag).commitAllowingStateLoss();

                }
            }
            mCurrentFragment = fragmentByTag;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onPreClick(TabLayout.Tab tab) {
        if (tab.getFragment().getSimpleName().equals("InformationFragment")) {
            mTabLayout.setVisibility(View.GONE);
        } else {
            mTabLayout.setVisibility(View.VISIBLE);
        }
        return true;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_setting:
                Toast.makeText(this, "SETTING", Toast.LENGTH_SHORT).show();
                return true;
            default:
                break;
        }

        return false;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出百行同业", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                AppManager.getInstance().finishAllActivity();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void setFrgment() {
        mTabLayout.setCurrentTab(0);
    }

//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//
//        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
//            if (isInterception()) {
//                KyLog.d("isInterception == " + isInterception());
//                if (backListener != null) {
//                    KyLog.d("backListener == " + backListener);
//                    backListener.onBackListener();
//                    return false;
//                }
//
//            }
//        }
//
//        return super.onKeyDown(keyCode, event);
//    }

//    @Override
//    public void onBackPressed() {
//        if (!BackHandlerHelper.handleBackPress(this)) {
//            super.onBackPressed();
//        }
//    }

}
