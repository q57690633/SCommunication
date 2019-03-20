package com.huxin.communication.ui.travel.details;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.util.m;
import com.huxin.communication.R;
import com.huxin.communication.adpter.TableNameAdapter;
import com.huxin.communication.adpter.ViewPagerAdapter;
import com.huxin.communication.base.BaseActivity;
import com.huxin.communication.controls.Constanst;
import com.huxin.communication.entity.AroundTravelEntity;
import com.huxin.communication.entity.ForeignTravelEntity;
import com.huxin.communication.entity.TicketInfoEntity;
import com.huxin.communication.http.ApiModule;
import com.huxin.communication.ui.TIMChatActivity;
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

public class TicketingDetailsActivity extends BaseActivity {

    private TextView mTextViewTravelTitle;
    private TextView mTextViewOriginalPrice;
    private TextView mTextViewReturnPrice;
    private TextView mTextViewReturnPriceChild;
    private TextView mTextViewOriginalPriceChild;
    private TextView mTextViewOriginalPriceEvening;
    private TextView mTextViewTicketAddr;
    private TextView mTextViewOpenTime;

    private TextView mTextViewViewCount;
    private TextView mTextViewIssueCount;
    private TextView mTextViewGeneralize;
    private TextView mTextViewUsername;
    private TextView mTextViewCompanyName;
    private TextView mTextViewPriceEveing;

    private RecyclerView mRecyclerView;

    private ViewPagerAdapter mViewPagerAdapter;

    private TableNameAdapter mAdapterTableName;

    private List<TicketInfoEntity.ListBean> mList;


    /**
     * 滚动焦点图片
     **/
    private ViewPager mViewPager;
    /**
     * 滚动焦点图片页码点
     **/
    private LinearLayout dot;

    private List<String> imageList = new ArrayList<>();


    private boolean mIsStop = false;// 焦点图触摸暂停切换

    private int big_index = 0;// 大焦点图自动切换初始位置

    private int position;

