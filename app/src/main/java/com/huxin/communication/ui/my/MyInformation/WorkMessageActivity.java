package com.huxin.communication.ui.my.MyInformation;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.huxin.communication.R;
import com.huxin.communication.ReleaseTabAdapter;
import com.huxin.communication.base.BaseActivity;
import com.huxin.communication.controls.Constanst;
import com.huxin.communication.custom.ReleaseDialog;
import com.huxin.communication.entity.MyPopVlaues;
import com.huxin.communication.http.ApiModule;
import com.huxin.communication.ui.house.release.ReleaseActivity;
import com.huxin.communication.utils.PreferenceUtil;
import com.huxin.communication.view.SpaceItemDecoration;
import com.sky.kylog.KyLog;

import java.util.ArrayList;
import java.util.List;

public class WorkMessageActivity extends BaseActivity implements View.OnClickListener {
    private TextView mTextViewWanCheng;
    private TextView mTextViewBianJi;
    private boolean isClicked = true;

    private EditText mEditTextUserName;
    private TextView mEditTextArea;
    private EditText mEditTextstoreName;
    private EditText mEditTextcompanyName;
    private TextView mEditTextcompanyCode;

    private LinearLayout mLinearLayoutPositions;
    private LinearLayout mLinearLayoutIndustryType;

    private TextView mTextViewPositions;
    private TextView mTextViewIndustryType;

    private List<MyPopVlaues> Kouweilist;

    private ReleaseDialog mReleaseDialog;

    private String positions;
    private String industryType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_work_message);
    }

    @Override
    protected void initViews() {
        setToolbarCenterMode("工作信息", MODE_BACK);
        mTextViewBianJi = (TextView) findViewById(R.id.toolbar_bianji);
        mTextViewWanCheng = (TextView) findViewById(R.id.toolbar_quxiao);

        mEditTextArea = findViewById(R.id.city);
        mEditTextUserName = findViewById(R.id.username);
<<<<<<< Updated upstream
        mEditTextcompanyCode = findViewById(R.id.companyCode);
=======
        mEditTextcompanyCode = findViewById(R.id.company_code);
>>>>>>> Stashed changes
        mEditTextcompanyName = findViewById(R.id.companyNmae);
        mEditTextstoreName = findViewById(R.id.storeName);
        mLinearLayoutIndustryType = findViewById(R.id.industryType_line);
        mLinearLayoutPositions = findViewById(R.id.line_positions);
        mTextViewPositions = findViewById(R.id.positions);
        mTextViewIndustryType = findViewById(R.id.industryType);

        mLinearLayoutPositions.setOnClickListener(this);
        mLinearLayoutIndustryType.setOnClickListener(this);
        mTextViewBianJi.setOnClickListener(this);
        mTextViewWanCheng.setOnClickListener(this);

    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        mTextViewBianJi.setVisibility(View.VISIBLE);
        mTextViewWanCheng.setVisibility(View.GONE);
<<<<<<< Updated upstream
        setData();
=======
>>>>>>> Stashed changes
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.line_positions:
                mReleaseDialog = new ReleaseDialog(this, setPosition());
                mReleaseDialog.setCancelable(true);

                mReleaseDialog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        mTextViewPositions.setText(setPosition().get(position).getName());
                        positions = setPosition().get(position).getName();
                        mReleaseDialog.cancel();
                    }
                });
                mReleaseDialog.show();
                break;

            case R.id.industryType_line:
                mReleaseDialog = new ReleaseDialog(this, setIndustryType());
                mReleaseDialog.setCancelable(true);

                mReleaseDialog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        mTextViewIndustryType.setText(setIndustryType().get(position).getName());
                        industryType = setIndustryType().get(position).getName();
                        mReleaseDialog.cancel();
                    }
                });
                mReleaseDialog.show();

                break;
            case R.id.toolbar_bianji:
                mTextViewWanCheng.setVisibility(View.VISIBLE);
                mTextViewBianJi.setVisibility(View.GONE);
                isClicked = false;


                break;

            case R.id.toolbar_quxiao:
                String userName = mEditTextUserName.getText().toString().trim();
<<<<<<< Updated upstream
                String area = PreferenceUtil.getString(Constanst.CITY_NAME);
