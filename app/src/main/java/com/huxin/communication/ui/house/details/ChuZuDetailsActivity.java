package com.huxin.communication.ui.house.details;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.huxin.communication.R;
import com.huxin.communication.adpter.DetailsTableNameAdapter;
import com.huxin.communication.adpter.SellDetailsAdapter;
import com.huxin.communication.adpter.ViewPagerAdapter;
import com.huxin.communication.base.BaseActivity;
import com.huxin.communication.entity.InformationDetailEntity;
import com.huxin.communication.http.ApiModule;
import com.huxin.communication.ui.TIMChatActivity;
import com.huxin.communication.ui.my.MyInformation.MyInformationActivity;
import com.huxin.communication.utils.DateUtil;
import com.huxin.communication.utils.PreferenceUtil;
import com.huxin.communication.view.SpaceItemDecoration;
import com.sky.kylog.KyLog;
import com.tencent.qcloud.uikit.TUIKit;
import com.tencent.qcloud.uikit.common.IUIKitCallBack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangzanxiong on 2018/12/13.
 */

public class ChuZuDetailsActivity extends BaseActivity {
    private RecyclerView mRecyclerView;
    private SellDetailsAdapter mAdpter;
    private RecyclerView mRecyclerViewTableName;
    private DetailsTableNameAdapter mAdapterTableName;
    private List<String> list;
    private int pid;
    private ViewPagerAdapter mViewPagerAdapter;
    private TextView mTextViewvillageName;
    private TextView mTextViewhouseType;
    private TextView mTextViewAcreage;
    private TextView mTextViewTotalPrice;
    private TextView mTextViewFitment;
    private TextView mTextViewUnitPrice;
    private TextView mTextViewOrientation;
    private TextView mTextViewhouseHoldAppliances;
    private TextView mTextViewfindNumber;
    private TextView mTextViewfindNumbers;
    private TextView mTextViewTotalFloorNumber;
    private TextView mTextViewPublicTime;
    private TextView mTextViewMoreSimilar;
    private List<InformationDetailEntity> mList;

    private TextView mTextViewCampany;
    private TextView mTextViewUserName;
    private String userName = "";
    /**
     * 滚动焦点图片
     **/
    private ViewPager mViewPager;
    /**
     * 滚动焦点图片页码点
     **/
    private LinearLayout dot;

    private boolean mIsStop = false;// 焦点图触摸暂停切换

