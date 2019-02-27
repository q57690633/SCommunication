package com.huxin.communication.ui.house.release;

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
import com.huxin.communication.ReleaseTabAdapter;
import com.huxin.communication.base.BaseActivity;
import com.huxin.communication.controls.Constanst;
import com.huxin.communication.custom.ReleaseDialog;
import com.huxin.communication.entity.MyPopVlaues;
import com.huxin.communication.http.ApiModule;
import com.huxin.communication.ui.MainActivity;
import com.huxin.communication.utils.PreferenceUtil;
import com.huxin.communication.view.SpaceItemDecoration;
import com.sky.kylog.KyLog;

import java.util.ArrayList;
import java.util.List;


/**
 * 发布求购
 */
public class ReleaseBuyActivity extends BaseActivity implements View.OnClickListener {
    private EditText mEditTextvillageNam;
    private EditText mEditTextminPrice;
    private EditText mEditTextmaxPrice;
    private EditText mEditTextminAcreage;
    private EditText mEditTextmaxAcreage;
    private EditText mEditTextremark;
    private RelativeLayout mRelativeLayoutHouseType;
    private RelativeLayout mRelativeLayoutFloorAge;
    private RelativeLayout mRelativeLayoutPermit;
//    private RelativeLayout mRelativeLayoutouHseholdAppliances;
    private TextView mTextViewConfirm;
    private TextView mTextViewHouseType;
    private TextView mTextViewFloorAge;
    private TextView mTextViewPermit;
//    private TextView mTextViewHseholdAppliances;

    private ImageView mImageViewStick;
    private ImageView mImageViewStickClick;
    private ImageView mImageViewUnlimitedEstate;
    private ImageView mImageViewUnlimitedEstateClick;

    private String HouseType;
    private String FloorAge;
    private String Permit;
    private String HseholdAppliances;


    private ReleaseTabAdapter mTabAdapter;

    private RecyclerView mRecyclerView;
    private List<MyPopVlaues> Kouweilist;
    private List<MyPopVlaues> keshulist;
    private ReleaseDialog mReleaseDialog;

    private int unlimitedEstate = 0;

    private int stick = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_release_buy);
    }

    @Override
    protected void initViews() {
        setToolbarCenterMode("发布求购", MODE_BACK);
        mEditTextmaxAcreage = (EditText) findViewById(R.id.maxAcreage_rent_release);

        mEditTextvillageNam = (EditText) findViewById(R.id.villageName_rent_release);

        mEditTextminPrice = (EditText) findViewById(R.id.minPrice_rent_release);

        mEditTextmaxPrice = (EditText) findViewById(R.id.maxPrice_rent_release);

        mEditTextremark = (EditText) findViewById(R.id.remark_rent_release);

        mEditTextminAcreage = (EditText) findViewById(R.id.minAcreage_rent_release);

        mRelativeLayoutHouseType = (RelativeLayout) findViewById(R.id.rl_release1_type);

        mRelativeLayoutFloorAge = (RelativeLayout) findViewById(R.id.rl_release2_type);

        mRelativeLayoutPermit = (RelativeLayout) findViewById(R.id.rl_release3_type);

//        mRelativeLayoutouHseholdAppliances = (RelativeLayout) findViewById(R.id.rl_release_type);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_release_rent);

        mTextViewConfirm = (TextView) findViewById(R.id.confirm);

        mTextViewHouseType = (TextView) findViewById(R.id.release1_type);

        mTextViewFloorAge = (TextView) findViewById(R.id.release2_type);

        mTextViewPermit = (TextView) findViewById(R.id.release3_type);

//        mTextViewHseholdAppliances = (TextView) findViewById(R.id.release_type);

        mImageViewStick = (ImageView) findViewById(R.id.stick_rent_release);

        mImageViewStickClick = (ImageView) findViewById(R.id.stick_rent_release_click);

        mImageViewUnlimitedEstate = (ImageView) findViewById(R.id.unlimitedEstate_rent_release);

        mImageViewUnlimitedEstateClick = (ImageView) findViewById(R.id.unlimitedEstate_rent_release_click);

        mRelativeLayoutFloorAge.setOnClickListener(this);
        mRelativeLayoutHouseType.setOnClickListener(this);
        mRelativeLayoutPermit.setOnClickListener(this);
//        mRelativeLayoutouHseholdAppliances.setOnClickListener(this);
        mTextViewConfirm.setOnClickListener(this);

        mImageViewStick.setOnClickListener(this);
        mImageViewStickClick.setOnClickListener(this);
        mImageViewUnlimitedEstate.setOnClickListener(this);
        mImageViewUnlimitedEstateClick.setOnClickListener(this);
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        setTabData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_release1_type:
                //居室
                mReleaseDialog = new ReleaseDialog(this, setHouseType());
                mReleaseDialog.setCancelable(true);

                mReleaseDialog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        mTextViewHouseType.setText(setHouseType().get(position).getName());
                        HouseType = setHouseType().get(position).getName();
                        mReleaseDialog.cancel();
                    }
                });
                mReleaseDialog.show();
                break;
            case R.id.rl_release2_type:
                //楼龄
                mReleaseDialog = new ReleaseDialog(this, setFitment());
                mReleaseDialog.setCancelable(true);
                mReleaseDialog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        mTextViewFloorAge.setText(setFitment().get(position).getName());
                        FloorAge = setFitment().get(position).getName();
                        mReleaseDialog.cancel();
                    }
                });
                mReleaseDialog.show();

                break;
            case R.id.rl_release3_type:
                //房本情况
                mReleaseDialog = new ReleaseDialog(this, setPaymentType());
                mReleaseDialog.setCancelable(true);
                mReleaseDialog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        mTextViewPermit.setText(setPaymentType().get(position).getName());
                        Permit = setPaymentType().get(position).getName();
                        mReleaseDialog.cancel();
                    }
                });
                mReleaseDialog.show();
                break;
