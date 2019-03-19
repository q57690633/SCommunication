package com.huxin.communication.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.huxin.communication.R;
import com.huxin.communication.ui.fragment.GroupChatFragment;
import com.huxin.communication.ui.fragment.PersonalChatFragment;
import com.tencent.qcloud.uikit.common.BaseFragment;

public class TIMChatActivity extends AppCompatActivity {

    BaseFragment mCurrentFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timchat);
        Bundle bundle = getIntent().getExtras();
        String type = getIntent().getStringExtra("TARGET_TYPE");
        if("group".equalsIgnoreCase(type)) {
            mCurrentFragment = new GroupChatFragment();
        }else {
            mCurrentFragment = new PersonalChatFragment();
        }

        if (mCurrentFragment != null) {
            mCurrentFragment.setArguments(bundle);
            getFragmentManager().beginTransaction().replace(R.id.empty_view, mCurrentFragment).commitAllowingStateLoss();
        }
    }
}