    private int big_index = 0;// 大焦点图自动切换初始位置

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_sell_details);
    }

    @Override
    protected void initViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_sell_details);
        mRecyclerViewTableName = (RecyclerView) findViewById(R.id.recycler_tableName);
        mViewPager = (ViewPager) findViewById(R.id.detail_viewPager);
        mTextViewvillageName = (TextView) findViewById(R.id.villageName_details_sell);
        mTextViewhouseType = (TextView) findViewById(R.id.houseType_detail_sell);
        mTextViewAcreage = (TextView) findViewById(R.id.acreage_detail_sell);
        mTextViewTotalPrice = (TextView) findViewById(R.id.totalPrice_details_sell);
        mTextViewFitment = (TextView) findViewById(R.id.fitment_detail_sell);
        mTextViewUnitPrice = (TextView) findViewById(R.id.unitPrice_detail_sell);
        mTextViewOrientation = (TextView) findViewById(R.id.orientation_detail_sell);
        mTextViewhouseHoldAppliances = (TextView) findViewById(R.id.houseHoldAppliances_detail_sell);
        mTextViewfindNumber = (TextView) findViewById(R.id.findNumber_detail_sell);
        mTextViewfindNumbers = (TextView) findViewById(R.id.findNumbers_detail_sell);
        mTextViewTotalFloorNumber = (TextView) findViewById(R.id.totalFloorNumber_detail_sell);
        mTextViewPublicTime = (TextView) findViewById(R.id.publicTime_detail_sell);
        mTextViewMoreSimilar = (TextView) findViewById(R.id.more_similar);

        mTextViewUserName = (TextView) findViewById(R.id.username);
        mTextViewCampany = (TextView) findViewById(R.id.campany);

        dot = (LinearLayout) findViewById(R.id.main_activity_focus_dot);
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.zaixianwen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String userSig = PreferenceUtil.getString("usersig");
                if (mList != null && mList.size() > 0) {
                    //KyLog.i("uid = " + mList.get(0).getUid());
                    //KyLog.i("usersig = " + userSig);
                    String userId = PreferenceUtil.getInt(UID) + "";
                    String userSig = PreferenceUtil.getString("usersig");
                    onRecvUserSig(userId, userSig, String.valueOf(mList.get(0).getUid()));
                }
            }
        });
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        initData();
    }

    public void initData() {
        pid = getIntent().getIntExtra("pid", 0);
        KyLog.d(pid + "");
        showProgressDialog();
        ApiModule.getInstance().getInformation(String.valueOf(pid), "2")
                .subscribe(informationDetailEntities -> {

                    cancelProgressDialog();
                    KyLog.d(informationDetailEntities.size() + "");
                    mList = informationDetailEntities;
                    if (informationDetailEntities != null && informationDetailEntities.size() > 0) {
                        setOnBinner(informationDetailEntities);
                        setTableNameAdapter(informationDetailEntities);
                        setData(informationDetailEntities);
                    }

                }, throwable -> {
                    KyLog.d(throwable.toString());
                    cancelProgressDialog();
                    Toast.makeText(this, throwable.getMessage().toString(), Toast.LENGTH_SHORT).show();
                });
    }

    private List<String> getTableNameList(List<InformationDetailEntity> list, int position) {
        List<String> tabNameList = new ArrayList<>();
        String[] strings = list.get(position).getTabName().split(",");
        KyLog.d(list.get(position).getTabName());
        for (String str : strings) {
            tabNameList.add(str);
        }
        return tabNameList;
    }

    private void setTableNameAdapter(List<InformationDetailEntity> list) {
        if (getTableNameList(list, 0).size() > 0) {
            GridLayoutManager manager = new GridLayoutManager(ChuZuDetailsActivity.this, 5);
            mAdapterTableName = new DetailsTableNameAdapter(getTableNameList(list, 0), this);
            mRecyclerViewTableName.setAdapter(mAdapterTableName);
            mRecyclerViewTableName.setLayoutManager(manager);
            mRecyclerViewTableName.addItemDecoration(new SpaceItemDecoration(0, 15));

            LinearLayoutManager managerLine = new LinearLayoutManager(this);
            mAdpter = new SellDetailsAdapter(list, this);
            mRecyclerView.setAdapter(mAdpter);
            mRecyclerView.setLayoutManager(managerLine);
//            mRecyclerView.addItemDecoration(new SpaceItemDecoration(0, 15));
        }
    }

    private void setData(List<InformationDetailEntity> list) {
        mTextViewTotalFloorNumber.setText(String.valueOf(list.get(0).getTotalFloorNumber()));
        mTextViewvillageName.setText(list.get(0).getVillageName());
        mTextViewhouseHoldAppliances.setText(String.valueOf(list.get(0).getHouseHoldAppliances()));
        mTextViewhouseType.setText(String.valueOf(list.get(0).getHouseType()));
        mTextViewFitment.setText(String.valueOf(list.get(0).getFitment()));
        mTextViewOrientation.setText(String.valueOf(list.get(0).getOrientation()));

        mTextViewAcreage.setText(String.valueOf(list.get(0).getAcreage()) + "㎡");
        mTextViewTotalPrice.setText(String.valueOf(list.get(0).getTotalPrice()) + "万");
        mTextViewUnitPrice.setText(String.valueOf(list.get(0).getUnitPrice()) + "㎡");
        mTextViewfindNumber.setText(String.valueOf(list.get(0).getFindNumber()) + "次");
        mTextViewfindNumbers.setText(String.valueOf(list.get(0).getFindNumber()) + "次");
        mTextViewPublicTime.setText(DateUtil.timeslashData(String.valueOf(list.get(0).getShowTime())));


        mTextViewCampany.setText(list.get(0).getUserModel().getCompanyName());
        mTextViewUserName.setText(list.get(0).getUserModel().getUsername());
        userName = list.get(0).getUserModel().getUsername();

        if (list.size() >= 2) {
            mTextViewMoreSimilar.setText("更多相似房源(" + (list.size() - 1) + ")");
        } else {
            mTextViewMoreSimilar.setText("更多相似房源(" + 0 + ")");
        }

    }

    // 设置焦点图片数量小圆点的方法
    // private int page=0;
    public void setCurPage(int page, int count) {
        try {
            dot.removeAllViews();
            for (int i = 0; i < count; i++) {
                ImageView imgCur = new ImageView(this);
                imgCur.setBackgroundResource(R.drawable.gray);
                imgCur.setId(i);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        -2, -2);
                // lp.leftMargin = 5;
                lp.rightMargin = 5;
                if (imgCur.getId() == page) {
                    imgCur.setBackgroundResource(R.drawable.orange);
                }
                dot.addView(imgCur, lp);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    private final int SCROLL_WHAT = 1;// 大焦点图

    private MyFocusHandler scrollHandler = new MyFocusHandler();

    /**
     * 焦点图自动滚动方法
     *
     * @param delayTimeInMills
     */
    private void sendScrollMessage(long delayTimeInMills) {
        /** remove messages before, keeps one message is running at most **/
        scrollHandler.removeMessages(SCROLL_WHAT);
        scrollHandler.sendEmptyMessageDelayed(SCROLL_WHAT, delayTimeInMills);
    }

    public void onScroll(int scrollY) {

    }


    private class MyFocusHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case SCROLL_WHAT:
//                    if (big_index == mSlideAdsList.size()) {
//                        big_index = 0;
//                    }
                    // 点击图片时，自动切换暂停
//                    if (!mIsStop) {
//                        int currentIndex = mViewPager.getCurrentItem();
//                        if (++currentIndex == mAdpter.getCount()) {
                    mViewPager.setCurrentItem(0);
//                        } else {
////                            mViewPager.setCurrentItem(currentIndex, true);
//                        }
//                    }
//                    sendScrollMessage(3000);
                    break;

            }
        }

    }

    /**
     * 加载binner图
     */
    private void setOnBinner(List<InformationDetailEntity> list) {
        List<String> imageList = new ArrayList<>();
        if (!TextUtils.isEmpty(list.get(0).getPhotoUrl())) {
            String[] str = list.get(0).getPhotoUrl().split(",");
            for (String strs : str) {
                imageList.add(strs);
            }
        }
        KyLog.d(imageList.size() + "");
        if (imageList.size() > 0) {
            mViewPagerAdapter = new ViewPagerAdapter(this, imageList);
            mViewPager.setAdapter(mViewPagerAdapter);

            if (imageList.size() > 1) {
                mViewPager.setCurrentItem(((Short.MAX_VALUE / 2) / imageList.size()) * imageList.size(), false);
            }
            setCurPage(0 % imageList.size(), imageList.size());

            mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                }

                @Override
                public void onPageSelected(int position) {
                    big_index = position;
                    setCurPage(position % imageList.size(), imageList.size());
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }
    }

    private void onRecvUserSig(String userId, String userSig, String targetId) {
        TUIKit.login(userId, userSig, new IUIKitCallBack() {
            @Override
            public void onSuccess(Object data) {
                KyLog.i("imlogin onSuccess", data);
                Intent intent = new Intent(ChuZuDetailsActivity.this, TIMChatActivity.class);
                intent.putExtra("TARGET_ID", targetId);
                intent.putExtra("username", userName);
                startActivity(intent);
            }

            @Override
            public void onError(String module, int errCode, String errMsg) {
                Toast.makeText(ChuZuDetailsActivity.this, "用户Id == " + userId + " \n" + "imlogin fail" + errMsg
                        + " \n" + "imlogin fail" + userSig, Toast.LENGTH_SHORT).show();
                KyLog.e("imlogin fail", errMsg);
            }
        });
    }
}