//            case R.id.rl_release_type:
//                //家具家电情况
//                mReleaseDialog = new ReleaseDialog(this, setOccupation());
//                mReleaseDialog.setCancelable(true);
//                mReleaseDialog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
////                        mTextViewHseholdAppliances.setText(setOccupation().get(position).getName());
//                        HseholdAppliances = setOccupation().get(position).getName();
//                        mReleaseDialog.cancel();
//                    }
//                });
//                mReleaseDialog.show();
//                break;
            case R.id.confirm:
                addWantedProduct();
                break;

            case R.id.stick_rent_release:
                stick = 2;
                mImageViewStick.setVisibility(View.GONE);
                mImageViewStickClick.setVisibility(View.VISIBLE);
                break;

            case R.id.stick_rent_release_click:
                stick = 1;
                mImageViewStick.setVisibility(View.VISIBLE);
                mImageViewStickClick.setVisibility(View.GONE);
                break;

            case R.id.unlimitedEstate_rent_release:
                unlimitedEstate = 0;
                mImageViewUnlimitedEstateClick.setVisibility(View.VISIBLE);
                mImageViewUnlimitedEstate.setVisibility(View.GONE);
                break;

            case R.id.unlimitedEstate_rent_release_click:
                unlimitedEstate = 1;
                mImageViewUnlimitedEstateClick.setVisibility(View.GONE);
                mImageViewUnlimitedEstate.setVisibility(View.VISIBLE);
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
        keshulist.add(new MyPopVlaues("不限"));
        keshulist.add(new MyPopVlaues("2年内"));
        keshulist.add(new MyPopVlaues("5年内"));
        keshulist.add(new MyPopVlaues("8年内"));
        keshulist.add(new MyPopVlaues("10年内"));
        keshulist.add(new MyPopVlaues("15年内"));
        keshulist.add(new MyPopVlaues("15年及以上"));
        return keshulist;
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

    private List<MyPopVlaues> setPaymentType() {
        Kouweilist = new ArrayList<MyPopVlaues>();
        Kouweilist.add(new MyPopVlaues("不限"));
        Kouweilist.add(new MyPopVlaues("不满两年"));
        Kouweilist.add(new MyPopVlaues("满两年"));
        Kouweilist.add(new MyPopVlaues("满五年"));

        return Kouweilist;
    }

    private void addWantedProduct() {
        String VillageName = mEditTextvillageNam.getText().toString().trim();
        String maxAcreage = mEditTextmaxAcreage.getText().toString().trim();
        String minPrice = mEditTextminPrice.getText().toString().trim();
        String maxPrice = mEditTextmaxPrice.getText().toString().trim();
        String minAcreage = mEditTextminAcreage.getText().toString().trim();
        String remark = mEditTextremark.getText().toString().trim();

        String tableId ;
        if (!TextUtils.isEmpty(PreferenceUtil.getString(Constanst.TAB_NMAE))){
            tableId = PreferenceUtil.getString(Constanst.TAB_NMAE);
        }else {
            tableId = "";

        }

        if (TextUtils.isEmpty(VillageName)  && TextUtils.isEmpty(Permit) && TextUtils.isEmpty(HouseType)){
            Toast.makeText(this, "请填写必填信息", Toast.LENGTH_SHORT).show();
            return;
        }
        showProgressDialog();
        ApiModule.getInstance().addBuyerProduct(VillageName, String.valueOf(unlimitedEstate), minPrice, maxPrice, minAcreage, maxAcreage, HouseType, FloorAge, Permit
                , remark, String.valueOf(stick), tableId)
                .subscribe(response -> {

                    cancelProgressDialog();
                    KyLog.d(response.getResultMsg());
                    Toast.makeText(this, response.getResultMsg(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this,MainActivity.class);
                    startActivity(intent);

                }, throwable -> {
                    KyLog.d(throwable.toString());
                    cancelProgressDialog();
                    Toast.makeText(this, throwable.getMessage().toString(), Toast.LENGTH_SHORT).show();
                });
    }

    private void setTabData() {
        ApiModule.getInstance().selectTab("3")
                .subscribe(selectTabEntity -> {
                    cancelProgressDialog();
                    if (selectTabEntity != null && selectTabEntity.getTag().size() > 0) {
                        GridLayoutManager manager = new GridLayoutManager(ReleaseBuyActivity.this, 4);
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
}
