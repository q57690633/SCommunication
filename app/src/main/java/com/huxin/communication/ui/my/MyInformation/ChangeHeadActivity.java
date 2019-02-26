package com.huxin.communication.ui.my.MyInformation;

import android.os.Bundle;

import com.huxin.communication.R;
import com.huxin.communication.base.BaseActivity;

public class ChangeHeadActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_change_head);
    }

    @Override
    protected void initViews() {
        setToolbarCenterMode("头像", MODE_BACK);
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {

    }

}
