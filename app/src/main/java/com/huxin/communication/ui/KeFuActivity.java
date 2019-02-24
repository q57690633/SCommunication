package com.huxin.communication.ui;

import android.os.Bundle;

import com.huxin.communication.R;
import com.huxin.communication.base.BaseActivity;

public class KeFuActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected void initContentView() {

        setContentView(R.layout.activity_ke_fu);
    }

    @Override
    protected void initViews() {
        setToolbarCenterMode("客服",MODE_BACK);
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {

    }

}
