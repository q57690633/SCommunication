package com.huxin.communication.ui.house.phone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.huxin.communication.R;
import com.huxin.communication.base.BaseActivity;

public class AddFriendActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_add_friend);

        findViewById(R.id.btn_invitation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddFriendActivity.this, InvitationActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initViews() {
        setToolbarCenterMode("添加好友", MODE_BACK);
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {

    }

}
