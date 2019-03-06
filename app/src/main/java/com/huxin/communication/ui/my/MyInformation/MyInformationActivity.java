package com.huxin.communication.ui.my.MyInformation;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huxin.communication.R;
import com.huxin.communication.base.BaseActivity;
import com.huxin.communication.controls.Constanst;
import com.huxin.communication.ui.TIMChatActivity;
import com.huxin.communication.utils.PreferenceUtil;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.sky.kylog.KyLog;
import com.tencent.qcloud.uikit.BaseUIKitConfigs;
import com.tencent.qcloud.uikit.TUIKit;
import com.tencent.qcloud.uikit.common.IUIKitCallBack;

import java.io.File;

import static com.huxin.communication.utils.FileUtil.getRealFilePathFromUri;

public class MyInformationActivity extends BaseActivity implements View.OnClickListener {
    private RelativeLayout mRelativeLayoutHead;
    private RelativeLayout mRelativeLayoutPhone;
    private RelativeLayout mRelativeLayoutWork;
    private TextView mTextViewPhone;
    private Uri uri;

    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_my_information);
    }

    @Override
    protected void initViews() {
        setToolbarCenterMode(R.string.my_information, 0);
        mRelativeLayoutHead = (RelativeLayout) findViewById(R.id.rl_change_head);
        mRelativeLayoutPhone = (RelativeLayout) findViewById(R.id.rl_change_phone);
        mRelativeLayoutWork = (RelativeLayout) findViewById(R.id.rl_work_message);
        mTextViewPhone = findViewById(R.id.one_phone);
        mImageView = findViewById(R.id.image_title);

        mRelativeLayoutHead.setOnClickListener(this);
        mRelativeLayoutPhone.setOnClickListener(this);
        mRelativeLayoutWork.setOnClickListener(this);

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setData(uri);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }


    @Override
    protected void loadData(Bundle savedInstanceState) {


        if (!TextUtils.isEmpty(PreferenceUtil.getString(Constanst.IMAGE_URL))) {
            ImageLoader.getInstance().displayImage(PreferenceUtil.getString(Constanst.IMAGE_URL), mImageView);
        }else {
            mImageView.setBackgroundResource(R.drawable.head2);
        }
        if (!TextUtils.isEmpty(PreferenceUtil.getString(Constanst.PHONE))) {
            mTextViewPhone.setText(PreferenceUtil.getString(Constanst.PHONE));
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_work_message:
                Intent intentWork = new Intent(this, WorkMessageActivity.class);
                startActivity(intentWork);

                break;
            case R.id.rl_change_head:

                Intent intentHead = new Intent(this, ChangeHeadActivity.class);
                startActivity(intentHead);
                break;
            case R.id.rl_change_phone:
                Intent intentPhone = new Intent(this, ChangePhoneActivity.class);
                startActivity(intentPhone);
                break;
//            case R.id.tui_button:
//                String userId = PreferenceUtil.getInt(UID) + "";
//                String userSig = PreferenceUtil.getString("usersig");
//                KyLog.i("uid = " + userId);
//                KyLog.i("userSig = " + userSig);
//                onRecvUserSig(userId, userSig);
//                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        KyLog.d(requestCode + "ss");
                if (resultCode == RESULT_OK) {
                    Uri mSaveUri = Uri.fromFile(new File(getCacheDir(), "cropped_" + System.currentTimeMillis() + ".jpg"));
                    uri = data.getData();
                    if (uri == null) {
                        return;
                    }
                    String cropImagePath = getRealFilePathFromUri(getApplicationContext(), mSaveUri);
                    Bitmap bitMap = BitmapFactory.decodeFile(cropImagePath);
//                    if (type == 1) {
//                        headImage1.setImageBitmap(bitMap);
//                    } else {
                    mImageView.setImageBitmap(bitMap);
//                    }

                }

    }

}
