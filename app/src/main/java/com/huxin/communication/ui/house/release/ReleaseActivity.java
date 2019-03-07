package com.huxin.communication.ui.house.release;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.huxin.communication.R;
import com.huxin.communication.ReleaseTabAdapter;
import com.huxin.communication.adpter.SelectByLikeAdapter;
import com.huxin.communication.base.BaseActivity;
import com.huxin.communication.controls.Constanst;
import com.huxin.communication.custom.ReleaseDialog;
import com.huxin.communication.entity.MyPopVlaues;
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

/**
 * 发布出售
 */
public class ReleaseActivity extends BaseActivity implements View.OnClickListener, ImagePickerAdapter.OnRecyclerViewItemClickListener {


    private EditText mEditTextVillageName;
    private EditText mEditTextAcreage;
    private TextView mEditTextHouseType;
    private EditText mEditTextTotalPrice;
    private EditText mEditTextFloorNumber;
    private EditText mEditTexTotalFloorNumber;
    private ImageView mEditTextNew;
    private ImageView mEditTextOld;
    private ImageView mEditTextLoans;
    private ImageView mEditTextKeying;
    private ImageView mEditTextNewClick;
    private ImageView mEditTextOldClick;
    private ImageView mEditTextLoansClick;
    private ImageView mEditTextKeyingClick;
    private ImageView mImageViewStick;

    private EditText mEditTextTitle;
    private EditText mEditTextHouseNumber;
    private EditText mEditTextPdu;
    private EditText mEditTextFloorSize;


    private TextView mTextViewHouseHoldAppliances;
    private TextView mTextViewFitment;
    private TextView mTextViewPermit;
    private TextView mTextViewOrientation;
    private TextView mTextViewPurpose;
    private TextView mTextViewConfirm;
    private TextView mTextViewExclusive;

    private List<MyPopVlaues> Kouweilist;
    private List<MyPopVlaues> keshulist;
    private String houseHoldAppliances;
    private String fitment;
    private String permit;
    private String orientation;
    private String purpose;
    private String houseType;
    private boolean isclicked = true;
    private boolean isclickeds = true;
    private boolean isclickedStick = true;

    private RecyclerView mRecyclerView;
    private ReleaseTabAdapter mTabAdapter;
    private ReleaseDialog mReleaseDialog;

    private int NeworOld = 2;
    private int loans = 1;
    private int keying = 1;
    private int exclusive = 2;
    private int stick = 1;

    public static final int IMAGE_ITEM_ADD = -1;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;

    private ArrayList<ImageItem> selImageList; //当前选择的所有图片
    private int maxImgCount = 9;               //允许选择图片最大数

    private RecyclerView mRecyclerViewAddPicture;
    private ImagePickerAdapter adapter;

    private HttpUtil httpUtil;

    private StringBuffer stringBuffer = new StringBuffer();