=======
                String area = mEditTextArea.getText().toString().trim();
>>>>>>> Stashed changes
                String storeName = mEditTextstoreName.getText().toString().trim();
                String companyName = mEditTextcompanyName.getText().toString().trim();
                if (!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(area)) {
                    if (PreferenceUtil.getInt("type") == 1) {
                        if (!TextUtils.isEmpty(storeName) && !TextUtils.isEmpty(positions)) {
                            updateUserInformation(userName, area, storeName, positions, industryType, companyName);
                        } else {
                            Toast.makeText(this, "请选择职位或者填写店名", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        if (!TextUtils.isEmpty(industryType)) {
                            updateUserInformation(userName, area, storeName, positions, industryType, companyName);
                        } else {
                            Toast.makeText(this, "请选择从业类型", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
<<<<<<< Updated upstream
                    Toast.makeText(this, "请填写姓名", Toast.LENGTH_SHORT).show();
=======
                    Toast.makeText(this, "请填写姓名或城市", Toast.LENGTH_SHORT).show();
>>>>>>> Stashed changes
                }

                break;
        }
    }

    private void updateUserInformation(String username, String area,
                                       String storeName, String position,
                                       String industryType, String companyName) {
        showProgressDialog();
        ApiModule.getInstance().updateUserInformation(username, area, storeName, position, industryType, companyName)
                .subscribe(selectTabEntity -> {
                    cancelProgressDialog();
                    finish();

                }, throwable -> {
                    KyLog.d(throwable.toString());
                    cancelProgressDialog();
                    Toast.makeText(this, throwable.getMessage().toString(), Toast.LENGTH_SHORT).show();
                });
    }

    private List<MyPopVlaues> setPosition() {
        Kouweilist = new ArrayList<MyPopVlaues>();
        Kouweilist.add(new MyPopVlaues("公司法人"));
        Kouweilist.add(new MyPopVlaues("公司经理"));
        Kouweilist.add(new MyPopVlaues("店长"));
        Kouweilist.add(new MyPopVlaues("店面助理"));
        Kouweilist.add(new MyPopVlaues("店员"));
        return Kouweilist;
    }

    private List<MyPopVlaues> setIndustryType() {
        Kouweilist = new ArrayList<MyPopVlaues>();
        Kouweilist.add(new MyPopVlaues("独立经纪人"));
        Kouweilist.add(new MyPopVlaues("个人经纪人"));
        Kouweilist.add(new MyPopVlaues("个人房主"));
        Kouweilist.add(new MyPopVlaues("兼职经纪人"));
        Kouweilist.add(new MyPopVlaues("公寓经理"));

        return Kouweilist;
    }

    private void setData(){
        if (!TextUtils.isEmpty(PreferenceUtil.getString(Constanst.USER_NAME))){
            mEditTextUserName.setText(PreferenceUtil.getString(Constanst.USER_NAME));
        }
        if (!TextUtils.isEmpty(PreferenceUtil.getString(Constanst.COMPANY_CODE))){
            mEditTextcompanyCode.setText(PreferenceUtil.getString(Constanst.COMPANY_CODE));
        }
        if (!TextUtils.isEmpty(PreferenceUtil.getString(Constanst.COMPANY))){
            mEditTextcompanyName.setText(PreferenceUtil.getString(Constanst.COMPANY));
        }
        if (!TextUtils.isEmpty(PreferenceUtil.getString(Constanst.POSITION))){
            mTextViewPositions.setText(PreferenceUtil.getString(Constanst.POSITION));
            positions = PreferenceUtil.getString(Constanst.POSITION);
        }
        if (!TextUtils.isEmpty(PreferenceUtil.getString(Constanst.INDUSTRYTYPE))){
            mTextViewIndustryType.setText(PreferenceUtil.getString(Constanst.INDUSTRYTYPE));
            industryType = PreferenceUtil.getString(Constanst.INDUSTRYTYPE);
        }
        if (!TextUtils.isEmpty(PreferenceUtil.getString(Constanst.CITY_NAME))){
            mEditTextArea.setText(PreferenceUtil.getString(Constanst.CITY_NAME));
        }
    }

}
