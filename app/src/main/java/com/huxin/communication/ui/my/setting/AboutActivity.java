package com.huxin.communication.ui.my.setting;

import android.os.Bundle;

import com.huxin.communication.R;
import com.huxin.communication.base.BaseActivity;

public class AboutActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_about);
    }

    @Override
    protected void initViews() {
        setToolbarCenterMode("关于我们", MODE_BACK);
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {

    }

}