    private RecyclerView mRecyclerViewSearch;
    private LinearLayout mLinearLayoutVillageNameSearch;
    private LinearLayout mLinearLayoutVillageName;
    private TextView mTextViewAddVillageName;
    private SelectByLikeAdapter mSelectBylikeAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_release);
    }

    @Override
    protected void initViews() {
        setToolbarCenterMode("发布出售", MODE_BACK);
        mEditTextAcreage = (EditText) findViewById(R.id.acreage_ed_release);
        mEditTextVillageName = (EditText) findViewById(R.id.villageName_ed_release);
        mEditTextHouseType = (TextView) findViewById(R.id.houseType_ed_release);
        mEditTextTotalPrice = (EditText) findViewById(R.id.totalPrice_ed_release);
        mEditTextFloorNumber = (EditText) findViewById(R.id.floorNumber_ed_release);
        mEditTexTotalFloorNumber = (EditText) findViewById(R.id.totalFloorNumber_ed_release);

        mEditTextNew = (ImageView) findViewById(R.id.new_tv_release);
        mEditTextOld = (ImageView) findViewById(R.id.Old_tv_release);
        mEditTextLoans = (ImageView) findViewById(R.id.loans_tv_release);
        mEditTextKeying = (ImageView) findViewById(R.id.keying_tv_release);

        mEditTextNewClick = (ImageView) findViewById(R.id.new_tv_release_click);
        mEditTextOldClick = (ImageView) findViewById(R.id.Old_tv_release_click);
        mEditTextLoansClick = (ImageView) findViewById(R.id.loans_tv_release_click);
        mEditTextKeyingClick = (ImageView) findViewById(R.id.keying_tv_release_click);

        mEditTextTitle = (EditText) findViewById(R.id.title_ed_release);
        mEditTextHouseNumber = (EditText) findViewById(R.id.houseNumber_ed_release);
        mEditTextPdu = (EditText) findViewById(R.id.pdu_ed_release);
        mEditTextFloorSize = (EditText) findViewById(R.id.floorSize_ed_release);

        mTextViewHouseHoldAppliances = (TextView) findViewById(R.id.Occupation_type);
        mTextViewFitment = (TextView) findViewById(R.id.release_type);
        mTextViewPermit = (TextView) findViewById(R.id.release1_type);
        mTextViewOrientation = (TextView) findViewById(R.id.release2_type);
        mTextViewPurpose = (TextView) findViewById(R.id.release3_type);
        mTextViewConfirm = (TextView) findViewById(R.id.confirm);

        mTextViewExclusive = (TextView) findViewById(R.id.exclusive_ed_release);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_release_sell);

        mRecyclerViewAddPicture = (RecyclerView) findViewById(R.id.recyclerView);

        mRecyclerViewSearch = (RecyclerView) findViewById(R.id.villagename_search_recycler);
        mLinearLayoutVillageName = (LinearLayout) findViewById(R.id.village_search_layout);
        mLinearLayoutVillageNameSearch = (LinearLayout) findViewById(R.id.villageName_search);
        mTextViewAddVillageName = (TextView) findViewById(R.id.add_village_name);
        mImageViewStick = findViewById(R.id.stick_ed_release);



        mTextViewHouseHoldAppliances.setOnClickListener(this);
        mTextViewFitment.setOnClickListener(this);
        mTextViewPermit.setOnClickListener(this);
        mTextViewOrientation.setOnClickListener(this);
        mTextViewPurpose.setOnClickListener(this);
        mEditTextHouseType.setOnClickListener(this);
        mTextViewConfirm.setOnClickListener(this);

        mEditTextNew.setOnClickListener(this);
        mEditTextOld.setOnClickListener(this);
        mEditTextLoans.setOnClickListener(this);
        mEditTextKeying.setOnClickListener(this);
        mEditTextNewClick.setOnClickListener(this);
        mEditTextOldClick.setOnClickListener(this);
        mEditTextLoansClick.setOnClickListener(this);
        mEditTextKeyingClick.setOnClickListener(this);
        mTextViewExclusive.setOnClickListener(this);

        mTextViewAddVillageName.setOnClickListener(this);
        mImageViewStick.setOnClickListener(this);

    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        setTabData();
        httpUtil = new HttpUtil();
        selImageList = new ArrayList<>();
        adapter = new ImagePickerAdapter(this, selImageList, maxImgCount);
        adapter.setOnItemClickListener(this);

        mRecyclerViewAddPicture.setLayoutManager(new GridLayoutManager(this, 4));
        mRecyclerViewAddPicture.setHasFixedSize(true);
        mRecyclerViewAddPicture.setAdapter(adapter);

        mEditTextVillageName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String villageName = mEditTextVillageName.getText().toString().trim();
                Toast.makeText(ReleaseActivity.this, villageName, Toast.LENGTH_SHORT).show();
                if (TextUtils.isEmpty(villageName)) {
                    mLinearLayoutVillageName.setVisibility(View.GONE);
                    mLinearLayoutVillageNameSearch.setVisibility(View.VISIBLE);

                } else {
                    Toast.makeText(ReleaseActivity.this, villageName, Toast.LENGTH_SHORT).show();
                    mLinearLayoutVillageName.setVisibility(View.VISIBLE);
                    mLinearLayoutVillageNameSearch.setVisibility(View.GONE);
                    selectByLike(villageName);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Occupation_type:
                mReleaseDialog = new ReleaseDialog(this, setOccupation());
                mReleaseDialog.setCancelable(true);

                mReleaseDialog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        mTextViewHouseHoldAppliances.setText(setOccupation().get(position).getName());
                        houseHoldAppliances = setOccupation().get(position).getName();
                        mReleaseDialog.cancel();
                    }
                });
                mReleaseDialog.show();

                break;
            case R.id.release_type:
                mReleaseDialog = new ReleaseDialog(this, setFitment());
                mReleaseDialog.setCancelable(true);

                mReleaseDialog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        mTextViewFitment.setText(setFitment().get(position).getName());
                        fitment = setFitment().get(position).getName();
                        mReleaseDialog.cancel();
                    }
                });
                mReleaseDialog.show();
                break;
            case R.id.release1_type:
                mReleaseDialog = new ReleaseDialog(this, setPermit());
                mReleaseDialog.setCancelable(true);

                mReleaseDialog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        mTextViewPermit.setText(setPermit().get(position).getName());
                        permit = setPermit().get(position).getName();
                        mReleaseDialog.cancel();
                    }
                });
                mReleaseDialog.show();
                break;
            case R.id.release2_type:
                mReleaseDialog = new ReleaseDialog(this, setOrientation());
                mReleaseDialog.setCancelable(true);

                mReleaseDialog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        mTextViewOrientation.setText(setOrientation().get(position).getName());
                        orientation = setOrientation().get(position).getName();
                        mReleaseDialog.cancel();
                    }
                });
                mReleaseDialog.show();
                break;
            case R.id.release3_type:

                mReleaseDialog = new ReleaseDialog(this, setPurpose());
                mReleaseDialog.setCancelable(true);

                mReleaseDialog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        mTextViewPurpose.setText(setPurpose().get(position).getName());
                        purpose = setPurpose().get(position).getName();
                        mReleaseDialog.cancel();
                    }
                });
                mReleaseDialog.show();
                break;
            case R.id.houseType_ed_release:
                mReleaseDialog = new ReleaseDialog(this, setHouseType());
                mReleaseDialog.setCancelable(true);

                mReleaseDialog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        mEditTextHouseType.setText(setHouseType().get(position).getName());
                        houseType = setHouseType().get(position).getName();
                        mReleaseDialog.cancel();
                    }
                });
                mReleaseDialog.show();
                break;

            case R.id.confirm:

                addSaleProduct();
                uploadImage(selImageList);
                break;
            case R.id.new_tv_release:
                mEditTextNewClick.setVisibility(View.VISIBLE);
                mEditTextOld.setVisibility(View.VISIBLE);
                mEditTextNew.setVisibility(View.GONE);
                mEditTextOldClick.setVisibility(View.GONE);
                NeworOld = 1;
                KyLog.d("new_tv_release");
                break;
            case R.id.Old_tv_release:
                mEditTextNew.setVisibility(View.VISIBLE);
                mEditTextOldClick.setVisibility(View.VISIBLE);
                mEditTextNewClick.setVisibility(View.GONE);
                mEditTextOld.setVisibility(View.GONE);
                NeworOld = 2;
                KyLog.d("Old_tv_release");

                break;
            case R.id.loans_tv_release:
                mEditTextLoans.setVisibility(View.GONE);
                mEditTextLoansClick.setVisibility(View.VISIBLE);
                loans = 1;
                break;
            case R.id.keying_tv_release:
                mEditTextKeying.setVisibility(View.GONE);
                mEditTextKeyingClick.setVisibility(View.VISIBLE);
                keying = 1;
                break;
            case R.id.new_tv_release_click:
                mEditTextNewClick.setVisibility(View.GONE);
                mEditTextOld.setVisibility(View.GONE);
                mEditTextNew.setVisibility(View.VISIBLE);
                mEditTextOldClick.setVisibility(View.VISIBLE);
