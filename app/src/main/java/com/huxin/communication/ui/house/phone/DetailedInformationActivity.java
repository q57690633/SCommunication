package com.huxin.communication.ui.house.phone;

import android.os.Bundle;

import com.huxin.communication.R;
import com.huxin.communication.base.BaseActivity;

public class DetailedInformationActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_detailed_information);
    }

    @Override
    protected void initViews() {
        setToolbarCenterMode("详细资料", MODE_BACK);
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {

    }

}
