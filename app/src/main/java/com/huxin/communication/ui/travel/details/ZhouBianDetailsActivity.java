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

import com.huxin.communication.R;
import com.huxin.communication.adpter.TableNameAdapter;
import com.huxin.communication.adpter.ViewPagerAdapter;
import com.huxin.communication.adpter.ZhouBianDuoXuanAdapter;
import com.huxin.communication.base.BaseActivity;
import com.huxin.communication.controls.Constanst;
import com.huxin.communication.entity.AroundTravelEntity;
import com.huxin.communication.entity.HeadTravelEntivty;
import com.huxin.communication.entity.HomeTravelEntity;
import com.huxin.communication.entity.InformationDetailEntity;
import com.huxin.communication.http.ApiModule;
import com.huxin.communication.ui.TIMChatActivity;
import com.huxin.communication.ui.house.details.ChuZuDetailsActivity;
import com.huxin.communication.ui.my.tuijian.TuiJianActivity;
import com.huxin.communication.ui.travel.CollectTravelActivity;
import com.huxin.communication.ui.travel.ZhouBianActivity;
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

public class ZhouBianDetailsActivity extends BaseActivity {
    private TextView mTextViewTravelTitle;
    private TextView mTextViewDepartName;
    private TextView mTextViewGoalsCity;
    private TextView mTextViewNumberDays;
    private TextView mTextViewTotalPrice;
    private TextView mTextViewReturnPrice;
    private TextView mTextViewTotalPriceChild;
    private TextView mTextViewReturnPriceChild;
    private TextView mTextViewSpotName;
    private TextView mTextViewViewCount;
    private TextView mTextViewIssueCount;
    private TextView mTextViewGeneralize;
    private TextView mTextViewUsername;
    private TextView mTextViewCompanyName;
    private String userName = "";
    private JSONObject jsonObject = new JSONObject();


    private ViewPagerAdapter mViewPagerAdapter;

    private RecyclerView mRecyclerView;

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

    private AroundTravelEntity.ListBean listBean;

    private HeadTravelEntivty headTravelEntivty;

    private int type;

    private ImageView mImageViewCollect;

    private ImageView mImageViewZhuanFa;