//                mEditTextNew.setBackgroundResource(R.drawable.icon_circle_selected);
//                mEditTextOld.setBackgroundResource(R.drawable.icon_circle_normal);
                NeworOld = 1;
                KyLog.d("new_tv_release");
                break;
            case R.id.Old_tv_release_click:
                mEditTextNewClick.setVisibility(View.VISIBLE);
                mEditTextOld.setVisibility(View.VISIBLE);
                mEditTextNew.setVisibility(View.GONE);
                mEditTextOldClick.setVisibility(View.GONE);
//                mEditTextNew.setBackgroundResource(R.drawable.icon_circle_normal);
//                mEditTextOld.setBackgroundResource(R.drawable.icon_circle_selected);
                NeworOld = 2;
                KyLog.d("Old_tv_release");

                break;
            case R.id.loans_tv_release_click:
                mEditTextLoans.setVisibility(View.VISIBLE);
                mEditTextLoansClick.setVisibility(View.GONE);

                isclicked = false;
                loans = 2;

                break;
            case R.id.keying_tv_release_click:
                mEditTextKeying.setVisibility(View.VISIBLE);
                mEditTextKeyingClick.setVisibility(View.GONE);

                keying = 2;
                break;

            case R.id.exclusive_ed_release:
                if (isclicked) {
                    mTextViewExclusive.setBackgroundColor(getResources().getColor(R.color.red_setting));
                    mTextViewExclusive.setTextColor(getResources().getColor(R.color.white));
                    isclicked = false;

                } else {
                    mTextViewExclusive.setBackgroundColor(getResources().getColor(R.color.login_forget_password_code_fort));
                    mTextViewExclusive.setTextColor(getResources().getColor(R.color.blank));
                    isclicked = false;
                }
                break;
            case R.id.add_village_name:
                Intent intent = new Intent(this, AddVillageNameActivity.class);
                startActivity(intent);
                break;
            case R.id.stick_ed_release:
                if (isclickedStick){
                    mImageViewStick.setBackgroundResource(R.drawable.icon_circle_selected);
                    isclickedStick = false;
                    stick = 1;
                }else {
                    mImageViewStick.setBackgroundResource(R.drawable.icon_circle_normal);
                    isclickedStick = true;
                    stick = 2;
                }
                break;


        }
    }


    private List<MyPopVlaues> setOccupation() {
        Kouweilist = new ArrayList<MyPopVlaues>();
        Kouweilist.add(new MyPopVlaues("家具家电齐全"));
        Kouweilist.add(new MyPopVlaues("无家具无家电"));
        Kouweilist.add(new MyPopVlaues("有家具无家电"));
        Kouweilist.add(new MyPopVlaues("无家具有家电"));

        return Kouweilist;
    }

    private List<MyPopVlaues> setFitment() {
        keshulist = new ArrayList<MyPopVlaues>();
        keshulist.add(new MyPopVlaues("毛坯"));
        keshulist.add(new MyPopVlaues("简装"));
        keshulist.add(new MyPopVlaues("精装"));
        keshulist.add(new MyPopVlaues("豪装"));
        keshulist.add(new MyPopVlaues("未交房"));
        return keshulist;
    }

    private List<MyPopVlaues> setPermit() {
        Kouweilist = new ArrayList<MyPopVlaues>();
        Kouweilist.add(new MyPopVlaues("未知"));
        Kouweilist.add(new MyPopVlaues("不满两年"));
        Kouweilist.add(new MyPopVlaues("满两年"));
        Kouweilist.add(new MyPopVlaues("满五年"));
        return Kouweilist;
    }


    private List<MyPopVlaues> setOrientation() {
        Kouweilist = new ArrayList<MyPopVlaues>();
        Kouweilist.add(new MyPopVlaues("南北"));
        Kouweilist.add(new MyPopVlaues("朝南"));
        Kouweilist.add(new MyPopVlaues("西南"));
        Kouweilist.add(new MyPopVlaues("东南"));
        Kouweilist.add(new MyPopVlaues("朝东"));
        Kouweilist.add(new MyPopVlaues("朝西"));
        Kouweilist.add(new MyPopVlaues("朝北"));
        Kouweilist.add(new MyPopVlaues("东北"));
        Kouweilist.add(new MyPopVlaues("西北"));


        return Kouweilist;
    }

    private List<MyPopVlaues> setPurpose() {
        Kouweilist = new ArrayList<MyPopVlaues>();
        Kouweilist.add(new MyPopVlaues("普通住宅"));
        Kouweilist.add(new MyPopVlaues("商业"));
        Kouweilist.add(new MyPopVlaues("商住两用"));
        Kouweilist.add(new MyPopVlaues("有独院"));
        Kouweilist.add(new MyPopVlaues("其他"));

        return Kouweilist;
    }


    private List<MyPopVlaues> setHouseType() {
        Kouweilist = new ArrayList<MyPopVlaues>();
        Kouweilist.add(new MyPopVlaues("一室"));
        Kouweilist.add(new MyPopVlaues("两室"));
        Kouweilist.add(new MyPopVlaues("三室"));
        Kouweilist.add(new MyPopVlaues("四室"));
        Kouweilist.add(new MyPopVlaues("五室及以上"));

        return Kouweilist;
    }

    private void addSaleProduct() {
        String VillageName = mEditTextVillageName.getText().toString().trim();
        String Acreage = mEditTextAcreage.getText().toString().trim();
        String totalPrice = mEditTextTotalPrice.getText().toString().trim();
        String floorNumber = mEditTextFloorNumber.getText().toString().trim();
        String totalFloorNumber = mEditTexTotalFloorNumber.getText().toString().trim();
        String title = mEditTextTitle.getText().toString().trim();
        String houseNumber = mEditTextHouseNumber.getText().toString().trim();
        String pdu = mEditTextPdu.getText().toString().trim();
        String floorSize = mEditTextFloorSize.getText().toString().trim();
        String tableId ;
        if (!TextUtils.isEmpty(PreferenceUtil.getString(Constanst.TAB_NMAE))){
            tableId = PreferenceUtil.getString(Constanst.TAB_NMAE);


        }else {
            tableId = "";

        }

        KyLog.d(loans + "");
        KyLog.d(keying + "");
        KyLog.d(VillageName + "");
        KyLog.d(Acreage + "");
        KyLog.d(totalPrice + "");
        KyLog.d(floorNumber + "");
        KyLog.d(totalFloorNumber + "");
        KyLog.d(title + "");
        KyLog.d(houseNumber + "");
        KyLog.d(pdu + "");
        KyLog.d(floorSize + "");

        KyLog.d(houseHoldAppliances + "");
        KyLog.d(fitment + "");
        KyLog.d(permit + "");
        KyLog.d(orientation + "");
        KyLog.d(purpose + "");
        KyLog.d(houseType + "");
//        KyLog.d(tableId.substring(1, tableId.length() - 1) + "");

        if (TextUtils.isEmpty(VillageName) && TextUtils.isEmpty(Acreage) && TextUtils.isEmpty(totalPrice) && TextUtils.isEmpty(houseType)){
            Toast.makeText(this, "请填写必填信息", Toast.LENGTH_SHORT).show();
            return;
        }


            showProgressDialog();
        ApiModule.getInstance().addSaleProduct(VillageName, Acreage, houseType, totalPrice
                , floorNumber, totalFloorNumber, String.valueOf(NeworOld), String.valueOf(loans), String.valueOf(keying),
                houseHoldAppliances, fitment, permit, orientation, purpose, title, String.valueOf(stick),
                "2", "2", pdu, floorSize, tableId)
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

    private void setTabData() {
        ApiModule.getInstance().selectTab("1")
                .subscribe(selectTabEntity -> {
                    cancelProgressDialog();
                    if (selectTabEntity != null && selectTabEntity.getTag().size() > 0) {
                        GridLayoutManager manager = new GridLayoutManager(ReleaseActivity.this, 4);
                        mTabAdapter = new ReleaseTabAdapter(selectTabEntity.getTag(), this);
                        mRecyclerView.setAdapter(mTabAdapter);
                        mRecyclerView.setLayoutManager(manager);
                        mRecyclerView.addItemDecoration(new SpaceItemDecoration(0, 15));
                    }

                }, throwable -> {
                    KyLog.d(throwable.toString());
                    cancelProgressDialog();
                    Toast.makeText(this, throwable.getMessage().toString(), Toast.LENGTH_SHORT).show();
                });
    }


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
                        Intent intent = new Intent(ReleaseActivity.this, ImageGridActivity.class);
                        intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                        startActivityForResult(intent, REQUEST_CODE_SELECT);
                        break;
                    case 1:
                        //打开选择,本次允许选择的数量
                        ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                        Intent intent1 = new Intent(ReleaseActivity.this, ImageGridActivity.class);
                        startActivityForResult(intent1, REQUEST_CODE_SELECT);
                        break;
                    default:
                        break;
                }
            }
        }, names);
    }

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



    private void uploadImage(ArrayList<ImageItem> pathList) {

        String url = "http://39.105.203.33/jlkf/mutual-trust/public/addSaleProduct";
        httpUtil.postFileRequest(url, null, pathList, new MyStringCallBack() {

            @Override
            public void onError(Call call, Exception e, int id) {
                super.onError(call, e, id);
            }

            @Override
            public void onResponse(String response, int id) {
                super.onResponse(response, id);
                KyLog.d(response);
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

    private void selectByLike(String villageName) {
        KyLog.d(villageName);
        showProgressDialog();
        ApiModule.getInstance().selectByLike(villageName)
                .subscribe(selectByLikeEntities -> {
                    cancelProgressDialog();
                    if (selectByLikeEntities != null && selectByLikeEntities.size() > 0) {
                        LinearLayoutManager manager = new LinearLayoutManager(ReleaseActivity.this);
                        mSelectBylikeAdapter = new SelectByLikeAdapter(selectByLikeEntities, this);
                        mRecyclerViewSearch.setAdapter(mSelectBylikeAdapter);
                        mRecyclerViewSearch.setLayoutManager(manager);
                        mRecyclerViewSearch.addItemDecoration(new SpaceItemDecoration(0, 15));
                        mSelectBylikeAdapter.setOnMyItemClickListener(new SelectByLikeAdapter.OnMyItemClickListener() {
                            @Override
                            public void myClick(View v, int pos) {
                                Toast.makeText(ReleaseActivity.this, selectByLikeEntities.get(pos).getVillageName(), Toast.LENGTH_SHORT).show();
                                mEditTextVillageName.setText(selectByLikeEntities.get(pos).getVillageName());
                                mLinearLayoutVillageName.setVisibility(View.GONE);
                                mLinearLayoutVillageNameSearch.setVisibility(View.VISIBLE);
                            }
                        });
                    }

                }, throwable -> {
                    KyLog.d(throwable.toString());
                    cancelProgressDialog();
                    Toast.makeText(this, throwable.getMessage().toString(), Toast.LENGTH_SHORT).show();
                });
    }
}
