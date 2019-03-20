package com.huxin.communication.ui.house.release;

import android.app.Activity;
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
import android.widget.RelativeLayout;
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
import com.huxin.communication.ui.cammer.MyStringCallBack;
import com.huxin.communication.utils.PreferenceUtil;
import com.huxin.communication.view.SpaceItemDecoration;
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


/**
 * 发布求租
 */
public class ReleaseRentActivity extends BaseActivity implements View.OnClickListener {
    private EditText mEditTextvillageNam;
    private EditText mEditTextminPrice;
    private EditText mEditTextmaxPrice;
    private EditText mEditTextminAcreage;
    private EditText mEditTextmaxAcreage;
    private EditText mEditTextremark;
    private RelativeLayout mRelativeLayoutHouseType;
    private RelativeLayout mRelativeLayoutFitment;
    private RelativeLayout mRelativeLayoutPaymentType;
    private RelativeLayout mRelativeLayoutouHseholdAppliances;
    private TextView mTextViewConfirm;
    private TextView mTextViewHouseType;
    private TextView mTextViewFitment;
    private TextView mTextViewPaymentType;
    private TextView mTextViewHseholdAppliances;

    private ImageView mImageViewStick;
    private ImageView mImageViewStickClick;
    private ImageView mImageViewUnlimitedEstate;
    private ImageView mImageViewUnlimitedEstateClick;

    private String HouseType;
    private String fitment;
    private String PaymentType;
    private String HseholdAppliances;


    private ReleaseTabAdapter mTabAdapter;

    private RecyclerView mRecyclerView;
    private List<MyPopVlaues> Kouweilist;
    private List<MyPopVlaues> keshulist;
    private ReleaseDialog mReleaseDialog;

    private int unlimitedEstate = 0;

    private int stick = 2;

    private RecyclerView mRecyclerViewSearch;
    private LinearLayout mLinearLayoutVillageNameSearch;
    private LinearLayout mLinearLayoutVillageName;
    private TextView mTextViewAddVillageName;
    private SelectByLikeAdapter mSelectBylikeAdapter;

    private HttpUtil httpUtil;

    private String type = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = getIntent().getStringExtra("type");

    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_release_rent);
        mEditTextmaxAcreage = (EditText) findViewById(R.id.maxAcreage_rent_release);

        mEditTextvillageNam = (EditText) findViewById(R.id.villageName_rent_release);

        mEditTextminPrice = (EditText) findViewById(R.id.minPrice_rent_release);

        mEditTextmaxPrice = (EditText) findViewById(R.id.maxPrice_rent_release);

        mEditTextremark = (EditText) findViewById(R.id.remark_rent_release);

        mEditTextminAcreage = (EditText) findViewById(R.id.minAcreage_rent_release);

        mRelativeLayoutHouseType = (RelativeLayout) findViewById(R.id.rl_release1_type);

        mRelativeLayoutFitment = (RelativeLayout) findViewById(R.id.rl_release2_type);

        mRelativeLayoutPaymentType = (RelativeLayout) findViewById(R.id.rl_Occupation_type);

        mRelativeLayoutouHseholdAppliances = (RelativeLayout) findViewById(R.id.rl_release_type);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_release_rent);

        mTextViewConfirm = (TextView) findViewById(R.id.confirm);

        mTextViewHouseType = (TextView) findViewById(R.id.release1_type);

        mTextViewFitment = (TextView) findViewById(R.id.release2_type);

        mTextViewPaymentType = (TextView) findViewById(R.id.Occupation_type);

        mTextViewHseholdAppliances = (TextView) findViewById(R.id.release_type);

        mImageViewStick = (ImageView) findViewById(R.id.stick_rent_release);

        mImageViewStickClick = (ImageView) findViewById(R.id.stick_rent_release_click);

        mImageViewUnlimitedEstate = (ImageView) findViewById(R.id.unlimitedEstate_rent_release);

        mImageViewUnlimitedEstateClick = (ImageView) findViewById(R.id.unlimitedEstate_rent_release_click);

        mRecyclerViewSearch = (RecyclerView) findViewById(R.id.villagename_search_recycler);
        mLinearLayoutVillageName = (LinearLayout) findViewById(R.id.village_search_layout);
        mLinearLayoutVillageNameSearch = (LinearLayout) findViewById(R.id.villageName_search);
        mTextViewAddVillageName = (TextView) findViewById(R.id.add_village_name);

        mRelativeLayoutFitment.setOnClickListener(this);
        mRelativeLayoutHouseType.setOnClickListener(this);
        mRelativeLayoutPaymentType.setOnClickListener(this);
        mRelativeLayoutouHseholdAppliances.setOnClickListener(this);
        mTextViewConfirm.setOnClickListener(this);

        mImageViewStick.setOnClickListener(this);
        mImageViewStickClick.setOnClickListener(this);
        mImageViewUnlimitedEstate.setOnClickListener(this);
        mImageViewUnlimitedEstateClick.setOnClickListener(this);

        mTextViewAddVillageName.setOnClickListener(this);

    }

    @Override
    protected void initViews() {
        setToolbarCenterMode("发布求租", MODE_BACK);
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        httpUtil = new HttpUtil();

        setTabData();

        mEditTextvillageNam.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String villageName = mEditTextvillageNam.getText().toString().trim();
                if (TextUtils.isEmpty(villageName)) {
                    mLinearLayoutVillageName.setVisibility(View.GONE);
                    mLinearLayoutVillageNameSearch.setVisibility(View.VISIBLE);

                } else {
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
                //装修
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
            case R.id.rl_Occupation_type:
                //付款方式
                mReleaseDialog = new ReleaseDialog(this, setPaymentType());
                mReleaseDialog.setCancelable(true);
                mReleaseDialog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        mTextViewPaymentType.setText(setPaymentType().get(position).getName());
                        PaymentType = setPaymentType().get(position).getName();
                        mReleaseDialog.cancel();
                    }
                });
                mReleaseDialog.show();
                break;
            case R.id.rl_release_type:
                //家具家电情况
                mReleaseDialog = new ReleaseDialog(this, setOccupation());
                mReleaseDialog.setCancelable(true);
                mReleaseDialog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        mTextViewHseholdAppliances.setText(setOccupation().get(position).getName());
                        HseholdAppliances = setOccupation().get(position).getName();
                        mReleaseDialog.cancel();
                    }
                });
                mReleaseDialog.show();
                break;
            case R.id.confirm:
