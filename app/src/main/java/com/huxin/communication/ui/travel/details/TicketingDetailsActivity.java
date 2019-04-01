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
import com.huxin.communication.adpter.TicketingDuoXuanAdapter;
import com.huxin.communication.adpter.ViewPagerAdapter;
import com.huxin.communication.base.BaseActivity;
import com.huxin.communication.controls.Constanst;
import com.huxin.communication.entity.AroundTravelEntity;
import com.huxin.communication.entity.ForeignTravelEntity;
import com.huxin.communication.entity.HeadTravelEntivty;
import com.huxin.communication.entity.TicketInfoEntity;
import com.huxin.communication.http.ApiModule;
import com.huxin.communication.ui.TIMChatActivity;
import com.huxin.communication.ui.my.tuijian.TuiJianActivity;
import com.huxin.communication.ui.travel.TicketingActivity;
import com.huxin.communication.utils.PreferenceUtil;
import com.huxin.communication.view.SpaceItemDecoration;
import com.sky.kylog.KyLog;
import com.tencent.qcloud.uikit.TUIKit;
import com.tencent.qcloud.uikit.common.IUIKitCallBack;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    private String userName;
    private JSONObject jsonObject = new JSONObject();

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

    private HeadTravelEntivty headTravelEntivty;

    private int type;

    private ImageView mImageViewCollect;

    private ImageView mImageViewZhuanFa;

    private boolean isClick = true;


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
        try {
            String json = getIntent().getStringExtra("detail");
            if(!TextUtils.isEmpty(json)) {
                jsonObject = new JSONObject(json);
                if(null == listBean && null != jsonObject) {
                    listBean = initTicketInfoBean(jsonObject);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        headTravelEntivty = getIntent().getParcelableExtra("headlist");

        type = getIntent().getIntExtra("type", 0);


        findViewById(R.id.zaixianwen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String userSig = PreferenceUtil.getString("usersig");
                if (listBean != null) {
                    //KyLog.i("uid = " + mList.get(0).getUid());
                    //KyLog.i("usersig = " + userSig);
                    String userId = PreferenceUtil.getInt(UID) + "";
                    String userSig = PreferenceUtil.getString("usersig");
                    if (type != 1) {
                        if (!userId.equals(String.valueOf(listBean.getUid()))) {
                            onRecvUserSig(userId, userSig, String.valueOf(listBean.getUid()));
                        } else {
                            Toast.makeText(TicketingDetailsActivity.this, "用户id相同，不能进行聊天", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        if (!userId.equals(String.valueOf(headTravelEntivty.getUid()))) {
                            onRecvUserSig(userId, userSig, String.valueOf(headTravelEntivty.getUid()));
                        } else {
                            Toast.makeText(TicketingDetailsActivity.this, "用户id相同，不能进行聊天", Toast.LENGTH_SHORT).show();
                        }
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
                String phone = null;
                if (type != 1) {
                    phone = listBean.getUserPhone();
                } else {
                    phone = headTravelEntivty.getUserPhone();

                }
                if (!TextUtils.isEmpty(phone)) {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
                    if (ActivityCompat.checkSelfPermission(TicketingDetailsActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions((Activity) TicketingDetailsActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                        return;
                    }
                    startActivity(intent);
                } else {
                    Toast.makeText(TicketingDetailsActivity.this, "电话号码为空", Toast.LENGTH_SHORT).show();
                }
            }
        });


        mImageViewCollect = findViewById(R.id.image_collect);
        mImageViewZhuanFa = findViewById(R.id.image_zhaungfa);
        mImageViewZhuanFa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zhuanfa(listBean);
            }
        });

        mImageViewCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isClick) {
                    if (type != 1) {
                        addTravelCollect(3, String.valueOf(listBean.getId()));
                    } else {
                        addTravelCollect(3, String.valueOf(headTravelEntivty.getId()));

                    }
                    isClick = false;
                    mImageViewCollect.setBackgroundResource(R.drawable.nav_icon_shoucang_click);
                } else {
                    if (type != 1) {
                        deleteTravelCollect(3, String.valueOf(listBean.getId()));
                    } else {
                        deleteTravelCollect(3, String.valueOf(headTravelEntivty.getId()));

                    }
                    isClick = true;
                    mImageViewCollect.setBackgroundResource(R.drawable.nav_icon_shoucang);
                }
            }
        });

    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        if (listBean != null) {
            if (listBean.getIsCollect() == 0) {
                mImageViewCollect.setBackgroundResource(R.drawable.nav_icon_shoucang);
            } else {
                isClick = false;
                mImageViewCollect.setBackgroundResource(R.drawable.nav_icon_shoucang_click);

            }
            setData(listBean, headTravelEntivty);
            setOnBinner(listBean, headTravelEntivty);
            setTextView(listBean, mRecyclerView, headTravelEntivty);
            updateViewCount(3, String.valueOf(listBean.getId()));
        }
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

    private void setData(TicketInfoEntity.ListBean entity, HeadTravelEntivty entivty) {
        if (type != 1) {
            if (entity != null) {
                mTextViewTravelTitle.setText(entity.getTicket_name());
                mTextViewOriginalPrice.setText(String.valueOf("成人：" + entity.getOriginal_price()) + "元");
                mTextViewReturnPrice.setText(String.valueOf("结算价：" + entity.getFinal_price()) + "元");
                mTextViewReturnPriceChild.setText(String.valueOf("结算价：" + entity.getFinal_price_child()) + "元");
                mTextViewViewCount.setText(String.valueOf(entity.getView_count()) + "次");
                mTextViewIssueCount.setText(String.valueOf(entity.getIssue_count()) + "次");
                if (!TextUtils.isEmpty(entity.getGeneralize())) {
                    mTextViewGeneralize.setText(entity.getGeneralize());
                }
                mTextViewUsername.setText(entity.getUsername());
                mTextViewCompanyName.setText(entity.getCompanyName());
                mTextViewOriginalPriceChild.setText(String.valueOf("成人：" + entity.getOriginal_price_child()) + "元");
                mTextViewOriginalPriceEvening.setText(String.valueOf("夜场：" + entity.getOriginal_price_evening()) + "元");
                mTextViewOriginalPriceEvening.setText(String.valueOf("结算价：" + entity.getFinal_price_evening()) + "元");

                mTextViewTicketAddr.setText(entity.getTicket_addr());
                mTextViewOpenTime.setText(entity.getOpen_time());
                userName = entity.getUsername();

            }
        } else {
            if (entivty != null) {
                mTextViewTravelTitle.setText(entivty.getTicket_name());
                mTextViewOriginalPrice.setText(String.valueOf("成人：" + entivty.getOriginal_price()) + "元");
                mTextViewReturnPrice.setText(String.valueOf("结算价：" + entivty.getFinal_price()) + "元");
                mTextViewReturnPriceChild.setText(String.valueOf("结算价：" + entivty.getFinal_price_child()) + "元");
                mTextViewViewCount.setText(String.valueOf(entivty.getView_count()) + "次");
                mTextViewIssueCount.setText(String.valueOf(entivty.getIssue_count()) + "次");
                if (!TextUtils.isEmpty(entivty.getGeneralize())) {
                    mTextViewGeneralize.setText(entivty.getGeneralize());
                }
                mTextViewUsername.setText(entivty.getUsername());
                mTextViewCompanyName.setText(entivty.getCompanyName());
                mTextViewOriginalPriceChild.setText(String.valueOf("成人：" + entivty.getOriginal_price_child()) + "元");
                mTextViewOriginalPriceEvening.setText(String.valueOf("夜场：" + entivty.getOriginal_price_evening()) + "元");
                mTextViewOriginalPriceEvening.setText(String.valueOf("结算价：" + entivty.getFinal_price_evening()) + "元");

                mTextViewTicketAddr.setText(entivty.getTicket_addr());
                mTextViewOpenTime.setText(entivty.getOpen_time());
                userName = entivty.getUsername();

            }
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
    private void setOnBinner(TicketInfoEntity.ListBean entity, HeadTravelEntivty entivty) {
        imageList = new ArrayList<>();
        if (type != 1) {
            if (!TextUtils.isEmpty(entity.getPhoto_url())) {
                String[] str = entity.getPhoto_url().split(",");
                for (String strs : str) {
                    imageList.add(strs);
                }
            }
        } else {
            if (!TextUtils.isEmpty(entivty.getPhoto_url())) {
                String[] str = entivty.getPhoto_url().split(",");
                for (String strs : str) {
                    imageList.add(strs);
                }
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

    private void setTextView(TicketInfoEntity.ListBean entity, RecyclerView linearLayout, HeadTravelEntivty entivty) {
        List<String> list1 = new ArrayList<>();
        if (type != 1) {
            if (!TextUtils.isEmpty(entity.getTagName())) {
                String[] strings = entity.getTagName().split(",");
                KyLog.d(entity.getTagName());
                for (int i = 0; i < strings.length; i++) {
                    list1.add(strings[i]);
                }
            }
        } else {
            if (!TextUtils.isEmpty(entivty.getTagName())) {
                String[] strings = entivty.getTagName().split(",");
                KyLog.d(entivty.getTagName());
                for (int i = 0; i < strings.length; i++) {
                    list1.add(strings[i]);
                }
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
                intent.putExtra("username", userName);
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

    private void addTravelCollect(int travelType, String id) {
        showProgressDialog();
        ApiModule.getInstance().addTravelCollect(id, travelType)
                .subscribe(response -> {
                    KyLog.object(response + "");
                    cancelProgressDialog();
                    Toast.makeText(this, "收藏成功", Toast.LENGTH_SHORT).show();
                }, throwable -> {
                    KyLog.d(throwable.toString());
                    cancelProgressDialog();
                    Toast.makeText(this, throwable.getMessage().toString(), Toast.LENGTH_SHORT).show();
                });
    }


    private void deleteTravelCollect(int travelType, String id) {
        showProgressDialog();
        ApiModule.getInstance().deleteCollectTravel(id, String.valueOf(travelType))
                .subscribe(response -> {
                    KyLog.object(response + "");
                    cancelProgressDialog();
                    Toast.makeText(this, "取消收藏成功", Toast.LENGTH_SHORT).show();
                }, throwable -> {
                    KyLog.d(throwable.toString());
                    cancelProgressDialog();
                    Toast.makeText(this, throwable.getMessage().toString(), Toast.LENGTH_SHORT).show();
                });
    }

    private void zhuanfa(TicketInfoEntity.ListBean listBean) {
        String data = getData(listBean, 1);
        KyLog.i("zhuanfa data = " + data);
        Bundle bundle = new Bundle();
        bundle.putString("data", data);
        bundle.putString("from", "zhuanfa");
        Intent intent = new Intent(TicketingDetailsActivity.this, TuiJianActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    public static String getData(TicketInfoEntity.ListBean SaleEntity, int TravelType) {

        String str = "";
        try {
            JSONObject jsonObject = new JSONObject();
            JSONObject data = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            JSONObject dataObj = new JSONObject();
            dataObj.put("ticket_name", SaleEntity.getTicket_name());
            dataObj.put("ticket_addr", SaleEntity.getTicket_addr());
            dataObj.put("original_price", SaleEntity.getOriginal_price());
            dataObj.put("tagName", SaleEntity.getTagName());
            dataObj.put("photo_url", SaleEntity.getPhoto_url());
            dataObj.put("headUrl", SaleEntity.getHeadUrl());
            dataObj.put("final_price", SaleEntity.getFinal_price());
            dataObj.put("final_price_child", SaleEntity.getFinal_price_child());
            dataObj.put("final_price_evening", SaleEntity.getFinal_price_evening());
            dataObj.put("final_price_family", SaleEntity.getFinal_price_family());
            dataObj.put("final_price_parent_child", SaleEntity.getFinal_price_parent_child());
            dataObj.put("final_price_total", SaleEntity.getFinal_price_total());
            dataObj.put("id", SaleEntity.getId());
            dataObj.put("open_time", SaleEntity.getOpen_time());
            dataObj.put("companyName", SaleEntity.getCompanyName());
            dataObj.put("userCity", SaleEntity.getUserCity());
            dataObj.put("userPhone", SaleEntity.getUserPhone());
            dataObj.put("uid", SaleEntity.getUid());
            dataObj.put("issue_count", SaleEntity.getIssue_count());
            dataObj.put("generalize", SaleEntity.getGeneralize());
            dataObj.put("username", SaleEntity.getUsername());
            jsonArray.put(dataObj);
            data.put("list", jsonArray);
            jsonObject.put("type", 2);
            jsonObject.put("travelType", 4);
            jsonObject.put("data", data);
            str = jsonObject.toString();
            KyLog.i("getData str = " + str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    private void updateViewCount(int travelType,String id) {
        showProgressDialog();
        ApiModule.getInstance().updateViewCount(id, String.valueOf(travelType))
                .subscribe(response -> {
                    KyLog.object(response + "");
                    cancelProgressDialog();
                }, throwable -> {
                    KyLog.d(throwable.toString());
                    cancelProgressDialog();
                    Toast.makeText(this, throwable.getMessage().toString(), Toast.LENGTH_SHORT).show();
                });
    }

    private TicketInfoEntity.ListBean initTicketInfoBean(JSONObject dataObj) throws JSONException {
        TicketInfoEntity.ListBean bean = new TicketInfoEntity.ListBean();

        String ticketName = dataObj.getString("ticket_name");
        String ticketAddr = dataObj.getString("ticket_addr");
        String originalPrice = dataObj.getString("original_price");
        String tagName = dataObj.getString("tagName");
        String photoUrl = dataObj.getString("photo_url");
        String headUrl = dataObj.getString("headUrl");
        String final_price = dataObj.getString("final_price");
        String final_price_child = dataObj.getString("final_price_child");
        String final_price_evening = dataObj.getString("final_price_evening");
        String final_price_family = dataObj.getString("final_price_family");
        String final_price_parent_child = dataObj.getString("final_price_parent_child");
        String final_price_total = dataObj.getString("final_price_total");
        String open_time = dataObj.getString("open_time");
        String companyName = dataObj.getString("companyName");
        String userCity = dataObj.getString("userCity");
        String userPhone = dataObj.getString("userPhone");
        String issue_count = dataObj.getString("issue_count");
        String generalize = dataObj.getString("generalize");
        String username = dataObj.getString("username");
        int uid = dataObj.getInt("uid");

        bean.setTicket_name(ticketName);
        bean.setTicket_addr(ticketAddr);
        bean.setOriginal_price(Double.parseDouble(originalPrice));
        bean.setTagName(tagName);
        bean.setPhoto_url(photoUrl);
        bean.setHeadUrl(headUrl);
        bean.setFinal_price(Double.parseDouble(final_price));
        bean.setFinal_price_child(Double.parseDouble(final_price_child));
        bean.setFinal_price_evening(Double.parseDouble(final_price_evening));
        bean.setFinal_price_family(Double.parseDouble(final_price_family));
        bean.setFinal_price_parent_child(Double.parseDouble(final_price_parent_child));
        bean.setFinal_price_total(Double.parseDouble(final_price_total));
        bean.setOpen_time(open_time);
        bean.setCompanyName(companyName);
        bean.setUserCity(userCity);
        bean.setUserPhone(userPhone);
        bean.setIssue_count(Integer.parseInt(issue_count));
        bean.setGeneralize(generalize);
        bean.setUsername(username);
        bean.setUid(uid);
        return bean;
    }
}