    private TicketInfoEntity.ListBean listBean;


    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_ticketing_details);
    }

    @Override
    protected void initViews() {
        mViewPager = (ViewPager) findViewById(R.id.detail_viewPager);
        dot = (LinearLayout) findViewById(R.id.main_activity_focus_dot);
        mTextViewTravelTitle = (TextView) findViewById(R.id.ticket_name);
        mTextViewReturnPrice = (TextView) findViewById(R.id.return_price);
        mTextViewOriginalPrice = (TextView) findViewById(R.id.original_price);
        mTextViewOriginalPriceChild = (TextView) findViewById(R.id.original_price_child);

        mTextViewReturnPriceChild = (TextView) findViewById(R.id.return_price_child);
        mTextViewOriginalPriceEvening = (TextView) findViewById(R.id.original_price_evening);
        mTextViewTicketAddr = (TextView) findViewById(R.id.ticket_addr);
        mTextViewOpenTime = (TextView) findViewById(R.id.open_time);


        mTextViewViewCount = (TextView) findViewById(R.id.view_count);
        mTextViewIssueCount = (TextView) findViewById(R.id.issue_count);
        mTextViewGeneralize = (TextView) findViewById(R.id.generalize);
        mTextViewUsername = (TextView) findViewById(R.id.username);
        mTextViewCompanyName = (TextView) findViewById(R.id.company_name);
        mTextViewPriceEveing = findViewById(R.id.return_price_evening);


        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_tableName);

        listBean = getIntent().getParcelableExtra("list");

        findViewById(R.id.zaixianwen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String userSig = PreferenceUtil.getString("usersig");
                if (listBean != null) {
                    //KyLog.i("uid = " + mList.get(0).getUid());
                    //KyLog.i("usersig = " + userSig);
                    String userId = PreferenceUtil.getInt(UID) + "";
                    String userSig = PreferenceUtil.getString("usersig");
                    if (!userId.equals(String.valueOf(listBean.getUid()))){
                        onRecvUserSig(userId, userSig, String.valueOf(listBean.getUid()));
                    }else {
                        Toast.makeText(TicketingDetailsActivity.this, "用户id相同，不能进行聊天", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        findViewById(R.id.call_phone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = listBean.getUserPhone();
                if (!TextUtils.isEmpty(phone)) {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
                    if (ActivityCompat.checkSelfPermission(TicketingDetailsActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions((Activity) TicketingDetailsActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                        return;
                    }
                    startActivity(intent);
                }else {
                    Toast.makeText(TicketingDetailsActivity.this, "电话号码为空", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        setData(listBean);
        setOnBinner(listBean);
        setTextView(listBean,mRecyclerView);
    }

//    private void gettingForeignTravel() {
//        showProgressDialog();
//        ApiModule.getInstance().getTicketInfo("1", PreferenceUtil.getString(Constanst.CITY_NATION_NAME), "", ""
//                , "", "", "", "", "1",null,"0",String.valueOf(PreferenceUtil.getInt(UID)))
//                .subscribe(ticketInfoEntity -> {
//                    cancelProgressDialog();
//                    mList = ticketInfoEntity.getList();
//
//                    KyLog.object(ticketInfoEntity);
//                    setData(ticketInfoEntity);
//                    setTextView(ticketInfoEntity.getList(), position, mRecyclerView);
//                    setOnBinner(ticketInfoEntity.getList());
//
//
//                }, throwable -> {
//                    KyLog.d(throwable.toString());
//                    cancelProgressDialog();
//                    Toast.makeText(this, throwable.getMessage().toString(), Toast.LENGTH_SHORT).show();
//                });
//    }

    private void setData(TicketInfoEntity.ListBean entity) {
        if (entity != null) {
            mTextViewTravelTitle.setText(entity.getTicket_name());
            mTextViewOriginalPrice.setText(String.valueOf("成人：" + entity.getOriginal_price())  + "元");
            mTextViewReturnPrice.setText(String.valueOf("结算价：" + entity.getFinal_price()) + "元");
            mTextViewReturnPriceChild.setText(String.valueOf("结算价：" + entity.getFinal_price_child()) + "元");
            mTextViewViewCount.setText(String.valueOf(entity.getView_count()) + "次");
            mTextViewIssueCount.setText(String.valueOf(entity.getIssue_count()) + "次");
            if (!TextUtils.isEmpty(entity.getGeneralize())) {
                mTextViewGeneralize.setText(entity.getGeneralize());
            }
            mTextViewUsername.setText(entity.getUsername());
            mTextViewCompanyName.setText(entity.getCompanyName());
            mTextViewOriginalPriceChild.setText(String.valueOf( "成人：" + entity.getOriginal_price_child()) + "元");
            mTextViewOriginalPriceEvening.setText(String.valueOf("夜场：" + entity.getOriginal_price_evening()) + "元");
            mTextViewOriginalPriceEvening.setText(String.valueOf("结算价：" + entity.getFinal_price_evening()) + "元");


            mTextViewTicketAddr.setText(entity.getTicket_addr());
            mTextViewOpenTime.setText(entity.getOpen_time());

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
                    if (big_index == imageList.size()) {
                        big_index = 0;
                    }
                    // 点击图片时，自动切换暂停
                    if (!mIsStop) {
                        int currentIndex = mViewPager.getCurrentItem();
                        if (++currentIndex == mViewPagerAdapter.getCount()) {
                            mViewPager.setCurrentItem(0);
                        } else {
//                            mViewPager.setCurrentItem(currentIndex, true);
                        }
                    }
                    sendScrollMessage(3000);
                    break;

            }
        }

    }

    /**
     * 加载binner图
     */
    private void setOnBinner(TicketInfoEntity.ListBean entity) {
        imageList = new ArrayList<>();
        if (!TextUtils.isEmpty(entity.getPhoto_url())) {
            String[] str = entity.getPhoto_url().split(",");
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

    private void setTextView(TicketInfoEntity.ListBean entity, RecyclerView linearLayout) {
        List<String> list1 = new ArrayList<>();
        if (!TextUtils.isEmpty(entity.getTagName())) {
            String[] strings = entity.getTagName().split(",");
            KyLog.d(entity.getTagName());
            for (int i = 0; i < strings.length; i++) {
                list1.add(strings[i]);
            }
        }

        if (list1.size() > 0) {
            GridLayoutManager manager = new GridLayoutManager(this, 5);
            mAdapterTableName = new TableNameAdapter(list1, this);
            linearLayout.setAdapter(mAdapterTableName);
            linearLayout.setLayoutManager(manager);
//            linearLayout.addItemDecoration(new SpaceItemDecoration(0, 15));
        }


    }


    private void onRecvUserSig(String userId, String userSig, String targetId) {
        TUIKit.login(userId, userSig, new IUIKitCallBack() {
            @Override
            public void onSuccess(Object data) {
                KyLog.i("imlogin onSuccess", data);
                Intent intent = new Intent(TicketingDetailsActivity.this, TIMChatActivity.class);
                intent.putExtra("TARGET_ID", targetId);
                startActivity(intent);
            }

            @Override
            public void onError(String module, int errCode, String errMsg) {
                Toast.makeText(TicketingDetailsActivity.this, "用户Id == " + userId + " \n" + "imlogin fail" + errMsg
                        + " \n" + "imlogin fail" + userSig, Toast.LENGTH_SHORT).show();
                KyLog.e("imlogin fail", errMsg);
            }
        });
    }
}