//                addWantedProduct();
                uploadImage();
                break;

            case R.id.stick_rent_release:
                stick = 1;
                mImageViewStick.setVisibility(View.GONE);
                mImageViewStickClick.setVisibility(View.VISIBLE);
                break;

            case R.id.stick_rent_release_click:
                stick = 2;
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
            case R.id.add_village_name:
                Intent intent = new Intent(this, AddVillageNameActivity.class);
                startActivity(intent);
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
        Kouweilist.add(new MyPopVlaues("押一付一"));
        Kouweilist.add(new MyPopVlaues("押一付三"));
        Kouweilist.add(new MyPopVlaues("押一付六"));
        Kouweilist.add(new MyPopVlaues("押一年付"));

        return Kouweilist;
    }

    private void addWantedProduct() {
        String VillageName = mEditTextvillageNam.getText().toString().trim();
        String maxAcreage = mEditTextmaxAcreage.getText().toString().trim();
        String minPrice = mEditTextminPrice.getText().toString().trim();
        String maxPrice = mEditTextmaxPrice.getText().toString().trim();
        String minAcreage = mEditTextminAcreage.getText().toString().trim();
        String remark = mEditTextremark.getText().toString().trim();
        String tableId;
        if (!TextUtils.isEmpty(PreferenceUtil.getString(Constanst.TAB_NMAE))) {
            tableId = PreferenceUtil.getString(Constanst.TAB_NMAE);

        } else {
            tableId = "";

        }

        if (TextUtils.isEmpty(VillageName) && TextUtils.isEmpty(fitment) && TextUtils.isEmpty(HouseType)) {
            Toast.makeText(this, "请填写必填信息", Toast.LENGTH_SHORT).show();
            return;
        }

        showProgressDialog();
        ApiModule.getInstance().addWantedProduct(VillageName, String.valueOf(unlimitedEstate), minPrice, maxPrice, minAcreage, maxAcreage, HouseType, fitment, PaymentType
                , HseholdAppliances, remark, String.valueOf(stick), tableId)
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
        ApiModule.getInstance().selectTab("4")
                .subscribe(selectTabEntity -> {
                    cancelProgressDialog();
                    if (selectTabEntity != null && selectTabEntity.getTag().size() > 0) {
                        GridLayoutManager manager = new GridLayoutManager(ReleaseRentActivity.this, 4);
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

    private void selectByLike(String villageName) {
        KyLog.d(villageName);
        ApiModule.getInstance().selectByLike(villageName)
                .subscribe(selectByLikeEntities -> {
                    if (selectByLikeEntities != null && selectByLikeEntities.size() > 0) {
                        LinearLayoutManager manager = new LinearLayoutManager(ReleaseRentActivity.this);
                        mSelectBylikeAdapter = new SelectByLikeAdapter(selectByLikeEntities, this);
                        mRecyclerViewSearch.setAdapter(mSelectBylikeAdapter);
                        mRecyclerViewSearch.setLayoutManager(manager);
                        mRecyclerViewSearch.addItemDecoration(new SpaceItemDecoration(0, 15));
                        mSelectBylikeAdapter.setOnMyItemClickListener(new SelectByLikeAdapter.OnMyItemClickListener() {
                            @Override
                            public void myClick(View v, int pos) {
                                mEditTextvillageNam.setText(selectByLikeEntities.get(pos).getVillageName());
                                mLinearLayoutVillageName.setVisibility(View.GONE);
                                mLinearLayoutVillageNameSearch.setVisibility(View.VISIBLE);
                            }
                        });
                    }

                }, throwable -> {
                    KyLog.d(throwable.toString());
                    Toast.makeText(this, throwable.getMessage().toString(), Toast.LENGTH_SHORT).show();
                });
    }

    private void uploadImage() {

        String VillageName = mEditTextvillageNam.getText().toString().trim();
        String maxAcreage = mEditTextmaxAcreage.getText().toString().trim();
        String minPrice = mEditTextminPrice.getText().toString().trim();
        String maxPrice = mEditTextmaxPrice.getText().toString().trim();
        String minAcreage = mEditTextminAcreage.getText().toString().trim();
        String remark = mEditTextremark.getText().toString().trim();
        String tableId;
        if (!TextUtils.isEmpty(PreferenceUtil.getString(Constanst.TAB_NMAE))) {
            tableId = PreferenceUtil.getString(Constanst.TAB_NMAE);

        } else {
            tableId = "";

        }

        if (TextUtils.isEmpty(VillageName) && TextUtils.isEmpty(fitment) && TextUtils.isEmpty(HouseType)) {
            Toast.makeText(this, "请填写必填信息", Toast.LENGTH_SHORT).show();
            return;
        }
        showProgressDialog();

        Map<String, String> map = new HashMap<>();
        map.put("villageName", VillageName);
        map.put("unlimitedEstate", String.valueOf(unlimitedEstate));
        map.put("minPrice", minPrice);
        map.put("maxPrice", maxPrice);
        map.put("minAcreage", minAcreage);
        map.put("maxAcreage", maxAcreage);

        if (!TextUtils.isEmpty(HouseType)) {
            map.put("houseType", HouseType);

        }
        if (!TextUtils.isEmpty(PaymentType)) {
            map.put("paymentType", PaymentType);

        }
        if (!TextUtils.isEmpty(fitment)) {
            map.put("fitment", fitment);
        }

        if (!TextUtils.isEmpty(HseholdAppliances)) {
            map.put("householdAppliances", HseholdAppliances);
        }

        map.put("remark", remark);
        map.put("uid", String.valueOf(PreferenceUtil.getInt(UID)));
        map.put("stick", String.valueOf(stick));
        map.put("tabId", tableId);
        map.put("token", PreferenceUtil.getString(TOKEN));


        String url = "http://39.105.203.33/jlkf/mutual-trust/public/addWantedProduct";
        httpUtil.postFileRequest(url, map, null, new MyStringCallBack() {

            @Override
            public void onError(Call call, Exception e, int id) {
                super.onError(call, e, id);
                KyLog.d("image == " + e.toString());
                Toast.makeText(ReleaseRentActivity.this, "发布失败", Toast.LENGTH_SHORT).show();

                cancelProgressDialog();

            }

            @Override
            public void onResponse(String response, int id) {
                super.onResponse(response, id);
                KyLog.d("image ==" + response);
                cancelProgressDialog();
                Toast.makeText(ReleaseRentActivity.this, "发布成功", Toast.LENGTH_SHORT).show();
                //返回图片的地址
                if (TextUtils.isEmpty(type)) {
                    Intent intent = new Intent(ReleaseRentActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    try {
                        JSONObject res = new JSONObject(response);
                        JSONObject data = res.getJSONObject("data");
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("type", 1);
                        jsonObject.put("houseType", 4);
                        JSONArray arr = new JSONArray();
                        arr.put(data);
                        jsonObject.put("data", arr);
                        String result = jsonObject.toString();
                        Bundle bundle = new Bundle();
                        bundle.putString("msg", result);
                        Intent intent = getIntent();
                        intent.putExtras(bundle);
                        setResult(Activity.RESULT_OK, intent);
                        finish();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

}
