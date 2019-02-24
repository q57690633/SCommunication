package com.huxin.communication.ui.travel.release;

import android.os.Bundle;

import com.huxin.communication.R;
import com.huxin.communication.base.BaseActivity;

public class ReleaseInterfaceActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_release_interface2);
    }

    @Override
    protected void initViews() {
        setToolbarCenterMode("发布", MODE_BACK);
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {

    }

}
