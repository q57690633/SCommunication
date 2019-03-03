package com.huxin.communication.ui.house.phone;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huxin.communication.R;
import com.huxin.communication.base.BaseActivity;
import com.huxin.communication.ui.TIMChatActivity;
import com.huxin.communication.ui.fragment.AssortmentFragment;
import com.huxin.communication.ui.house.TopSelectionActivity;
import com.huxin.communication.ui.my.tuijian.TuiJianActivity;
import com.huxin.communication.ui.travel.TopSelectionTravelActivity;
import com.huxin.communication.utils.PreferenceUtil;
import com.sky.kylog.KyLog;

public class FriendDetailedActivity extends BaseActivity implements View.OnClickListener {

    private String name;
    private String address;
    private String industry;
    private String phone;
    private String starFriend;
    private int uid;

    private boolean isMessageAlert = false;
    private boolean isSetTop = false;
    private boolean isStarFriend = false;

    private RelativeLayout mRelativeLayoutTuiJian;
    private RelativeLayout mRelativeLayoutHistory;
    private RelativeLayout mRelativeLayoutPhone;
    private RelativeLayout mRelativeLayoutStarFriend;

    private TextView mNameTv;
    private TextView mAddressTv;
    private TextView mIndustryTv;
    private TextView mPhoneTv;
    private TextView mConfirmTv;
    private ImageView mMessageAlertIv;
    private ImageView mSetTopIv;
    private ImageView mStarFriendIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        name = intent.getStringExtra(AssortmentFragment.NAME_TAG);
        address = intent.getStringExtra(AssortmentFragment.ADDRESS_TAG);
        industry = intent.getStringExtra(AssortmentFragment.INDUSTRY_TAG);
        phone = intent.getStringExtra(AssortmentFragment.PHONE_TAG);
        starFriend = intent.getStringExtra(AssortmentFragment.STAR_FRIEND_TAG);
        uid = intent.getIntExtra(AssortmentFragment.UID_TAG, 0);
        mNameTv.setText(name);
        mAddressTv.setText(address);
        mIndustryTv.setText(industry);
        mPhoneTv.setText(getResources().getString(R.string.phone) + phone);
    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_friend_detailed);

    }

    @Override
    protected void initViews() {
        setToolbarCenterMode(getResources().getString(R.string.detailed_information), MODE_BACK);
        mRelativeLayoutHistory = (RelativeLayout) findViewById(R.id.history_rl);
        mRelativeLayoutTuiJian = (RelativeLayout) findViewById(R.id.tuijian_rl);
        mRelativeLayoutPhone = (RelativeLayout) findViewById(R.id.phone_rl);
        mRelativeLayoutStarFriend = (RelativeLayout) findViewById(R.id.star_rl);
        mNameTv = (TextView) findViewById(R.id.name_tv);
        mAddressTv = (TextView) findViewById(R.id.address_tv);
        mIndustryTv = (TextView) findViewById(R.id.industry_type_tv);
        mPhoneTv = (TextView) findViewById(R.id.phone_tv);
        mConfirmTv = (TextView) findViewById(R.id.confirm);
        mMessageAlertIv = (ImageView) findViewById(R.id.message_alert_iv);
        mSetTopIv = (ImageView) findViewById(R.id.set_top_iv);
        mStarFriendIv = (ImageView) findViewById(R.id.star_friend_iv);

        mRelativeLayoutHistory.setOnClickListener(this);
        mRelativeLayoutTuiJian.setOnClickListener(this);
        mRelativeLayoutPhone.setOnClickListener(this);
        mConfirmTv.setOnClickListener(this);

        if(isMessageAlert) {
            mMessageAlertIv.setImageDrawable(getDrawable(R.drawable.switch_open));
        }else {
            mMessageAlertIv.setImageDrawable(getDrawable(R.drawable.switch_close));
        }

        if(isSetTop) {
            mSetTopIv.setImageDrawable(getDrawable(R.drawable.switch_open));
        }else {
            mSetTopIv.setImageDrawable(getDrawable(R.drawable.switch_close));
        }

        if(isStarFriend) {
            mStarFriendIv.setImageDrawable(getDrawable(R.drawable.switch_open));
            mRelativeLayoutStarFriend.setVisibility(View.VISIBLE);
        }else {
            mStarFriendIv.setImageDrawable(getDrawable(R.drawable.switch_close));
            mRelativeLayoutStarFriend.setVisibility(View.GONE);
        }
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
                    intent.putExtra("uid", uid);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(this, TopSelectionTravelActivity.class);
                    intent.putExtra("uid", uid);
                    startActivity(intent);
                }
                break;
            case R.id.phone_rl:
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
                startActivity(intent);
                break;
            case R.id.confirm:
                Intent chatIntent = new Intent(this, TIMChatActivity.class);
                chatIntent.putExtra("TARGET_TYPE", "C2C");
                chatIntent.putExtra("TARGET_ID", uid + "");
                startActivity(chatIntent);
        }
    }
}
