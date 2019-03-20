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

import com.huxin.communication.GetMsgManager;
import com.huxin.communication.R;
import com.huxin.communication.base.BaseActivity;
import com.huxin.communication.controls.Constanst;
import com.huxin.communication.entity.GetMessageEntity;
import com.huxin.communication.http.ApiModule;
import com.huxin.communication.listener.GetMessageListener;
import com.huxin.communication.utils.PreferenceUtil;
import com.sky.kylog.KyLog;
import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMConversation;
import com.tencent.imsdk.TIMConversationType;
import com.tencent.imsdk.TIMElem;
import com.tencent.imsdk.TIMElemType;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.TIMMessageListener;
import com.tencent.imsdk.TIMTextElem;
import com.tencent.imsdk.ext.message.TIMConversationExt;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private ImageView mImageViewChickbox;
    private ImageView mImageViewChicked;
    private TextView mTextViewWalling;

    private TextView mTextViewForgetPassWord;
    private TextView mTextViewLogin;
    private TextView mTextViewRegister;

    private EditText mEditTextPhone;
    private EditText mEditTextPassWord;

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
        mTextViewForgetPassWord = (TextView) findViewById(R.id.forget_password);


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

            case R.id.login:
                loginData();
                break;
            case R.id.register:
                Intent intentRegister = new Intent(this, RegisterOneActivity.class);
                startActivity(intentRegister);
                break;
        }

    }

    public void loginData() {
        String phone = mEditTextPhone.getText().toString().trim();
        String password = mEditTextPassWord.getText().toString().trim();
        if (!TextUtils.isEmpty(password) && !TextUtils.isEmpty(phone)) {
            showProgressDialog();
            ApiModule.getInstance().logins(phone, password)
                    .subscribe(loginEntity -> {
                        KyLog.i("----------登录---------");
                        KyLog.object(loginEntity);

                        if (TextUtils.isEmpty(loginEntity.getCity())) {
                            Intent intent = new Intent(this, RegisterInformationActivity.class);
                            startActivity(intent);
                            return;
                        }
//                            Intent intentLogin = new Intent(this, MainActivity.class);
//                            startActivity(intentLogin);
                        PreferenceUtil.putInt("type", loginEntity.getRegisterType());
                        PreferenceUtil.putString(TOKEN, loginEntity.getToken());
                        PreferenceUtil.putInt(UID, loginEntity.getUid());
                        PreferenceUtil.putString("usersig", loginEntity.getUsersig());
                        PreferenceUtil.putString("identifier", loginEntity.getIdentifier());
                        PreferenceUtil.putString(Constanst.CITY_NAME, loginEntity.getCity());
                        PreferenceUtil.putString(Constanst.CORP,loginEntity.getCOrP());
                        PreferenceUtil.putString(Constanst.DISTRICT_NAME, loginEntity.getArea());
                        if (!TextUtils.isEmpty(loginEntity.getProvince())) {
                            PreferenceUtil.putString(Constanst.PROVINCE_NAME, loginEntity.getProvince());
                        }
                        PreferenceUtil.putString(Constanst.TOP_ZHIDING, String.valueOf(loginEntity.getStickNumber()));

                        PreferenceUtil.putString(Constanst.USER_NAME, loginEntity.getUsername());
                        PreferenceUtil.putString(Constanst.PHONE, loginEntity.getPhone());
                        PreferenceUtil.putString(Constanst.SECOND_PHONE, loginEntity.getSecondPhone());
                        PreferenceUtil.putString(Constanst.IMAGE_URL, loginEntity.getHeadUrl());
                        PreferenceUtil.putString(Constanst.COMPANY, loginEntity.getCompanyName());
                        PreferenceUtil.putString(Constanst.COMPANY_CODE, loginEntity.getLicenseCode());
                        PreferenceUtil.putString(Constanst.STORE_NAME, loginEntity.getStoreName());
                        PreferenceUtil.putString(Constanst.POSITION, loginEntity.getPositions());
                        PreferenceUtil.putString(Constanst.INDUSTRYTYPE, loginEntity.getIndustryType());


                        TIMManager.getInstance().login(loginEntity.getIdentifier(), loginEntity.getUsersig(), new TIMCallBack() {
                            @Override
                            public void onError(int i, String s) {
                                Toast.makeText(LoginActivity.this, i + "== error == " + s, Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onSuccess() {
                                Intent intentLogin = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intentLogin);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                                    }
                                });
//
                            }
                        });

                        cancelProgressDialog();
                    }, throwable -> {
                        cancelProgressDialog();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(LoginActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                KyLog.d(throwable.toString());


                            }
                        });
                    });


        } else {
            Toast.makeText(this, "请填写手机号码或密码", Toast.LENGTH_SHORT).show();
        }
    }

    private void getNewMsg() {
        TIMManager.getInstance().addMessageListener(new TIMMessageListener() {
            @Override
            public boolean onNewMessages(List<TIMMessage> msgs) {
                KyLog.i("----------收到新消息---------");
                List<GetMessageEntity> list = new ArrayList<>();
                for (int i = 0; i < msgs.size(); i++) {
                    String text = "";
                    TIMMessage message = msgs.get(i);
                    if (i == 0) {
                        TIMElem elem = message.getElement(0);
                        if (elem.getType() == TIMElemType.Text) {
                            TIMTextElem e = (TIMTextElem) elem;
                            text = e.getText();
                        }
                    }
                    String sender = message.getSender();
                    String faceUrl = message.getSenderProfile().getFaceUrl();
                    TIMConversationType conversationType = message.getConversation().getType();
                    String type = conversationType.name();
                    long timeStamp = message.timestamp();
                    TIMConversation con = TIMManager.getInstance().getConversation(TIMConversationType.C2C, message.getConversation().getPeer());
                    TIMConversationExt conExt = new TIMConversationExt(con);
                    long count = conExt.getUnreadMessageNum();
                    GetMessageEntity entity = new GetMessageEntity();
                    entity.setHead_url(faceUrl);
                    entity.setId(sender);
                    entity.setMsg(text);
                    entity.setNum((int) count);
                    entity.setTimeStamp(timeStamp);
                    entity.setType(type);
                    list.add(entity);
                }
                GetMsgManager msgManager = GetMsgManager.instants();
                msgManager.setList(list);
                return true;
            }
        });
    }
}
