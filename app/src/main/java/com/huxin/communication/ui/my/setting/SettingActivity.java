package com.huxin.communication.ui.my.setting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.huxin.communication.R;
import com.huxin.communication.base.BaseActivity;
import com.huxin.communication.controls.Constanst;
import com.huxin.communication.ui.LoginActivity;
import com.huxin.communication.utils.PreferenceUtil;
import com.sky.kylog.KyLog;
import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMManager;

public class SettingActivity extends BaseActivity implements View.OnClickListener {

    private RelativeLayout mRelativeLayoutNewMessage;
    private RelativeLayout mRelativeLayoutAbout;
    private TextView mTextViewExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_setting);
    }

    @Override
    protected void initViews() {
        setToolbarCenterMode("设置", MODE_BACK);
        mRelativeLayoutAbout = (RelativeLayout) findViewById(R.id.rl_about);
        mRelativeLayoutNewMessage = (RelativeLayout) findViewById(R.id.rl_new_message);
        mTextViewExit = (TextView) findViewById(R.id.exit);
        mRelativeLayoutNewMessage.setOnClickListener(this);
        mRelativeLayoutAbout.setOnClickListener(this);
        mTextViewExit.setOnClickListener(this);
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_about:
                Intent intentAbout = new Intent(this, AboutActivity.class);
                startActivity(intentAbout);
                break;

            case R.id.rl_new_message:
                Intent intentNewMessage = new Intent(this, NewMessageActivity.class);
                startActivity(intentNewMessage);
                break;
            case R.id.exit:
               loginout();
                break;
        }

    }

    public void loginout(){
        showProgressDialog();
        //登出
        TIMManager.getInstance().logout(new TIMCallBack() {
            @Override
            public void onError(int code, String desc) {
            cancelProgressDialog();
                //错误码 code 和错误描述 desc，可用于定位请求失败原因
                //错误码 code 列表请参见错误码表
                KyLog.d( "logout failed. code: " + code + " errmsg: " + desc);
            }

            @Override
            public void onSuccess() {
                //登出成功
                cancelProgressDialog();
                Intent intentExit = new Intent(SettingActivity.this, LoginActivity.class);
                startActivity(intentExit);
                finish();
                PreferenceUtil.removeSp(TOKEN,Constanst.SP_NAME);
                Toast.makeText(SettingActivity.this, "退出成功", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
