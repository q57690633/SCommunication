package com.huxin.communication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.huxin.communication.R;
import com.huxin.communication.base.BaseActivity;
import com.huxin.communication.http.ApiModule;
import com.huxin.communication.utils.PreferenceUtil;
import com.sky.kylog.KyLog;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    private TextView mTextVieSendCode;
    private EditText mEditTextSendCode;
    private EditText mEditTextPhone;
    private EditText mEditTextPassWord;
    private TextView mTextviewRegister;
    private TimeCount mTimeCount;

    private ImageView mImageViewChickbox;
    private ImageView mImageViewChicked;
    private TextView mTextViewWalling;

    private boolean isClicked = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_register);
    }

    @Override
    protected void initViews() {
        setToolbarCenterMode("注册", MODE_BACK);
        mEditTextPassWord = (EditText) findViewById(R.id.register_password);
        mEditTextPhone = (EditText) findViewById(R.id.register_phone);
        mTextVieSendCode = (TextView) findViewById(R.id.send_code);
        mTextviewRegister = (TextView) findViewById(R.id.register);
        mEditTextSendCode = (EditText) findViewById(R.id.verification_code);

        mTextViewWalling = (TextView) findViewById(R.id.tv_walling);
        mImageViewChickbox = (ImageView) findViewById(R.id.checkBos);
        mImageViewChicked = (ImageView) findViewById(R.id.checkBos_clicked);

        mImageViewChickbox.setOnClickListener(this);
        mTextviewRegister.setOnClickListener(this);
        mTextVieSendCode.setOnClickListener(this);
    }

    @Override
    protected void loadData(Bundle sgavedInstanceState) {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register:
                if (isClicked) {
                    registerData();
                } else {
                    Toast.makeText(this, "请同意《互信用户服务协议》及《互信隐私权政策》", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.send_code:
                SendMessageData();
                break;
            case R.id.checkBos:
                if (isClicked) {
                    mImageViewChicked.setVisibility(View.GONE);
                    isClicked = false;
                } else {
                    mImageViewChicked.setVisibility(View.VISIBLE);
                    isClicked = true;
                }
                break;

        }
    }

    public void registerData() {
        showProgressDialog();
        ApiModule.getInstance().registers(PreferenceUtil.getInt("type"), mEditTextPhone.getText().toString().trim(),
                mEditTextSendCode.getText().toString().trim(), mEditTextPassWord.getText().toString().trim())
                .subscribe(registerEntity -> {
                    PreferenceUtil.putString(PHONE, registerEntity.getPhone());
                    PreferenceUtil.putString(TOKEN, registerEntity.getToken());
                    PreferenceUtil.putInt(UID, registerEntity.getUid());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();

                        }
                    });
                    Intent intent = new Intent(RegisterActivity.this, RegisterInformationActivity.class);
                    startActivity(intent);
                    cancelProgressDialog();
                }, throwable -> {
                    KyLog.d(throwable.toString());
                    cancelProgressDialog();
                    Toast.makeText(this, throwable.getMessage().toString(), Toast.LENGTH_SHORT).show();
                });

    }

    public void SendMessageData() {
        String phone = mEditTextPhone.getText().toString().trim();
        if (!TextUtils.isEmpty(phone)) {
            showProgressDialog();
            ApiModule.getInstance().sendMessage(phone).subscribe(Response -> {
                cancelProgressDialog();
                if (Response.getResultCode() == 0) {
                    mTimeCount = new TimeCount(120000, 1000);
                    mTimeCount.start();
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

    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            mTextVieSendCode.setClickable(false);
            mTextVieSendCode.setText(millisUntilFinished / 1000 + "s 重新发送");
        }

        @Override
        public void onFinish() {
            mTextVieSendCode.setText("重新发送");
            mTextVieSendCode.setClickable(true);
        }
    }
}
