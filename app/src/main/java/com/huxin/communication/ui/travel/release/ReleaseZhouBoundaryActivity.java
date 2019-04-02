package com.huxin.communication.ui.travel.release;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
import com.huxin.communication.entity.MyPopVlaues;
import com.huxin.communication.entity.TabTravelNameEntity;
import com.huxin.communication.http.ApiModule;
import com.huxin.communication.ui.InlandSpotActivity;
import com.huxin.communication.ui.MainActivity;
import com.huxin.communication.ui.ProvincesTravelActivity;
import com.huxin.communication.ui.cammer.HttpUtil;
import com.huxin.communication.ui.cammer.ImagePickerAdapter;
import com.huxin.communication.ui.cammer.MyStringCallBack;
import com.huxin.communication.ui.cammer.SelectDialog;
import com.huxin.communication.utils.NumberUtils;
import com.huxin.communication.utils.PreferenceUtil;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.sky.kylog.KyLog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.IllegalFormatCodePointException;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

public class ReleaseZhouBoundaryActivity extends BaseActivity implements View.OnClickListener, ImagePickerAdapter.OnRecyclerViewItemClickListener {
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

    private CheckBox mImageViewShuaiWei;
    private CheckBox mImageViewCaiXian;

    private CheckBox mImageViewStickNew;
    private CheckBox mImageViewStickLow;
    private CheckBox mImageViewStickBetter;
    private CheckBox mImageViewStickThrow;
    private CheckBox mImageViewStickRate;
    private CheckBox mImageViewStickReturn;
    private CheckBox mImageViewStickHot;
    private CheckBox mImageViewStickZeroC;
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
    private int id = 0;
    private AroundTravelEntity.ListBean listBean;


    private ImageView mRelativeLayoutStickZeroC;
    private ImageView mRelativeLayoutStickReturn;
    private ImageView mRelativeLayoutStickHot;
    private ImageView mRelativeLayoutStickThrow;
    private ImageView mRelativeLayoutStickBetter;
    private ImageView mRelativeLayoutStickLow;
    private ImageView mRelativeLayoutStickNew;
    private ImageView mRelativeLayoutStickRate;


    private TextView mTextViewTopMessage;

    private TableTravelTrafficAdapter mAdapterTableName;
    private TableTravelActivityAdapter mAdapterAtivityTableName;
    private TableTravelAddressListAdapter mAdapterAddressTableName;
    private TableTravelConsAdapter mAdapterConsTableName;
    private TableTravelOtherAdapter mAdapterOtherTableName;
    private TableTravelStayAdapter mAdapterStayTableName;
    private List<String> Tablist = new ArrayList<>();


    private String TotalPrice;
    private String FinalPrice;
    private String ReturnPrice;
    private String TotalPriceChild;
    private String finalPriceChild;
    private String ReturnPriceChild;

