package com.huxin.communication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huxin.communication.R;
import com.huxin.communication.base.BaseActivity;
import com.huxin.communication.controls.Constanst;
import com.huxin.communication.custom.ReleaseDialog;
import com.huxin.communication.entity.AddressEntity;
import com.huxin.communication.entity.EvnBusCityEntity;
import com.huxin.communication.entity.EvnBusCountyEntity;
import com.huxin.communication.entity.EvnBusEntity;
import com.huxin.communication.entity.MyPopVlaues;
import com.huxin.communication.http.ApiModule;
import com.huxin.communication.utils.JsonUtils;
import com.huxin.communication.utils.PreferenceUtil;
import com.sky.kylog.KyLog;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class RegisterInformationActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout mLinearLayoutCompanyLine;
    private RelativeLayout mRelativeLayoutOccupation;
    private LinearLayout mLinearLayoutOnePerson;

    private RelativeLayout mRelativeLayoutProvince;
    private RelativeLayout mRelativeLayoutCity;
    private RelativeLayout mRelativeLayoutCounty;

    private RelativeLayout mRelativeLayoutOccupationType;
    private TextView mTextViewOccupation;

    private TextView mTextViewComPany;
    private TextView mTextViewOnePerson;
    private TextView mTextViewProvince;
    private TextView mTextViewCity;
    private TextView mTextViewCounty;
    private TextView mTextViewOccupationType;
    private EditText mEditTextCompanyName;
    private EditText mEditTextCompanyCode;
    private EditText mEditTextInvitationCode;
    private EditText mEditTextInvitationCodeTwo;
    private String cOrP;
    private ArrayList<AddressEntity> mData;
    private List<MyPopVlaues> Kouweilist;


    private ReleaseDialog mReleaseDialog;

    private String purpose;
    private String Fitment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_register_information);
        mLinearLayoutCompanyLine = (LinearLayout) findViewById(R.id.line_Occupation);
        mLinearLayoutOnePerson = (LinearLayout) findViewById(R.id.one_person_rl);
        mRelativeLayoutOccupation = (RelativeLayout) findViewById(R.id.rl_Occupation);
        mRelativeLayoutOccupationType = (RelativeLayout) findViewById(R.id.rl_Occupation_type);
        mTextViewComPany = (TextView) findViewById(R.id.company);
        mTextViewOnePerson = (TextView) findViewById(R.id.one_person);
        mRelativeLayoutProvince = (RelativeLayout) findViewById(R.id.province_rl);
        mRelativeLayoutCity = (RelativeLayout) findViewById(R.id.city_rl);
        mRelativeLayoutCounty = (RelativeLayout) findViewById(R.id.county_rl);
        mEditTextCompanyName = (EditText) findViewById(R.id.company_name);
        mEditTextCompanyCode = (EditText) findViewById(R.id.company_code);
        mEditTextInvitationCode = (EditText) findViewById(R.id.Invitation_code);
        mEditTextInvitationCodeTwo = (EditText) findViewById(R.id.Invitation_code_two);

        mTextViewProvince = (TextView) findViewById(R.id.province);
        mTextViewCounty = (TextView) findViewById(R.id.county);
        mTextViewCity = (TextView) findViewById(R.id.city);
        mTextViewOccupationType = (TextView) findViewById(R.id.Occupation_type);
        mTextViewOccupation = (TextView) findViewById(R.id.Occupation);

        mTextViewComPany.setOnClickListener(this);
        mTextViewOnePerson.setOnClickListener(this);
        mRelativeLayoutProvince.setOnClickListener(this);
        mRelativeLayoutCity.setOnClickListener(this);
        mRelativeLayoutCounty.setOnClickListener(this);
        mRelativeLayoutOccupationType.setOnClickListener(this);
        mRelativeLayoutOccupation.setOnClickListener(this);


        findViewById(R.id.jinru).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String InvitationCode = mEditTextInvitationCode.getText().toString().trim();
                String CompanyCode = mEditTextCompanyCode.getText().toString().trim();
                String InvitationCodeTwo = mEditTextInvitationCodeTwo.getText().toString().trim();
                String CompanyName = mEditTextCompanyName.getText().toString().trim();
                if (cOrP.equals("1")) {
                    if (!TextUtils.isEmpty(CompanyCode) && !TextUtils.isEmpty(CompanyName)) {
                        getAddUserInformation(InvitationCode, CompanyCode, InvitationCodeTwo, CompanyName);
                    } else {
                        Toast.makeText(RegisterInformationActivity.this, "请填写公司编码或者公司名称", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (!TextUtils.isEmpty(InvitationCode) && !TextUtils.isEmpty(InvitationCodeTwo)) {
                        getAddUserInformation(InvitationCode, CompanyCode, InvitationCodeTwo, CompanyName);
                    } else {
                        Toast.makeText(RegisterInformationActivity.this, "请填写邀请码", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    protected void initViews() {
        setToolbarCenterMode("填写详细信息", MODE_BACK);
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        addjson();


    }

    @Override
    protected void onResume() {
        super.onResume();
        setDatas();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.company:
                mRelativeLayoutOccupation.setVisibility(View.VISIBLE);
                mLinearLayoutOnePerson.setVisibility(View.GONE);
                mLinearLayoutCompanyLine.setVisibility(View.VISIBLE);
                mTextViewOnePerson.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewComPany.setBackgroundResource(R.color.blue);
                mTextViewComPany.setTextColor(getResources().getColor(R.color.white));
                mTextViewOnePerson.setTextColor(getResources().getColor(R.color.register_font));
                cOrP = "1";
                break;
            case R.id.one_person:
                mRelativeLayoutOccupation.setVisibility(View.GONE);
                mLinearLayoutOnePerson.setVisibility(View.VISIBLE);
                mLinearLayoutCompanyLine.setVisibility(View.GONE);
                mTextViewOnePerson.setBackgroundResource(R.color.blue);
                mTextViewComPany.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewComPany.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewOnePerson.setTextColor(getResources().getColor(R.color.white));
                cOrP = "2";
                break;

            case R.id.province_rl:

                Intent intent = new Intent(this, ProvincesActivity.class);
                if (mData != null && mData.size() > 0) {
                    EventBus.getDefault().postSticky(new EvnBusEntity(mData));
                }
                startActivity(intent);
                for (AddressEntity entity : mData) {
                    KyLog.d(entity.getName());
                }


                break;
            case R.id.city_rl:
                List<AddressEntity.ListBeanX> list = new ArrayList<>();
                if (mData != null && mData.size() > 0) {
                    for (AddressEntity entity : mData) {
                        if (entity.getList() != null && entity.getList().size() > 0) {
                            if (entity.getId().equals(PreferenceUtil.getString(Constanst.PROVINCE_ID))) {
                                list.addAll(entity.getList());
                            }
                        }
                    }
                }
                if (list.size() > 0) {
                    EventBus.getDefault().postSticky(new EvnBusCityEntity(list));
                    Intent intentCity = new Intent(this, CityActivity.class);
                    startActivity(intentCity);
                }else {
                    Toast.makeText(this, "请先选择省", Toast.LENGTH_SHORT).show();
                }

                for (AddressEntity.ListBeanX entity : list) {
                    KyLog.d(entity.getName());
                }

                break;
            case R.id.county_rl:
                ArrayList<AddressEntity.ListBeanX.ListBean> listCounty = new ArrayList<>();
                if (mData != null && mData.size() > 0) {
                    for (AddressEntity entity : mData) {
                        for (AddressEntity.ListBeanX listBean: entity.getList()) {
                            if (listBean.getList() != null && listBean.getList().size() > 0) {
                                if (listBean.getId().equals(PreferenceUtil.getString(Constanst.CITY_ID))){
                                    listCounty.addAll(listBean.getList());
                                }
                            }
                        }

                    }
                }
                if (listCounty.size() > 0) {
                    EventBus.getDefault().postSticky(new EvnBusCountyEntity(listCounty));
                    Intent intentCounty = new Intent(this, CountyActivity.class);
                    startActivity(intentCounty);
                }else {
                    Toast.makeText(this, "请先选择省或者市", Toast.LENGTH_SHORT).show();
                }

        for (AddressEntity.ListBeanX.ListBean entity : listCounty) {
            KyLog.d(entity.getName());
        }
        break;
        case R.id.rl_Occupation_type:
            if (PreferenceUtil.getInt("type") == 1) {
                mReleaseDialog = new ReleaseDialog(this, setOccupation());
                mReleaseDialog.setCancelable(true);

                mReleaseDialog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        mTextViewOccupationType.setText(setOccupation().get(position).getName());
                        purpose = setOccupation().get(position).getName();
                        mReleaseDialog.cancel();
                    }
                });

            }else {
                mReleaseDialog = new ReleaseDialog(this, setTravelOccupation());
                mReleaseDialog.setCancelable(true);

                mReleaseDialog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        mTextViewOccupationType.setText(setTravelOccupation().get(position).getName());
                        purpose = setTravelOccupation().get(position).getName();
                        mReleaseDialog.cancel();
                    }
                });
            }
            mReleaseDialog.show();
            break;
        case R.id.rl_Occupation:
            if (PreferenceUtil.getInt("type") == 1) {
                mReleaseDialog = new ReleaseDialog(this, setFitment());
                mReleaseDialog.setCancelable(true);

                mReleaseDialog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        mTextViewOccupation.setText(setFitment().get(position).getName());
                        Fitment = setFitment().get(position).getName();
                        mReleaseDialog.cancel();
                    }
                });
            }else {
                mReleaseDialog = new ReleaseDialog(this, setTravelFitment());
                mReleaseDialog.setCancelable(true);

                mReleaseDialog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        mTextViewOccupation.setText(setTravelFitment().get(position).getName());
                        Fitment = setTravelFitment().get(position).getName();
                        mReleaseDialog.cancel();
                    }
                });
            }
        mReleaseDialog.show();
        break;
    }

}

    public void setDatas() {
        if (!TextUtils.isEmpty(PreferenceUtil.getString(Constanst.PROVINCE_NAME))) {
            mTextViewProvince.setText(PreferenceUtil.getString(Constanst.PROVINCE_NAME));
        }

        if (!TextUtils.isEmpty(PreferenceUtil.getString(Constanst.CITY_NAME))) {
            mTextViewCity.setText(PreferenceUtil.getString(Constanst.CITY_NAME));
        }

        if (!TextUtils.isEmpty(PreferenceUtil.getString(Constanst.DISTRICT_NAME))) {
            mTextViewCounty.setText(PreferenceUtil.getString(Constanst.DISTRICT_NAME));
        }

    }

    private void getAddUserInformation(String InvitationCode, String CompanyCode, String InvitationCodeTwo, String CompanyName) {


        showProgressDialog();
        ApiModule.getInstance().updatUserInformation(PreferenceUtil.getString(Constanst.PROVINCE_NAME), PreferenceUtil.getString(Constanst.CITY_NAME), PreferenceUtil.getString(Constanst.DISTRICT_NAME), CompanyName,
                CompanyCode, Fitment, purpose,
                InvitationCode, InvitationCodeTwo,
                cOrP, String.valueOf(PreferenceUtil.getInt(UID)), PreferenceUtil.getString(TOKEN), PreferenceUtil.getString(PHONE))
                .subscribe(registerEntity -> {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(RegisterInformationActivity.this, "提交成功", Toast.LENGTH_SHORT).show();

                        }
                    });
                    Intent intent = new Intent(RegisterInformationActivity.this, MainActivity.class);
                    startActivity(intent);
                    cancelProgressDialog();
                }, throwable -> {
                    KyLog.d(throwable.toString());
                    cancelProgressDialog();
                    Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                });

    }


    /**
     * 读取json文件的省，市，区
     *
     * @return
     */
    public void addjson() {
        ArrayList<AddressEntity> data = new ArrayList<>();
        try {
            String json = JsonUtils.toString(this.getAssets().open("address.json"));
            Gson gson = new Gson();
            data = gson.fromJson(json, new TypeToken<List<AddressEntity>>() {
            }.getType());
            mData = data;
//            MainDataUtil.saveAddressList(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<MyPopVlaues> setOccupation() {
        Kouweilist = new ArrayList<MyPopVlaues>();
        Kouweilist.add(new MyPopVlaues("独立经纪人"));
        Kouweilist.add(new MyPopVlaues("自由经纪人"));
        Kouweilist.add(new MyPopVlaues("兼职经纪人"));
        Kouweilist.add(new MyPopVlaues("公寓经理"));
        Kouweilist.add(new MyPopVlaues("短租房经纪人"));
        Kouweilist.add(new MyPopVlaues("个人房主"));

        return Kouweilist;
    }

    private List<MyPopVlaues> setFitment() {
        Kouweilist = new ArrayList<MyPopVlaues>();
        Kouweilist.add(new MyPopVlaues("公司法人"));
        Kouweilist.add(new MyPopVlaues("公司经理"));
        Kouweilist.add(new MyPopVlaues("区域总监"));
        Kouweilist.add(new MyPopVlaues("店长"));
        Kouweilist.add(new MyPopVlaues("店面经理"));
        Kouweilist.add(new MyPopVlaues("店面助理"));
        Kouweilist.add(new MyPopVlaues("秘书"));
        Kouweilist.add(new MyPopVlaues("经纪人"));
        Kouweilist.add(new MyPopVlaues("店员"));

        return Kouweilist;
    }


    private List<MyPopVlaues> setTravelOccupation() {
        Kouweilist = new ArrayList<MyPopVlaues>();
        Kouweilist.add(new MyPopVlaues("独立经纪人"));
        Kouweilist.add(new MyPopVlaues("自由经纪人"));
        Kouweilist.add(new MyPopVlaues("兼职代理人"));
        return Kouweilist;
    }

    private List<MyPopVlaues> setTravelFitment() {
        Kouweilist = new ArrayList<MyPopVlaues>();
        Kouweilist.add(new MyPopVlaues("公司法人"));
        Kouweilist.add(new MyPopVlaues("公司经理"));
        Kouweilist.add(new MyPopVlaues("区域总监"));
        Kouweilist.add(new MyPopVlaues("店面经理"));
        Kouweilist.add(new MyPopVlaues("行政"));
        Kouweilist.add(new MyPopVlaues("计调"));
        Kouweilist.add(new MyPopVlaues("经纪人"));
        Kouweilist.add(new MyPopVlaues("店员"));


        return Kouweilist;
    }
}
