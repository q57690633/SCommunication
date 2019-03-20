package com.tencent.qcloud.uikit.business.chat.group.view;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.tencent.qcloud.uikit.R;

public class GroupDeleteMemberActivity extends AppCompatActivity {

    private final String TAG = "GroupDeleteMemberActivity";
    private Context mContext;
    private RecyclerView mDeleteMemberRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_delete_member);
        mDeleteMemberRv = (RecyclerView) findViewById(R.id.delete_member_rv);
    }
}
