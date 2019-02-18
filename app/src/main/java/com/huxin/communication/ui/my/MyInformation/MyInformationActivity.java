package com.huxin.communication.ui.my.MyInformation;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;

import com.huxin.communication.R;
import com.huxin.communication.base.BaseActivity;
import com.huxin.communication.ui.my.setting.AboutActivity;

public class MyInformationActivity extends BaseActivity implements View.OnClickListener {
    private RelativeLayout mRelativeLayoutHead;
    private RelativeLayout mRelativeLayoutPhone;
    private RelativeLayout mRelativeLayoutWork;

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

        mRelativeLayoutHead.setOnClickListener(this);
        mRelativeLayoutPhone.setOnClickListener(this);
        mRelativeLayoutWork.setOnClickListener(this);

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
        }
    }
}
