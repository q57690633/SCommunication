package com.huxin.communication.ui.my.setting;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.huxin.communication.R;
import com.huxin.communication.base.BaseActivity;

public class NewMessageActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_new_message);
    }

    @Override
    protected void initViews() {
        setToolbarCenterMode("新消息通知",MODE_BACK);
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {

    }

}
