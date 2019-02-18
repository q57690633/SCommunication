package com.huxin.communication.ui.house;

import android.os.Bundle;

import com.huxin.communication.R;
import com.huxin.communication.base.BaseActivity;

/**
 * 消息提醒
 * Created by yangzanxiong on 2018/12/13.
 */

public class MessageRemindActivity extends BaseActivity {
    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_message_remind);
    }

    @Override
    protected void initViews() {
        setToolbarCenterMode("消息提醒", MODE_BACK);
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {

    }
}
