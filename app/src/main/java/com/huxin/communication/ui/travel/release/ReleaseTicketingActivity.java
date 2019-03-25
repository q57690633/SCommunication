package com.huxin.communication.ui.travel.release;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aitangba.pickdatetime.DatePickDialog;
import com.aitangba.pickdatetime.OnSureListener;
import com.aitangba.pickdatetime.bean.DateParams;
import com.huxin.communication.R;
import com.huxin.communication.adpter.ImagePickerTravelAdapter;
import com.huxin.communication.adpter.TableTravelActivityAdapter;
import com.huxin.communication.adpter.TableTravelOverseasAdapter;
import com.huxin.communication.base.BaseActivity;
import com.huxin.communication.controls.Constanst;
import com.huxin.communication.entity.ForeignTravelEntity;
import com.huxin.communication.entity.TabTravelNameEntity;
import com.huxin.communication.entity.TicketInfoEntity;
import com.huxin.communication.http.ApiModule;
import com.huxin.communication.ui.CityTravelActivity;
import com.huxin.communication.ui.MainActivity;
import com.huxin.communication.ui.ProvincesTravelActivity;
import com.huxin.communication.ui.cammer.HttpUtil;
import com.huxin.communication.ui.cammer.ImagePickerAdapter;
import com.huxin.communication.ui.cammer.MyStringCallBack;
import com.huxin.communication.ui.cammer.SelectDialog;
import com.huxin.communication.ui.house.release.ReleaseActivity;
import com.huxin.communication.utils.DateUtil;
import com.huxin.communication.utils.PreferenceUtil;
import com.huxin.communication.view.SpaceItemDecoration;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.sky.kylog.KyLog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import retrofit2.http.Field;

import static com.huxin.communication.HuXinApplication.mContext;

public class ReleaseTicketingActivity extends BaseActivity implements View.OnClickListener, ImagePickerAdapter.OnRecyclerViewItemClickListener {

    private TextView mTextViewSpot;
    private TextView mTextViewOther;
    private LinearLayout mLinearLayoutSpot;
    private LinearLayout mLinearLayoutOther;

    private EditText mEditTextTicketName;

    private EditText mEditTextfinalprice;
    private EditText mEditTextoriginalprice;
    private EditText mEditTextFinalPriceChild;
    private EditText mEditTextoOiginalPriceChild;
    private EditText mEditTextFinalPriceEvening;
    private EditText mEditTextOriginalPriceEvening;
    private EditText mEditTextFinalPriceParentChild;
    private EditText mEditTextOriginalPriceParentChild;
    private EditText mEditTextFinalPriceFamily;
    private EditText mEditTextOriginalPriceFamily;
    private EditText mEditTextFinalPriceTotal;
    private EditText mEditTextOriginalPriceTotal;
    private EditText mEditTextFinalBoat;
    private EditText mEditTextOriginalBoat;
    private EditText mEditTextFinalCar;
    private EditText mEditTextOriginalCar;


    private RecyclerView mRecyclerViewtheme;
    private RecyclerView mRecyclerViewActivity;

    private ImageView mImageViewStickNew;
    private ImageView mImageViewStickLow;
    private ImageView mImageViewStickBetter;
    private ImageView mImageViewStickThrow;
    private ImageView mImageViewStickRate;
    private ImageView mImageViewStickReturn;
    private ImageView mImageViewStickHot;
    private ImageView mImageViewStickZeroC;

    private TextView mTextViewConfirm;
    private TextView mTextViewProvince;
    private TextView mTextViewCity;
    private TextView mEditTextStartTime;
    private TextView mEditTextEndStart;

    private RelativeLayout mRelativeLayoutProvince;
    private RelativeLayout mRelativeLayoutCity;


    private int news = 0;
    private int low = 0;
    private int better = 0;
    private int shuaiwei = 0;
    private int rate = 0;
    private int returns = 0;
    private int hot = 0;
    private int zeroC = 0;
    private int stick = 0;

    private int caixian = 0;

    public static final int IMAGE_ITEM_ADD = -1;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;

    private ArrayList<ImageItem> selImageList; //当前选择的所有图片
    private ArrayList<ImageItem> selImageListOther; //当前选择的所有图片


    private int maxImgCount = 9;               //允许选择图片最大数

    private RecyclerView mRecyclerViewAddPicture;
    private ImagePickerTravelAdapter adapter;

    private HttpUtil httpUtil;


    private String Activity;
    private String Other;
    private String type = null;

    private String endTime;
    private String startTime;

    private TextView mTextViewTopMessage;

    private ImageView mRelativeLayoutStickZeroC;
    private ImageView mRelativeLayoutStickReturn;
    private ImageView mRelativeLayoutStickHot;
    private ImageView mRelativeLayoutStickThrow;
    private ImageView mRelativeLayoutStickBetter;
    private ImageView mRelativeLayoutStickLow;
    private ImageView mRelativeLayoutStickNew;
    private ImageView mRelativeLayoutStickRate;

    private EditText mEditTextGeneralize;

    private ImageView mImageViewShuaiWei;

    private ImageView mImageViewCaiXian;

    private int id = 0;
    private TicketInfoEntity.ListBean listBean;
    private int ticket_type = 1;

    //******************分割线******************//

    private EditText mEditTextTicketNameOther;

    private EditText mEditTextfinalpriceOther;
    private EditText mEditTextoriginalpriceOther;

    private RecyclerView mRecyclerViewthemeOther;

    private ImageView mImageViewStickNewOther;
    private ImageView mImageViewStickLowOther;
    private ImageView mImageViewStickBetterOther;
    private ImageView mImageViewStickThrowOther;
    private ImageView mImageViewStickRateOther;
    private ImageView mImageViewStickReturnOther;
    private ImageView mImageViewStickHotOther;
    private ImageView mImageViewStickZeroCOther;

    private TextView mTextViewConfirmOther;
    private TextView mTextViewProvinceOther;
    private TextView mTextViewCityOther;
    private TextView mEditTextStartTimeOther;
    private TextView mEditTextEndStartOther;

    private RelativeLayout mRelativeLayoutProvinceOther;
    private RelativeLayout mRelativeLayoutCityOther;

    private RecyclerView mRecyclerViewAddPictureOther;
    private ImagePickerTravelAdapter adapterOther;

    private TextView mTextViewTopMessageOther;


    private ImageView mRelativeLayoutStickZeroCOther;
    private ImageView mRelativeLayoutStickReturnOther;
    private ImageView mRelativeLayoutStickHotOther;
    private ImageView mRelativeLayoutStickThrowOther;
    private ImageView mRelativeLayoutStickBetterOther;
    private ImageView mRelativeLayoutStickLowOther;
    private ImageView mRelativeLayoutStickNewOther;
    private ImageView mRelativeLayoutStickRateOther;

    private EditText mEditTextGeneralizeOther;

    private ImageView mImageViewShuaiWeiOther;

