package com.huxin.communication.ui.travel.release;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.huxin.communication.R;
import com.huxin.communication.adpter.ImagePickerTravelAdapter;
import com.huxin.communication.adpter.TableTravelActivityAdapter;
import com.huxin.communication.adpter.TableTravelAddressListAdapter;
import com.huxin.communication.adpter.TableTravelConsAdapter;
import com.huxin.communication.adpter.TableTravelOtherAdapter;
import com.huxin.communication.adpter.TableTravelStayAdapter;
import com.huxin.communication.adpter.TableTravelTrafficAdapter;
import com.huxin.communication.base.BaseActivity;
import com.huxin.communication.controls.Constanst;
import com.huxin.communication.custom.ReleaseDialog;
import com.huxin.communication.entity.AroundTravelEntity;
import com.huxin.communication.entity.ForeignTravelEntity;
import com.huxin.communication.entity.MyPopVlaues;
import com.huxin.communication.entity.TabTravelNameEntity;
import com.huxin.communication.http.ApiModule;
import com.huxin.communication.ui.ForeignNationActivity;
import com.huxin.communication.ui.ForeignSpotActivity;
import com.huxin.communication.ui.MainActivity;
import com.huxin.communication.ui.ProvincesTravelActivity;
import com.huxin.communication.ui.cammer.HttpUtil;
import com.huxin.communication.ui.cammer.ImagePickerAdapter;
import com.huxin.communication.ui.cammer.MyStringCallBack;
import com.huxin.communication.ui.cammer.SelectDialog;
import com.huxin.communication.ui.house.release.ReleaseActivity;
import com.huxin.communication.utils.PreferenceUtil;
import com.huxin.communication.view.SpaceItemDecoration;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.sky.kylog.KyLog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import retrofit2.http.Field;

public class OverseasReleaseActivity extends BaseActivity implements View.OnClickListener, ImagePickerAdapter.OnRecyclerViewItemClickListener {

    private RelativeLayout mRelativeLayoutOccupationType;
    private RelativeLayout mRelativeLayoutMudiType;
    private RelativeLayout mRelativeLayoutHotType;
    private RelativeLayout mRelativeLayoutDayType;
    private RelativeLayout mRelativeLayoutRelease1Type;

    private EditText mTextViewTotalPrice;
    private EditText mTextViewFinalPrice;
    private EditText mTextViewReturnPrice;
    private EditText mTextViewTotalPriceChild;
    private EditText mTextViewFinalPriceChild;
    private EditText mTextViewReturnPriceChild;
    private EditText mEditTextTravelTitle;
    private EditText mEditTextGeneralize;

    private TextView mTextViewOccupationType;
    private TextView mTextViewMudiType;
    private TextView mTextViewHotType;
    private TextView mTextViewDayType;
    private TextView mTextViewRelease1Type;

    private RecyclerView mRecyclerViewAddress;
    private RecyclerView mRecyclerViewTraffic;
    private RecyclerView mRecyclerViewCons;
    private RecyclerView mRecyclerViewActivity;
    private RecyclerView mRecyclerViewStay;
    private RecyclerView mRecyclerViewOther;

    private ImageView mImageViewShuaiWei;
    private ImageView mImageViewShuaiWeiClick;

    private ImageView mImageViewCaiXian;
    private ImageView getmImageViewCaiXianClick;

    private ImageView mImageViewStickNew;
//    private ImageView mImageViewStickNewClick;

    private ImageView mImageViewStickLow;
//    private ImageView mImageViewStickLowClick;

    private ImageView mImageViewStickBetter;
//    private ImageView mImageViewStickBetterClick;

    private ImageView mImageViewStickThrow;
//    private ImageView mImageViewStickThrowClick;

    private ImageView mImageViewStickRate;
//    private ImageView mImageViewStickRateClick;

    private ImageView mImageViewStickReturn;
//    private ImageView mImageViewStickReturnClick;

    private ImageView mImageViewStickHot;
//    private ImageView mImageViewStickHotClick;

    private ImageView mImageViewStickZeroC;

    private ImageView mRelativeLayoutStickZeroC;
    private ImageView mRelativeLayoutStickReturn;
    private ImageView mRelativeLayoutStickHot;
    private ImageView mRelativeLayoutStickThrow;
    private ImageView mRelativeLayoutStickBetter;
    private ImageView mRelativeLayoutStickLow;
    private ImageView mRelativeLayoutStickNew;
    private ImageView mRelativeLayoutStickRate;


    private TextView mTextViewConfirm;
    private int news = 0;
    private int low = 0;
    private int better = 0;
    private int shuaiwei = 0;
    private int rate = 0;
    private int returns = 0;
    private int hot = 0;
    private int zeroC = 0;
    private int stick = 0;
    private ReleaseDialog mReleaseDialog;
    private List<MyPopVlaues> Kouweilist;

    private String pickupPrice;

    private String day;

    private int pickupPrices;

    private int caixian = 0;

    public static final int IMAGE_ITEM_ADD = -1;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;

    private ArrayList<ImageItem> selImageList; //当前选择的所有图片
    private int maxImgCount = 9;               //允许选择图片最大数

    private RecyclerView mRecyclerViewAddPicture;
    private ImagePickerTravelAdapter adapter;

    private HttpUtil httpUtil;

    private String Traffic;
    private String Address;
    private String Consume;
    private String Activity;
    private String Stay;
    private String Other;

    private String type = null;

    private TextView mTextViewTopMessage;

