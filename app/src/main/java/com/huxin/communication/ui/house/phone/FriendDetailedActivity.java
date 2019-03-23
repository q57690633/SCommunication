package com.huxin.communication.ui.house.phone;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.huxin.communication.R;
import com.huxin.communication.base.BaseActivity;
import com.huxin.communication.controls.Constanst;
import com.huxin.communication.http.ApiModule;
import com.huxin.communication.ui.TIMChatActivity;
import com.huxin.communication.ui.fragment.AssortmentFragment;
import com.huxin.communication.ui.house.TopSelectionActivity;
import com.huxin.communication.ui.my.MyInformation.MyInformationActivity;
import com.huxin.communication.ui.my.collect.DataBaseActivity;
import com.huxin.communication.ui.my.collect.DataBaseTravelActivity;
import com.huxin.communication.ui.my.tuijian.TuiJianActivity;
import com.huxin.communication.ui.travel.TopSelectionTravelActivity;
import com.huxin.communication.utils.PreferenceUtil;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.sky.kylog.KyLog;
import com.tencent.qcloud.uikit.TUIKit;
import com.tencent.qcloud.uikit.common.IUIKitCallBack;

public class FriendDetailedActivity extends BaseActivity implements View.OnClickListener {

    private String name;
    private String address;
    private String industry;
    private String phone;
    private String starFriend;
    private String imageUrl;
    private String star;

    private int uid;

    private boolean isMessageAlert = true;
    private boolean isSetTop = true;
    private boolean isStarFriend = true;

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
    private ImageView mImageViewHead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        name = intent.getStringExtra(AssortmentFragment.NAME_TAG);
        address = intent.getStringExtra(AssortmentFragment.ADDRESS_TAG);
        industry = intent.getStringExtra(AssortmentFragment.INDUSTRY_TAG);
        phone = intent.getStringExtra(AssortmentFragment.PHONE_TAG);
        starFriend = intent.getStringExtra(AssortmentFragment.STAR_FRIEND_TAG);
        imageUrl = intent.getStringExtra(AssortmentFragment.IMAGE_TAG);
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
        mImageViewHead = findViewById(R.id.image_head);
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
        mStarFriendIv.setOnClickListener(this);
        mSetTopIv.setOnClickListener(this);
        mMessageAlertIv.setOnClickListener(this);

        if (!TextUtils.isEmpty(imageUrl)) {
            ImageLoader.getInstance().displayImage(imageUrl, mImageViewHead);
        } else {
            mImageViewHead.setBackgroundResource(R.drawable.head2);
        }

