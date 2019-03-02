package com.huxin.communication.ui.my.MyInformation;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.huxin.communication.R;
import com.huxin.communication.base.BaseActivity;
import com.huxin.communication.controls.Constanst;
import com.huxin.communication.http.ApiModule;
import com.huxin.communication.ui.RegisterActivity;
import com.huxin.communication.utils.PreferenceUtil;
import com.sky.kylog.KyLog;

public class ChangePhoneActivity extends BaseActivity implements View.OnClickListener {
    private TextView mTextViewWanCheng;
    private TextView mTextViewBianJi;
    private boolean isClicked = false;

    private EditText mEditTextPhone;
    private EditText mEditTextPhoneCode;
    private EditText mEditTextSecondPhone;
    private EditText mEditTextSecondPhoneCode;

    private TextView mTextViewGetPhoneCode;
    private TextView mTextViewGetSecondPhoneCode;
    private TextView mTextViewPhone;
    private TextView mTextViewSecondPhone;


    private TimeCount mTimeCount;
    private TimeSecondCount mTimeSecondCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_change_phone);
    }

    @Override
    protected void initViews() {
        setToolbarCenterMode("更改手机号", MODE_BACK);
        mTextViewBianJi = (TextView) findViewById(R.id.toolbar_bianji);
        mTextViewWanCheng = (TextView) findViewById(R.id.toolbar_quxiao);

        mEditTextPhone = findViewById(R.id.phone_ed);
        mEditTextPhoneCode = findViewById(R.id.phone_code);
        mEditTextSecondPhone = findViewById(R.id.second_phone_ed);
        mEditTextSecondPhoneCode = findViewById(R.id.second_phone_code);
        mTextViewGetPhoneCode = findViewById(R.id.get_phone_code);
        mTextViewGetSecondPhoneCode = findViewById(R.id.get_second_phone);

        mTextViewPhone = findViewById(R.id.phone);
        mTextViewSecondPhone = findViewById(R.id.second_phone);


        mTextViewGetSecondPhoneCode.setOnClickListener(this);
        mTextViewGetPhoneCode.setOnClickListener(this);
        mTextViewWanCheng.setOnClickListener(this);
        mTextViewBianJi.setOnClickListener(this);

    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        if (!isClicked) {
            mTextViewBianJi.setVisibility(View.VISIBLE);
            mTextViewWanCheng.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(PreferenceUtil.getString(Constanst.PHONE))){
              mTextViewPhone.setText(PreferenceUtil.getString(Constanst.PHONE));
        }

         if (!TextUtils.isEmpty(PreferenceUtil.getString(Constanst.SECOND_PHONE))){
               mTextViewSecondPhone.setText(PreferenceUtil.getString(Constanst.SECOND_PHONE));
         }
        

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.get_phone_code:
                SendMessageData(mEditTextPhone.getText().toString().trim(), 1);
                break;
            case R.id.get_second_phone:
                SendMessageData(mEditTextSecondPhone.getText().toString().trim(), 2);

                break;

            case R.id.toolbar_bianji:
                if (isClicked) {
                    mTextViewWanCheng.setVisibility(View.VISIBLE);
                    mTextViewBianJi.setVisibility(View.GONE);
                    isClicked = false;
                    String phone = mEditTextPhone.getText().toString().trim();
                    String phoneCode = mEditTextPhoneCode.getText().toString().trim();
                    String secondPhone = mEditTextSecondPhone.getText().toString().trim();
                    String secondPhoneCode = mEditTextSecondPhoneCode.getText().toString().trim();

                    if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(phoneCode)){
                        updateUserPhone(phone,phoneCode,secondPhone,secondPhoneCode);
                    }else {
                        Toast.makeText(this, "请填写需要修改的密码", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    mTextViewBianJi.setVisibility(View.VISIBLE);
                    mTextViewWanCheng.setVisibility(View.GONE);
                    isClicked = true;
                }
                break;
            case R.id.toolbar_quxiao:
                finish();


                break;
        }
    }


    public void SendMessageData(String phone, int type) {
        if (!TextUtils.isEmpty(phone)) {
            showProgressDialog();
            ApiModule.getInstance().sendMessage(phone).subscribe(Response -> {
                cancelProgressDialog();
                if (Response.getResultCode() == 0) {
                    if (type == 1) {
                        mTimeCount = new TimeCount(120000, 1000);
                        mTimeCount.start();
                    } else {
                        mTimeSecondCount = new TimeSecondCount(120000, 1000);
                        mTimeSecondCount.start();
                    }
                }
                Toast.makeText(this, Response.getResultMsg(), Toast.LENGTH_SHORT).show();
            }, throwable -> {
                KyLog.d(throwable.toString());
                cancelProgressDialog();
            });
        } else {
            Toast.makeText(this, "请先输入电话号码", Toast.LENGTH_SHORT).show();
        }

    }

    public void updateUserPhone(String phone, String authCode, String secondPhone, String secondAuthCode) {
        if (!TextUtils.isEmpty(phone)) {
            showProgressDialog();
            ApiModule.getInstance().updateUserPhone(phone, authCode, secondPhone, secondAuthCode)
                    .subscribe(updateUserPhoneEntity -> {
                cancelProgressDialog();
                        Toast.makeText(this, "更新成功", Toast.LENGTH_SHORT).show();

            }, throwable -> {
                KyLog.d(throwable.toString());
                cancelProgressDialog();
            });
        } else {
            Toast.makeText(this, "请先输入电话号码", Toast.LENGTH_SHORT).show();
        }

    }

    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            mTextViewGetPhoneCode.setClickable(false);
            mTextViewGetPhoneCode.setText(millisUntilFinished / 1000 + "s 重新发送");
        }

        @Override
        public void onFinish() {
            mTextViewGetPhoneCode.setText("重新发送");
            mTextViewGetPhoneCode.setClickable(true);
        }
    }

    class TimeSecondCount extends CountDownTimer {

        public TimeSecondCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            mTextViewGetSecondPhoneCode.setClickable(false);
            mTextViewGetSecondPhoneCode.setText(millisUntilFinished / 1000 + "s 重新发送");
        }

        @Override
        public void onFinish() {
            mTextViewGetSecondPhoneCode.setText("重新发送");
            mTextViewGetSecondPhoneCode.setClickable(true);
        }
    }

}
