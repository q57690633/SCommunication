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
import com.huxin.communication.adpter.TableTravelActivityAdapter;
import com.huxin.communication.adpter.TableTravelAddressListAdapter;
import com.huxin.communication.adpter.TableTravelConsAdapter;
import com.huxin.communication.adpter.TableTravelOtherAdapter;
import com.huxin.communication.adpter.TableTravelStayAdapter;
import com.huxin.communication.adpter.TableTravelTrafficAdapter;
import com.huxin.communication.base.BaseActivity;
import com.huxin.communication.controls.Constanst;
import com.huxin.communication.custom.ReleaseDialog;
import com.huxin.communication.entity.MyPopVlaues;
import com.huxin.communication.entity.TabTravelNameEntity;
import com.huxin.communication.http.ApiModule;
import com.huxin.communication.ui.InlandSpotActivity;
import com.huxin.communication.ui.MainActivity;
import com.huxin.communication.ui.ProvincesTravelActivity;
import com.huxin.communication.utils.PreferenceUtil;
import com.huxin.communication.view.SpaceItemDecoration;
import com.sky.kylog.KyLog;

import java.util.ArrayList;
import java.util.List;

public class ReleaseGuoNeiActivity extends BaseActivity implements View.OnClickListener {

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
    private ImageView mImageViewStickNewClick;

    private ImageView mImageViewStickLow;
    private ImageView mImageViewStickLowClick;

    private ImageView mImageViewStickBetter;
    private ImageView mImageViewStickBetterClick;

    private ImageView mImageViewStickThrow;
    private ImageView mImageViewStickThrowClick;

    private ImageView mImageViewStickRate;
    private ImageView mImageViewStickRateClick;

    private ImageView mImageViewStickReturn;
    private ImageView mImageViewStickReturnClick;

    private ImageView mImageViewStickHot;
    private ImageView mImageViewStickHotClick;

