package com.huxin.communication.ui.travel.release;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.huxin.communication.R;
import com.huxin.communication.adpter.TableTravelActivityAdapter;
import com.huxin.communication.adpter.TableTravelOverseasAdapter;
import com.huxin.communication.base.BaseActivity;
import com.huxin.communication.controls.Constanst;
import com.huxin.communication.entity.TabTravelNameEntity;
import com.huxin.communication.http.ApiModule;
import com.huxin.communication.ui.MainActivity;
import com.huxin.communication.ui.cammer.HttpUtil;
import com.huxin.communication.ui.cammer.ImagePickerAdapter;
import com.huxin.communication.ui.cammer.MyStringCallBack;
import com.huxin.communication.ui.cammer.SelectDialog;
import com.huxin.communication.utils.PreferenceUtil;
import com.huxin.communication.view.SpaceItemDecoration;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.sky.kylog.KyLog;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class ReleaseTicketingActivity extends BaseActivity implements View.OnClickListener ,ImagePickerAdapter.OnRecyclerViewItemClickListener{

    private TextView mTextViewSpot;
    private TextView mTextViewOther;
    private LinearLayout mLinearLayoutSpot;
    private LinearLayout mLinearLayoutOther;

    private EditText mEditTextTicketName;
    private EditText mEditTextTicketAddr;
    private EditText mEditTextStartTime;
    private EditText mEditTextEndStart;
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

    private int caixian = 0;

    public static final int IMAGE_ITEM_ADD = -1;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;

    private ArrayList<ImageItem> selImageList; //当前选择的所有图片
    private int maxImgCount = 9;               //允许选择图片最大数

    private RecyclerView mRecyclerViewAddPicture;
    private ImagePickerAdapter adapter;

    private HttpUtil httpUtil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_release_ticketing);
    }

    @Override
    protected void initViews() {
        setToolbarCenterMode("发布票务", MODE_BACK);
        mTextViewOther = (TextView) findViewById(R.id.other_tv);
        mTextViewSpot = (TextView) findViewById(R.id.spot_tv);
        mLinearLayoutSpot = (LinearLayout) findViewById(R.id.spot_rl);
        mLinearLayoutOther = (LinearLayout) findViewById(R.id.other_rl);

        mEditTextTicketName = (EditText) findViewById(R.id.ticket_name);
        mEditTextTicketAddr = (EditText) findViewById(R.id.ticket_addr);
        mEditTextStartTime = (EditText) findViewById(R.id.start_time);
        mEditTextEndStart = (EditText) findViewById(R.id.end_start);
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

        mRecyclerViewActivity = (RecyclerView) findViewById(R.id.ticket_activity);
        mRecyclerViewtheme = (RecyclerView) findViewById(R.id.ticket_theme);




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

        mRecyclerViewAddPicture = (RecyclerView) findViewById(R.id.recyclerView);


        mTextViewSpot.setOnClickListener(this);
        mTextViewOther.setOnClickListener(this);

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
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        selectTravelTab();
        httpUtil = new HttpUtil();
        selImageList = new ArrayList<>();
        adapter = new ImagePickerAdapter(this, selImageList, maxImgCount);
        adapter.setOnItemClickListener(this);

        mRecyclerViewAddPicture.setLayoutManager(new GridLayoutManager(this, 4));
        mRecyclerViewAddPicture.setHasFixedSize(true);
        mRecyclerViewAddPicture.setAdapter(adapter);
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
                break;
            case R.id.other_tv:
                mLinearLayoutOther.setVisibility(View.VISIBLE);
                mLinearLayoutSpot.setVisibility(View.GONE);
                mTextViewSpot.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewOther.setBackgroundResource(R.color.blue);
                mTextViewOther.setTextColor(getResources().getColor(R.color.white_ticketing));
                mTextViewSpot.setTextColor(getResources().getColor(R.color.register_font));
                break;
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
//            case R.id.lineOrThrow_shuaiWei:
//                break;
//            case R.id.lineOrThrow_caixian:
//                break;
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
                issueTicketForeignRoute();
                break;
        }
    }

    private void issueTicketForeignRoute() {
        String TicketName = mEditTextTicketName.getText().toString().trim();
        String EndStart = mEditTextEndStart.getText().toString().trim();
        String FinalBoat = mEditTextFinalBoat.getText().toString().trim();
        String FinalCar = mEditTextFinalCar.getText().toString().trim();
        String finalprice = mEditTextfinalprice.getText().toString().trim();
        String FinalPriceChild = mEditTextFinalPriceChild.getText().toString().trim();
        String FinalPriceEvening = mEditTextFinalPriceEvening.getText().toString().trim();
        String FinalPriceFamily = mEditTextFinalPriceFamily.getText().toString().trim();
        String FinalPriceParentChild = mEditTextFinalPriceParentChild.getText().toString().trim();
        String FinalPriceTotal = mEditTextFinalPriceTotal.getText().toString().trim();
        String OiginalPriceChild = mEditTextoOiginalPriceChild.getText().toString().trim();
        String OriginalBoat = mEditTextOriginalBoat.getText().toString().trim();
        String OriginalCar = mEditTextOriginalCar.getText().toString().trim();
        String originalprice = mEditTextoriginalprice.getText().toString().trim();
        String OriginalPriceEvening = mEditTextOriginalPriceEvening.getText().toString().trim();
        String OriginalPriceFamily = mEditTextOriginalPriceFamily.getText().toString().trim();
        String OriginalPriceParentChild = mEditTextOriginalPriceParentChild.getText().toString().trim();
        String OriginalPriceTotal = mEditTextOriginalPriceTotal.getText().toString().trim();
        String StartTime = mEditTextStartTime.getText().toString().trim();
        String TicketAddr = mEditTextTicketAddr.getText().toString().trim();

        if (news == 0 && low == 0 && better == 0 && shuaiwei == 0 && rate == 0 && returns == 0 && hot == 0 && zeroC == 0) {
            stick = 2;
        } else {
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

        ApiModule.getInstance().issueTicketForeignRoute("", "", TicketName, TicketAddr, "1",
                StartTime + "~" + EndStart, originalprice, finalprice, OiginalPriceChild, FinalPriceChild, OriginalPriceEvening, FinalPriceEvening,
                OriginalPriceParentChild, FinalPriceParentChild, OriginalPriceFamily, FinalPriceFamily, OriginalBoat, FinalBoat, OriginalCar, FinalCar,
                null, null, null, String.valueOf(stick), String.valueOf(caixian),
                null, String.valueOf(news), String.valueOf(low), String.valueOf(better), String.valueOf(shuaiwei), String.valueOf(rate), String.valueOf(returns), String.valueOf(hot),
                String.valueOf(zeroC), null,
                OriginalPriceTotal, FinalPriceTotal, "")
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
                    setThemeListData(tabTravelNameEntity.getOverseaslist(), mRecyclerViewtheme);


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

    private void setThemeListData(List<TabTravelNameEntity.OverseaslistBean> list, RecyclerView recyclerView) {
        if (list.size() > 0) {
            GridLayoutManager manager = new GridLayoutManager(this, 5);
            TableTravelOverseasAdapter mAdapterTableName = new TableTravelOverseasAdapter(list, this);
            recyclerView.setAdapter(mAdapterTableName);
            recyclerView.setLayoutManager(manager);
            recyclerView.addItemDecoration(new SpaceItemDecoration(0, 15));
        }
    }

    /**
     * 打开相机
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
                        intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS,true); // 是否是直接打开相机
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
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        KyLog.d(requestCode  + "");
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (data != null && requestCode == REQUEST_CODE_SELECT) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images != null){
                    selImageList.addAll(images);
                    adapter.setImages(selImageList);
                }
            }
        } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
            //预览图片返回
            if (data != null && requestCode == REQUEST_CODE_PREVIEW) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
                if (images != null){
                    selImageList.clear();
                    selImageList.addAll(images);
                    adapter.setImages(selImageList);
                }
            }
        }
    }

    private String url="http://39.105.203.33/jlkf/mutual-trust/public/addRentProduct";

    /**
     * 上传图片
     * @param pathList
     */
    private void uploadImage(ArrayList<ImageItem> pathList) {
        httpUtil.postFileRequest(url, null, pathList, new MyStringCallBack() {

            @Override
            public void onError(Call call, Exception e, int id) {
                super.onError(call, e, id);
                KyLog.d(e + "");
            }

            @Override
            public void onResponse(String response, int id) {
                super.onResponse(response, id);
                //返回图片的地址
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
}
