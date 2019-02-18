package com.huxin.communication.ui.travel.details;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.huxin.communication.R;
import com.huxin.communication.adpter.TableNameAdapter;
import com.huxin.communication.adpter.ViewPagerAdapter;
import com.huxin.communication.base.BaseActivity;
import com.huxin.communication.controls.Constanst;
import com.huxin.communication.entity.AroundTravelEntity;
import com.huxin.communication.entity.TicketInfoEntity;
import com.huxin.communication.http.ApiModule;
import com.huxin.communication.utils.PreferenceUtil;
import com.huxin.communication.view.SpaceItemDecoration;
import com.sky.kylog.KyLog;

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

    private RecyclerView mRecyclerView;

    private ViewPagerAdapter mViewPagerAdapter;

    private TableNameAdapter mAdapterTableName;

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


        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_tableName);

        position = getIntent().getIntExtra("position", 0);

    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        gettingForeignTravel();
    }

    private void gettingForeignTravel() {
        showProgressDialog();
        ApiModule.getInstance().getTicketInfo("1", PreferenceUtil.getString(Constanst.CITY_NATION_NAME), "", ""
                , "", "", "", "", "1",null)
                .subscribe(ticketInfoEntity -> {
                    cancelProgressDialog();
                    KyLog.object(ticketInfoEntity);
                    setData(ticketInfoEntity);
                    setTextView(ticketInfoEntity.getList(), position, mRecyclerView);
                    setOnBinner(ticketInfoEntity.getList());


                }, throwable -> {
                    KyLog.d(throwable.toString());
                    cancelProgressDialog();
                    Toast.makeText(this, throwable.getMessage().toString(), Toast.LENGTH_SHORT).show();
                });
    }

    private void setData(TicketInfoEntity entity) {
        if (entity.getList() != null && entity.getList().size() > 0) {
            mTextViewTravelTitle.setText(entity.getList().get(position).getTicket_name());
            mTextViewOriginalPrice.setText(entity.getList().get(position).getOriginal_price());
            mTextViewReturnPrice.setText(entity.getList().get(position).getReturn_price());
            mTextViewReturnPriceChild.setText(entity.getList().get(position).getReturn_price_child());
            mTextViewViewCount.setText(entity.getList().get(position).getView_count());
            mTextViewIssueCount.setText(entity.getList().get(position).getIssue_count());
            mTextViewGeneralize.setText(entity.getList().get(position).getGeneralize());
            mTextViewUsername.setText(entity.getList().get(position).getUsername());
            mTextViewCompanyName.setText(entity.getList().get(position).getCompanyName());
            mTextViewOriginalPriceChild.setText(entity.getList().get(position).getOriginal_price_child());
            mTextViewOriginalPriceEvening.setText(String.valueOf(entity.getList().get(position).getOriginal_price_evening()));

            mTextViewTicketAddr.setText(entity.getList().get(position).getTicket_addr());
            mTextViewOpenTime.setText(entity.getList().get(position).getOpen_time());

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
    private void setOnBinner(List<TicketInfoEntity.ListBean> list) {
        imageList = new ArrayList<>();
        if (!TextUtils.isEmpty(list.get(0).getPhoto_url())) {
            String[] str = list.get(0).getPhoto_url().split(",");
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

    private void setTextView(List<TicketInfoEntity.ListBean> list, int position, RecyclerView linearLayout) {
        List<String> list1 = new ArrayList<>();
        String[] strings = list.get(position).getTagName().split(",");
        KyLog.d(list.get(position).getTagName());
        for (int i = 0; i < strings.length; i++) {
            list1.add(strings[i]);
        }
        if (list1.size() > 0) {
            GridLayoutManager manager = new GridLayoutManager(this, 3);
            mAdapterTableName = new TableNameAdapter(list1, this);
            linearLayout.setAdapter(mAdapterTableName);
            linearLayout.setLayoutManager(manager);
            linearLayout.addItemDecoration(new SpaceItemDecoration(0, 15));
        }


    }
}