    private ImageView mImageViewStickZeroC;
    private ImageView mImageViewStickZeroClick;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_release2);
    }

    @Override
    protected void initViews() {
        setToolbarCenterMode("发布国内游线路", MODE_BACK);
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
        mImageViewStickNewClick = (ImageView) findViewById(R.id.stick_new_click);

        mImageViewStickLow = (ImageView) findViewById(R.id.stick_low);
        mImageViewStickLowClick = (ImageView) findViewById(R.id.stick_low_click);

        mImageViewStickBetter = (ImageView) findViewById(R.id.stick_better);
        mImageViewStickBetterClick = (ImageView) findViewById(R.id.stick_better_click);

        mImageViewStickThrow = (ImageView) findViewById(R.id.stick_throw);
        mImageViewStickThrowClick = (ImageView) findViewById(R.id.stick_throw_click);

        mImageViewStickRate = (ImageView) findViewById(R.id.stick_rate);
        mImageViewStickRateClick = (ImageView) findViewById(R.id.stick_rate_click);

        mImageViewStickReturn = (ImageView) findViewById(R.id.stick_return);
        mImageViewStickReturnClick = (ImageView) findViewById(R.id.stick_return_click);


        mImageViewStickHot = (ImageView) findViewById(R.id.stick_hot);
        mImageViewStickHotClick = (ImageView) findViewById(R.id.stick_hot_click);

        mImageViewStickZeroC = (ImageView) findViewById(R.id.stick_zeroC);
        mImageViewStickZeroClick = (ImageView) findViewById(R.id.stick_zeroC_click);

        mTextViewConfirm = (TextView) findViewById(R.id.confirm);

        mImageViewShuaiWei.setOnClickListener(this);
        mImageViewShuaiWeiClick.setOnClickListener(this);
        mImageViewCaiXian.setOnClickListener(this);
        getmImageViewCaiXianClick.setOnClickListener(this);
        mImageViewStickNew.setOnClickListener(this);
        mImageViewStickNewClick.setOnClickListener(this);
        mImageViewStickLow.setOnClickListener(this);
        mImageViewStickLowClick.setOnClickListener(this);
        mImageViewStickBetter.setOnClickListener(this);
        mImageViewStickBetterClick.setOnClickListener(this);
        mImageViewStickThrow.setOnClickListener(this);
        mImageViewStickThrowClick.setOnClickListener(this);
        mImageViewStickRate.setOnClickListener(this);
        mImageViewStickRateClick.setOnClickListener(this);
        mImageViewStickReturn.setOnClickListener(this);
        mImageViewStickReturnClick.setOnClickListener(this);
        mImageViewStickHot.setOnClickListener(this);
        mImageViewStickHotClick.setOnClickListener(this);
        mImageViewStickZeroC.setOnClickListener(this);
        mImageViewStickZeroClick.setOnClickListener(this);
        mTextViewConfirm.setOnClickListener(this);
        mRelativeLayoutOccupationType.setOnClickListener(this);
        mRelativeLayoutMudiType.setOnClickListener(this);
        mRelativeLayoutHotType.setOnClickListener(this);
        mRelativeLayoutDayType.setOnClickListener(this);
        mRelativeLayoutRelease1Type.setOnClickListener(this);
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        selectTravelTab();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setViewData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.stick_better:
                mImageViewStickBetterClick.setVisibility(View.VISIBLE);
                mImageViewStickBetter.setVisibility(View.GONE);
                better = 1;
                break;
            case R.id.stick_better_click:
                mImageViewStickBetterClick.setVisibility(View.GONE);
                mImageViewStickBetter.setVisibility(View.VISIBLE);
                better = 0;
                break;
            case R.id.stick_hot:
                mImageViewStickHot.setVisibility(View.GONE);
                mImageViewStickHotClick.setVisibility(View.VISIBLE);
                hot = 1;
                break;
            case R.id.stick_hot_click:
                mImageViewStickHotClick.setVisibility(View.GONE);
                mImageViewStickHot.setVisibility(View.VISIBLE);
                hot = 0;
                break;
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
            case R.id.stick_new:
                mImageViewStickNew.setVisibility(View.GONE);
                mImageViewStickNewClick.setVisibility(View.VISIBLE);
                news = 1;
                break;
            case R.id.stick_new_click:
                mImageViewStickNewClick.setVisibility(View.GONE);
                mImageViewStickNew.setVisibility(View.VISIBLE);
                news = 0;
                break;
            case R.id.stick_low:
                mImageViewStickLow.setVisibility(View.GONE);
                mImageViewStickLowClick.setVisibility(View.VISIBLE);
                low = 1;
                break;
            case R.id.stick_low_click:
                mImageViewStickLowClick.setVisibility(View.GONE);
                mImageViewStickLow.setVisibility(View.VISIBLE);
                news = 0;
                break;
            case R.id.stick_throw:
                mImageViewStickThrowClick.setVisibility(View.VISIBLE);
                mImageViewStickThrow.setVisibility(View.GONE);
                shuaiwei = 1;
                break;
            case R.id.stick_throw_click:
                mImageViewStickThrowClick.setVisibility(View.GONE);
                mImageViewStickThrow.setVisibility(View.VISIBLE);
                shuaiwei = 0;
                break;
            case R.id.stick_rate:
                mImageViewStickRateClick.setVisibility(View.VISIBLE);
                mImageViewStickRate.setVisibility(View.GONE);
                rate = 1;
                break;
            case R.id.stick_rate_click:
                mImageViewStickRateClick.setVisibility(View.GONE);
                mImageViewStickRate.setVisibility(View.VISIBLE);
                rate = 0;
                break;
            case R.id.stick_return:
                mImageViewStickReturnClick.setVisibility(View.VISIBLE);
                mImageViewStickReturn.setVisibility(View.GONE);
                returns = 1;
                break;
            case R.id.stick_return_click:
                mImageViewStickReturnClick.setVisibility(View.GONE);
                mImageViewStickReturn.setVisibility(View.VISIBLE);
                returns = 0;
                break;
            case R.id.stick_zeroC:
                mImageViewStickZeroClick.setVisibility(View.VISIBLE);
                mImageViewStickZeroC.setVisibility(View.GONE);
                zeroC = 1;
                break;
            case R.id.stick_zeroC_click:
                mImageViewStickZeroClick.setVisibility(View.GONE);
                mImageViewStickZeroC.setVisibility(View.VISIBLE);
                zeroC = 0;
                break;
            case R.id.confirm:
                issueAroundRoute();
                break;
            case R.id.rl_travel_Occupation_type:
                Intent intentOccupation = new Intent(this, ProvincesTravelActivity.class);
                intentOccupation.putExtra("type", 1);
                startActivity(intentOccupation);
                break;
            case R.id.rl_travel_mudi_type:
                Intent intentmudi = new Intent(this, ProvincesTravelActivity.class);
                intentmudi.putExtra("type", 2);
                startActivity(intentmudi);
                break;

            case R.id.rl_travel_hot_type:
                Intent intenthot = new Intent(this, InlandSpotActivity.class);
                startActivity(intenthot);
                break;

            case R.id.rl_travel_day_type:
                mReleaseDialog = new ReleaseDialog(this, setDayType());
                mReleaseDialog.setCancelable(true);

                mReleaseDialog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        mTextViewDayType.setText(setDayType().get(position).getName());
                        day = setDayType().get(position).getName();
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
        if (!TextUtils.isEmpty(PreferenceUtil.getString(Constanst.PROVINCE_MUDI_TRAVEL_NAME))
                && !TextUtils.isEmpty(PreferenceUtil.getString(Constanst.CITY_MUDI_TRAVEL_NAME))) {

            mTextViewMudiType.setText(PreferenceUtil.getString(Constanst.PROVINCE_MUDI_TRAVEL_NAME) + "," + PreferenceUtil.getString(Constanst.CITY_MUDI_TRAVEL_NAME));
        }
        if (!TextUtils.isEmpty(PreferenceUtil.getString(Constanst.SPOT_NAME))) {
            mTextViewHotType.setText(PreferenceUtil.getString(Constanst.SPOT_NAME));
        }
    }

    private List<MyPopVlaues> setDayType() {
        Kouweilist = new ArrayList<MyPopVlaues>();
        Kouweilist.add(new MyPopVlaues("1"));
        Kouweilist.add(new MyPopVlaues("2"));
        Kouweilist.add(new MyPopVlaues("3"));
        Kouweilist.add(new MyPopVlaues("4"));

        return Kouweilist;
    }

    private List<MyPopVlaues> setPaymentType() {
        Kouweilist = new ArrayList<MyPopVlaues>();
        Kouweilist.add(new MyPopVlaues("有周边接送费"));
        Kouweilist.add(new MyPopVlaues("无接送费"));

        return Kouweilist;
    }

    private void issueAroundRoute() {
        String TravelTitle = mEditTextTravelTitle.getText().toString().trim();
        String Generalize = mEditTextGeneralize.getText().toString().trim();
        String TotalPrice = mTextViewTotalPrice.getText().toString().trim();
        String FinalPrice = mTextViewFinalPrice.getText().toString().trim();
        String ReturnPrice = mTextViewReturnPrice.getText().toString().trim();
        String TotalPriceChild = mTextViewTotalPriceChild.getText().toString().trim();
        String finalPriceChild = mTextViewFinalPriceChild.getText().toString().trim();
        String ReturnPriceChild = mTextViewReturnPriceChild.getText().toString().trim();
        if (pickupPrice.equals("有周边接送费")) {
            pickupPrices = 1;
        } else {
            pickupPrices = 0;
        }
        if (news == 0 && low == 0 && better == 0 && shuaiwei == 0 && rate == 0 && returns == 0 && hot == 0 && zeroC == 0){
            stick = 2;
        }else {
            stick = 1;
        }
        showProgressDialog();
        KyLog.d(PreferenceUtil.getString(Constanst.CITY_CODE));
        KyLog.d(PreferenceUtil.getString(Constanst.PROVINCE_CODE));

        KyLog.d(PreferenceUtil.getString(Constanst.SPOT_ID));
        KyLog.d(PreferenceUtil.getString(Constanst.SPOT_NAME));
        KyLog.d(PreferenceUtil.getString(Constanst.CITY_MUDI_TRAVEL_NAME));
        KyLog.d(PreferenceUtil.getString(Constanst.PROVINCE_MUDI_TRAVEL_NAME));
        KyLog.d(PreferenceUtil.getString(Constanst.CITY_MUDI_CODE));
        KyLog.d(PreferenceUtil.getString(Constanst.CITY_TRAVEL_NAME));

        ApiModule.getInstance().issueAroundRoute(PreferenceUtil.getString(Constanst.CITY_CODE), PreferenceUtil.getString(Constanst.PROVINCE_CODE), PreferenceUtil.getString(Constanst.SPOT_ID), PreferenceUtil.getString(Constanst.SPOT_NAME), day, TotalPrice, FinalPrice, ReturnPrice, String.valueOf(pickupPrices), TotalPriceChild, finalPriceChild, ReturnPriceChild,
                null, null, null, null, null, null,
                TravelTitle, Generalize, String.valueOf(stick),String.valueOf(caixian), null, String.valueOf(news),
                String.valueOf(low), String.valueOf(better), String.valueOf(shuaiwei), String.valueOf(rate), String.valueOf(returns), String.valueOf(hot),
                String.valueOf(zeroC), PreferenceUtil.getString(Constanst.CITY_MUDI_TRAVEL_NAME), PreferenceUtil.getString(Constanst.PROVINCE_MUDI_TRAVEL_NAME), PreferenceUtil.getString(Constanst.CITY_MUDI_CODE), PreferenceUtil.getString(Constanst.CITY_TRAVEL_NAME))
                .subscribe(response -> {

                    cancelProgressDialog();
                    KyLog.d(response.getResultMsg());
                    Toast.makeText(this, response.getResultMsg(), Toast.LENGTH_SHORT).show();
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

}
