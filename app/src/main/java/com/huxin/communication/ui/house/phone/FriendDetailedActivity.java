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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FriendDetailedActivity extends BaseActivity implements View.OnClickListener {

    private String name;
    private String address;
    private String industry;
    private String phone;
    private String starFriend;
    private String imageUrl;
    private String star;
    private String company;
    private String friend;


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
    private TextView mDelete;
    private ImageView mMessageAlertIv;
    private ImageView mSetTopIv;
    private ImageView mStarFriendIv;
    private ImageView mImageViewHead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();


    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_friend_detailed);
        name = getIntent().getStringExtra(AssortmentFragment.NAME_TAG);
        address = getIntent().getStringExtra(AssortmentFragment.ADDRESS_TAG);
        industry = getIntent().getStringExtra(AssortmentFragment.INDUSTRY_TAG);
        phone = getIntent().getStringExtra(AssortmentFragment.PHONE_TAG);
        starFriend = getIntent().getStringExtra(AssortmentFragment.STAR_FRIEND_TAG);
        imageUrl = getIntent().getStringExtra(AssortmentFragment.IMAGE_TAG);
        uid = getIntent().getIntExtra(AssortmentFragment.UID_TAG, 0);

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
        mDelete = findViewById(R.id.delete_phone);

        mRelativeLayoutHistory.setOnClickListener(this);
        mRelativeLayoutTuiJian.setOnClickListener(this);
        mRelativeLayoutPhone.setOnClickListener(this);
        mConfirmTv.setOnClickListener(this);
        mStarFriendIv.setOnClickListener(this);
        mSetTopIv.setOnClickListener(this);
        mMessageAlertIv.setOnClickListener(this);
        mDelete.setOnClickListener(this);


    }

    @Override
    protected void loadData(Bundle savedInstanceState) {

        getUseInfo(String.valueOf(uid));

        KyLog.d(PreferenceUtil.getInt(Constanst.ISMESSAGEALERT_TYPE) + "== star");
        KyLog.d(PreferenceUtil.getInt(Constanst.TOP_NAME) + " == star");
        star = getIntent().getStringExtra("star");
        company = getIntent().getStringExtra("companyFriend");
        friend = getIntent().getStringExtra("Friend");


        KyLog.d(star + "== star");
        KyLog.d(company + "== star");
        KyLog.d(friend + "== star");

        String groupTop = null;
        if (!TextUtils.isEmpty(star)) {
            groupTop = com.tencent.qcloud.uikit.PreferenceUtil.getString(this, "PersonStarTop");

        } else if (!TextUtils.isEmpty(company)) {
            groupTop = com.tencent.qcloud.uikit.PreferenceUtil.getString(this, "PersonCompanyTop");


        } else if (!TextUtils.isEmpty(friend)) {
            groupTop = com.tencent.qcloud.uikit.PreferenceUtil.getString(this, "PersonTop");

        }

        if (!TextUtils.isEmpty(groupTop)) {

            if (!TextUtils.isEmpty(groupTop)) {
                try {
                    JSONArray jsonArray = new JSONArray(groupTop);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        KyLog.d(jsonArray.getString(i));
                        if (jsonArray.getString(i).equalsIgnoreCase(String.valueOf(uid))) {
                            mSetTopIv.setBackgroundResource(R.drawable.switch_open);
                            isMessageAlert = false;
                        } else {
                            mSetTopIv.setBackgroundResource(R.drawable.switch_close);
                            isMessageAlert = true;
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            KyLog.d(uid + "");
        } else {
            mMessageAlertIv.setBackgroundResource(R.drawable.switch_close);
            isMessageAlert = true;

        }
        String mute = null;
        mute = com.tencent.qcloud.uikit.PreferenceUtil.getString(this, "mute");

        if (!TextUtils.isEmpty(mute)) {
            try {
                JSONArray jsonArray = new JSONArray(mute);
                for (int i = 0; i < jsonArray.length(); i++) {
                    KyLog.d(jsonArray.getString(i));
                    if (jsonArray.getString(i).equalsIgnoreCase(String.valueOf(uid))) {
                        mMessageAlertIv.setBackgroundResource(R.drawable.switch_open);
                        isMessageAlert = false;
                    } else {
                        mMessageAlertIv.setBackgroundResource(R.drawable.switch_close);
                        isMessageAlert = true;
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            KyLog.d(uid + "");
        } else {
            mMessageAlertIv.setBackgroundResource(R.drawable.switch_close);
            isMessageAlert = true;

        }

        if (!TextUtils.isEmpty(star)) {
            if (star.equalsIgnoreCase("star")) {
                isStarFriend = false;
                mStarFriendIv.setBackgroundResource(R.drawable.switch_open);
                mRelativeLayoutStarFriend.setVisibility(View.VISIBLE);

            } else {
                isStarFriend = true;
                mStarFriendIv.setBackgroundResource(R.drawable.switch_close);
                mRelativeLayoutStarFriend.setVisibility(View.GONE);
            }
        } else {
            isStarFriend = true;
            mStarFriendIv.setBackgroundResource(R.drawable.switch_close);
            mRelativeLayoutStarFriend.setVisibility(View.GONE);
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tuijian_rl:
                Intent intentTuiJian = new Intent(this, TuiJianActivity.class);
                intentTuiJian.putExtra("data", getData());
                intentTuiJian.putExtra("from", "tuijian");
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
                        chatIntent.putExtra("username", name);
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
                    String str = null;
                    str = com.tencent.qcloud.uikit.PreferenceUtil.getString(this, "mute");

                    try {
                        JSONArray array;
                        if (null == str) {
                            array = new JSONArray();
                        } else {
                            array = new JSONArray(str);
                        }
                        array.put(uid);
                        com.tencent.qcloud.uikit.PreferenceUtil.putString(this, "mute", array.toString());

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    mMessageAlertIv.setBackgroundResource(R.drawable.switch_open);
                } else {
                    String str = null;

                    str = com.tencent.qcloud.uikit.PreferenceUtil.getString(this, "mute");

                    try {
                        JSONArray jsonArray = new JSONArray(str);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            if (String.valueOf(uid).equals(jsonArray.getString(i))) {
                                jsonArray.remove(i);
                            }
                            com.tencent.qcloud.uikit.PreferenceUtil.putString(this, "mute", jsonArray.toString());
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    isMessageAlert = true;
                    mMessageAlertIv.setBackgroundResource(R.drawable.switch_close);
                }
                break;
            case R.id.set_top_iv:
                if (isSetTop) {
                    isSetTop = false;
                    String str = null;

                    if (!TextUtils.isEmpty(star)) {
                        str = com.tencent.qcloud.uikit.PreferenceUtil.getString(this, "PersonStarTop");

                    } else if (!TextUtils.isEmpty(company)) {
                        str = com.tencent.qcloud.uikit.PreferenceUtil.getString(this, "PersonCompanyTop");


                    } else if (!TextUtils.isEmpty(friend)) {
                        str = com.tencent.qcloud.uikit.PreferenceUtil.getString(this, "PersonTop");

                    }

                    try {
                        JSONArray array;
                        if (null == str) {
                            array = new JSONArray();
                        } else {
                            array = new JSONArray(str);
                        }
                        array.put(uid);
                        if (!TextUtils.isEmpty(star)) {
                            com.tencent.qcloud.uikit.PreferenceUtil.putString(this, "PersonStarTop", array.toString());

                        } else if (!TextUtils.isEmpty(company)) {
                            com.tencent.qcloud.uikit.PreferenceUtil.putString(this, "PersonCompanyTop", array.toString());

                        } else if (!TextUtils.isEmpty(friend)) {
                            com.tencent.qcloud.uikit.PreferenceUtil.putString(this, "PersonTop", array.toString());


                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    mSetTopIv.setBackgroundResource(R.drawable.switch_open);
                } else {

                    String str = null;

                    if (!TextUtils.isEmpty(star)) {
                        str = com.tencent.qcloud.uikit.PreferenceUtil.getString(this, "PersonStarTop");

                    } else if (!TextUtils.isEmpty(company)) {
                        str = com.tencent.qcloud.uikit.PreferenceUtil.getString(this, "PersonCompanyTop");


                    } else if (!TextUtils.isEmpty(friend)) {
                        str = com.tencent.qcloud.uikit.PreferenceUtil.getString(this, "PersonTop");

                    }
                    try {
                        JSONArray jsonArray = new JSONArray(str);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            if (String.valueOf(uid).equals(jsonArray.getString(i))) {
                                jsonArray.remove(i);
                            }
                            if (!TextUtils.isEmpty(star)) {
                                com.tencent.qcloud.uikit.PreferenceUtil.putString(this, "PersonStarTop", jsonArray.toString());

                            } else if (!TextUtils.isEmpty(company)) {
                                com.tencent.qcloud.uikit.PreferenceUtil.putString(this, "PersonCompanyTop", jsonArray.toString());

                            } else if (!TextUtils.isEmpty(friend)) {
                                com.tencent.qcloud.uikit.PreferenceUtil.putString(this, "PersonTop", jsonArray.toString());


                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    isSetTop = true;
                    mSetTopIv.setBackgroundResource(R.drawable.switch_close);
                }
                break;
            case R.id.star_friend_iv:
                if (isStarFriend) {
                    isStarFriend = false;
                    addStarFriend(String.valueOf(uid), "1");
                    mStarFriendIv.setBackgroundResource(R.drawable.switch_open);
                    mRelativeLayoutStarFriend.setVisibility(View.VISIBLE);
                } else {
                    isStarFriend = true;
                    addStarFriend(String.valueOf(uid), "0");
                    mStarFriendIv.setBackgroundResource(R.drawable.switch_close);
                    mRelativeLayoutStarFriend.setVisibility(View.GONE);
                }
                break;

            case R.id.delete_phone:
                deleteFriend(String.valueOf(uid));
                break;
        }
    }

    private String getData() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("headUrl", imageUrl);
            jsonObject.put("phone", phone);
            jsonObject.put("uid", uid);
            jsonObject.put("username", name);
        } catch (Exception e) {

        }
        return jsonObject.toString();
    }


    private void addStarFriend(String friendId, String type) {
        ApiModule.getInstance().addStarFriend(friendId, type)
                .subscribe(response -> {
                    Toast.makeText(this, response.getResultMsg(), Toast.LENGTH_SHORT).show();
                }, throwable -> {
                    KyLog.d(throwable.toString());
                    cancelProgressDialog();
                    Toast.makeText(this, throwable.getMessage().toString(), Toast.LENGTH_SHORT).show();
                });
    }

    private void deleteFriend(String friendId) {
        ApiModule.getInstance().deleteFriend(String.valueOf(PreferenceUtil.getInt(UID)), friendId)
                .subscribe(response -> {
                    Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
                }, throwable -> {
                    KyLog.d(throwable.toString());
                    cancelProgressDialog();
                    Toast.makeText(this, throwable.getMessage().toString(), Toast.LENGTH_SHORT).show();
                });
    }

    private void getUseInfo(String id) {
        ApiModule.getInstance().getUserInfo(id)
                .subscribe(loginEntity -> {
                    KyLog.object(loginEntity.getStickNumber());
                    mNameTv.setText(loginEntity.getUsername());
                    mAddressTv.setText(loginEntity.getCompanyName());
                    mIndustryTv.setText(loginEntity.getIndustryType());
                    mPhoneTv.setText(getResources().getString(R.string.phone) + loginEntity.getPhone());

                    if (!TextUtils.isEmpty(loginEntity.getHeadUrl())) {
                        ImageLoader.getInstance().displayImage(loginEntity.getHeadUrl(), mImageViewHead);
                    } else {
                        mImageViewHead.setBackgroundResource(R.drawable.head2);
                    }

                    if (loginEntity.getIsFriend() == 0) {
                        mDelete.setVisibility(View.GONE);
                    } else if (loginEntity.getIsFriend() == 1) {
                        mDelete.setVisibility(View.VISIBLE);

                    }

//                    if (loginEntity.getIsStarFriend() == 0) {
//                        isStarFriend = true;
//                        mStarFriendIv.setBackgroundResource(R.drawable.switch_close);
//                        mRelativeLayoutStarFriend.setVisibility(View.GONE);
//                    } else if (loginEntity.getIsStarFriend() == 1) {
//                        isStarFriend = false;
//                        mStarFriendIv.setBackgroundResource(R.drawable.switch_open);
//                        mRelativeLayoutStarFriend.setVisibility(View.VISIBLE);
//                    }
                }, throwable -> {
//                    Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    KyLog.d(throwable.getMessage());
                });
    }
}
