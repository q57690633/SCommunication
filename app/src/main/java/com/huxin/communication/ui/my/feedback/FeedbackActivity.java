package com.huxin.communication.ui.my.feedback;

import android.os.Bundle;

import com.huxin.communication.R;
import com.huxin.communication.base.BaseActivity;

public class FeedbackActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_feedback);
    }

    @Override
    protected void initViews() {
        setToolbarCenterMode("意见反馈",MODE_BACK);
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {

    }

}