    private ImageView mImageViewCaiXianOther;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_release_ticketing);
        type = getIntent().getStringExtra("type");
        id = getIntent().getIntExtra("id", 0);
        listBean = getIntent().getParcelableExtra("list");
        KyLog.d(id + " === ");
        KyLog.object(listBean + " === ");
    }

    @Override
    protected void initViews() {
        if (id == 0) {
            setToolbarCenterMode("发布票务", MODE_BACK);
        } else {
            setToolbarCenterMode("编辑票务", MODE_BACK);

        }
        mTextViewOther = (TextView) findViewById(R.id.other_tv);
        mTextViewSpot = (TextView) findViewById(R.id.spot_tv);
        mLinearLayoutSpot = (LinearLayout) findViewById(R.id.spot_rl);
        mLinearLayoutOther = (LinearLayout) findViewById(R.id.other_rl);

        mEditTextTicketName = (EditText) findViewById(R.id.ticket_name);

        mEditTextfinalprice = (EditText) findViewById(R.id.final_price);
        mEditTextoriginalprice = (EditText) findViewById(R.id.original_price);
        mEditTextFinalPriceChild = (EditText) findViewById(R.id.final_price_child);
        mEditTextoOiginalPriceChild = (EditText) findViewById(R.id.original_price_child);
        mEditTextFinalPriceEvening = (EditText) findViewById(R.id.final_price_evening);
        mEditTextOriginalPriceEvening = (EditText) findViewById(R.id.original_price_evening);
        mEditTextFinalPriceParentChild = (EditText) findViewById(R.id.final_price_parent_child);
        mEditTextOriginalPriceParentChild = (EditText) findViewById(R.id.original_price_parent_child);
        mEditTextFinalPriceFamily = (EditText) findViewById(R.id.final_price_family);
        mEditTextOriginalPriceFamily = (EditText) findViewById(R.id.original_price_family);
        mEditTextFinalPriceTotal = (EditText) findViewById(R.id.final_price_total);
        mEditTextOriginalPriceTotal = (EditText) findViewById(R.id.original_price_total);
        mEditTextFinalBoat = (EditText) findViewById(R.id.final_boat);
        mEditTextOriginalBoat = (EditText) findViewById(R.id.original_boat);
        mEditTextFinalCar = (EditText) findViewById(R.id.final_car);
        mEditTextOriginalCar = (EditText) findViewById(R.id.original_car);

        mImageViewShuaiWei = (ImageView) findViewById(R.id.ticket_shuaiWei);

        mImageViewCaiXian = (ImageView) findViewById(R.id.lineOrThrow_shuaiWei);

        mRecyclerViewActivity = (RecyclerView) findViewById(R.id.ticket_activity);
        mRecyclerViewtheme = (RecyclerView) findViewById(R.id.ticket_theme);


        mImageViewStickNew = (ImageView) findViewById(R.id.stick_new);
        mImageViewStickLow = (ImageView) findViewById(R.id.stick_low);
        mImageViewStickBetter = (ImageView) findViewById(R.id.stick_better);
        mImageViewStickThrow = (ImageView) findViewById(R.id.stick_throw);
        mImageViewStickRate = (ImageView) findViewById(R.id.stick_rate);
        mImageViewStickReturn = (ImageView) findViewById(R.id.stick_return);
        mImageViewStickHot = (ImageView) findViewById(R.id.stick_hot);
        mImageViewStickZeroC = (ImageView) findViewById(R.id.stick_zeroC);
        mTextViewConfirm = (TextView) findViewById(R.id.confirm);
        mRecyclerViewAddPicture = (RecyclerView) findViewById(R.id.recyclerView);

        mRelativeLayoutProvince = findViewById(R.id.rl_travel_ticket_province);
        mRelativeLayoutCity = findViewById(R.id.rl_travel_ticket_city);
        mTextViewProvince = findViewById(R.id.travel_ticket_province);
        mTextViewCity = findViewById(R.id.travel_ticket_city);
        mEditTextStartTime = findViewById(R.id.start_time);
        mEditTextEndStart = findViewById(R.id.end_start);

        mTextViewTopMessage = findViewById(R.id.top_message);

        mRelativeLayoutStickNew = findViewById(R.id.rl_stick_new);
        mRelativeLayoutStickBetter = findViewById(R.id.rl_stick_better);
        mRelativeLayoutStickLow = findViewById(R.id.rl_stick_low);
        mRelativeLayoutStickRate = findViewById(R.id.rl_stick_rate);
        mRelativeLayoutStickReturn = findViewById(R.id.rl_stick_return);
        mRelativeLayoutStickHot = findViewById(R.id.rl_stick_hot);
        mRelativeLayoutStickThrow = findViewById(R.id.rl_stick_throw);
        mRelativeLayoutStickZeroC = findViewById(R.id.rl_stick_zeroC);
        mEditTextGeneralize = findViewById(R.id.generalize);


        //**********************分割线**************************//

        mEditTextTicketNameOther = (EditText) findViewById(R.id.other_ticket_name);


        mEditTextfinalpriceOther = (EditText) findViewById(R.id.other_final_price);
        mEditTextoriginalpriceOther = (EditText) findViewById(R.id.other_original_price);

        mImageViewShuaiWeiOther = (ImageView) findViewById(R.id.other_ticket_shuaiWei);
        mImageViewCaiXianOther = (ImageView) findViewById(R.id.other_lineOrThrow_shuaiWei);
        mRecyclerViewthemeOther = (RecyclerView) findViewById(R.id.other_ticket_theme);

        mImageViewStickNewOther = (ImageView) findViewById(R.id.other_stick_new);
        mImageViewStickLowOther = (ImageView) findViewById(R.id.other_stick_low);
        mImageViewStickBetterOther = (ImageView) findViewById(R.id.other_stick_better);
        mImageViewStickThrowOther = (ImageView) findViewById(R.id.other_stick_throw);
        mImageViewStickRateOther = (ImageView) findViewById(R.id.other_stick_rate);
        mImageViewStickReturnOther = (ImageView) findViewById(R.id.other_stick_return);
        mImageViewStickHotOther = (ImageView) findViewById(R.id.other_stick_hot);
        mImageViewStickZeroCOther = (ImageView) findViewById(R.id.other_stick_zeroC);
        mTextViewConfirmOther = (TextView) findViewById(R.id.other_confirm);
        mRecyclerViewAddPictureOther = (RecyclerView) findViewById(R.id.other_recyclerView);


        mRelativeLayoutProvinceOther = findViewById(R.id.other_rl_travel_ticket_province);
        mRelativeLayoutCityOther = findViewById(R.id.other_rl_travel_ticket_city);
        mTextViewProvinceOther = findViewById(R.id.other_travel_ticket_province);
        mTextViewCityOther = findViewById(R.id.other_travel_ticket_city);
        mEditTextStartTimeOther = findViewById(R.id.other_start_time);
        mEditTextEndStartOther = findViewById(R.id.other_end_start);

        mTextViewTopMessageOther = findViewById(R.id.other_top_message);

        mRelativeLayoutStickNewOther = findViewById(R.id.other_rl_stick_new);
        mRelativeLayoutStickBetterOther = findViewById(R.id.other_rl_stick_better);
        mRelativeLayoutStickLowOther = findViewById(R.id.other_rl_stick_low);
        mRelativeLayoutStickRateOther = findViewById(R.id.other_rl_stick_rate);
        mRelativeLayoutStickReturnOther = findViewById(R.id.other_rl_stick_return);
        mRelativeLayoutStickHotOther = findViewById(R.id.other_rl_stick_hot);
        mRelativeLayoutStickThrowOther = findViewById(R.id.other_rl_stick_throw);
        mRelativeLayoutStickZeroCOther = findViewById(R.id.other_rl_stick_zeroC);

        mEditTextGeneralizeOther = findViewById(R.id.other_generalize);

        mTextViewSpot.setOnClickListener(this);
        mTextViewOther.setOnClickListener(this);

        mImageViewStickNew.setOnClickListener(this);
        mImageViewStickLow.setOnClickListener(this);
        mImageViewStickBetter.setOnClickListener(this);
        mImageViewStickThrow.setOnClickListener(this);
        mImageViewStickRate.setOnClickListener(this);
        mImageViewStickReturn.setOnClickListener(this);
        mImageViewStickHot.setOnClickListener(this);
        mImageViewStickZeroC.setOnClickListener(this);
        mTextViewConfirm.setOnClickListener(this);

        mRelativeLayoutCity.setOnClickListener(this);
        mRelativeLayoutProvince.setOnClickListener(this);

        mEditTextEndStart.setOnClickListener(this);
        mEditTextStartTime.setOnClickListener(this);

        mRelativeLayoutStickZeroC.setOnClickListener(this);
        mRelativeLayoutStickBetter.setOnClickListener(this);
        mRelativeLayoutStickLow.setOnClickListener(this);
        mRelativeLayoutStickReturn.setOnClickListener(this);
        mRelativeLayoutStickRate.setOnClickListener(this);
        mRelativeLayoutStickHot.setOnClickListener(this);
        mRelativeLayoutStickThrow.setOnClickListener(this);
        mRelativeLayoutStickNew.setOnClickListener(this);


        //***********************分割线*************************//

        mImageViewStickNewOther.setOnClickListener(this);
        mImageViewStickLowOther.setOnClickListener(this);
        mImageViewStickBetterOther.setOnClickListener(this);
        mImageViewStickThrowOther.setOnClickListener(this);
        mImageViewStickRateOther.setOnClickListener(this);
        mImageViewStickReturnOther.setOnClickListener(this);
        mImageViewStickHotOther.setOnClickListener(this);
        mImageViewStickZeroCOther.setOnClickListener(this);
        mTextViewConfirmOther.setOnClickListener(this);

        mRelativeLayoutCityOther.setOnClickListener(this);
        mRelativeLayoutProvinceOther.setOnClickListener(this);

        mEditTextEndStartOther.setOnClickListener(this);
        mEditTextStartTimeOther.setOnClickListener(this);

        mRelativeLayoutStickZeroCOther.setOnClickListener(this);
        mRelativeLayoutStickBetterOther.setOnClickListener(this);
        mRelativeLayoutStickLowOther.setOnClickListener(this);
        mRelativeLayoutStickReturnOther.setOnClickListener(this);
        mRelativeLayoutStickRateOther.setOnClickListener(this);
        mRelativeLayoutStickHotOther.setOnClickListener(this);
        mRelativeLayoutStickThrowOther.setOnClickListener(this);
        mRelativeLayoutStickNewOther.setOnClickListener(this);

    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        httpUtil = new HttpUtil();
        selImageList = new ArrayList<>();
        adapter = new ImagePickerTravelAdapter(this, selImageList, maxImgCount);
        adapter.setOnItemClickListener(this);
        mRecyclerViewAddPicture.setLayoutManager(new GridLayoutManager(this, 4));
        mRecyclerViewAddPicture.setHasFixedSize(true);
        mRecyclerViewAddPicture.setAdapter(adapter);

        selImageListOther = new ArrayList<>();
        adapterOther = new ImagePickerTravelAdapter(this, selImageListOther, maxImgCount);
        adapterOther.setOnItemClickListener(this);
        mRecyclerViewAddPictureOther.setLayoutManager(new GridLayoutManager(this, 4));
        mRecyclerViewAddPictureOther.setHasFixedSize(true);
        mRecyclerViewAddPictureOther.setAdapter(adapterOther);

        mTextViewSpot.setBackgroundResource(R.color.blue);
        mTextViewOther.setBackgroundResource(R.color.login_forget_password_code_fort);
        mTextViewOther.setTextColor(getResources().getColor(R.color.register_font));
        mTextViewSpot.setTextColor(getResources().getColor(R.color.white_ticketing));

    }

    @Override
    protected void onResume() {
        super.onResume();
        selectTravelTab(ticket_type);
        if (ticket_type == 1) {
            mTextViewTopMessage.setText("置顶信息剩余" + String.valueOf(PreferenceUtil.getInt(Constanst.TOP_ZHIDING)) + "条");
            mTextViewProvince.setText(PreferenceUtil.getString(Constanst.TICKET_PROVINCE_NAME));
            mTextViewCity.setText(PreferenceUtil.getString(Constanst.TICKET_CITY_NAME));
        } else {
            mTextViewTopMessageOther.setText("置顶信息剩余" + String.valueOf(PreferenceUtil.getInt(Constanst.TOP_ZHIDING)) + "条");
            mTextViewProvinceOther.setText(PreferenceUtil.getString(Constanst.TICKET_PROVINCE_NAME));
            mTextViewCityOther.setText(PreferenceUtil.getString(Constanst.TICKET_CITY_NAME));
        }
        if (ticket_type == 1) {
            SetEnabled();
        } else {
            SetOtherEnabled();

        }

        if (id != 0) {
            if (ticket_type == 1) {
                setData();
            } else {
                setOterData();
            }
        }
    }


    private void setData() {
        KyLog.object(listBean);
        if (listBean != null) {
            if (!TextUtils.isEmpty(listBean.getTicket_name())) {
                mEditTextTicketName.setText(String.valueOf(listBean.getTicket_name()));
            }
            mEditTextFinalBoat.setText(String.valueOf(listBean.getFinal_boat()));
            mEditTextFinalCar.setText(String.valueOf(listBean.getFinal_car()));
            mEditTextfinalprice.setText(String.valueOf(listBean.getFinal_price()));
            mEditTextFinalPriceChild.setText(String.valueOf(listBean.getFinal_price_child()));
            mEditTextFinalPriceEvening.setText(String.valueOf(listBean.getFinal_price_evening()));
            mEditTextFinalPriceFamily.setText(String.valueOf(listBean.getFinal_price_family()));
            mEditTextFinalPriceParentChild.setText(String.valueOf(listBean.getFinal_price_parent_child()));
            mEditTextFinalPriceTotal.setText(String.valueOf(listBean.getFinal_price_total()));
            mEditTextoOiginalPriceChild.setText(String.valueOf(listBean.getFinal_price_parent_child()));
            mEditTextOriginalBoat.setText(String.valueOf(listBean.getOriginal_boat()));
            mEditTextOriginalCar.setText(String.valueOf(listBean.getOriginal_car()));
            mEditTextoriginalprice.setText(String.valueOf(listBean.getOriginal_price()));
            mEditTextOriginalPriceEvening.setText(String.valueOf(listBean.getOriginal_price_evening()));
            mEditTextOriginalPriceFamily.setText(String.valueOf(listBean.getOriginal_price_family()));
            mEditTextOriginalPriceParentChild.setText(String.valueOf(listBean.getOriginal_price_parent_child()));
            mEditTextOriginalPriceTotal.setText(String.valueOf(listBean.getOriginal_price_total()));


            if (!TextUtils.isEmpty(listBean.getTicket_addr())) {
                mTextViewProvince.setText(String.valueOf(listBean.getTicket_addr()));

            }

            if (!TextUtils.isEmpty(listBean.getGeneralize())) {
                mEditTextGeneralize.setText(String.valueOf(listBean.getGeneralize()));

            }

            if (!TextUtils.isEmpty(listBean.getOpen_time())) {
                String [] times = listBean.getOpen_time().split("~");
                KyLog.d(times[times.length - 2] + " === " + times[times.length - 1]);
                mEditTextStartTime.setText(times[times.length - 2]);
                mEditTextEndStart.setText(times[times.length - 1]);

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

        }
    }


    private void setOterData() {
        KyLog.object(listBean);
        if (listBean != null) {
            if (!TextUtils.isEmpty(listBean.getTicket_name())) {
                mEditTextTicketNameOther.setText(String.valueOf(listBean.getTicket_name()));
            }
            mEditTextfinalpriceOther.setText(String.valueOf(listBean.getFinal_price()));
            mEditTextoriginalpriceOther.setText(String.valueOf(listBean.getOriginal_price()));


            if (!TextUtils.isEmpty(listBean.getTicket_addr())) {
                mTextViewProvinceOther.setText(String.valueOf(listBean.getTicket_addr()));

            }

            if (!TextUtils.isEmpty(listBean.getGeneralize())) {
                mEditTextGeneralizeOther.setText(String.valueOf(listBean.getGeneralize()));

            }

            if (!TextUtils.isEmpty(listBean.getIssue_time())) {
                mEditTextStartTimeOther.setText(listBean.getIssue_time());
            }

            if (listBean.getLine_or_throw() == 0) {
                mImageViewShuaiWeiOther.setBackgroundResource(R.drawable.icon_circle_selected);
                mImageViewCaiXianOther.setBackgroundResource(R.drawable.icon_circle_normal);

                caixian = 1;
            } else {
                mImageViewShuaiWeiOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewCaiXianOther.setBackgroundResource(R.drawable.icon_circle_selected);
                caixian = 2;
            }

            if (listBean.getStick_better() == 1) {
                mImageViewStickNewOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickLowOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickHotOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickThrowOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickRateOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickReturnOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickZeroCOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickBetterOther.setBackgroundResource(R.drawable.icon_circle_selected);
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
                mImageViewStickNewOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickLowOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickHotOther.setBackgroundResource(R.drawable.icon_circle_selected);
                mImageViewStickThrowOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickRateOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickReturnOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickZeroCOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickBetterOther.setBackgroundResource(R.drawable.icon_circle_normal);
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
                mImageViewStickNewOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickLowOther.setBackgroundResource(R.drawable.icon_circle_selected);
                mImageViewStickHotOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickThrowOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickRateOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickReturnOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickZeroCOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickBetterOther.setBackgroundResource(R.drawable.icon_circle_normal);
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
                mImageViewStickNewOther.setBackgroundResource(R.drawable.icon_circle_selected);
                mImageViewStickLowOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickHotOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickThrowOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickRateOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickReturnOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickZeroCOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickBetterOther.setBackgroundResource(R.drawable.icon_circle_normal);
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
                mImageViewStickNewOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickLowOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickHotOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickThrowOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickRateOther.setBackgroundResource(R.drawable.icon_circle_selected);
                mImageViewStickReturnOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickZeroCOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickBetterOther.setBackgroundResource(R.drawable.icon_circle_normal);
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
                mImageViewStickNewOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickLowOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickHotOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickThrowOther.setBackgroundResource(R.drawable.icon_circle_selected);
                mImageViewStickRateOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickReturnOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickZeroCOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickBetterOther.setBackgroundResource(R.drawable.icon_circle_normal);
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
                mImageViewStickNewOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickLowOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickHotOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickThrowOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickRateOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickReturnOther.setBackgroundResource(R.drawable.icon_circle_selected);
                mImageViewStickZeroCOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickBetterOther.setBackgroundResource(R.drawable.icon_circle_normal);
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
                mImageViewStickNewOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickLowOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickHotOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickThrowOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickRateOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickReturnOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickZeroCOther.setBackgroundResource(R.drawable.icon_circle_selected);
                mImageViewStickBetterOther.setBackgroundResource(R.drawable.icon_circle_normal);
                better = 0;
                news = 0;
                low = 0;
                hot = 0;
                shuaiwei = 0;
                rate = 0;
                returns = 0;
                zeroC = 1;
            }

        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.spot_tv:
                mLinearLayoutOther.setVisibility(View.GONE);
                mLinearLayoutSpot.setVisibility(View.VISIBLE);
                mTextViewSpot.setBackgroundResource(R.color.blue);
                mTextViewOther.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewOther.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewSpot.setTextColor(getResources().getColor(R.color.white_ticketing));
                ticket_type = 1;
                break;
            case R.id.other_tv:
                mLinearLayoutOther.setVisibility(View.VISIBLE);
                mLinearLayoutSpot.setVisibility(View.GONE);
                mTextViewSpot.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewOther.setBackgroundResource(R.color.blue);
                mTextViewOther.setTextColor(getResources().getColor(R.color.white_ticketing));
                mTextViewSpot.setTextColor(getResources().getColor(R.color.register_font));
                ticket_type = 2;
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
                    if (id == 0) {
                        uploadImage(selImageList);
                    }else {
                        uploadDataImage(selImageList);
                    }

                break;

            case R.id.rl_travel_ticket_province:
                Intent intent = new Intent(this, ProvincesTravelActivity.class);
                intent.putExtra("type", 4);
                startActivity(intent);
                break;
            case R.id.rl_travel_ticket_city:

                Intent intentCity = new Intent(this, CityTravelActivity.class);
                intentCity.putExtra("type", 4);
                intentCity.putExtra(Constanst.PROVINCE_CODE, PreferenceUtil.getString(Constanst.TICKET_PROVINCE_CODE));
                startActivity(intentCity);
                break;

            case R.id.start_time:
                StartDatePickDialog(DateParams.TYPE_HOUR, DateParams.TYPE_MINUTE);

                break;
            case R.id.end_start:
                EndDatePickDialog(DateParams.TYPE_HOUR, DateParams.TYPE_MINUTE);
                break;

            case R.id.ticket_shuaiWei:
                mImageViewShuaiWei.setBackgroundResource(R.drawable.icon_circle_selected);
                mImageViewCaiXian.setBackgroundResource(R.drawable.icon_circle_normal);

                caixian = 1;

                break;
            case R.id.lineOrThrow_shuaiWei:
                mImageViewCaiXian.setBackgroundResource(R.drawable.icon_circle_selected);
                mImageViewShuaiWei.setBackgroundResource(R.drawable.icon_circle_normal);

                caixian = 2;
                break;


            case R.id.other_rl_stick_better:
                mImageViewStickNewOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickLowOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickHotOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickThrowOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickRateOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickReturnOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickZeroCOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickBetterOther.setBackgroundResource(R.drawable.icon_circle_selected);
                better = 1;

                news = 0;
                low = 0;
                hot = 0;
                shuaiwei = 0;
                rate = 0;
                returns = 0;
                zeroC = 0;
                break;
            case R.id.other_rl_stick_hot:
                mImageViewStickNewOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickLowOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickHotOther.setBackgroundResource(R.drawable.icon_circle_selected);
                mImageViewStickThrowOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickRateOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickReturnOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickZeroCOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickBetterOther.setBackgroundResource(R.drawable.icon_circle_normal);
                better = 0;
                news = 0;
                low = 0;
                hot = 1;
                shuaiwei = 0;
                rate = 0;
                returns = 0;
                zeroC = 0;
                break;


            case R.id.other_rl_stick_new:
                mImageViewStickNewOther.setBackgroundResource(R.drawable.icon_circle_selected);
                mImageViewStickLowOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickHotOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickThrowOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickRateOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickReturnOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickZeroCOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickBetterOther.setBackgroundResource(R.drawable.icon_circle_normal);
                better = 0;
                news = 1;
                low = 0;
                hot = 0;
                shuaiwei = 0;
                rate = 0;
                returns = 0;
                zeroC = 0;
                break;

            case R.id.other_rl_stick_low:
                mImageViewStickNewOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickLowOther.setBackgroundResource(R.drawable.icon_circle_selected);
                mImageViewStickHotOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickThrowOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickRateOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickReturnOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickZeroCOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickBetterOther.setBackgroundResource(R.drawable.icon_circle_normal);
                better = 0;
                news = 0;
                low = 1;
                hot = 0;
                shuaiwei = 0;
                rate = 0;
                returns = 0;
                zeroC = 0;
                break;

            case R.id.other_rl_stick_throw:
                mImageViewStickNewOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickLowOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickHotOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickThrowOther.setBackgroundResource(R.drawable.icon_circle_selected);
                mImageViewStickRateOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickReturnOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickZeroCOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickBetterOther.setBackgroundResource(R.drawable.icon_circle_normal);
                better = 0;
                news = 0;
                low = 0;
                hot = 0;
                shuaiwei = 1;
                rate = 0;
                returns = 0;
                zeroC = 0;
                break;

            case R.id.other_rl_stick_rate:
                mImageViewStickNewOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickLowOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickHotOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickThrowOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickRateOther.setBackgroundResource(R.drawable.icon_circle_selected);
                mImageViewStickReturnOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickZeroCOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickBetterOther.setBackgroundResource(R.drawable.icon_circle_normal);
                better = 0;
                news = 0;
                low = 0;
                hot = 0;
                shuaiwei = 0;
                rate = 1;
                returns = 0;
                zeroC = 0;
                break;

            case R.id.other_rl_stick_return:
                mImageViewStickNewOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickLowOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickHotOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickThrowOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickRateOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickReturnOther.setBackgroundResource(R.drawable.icon_circle_selected);
                mImageViewStickZeroCOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickBetterOther.setBackgroundResource(R.drawable.icon_circle_normal);
                better = 0;
                news = 0;
                low = 0;
                hot = 0;
                shuaiwei = 0;
                rate = 0;
                returns = 1;
                zeroC = 0;
                break;

            case R.id.other_rl_stick_zeroC:
                mImageViewStickNewOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickLowOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickHotOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickThrowOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickRateOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickReturnOther.setBackgroundResource(R.drawable.icon_circle_normal);
                mImageViewStickZeroCOther.setBackgroundResource(R.drawable.icon_circle_selected);
                mImageViewStickBetterOther.setBackgroundResource(R.drawable.icon_circle_normal);
                better = 0;
                news = 0;
                low = 0;
                hot = 0;
                shuaiwei = 0;
                rate = 0;
                returns = 0;
                zeroC = 1;
                break;
            case R.id.other_confirm:
                if (id == 0) {
                    uploadImage(selImageListOther);
                }else {
                    uploadDataImage(selImageListOther);
                }
                break;

            case R.id.other_rl_travel_ticket_province:
                Intent intentOther = new Intent(this, ProvincesTravelActivity.class);
                intentOther.putExtra("type", 4);
                startActivity(intentOther);
                break;
            case R.id.other_rl_travel_ticket_city:

                Intent intentCityOther = new Intent(this, CityTravelActivity.class);
                intentCityOther.putExtra("type", 4);
                intentCityOther.putExtra(Constanst.PROVINCE_CODE, PreferenceUtil.getString(Constanst.TICKET_PROVINCE_CODE));
                startActivity(intentCityOther);
                break;

            case R.id.other_start_time:
                StartDatePickDialog(DateParams.TYPE_HOUR, DateParams.TYPE_MINUTE);

                break;
            case R.id.other_end_start:
                EndDatePickDialog(DateParams.TYPE_HOUR, DateParams.TYPE_MINUTE);
                break;

            case R.id.other_ticket_shuaiWei:
                mImageViewShuaiWeiOther.setBackgroundResource(R.drawable.icon_circle_selected);
                mImageViewCaiXianOther.setBackgroundResource(R.drawable.icon_circle_normal);

                caixian = 1;

                break;
            case R.id.other_lineOrThrow_shuaiWei:
                mImageViewCaiXianOther.setBackgroundResource(R.drawable.icon_circle_selected);
                mImageViewShuaiWeiOther.setBackgroundResource(R.drawable.icon_circle_normal);

                caixian = 2;
                break;
        }
    }


    private void selectTravelTab(int type) {
        showProgressDialog();
        ApiModule.getInstance().selectTravelTab(String.valueOf(type))
                .subscribe(tabTravelNameEntity -> {
                    KyLog.object(tabTravelNameEntity + "");
                    cancelProgressDialog();
                    if (type == 1) {
                        setActivityData(tabTravelNameEntity.getActivityList(), mRecyclerViewActivity);
                        setThemeListData(tabTravelNameEntity.getThemeLists(), mRecyclerViewtheme);

                    } else {
                        setThemeListData(tabTravelNameEntity.getThemeLists(), mRecyclerViewthemeOther);

                    }

                }, throwable -> {
                    KyLog.d(throwable.toString());
                    cancelProgressDialog();
                    Toast.makeText(this, throwable.getMessage().toString(), Toast.LENGTH_SHORT).show();
                });
    }


    private void setActivityData(List<TabTravelNameEntity.ActivityListBean> list, RecyclerView recyclerView) {
        if (list != null && list.size() > 0) {
            GridLayoutManager manager = new GridLayoutManager(this, 5);
            TableTravelActivityAdapter mAdapterTableName = new TableTravelActivityAdapter(list, this);
            recyclerView.setAdapter(mAdapterTableName);
            recyclerView.setLayoutManager(manager);
//            recyclerView.addItemDecoration(new SpaceItemDecoration(0, 15));
        }
    }

    private void setThemeListData(List<TabTravelNameEntity.ThemeList> list, RecyclerView recyclerView) {
        if (list != null && list.size() > 0) {
            GridLayoutManager manager = new GridLayoutManager(this, 5);
            TableTravelOverseasAdapter mAdapterTableName = new TableTravelOverseasAdapter(list, this);
            recyclerView.setAdapter(mAdapterTableName);
            recyclerView.setLayoutManager(manager);
//            recyclerView.addItemDecoration(new SpaceItemDecoration(0, 15));
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
                        Intent intent = new Intent(ReleaseTicketingActivity.this, ImageGridActivity.class);
                        intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                        startActivityForResult(intent, REQUEST_CODE_SELECT);
                        break;
                    case 1:
                        //打开选择,本次允许选择的数量
                        ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                        Intent intent1 = new Intent(ReleaseTicketingActivity.this, ImageGridActivity.class);
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
                    if (ticket_type == 1) {
                        selImageList.addAll(images);
                        adapter.setImages(selImageList);
                    }else {
                        selImageListOther.addAll(images);
                        adapterOther.setImages(selImageListOther);
                    }
                }
            }
        } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
            //预览图片返回
            if (data != null && requestCode == REQUEST_CODE_PREVIEW) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
                if (images != null) {
                    if (ticket_type == 1) {
                        selImageList.clear();
                        selImageList.addAll(images);
                        adapter.setImages(selImageList);
                    }else {
                        selImageListOther.clear();
                        selImageListOther.addAll(images);
                        adapterOther.setImages(selImageListOther);
                    }
                }
            }
        }
    }

    String TicketName;
    String FinalBoat;
    String FinalCar;
    String finalprice;
    String FinalPriceChild;
    String FinalPriceEvening;
    String FinalPriceFamily;
    String FinalPriceParentChild;
    String FinalPriceTotal;
    String OiginalPriceChild;
    String OriginalBoat;
    String OriginalCar;
    String originalprice;
    String OriginalPriceEvening;
    String OriginalPriceFamily;
    String OriginalPriceParentChild;
    String OriginalPriceTotal;

    /**
     * 上传图片
     *
     * @param pathList
     */
    private void uploadDataImage(ArrayList<ImageItem> pathList) {
        if (ticket_type == 1) {
            TicketName = mEditTextTicketName.getText().toString().trim();
            FinalBoat = mEditTextFinalBoat.getText().toString().trim();
            FinalCar = mEditTextFinalCar.getText().toString().trim();
            finalprice = mEditTextfinalprice.getText().toString().trim();
            FinalPriceChild = mEditTextFinalPriceChild.getText().toString().trim();
            FinalPriceEvening = mEditTextFinalPriceEvening.getText().toString().trim();
            FinalPriceFamily = mEditTextFinalPriceFamily.getText().toString().trim();
            FinalPriceParentChild = mEditTextFinalPriceParentChild.getText().toString().trim();
            FinalPriceTotal = mEditTextFinalPriceTotal.getText().toString().trim();
            OiginalPriceChild = mEditTextoOiginalPriceChild.getText().toString().trim();
            OriginalBoat = mEditTextOriginalBoat.getText().toString().trim();
            OriginalCar = mEditTextOriginalCar.getText().toString().trim();
            originalprice = mEditTextoriginalprice.getText().toString().trim();
            OriginalPriceEvening = mEditTextOriginalPriceEvening.getText().toString().trim();
            OriginalPriceFamily = mEditTextOriginalPriceFamily.getText().toString().trim();
            OriginalPriceParentChild = mEditTextOriginalPriceParentChild.getText().toString().trim();
            OriginalPriceTotal = mEditTextOriginalPriceTotal.getText().toString().trim();
        } else {
            TicketName = mEditTextTicketNameOther.getText().toString().trim();
            finalprice = mEditTextfinalpriceOther.getText().toString().trim();
            originalprice = mEditTextoriginalpriceOther.getText().toString().trim();

        }

        String TicketAddr = PreferenceUtil.getString(Constanst.TICKET_PROVINCE_NAME) + PreferenceUtil.getString(Constanst.TICKET_CITY_NAME);

        if (TextUtils.isEmpty(TicketAddr) && TextUtils.isEmpty(TicketName)) {
            Toast.makeText(this, "请填写地址或景点名称", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(startTime) && TextUtils.isEmpty(endTime)) {
            Toast.makeText(this, "请选择时间", Toast.LENGTH_SHORT).show();
            return;
        }

        Activity = PreferenceUtil.getString(Constanst.TAB_NMAE_ACTIVITY);
        Other = PreferenceUtil.getString(Constanst.TAB_NMAE_OTHER);

        if (news == 0 && low == 0 && better == 0 && shuaiwei == 0 && rate == 0 && returns == 0 && hot == 0 && zeroC == 0) {
            stick = 2;
        } else {
            stick = 1;
        }
        showProgressDialog();
        KyLog.d(PreferenceUtil.getString(Constanst.CITY_NAME));
        KyLog.d(PreferenceUtil.getString(Constanst.PROVINCE_CODE));

        KyLog.d(PreferenceUtil.getString(Constanst.SPOT_ID));
        KyLog.d(PreferenceUtil.getString(Constanst.SPOT_NAME));
        KyLog.d(PreferenceUtil.getString(Constanst.CITY_MUDI_TRAVEL_NAME));
        KyLog.d(PreferenceUtil.getString(Constanst.PROVINCE_MUDI_TRAVEL_NAME));
        KyLog.d(PreferenceUtil.getString(Constanst.CITY_MUDI_CODE));
        KyLog.d(PreferenceUtil.getString(Constanst.CITY_TRAVEL_NAME));

        Map<String, String> map = new HashMap<>();
        if (!TextUtils.isEmpty(PreferenceUtil.getString(Constanst.TICKET_PROVINCE_NAME))) {
            map.put("ticket_pro_name", PreferenceUtil.getString(Constanst.TICKET_PROVINCE_NAME));
        }

        if (!TextUtils.isEmpty(PreferenceUtil.getString(Constanst.TICKET_CITY_NAME))) {
            map.put("ticket_city_name", PreferenceUtil.getString(Constanst.TICKET_CITY_NAME));
        }
        if (!TextUtils.isEmpty(TicketName)) {
            map.put("ticket_name", TicketName);
        }

        if (!TextUtils.isEmpty(TicketAddr)) {
            map.put("ticket_addr", TicketAddr);
        }

        map.put("ticket_type", String.valueOf(ticket_type));
        if (!TextUtils.isEmpty(startTime)&& !TextUtils.isEmpty(endTime))
        map.put("open_time", startTime + "~" + endTime);
        if (!TextUtils.isEmpty(originalprice)) {

            map.put("original_price", originalprice);
        }
        if (!TextUtils.isEmpty(finalprice)){
            map.put("final_price", finalprice);

        }
        if (!TextUtils.isEmpty(FinalPriceChild)) {
            map.put("original_price_child", FinalPriceChild);
        }

        if (!TextUtils.isEmpty(FinalPriceChild)) {
            map.put("final_price_child", FinalPriceChild);
        }

        if (!TextUtils.isEmpty(OriginalPriceEvening)) {
            map.put("original_price_evening", OriginalPriceEvening);
        }
        if (!TextUtils.isEmpty(FinalPriceEvening)) {

            map.put("final_price_evening", FinalPriceEvening);
        }
        if (!TextUtils.isEmpty(OriginalPriceParentChild)) {

            map.put("original_price_parent_child", OriginalPriceParentChild);
        }
        if (!TextUtils.isEmpty(FinalPriceParentChild)) {

            map.put("final_price_parent_child", FinalPriceParentChild);
        }
        if (!TextUtils.isEmpty(OriginalPriceFamily)) {

            map.put("original_price_family", OriginalPriceFamily);
        }

        if (!TextUtils.isEmpty(FinalPriceFamily)) {

            map.put("final_price_family", FinalPriceFamily);
        }

        if (!TextUtils.isEmpty(OriginalBoat)) {

            map.put("original_boat", OriginalBoat);
        }
        if (!TextUtils.isEmpty(FinalBoat)) {

            map.put("final_boat", FinalBoat);}
        if (!TextUtils.isEmpty(OriginalCar)) {

            map.put("original_car", OriginalCar);
        }
        if (!TextUtils.isEmpty(FinalCar)) {

            map.put("final_car", FinalCar);
        }
        map.put("ticket_theme_id", "");
        if (!TextUtils.isEmpty(Activity)) {
            map.put("ticket_activity_id", Activity);
        }
        if (!TextUtils.isEmpty(Other)) {
            map.put("ticket_other_id", Other);
        }

        map.put("uid", String.valueOf(PreferenceUtil.getInt(UID)));
        map.put("stick", String.valueOf(stick));
        if (caixian != 0) {
            map.put("line_or_throw", String.valueOf(caixian));
        }
        map.put("stick_new", String.valueOf(news));
        map.put("stick_low", String.valueOf(low));
        map.put("stick_better", String.valueOf(better));
        map.put("stick_throw", String.valueOf(shuaiwei));
        map.put("stick_rate", String.valueOf(rate));
        map.put("stick_return", String.valueOf(returns));
        map.put("stick_hot", String.valueOf(hot));
        map.put("stick_zeroC", String.valueOf(zeroC));
        map.put("token", PreferenceUtil.getString(TOKEN));

        map.put("generalize", "");
        if (!TextUtils.isEmpty(OriginalPriceTotal)) {

            map.put("original_price_total", OriginalPriceTotal);
        }
        if (!TextUtils.isEmpty(FinalPriceTotal)) {

            map.put("final_price_total", FinalPriceTotal);
        }
        if (!TextUtils.isEmpty(PreferenceUtil.getString(Constanst.TICKET_PROVINCE_CODE))) {
            map.put("ticket_pro_code", PreferenceUtil.getString(Constanst.TICKET_PROVINCE_CODE));
        }
        map.put("id", String.valueOf(id));

        map.put("user_idForCol", String.valueOf(PreferenceUtil.getInt(UID)));

        KyLog.object(map);
        String url = "http://39.105.203.33/jlkf/mutual-trust/travel/issueTicket";


        httpUtil.postFileRequest(url, map, pathList, new MyStringCallBack() {

            @Override
            public void onError(Call call, Exception e, int id) {
                cancelProgressDialog();
                super.onError(call, e, id);
                KyLog.d(e + "");
                Toast.makeText(ReleaseTicketingActivity.this, "发布失败", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onResponse(String response, int id) {
                cancelProgressDialog();
                super.onResponse(response, id);
                KyLog.d(response);
                //返回图片的地址
                getUseInfo();
                Toast.makeText(ReleaseTicketingActivity.this, "发布成功", Toast.LENGTH_SHORT).show();
                if (TextUtils.isEmpty(type)) {
                    Intent intent = new Intent(ReleaseTicketingActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    try {
                        JSONObject res = new JSONObject();

                        JSONArray array = new JSONArray();
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("type", 2);
                        jsonObject.put("travelType", 4);
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
    private void uploadImage(ArrayList<ImageItem> pathList) {

        if (ticket_type == 1) {
            TicketName = mEditTextTicketName.getText().toString().trim();
            FinalBoat = mEditTextFinalBoat.getText().toString().trim();
            FinalCar = mEditTextFinalCar.getText().toString().trim();
            finalprice = mEditTextfinalprice.getText().toString().trim();
            FinalPriceChild = mEditTextFinalPriceChild.getText().toString().trim();
            FinalPriceEvening = mEditTextFinalPriceEvening.getText().toString().trim();
            FinalPriceFamily = mEditTextFinalPriceFamily.getText().toString().trim();
            FinalPriceParentChild = mEditTextFinalPriceParentChild.getText().toString().trim();
            FinalPriceTotal = mEditTextFinalPriceTotal.getText().toString().trim();
            OiginalPriceChild = mEditTextoOiginalPriceChild.getText().toString().trim();
            OriginalBoat = mEditTextOriginalBoat.getText().toString().trim();
            OriginalCar = mEditTextOriginalCar.getText().toString().trim();
            originalprice = mEditTextoriginalprice.getText().toString().trim();
            OriginalPriceEvening = mEditTextOriginalPriceEvening.getText().toString().trim();
            OriginalPriceFamily = mEditTextOriginalPriceFamily.getText().toString().trim();
            OriginalPriceParentChild = mEditTextOriginalPriceParentChild.getText().toString().trim();
            OriginalPriceTotal = mEditTextOriginalPriceTotal.getText().toString().trim();
        } else {
            TicketName = mEditTextTicketNameOther.getText().toString().trim();
            finalprice = mEditTextfinalpriceOther.getText().toString().trim();
            originalprice = mEditTextoriginalpriceOther.getText().toString().trim();

        }

        String TicketAddr = PreferenceUtil.getString(Constanst.TICKET_PROVINCE_NAME) + PreferenceUtil.getString(Constanst.TICKET_CITY_NAME);

        if (TextUtils.isEmpty(TicketAddr) || TextUtils.isEmpty(TicketName)) {
            Toast.makeText(this, "请填写地址或景点名称", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(startTime) || TextUtils.isEmpty(endTime)) {
            Toast.makeText(this, "请选择时间", Toast.LENGTH_SHORT).show();
            return;
        }

        Activity = PreferenceUtil.getString(Constanst.TAB_NMAE_ACTIVITY);
        Other = PreferenceUtil.getString(Constanst.TAB_NMAE_OTHER);

        if (news == 0 && low == 0 && better == 0 && shuaiwei == 0 && rate == 0 && returns == 0 && hot == 0 && zeroC == 0) {
            stick = 2;
        } else {
            stick = 1;
        }
        showProgressDialog();
        KyLog.d(PreferenceUtil.getString(Constanst.CITY_NAME));
        KyLog.d(PreferenceUtil.getString(Constanst.PROVINCE_CODE));

        KyLog.d(PreferenceUtil.getString(Constanst.SPOT_ID));
        KyLog.d(PreferenceUtil.getString(Constanst.SPOT_NAME));
        KyLog.d(PreferenceUtil.getString(Constanst.CITY_MUDI_TRAVEL_NAME));
        KyLog.d(PreferenceUtil.getString(Constanst.PROVINCE_MUDI_TRAVEL_NAME));
        KyLog.d(PreferenceUtil.getString(Constanst.CITY_MUDI_CODE));
        KyLog.d(PreferenceUtil.getString(Constanst.CITY_TRAVEL_NAME));

        Map<String, String> map = new HashMap<>();
        if (!TextUtils.isEmpty(PreferenceUtil.getString(Constanst.TICKET_PROVINCE_NAME))) {
            map.put("ticket_pro_name", PreferenceUtil.getString(Constanst.TICKET_PROVINCE_NAME));
        }

        if (!TextUtils.isEmpty(PreferenceUtil.getString(Constanst.TICKET_CITY_NAME))) {
            map.put("ticket_city_name", PreferenceUtil.getString(Constanst.TICKET_CITY_NAME));
        }
        if (!TextUtils.isEmpty(TicketName)) {
            map.put("ticket_name", TicketName);
        }

        if (!TextUtils.isEmpty(TicketAddr)) {
            map.put("ticket_addr", TicketAddr);
        }

        map.put("ticket_type", String.valueOf(ticket_type));
        if (!TextUtils.isEmpty(startTime)&& !TextUtils.isEmpty(endTime))
            map.put("open_time", startTime + "~" + endTime);
        if (!TextUtils.isEmpty(originalprice)) {

            map.put("original_price", originalprice);
        }
        if (!TextUtils.isEmpty(finalprice)){
            map.put("final_price", finalprice);

        }
        if (!TextUtils.isEmpty(FinalPriceChild)) {
            map.put("original_price_child", FinalPriceChild);
        }

        if (!TextUtils.isEmpty(FinalPriceChild)) {
            map.put("final_price_child", FinalPriceChild);
        }

        if (!TextUtils.isEmpty(OriginalPriceEvening)) {
            map.put("original_price_evening", OriginalPriceEvening);
        }
        if (!TextUtils.isEmpty(FinalPriceEvening)) {

            map.put("final_price_evening", FinalPriceEvening);
        }
        if (!TextUtils.isEmpty(OriginalPriceParentChild)) {

            map.put("original_price_parent_child", OriginalPriceParentChild);
        }
        if (!TextUtils.isEmpty(FinalPriceParentChild)) {

            map.put("final_price_parent_child", FinalPriceParentChild);
        }
        if (!TextUtils.isEmpty(OriginalPriceFamily)) {

            map.put("original_price_family", OriginalPriceFamily);
        }

        if (!TextUtils.isEmpty(FinalPriceFamily)) {

            map.put("final_price_family", FinalPriceFamily);
        }

        if (!TextUtils.isEmpty(OriginalBoat)) {

            map.put("original_boat", OriginalBoat);
        }
        if (!TextUtils.isEmpty(FinalBoat)) {

            map.put("final_boat", FinalBoat);}
        if (!TextUtils.isEmpty(OriginalCar)) {

            map.put("original_car", OriginalCar);
        }
        if (!TextUtils.isEmpty(FinalCar)) {

            map.put("final_car", FinalCar);
        }
        map.put("ticket_theme_id", "");
        if (!TextUtils.isEmpty(Activity)) {
            map.put("ticket_activity_id", Activity);
        }
        if (!TextUtils.isEmpty(Other)) {
            map.put("ticket_other_id", Other);
        }

        map.put("uid", String.valueOf(PreferenceUtil.getInt(UID)));
        map.put("stick", String.valueOf(stick));
        if (caixian != 0) {
            map.put("line_or_throw", String.valueOf(caixian));
        }
        map.put("stick_new", String.valueOf(news));
        map.put("stick_low", String.valueOf(low));
        map.put("stick_better", String.valueOf(better));
        map.put("stick_throw", String.valueOf(shuaiwei));
        map.put("stick_rate", String.valueOf(rate));
        map.put("stick_return", String.valueOf(returns));
        map.put("stick_hot", String.valueOf(hot));
        map.put("stick_zeroC", String.valueOf(zeroC));
        map.put("token", PreferenceUtil.getString(TOKEN));

        map.put("generalize", "");
        if (!TextUtils.isEmpty(OriginalPriceTotal)) {

            map.put("original_price_total", OriginalPriceTotal);
        }
        if (!TextUtils.isEmpty(FinalPriceTotal)) {

            map.put("final_price_total", FinalPriceTotal);
        }
        if (!TextUtils.isEmpty(PreferenceUtil.getString(Constanst.TICKET_PROVINCE_CODE))) {
            map.put("ticket_pro_code", PreferenceUtil.getString(Constanst.TICKET_PROVINCE_CODE));
        }
        map.put("user_idForCol", String.valueOf(PreferenceUtil.getInt(UID)));

        KyLog.object(map);
        String url = "http://39.105.203.33/jlkf/mutual-trust/travel/issueTicket";


        httpUtil.postFileRequest(url, map, pathList, new MyStringCallBack() {

            @Override
            public void onError(Call call, Exception e, int id) {
                cancelProgressDialog();
                super.onError(call, e, id);
                KyLog.d(e + "");
                Toast.makeText(ReleaseTicketingActivity.this, "发布失败", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onResponse(String response, int id) {
                cancelProgressDialog();
                super.onResponse(response, id);
                KyLog.d(response);
                //返回图片的地址
                getUseInfo();
                Toast.makeText(ReleaseTicketingActivity.this, "发布成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ReleaseTicketingActivity.this, MainActivity.class);
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

    public void StartDatePickDialog(@DateParams.Type int... style) {
        Calendar todayCal = Calendar.getInstance();
        Calendar startCal = Calendar.getInstance();
        Calendar endCal = Calendar.getInstance();
        endCal.add(Calendar.YEAR, 6);

        new DatePickDialog.Builder()
                .setTypes(style)
                .setCurrentDate(todayCal.getTime())
                .setStartDate(startCal.getTime())
                .setEndDate(endCal.getTime())
                .setOnSureListener(new OnSureListener() {
                    @Override
                    public void onSure(Date date) {
                        startTime = new SimpleDateFormat("HH:mm").format(date);
                        if (ticket_type == 1) {
                            mEditTextStartTime.setText(startTime);
                        } else {
                            mEditTextStartTimeOther.setText(startTime);
                        }
                    }
                })
                .show(this);
    }

    public void EndDatePickDialog(@DateParams.Type int... style) {
        Calendar todayCal = Calendar.getInstance();
        Calendar startCal = Calendar.getInstance();
        Calendar endCal = Calendar.getInstance();
        endCal.add(Calendar.YEAR, 6);

        new DatePickDialog.Builder()
                .setTypes(style)
                .setCurrentDate(todayCal.getTime())
                .setStartDate(startCal.getTime())
                .setEndDate(endCal.getTime())
                .setOnSureListener(new OnSureListener() {
                    @Override
                    public void onSure(Date date) {
                        endTime = new SimpleDateFormat("HH:mm").format(date);
                        if (ticket_type == 1) {
                            mEditTextEndStart.setText(endTime);
                        } else {
                            mEditTextEndStartOther.setText(endTime);
                        }
                    }
                })
                .show(this);
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


    private void SetOtherEnabled() {
        if (PreferenceUtil.getInt(Constanst.TOP_ZHIDING) <= 0) {
            mRelativeLayoutStickBetterOther.setEnabled(false);
            mRelativeLayoutStickBetterOther.setFocusable(false);

            mRelativeLayoutStickHotOther.setEnabled(false);
            mRelativeLayoutStickHotOther.setFocusable(false);

            mRelativeLayoutStickLowOther.setEnabled(false);
            mRelativeLayoutStickLowOther.setFocusable(false);

            mRelativeLayoutStickNewOther.setEnabled(false);
            mRelativeLayoutStickNewOther.setFocusable(false);

            mRelativeLayoutStickRateOther.setEnabled(false);
            mRelativeLayoutStickRateOther.setFocusable(false);

            mRelativeLayoutStickReturnOther.setEnabled(false);
            mRelativeLayoutStickReturnOther.setFocusable(false);

            mRelativeLayoutStickThrowOther.setEnabled(false);
            mRelativeLayoutStickThrowOther.setFocusable(false);

            mRelativeLayoutStickZeroCOther.setEnabled(false);
            mRelativeLayoutStickZeroCOther.setFocusable(false);
        } else {
            mRelativeLayoutStickBetterOther.setEnabled(true);
            mRelativeLayoutStickBetterOther.setFocusable(true);

            mRelativeLayoutStickHotOther.setEnabled(true);
            mRelativeLayoutStickHotOther.setFocusable(true);

            mRelativeLayoutStickLowOther.setEnabled(true);
            mRelativeLayoutStickLowOther.setFocusable(true);

            mRelativeLayoutStickNewOther.setEnabled(true);
            mRelativeLayoutStickNewOther.setFocusable(true);

            mRelativeLayoutStickRateOther.setEnabled(true);
            mRelativeLayoutStickRateOther.setFocusable(true);

            mRelativeLayoutStickReturnOther.setEnabled(true);
            mRelativeLayoutStickReturnOther.setFocusable(true);

            mRelativeLayoutStickThrowOther.setEnabled(true);
            mRelativeLayoutStickThrowOther.setFocusable(true);

            mRelativeLayoutStickZeroCOther.setEnabled(true);
            mRelativeLayoutStickZeroCOther.setFocusable(true);
        }
    }

}