    private int id = 0;
    private ForeignTravelEntity.ListBean listBean;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_overseas_release);
        httpUtil = new HttpUtil();

        type = getIntent().getStringExtra("type");
        id = getIntent().getIntExtra("id", 0);
        listBean = getIntent().getParcelableExtra("list");

    }

    @Override
    protected void initViews() {
        if (id == 0) {
            setToolbarCenterMode("发布境外游线路", MODE_BACK);
        } else {
            setToolbarCenterMode("编辑境外游线路", MODE_BACK);

        }

        mRelativeLayoutOccupationType = (RelativeLayout) findViewById(R.id.rl_travel_Occupation_type);
        mRelativeLayoutMudiType = (RelativeLayout) findViewById(R.id.rl_travel_mudi_type);
        mRelativeLayoutHotType = (RelativeLayout) findViewById(R.id.rl_travel_hot_type);
        mRelativeLayoutDayType = (RelativeLayout) findViewById(R.id.rl_travel_day_type);
        mRelativeLayoutRelease1Type = (RelativeLayout) findViewById(R.id.rl_release1_type);

        mTextViewTotalPrice = (EditText) findViewById(R.id.totalPrice);
        mTextViewFinalPrice = (EditText) findViewById(R.id.finalPrice);
        mTextViewReturnPrice = (EditText) findViewById(R.id.returnPrice);
        mTextViewTotalPriceChild = (EditText) findViewById(R.id.totalPriceChild);
        mTextViewFinalPriceChild = (EditText) findViewById(R.id.finalPriceChild);
        mTextViewReturnPriceChild = (EditText) findViewById(R.id.returnPriceChild);
        mTextViewMudiType = (TextView) findViewById(R.id.travel_mudi_type);
        mTextViewHotType = (TextView) findViewById(R.id.travel_hot_type);
        mTextViewDayType = (TextView) findViewById(R.id.travel_day_type);
        mTextViewRelease1Type = (TextView) findViewById(R.id.release1_type);
        mTextViewOccupationType = (TextView) findViewById(R.id.travel_Occupation_type);
        mTextViewConfirm = (TextView) findViewById(R.id.confirm);


        mEditTextTravelTitle = (EditText) findViewById(R.id.travelTitle);
        mEditTextGeneralize = (EditText) findViewById(R.id.generalize);

        mRecyclerViewAddress = (RecyclerView) findViewById(R.id.address_recycler);
        mRecyclerViewTraffic = (RecyclerView) findViewById(R.id.traffic_recycler);
        mRecyclerViewCons = (RecyclerView) findViewById(R.id.cons_recycler);
        mRecyclerViewActivity = (RecyclerView) findViewById(R.id.activity_recycler);
        mRecyclerViewStay = (RecyclerView) findViewById(R.id.stay_recycler);
        mRecyclerViewOther = (RecyclerView) findViewById(R.id.other_recycler);

        mImageViewShuaiWei = (ImageView) findViewById(R.id.lineOrThrow_shuaiWei);
        mImageViewShuaiWeiClick = (ImageView) findViewById(R.id.lineOrThrow_shuaiWei_click);

        mImageViewCaiXian = (ImageView) findViewById(R.id.lineOrThrow_caixian);
        getmImageViewCaiXianClick = (ImageView) findViewById(R.id.lineOrThrow_caixian_click);

        mImageViewStickNew = (ImageView) findViewById(R.id.stick_new);
//        mImageViewStickNewClick = (ImageView) findViewById(R.id.stick_new_click);

        mImageViewStickLow = (ImageView) findViewById(R.id.stick_low);
//        mImageViewStickLowClick = (ImageView) findViewById(R.id.stick_low_click);

        mImageViewStickBetter = (ImageView) findViewById(R.id.stick_better);
//        mImageViewStickBetterClick = (ImageView) findViewById(R.id.stick_better_click);

        mImageViewStickThrow = (ImageView) findViewById(R.id.stick_throw);
//        mImageViewStickThrowClick = (ImageView) findViewById(R.id.stick_throw_click);

        mImageViewStickRate = (ImageView) findViewById(R.id.stick_rate);
//        mImageViewStickRateClick = (ImageView) findViewById(R.id.stick_rate_click);

        mImageViewStickReturn = (ImageView) findViewById(R.id.stick_return);
//        mImageViewStickReturnClick = (ImageView) findViewById(R.id.stick_return_click);


        mImageViewStickHot = (ImageView) findViewById(R.id.stick_hot);
//        mImageViewStickHotClick = (ImageView) findViewById(R.id.stick_hot_click);

        mImageViewStickZeroC = (ImageView) findViewById(R.id.stick_zeroC);
//        mImageViewStickZeroClick = (ImageView) findViewById(R.id.stick_zeroC_click);

        mTextViewConfirm = (TextView) findViewById(R.id.confirm);

        mTextViewTopMessage = findViewById(R.id.top_message);

        mRecyclerViewAddPicture = (RecyclerView) findViewById(R.id.recyclerView);


        mRelativeLayoutStickNew = findViewById(R.id.rl_stick_new);
        mRelativeLayoutStickBetter = findViewById(R.id.rl_stick_better);
        mRelativeLayoutStickLow = findViewById(R.id.rl_stick_low);
        mRelativeLayoutStickRate = findViewById(R.id.rl_stick_rate);
        mRelativeLayoutStickReturn = findViewById(R.id.rl_stick_return);
        mRelativeLayoutStickHot = findViewById(R.id.rl_stick_hot);
        mRelativeLayoutStickThrow = findViewById(R.id.rl_stick_throw);
        mRelativeLayoutStickZeroC = findViewById(R.id.rl_stick_zeroC);


        mImageViewShuaiWei.setOnClickListener(this);
        mImageViewShuaiWeiClick.setOnClickListener(this);
        mImageViewCaiXian.setOnClickListener(this);
        getmImageViewCaiXianClick.setOnClickListener(this);
        mImageViewStickNew.setOnClickListener(this);
//        mImageViewStickNewClick.setOnClickListener(this);
        mImageViewStickLow.setOnClickListener(this);
//        mImageViewStickLowClick.setOnClickListener(this);
        mImageViewStickBetter.setOnClickListener(this);
//        mImageViewStickBetterClick.setOnClickListener(this);
        mImageViewStickThrow.setOnClickListener(this);
//        mImageViewStickThrowClick.setOnClickListener(this);
        mImageViewStickRate.setOnClickListener(this);
//        mImageViewStickRateClick.setOnClickListener(this);
        mImageViewStickReturn.setOnClickListener(this);
//        mImageViewStickReturnClick.setOnClickListener(this);
        mImageViewStickHot.setOnClickListener(this);
//        mImageViewStickHotClick.setOnClickListener(this);
        mImageViewStickZeroC.setOnClickListener(this);
//        mImageViewStickZeroClick.setOnClickListener(this);
        mTextViewConfirm.setOnClickListener(this);
        mRelativeLayoutOccupationType.setOnClickListener(this);
        mRelativeLayoutMudiType.setOnClickListener(this);
        mRelativeLayoutHotType.setOnClickListener(this);
        mRelativeLayoutDayType.setOnClickListener(this);
        mRelativeLayoutRelease1Type.setOnClickListener(this);

        mRelativeLayoutStickZeroC.setOnClickListener(this);
        mRelativeLayoutStickBetter.setOnClickListener(this);
        mRelativeLayoutStickLow.setOnClickListener(this);
        mRelativeLayoutStickReturn.setOnClickListener(this);
        mRelativeLayoutStickRate.setOnClickListener(this);
        mRelativeLayoutStickHot.setOnClickListener(this);
        mRelativeLayoutStickThrow.setOnClickListener(this);
        mRelativeLayoutStickNew.setOnClickListener(this);
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        selectTravelTab();
        deteledData();
        httpUtil = new HttpUtil();
        selImageList = new ArrayList<>();
        adapter = new ImagePickerTravelAdapter(this, selImageList, maxImgCount);
        adapter.setOnItemClickListener(this);
        mRecyclerViewAddPicture.setLayoutManager(new GridLayoutManager(this, 4));
        mRecyclerViewAddPicture.setHasFixedSize(true);
        mRecyclerViewAddPicture.setAdapter(adapter);

        mTextViewTopMessage.setText("置顶信息剩余" + String.valueOf(PreferenceUtil.getInt(Constanst.TOP_ZHIDING)) + "条");
        SetEnabled();
        if (id != 0) {
            setData();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setViewData();
    }


    private void setData() {
        if (listBean != null) {
            mTextViewTotalPrice.setText(String.valueOf(listBean.getTotal_price()));
            mTextViewFinalPrice.setText(String.valueOf(listBean.getFinal_price()));
            mTextViewReturnPrice.setText(String.valueOf(listBean.getReturn_price()));
            mTextViewTotalPriceChild.setText(String.valueOf(listBean.getTotal_price_child()));
            mTextViewFinalPriceChild.setText(String.valueOf(listBean.getFinal_price_child()));
            mTextViewReturnPriceChild.setText(String.valueOf(listBean.getReturn_price_child()));
            mTextViewDayType.setText(String.valueOf(listBean.getNumber_days()));
            day = String.valueOf(listBean.getNumber_days());


            if (!TextUtils.isEmpty(listBean.getTravel_title())) {
                mEditTextTravelTitle.setText(String.valueOf(listBean.getTravel_title()));

            }

            if (!TextUtils.isEmpty(listBean.getGeneralize())) {
                mEditTextGeneralize.setText(String.valueOf(listBean.getGeneralize()));

            }

            if (!TextUtils.isEmpty(listBean.getGoals_nat_name()) &&!TextUtils.isEmpty(listBean.getGoals_name()) ) {
                mTextViewMudiType.setText(listBean.getGoals_nat_name() + listBean.getGoals_name());
                PreferenceUtil.putString(Constanst.NATION_NAME,listBean.getGoals_nat_name());
                PreferenceUtil.putString(Constanst.CITY_NATION_NAME,listBean.getGoals_name());
            }

            if (!TextUtils.isEmpty(listBean.getDepart_name()) && !TextUtils.isEmpty(listBean.getDepart_pro_name())) {
                mTextViewOccupationType.setText(listBean.getDepart_name() + listBean.getDepart_pro_name());
                PreferenceUtil.putString(Constanst.CITY_NAME,listBean.getDepart_name());
                PreferenceUtil.putString(Constanst.PROVINCE_NAME,listBean.getDepart_pro_name());

            }

            if (!TextUtils.isEmpty(listBean.getSpot_name())) {
                mTextViewHotType.setText(listBean.getSpot_name());
                PreferenceUtil.putString(Constanst.SPOT_NATION_NAME,listBean.getSpot_name());

            }

            if (listBean.getLine_or_throw() == 0) {
                mImageViewShuaiWei.setBackgroundResource(R.drawable.icon_circle_selected);
                mImageViewCaiXian.setBackgroundResource(R.drawable.icon_circle_normal);
                caixian = 1;
            } else {
                mImageViewShuaiWei.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewCaiXian.setBackgroundResource(R.drawable.icon_circle_selected);
                caixian = 2;
            }

            if (listBean.getStick_better() == 1) {
                mImageViewStickNew.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickLow.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickHot.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickThrow.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickRate.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickReturn.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickZeroC.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickBetter.setBackgroundResource(R.drawable.icon_circle_selected);
                better = 1;
                news = 0;
                low = 0;
                hot = 0;
                shuaiwei = 0;
                rate = 0;
                returns = 0;
                zeroC = 0;
            }

            if (listBean.getStick_hot() == 1) {
                mImageViewStickNew.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickLow.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickHot.setBackgroundResource(R.drawable.icon_circle_selected);
                mImageViewStickThrow.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickRate.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickReturn.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickZeroC.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickBetter.setBackgroundResource(R.drawable.icon_circle_normal);
                better = 0;
                news = 0;
                low = 0;
                hot = 1;
                shuaiwei = 0;
                rate = 0;
                returns = 0;
                zeroC = 0;
            }

            if (listBean.getStick_low() == 1) {
                mImageViewStickNew.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickLow.setBackgroundResource(R.drawable.icon_circle_selected);
                mImageViewStickHot.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickThrow.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickRate.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickReturn.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickZeroC.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickBetter.setBackgroundResource(R.drawable.icon_circle_normal);
                better = 0;
                news = 0;
                low = 1;
                hot = 0;
                shuaiwei = 0;
                rate = 0;
                returns = 0;
                zeroC = 0;
            }
            if (listBean.getStick_new() == 1) {
                mImageViewStickNew.setBackgroundResource(R.drawable.icon_circle_selected);
                mImageViewStickLow.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickHot.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickThrow.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickRate.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickReturn.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickZeroC.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickBetter.setBackgroundResource(R.drawable.icon_circle_normal);
                better = 0;
                news = 1;
                low = 0;
                hot = 0;
                shuaiwei = 0;
                rate = 0;
                returns = 0;
                zeroC = 0;
            }

            if (listBean.getStick_rate() == 1) {
                mImageViewStickNew.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickLow.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickHot.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickThrow.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickRate.setBackgroundResource(R.drawable.icon_circle_selected);
                mImageViewStickReturn.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickZeroC.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickBetter.setBackgroundResource(R.drawable.icon_circle_normal);
                better = 0;
                news = 0;
                low = 0;
                hot = 0;
                shuaiwei = 0;
                rate = 1;
                returns = 0;
                zeroC = 0;
            }

            if (listBean.getStick_throw() == 1) {
                mImageViewStickNew.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickLow.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickHot.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickThrow.setBackgroundResource(R.drawable.icon_circle_selected);
                mImageViewStickRate.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickReturn.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickZeroC.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickBetter.setBackgroundResource(R.drawable.icon_circle_normal);
                better = 0;
                news = 0;
                low = 0;
                hot = 0;
                shuaiwei = 1;
                rate = 0;
                returns = 0;
                zeroC = 0;
            }

            if (listBean.getStick_return() == 1) {
                mImageViewStickNew.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickLow.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickHot.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickThrow.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickRate.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickReturn.setBackgroundResource(R.drawable.icon_circle_selected);
                mImageViewStickZeroC.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickBetter.setBackgroundResource(R.drawable.icon_circle_normal);
                better = 0;
                news = 0;
                low = 0;
                hot = 0;
                shuaiwei = 0;
                rate = 0;
                returns = 1;
                zeroC = 0;
            }

            if (listBean.getStick_zeroC() == 1) {
                mImageViewStickNew.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickLow.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickHot.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickThrow.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickRate.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickReturn.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickZeroC.setBackgroundResource(R.drawable.icon_circle_selected);
                mImageViewStickBetter.setBackgroundResource(R.drawable.icon_circle_normal);
                better = 0;
                news = 0;
                low = 0;
                hot = 0;
                shuaiwei = 0;
                rate = 0;
                returns = 0;
                zeroC = 1;
            }

            if (listBean.getPickup_price() == 0) {
                mTextViewRelease1Type.setText("有周边接送费");
                pickupPrice = "有周边接送费";
            } else {
                mTextViewRelease1Type.setText("无接送费");
                pickupPrice = "无接送费";

            }
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lineOrThrow_shuaiWei:
                mImageViewShuaiWeiClick.setVisibility(View.VISIBLE);
                mImageViewShuaiWei.setVisibility(View.GONE);
                getmImageViewCaiXianClick.setVisibility(View.GONE);
                mImageViewCaiXian.setVisibility(View.VISIBLE);
                caixian = 2;
                break;
            case R.id.lineOrThrow_caixian:
                getmImageViewCaiXianClick.setVisibility(View.VISIBLE);
                mImageViewCaiXian.setVisibility(View.GONE);
                mImageViewShuaiWeiClick.setVisibility(View.GONE);
                mImageViewShuaiWei.setVisibility(View.VISIBLE);
                caixian = 1;
                break;
            case R.id.rl_stick_better:
                mImageViewStickNew.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickLow.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickHot.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickThrow.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickRate.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickReturn.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickZeroC.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickBetter.setBackgroundResource(R.drawable.icon_circle_selected);
                better = 1;

                news = 0;
                low = 0;
                hot = 0;
                shuaiwei = 0;
                rate = 0;
                returns = 0;
                zeroC = 0;
                break;
            case R.id.rl_stick_hot:
                mImageViewStickNew.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickLow.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickHot.setBackgroundResource(R.drawable.icon_circle_selected);
                mImageViewStickThrow.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickRate.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickReturn.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickZeroC.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickBetter.setBackgroundResource(R.drawable.icon_circle_normal);
                better = 0;
                news = 0;
                low = 0;
                hot = 1;
                shuaiwei = 0;
                rate = 0;
                returns = 0;
                zeroC = 0;
                break;


            case R.id.rl_stick_new:
                mImageViewStickNew.setBackgroundResource(R.drawable.icon_circle_selected);
                mImageViewStickLow.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickHot.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickThrow.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickRate.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickReturn.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickZeroC.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickBetter.setBackgroundResource(R.drawable.icon_circle_normal);
                better = 0;
                news = 1;
                low = 0;
                hot = 0;
                shuaiwei = 0;
                rate = 0;
                returns = 0;
                zeroC = 0;
                break;

            case R.id.rl_stick_low:
                mImageViewStickNew.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickLow.setBackgroundResource(R.drawable.icon_circle_selected);
                mImageViewStickHot.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickThrow.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickRate.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickReturn.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickZeroC.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickBetter.setBackgroundResource(R.drawable.icon_circle_normal);
                better = 0;
                news = 0;
                low = 1;
                hot = 0;
                shuaiwei = 0;
                rate = 0;
                returns = 0;
                zeroC = 0;
                break;

            case R.id.rl_stick_throw:
                mImageViewStickNew.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickLow.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickHot.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickThrow.setBackgroundResource(R.drawable.icon_circle_selected);
                mImageViewStickRate.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickReturn.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickZeroC.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickBetter.setBackgroundResource(R.drawable.icon_circle_normal);
                better = 0;
                news = 0;
                low = 0;
                hot = 0;
                shuaiwei = 1;
                rate = 0;
                returns = 0;
                zeroC = 0;
                break;

            case R.id.rl_stick_rate:
                mImageViewStickNew.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickLow.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickHot.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickThrow.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickRate.setBackgroundResource(R.drawable.icon_circle_selected);
                mImageViewStickReturn.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickZeroC.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickBetter.setBackgroundResource(R.drawable.icon_circle_normal);
                better = 0;
                news = 0;
                low = 0;
                hot = 0;
                shuaiwei = 0;
                rate = 1;
                returns = 0;
                zeroC = 0;
                break;

            case R.id.rl_stick_return:
                mImageViewStickNew.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickLow.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickHot.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickThrow.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickRate.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickReturn.setBackgroundResource(R.drawable.icon_circle_selected);
                mImageViewStickZeroC.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickBetter.setBackgroundResource(R.drawable.icon_circle_normal);
                better = 0;
                news = 0;
                low = 0;
                hot = 0;
                shuaiwei = 0;
                rate = 0;
                returns = 1;
                zeroC = 0;
                break;

            case R.id.rl_stick_zeroC:
                mImageViewStickNew.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickLow.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickHot.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickThrow.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickRate.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickReturn.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickZeroC.setBackgroundResource(R.drawable.icon_circle_selected);
                mImageViewStickBetter.setBackgroundResource(R.drawable.icon_circle_normal);
                better = 0;
                news = 0;
                low = 0;
                hot = 0;
                shuaiwei = 0;
                rate = 0;
                returns = 0;
                zeroC = 1;
                break;
            case R.id.confirm:
//                issueForeignRoute();

                if (id == 0) {
                    uploadImage(selImageList);
                } else {
                    uploadDataImage(selImageList);
                }
                break;
            case R.id.rl_travel_Occupation_type:
                Intent intentOccupation = new Intent(this, ProvincesTravelActivity.class);
                intentOccupation.putExtra("type", 1);
                startActivity(intentOccupation);
                break;
            case R.id.rl_travel_mudi_type:
                Intent intentmudi = new Intent(this, ForeignNationActivity.class);
//                intentmudi.putExtra("type", 2);
                startActivity(intentmudi);
                break;

            case R.id.rl_travel_hot_type:
                Intent intenthot = new Intent(this, ForeignSpotActivity.class);
                startActivity(intenthot);
                break;

            case R.id.rl_travel_day_type:
                mReleaseDialog = new ReleaseDialog(this, setDayType());
                mReleaseDialog.setCancelable(true);

                mReleaseDialog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        mTextViewDayType.setText(setDayType().get(position).getName().substring(0, setDayType().get(position).getName().length() - 1));
                        day = setDayType().get(position).getName().substring(0, setDayType().get(position).getName().length() - 1);
                        mReleaseDialog.cancel();
                    }
                });
                mReleaseDialog.show();
                break;
            case R.id.rl_release1_type:
                mReleaseDialog = new ReleaseDialog(this, setPaymentType());
                mReleaseDialog.setCancelable(true);

                mReleaseDialog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        mTextViewRelease1Type.setText(setPaymentType().get(position).getName());
                        pickupPrice = setPaymentType().get(position).getName();
                        mReleaseDialog.cancel();
                    }
                });
                mReleaseDialog.show();
                break;

        }
    }

    private void setViewData() {
        if (!TextUtils.isEmpty(PreferenceUtil.getString(Constanst.PROVINCE_TRAVEL_NAME))
                && !TextUtils.isEmpty(PreferenceUtil.getString(Constanst.CITY_TRAVEL_NAME))) {
            mTextViewOccupationType.setText(PreferenceUtil.getString(Constanst.PROVINCE_TRAVEL_NAME) + "," + PreferenceUtil.getString(Constanst.CITY_TRAVEL_NAME));
        }
        if (!TextUtils.isEmpty(PreferenceUtil.getString(Constanst.CITY_NATION_NAME)) && !TextUtils.isEmpty(PreferenceUtil.getString(Constanst.NATION_NAME))) {

            mTextViewMudiType.setText(PreferenceUtil.getString(Constanst.NATION_NAME) + "," + PreferenceUtil.getString(Constanst.CITY_NATION_NAME));
        }
        if (!TextUtils.isEmpty(PreferenceUtil.getString(Constanst.SPOT_NATION_NAME))) {
            mTextViewHotType.setText(PreferenceUtil.getString(Constanst.SPOT_NATION_NAME));
        }
    }

    private void deteledData() {
        if (!TextUtils.isEmpty(PreferenceUtil.getString(Constanst.PROVINCE_TRAVEL_NAME))
                && !TextUtils.isEmpty(PreferenceUtil.getString(Constanst.CITY_TRAVEL_NAME))) {
            PreferenceUtil.removeSp(Constanst.PROVINCE_TRAVEL_NAME, Constanst.SP_NAME);
            PreferenceUtil.removeSp(Constanst.CITY_TRAVEL_NAME, Constanst.SP_NAME);

        }
        if (!TextUtils.isEmpty(PreferenceUtil.getString(Constanst.CITY_NATION_NAME)) && !TextUtils.isEmpty(PreferenceUtil.getString(Constanst.NATION_NAME))) {
            PreferenceUtil.removeSp(Constanst.CITY_NATION_NAME, Constanst.SP_NAME);
            PreferenceUtil.removeSp(Constanst.NATION_NAME, Constanst.SP_NAME);
        }
        if (!TextUtils.isEmpty(PreferenceUtil.getString(Constanst.SPOT_NATION_NAME))) {
            PreferenceUtil.removeSp(Constanst.SPOT_NATION_NAME, Constanst.SP_NAME);
            PreferenceUtil.removeSp(Constanst.SPOT_NATION_NAME, Constanst.SP_NAME);
        }
    }

    private List<MyPopVlaues> setDayType() {
        Kouweilist = new ArrayList<MyPopVlaues>();
        Kouweilist.add(new MyPopVlaues("1天"));
        Kouweilist.add(new MyPopVlaues("2天"));
        Kouweilist.add(new MyPopVlaues("3天"));
        Kouweilist.add(new MyPopVlaues("4天"));

        return Kouweilist;
    }

    private List<MyPopVlaues> setPaymentType() {
        Kouweilist = new ArrayList<MyPopVlaues>();
        Kouweilist.add(new MyPopVlaues("有周边接送费"));
        Kouweilist.add(new MyPopVlaues("无接送费"));

        return Kouweilist;
    }

    private void issueForeignRoute() {
        String TravelTitle = mEditTextTravelTitle.getText().toString().trim();
        String Generalize = mEditTextGeneralize.getText().toString().trim();
        String TotalPrice = mTextViewTotalPrice.getText().toString().trim();
        String FinalPrice = mTextViewFinalPrice.getText().toString().trim();
        String ReturnPrice = mTextViewReturnPrice.getText().toString().trim();
        String TotalPriceChild = mTextViewTotalPriceChild.getText().toString().trim();
        String finalPriceChild = mTextViewFinalPriceChild.getText().toString().trim();
        String ReturnPriceChild = mTextViewReturnPriceChild.getText().toString().trim();

        String activity = PreferenceUtil.getString(Constanst.TAB_NMAE_ACTIVITY);
        String address = PreferenceUtil.getString(Constanst.TAB_NMAE_ADDRESS);
        String cons = PreferenceUtil.getString(Constanst.TAB_NMAE_CONS);
        String other = PreferenceUtil.getString(Constanst.TAB_NMAE_OTHER);
        String stay = PreferenceUtil.getString(Constanst.TAB_NMAE_STAY);
        String traffic = PreferenceUtil.getString(Constanst.TAB_NMAE_TRAFFIC);

        if (pickupPrice.equals("有周边接送费")) {
            pickupPrices = 1;
        } else {
            pickupPrices = 0;
        }
        if (news == 0 && low == 0 && better == 0 && shuaiwei == 0 && rate == 0 && returns == 0 && hot == 0 && zeroC == 0) {
            stick = 2;
        } else {
            stick = 1;
        }
        showProgressDialog();
        KyLog.d(PreferenceUtil.getString(Constanst.CITY_NAME));
        KyLog.d(PreferenceUtil.getString(Constanst.PROVINCE_NAME));

        KyLog.d(PreferenceUtil.getString(Constanst.NATION_NAME));
        KyLog.d(PreferenceUtil.getString(Constanst.CITY_NATION_NAME));
        KyLog.d(PreferenceUtil.getString(Constanst.SPOT_NATION_NAME));


//        if (TextUtils.isEmpty(VillageName) && TextUtils.isEmpty(Acreage) && TextUtils.isEmpty(totalPrice) && TextUtils.isEmpty(houseType)){
//            Toast.makeText(this, "请填写必填信息", Toast.LENGTH_SHORT).show();
//            return;
//        }


        ApiModule.getInstance().issueForeignRoute(PreferenceUtil.getString(Constanst.CITY_NAME), PreferenceUtil.getString(Constanst.PROVINCE_NAME), PreferenceUtil.getString(Constanst.NATION_NAME),
                PreferenceUtil.getString(Constanst.CITY_NATION_NAME), PreferenceUtil.getString(Constanst.SPOT_NATION_NAME), String.valueOf(stick), String.valueOf(caixian), day, TotalPrice, FinalPrice, ReturnPrice, String.valueOf(pickupPrices),
                TotalPriceChild, finalPriceChild, ReturnPriceChild, address, traffic, cons,
                activity, stay, other, TravelTitle, Generalize, null,
                String.valueOf(news), String.valueOf(low), String.valueOf(better), String.valueOf(shuaiwei), String.valueOf(rate), String.valueOf(returns), String.valueOf(hot),
                String.valueOf(zeroC), "")
                .subscribe(response -> {

                    cancelProgressDialog();
//                    KyLog.d(response.getResultMsg());
//                    Toast.makeText(this, response.getResultMsg(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);

                }, throwable -> {
                    KyLog.d(throwable.toString());
                    cancelProgressDialog();
                    Toast.makeText(this, throwable.getMessage().toString(), Toast.LENGTH_SHORT).show();
                });
    }


    private void selectTravelTab() {
        showProgressDialog();
        ApiModule.getInstance().selectTravelTab("1")
                .subscribe(tabTravelNameEntity -> {
                    KyLog.object(tabTravelNameEntity + "");
                    cancelProgressDialog();
                    setActivityData(tabTravelNameEntity.getActivityList(), mRecyclerViewActivity);
                    setAddressListData(tabTravelNameEntity.getAddressList(), mRecyclerViewAddress);
                    setConsData(tabTravelNameEntity.getConsList(), mRecyclerViewCons);
                    setOtherData(tabTravelNameEntity.getOtherList(), mRecyclerViewOther);
                    setTrafficData(tabTravelNameEntity.getTrafficList(), mRecyclerViewTraffic);
                    setStayData(tabTravelNameEntity.getStayList(), mRecyclerViewStay);


                }, throwable -> {
                    KyLog.d(throwable.toString());
                    cancelProgressDialog();
                    Toast.makeText(this, throwable.getMessage().toString(), Toast.LENGTH_SHORT).show();
                });
    }


    private void setActivityData(List<TabTravelNameEntity.ActivityListBean> list, RecyclerView recyclerView) {
        if (list.size() > 0) {
            GridLayoutManager manager = new GridLayoutManager(this, 5);
            TableTravelActivityAdapter mAdapterTableName = new TableTravelActivityAdapter(list, this);
            recyclerView.setAdapter(mAdapterTableName);
            recyclerView.setLayoutManager(manager);
            recyclerView.addItemDecoration(new SpaceItemDecoration(0, 15));
        }
    }

    private void setAddressListData(List<TabTravelNameEntity.AddressListBean> list, RecyclerView recyclerView) {
        if (list.size() > 0) {
            GridLayoutManager manager = new GridLayoutManager(this, 5);
            TableTravelAddressListAdapter mAdapterTableName = new TableTravelAddressListAdapter(list, this);
            recyclerView.setAdapter(mAdapterTableName);
            recyclerView.setLayoutManager(manager);
            recyclerView.addItemDecoration(new SpaceItemDecoration(0, 15));
        }
    }

    private void setConsData(List<TabTravelNameEntity.ConsListBean> list, RecyclerView recyclerView) {
        if (list.size() > 0) {
            GridLayoutManager manager = new GridLayoutManager(this, 5);
            TableTravelConsAdapter mAdapterTableName = new TableTravelConsAdapter(list, this);
            recyclerView.setAdapter(mAdapterTableName);
            recyclerView.setLayoutManager(manager);
            recyclerView.addItemDecoration(new SpaceItemDecoration(0, 15));
        }
    }

    private void setOtherData(List<TabTravelNameEntity.OtherListBean> list, RecyclerView recyclerView) {
        if (list.size() > 0) {
            GridLayoutManager manager = new GridLayoutManager(this, 5);
            TableTravelOtherAdapter mAdapterTableName = new TableTravelOtherAdapter(list, this);
            recyclerView.setAdapter(mAdapterTableName);
            recyclerView.setLayoutManager(manager);
            recyclerView.addItemDecoration(new SpaceItemDecoration(0, 15));
        }
    }

    private void setStayData(List<TabTravelNameEntity.StayListBean> list, RecyclerView recyclerView) {
        if (list.size() > 0) {
            GridLayoutManager manager = new GridLayoutManager(this, 5);
            TableTravelStayAdapter mAdapterTableName = new TableTravelStayAdapter(list, this);
            recyclerView.setAdapter(mAdapterTableName);
            recyclerView.setLayoutManager(manager);
            recyclerView.addItemDecoration(new SpaceItemDecoration(0, 15));
        }
    }

    private void setTrafficData(List<TabTravelNameEntity.TrafficListBean> list, RecyclerView recyclerView) {
        if (list.size() > 0) {
            GridLayoutManager manager = new GridLayoutManager(this, 5);
            TableTravelTrafficAdapter mAdapterTableName = new TableTravelTrafficAdapter(list, this);
            recyclerView.setAdapter(mAdapterTableName);
            recyclerView.setLayoutManager(manager);
            recyclerView.addItemDecoration(new SpaceItemDecoration(0, 15));
        }
    }


    /**
     * 打开相机
     *
     * @param view
     * @param position
     */
    @Override
    public void onItemClick(View view, int position) {
        List<String> names = new ArrayList<>();
        names.add("拍照");
        names.add("相册");
        showDialog(new SelectDialog.SelectDialogListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: // 直接调起相机
                        //打开选择,本次允许选择的数量
                        ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                        Intent intent = new Intent(OverseasReleaseActivity.this, ImageGridActivity.class);
                        intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                        startActivityForResult(intent, REQUEST_CODE_SELECT);
                        break;
                    case 1:
                        //打开选择,本次允许选择的数量
                        ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                        Intent intent1 = new Intent(OverseasReleaseActivity.this, ImageGridActivity.class);
                        startActivityForResult(intent1, REQUEST_CODE_SELECT);
                        break;
                    default:
                        break;
                }
            }
        }, names);
    }

    /**
     * 获取返回的图片信息
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        KyLog.d(requestCode + "");
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (data != null && requestCode == REQUEST_CODE_SELECT) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images != null) {
                    selImageList.addAll(images);
                    adapter.setImages(selImageList);
                }
            }
        } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
            //预览图片返回
            if (data != null && requestCode == REQUEST_CODE_PREVIEW) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
                if (images != null) {
                    selImageList.clear();
                    selImageList.addAll(images);
                    adapter.setImages(selImageList);
                }
            }
        }
    }


    /**
     * 上传图片
     *
     * @param pathList
     */
    private void uploadImage(ArrayList<ImageItem> pathList) {

        String TravelTitle = mEditTextTravelTitle.getText().toString().trim();
        String Generalize = mEditTextGeneralize.getText().toString().trim();
        String TotalPrice = mTextViewTotalPrice.getText().toString().trim();
        String FinalPrice = mTextViewFinalPrice.getText().toString().trim();
        String ReturnPrice = mTextViewReturnPrice.getText().toString().trim();
        String TotalPriceChild = mTextViewTotalPriceChild.getText().toString().trim();
        String finalPriceChild = mTextViewFinalPriceChild.getText().toString().trim();
        String ReturnPriceChild = mTextViewReturnPriceChild.getText().toString().trim();
        Traffic = PreferenceUtil.getString(Constanst.TAB_NMAE_TRAFFIC);
        Address = PreferenceUtil.getString(Constanst.TAB_NMAE_ADDRESS);
        Consume = PreferenceUtil.getString(Constanst.TAB_NMAE_CONS);
        Activity = PreferenceUtil.getString(Constanst.TAB_NMAE_ACTIVITY);
        Stay = PreferenceUtil.getString(Constanst.TAB_NMAE_STAY);
        Other = PreferenceUtil.getString(Constanst.TAB_NMAE_OTHER);
        if (!TextUtils.isEmpty(pickupPrice)) {
            if (pickupPrice.equals("有周边接送费")) {
                pickupPrices = 1;
            } else {
                pickupPrices = 0;
            }
        } else {
            Toast.makeText(this, "请选择有无接送费", Toast.LENGTH_SHORT).show();
            return;
        }
        if (news == 0 && low == 0 && better == 0 && shuaiwei == 0 && rate == 0 && returns == 0 && hot == 0 && zeroC == 0) {
            stick = 2;
        } else {
            stick = 1;
        }
        KyLog.d(PreferenceUtil.getString(Constanst.CITY_NAME));
        KyLog.d(PreferenceUtil.getString(Constanst.PROVINCE_CODE));

        KyLog.d(PreferenceUtil.getString(Constanst.SPOT_ID));
        KyLog.d(PreferenceUtil.getString(Constanst.SPOT_NAME));
        KyLog.d(PreferenceUtil.getString(Constanst.CITY_MUDI_TRAVEL_NAME));
        KyLog.d(PreferenceUtil.getString(Constanst.PROVINCE_MUDI_TRAVEL_NAME));
        KyLog.d(PreferenceUtil.getString(Constanst.CITY_MUDI_CODE));
        KyLog.d(PreferenceUtil.getString(Constanst.CITY_TRAVEL_NAME));

        if (
                TextUtils.isEmpty(PreferenceUtil.getString(Constanst.CITY_NAME))
                || TextUtils.isEmpty(PreferenceUtil.getString(Constanst.PROVINCE_NAME)) || TextUtils.isEmpty(PreferenceUtil.getString(Constanst.NATION_NAME))
                || TextUtils.isEmpty(PreferenceUtil.getString(Constanst.CITY_NATION_NAME)) || TextUtils.isEmpty(PreferenceUtil.getString(Constanst.SPOT_NATION_NAME))) {

            Toast.makeText(this, "请选择出发地或者目的地或景点", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(day) || TextUtils.isEmpty(TotalPrice) || TextUtils.isEmpty(ReturnPrice) || TextUtils.isEmpty(FinalPrice)) {
            Toast.makeText(this, "请选择天数或填写成人价格", Toast.LENGTH_SHORT).show();
            return;
        }

        showProgressDialog();

        Map<String, String> map = new HashMap<>();
        map.put("depart_name", PreferenceUtil.getString(Constanst.CITY_NAME));
        map.put("depart_pro_name", PreferenceUtil.getString(Constanst.PROVINCE_NAME));
        map.put("goals_nat_name", PreferenceUtil.getString(Constanst.NATION_NAME));
        map.put("goals_name", PreferenceUtil.getString(Constanst.CITY_NATION_NAME));
        map.put("spot_name", PreferenceUtil.getString(Constanst.SPOT_NATION_NAME));
        map.put("stick", String.valueOf(stick));
        map.put("uid", String.valueOf(PreferenceUtil.getInt(UID)));
        map.put("line_or_throw", String.valueOf(caixian));
        map.put("number_days", day);

        map.put("total_price", TotalPrice);
        map.put("final_price", FinalPrice);
        map.put("return_price", ReturnPrice);
        map.put("pickup_price", String.valueOf(pickupPrices));
        map.put("total_price_child", TotalPriceChild);
        map.put("final_price_child", finalPriceChild);

        map.put("return_price_child", ReturnPriceChild);
        if (!TextUtils.isEmpty(Address)) {
            map.put("tAddressId", Address);
        }
        if (!TextUtils.isEmpty(Traffic)) {

            map.put("tTrafficId", Traffic);
        }
        if (!TextUtils.isEmpty(Consume)) {

            map.put("tConsumeId", Consume);
        }
        if (!TextUtils.isEmpty(Activity)) {

            map.put("tActivityId", Activity);
        }
        if (!TextUtils.isEmpty(Stay)) {

            map.put("tStayId", Stay);
        }
        if (!TextUtils.isEmpty(Other)) {

            map.put("tOtherId", Other);
        }
        map.put("travel_title", TravelTitle);
        map.put("generalize", Generalize);

        map.put("stick_new", String.valueOf(news));
        map.put("stick_low", String.valueOf(low));
        map.put("stick_better", String.valueOf(better));
        map.put("stick_throw", String.valueOf(shuaiwei));
        map.put("stick_rate", String.valueOf(rate));
        map.put("stick_return", String.valueOf(returns));
        map.put("stick_hot", String.valueOf(hot));
        map.put("stick_zeroC", String.valueOf(zeroC));
        map.put("token", PreferenceUtil.getString(TOKEN));
//        map.put("user_idForCol", String.valueOf(PreferenceUtil.getInt(UID)));
        KyLog.object(map);


        String url = "http://39.105.203.33/jlkf/mutual-trust/travel/issueForeignRoute";

        httpUtil.postFileRequest(url, map, pathList, new MyStringCallBack() {

            @Override
            public void onError(Call call, Exception e, int id) {
                super.onError(call, e, id);
                KyLog.d(e + "");
                Toast.makeText(OverseasReleaseActivity.this, "发布失败", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onResponse(String response, int id) {
                super.onResponse(response, id);
                //返回图片的地址
                KyLog.d(response);
                getUseInfo();

                Toast.makeText(OverseasReleaseActivity.this, "发布成功", Toast.LENGTH_SHORT).show();
                if (TextUtils.isEmpty(type)) {
                    Intent intent = new Intent(OverseasReleaseActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    try {
                        JSONObject res = new JSONObject();

                        JSONArray array = new JSONArray();
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("type", 2);
                        jsonObject.put("travelType", 3);
                        JSONObject data = new JSONObject(response).getJSONObject("data");
                        jsonObject.put("data", data);
                        array.put(jsonObject);

                        res.put("type", 2);
                        res.put("arrData", array);
                        String result = res.toString();
                        Bundle bundle = new Bundle();
                        bundle.putString("msg", result);
                        Intent intent = getIntent();
                        intent.putExtras(bundle);
                        setResult(android.app.Activity.RESULT_OK, intent);
                        finish();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }


    /**
     * 更新图片
     *
     * @param pathList
     */
    private void uploadDataImage(ArrayList<ImageItem> pathList) {
        KyLog.d("uploadDataImageJinWaiYou");

        String TravelTitle = mEditTextTravelTitle.getText().toString().trim();
        String Generalize = mEditTextGeneralize.getText().toString().trim();
        String TotalPrice = mTextViewTotalPrice.getText().toString().trim();
        String FinalPrice = mTextViewFinalPrice.getText().toString().trim();
        String ReturnPrice = mTextViewReturnPrice.getText().toString().trim();
        String TotalPriceChild = mTextViewTotalPriceChild.getText().toString().trim();
        String finalPriceChild = mTextViewFinalPriceChild.getText().toString().trim();
        String ReturnPriceChild = mTextViewReturnPriceChild.getText().toString().trim();
        Traffic = PreferenceUtil.getString(Constanst.TAB_NMAE_TRAFFIC);
        Address = PreferenceUtil.getString(Constanst.TAB_NMAE_ADDRESS);
        Consume = PreferenceUtil.getString(Constanst.TAB_NMAE_CONS);
        Activity = PreferenceUtil.getString(Constanst.TAB_NMAE_ACTIVITY);
        Stay = PreferenceUtil.getString(Constanst.TAB_NMAE_STAY);
        Other = PreferenceUtil.getString(Constanst.TAB_NMAE_OTHER);
        if (!TextUtils.isEmpty(pickupPrice)) {
            if (pickupPrice.equals("有周边接送费")) {
                pickupPrices = 1;
            } else {
                pickupPrices = 0;
            }
        } else {
            Toast.makeText(this, "请选择有无接送费", Toast.LENGTH_SHORT).show();
            return;
        }
        if (news == 0 && low == 0 && better == 0 && shuaiwei == 0 && rate == 0 && returns == 0 && hot == 0 && zeroC == 0) {
            stick = 2;
        } else {
            stick = 1;
        }
        KyLog.d(PreferenceUtil.getString(Constanst.CITY_NAME));
        KyLog.d(PreferenceUtil.getString(Constanst.PROVINCE_CODE));

        KyLog.d(PreferenceUtil.getString(Constanst.SPOT_ID));
        KyLog.d(PreferenceUtil.getString(Constanst.SPOT_NAME));
        KyLog.d(PreferenceUtil.getString(Constanst.CITY_MUDI_TRAVEL_NAME));
        KyLog.d(PreferenceUtil.getString(Constanst.PROVINCE_MUDI_TRAVEL_NAME));
        KyLog.d(PreferenceUtil.getString(Constanst.CITY_MUDI_CODE));
        KyLog.d(PreferenceUtil.getString(Constanst.CITY_TRAVEL_NAME));

        if (TextUtils.isEmpty(PreferenceUtil.getString(Constanst.CITY_NAME))
                        || TextUtils.isEmpty(PreferenceUtil.getString(Constanst.PROVINCE_NAME)) || TextUtils.isEmpty(PreferenceUtil.getString(Constanst.NATION_NAME))
                        || TextUtils.isEmpty(PreferenceUtil.getString(Constanst.CITY_NATION_NAME)) || TextUtils.isEmpty(PreferenceUtil.getString(Constanst.SPOT_NATION_NAME))) {

            Toast.makeText(this, "请选择出发地或者目的地或景点", Toast.LENGTH_SHORT).show();
            return;
        }


        if (TextUtils.isEmpty(day) || TextUtils.isEmpty(TotalPrice) || TextUtils.isEmpty(ReturnPrice) || TextUtils.isEmpty(FinalPrice)) {
            Toast.makeText(this, "请选择天数或填写成人价格", Toast.LENGTH_SHORT).show();
            return;
        }

        showProgressDialog();

        Map<String, String> map = new HashMap<>();
        map.put("depart_name", PreferenceUtil.getString(Constanst.CITY_NAME));
        map.put("depart_pro_name", PreferenceUtil.getString(Constanst.PROVINCE_NAME));
        map.put("goals_nat_name", PreferenceUtil.getString(Constanst.NATION_NAME));
        map.put("goals_name", PreferenceUtil.getString(Constanst.CITY_NATION_NAME));
        map.put("spot_name", PreferenceUtil.getString(Constanst.SPOT_NATION_NAME));
        map.put("stick", String.valueOf(stick));
        map.put("uid", String.valueOf(PreferenceUtil.getInt(UID)));
        map.put("line_or_throw", String.valueOf(caixian));
        map.put("number_days", day);

        map.put("total_price", TotalPrice);
        map.put("final_price", FinalPrice);
        map.put("return_price", ReturnPrice);
        map.put("pickup_price", String.valueOf(pickupPrices));
        map.put("total_price_child", TotalPriceChild);
        map.put("final_price_child", finalPriceChild);

        map.put("return_price_child", ReturnPriceChild);
        if (!TextUtils.isEmpty(Address)) {
            map.put("tAddressId", Address);
        }
        if (!TextUtils.isEmpty(Traffic)) {

            map.put("tTrafficId", Traffic);
        }
        if (!TextUtils.isEmpty(Consume)) {

            map.put("tConsumeId", Consume);
        }
        if (!TextUtils.isEmpty(Activity)) {

            map.put("tActivityId", Activity);
        }
        if (!TextUtils.isEmpty(Stay)) {

            map.put("tStayId", Stay);
        }
        if (!TextUtils.isEmpty(Other)) {

            map.put("tOtherId", Other);
        }
        map.put("travel_title", TravelTitle);
        map.put("generalize", Generalize);

        map.put("stick_new", String.valueOf(news));
        map.put("stick_low", String.valueOf(low));
        map.put("stick_better", String.valueOf(better));
        map.put("stick_throw", String.valueOf(shuaiwei));
        map.put("stick_rate", String.valueOf(rate));
        map.put("stick_return", String.valueOf(returns));
        map.put("stick_hot", String.valueOf(hot));
        map.put("stick_zeroC", String.valueOf(zeroC));
        map.put("token", PreferenceUtil.getString(TOKEN));
        map.put("id", String.valueOf(id));

//        map.put("user_idForCol", String.valueOf(PreferenceUtil.getInt(UID)));
        KyLog.object(map);


        String url = "http://39.105.203.33/jlkf/mutual-trust/travel/updatePersonageForiegn";

        httpUtil.postFileRequest(url, map, pathList, new MyStringCallBack() {

            @Override
            public void onError(Call call, Exception e, int id) {
                super.onError(call, e, id);
                KyLog.d(e + "");
                Toast.makeText(OverseasReleaseActivity.this, "修改失败", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onResponse(String response, int id) {
                super.onResponse(response, id);
                //返回图片的地址
                KyLog.d(response);
                getUseInfo();

                Toast.makeText(OverseasReleaseActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(OverseasReleaseActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private SelectDialog showDialog(SelectDialog.SelectDialogListener listener, List<String> names) {
        SelectDialog dialog = new SelectDialog(this, R.style.transparentFrameWindowStyle, listener, names);
        if (!this.isFinishing()) {
            dialog.show();
        }
        return dialog;
    }

    private void getUseInfo() {
        ApiModule.getInstance().getUserInfo(String.valueOf(PreferenceUtil.getInt(UID)))
                .subscribe(loginEntity -> {
                    PreferenceUtil.putInt(Constanst.TOP_ZHIDING, loginEntity.getStickNumber());

                }, throwable -> {
//                    Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    KyLog.d(throwable.getMessage());

                });
    }

    private void SetEnabled() {
        if (PreferenceUtil.getInt(Constanst.TOP_ZHIDING) <= 0) {
            mRelativeLayoutStickBetter.setEnabled(false);
            mRelativeLayoutStickBetter.setFocusable(false);

            mRelativeLayoutStickHot.setEnabled(false);
            mRelativeLayoutStickHot.setFocusable(false);

            mRelativeLayoutStickLow.setEnabled(false);
            mRelativeLayoutStickLow.setFocusable(false);

            mRelativeLayoutStickNew.setEnabled(false);
            mRelativeLayoutStickNew.setFocusable(false);

            mRelativeLayoutStickRate.setEnabled(false);
            mRelativeLayoutStickRate.setFocusable(false);

            mRelativeLayoutStickReturn.setEnabled(false);
            mRelativeLayoutStickReturn.setFocusable(false);

            mRelativeLayoutStickThrow.setEnabled(false);
            mRelativeLayoutStickThrow.setFocusable(false);

            mRelativeLayoutStickZeroC.setEnabled(false);
            mRelativeLayoutStickZeroC.setFocusable(false);
        } else {
            mRelativeLayoutStickBetter.setEnabled(true);
            mRelativeLayoutStickBetter.setFocusable(true);

            mRelativeLayoutStickHot.setEnabled(true);
            mRelativeLayoutStickHot.setFocusable(true);

            mRelativeLayoutStickLow.setEnabled(true);
            mRelativeLayoutStickLow.setFocusable(true);

            mRelativeLayoutStickNew.setEnabled(true);
            mRelativeLayoutStickNew.setFocusable(true);

            mRelativeLayoutStickRate.setEnabled(true);
            mRelativeLayoutStickRate.setFocusable(true);

            mRelativeLayoutStickReturn.setEnabled(true);
            mRelativeLayoutStickReturn.setFocusable(true);

            mRelativeLayoutStickThrow.setEnabled(true);
            mRelativeLayoutStickThrow.setFocusable(true);

            mRelativeLayoutStickZeroC.setEnabled(true);
            mRelativeLayoutStickZeroC.setFocusable(true);
        }
    }
}
