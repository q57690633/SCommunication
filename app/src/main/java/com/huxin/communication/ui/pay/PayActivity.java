package com.huxin.communication.ui.pay;

import android.os.Bundle;

import com.huxin.communication.R;
import com.huxin.communication.base.BaseActivity;

public class PayActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_pay);
    }

    @Override
    protected void initViews() {
        setToolbarCenterMode("会员",MODE_BACK);

    }

    @Override
    protected void loadData(Bundle savedInstanceState) {

    }

}