    private boolean isClick = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_zhou_bian_details);
    }

    @Override
    protected void initViews() {
        mViewPager = (ViewPager) findViewById(R.id.detail_viewPager);
        dot = (LinearLayout) findViewById(R.id.main_activity_focus_dot);
        mTextViewTravelTitle = (TextView) findViewById(R.id.travelTitle);
        mTextViewDepartName = (TextView) findViewById(R.id.depart_name);
        mTextViewGoalsCity = (TextView) findViewById(R.id.goals_city);
        mTextViewNumberDays = (TextView) findViewById(R.id.numberDays);
        mTextViewTotalPrice = (TextView) findViewById(R.id.totalPrice);
        mTextViewReturnPrice = (TextView) findViewById(R.id.returnPrice);
        mTextViewTotalPriceChild = (TextView) findViewById(R.id.totalPriceChild);
        mTextViewReturnPriceChild = (TextView) findViewById(R.id.returnPriceChild);
        mTextViewSpotName = (TextView) findViewById(R.id.spotName);
        mTextViewViewCount = (TextView) findViewById(R.id.view_count);
        mTextViewIssueCount = (TextView) findViewById(R.id.issue_count);
        mTextViewGeneralize = (TextView) findViewById(R.id.generalize);
        mTextViewUsername = (TextView) findViewById(R.id.username);
        mTextViewCompanyName = (TextView) findViewById(R.id.companyName);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_tableName);


        listBean = getIntent().getParcelableExtra("list");
        try {
            String json = getIntent().getStringExtra("detail");
            if (!TextUtils.isEmpty(json)) {
                jsonObject = new JSONObject(json);
                if (null == listBean && null != jsonObject) {
                    listBean = initAroundTravenBean(jsonObject);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        headTravelEntivty = getIntent().getParcelableExtra("headlist");

        type = getIntent().getIntExtra("type", 0);


        KyLog.object(listBean);

        findViewById(R.id.zaixianwen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String userSig = PreferenceUtil.getString("usersig");
                if (listBean != null) {
                    //KyLog.i("uid = " + mList.get(0).getUid());
                    //KyLog.i("usersig = " + userSig);
                    String userId = PreferenceUtil.getInt(UID) + "";
                    String userSig = PreferenceUtil.getString("usersig");
                    KyLog.d(userId);
                    KyLog.d(listBean.getId() + "");

                    if (type != 1) {
                        if (!userId.equals(String.valueOf(listBean.getUid()))) {
                            onRecvUserSig(userId, userSig, String.valueOf(listBean.getUid()));
                        } else {
                            Toast.makeText(ZhouBianDetailsActivity.this, "用户id相同，不能进行聊天", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        if (!userId.equals(String.valueOf(headTravelEntivty.getUid()))) {
                            onRecvUserSig(userId, userSig, String.valueOf(headTravelEntivty.getUid()));
                        } else {
                            Toast.makeText(ZhouBianDetailsActivity.this, "用户id相同，不能进行聊天", Toast.LENGTH_SHORT).show();
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
                    if (ActivityCompat.checkSelfPermission(ZhouBianDetailsActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions((Activity) ZhouBianDetailsActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                        return;
                    }
                    startActivity(intent);
                } else {
                    Toast.makeText(ZhouBianDetailsActivity.this, "电话号码为空", Toast.LENGTH_SHORT).show();
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
                        addTravelCollect(1, String.valueOf(listBean.getId()));
                    } else {
                        addTravelCollect(1, String.valueOf(headTravelEntivty.getId()));

                    }
                    isClick = false;
                    mImageViewCollect.setBackgroundResource(R.drawable.nav_icon_shoucang_click);
                } else {
                    if (type != 1) {
                        deleteTravelCollect(1, String.valueOf(listBean.getId()));
                    } else {
                        deleteTravelCollect(1, String.valueOf(headTravelEntivty.getId()));

                    }
                    isClick = true;
                    mImageViewCollect.setBackgroundResource(R.drawable.nav_icon_shoucang);
                }
            }
        });
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        if (type != 1) {
            if (listBean != null) {
                if (listBean.getIsCollect() == 0) {
                    mImageViewCollect.setBackgroundResource(R.drawable.nav_icon_shoucang);
                } else {
                    isClick = false;
                    mImageViewCollect.setBackgroundResource(R.drawable.nav_icon_shoucang_click);

                }
                updateViewCount(1, String.valueOf(listBean.getId()));
            }

        } else {
            if (headTravelEntivty != null) {
                if (headTravelEntivty.getIsCollect() == 0) {
                    mImageViewCollect.setBackgroundResource(R.drawable.nav_icon_shoucang);
                } else {
                    isClick = false;
                    mImageViewCollect.setBackgroundResource(R.drawable.nav_icon_shoucang_click);

                }
                updateViewCount(1, String.valueOf(headTravelEntivty.getId()));
            }

        }
        if (listBean != null) {
            setData(listBean, headTravelEntivty);
            setOnBinner(listBean, headTravelEntivty);
            setTextView(listBean, headTravelEntivty, mRecyclerView);
        }
    }


    private void setData(AroundTravelEntity.ListBean entity, HeadTravelEntivty entivty) {
        if (type != 1) {
            if (entity != null) {
                mTextViewTravelTitle.setText(entity.getTravelTitle());
                mTextViewDepartName.setText(entity.getDepart_name());
                mTextViewGoalsCity.setText(entity.getGoals_city());
                mTextViewNumberDays.setText(String.valueOf(entity.getNumberDays()) + "天");
                mTextViewTotalPrice.setText(String.valueOf("成人：" + entity.getTotalPrice()) + "元");
                mTextViewReturnPrice.setText(String.valueOf("返佣：" + entity.getReturnPrice()) + "元");
                mTextViewTotalPriceChild.setText(String.valueOf("儿童：" + entity.getTotalPriceChild()) + "元");
                mTextViewReturnPriceChild.setText(String.valueOf("返佣：" + entity.getReturnPriceChild()) + "元");
                mTextViewSpotName.setText(String.valueOf(entity.getSpotName()));
                mTextViewViewCount.setText(String.valueOf(entity.getView_count()) + "次");
                mTextViewIssueCount.setText(String.valueOf(entity.getIssue_count()) + "次");
                mTextViewGeneralize.setText(entity.getGeneralize());
                mTextViewUsername.setText(entity.getUsername());
                mTextViewCompanyName.setText(entity.getCompanyName());
                userName = entity.getUsername();

            }
        } else {
            if (entivty != null) {
                mTextViewTravelTitle.setText(entivty.getTravelTitle());
                mTextViewDepartName.setText(entivty.getDepart_name());
                mTextViewGoalsCity.setText(entivty.getGoals_city());
                mTextViewNumberDays.setText(String.valueOf(entivty.getNumberDays()) + "天");
                mTextViewTotalPrice.setText(String.valueOf("成人：" + entivty.getTotalPrice()) + "元");
                mTextViewReturnPrice.setText(String.valueOf("返佣：" + entivty.getReturnPrice()) + "元");
                mTextViewTotalPriceChild.setText(String.valueOf("儿童：" + entivty.getTotalPriceChild()) + "元");
                mTextViewReturnPriceChild.setText(String.valueOf("返佣：" + entivty.getReturnPriceChild()) + "元");
                mTextViewSpotName.setText(String.valueOf(entivty.getSpotName()));
                mTextViewViewCount.setText(String.valueOf(entivty.getView_count()) + "次");
                mTextViewIssueCount.setText(String.valueOf(entivty.getIssue_count()) + "次");
                mTextViewGeneralize.setText(entivty.getGeneralize());
                mTextViewUsername.setText(entivty.getUsername());
                mTextViewCompanyName.setText(entivty.getCompanyName());
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
    private void setOnBinner(AroundTravelEntity.ListBean listBean, HeadTravelEntivty entivty) {
        imageList = new ArrayList<>();
        if (type != 1) {
            if (!TextUtils.isEmpty(listBean.getPhoto_url())) {
                String[] str = listBean.getPhoto_url().split(",");
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


    private void setTextView(AroundTravelEntity.ListBean entity, HeadTravelEntivty entivty, RecyclerView linearLayout) {
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
                Intent intent = new Intent(ZhouBianDetailsActivity.this, TIMChatActivity.class);
                intent.putExtra("TARGET_ID", targetId);
                intent.putExtra("username", userName);
                startActivity(intent);
            }

            @Override
            public void onError(String module, int errCode, String errMsg) {
                Toast.makeText(ZhouBianDetailsActivity.this, "用户Id == " + userId + " \n" + "imlogin fail" + errMsg
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


    private void zhuanfa(AroundTravelEntity.ListBean listBean) {
        String data = getData(listBean, 1);
        KyLog.i("zhuanfa data = " + data);
        Bundle bundle = new Bundle();
        bundle.putString("data", data);
        bundle.putString("from", "zhuanfa");
        Intent intent = new Intent(ZhouBianDetailsActivity.this, TuiJianActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    public static String getData(AroundTravelEntity.ListBean SaleEntity, int TravelType) {
        String str = "";
        try {
            JSONObject jsonObject = new JSONObject();
            JSONObject data = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            JSONObject dataObj = new JSONObject();
            dataObj.put("depart_name", SaleEntity.getDepart_name());
            dataObj.put("goals_city", String.valueOf(SaleEntity.getGoals_city()));
            dataObj.put("headUrl", String.valueOf(SaleEntity.getHeadUrl()));
            dataObj.put("numberDays", String.valueOf(SaleEntity.getNumberDays()));
            dataObj.put("photo_url", SaleEntity.getPhoto_url());
            dataObj.put("finalPrice", String.valueOf(SaleEntity.getReturnPrice()));
            dataObj.put("finalPriceChild", SaleEntity.getReturnPriceChild());
            dataObj.put("totalPrice", String.valueOf(SaleEntity.getTotalPrice()));
            dataObj.put("totalPriceChild", SaleEntity.getTotalPriceChild());
            dataObj.put("spotName", String.valueOf(SaleEntity.getSpotName()));
            dataObj.put("tagName", SaleEntity.getTagName());
            dataObj.put("userCity", SaleEntity.getUserCity());
            dataObj.put("username", SaleEntity.getUsername());
            dataObj.put("TActivityId", SaleEntity.getTActivityId());
            dataObj.put("TAddressId", SaleEntity.getTAddressId());
            dataObj.put("TConsumeId", SaleEntity.getTConsumeId());
            dataObj.put("TOtherId", SaleEntity.getTOtherId());
            dataObj.put("TOverseasId", SaleEntity.getTOverseasId());
            dataObj.put("TStayId", SaleEntity.getTStayId());
            dataObj.put("TTrafficId", SaleEntity.getTTrafficId());
            dataObj.put("companyName", SaleEntity.getCompanyName());
            dataObj.put("id", SaleEntity.getId());
            jsonArray.put(dataObj);
            data.put("list", jsonArray);
            jsonObject.put("type", 2);
            jsonObject.put("travelType", 1);
            jsonObject.put("data", data);
            str = jsonObject.toString();
            KyLog.i("getData str = " + str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    private void updateViewCount(int travelType, String id) {
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


    private AroundTravelEntity.ListBean initAroundTravenBean(JSONObject dataObj) throws JSONException {
        AroundTravelEntity.ListBean bean = new AroundTravelEntity.ListBean();

        String departName = dataObj.getString("depart_name");
        String goalsCity = dataObj.getString("goals_city");
        String totalPrice = dataObj.getString("totalPrice");
        String returnPrice = dataObj.getString("finalPrice");
        String totalPriceChild = dataObj.getString("totalPriceChild");
        String returnPriceChild = dataObj.getString("finalPriceChild");
        String tagName = dataObj.getString("tagName");
        String numberDays = dataObj.getString("numberDays");
        String photoUrl = dataObj.getString("photo_url");
        String TActivityId = dataObj.getString("TActivityId");
        String TAddressId = dataObj.getString("TAddressId");
        String TConsumeId = dataObj.getString("TConsumeId");
        String TOtherId = dataObj.getString("TOtherId");
        String TOverseasId = dataObj.getString("TOverseasId");
        String TStayId = dataObj.getString("TStayId");
        String TTrafficId = dataObj.getString("TTrafficId");
        int id = dataObj.getInt("id");

        bean.setDepart_name(departName);
        bean.setGoals_city(goalsCity);
        bean.setTotalPrice(Double.parseDouble(totalPrice));
        bean.setReturnPrice(Double.parseDouble(returnPrice));
        bean.setTotalPriceChild(Double.parseDouble(totalPriceChild));
        bean.setReturnPriceChild(Double.parseDouble(returnPriceChild));
        bean.setTagName(tagName);
        bean.setNumberDays(Integer.parseInt(numberDays));
        bean.setPhoto_url(photoUrl);
        bean.setTActivityId(TActivityId);
        bean.setTAddressId(TAddressId);
        bean.setTConsumeId(TConsumeId);
        bean.setTOtherId(TOtherId);
        bean.setTOverseasId(TOverseasId);
        bean.setTStayId(TStayId);
        bean.setTTrafficId(TTrafficId);
        bean.setUid(id);
        return bean;
    }

}
