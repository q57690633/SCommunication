package com.huxin.communication.ui.my.MyInformation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.huxin.communication.R;
import com.huxin.communication.base.BaseActivity;
import com.huxin.communication.controls.Constanst;
import com.huxin.communication.ui.TIMChatActivity;
import com.huxin.communication.utils.PreferenceUtil;
import com.sky.kylog.KyLog;
import com.tencent.qcloud.uikit.BaseUIKitConfigs;
import com.tencent.qcloud.uikit.TUIKit;
import com.tencent.qcloud.uikit.common.IUIKitCallBack;

public class MyInformationActivity extends BaseActivity implements View.OnClickListener {
    private RelativeLayout mRelativeLayoutHead;
    private RelativeLayout mRelativeLayoutPhone;
    private RelativeLayout mRelativeLayoutWork;
    private Button mTUIButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_my_information);
    }

    @Override
    protected void initViews() {
        setToolbarCenterMode(R.string.my_information, MODE_BACK);
        mRelativeLayoutHead = (RelativeLayout) findViewById(R.id.rl_change_head);
        mRelativeLayoutPhone = (RelativeLayout) findViewById(R.id.rl_change_phone);
        mRelativeLayoutWork = (RelativeLayout) findViewById(R.id.rl_work_message);
        mTUIButton = (Button) findViewById(R.id.tui_button);

        mRelativeLayoutHead.setOnClickListener(this);
        mRelativeLayoutPhone.setOnClickListener(this);
        mRelativeLayoutWork.setOnClickListener(this);
        mTUIButton.setOnClickListener(this);

    }


    @Override
    protected void loadData(Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_work_message:
                Intent intentWork = new Intent(this, WorkMessageActivity.class);
                startActivity(intentWork);

                break;
            case R.id.rl_change_head:

                Intent intentHead = new Intent(this, ChangeHeadActivity.class);
                startActivity(intentHead);
                break;
            case R.id.rl_change_phone:
                Intent intentPhone = new Intent(this, ChangePhoneActivity.class);
                startActivity(intentPhone);
                break;
            case R.id.tui_button:
                String userId = PreferenceUtil.getInt(UID) + "";
                String userSig = PreferenceUtil.getString("usersig");
                KyLog.i("uid = " + userId);
                KyLog.i("userSig = " + userSig);
                onRecvUserSig(userId, userSig);
                break;
        }
    }

    private void onRecvUserSig(String userId, String userSig) {
        TUIKit.login(userId, userSig, new IUIKitCallBack() {
            @Override
            public void onSuccess(Object data) {
                KyLog.i("imlogin onSuccess", data);
                Intent intent = new Intent(MyInformationActivity.this, TIMChatActivity.class);
                startActivity(intent);
            }
            @Override
            public void onError(String module, int errCode, String errMsg) {
                KyLog.e("imlogin fail", errMsg);
            }
        });
    }
}