    private boolean isCaiXian = true;
    private boolean isShuaiWei = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_release_zhou_boundary);
        type = getIntent().getStringExtra("type");
        id = getIntent().getIntExtra("id", 0);
        listBean = getIntent().getParcelableExtra("list");

    }

    @Override
    protected void initViews() {
        if (id == 0) {
            setToolbarCenterMode("发布周边游线路", MODE_BACK);
        } else {
            setToolbarCenterMode("编辑周边游线路", MODE_BACK);

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

        mImageViewShuaiWei = findViewById(R.id.lineOrThrow_shuaiWei);
//        mImageViewShuaiWeiClick =  findViewById(R.id.lineOrThrow_shuaiWei_click);

        mImageViewCaiXian = findViewById(R.id.lineOrThrow_caixian);
//        getmImageViewCaiXianClick = (ImageView) findViewById(R.id.lineOrThrow_caixian_click);

        mImageViewStickNew = findViewById(R.id.stick_new);
//        mImageViewStickNewClick = (ImageView) findViewById(R.id.stick_new_click);

        mImageViewStickLow = findViewById(R.id.stick_low);
//        mImageViewStickLowClick = (ImageView) findViewById(R.id.stick_low_click);

        mImageViewStickBetter = findViewById(R.id.stick_better);
//        mImageViewStickBetterClick = (ImageView) findViewById(R.id.stick_better_click);

        mImageViewStickThrow = findViewById(R.id.stick_throw);
//        mImageViewStickThrowClick = (ImageView) findViewById(R.id.stick_throw_click);

        mImageViewStickRate = findViewById(R.id.stick_rate);
//        mImageViewStickRateClick = (ImageView) findViewById(R.id.stick_rate_click);

        mImageViewStickReturn = findViewById(R.id.stick_return);
//        mImageViewStickReturnClick = (ImageView) findViewById(R.id.stick_return_click);


        mImageViewStickHot = findViewById(R.id.stick_hot);
//        mImageViewStickHotClick = (ImageView) findViewById(R.id.stick_hot_click);

        mImageViewStickZeroC = findViewById(R.id.stick_zeroC);
//        mImageViewStickZeroClick = (ImageView) findViewById(R.id.stick_zeroC_click);

        mTextViewConfirm = (TextView) findViewById(R.id.confirm);

        mRecyclerViewAddPicture = (RecyclerView) findViewById(R.id.recyclerView);

        mTextViewTopMessage = findViewById(R.id.top_message);

        mRelativeLayoutStickNew = findViewById(R.id.rl_stick_new);
        mRelativeLayoutStickBetter = findViewById(R.id.rl_stick_better);
        mRelativeLayoutStickLow = findViewById(R.id.rl_stick_low);
        mRelativeLayoutStickRate = findViewById(R.id.rl_stick_rate);
        mRelativeLayoutStickReturn = findViewById(R.id.rl_stick_return);
        mRelativeLayoutStickHot = findViewById(R.id.rl_stick_hot);
        mRelativeLayoutStickThrow = findViewById(R.id.rl_stick_throw);
        mRelativeLayoutStickZeroC = findViewById(R.id.rl_stick_zeroC);


//        mImageViewShuaiWei.setOnClickListener(this);
//        mImageViewShuaiWeiClick.setOnClickListener(this);
//        mImageViewCaiXian.setOnClickListener(this);
//        getmImageViewCaiXianClick.setOnClickListener(this);
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

        getUseInfo();
        selectTravelTab();
        deteledData();
        httpUtil = new HttpUtil();
        selImageList = new ArrayList<>();
        adapter = new ImagePickerTravelAdapter(this, selImageList, maxImgCount);
        adapter.setOnItemClickListener(this);
        mTextViewTopMessage.setText("置顶信息剩余" + PreferenceUtil.getInt(Constanst.TOP_ZHIDING) + "条");

        mRecyclerViewAddPicture.setLayoutManager(new GridLayoutManager(this, 4));
        mRecyclerViewAddPicture.setHasFixedSize(true);
        mRecyclerViewAddPicture.setAdapter(adapter);
//        SetEnabled();

        if (id != 0) {
            setData();
        }

        setEditText();

        setListener();


    }

    private void deteledData() {

        if (!TextUtils.isEmpty(PreferenceUtil.getString(Constanst.CITY_MUDI_TRAVEL_NAME))) {
            PreferenceUtil.removeSp(Constanst.CITY_MUDI_TRAVEL_NAME, Constanst.SP_NAME);

        }

        if (!TextUtils.isEmpty(PreferenceUtil.getString(Constanst.PROVINCE_MUDI_TRAVEL_NAME))) {
            PreferenceUtil.removeSp(Constanst.PROVINCE_MUDI_TRAVEL_NAME, Constanst.SP_NAME);

        }
        if (!TextUtils.isEmpty(PreferenceUtil.getString(Constanst.CITY_TRAVEL_NAME))) {
            PreferenceUtil.removeSp(Constanst.CITY_TRAVEL_NAME, Constanst.SP_NAME);
        }

        if (!TextUtils.isEmpty(PreferenceUtil.getString(Constanst.SPOT_NAME))) {
            PreferenceUtil.removeSp(Constanst.SPOT_NAME, Constanst.SP_NAME);
        }
    }



    private void setListener(){
        mImageViewShuaiWei.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    mImageViewCaiXian.setChecked(false);
                    caixian = 2;
                }
            }
        });

        mImageViewCaiXian.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    mImageViewShuaiWei.setChecked(false);
                    caixian = 1;
                }
            }
        });
        mImageViewStickNew.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (PreferenceUtil.getInt(Constanst.TOP_ZHIDING) > 0) {

                    if (b) {

                        mImageViewStickLow.setChecked(false);
                        mImageViewStickHot.setChecked(false);
                        mImageViewStickThrow.setChecked(false);
                        mImageViewStickRate.setChecked(false);
                        mImageViewStickReturn.setChecked(false);
                        mImageViewStickZeroC.setChecked(false);
                        mImageViewStickBetter.setChecked(false);
                        better = 0;
                        news = 1;
                        low = 0;
                        hot = 0;
                        shuaiwei = 0;
                        rate = 0;
                        returns = 0;
                        zeroC = 0;
                    }

                } else {
                    mImageViewStickNew.setChecked(false);

                    Toast.makeText(ReleaseZhouBoundaryActivity.this, "当前置顶条数不够，请立即充值", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mImageViewStickLow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (PreferenceUtil.getInt(Constanst.TOP_ZHIDING) > 0) {

                    if (b) {

                        mImageViewStickNew.setChecked(false);
                        mImageViewStickHot.setChecked(false);
                        mImageViewStickThrow.setChecked(false);
                        mImageViewStickRate.setChecked(false);
                        mImageViewStickReturn.setChecked(false);
                        mImageViewStickZeroC.setChecked(false);
                        mImageViewStickBetter.setChecked(false);
                        better = 0;
                        news = 0;
                        low = 1;
                        hot = 0;
                        shuaiwei = 0;
                        rate = 0;
                        returns = 0;
                        zeroC = 0;
                    }

                } else {
                    mImageViewStickLow.setChecked(false);

                    Toast.makeText(ReleaseZhouBoundaryActivity.this, "当前置顶条数不够，请立即充值", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mImageViewStickHot.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                KyLog.d(PreferenceUtil.getInt(Constanst.TOP_ZHIDING) + "");
                if (PreferenceUtil.getInt(Constanst.TOP_ZHIDING) > 0) {
                    if (b) {
                        mImageViewStickNew.setChecked(false);
                        mImageViewStickLow.setChecked(false);
                        mImageViewStickThrow.setChecked(false);
                        mImageViewStickRate.setChecked(false);
                        mImageViewStickReturn.setChecked(false);
                        mImageViewStickZeroC.setChecked(false);
                        mImageViewStickBetter.setChecked(false);
                        better = 0;
                        news = 0;
                        low = 0;
                        hot = 1;
                        shuaiwei = 0;
                        rate = 0;
                        returns = 0;
                        zeroC = 0;
                    }
                } else {
                    mImageViewStickHot.setChecked(false);
                    Toast.makeText(ReleaseZhouBoundaryActivity.this, "当前置顶条数不够，请立即充值", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mImageViewStickThrow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (PreferenceUtil.getInt(Constanst.TOP_ZHIDING) > 0) {

                    if (b) {

                        mImageViewStickNew.setChecked(false);
                        mImageViewStickLow.setChecked(false);
                        mImageViewStickHot.setChecked(false);
                        mImageViewStickRate.setChecked(false);
                        mImageViewStickReturn.setChecked(false);
                        mImageViewStickZeroC.setChecked(false);
                        mImageViewStickBetter.setChecked(false);
                        better = 0;
                        news = 0;
                        low = 0;
                        hot = 0;
                        shuaiwei = 1;
                        rate = 0;
                        returns = 0;
                        zeroC = 0;
                    }

                } else {
                    mImageViewStickThrow.setChecked(false);
                    Toast.makeText(ReleaseZhouBoundaryActivity.this, "当前置顶条数不够，请立即充值", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mImageViewStickRate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (PreferenceUtil.getInt(Constanst.TOP_ZHIDING) > 0) {

                    if (b) {

                        mImageViewStickNew.setChecked(false);
                        mImageViewStickLow.setChecked(false);
                        mImageViewStickHot.setChecked(false);
                        mImageViewStickThrow.setChecked(false);
                        mImageViewStickReturn.setChecked(false);
                        mImageViewStickZeroC.setChecked(false);
                        mImageViewStickBetter.setChecked(false);
                        better = 0;
                        news = 0;
                        low = 0;
                        hot = 0;
                        shuaiwei = 0;
                        rate = 1;
                        returns = 0;
                        zeroC = 0;
                    }

                } else {
                    mImageViewStickRate.setChecked(false);

                    Toast.makeText(ReleaseZhouBoundaryActivity.this, "当前置顶条数不够，请立即充值", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mImageViewStickReturn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (PreferenceUtil.getInt(Constanst.TOP_ZHIDING) > 0) {

                    if (b) {

                        mImageViewStickNew.setChecked(false);
                        mImageViewStickLow.setChecked(false);
                        mImageViewStickHot.setChecked(false);
                        mImageViewStickThrow.setChecked(false);
                        mImageViewStickRate.setChecked(false);
                        mImageViewStickZeroC.setChecked(false);
                        mImageViewStickBetter.setChecked(false);
                        better = 0;
                        news = 0;
                        low = 0;
                        hot = 0;
                        shuaiwei = 0;
                        rate = 0;
                        returns = 1;
                        zeroC = 0;
                    }

                } else {
                    mImageViewStickReturn.setChecked(false);

                    Toast.makeText(ReleaseZhouBoundaryActivity.this, "当前置顶条数不够，请立即充值", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mImageViewStickZeroC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()

        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (PreferenceUtil.getInt(Constanst.TOP_ZHIDING) > 0) {

                    if (b) {

                        mImageViewStickNew.setChecked(false);
                        mImageViewStickLow.setChecked(false);
                        mImageViewStickHot.setChecked(false);
                        mImageViewStickThrow.setChecked(false);
                        mImageViewStickRate.setChecked(false);
                        mImageViewStickReturn.setChecked(false);
                        mImageViewStickBetter.setChecked(false);
                        better = 0;
                        news = 0;
                        low = 0;
                        hot = 0;
                        shuaiwei = 0;
                        rate = 0;
                        returns = 0;
                        zeroC = 1;
                    }

                } else {
                    mImageViewStickZeroC.setChecked(false);

                    Toast.makeText(ReleaseZhouBoundaryActivity.this, "当前置顶条数不够，请立即充值", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mImageViewStickBetter.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()

        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (PreferenceUtil.getInt(Constanst.TOP_ZHIDING) > 0) {

                    if (b) {

                        mImageViewStickNew.setChecked(false);
                        mImageViewStickLow.setChecked(false);
                        mImageViewStickHot.setChecked(false);
                        mImageViewStickThrow.setChecked(false);
                        mImageViewStickRate.setChecked(false);
                        mImageViewStickReturn.setChecked(false);
                        mImageViewStickZeroC.setChecked(false);
                        better = 1;
                        news = 0;
                        low = 0;
                        hot = 0;
                        shuaiwei = 0;
                        rate = 0;
                        returns = 0;
                        zeroC = 0;
                    }

                } else {
                    mImageViewStickBetter.setChecked(false);

                    Toast.makeText(ReleaseZhouBoundaryActivity.this, "当前置顶条数不够，请立即充值", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private TextWatcher watcherTotal;
    private TextWatcher watcherReturn;
    private TextWatcher watcherFinal;
    private TextWatcher watcherTotalChild;
    private TextWatcher watcherReturnChild;
    private TextWatcher watcherFinalChild;


    private void setEditText() {
        watcherTotal = new TextWatcher() {
            private String mBefore;// 用于记录变化前的文字
            private int mCursor;// 用于记录变化时光标的位置

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                mBefore = s.toString();
                mCursor = start;
                KyLog.d("beforeTextChanged: " + s + ", " + mCursor);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                KyLog.d("onTextChanged: " + s);
            }

            @Override
            public void afterTextChanged(Editable s) {
                TotalPrice = mTextViewTotalPrice.getText().toString().trim();
                FinalPrice = mTextViewFinalPrice.getText().toString().trim();
                ReturnPrice = mTextViewReturnPrice.getText().toString().trim();
                if (!TextUtils.isEmpty(TotalPrice) && NumberUtils.isNumeric(TotalPrice)) {
                    if (!TextUtils.isEmpty(FinalPrice) && NumberUtils.isNumeric(FinalPrice)) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mTextViewReturnPrice.removeTextChangedListener(watcherReturn);
                                mTextViewReturnPrice.setText(String.valueOf(Integer.parseInt(TotalPrice) - Integer.parseInt(FinalPrice)));
                                mTextViewReturnPrice.addTextChangedListener(watcherReturn);
                            }
                        });
                        ReturnPrice = String.valueOf(Integer.parseInt(TotalPrice) - Integer.parseInt(FinalPrice));
                        KyLog.d(ReturnPrice);

                    } else if (!TextUtils.isEmpty(ReturnPrice) && NumberUtils.isNumeric(ReturnPrice)) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mTextViewFinalPrice.removeTextChangedListener(watcherFinal);
                                mTextViewFinalPrice.setText(String.valueOf(Integer.parseInt(TotalPrice) - Integer.parseInt(ReturnPrice)));
                                mTextViewFinalPrice.addTextChangedListener(watcherFinal);
                            }
                        });
                        FinalPrice = String.valueOf(Integer.parseInt(TotalPrice) - Integer.parseInt(ReturnPrice));
                        KyLog.d(FinalPrice);
                    }
                }
            }
        };
        mTextViewTotalPrice.addTextChangedListener(watcherTotal);

        watcherReturn = new TextWatcher() {
            private String mBefore;// 用于记录变化前的文字
            private int mCursor;// 用于记录变化时光标的位置

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                mBefore = s.toString();
                mCursor = start;
                KyLog.d("beforeTextChanged: " + s + ", " + mCursor);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                KyLog.d("onTextChanged: " + s);
            }

            @Override
            public void afterTextChanged(Editable s) {
                TotalPrice = mTextViewTotalPrice.getText().toString().trim();
                FinalPrice = mTextViewFinalPrice.getText().toString().trim();
                ReturnPrice = mTextViewReturnPrice.getText().toString().trim();
                if (!TextUtils.isEmpty(ReturnPrice) && NumberUtils.isNumeric(ReturnPrice)) {
                    if (!TextUtils.isEmpty(FinalPrice) && NumberUtils.isNumeric(FinalPrice)) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mTextViewTotalPrice.removeTextChangedListener(watcherTotal);
                                mTextViewTotalPrice.setText(String.valueOf(Integer.parseInt(FinalPrice) + Integer.parseInt(ReturnPrice)));
                                mTextViewTotalPrice.addTextChangedListener(watcherTotal);

                            }
                        });
                        TotalPrice = String.valueOf(Integer.parseInt(FinalPrice) + Integer.parseInt(ReturnPrice));
                        KyLog.d(TotalPrice);
                    } else if (!TextUtils.isEmpty(TotalPrice) && NumberUtils.isNumeric(TotalPrice)) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mTextViewFinalPrice.removeTextChangedListener(watcherFinal);
                                mTextViewFinalPrice.setText(String.valueOf(Integer.parseInt(TotalPrice) - Integer.parseInt(ReturnPrice)));
                                mTextViewFinalPrice.addTextChangedListener(watcherFinal);
                            }
                        });
                        FinalPrice = String.valueOf(Integer.parseInt(TotalPrice) - Integer.parseInt(ReturnPrice));
                        KyLog.d(FinalPrice);

                    }
                }
            }
        };
        mTextViewReturnPrice.addTextChangedListener(watcherReturn);

        watcherFinal = new TextWatcher() {
            private String mBefore;// 用于记录变化前的文字
            private int mCursor;// 用于记录变化时光标的位置

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                mBefore = s.toString();
                mCursor = start;
                KyLog.d("beforeTextChanged: " + s + ", " + mCursor);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                KyLog.d("onTextChanged: " + s);
            }

            @Override
            public void afterTextChanged(Editable s) {
                TotalPrice = mTextViewTotalPrice.getText().toString().trim();
                FinalPrice = mTextViewFinalPrice.getText().toString().trim();
                ReturnPrice = mTextViewReturnPrice.getText().toString().trim();

                if (!TextUtils.isEmpty(FinalPrice) && NumberUtils.isNumeric(FinalPrice)) {
                    if (!TextUtils.isEmpty(TotalPrice) && NumberUtils.isNumeric(TotalPrice)) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mTextViewReturnPrice.removeTextChangedListener(watcherReturn);
                                mTextViewReturnPrice.setText(String.valueOf(Integer.parseInt(TotalPrice) - Integer.parseInt(FinalPrice)));
                                mTextViewReturnPrice.addTextChangedListener(watcherReturn);

                            }
                        });
                        ReturnPrice = String.valueOf(Integer.parseInt(TotalPrice) - Integer.parseInt(FinalPrice));
                        KyLog.d(ReturnPrice);

                    } else if (!TextUtils.isEmpty(ReturnPrice) && NumberUtils.isNumeric(ReturnPrice)) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mTextViewTotalPrice.removeTextChangedListener(watcherTotal);
                                mTextViewTotalPrice.setText(String.valueOf(Integer.parseInt(ReturnPrice) + Integer.parseInt(FinalPrice)));
                                mTextViewTotalPrice.addTextChangedListener(watcherTotal);

                            }
                        });
                        TotalPrice = String.valueOf(Integer.parseInt(ReturnPrice) + Integer.parseInt(FinalPrice));
                        KyLog.d(TotalPrice);
                    }
                }
            }
        };
        mTextViewFinalPrice.addTextChangedListener(watcherFinal);

        watcherTotalChild = new TextWatcher() {
            private String mBefore;// 用于记录变化前的文字
            private int mCursor;// 用于记录变化时光标的位置

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                mBefore = s.toString();
                mCursor = start;
                KyLog.d("beforeTextChanged: " + s + ", " + mCursor);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                KyLog.d("onTextChanged: " + s);
            }

            @Override
            public void afterTextChanged(Editable s) {
                TotalPriceChild = mTextViewTotalPriceChild.getText().toString().trim();
                finalPriceChild = mTextViewFinalPriceChild.getText().toString().trim();
                ReturnPriceChild = mTextViewReturnPriceChild.getText().toString().trim();

                if (!TextUtils.isEmpty(TotalPriceChild) && NumberUtils.isNumeric(TotalPriceChild)) {
                    if (!TextUtils.isEmpty(ReturnPriceChild) && NumberUtils.isNumeric(ReturnPriceChild)) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mTextViewFinalPriceChild.removeTextChangedListener(watcherFinalChild);
                                mTextViewFinalPriceChild.setText(String.valueOf(Integer.parseInt(TotalPriceChild) - Integer.parseInt(ReturnPriceChild)));
                                mTextViewFinalPriceChild.addTextChangedListener(watcherFinalChild);

                            }
                        });
                        finalPriceChild = String.valueOf(Integer.parseInt(TotalPriceChild) - Integer.parseInt(ReturnPriceChild));
                        KyLog.d(finalPriceChild);
                    } else if (!TextUtils.isEmpty(finalPriceChild) && NumberUtils.isNumeric(finalPriceChild)) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mTextViewReturnPriceChild.removeTextChangedListener(watcherReturn);
                                mTextViewReturnPriceChild.setText(String.valueOf(Integer.parseInt(TotalPriceChild) - Integer.parseInt(finalPriceChild)));
                                mTextViewReturnPriceChild.addTextChangedListener(watcherReturn);

                            }
                        });
                        ReturnPriceChild = String.valueOf(Integer.parseInt(TotalPriceChild) - Integer.parseInt(finalPriceChild));
                        KyLog.d(ReturnPriceChild);

                    }
                }
            }
        };
        mTextViewTotalPriceChild.addTextChangedListener(watcherTotalChild);

        watcherReturnChild = new TextWatcher() {
            private String mBefore;// 用于记录变化前的文字
            private int mCursor;// 用于记录变化时光标的位置

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                mBefore = s.toString();
                mCursor = start;
                KyLog.d("beforeTextChanged: " + s + ", " + mCursor);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                KyLog.d("onTextChanged: " + s);
            }

            @Override
            public void afterTextChanged(Editable s) {
                TotalPriceChild = mTextViewTotalPriceChild.getText().toString().trim();
                finalPriceChild = mTextViewFinalPriceChild.getText().toString().trim();
                ReturnPriceChild = mTextViewReturnPriceChild.getText().toString().trim();

                if (!TextUtils.isEmpty(ReturnPriceChild) && NumberUtils.isNumeric(ReturnPriceChild)) {
                    if (!TextUtils.isEmpty(TotalPriceChild) && NumberUtils.isNumeric(TotalPriceChild)) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mTextViewTotalPriceChild.removeTextChangedListener(watcherFinalChild);
                                mTextViewFinalPriceChild.setText(String.valueOf(Integer.parseInt(TotalPriceChild) - Integer.parseInt(ReturnPriceChild)));
                                mTextViewTotalPriceChild.addTextChangedListener(watcherFinalChild);

                            }
                        });
                        finalPriceChild = String.valueOf(Integer.parseInt(TotalPriceChild) - Integer.parseInt(ReturnPriceChild));
                        KyLog.d(finalPriceChild);
                    } else if (!TextUtils.isEmpty(finalPriceChild) && NumberUtils.isNumeric(finalPriceChild)) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mTextViewTotalPriceChild.removeTextChangedListener(watcherTotalChild);
                                mTextViewTotalPriceChild.setText(String.valueOf(Integer.parseInt(ReturnPriceChild) + Integer.parseInt(finalPriceChild)));
                                mTextViewTotalPriceChild.addTextChangedListener(watcherTotalChild);

                            }
                        });
                        TotalPriceChild = String.valueOf(Integer.parseInt(ReturnPriceChild) + Integer.parseInt(finalPriceChild));
                        KyLog.d(TotalPriceChild);

                    }
                }
            }
        };
        mTextViewReturnPriceChild.addTextChangedListener(watcherReturnChild);


        watcherFinalChild = new TextWatcher() {
            private String mBefore;// 用于记录变化前的文字
            private int mCursor;// 用于记录变化时光标的位置

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                mBefore = s.toString();
                mCursor = start;
                KyLog.d("beforeTextChanged: " + s + ", " + mCursor);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                KyLog.d("onTextChanged: " + s);
            }

            @Override
            public void afterTextChanged(Editable s) {
                TotalPriceChild = mTextViewTotalPriceChild.getText().toString().trim();
                finalPriceChild = mTextViewFinalPriceChild.getText().toString().trim();
                ReturnPriceChild = mTextViewReturnPriceChild.getText().toString().trim();

                if (!TextUtils.isEmpty(finalPriceChild) && NumberUtils.isNumeric(finalPriceChild)) {
                    if (!TextUtils.isEmpty(TotalPriceChild) && NumberUtils.isNumeric(TotalPriceChild)) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mTextViewReturnPriceChild.removeTextChangedListener(watcherReturnChild);
                                mTextViewReturnPriceChild.setText(String.valueOf(Integer.parseInt(TotalPriceChild) - Integer.parseInt(finalPriceChild)));
                                mTextViewReturnPriceChild.addTextChangedListener(watcherReturnChild);

                            }
                        });
                        ReturnPriceChild = String.valueOf(Integer.parseInt(TotalPriceChild) - Integer.parseInt(finalPriceChild));
                        KyLog.d(ReturnPriceChild);
                    } else if (!TextUtils.isEmpty(ReturnPriceChild) && NumberUtils.isNumeric(ReturnPriceChild)) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mTextViewTotalPriceChild.removeTextChangedListener(watcherTotalChild);
                                mTextViewTotalPriceChild.setText(String.valueOf(Integer.parseInt(ReturnPriceChild) + Integer.parseInt(finalPriceChild)));
                                mTextViewTotalPriceChild.addTextChangedListener(watcherTotalChild);

                            }
                        });
                        TotalPriceChild = String.valueOf(Integer.parseInt(ReturnPriceChild) + Integer.parseInt(finalPriceChild));
                        KyLog.d(TotalPriceChild);

                    }
                }
            }
        };
        mTextViewFinalPriceChild.addTextChangedListener(watcherFinalChild);
    }


    private void setData() {

        if (listBean != null) {
            mTextViewTotalPrice.setText(String.valueOf(listBean.getTotalPrice()));
            mTextViewFinalPrice.setText(String.valueOf(listBean.getFinalPrice()));
            mTextViewReturnPrice.setText(String.valueOf(listBean.getReturnPrice()));
            mTextViewTotalPriceChild.setText(String.valueOf(listBean.getTotalPriceChild()));
            mTextViewFinalPriceChild.setText(String.valueOf(listBean.getFinalPriceChild()));
            mTextViewReturnPriceChild.setText(String.valueOf(listBean.getReturnPriceChild()));


            if (!TextUtils.isEmpty(listBean.getTravelTitle())) {
                mEditTextTravelTitle.setText(String.valueOf(listBean.getTravelTitle()));

            }

            if (!TextUtils.isEmpty(listBean.getGeneralize())) {
                mEditTextGeneralize.setText(String.valueOf(listBean.getGeneralize()));

            }

            if (listBean.getNumberDays() > 0) {
                mTextViewDayType.setText(String.valueOf(listBean.getNumberDays()));
                day = String.valueOf(listBean.getNumberDays());
            }

            if (!TextUtils.isEmpty(listBean.getGoals_city()) && !TextUtils.isEmpty(listBean.getGoals_city_code())
                    && !TextUtils.isEmpty(listBean.getGoals_pro())) {
                mTextViewMudiType.setText(listBean.getGoals_pro() + "," + listBean.getGoals_city());
                PreferenceUtil.putString(Constanst.CITY_MUDI_TRAVEL_NAME, listBean.getGoals_city());
                PreferenceUtil.putString(Constanst.PROVINCE_MUDI_TRAVEL_NAME, listBean.getGoals_pro());
                PreferenceUtil.putString(Constanst.CITY_MUDI_CODE, listBean.getGoals_pro());

            }

            if (!TextUtils.isEmpty(listBean.getDepart_name()) && !TextUtils.isEmpty(listBean.getDepart_code())
                    && !TextUtils.isEmpty(listBean.getDepart_pro_code())) {
                mTextViewOccupationType.setText(listBean.getDepart_name());
                PreferenceUtil.putString(Constanst.CITY_CODE, listBean.getDepart_code());
                PreferenceUtil.putString(Constanst.PROVINCE_CODE, listBean.getDepart_pro_code());
                PreferenceUtil.putString(Constanst.CITY_TRAVEL_NAME, listBean.getDepart_name());


            }

            if (!TextUtils.isEmpty(listBean.getSpotName()) && !TextUtils.isEmpty(listBean.getSpotName())) {
                mTextViewHotType.setText(listBean.getSpotName());
                PreferenceUtil.putString(Constanst.SPOT_NAME, listBean.getSpotName());
                PreferenceUtil.putString(Constanst.SPOT_ID, listBean.getGoalsId());

            }

            if (listBean.getLineOrThrow() == 0) {
                caixian = 1;

                mImageViewShuaiWei.setChecked(true);
                mImageViewCaiXian.setChecked(false);
            } else {
                mImageViewShuaiWei.setChecked(false);
                mImageViewCaiXian.setChecked(true);
                caixian = 2;
            }

            if (listBean.getStick_better() == 1) {
                mImageViewStickNew.setChecked(false);
                mImageViewStickLow.setChecked(false);
                mImageViewStickHot.setChecked(false);
                mImageViewStickThrow.setChecked(false);
                mImageViewStickRate.setChecked(false);
                mImageViewStickReturn.setChecked(false);
                mImageViewStickZeroC.setChecked(false);
                mImageViewStickBetter.setChecked(true);
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
                mImageViewStickNew.setChecked(false);
                mImageViewStickLow.setChecked(false);
                mImageViewStickHot.setChecked(true);
                mImageViewStickThrow.setChecked(false);
                mImageViewStickRate.setChecked(false);
                mImageViewStickReturn.setChecked(false);
                mImageViewStickZeroC.setChecked(false);
                mImageViewStickBetter.setChecked(false);
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
                mImageViewStickNew.setChecked(false);
                mImageViewStickLow.setChecked(true);
                mImageViewStickHot.setChecked(false);
                mImageViewStickThrow.setChecked(false);
                mImageViewStickRate.setChecked(false);
                mImageViewStickReturn.setChecked(false);
                mImageViewStickZeroC.setChecked(false);
                mImageViewStickBetter.setChecked(false);
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
                mImageViewStickNew.setChecked(true);
                mImageViewStickLow.setChecked(false);
                mImageViewStickHot.setChecked(false);
                mImageViewStickThrow.setChecked(false);
                mImageViewStickRate.setChecked(false);
                mImageViewStickReturn.setChecked(false);
                mImageViewStickZeroC.setChecked(false);
                mImageViewStickBetter.setChecked(false);
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
                mImageViewStickNew.setChecked(false);
                mImageViewStickLow.setChecked(false);
                mImageViewStickHot.setChecked(false);
                mImageViewStickThrow.setChecked(false);
                mImageViewStickRate.setChecked(true);
                mImageViewStickReturn.setChecked(false);
                mImageViewStickZeroC.setChecked(false);
                mImageViewStickBetter.setChecked(false);
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
                mImageViewStickNew.setChecked(false);
                mImageViewStickLow.setChecked(false);
                mImageViewStickHot.setChecked(false);
                mImageViewStickThrow.setChecked(true);
                mImageViewStickRate.setChecked(false);
                mImageViewStickReturn.setChecked(false);
                mImageViewStickZeroC.setChecked(false);
                mImageViewStickBetter.setChecked(false);
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
                mImageViewStickNew.setChecked(false);
                mImageViewStickLow.setChecked(false);
                mImageViewStickHot.setChecked(false);
                mImageViewStickThrow.setChecked(false);
                mImageViewStickRate.setChecked(false);
                mImageViewStickReturn.setChecked(true);
                mImageViewStickZeroC.setChecked(false);
                mImageViewStickBetter.setChecked(false);
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
                mImageViewStickNew.setChecked(false);
                mImageViewStickLow.setChecked(false);
                mImageViewStickHot.setChecked(false);
                mImageViewStickThrow.setChecked(false);
                mImageViewStickRate.setChecked(false);
                mImageViewStickReturn.setChecked(false);
                mImageViewStickZeroC.setChecked(true);
                mImageViewStickBetter.setChecked(false);
                better = 0;
                news = 0;
                low = 0;
                hot = 0;
                shuaiwei = 0;
                rate = 0;
                returns = 0;
                zeroC = 1;
            }

            if (listBean.getPickupPrice() == 0) {
                mTextViewRelease1Type.setText("有周边接送费");
                pickupPrice = "有周边接送费";
            } else {
                mTextViewRelease1Type.setText("无接送费");
                pickupPrice = "无接送费";

            }

            if (!TextUtils.isEmpty(listBean.getTOtherId())) {
                setTabData(listBean.getTOtherId());
                PreferenceUtil.putString(Constanst.TAB_NMAE_OTHER, listBean.getTOtherId());
            }

            if (!TextUtils.isEmpty(listBean.getTActivityId())) {
                setTabData(listBean.getTActivityId());
                PreferenceUtil.putString(Constanst.TAB_NMAE_ACTIVITY, listBean.getTActivityId());
            }

            if (!TextUtils.isEmpty(listBean.getTAddressId())) {
                setTabData(listBean.getTAddressId());
                PreferenceUtil.putString(Constanst.TAB_NMAE_ADDRESS, listBean.getTAddressId());
            }

            if (!TextUtils.isEmpty(listBean.getTConsumeId())) {
                setTabData(listBean.getTConsumeId());
                PreferenceUtil.putString(Constanst.TAB_NMAE_CONS, listBean.getTConsumeId());
            }

            if (!TextUtils.isEmpty(listBean.getTStayId())) {
                setTabData(listBean.getTStayId());
                PreferenceUtil.putString(Constanst.TAB_NMAE_STAY, listBean.getTStayId());
            }

            if (!TextUtils.isEmpty(listBean.getTTrafficId())) {
                setTabData(listBean.getTTrafficId());
                PreferenceUtil.putString(Constanst.TAB_NMAE_TRAFFIC, listBean.getTTrafficId());
            }
//            KyLog.d(listBean.getPhoto_url());
//            String[] str = listBean.getPhoto_url().split(",");
//            for (int i = 0; i < str.length; i++) {
//                File file = new File(Environment.getExternalStorageDirectory(), str[i]);
//                if (!file.exists() || !file.isDirectory()) {
//                    file.mkdirs();
//                }
//                int finalI = i;
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            saveFile(loadRmoteImage(str[finalI]), Environment.getExternalStorageDirectory().getPath() + "image" + finalI + ".png");
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//
//
//                    }
//                }).start();
//            }
//            adapter.setImages(selImageList);
//            KyLog.d(selImageList.size() + "tab");
        }


    }

    public void setTabData(String tabName) {
        String[] str = tabName.split(",");
        for (int i = 0; i < str.length; i++) {
            Tablist.add(str[i]);
        }

        KyLog.d(Tablist.size() + "tab");
    }


    @Override
    protected void onResume() {
        super.onResume();
        setViewData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.lineOrThrow_shuaiWei:
//                if (isShuaiWei){
//                    isShuaiWei = false;
//                    isCaiXian = true;
//                    mImageViewShuaiWei.setBackgroundResource(R.drawable.icon_circle_selected);
//                    mImageViewCaiXian.setBackgroundResource(R.drawable.icon_circle_normal);
//                    caixian = 2;
//                }else {
//                    isShuaiWei = true;
//                    isCaiXian = false;
//                    mImageViewShuaiWei.setBackgroundResource(R.drawable.icon_circle_normal);
//                    mImageViewCaiXian.setBackgroundResource(R.drawable.icon_circle_normal);
//                    caixian = 0;
//                }
//
//                break;
//            case R.id.lineOrThrow_caixian:
//                if (isCaiXian){
//                    isCaiXian = false;
//                    isShuaiWei = true;
//                    mImageViewShuaiWei.setBackgroundResource(R.drawable.icon_circle_normal);
//                    mImageViewCaiXian.setBackgroundResource(R.drawable.icon_circle_selected);
//                    caixian = 1;
//                }else {
//                    isCaiXian = true;
//                    isShuaiWei = false;
//                    mImageViewShuaiWei.setBackgroundResource(R.drawable.icon_circle_normal);
//                    mImageViewCaiXian.setBackgroundResource(R.drawable.icon_circle_normal);
//                    caixian = 0;
//
//                }
//
//                break;
//            case R.id.rl_stick_better:
//                if (PreferenceUtil.getInt(Constanst.TOP_ZHIDING) > 0) {
//                    mImageViewStickNew.setBackgroundResource(R.drawable.icon_circle_normal);
//                    mImageViewStickLow.setBackgroundResource(R.drawable.icon_circle_normal);
//                    mImageViewStickHot.setBackgroundResource(R.drawable.icon_circle_normal);
//                    mImageViewStickThrow.setBackgroundResource(R.drawable.icon_circle_normal);
//                    mImageViewStickRate.setBackgroundResource(R.drawable.icon_circle_normal);
//                    mImageViewStickReturn.setBackgroundResource(R.drawable.icon_circle_normal);
//                    mImageViewStickZeroC.setBackgroundResource(R.drawable.icon_circle_normal);
//                    mImageViewStickBetter.setBackgroundResource(R.drawable.icon_circle_selected);
//                    better = 1;
//
//                    news = 0;
//                    low = 0;
//                    hot = 0;
//                    shuaiwei = 0;
//                    rate = 0;
//                    returns = 0;
//                    zeroC = 0;
//                } else {
//                    Toast.makeText(this, "当前置顶条数不够，请立即充值", Toast.LENGTH_SHORT).show();
//                }
//                break;
//            case R.id.rl_stick_hot:
//                if (PreferenceUtil.getInt(Constanst.TOP_ZHIDING) > 0) {
//                    mImageViewStickNew.setBackgroundResource(R.drawable.icon_circle_normal);
//                    mImageViewStickLow.setBackgroundResource(R.drawable.icon_circle_normal);
//                    mImageViewStickHot.setBackgroundResource(R.drawable.icon_circle_selected);
//                    mImageViewStickThrow.setBackgroundResource(R.drawable.icon_circle_normal);
//                    mImageViewStickRate.setBackgroundResource(R.drawable.icon_circle_normal);
//                    mImageViewStickReturn.setBackgroundResource(R.drawable.icon_circle_normal);
//                    mImageViewStickZeroC.setBackgroundResource(R.drawable.icon_circle_normal);
//                    mImageViewStickBetter.setBackgroundResource(R.drawable.icon_circle_normal);
//                    better = 0;
//                    news = 0;
//                    low = 0;
//                    hot = 1;
//                    shuaiwei = 0;
//                    rate = 0;
//                    returns = 0;
//                    zeroC = 0;
//                } else {
//                    Toast.makeText(this, "当前置顶条数不够，请立即充值", Toast.LENGTH_SHORT).show();
//                }
//                break;
//
//
//            case R.id.rl_stick_new:
//                if (PreferenceUtil.getInt(Constanst.TOP_ZHIDING) > 0) {
//
//                    mImageViewStickNew.setBackgroundResource(R.drawable.icon_circle_selected);
//                    mImageViewStickLow.setBackgroundResource(R.drawable.icon_circle_normal);
//                    mImageViewStickHot.setBackgroundResource(R.drawable.icon_circle_normal);
//                    mImageViewStickThrow.setBackgroundResource(R.drawable.icon_circle_normal);
//                    mImageViewStickRate.setBackgroundResource(R.drawable.icon_circle_normal);
//                    mImageViewStickReturn.setBackgroundResource(R.drawable.icon_circle_normal);
//                    mImageViewStickZeroC.setBackgroundResource(R.drawable.icon_circle_normal);
//                    mImageViewStickBetter.setBackgroundResource(R.drawable.icon_circle_normal);
//                    better = 0;
//                    news = 1;
//                    low = 0;
//                    hot = 0;
//                    shuaiwei = 0;
//                    rate = 0;
//                    returns = 0;
//                    zeroC = 0;
//                } else {
//                    Toast.makeText(this, "当前置顶条数不够，请立即充值", Toast.LENGTH_SHORT).show();
//                }
//                break;
//
//            case R.id.rl_stick_low:
//                if (PreferenceUtil.getInt(Constanst.TOP_ZHIDING) > 0) {
//
//                    mImageViewStickNew.setBackgroundResource(R.drawable.icon_circle_normal);
//                    mImageViewStickLow.setBackgroundResource(R.drawable.icon_circle_selected);
//                    mImageViewStickHot.setBackgroundResource(R.drawable.icon_circle_normal);
//                    mImageViewStickThrow.setBackgroundResource(R.drawable.icon_circle_normal);
//                    mImageViewStickRate.setBackgroundResource(R.drawable.icon_circle_normal);
//                    mImageViewStickReturn.setBackgroundResource(R.drawable.icon_circle_normal);
//                    mImageViewStickZeroC.setBackgroundResource(R.drawable.icon_circle_normal);
//                    mImageViewStickBetter.setBackgroundResource(R.drawable.icon_circle_normal);
//                    better = 0;
//                    news = 0;
//                    low = 1;
//                    hot = 0;
//                    shuaiwei = 0;
//                    rate = 0;
//                    returns = 0;
//                    zeroC = 0;
//                } else {
//                    Toast.makeText(this, "当前置顶条数不够，请立即充值", Toast.LENGTH_SHORT).show();
//                }
//                break;
//
//            case R.id.rl_stick_throw:
//                if (PreferenceUtil.getInt(Constanst.TOP_ZHIDING) > 0) {
//
//                    mImageViewStickNew.setBackgroundResource(R.drawable.icon_circle_normal);
//                    mImageViewStickLow.setBackgroundResource(R.drawable.icon_circle_normal);
//                    mImageViewStickHot.setBackgroundResource(R.drawable.icon_circle_normal);
//                    mImageViewStickThrow.setBackgroundResource(R.drawable.icon_circle_selected);
//                    mImageViewStickRate.setBackgroundResource(R.drawable.icon_circle_normal);
//                    mImageViewStickReturn.setBackgroundResource(R.drawable.icon_circle_normal);
//                    mImageViewStickZeroC.setBackgroundResource(R.drawable.icon_circle_normal);
//                    mImageViewStickBetter.setBackgroundResource(R.drawable.icon_circle_normal);
//                    better = 0;
//                    news = 0;
//                    low = 0;
//                    hot = 0;
//                    shuaiwei = 1;
//                    rate = 0;
//                    returns = 0;
//                    zeroC = 0;
//                } else {
//                    Toast.makeText(this, "当前置顶条数不够，请立即充值", Toast.LENGTH_SHORT).show();
//                }
//                break;
//
//            case R.id.rl_stick_rate:
//                if (PreferenceUtil.getInt(Constanst.TOP_ZHIDING) > 0) {
//
//                    mImageViewStickNew.setBackgroundResource(R.drawable.icon_circle_normal);
//                    mImageViewStickLow.setBackgroundResource(R.drawable.icon_circle_normal);
//                    mImageViewStickHot.setBackgroundResource(R.drawable.icon_circle_normal);
//                    mImageViewStickThrow.setBackgroundResource(R.drawable.icon_circle_normal);
//                    mImageViewStickRate.setBackgroundResource(R.drawable.icon_circle_selected);
//                    mImageViewStickReturn.setBackgroundResource(R.drawable.icon_circle_normal);
//                    mImageViewStickZeroC.setBackgroundResource(R.drawable.icon_circle_normal);
//                    mImageViewStickBetter.setBackgroundResource(R.drawable.icon_circle_normal);
//                    better = 0;
//                    news = 0;
//                    low = 0;
//                    hot = 0;
//                    shuaiwei = 0;
//                    rate = 1;
//                    returns = 0;
//                    zeroC = 0;
//                } else {
//                    Toast.makeText(this, "当前置顶条数不够，请立即充值", Toast.LENGTH_SHORT).show();
//                }
//                break;
//
//            case R.id.rl_stick_return:
//                if (PreferenceUtil.getInt(Constanst.TOP_ZHIDING) > 0) {
//
//                    mImageViewStickNew.setBackgroundResource(R.drawable.icon_circle_normal);
//                    mImageViewStickLow.setBackgroundResource(R.drawable.icon_circle_normal);
//                    mImageViewStickHot.setBackgroundResource(R.drawable.icon_circle_normal);
//                    mImageViewStickThrow.setBackgroundResource(R.drawable.icon_circle_normal);
//                    mImageViewStickRate.setBackgroundResource(R.drawable.icon_circle_normal);
//                    mImageViewStickReturn.setBackgroundResource(R.drawable.icon_circle_selected);
//                    mImageViewStickZeroC.setBackgroundResource(R.drawable.icon_circle_normal);
//                    mImageViewStickBetter.setBackgroundResource(R.drawable.icon_circle_normal);
//                    better = 0;
//                    news = 0;
//                    low = 0;
//                    hot = 0;
//                    shuaiwei = 0;
//                    rate = 0;
//                    returns = 1;
//                    zeroC = 0;
//                } else {
//                    Toast.makeText(this, "当前置顶条数不够，请立即充值", Toast.LENGTH_SHORT).show();
//                }
//                break;
//
//            case R.id.rl_stick_zeroC:
//                if (PreferenceUtil.getInt(Constanst.TOP_ZHIDING) > 0) {
//
//                    mImageViewStickNew.setBackgroundResource(R.drawable.icon_circle_normal);
//                    mImageViewStickLow.setBackgroundResource(R.drawable.icon_circle_normal);
//                    mImageViewStickHot.setBackgroundResource(R.drawable.icon_circle_normal);
//                    mImageViewStickThrow.setBackgroundResource(R.drawable.icon_circle_normal);
//                    mImageViewStickRate.setBackgroundResource(R.drawable.icon_circle_normal);
//                    mImageViewStickReturn.setBackgroundResource(R.drawable.icon_circle_normal);
//                    mImageViewStickZeroC.setBackgroundResource(R.drawable.icon_circle_selected);
//                    mImageViewStickBetter.setBackgroundResource(R.drawable.icon_circle_normal);
//                    better = 0;
//                    news = 0;
//                    low = 0;
//                    hot = 0;
//                    shuaiwei = 0;
//                    rate = 0;
//                    returns = 0;
//                    zeroC = 1;
//                } else {
//                    Toast.makeText(this, "当前置顶条数不够，请立即充值", Toast.LENGTH_SHORT).show();
//                }
//                break;

            case R.id.confirm:
//                issueAroundRoute();
                if (id == 0) {
                    uploadImage(selImageList);
                } else {
                    uploadDataImage(selImageList, id);
                }

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


    private void setActivityData
            (List<TabTravelNameEntity.ActivityListBean> list, RecyclerView recyclerView) {
        if (list.size() > 0) {
            GridLayoutManager manager = new GridLayoutManager(this, 4);
            mAdapterAtivityTableName = new TableTravelActivityAdapter(list, this);
            recyclerView.setAdapter(mAdapterAtivityTableName);
            recyclerView.setLayoutManager(manager);
            if (list.size() > 0) {
                mAdapterAtivityTableName.setTabList(Tablist);
            }
        }
    }

    private void setAddressListData
            (List<TabTravelNameEntity.AddressListBean> list, RecyclerView recyclerView) {
        if (list.size() > 0) {
            GridLayoutManager manager = new GridLayoutManager(this, 4);
            mAdapterAddressTableName = new TableTravelAddressListAdapter(list, this);
            recyclerView.setAdapter(mAdapterAddressTableName);
            recyclerView.setLayoutManager(manager);
            if (list.size() > 0) {
                mAdapterAddressTableName.setTabList(Tablist);
            }
        }
    }

    private void setConsData(List<TabTravelNameEntity.ConsListBean> list, RecyclerView
            recyclerView) {
        if (list.size() > 0) {
            GridLayoutManager manager = new GridLayoutManager(this, 4);
            mAdapterConsTableName = new TableTravelConsAdapter(list, this);
            recyclerView.setAdapter(mAdapterConsTableName);
            recyclerView.setLayoutManager(manager);
            if (list.size() > 0) {
                mAdapterConsTableName.setTabList(Tablist);
            }
        }
    }

    private void setOtherData(List<TabTravelNameEntity.OtherListBean> list, RecyclerView
            recyclerView) {
        if (list.size() > 0) {
            GridLayoutManager manager = new GridLayoutManager(this, 4);
            mAdapterOtherTableName = new TableTravelOtherAdapter(list, this);
            recyclerView.setAdapter(mAdapterOtherTableName);
            recyclerView.setLayoutManager(manager);
            if (list.size() > 0) {
                mAdapterOtherTableName.setTabList(Tablist);
            }
        }
    }

    private void setStayData(List<TabTravelNameEntity.StayListBean> list, RecyclerView
            recyclerView) {
        if (list.size() > 0) {
            GridLayoutManager manager = new GridLayoutManager(this, 4);
            mAdapterStayTableName = new TableTravelStayAdapter(list, this);
            recyclerView.setAdapter(mAdapterStayTableName);
            recyclerView.setLayoutManager(manager);
            if (list.size() > 0) {
                mAdapterStayTableName.setTabList(Tablist);
            }
        }
    }

    private void setTrafficData(List<TabTravelNameEntity.TrafficListBean> list, RecyclerView
            recyclerView) {
        if (list.size() > 0) {
            GridLayoutManager manager = new GridLayoutManager(this, 4);
            mAdapterTableName = new TableTravelTrafficAdapter(list, this);
            recyclerView.setAdapter(mAdapterTableName);
            recyclerView.setLayoutManager(manager);
            if (list.size() > 0) {
                mAdapterTableName.setTabList(Tablist);
            }
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
                        Intent intent = new Intent(ReleaseZhouBoundaryActivity.this, ImageGridActivity.class);
                        intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                        startActivityForResult(intent, REQUEST_CODE_SELECT);
                        break;
                    case 1:
                        //打开选择,本次允许选择的数量
                        ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                        Intent intent1 = new Intent(ReleaseZhouBoundaryActivity.this, ImageGridActivity.class);
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
        KyLog.d(PreferenceUtil.getString(Constanst.CITY_CODE));
        KyLog.d(PreferenceUtil.getString(Constanst.PROVINCE_CODE));

        KyLog.d(PreferenceUtil.getString(Constanst.SPOT_ID));
        KyLog.d(PreferenceUtil.getString(Constanst.SPOT_NAME));
        KyLog.d(PreferenceUtil.getString(Constanst.CITY_MUDI_TRAVEL_NAME));
        KyLog.d(PreferenceUtil.getString(Constanst.PROVINCE_MUDI_TRAVEL_NAME));
        KyLog.d(PreferenceUtil.getString(Constanst.CITY_MUDI_CODE));
        KyLog.d(PreferenceUtil.getString(Constanst.CITY_TRAVEL_NAME));

        if (TextUtils.isEmpty(PreferenceUtil.getString(Constanst.CITY_CODE)) || TextUtils.isEmpty(PreferenceUtil.getString(Constanst.PROVINCE_CODE))
                || TextUtils.isEmpty(PreferenceUtil.getString(Constanst.SPOT_ID)) || TextUtils.isEmpty(PreferenceUtil.getString(Constanst.SPOT_NAME))
                || TextUtils.isEmpty(PreferenceUtil.getString(Constanst.CITY_MUDI_CODE)) || TextUtils.isEmpty(PreferenceUtil.getString(Constanst.CITY_TRAVEL_NAME))
                || TextUtils.isEmpty(PreferenceUtil.getString(Constanst.CITY_MUDI_TRAVEL_NAME)) || TextUtils.isEmpty(PreferenceUtil.getString(Constanst.PROVINCE_MUDI_TRAVEL_NAME))) {

            Toast.makeText(this, "请选择出发地或者目的地或景点", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(day) || TextUtils.isEmpty(TotalPrice) || TextUtils.isEmpty(ReturnPrice) || TextUtils.isEmpty(FinalPrice)) {
            Toast.makeText(this, "请选择天数或填写成人价格", Toast.LENGTH_SHORT).show();
            return;
        }

        if (pathList.size() <= 0) {
            Toast.makeText(this, "请上传图片", Toast.LENGTH_SHORT).show();
            return;
        }


        KyLog.d(low + "");
        KyLog.d(rate + "");
        KyLog.d(returns + "");
        KyLog.d(shuaiwei + "");
        KyLog.d(hot + "");
        KyLog.d(news + "");


        showProgressDialog();

        Map<String, String> map = new HashMap<>();
        map.put("depart_code", PreferenceUtil.getString(Constanst.CITY_CODE));
        map.put("depart_pro_code", PreferenceUtil.getString(Constanst.PROVINCE_CODE));
        map.put("goalsId", PreferenceUtil.getString(Constanst.SPOT_ID));
        map.put("spotName", PreferenceUtil.getString(Constanst.SPOT_NAME));
        map.put("numberDays", day);
        map.put("totalPrice", TotalPrice);
        map.put("finalPrice", FinalPrice);
        map.put("returnPrice", ReturnPrice);
        map.put("pickupPrice", String.valueOf(pickupPrices));

        map.put("totalPriceChild", TotalPriceChild);
        map.put("finalPriceChild", finalPriceChild);
        map.put("returnPriceChild", ReturnPriceChild);
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
        map.put("travelTitle", TravelTitle);
        map.put("generalize", Generalize);
        map.put("stick", String.valueOf(stick));

        map.put("uid", String.valueOf(PreferenceUtil.getInt(UID)));
        if (caixian != 0) {
            map.put("lineOrThrow", String.valueOf(caixian));
        }
        map.put("stick_new", String.valueOf(news));
        map.put("stick_low", String.valueOf(low));
        map.put("stick_better", String.valueOf(better));
        map.put("stick_throw", String.valueOf(shuaiwei));
        map.put("stick_rate", String.valueOf(rate));
        map.put("stick_return", String.valueOf(returns));
        map.put("stick_hot", String.valueOf(hot));
        map.put("stick_zeroC", String.valueOf(zeroC));
        map.put("goals_city", PreferenceUtil.getString(Constanst.CITY_MUDI_TRAVEL_NAME));
        map.put("goals_pro", PreferenceUtil.getString(Constanst.PROVINCE_MUDI_TRAVEL_NAME));
        map.put("goals_city_code", PreferenceUtil.getString(Constanst.CITY_MUDI_CODE));
        map.put("depart_name", PreferenceUtil.getString(Constanst.CITY_TRAVEL_NAME));
        map.put("travel_kind", "1");
        map.put("token", PreferenceUtil.getString(TOKEN));
        map.put("user_idForCol", String.valueOf(PreferenceUtil.getInt(UID)));


        String url = "http://39.105.203.33/jlkf/mutual-trust/travel/issueAroundRoute";
        KyLog.object(map);

        httpUtil.postFileRequest(url, map, pathList, new MyStringCallBack() {

            @Override
            public void onError(Call call, Exception e, int id) {
                super.onError(call, e, id);
                KyLog.d("release === " + e);
                Toast.makeText(ReleaseZhouBoundaryActivity.this, "发布失败", Toast.LENGTH_SHORT).show();
                cancelProgressDialog();
            }

            @Override
            public void onResponse(String response, int id) {
                super.onResponse(response, id);
                //返回图片的地址
                cancelProgressDialog();
                getUseInfo();
                KyLog.d("release == " + response);
                Toast.makeText(ReleaseZhouBoundaryActivity.this, "发布成功", Toast.LENGTH_SHORT).show();
                if (TextUtils.isEmpty(type)) {
                    Intent intent = new Intent(ReleaseZhouBoundaryActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    try {
                        JSONObject res = new JSONObject();

                        JSONArray array = new JSONArray();
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("type", 2);
                        jsonObject.put("travelType", 2);
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
     * 上传图片
     *
     * @param pathList
     */
    private void uploadDataImage(ArrayList<ImageItem> pathList, int id) {

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
        KyLog.d(PreferenceUtil.getString(Constanst.CITY_CODE));
        KyLog.d(PreferenceUtil.getString(Constanst.PROVINCE_CODE));

        KyLog.d(PreferenceUtil.getString(Constanst.SPOT_ID));
        KyLog.d(PreferenceUtil.getString(Constanst.SPOT_NAME));
        KyLog.d(PreferenceUtil.getString(Constanst.CITY_MUDI_TRAVEL_NAME));
        KyLog.d(PreferenceUtil.getString(Constanst.PROVINCE_MUDI_TRAVEL_NAME));
        KyLog.d(PreferenceUtil.getString(Constanst.CITY_MUDI_CODE));
        KyLog.d(PreferenceUtil.getString(Constanst.CITY_TRAVEL_NAME));

        if (TextUtils.isEmpty(PreferenceUtil.getString(Constanst.CITY_CODE)) && TextUtils.isEmpty(PreferenceUtil.getString(Constanst.PROVINCE_CODE))
                && TextUtils.isEmpty(PreferenceUtil.getString(Constanst.SPOT_ID)) && TextUtils.isEmpty(PreferenceUtil.getString(Constanst.SPOT_NAME))
                && TextUtils.isEmpty(PreferenceUtil.getString(Constanst.CITY_MUDI_CODE)) && TextUtils.isEmpty(PreferenceUtil.getString(Constanst.CITY_TRAVEL_NAME))
                && TextUtils.isEmpty(PreferenceUtil.getString(Constanst.CITY_MUDI_TRAVEL_NAME)) && TextUtils.isEmpty(PreferenceUtil.getString(Constanst.PROVINCE_MUDI_TRAVEL_NAME))) {

            Toast.makeText(this, "请选择出发地或者目的地或景点", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(day) && TextUtils.isEmpty(TotalPrice) && TextUtils.isEmpty(ReturnPrice) && TextUtils.isEmpty(FinalPrice)) {
            Toast.makeText(this, "请选择天数或填写成人价格", Toast.LENGTH_SHORT).show();
            return;
        }

        if (pathList.size() <= 0) {
            Toast.makeText(this, "请上传图片", Toast.LENGTH_SHORT).show();
            return;
        }


        KyLog.d(low + "");
        KyLog.d(rate + "");
        KyLog.d(returns + "");
        KyLog.d(shuaiwei + "");
        KyLog.d(hot + "");
        KyLog.d(news + "");

        showProgressDialog();

        Map<String, String> map = new HashMap<>();
        if (TextUtils.isEmpty(listBean.getDepart_code())) {
            map.put("depart_code", PreferenceUtil.getString(Constanst.CITY_CODE));
        } else {
            map.put("depart_code", listBean.getDepart_code());
        }
        if (TextUtils.isEmpty(listBean.getDepart_pro_code())) {
            map.put("depart_pro_code", PreferenceUtil.getString(Constanst.PROVINCE_CODE));
        } else {
            map.put("depart_pro_code", listBean.getDepart_pro_code());
        }

        if (TextUtils.isEmpty(listBean.getGoalsId())) {
            map.put("goalsId", PreferenceUtil.getString(Constanst.SPOT_ID));

        } else {
            map.put("goalsId", listBean.getGoalsId());
        }

        if (TextUtils.isEmpty(listBean.getSpotName())) {
            map.put("spotName", PreferenceUtil.getString(Constanst.SPOT_NAME));
        } else {
            map.put("spotName", listBean.getSpotName());
        }

        map.put("numberDays", day);
        map.put("totalPrice", TotalPrice);
        map.put("finalPrice", FinalPrice);
        map.put("returnPrice", ReturnPrice);
        map.put("pickupPrice", String.valueOf(pickupPrices));

        map.put("totalPriceChild", TotalPriceChild);
        map.put("finalPriceChild", finalPriceChild);
        map.put("returnPriceChild", ReturnPriceChild);
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
        map.put("travelTitle", TravelTitle);
        map.put("generalize", Generalize);
        map.put("stick", String.valueOf(stick));

        map.put("uid", String.valueOf(PreferenceUtil.getInt(UID)));
        map.put("lineOrThrow", String.valueOf(caixian));
        map.put("stick_new", String.valueOf(news));
        map.put("stick_low", String.valueOf(low));
        map.put("stick_better", String.valueOf(better));
        map.put("stick_throw", String.valueOf(shuaiwei));
        map.put("stick_rate", String.valueOf(rate));
        map.put("stick_return", String.valueOf(returns));
        map.put("stick_hot", String.valueOf(hot));
        map.put("stick_zeroC", String.valueOf(zeroC));

        if (TextUtils.isEmpty(listBean.getGoals_city())) {
            map.put("goals_city", PreferenceUtil.getString(Constanst.CITY_MUDI_TRAVEL_NAME));

        } else {
            map.put("goals_city", listBean.getGoals_city());
        }

        if (TextUtils.isEmpty(listBean.getGoals_pro())) {
            map.put("goals_pro", PreferenceUtil.getString(Constanst.PROVINCE_MUDI_TRAVEL_NAME));

        } else {
            map.put("goals_pro", listBean.getGoals_pro());
        }

        if (TextUtils.isEmpty(listBean.getGoals_city_code())) {
            map.put("goals_city_code", PreferenceUtil.getString(Constanst.CITY_MUDI_CODE));

        } else {
            map.put("goals_city_code", listBean.getGoals_city_code());
        }

        if (TextUtils.isEmpty(listBean.getDepart_name())) {
            map.put("depart_name", PreferenceUtil.getString(Constanst.CITY_TRAVEL_NAME));
        } else {
            map.put("depart_name", listBean.getDepart_name());
        }
        map.put("id", String.valueOf(id));
        map.put("token", PreferenceUtil.getString(TOKEN));
//        map.put("user_idForCol", String.valueOf(PreferenceUtil.getInt(UID)));


        String url = "http://39.105.203.33/jlkf/mutual-trust/travel/updatePersonageTravel";
        KyLog.object(map);

        httpUtil.postFileRequest(url, map, pathList, new MyStringCallBack() {

            @Override
            public void onError(Call call, Exception e, int id) {
                super.onError(call, e, id);
                KyLog.d("release === " + e);
                Toast.makeText(ReleaseZhouBoundaryActivity.this, "修改失败", Toast.LENGTH_SHORT).show();
                cancelProgressDialog();
            }

            @Override
            public void onResponse(String response, int id) {
                super.onResponse(response, id);
                //返回图片的地址
                getUseInfo();
                cancelProgressDialog();
                KyLog.d("release == " + response);
                Toast.makeText(ReleaseZhouBoundaryActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ReleaseZhouBoundaryActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }


    private SelectDialog showDialog(SelectDialog.SelectDialogListener
                                            listener, List<String> names) {
        SelectDialog dialog = new SelectDialog(this, R.style.transparentFrameWindowStyle, listener, names);
        if (!this.isFinishing()) {
            dialog.show();
        }
        return dialog;
    }


    private void getUseInfo() {
        ApiModule.getInstance().getUserInfo(String.valueOf(PreferenceUtil.getInt(UID)))
                .subscribe(loginEntity -> {
                    KyLog.object(loginEntity.getStickNumber());
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

    /**
     * @param imgUrl 远程图片文件的URL
     *               <p>
     *               下载远程图片
     */
    private Bitmap loadRmoteImage(String imgUrl) {
        KyLog.d("loadRmoteImage == ");
        Bitmap bitmap = null;
        URL url = null;
        try {
            url = new URL(imgUrl);
            InputStream is = null;
            BufferedInputStream bis = null;
            try {
                is = url.openConnection().getInputStream();
                bis = new BufferedInputStream(is);
                bitmap = BitmapFactory.decodeStream(bis);
                KyLog.d(bitmap + " === loadRmoteImage");
                return bitmap;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
//        image.setImageBitmap(bitmap);
    }

    private String SAVE_PIC_PATH = Environment.getExternalStorageState().equalsIgnoreCase(Environment.MEDIA_MOUNTED)
            ? Environment.getExternalStorageDirectory().getAbsolutePath() : "/mnt/sdcard";//

    private String SAVE_REAL_PATH = SAVE_PIC_PATH + "/good/savePic";//保存的确

    /**
     * 保存位图到本地
     *
     * @return void
     */
    //保存方法
    private void saveFile(Bitmap bm, String fileName) throws IOException {
        String subForder = SAVE_REAL_PATH;
        File foder = new File(subForder);
        if (!foder.exists()) foder.mkdirs();

        File myCaptureFile = new File(subForder, fileName);
        KyLog.e("lgq", "图片保持。。。。wwww。。。。" + myCaptureFile);
//        ends = myCaptureFile.getPath();
        if (!myCaptureFile.exists()) myCaptureFile.createNewFile();
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
        bm.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        bos.flush();
        bos.close();
    }


}
