package com.huxin.communication.ui.house.phone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.huxin.communication.R;
import com.huxin.communication.base.BaseActivity;
import com.huxin.communication.ui.house.TopSelectionActivity;
import com.huxin.communication.ui.my.tuijian.TuiJianActivity;
import com.huxin.communication.ui.travel.TopSelectionTravelActivity;
import com.huxin.communication.utils.PreferenceUtil;

public class FriendDetailedActivity extends BaseActivity implements View.OnClickListener {

    private RelativeLayout mRelativeLayoutTuiJian;
    private RelativeLayout mRelativeLayoutHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_friend_detailed);

    }

    @Override
    protected void initViews() {
        setToolbarCenterMode("详细资料", MODE_BACK);
        mRelativeLayoutHistory = (RelativeLayout) findViewById(R.id.history_rl);
        mRelativeLayoutTuiJian = (RelativeLayout) findViewById(R.id.tuijian_rl);
        mRelativeLayoutHistory.setOnClickListener(this);
        mRelativeLayoutTuiJian.setOnClickListener(this);
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tuijian_rl:
                Intent intentTuiJian = new Intent(this, TuiJianActivity.class);
                startActivity(intentTuiJian);
                break;
            case R.id.history_rl:
                if (PreferenceUtil.getInt("type") == 1){
                    Intent intent = new Intent(this, TopSelectionActivity.class);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(this, TopSelectionTravelActivity.class);
                    startActivity(intent);
                }
                break;
        }
    }
}
