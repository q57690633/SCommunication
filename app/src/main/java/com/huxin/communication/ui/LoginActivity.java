package com.huxin.communication.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.huxin.communication.R;
import com.huxin.communication.base.BaseActivity;
import com.huxin.communication.controls.Constanst;
import com.huxin.communication.http.ApiModule;
import com.huxin.communication.utils.PreferenceUtil;
import com.nostra13.universalimageloader.utils.L;
import com.sky.kylog.KyLog;
import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMManager;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private ImageView mImageViewChickbox;
    private ImageView mImageViewChicked;

    private TextView mTextViewForgetPassWord;
    private TextView mTextViewWalling;
    private TextView mTextViewLogin;
    private TextView mTextViewRegister;

    private EditText mEditTextPhone;
    private EditText mEditTextPassWord;
    private boolean isClicked = false;

    public static void jumpTo(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void initViews() {
        setToolbarCenterMode("登录账号", 0);
        mEditTextPhone = (EditText) findViewById(R.id.phone);
        mEditTextPassWord = (EditText) findViewById(R.id.password);

        mTextViewLogin = (TextView) findViewById(R.id.login);
        mTextViewRegister = (TextView) findViewById(R.id.register);
        mTextViewWalling = (TextView) findViewById(R.id.tv_walling);
        mTextViewForgetPassWord = (TextView) findViewById(R.id.forget_password);

        mImageViewChickbox = (ImageView) findViewById(R.id.checkBos);

        mImageViewChicked = (ImageView) findViewById(R.id.checkBos_clicked);

        mImageViewChickbox.setOnClickListener(this);
        mTextViewLogin.setOnClickListener(this);
        mTextViewForgetPassWord.setOnClickListener(this);
        mTextViewRegister.setOnClickListener(this);


    }

    @Override
    protected void loadData(Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.forget_password:
                Intent intent = new Intent(this, ForgetPassWordActivity.class);
                startActivity(intent);
                break;
            case R.id.checkBos:
                if (isClicked){
                    mImageViewChicked.setVisibility(View.GONE);
                    isClicked = false;
                }else {
                    mImageViewChicked.setVisibility(View.VISIBLE);
                    isClicked = true;
                }
                break;
            case R.id.login:
                loginData();
//                Intent intentLogin = new Intent(this, MainActivity.class);
//                startActivity(intentLogin);
//                PreferenceUtil.putInt("type", 1);
                break;
            case R.id.register:
                Intent intentRegister = new Intent(this, RegisterOneActivity.class);
                startActivity(intentRegister);
                break;
        }

    }

    public void loginData(){
        String phone = mEditTextPhone.getText().toString().trim();
                String password = mEditTextPassWord.getText().toString().trim();
        if (!TextUtils.isEmpty(password) && !TextUtils.isEmpty(phone) ){


        if (isClicked){
        showProgressDialog();
        ApiModule.getInstance().logins(phone,password)
                .subscribe(loginEntity -> {
                    KyLog.object(loginEntity);
                    Intent intentLogin = new Intent(this, MainActivity.class);
                    startActivity(intentLogin);
                    PreferenceUtil.putInt("type", loginEntity.getRegisterType());
                    PreferenceUtil.putString(TOKEN, loginEntity.getToken());
                    PreferenceUtil.putInt(UID, loginEntity.getUid());
                    PreferenceUtil.putString("usersig", loginEntity.getUsersig());
                    PreferenceUtil.putString("identifier", loginEntity.getIdentifier());
                    PreferenceUtil.putString(Constanst.CITY_NAME, loginEntity.getCity());
                    PreferenceUtil.putString(Constanst.DISTRICT_NAME, loginEntity.getCounty());

                    PreferenceUtil.putString(Constanst.USER_NAME, loginEntity.getUsername());
                    PreferenceUtil.putString(Constanst.PHONE, loginEntity.getPhone());
                    PreferenceUtil.putString(Constanst.IMAGE_URL, loginEntity.getHeadUrl());
                    PreferenceUtil.putString(Constanst.COMPANY, loginEntity.getCompanyName());


                    TIMManager.getInstance().login(loginEntity.getIdentifier(), loginEntity.getUsersig(), new TIMCallBack() {
                        @Override
                        public void onError(int i, String s) {

                        }

                        @Override
                        public void onSuccess() {

                        }
                    });

                    cancelProgressDialog();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                        }
                    });
                },throwable -> {
                    cancelProgressDialog();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                            KyLog.d(throwable.toString());


                        }
                    });
                });

    }else {
            Toast.makeText(this, "请同意《互信用户服务协议》及《互信隐私权政策》", Toast.LENGTH_SHORT).show();
        }
        }else {
            Toast.makeText(this, "请填写手机号码或密码", Toast.LENGTH_SHORT).show();
        }
    }
}