        findViewById(R.id.delete_phone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteFriend(String.valueOf(uid));
            }
        });
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {

        KyLog.d(PreferenceUtil.getInt(Constanst.ISMESSAGEALERT_TYPE) + "== star");
        KyLog.d(PreferenceUtil.getInt(Constanst.TOP_NAME) + " == star");
        star = getIntent().getStringExtra("starFriend");

        KyLog.d(star + "== star");



        if (PreferenceUtil.getInt(Constanst.ISMESSAGEALERT_TYPE) == 1){
            mMessageAlertIv.setBackgroundResource(R.drawable.switch_open);
            isMessageAlert = true;
        }else {
            mMessageAlertIv.setBackgroundResource(R.drawable.switch_close);
            isMessageAlert = false;
        }

        if (PreferenceUtil.getInt(Constanst.TOP_NAME) == 1){
            mSetTopIv.setBackgroundResource(R.drawable.switch_open);
            isMessageAlert = true;
        }else {
            mSetTopIv.setBackgroundResource(R.drawable.switch_close);
            isMessageAlert = false;
        }

        if (TextUtils.isEmpty(star)){
            isStarFriend = true;
            mStarFriendIv.setBackgroundResource(R.drawable.switch_close);
            mRelativeLayoutStarFriend.setVisibility(View.GONE);

        }else {
            isStarFriend = false;
            mStarFriendIv.setBackgroundResource(R.drawable.switch_open);
            mRelativeLayoutStarFriend.setVisibility(View.VISIBLE);


        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tuijian_rl:
                Intent intentTuiJian = new Intent(this, TuiJianActivity.class);
                startActivity(intentTuiJian);
                break;
            case R.id.history_rl:
                if (PreferenceUtil.getInt("type") == 1) {
                    Intent intent = new Intent(this, DataBaseActivity.class);
                    intent.putExtra("uid", uid);
                    intent.putExtra("dateNumber", 1);

                    startActivity(intent);
                } else {
                    Intent intent = new Intent(this, DataBaseTravelActivity.class);
                    intent.putExtra("uid", uid);
                    intent.putExtra("dateNumber", 1);

                    startActivity(intent);
                }
                break;
            case R.id.phone_rl:
                Intent intentphone = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                    return;
                }
                startActivity(intentphone);
                break;
            case R.id.confirm:
                String userId = PreferenceUtil.getInt("uid") + "";
                String userSig = PreferenceUtil.getString("usersig");
                showProgressDialog();
                TUIKit.login(userId, userSig, new IUIKitCallBack() {
                    @Override
                    public void onSuccess(Object data) {
                        cancelProgressDialog();
                        KyLog.i("home onSuccess", data);
                        Intent chatIntent = new Intent(FriendDetailedActivity.this, TIMChatActivity.class);
                        chatIntent.putExtra("TARGET_TYPE", "C2C");
                        chatIntent.putExtra("TARGET_ID", uid + "");
                        startActivity(chatIntent);
                    }

                    @Override
                    public void onError(String module, int errCode, String errMsg) {
                        cancelProgressDialog();
                        KyLog.e("home fail", errMsg);
                    }
                });
                break;
            case R.id.message_alert_iv:
                if (isMessageAlert) {
                    isMessageAlert = false;
                    PreferenceUtil.putInt(Constanst.ISMESSAGEALERT_TYPE,1);//1.代表静音
                    PreferenceUtil.putInt(Constanst.ISMESSAGEALERT_CODE,uid);
                    mMessageAlertIv.setBackgroundResource(R.drawable.switch_open);
                } else {
                    PreferenceUtil.putInt(Constanst.ISMESSAGEALERT_TYPE,0);//2.代表解除静音
                    PreferenceUtil.putInt(Constanst.ISMESSAGEALERT_CODE,uid);
                    isMessageAlert = true;
                    mMessageAlertIv.setBackgroundResource(R.drawable.switch_close);
                }
                break;
            case R.id.set_top_iv:
                if (isSetTop) {
                    isSetTop = false;
                    PreferenceUtil.putInt(Constanst.TOP_NAME,1);
                    mSetTopIv.setBackgroundResource(R.drawable.switch_open);
                } else {
                    PreferenceUtil.putInt(Constanst.TOP_NAME,0);
                    isSetTop = true;
                    mSetTopIv.setBackgroundResource(R.drawable.switch_close);
                }
                break;
            case R.id.star_friend_iv:
                if (isStarFriend) {
                    isStarFriend = false;
                    addStarFriend(String.valueOf(uid),"1");
                    mStarFriendIv.setBackgroundResource(R.drawable.switch_open);
                    mRelativeLayoutStarFriend.setVisibility(View.VISIBLE);
                } else {
                    isStarFriend = true;
                    addStarFriend(String.valueOf(uid),"0");
                    mStarFriendIv.setBackgroundResource(R.drawable.switch_close);
                    mRelativeLayoutStarFriend.setVisibility(View.GONE);
                }
                break;
        }
    }



    private void addStarFriend(String friendId, String type) {
        ApiModule.getInstance().addStarFriend(friendId,type)
                .subscribe(response -> {
                        Toast.makeText(this, response.getResultMsg(), Toast.LENGTH_SHORT).show();
                }, throwable -> {
                    KyLog.d(throwable.toString());
                    cancelProgressDialog();
                    Toast.makeText(this, throwable.getMessage().toString(), Toast.LENGTH_SHORT).show();
                });
    }

    private void deleteFriend(String friendId) {
        ApiModule.getInstance().deleteFriend(String.valueOf(PreferenceUtil.getInt(UID)),friendId)
                .subscribe(response -> {
                    Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
                }, throwable -> {
                    KyLog.d(throwable.toString());
                    cancelProgressDialog();
                    Toast.makeText(this, throwable.getMessage().toString(), Toast.LENGTH_SHORT).show();
                });
    }
}
