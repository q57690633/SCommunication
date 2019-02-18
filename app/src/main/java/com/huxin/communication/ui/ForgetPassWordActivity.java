package com.huxin.communication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.huxin.communication.R;
import com.huxin.communication.base.BaseActivity;
import com.huxin.communication.http.ApiModule;
import com.sky.kylog.KyLog;

public class ForgetPassWordActivity extends BaseActivity implements View.OnClickListener {

    private EditText mEditTextPhone;
    private EditText mEditTextPassword;
    private EditText mEditTextCode;
    private TextView mTextViewQueDing;
    private TextView mTextViewCode;
    private TimeCount mTimeCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_forget_pass_word);
    }

    @Override
    protected void initViews() {
        setToolbarCenterMode("忘记密码",MODE_BACK);
        mEditTextCode = (EditText) findViewById(R.id.verification_code);
        mEditTextPhone = (EditText) findViewById(R.id.phone);
        mEditTextPassword = (EditText) findViewById(R.id.password);
        mTextViewQueDing = (TextView) findViewById(R.id.queding);
        mTextViewCode = (TextView) findViewById(R.id.tv_code);

        mTextViewQueDing.setOnClickListener(this);
        mTextViewCode.setOnClickListener(this);
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {

    }


    private void getForgetPasswords(){
        showProgressDialog();
        ApiModule.getInstance().forgetPasswords(mEditTextPhone.getText().toString().trim(),
                mEditTextPassword.getText().toString().trim(), mEditTextCode.getText().toString().trim())
                .subscribe(registerEntity -> {
                    cancelProgressDialog();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(ForgetPassWordActivity.this, "修改成功", Toast.LENGTH_SHORT).show();

                        }
                    });
                    Intent intent = new Intent(ForgetPassWordActivity.this,LoginActivity.class);
                    startActivity(intent);
                },throwable -> {
                    KyLog.d(throwable.toString());
                    cancelProgressDialog();
                    Toast.makeText(this, throwable.getMessage().toString(), Toast.LENGTH_SHORT).show();
                });

    }

    public void SendMessageData(){
        String phone = mEditTextPhone.getText().toString().trim();
        if (!TextUtils.isEmpty(phone)){
            showProgressDialog();
            ApiModule.getInstance().sendMessage(phone).subscribe(Response -> {
                cancelProgressDialog();
                if (Response.getResultCode() == 0) {
                    KyLog.d(Response.getResultCode() + "");
                    mTimeCount = new TimeCount(120000, 1000);
                    mTimeCount.start();
                }
                Toast.makeText(this, Response.getResultMsg(), Toast.LENGTH_SHORT).show();
            },throwable -> {
                KyLog.d(throwable.toString());
                cancelProgressDialog();
            });
        }else {
            Toast.makeText(this, "请先输入电话号码", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_code:
                SendMessageData();
                break;
            case R.id.queding:
                getForgetPasswords();
                break;
        }
    }

    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            mTextViewCode.setClickable(false);
            mTextViewCode.setText(millisUntilFinished / 1000 + "s 重新发送");
        }

        @Override
        public void onFinish() {
            mTextViewCode.setText("重新发送");
            mTextViewCode.setClickable(true);
        }
    }
}
