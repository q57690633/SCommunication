package com.huxin.communication.ui.house.phone;

import android.os.Bundle;

import com.huxin.communication.R;
import com.huxin.communication.base.BaseActivity;

public class InvitationsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_invitation);

    }

    @Override
    protected void initViews() {
        setToolbarCenterMode("邀请", MODE_BACK);
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {

    }

}