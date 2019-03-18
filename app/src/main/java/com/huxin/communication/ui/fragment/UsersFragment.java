package com.huxin.communication.ui.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.huxin.communication.R;
import com.huxin.communication.base.BaseFragment;
import com.huxin.communication.controls.Constanst;
import com.huxin.communication.ui.my.MyInformation.MyInformationActivity;
import com.huxin.communication.ui.my.collect.CollectionActivity;
import com.huxin.communication.ui.my.collect.DataBaseActivity;
import com.huxin.communication.ui.my.collect.DataBaseTravelActivity;
import com.huxin.communication.ui.my.feedback.FeedbackActivity;
import com.huxin.communication.ui.my.setting.SettingActivity;
import com.huxin.communication.ui.travel.CollectTravelActivity;
import com.huxin.communication.utils.PreferenceUtil;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.sky.kylog.KyLog;
import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMManager;

import java.io.File;

import static android.app.Activity.RESULT_OK;
import static com.huxin.communication.utils.FileUtil.getRealFilePathFromUri;

/**
 */
public class UsersFragment extends BaseFragment implements View.OnClickListener {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private RelativeLayout mRelativeLayoutMe;
    private RelativeLayout mRelativeLayoutSql;
    private RelativeLayout mRelativeLayoutCollect;
    private RelativeLayout mRelativeLayoutOpinion;
    private RelativeLayout mRelativeLayoutSetting;

    private ImageView mImageViewHead;
    private TextView mTextViewUserName;
    private TextView mTextViewPhone;
    private TextView mTextViewCompany;


    public UsersFragment() {
        // Required empty public constructor
    }

    private Uri uri;
    private Bitmap bitMap;


    public static UsersFragment newInstance(String param1, String param2) {
        UsersFragment fragment = new UsersFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_users, container, false);
    }

    @Override
    protected void initView(View view) {
        mRelativeLayoutCollect = (RelativeLayout) view.findViewById(R.id.rl_collect);
        mRelativeLayoutMe = (RelativeLayout) view.findViewById(R.id.rl_me);
        mRelativeLayoutOpinion = (RelativeLayout) view.findViewById(R.id.rl_opinion);
        mRelativeLayoutSetting = (RelativeLayout) view.findViewById(R.id.rl_setting);
        mRelativeLayoutSql = (RelativeLayout) view.findViewById(R.id.rl_sql);

        mTextViewCompany = (TextView) view.findViewById(R.id.company_name);
        mTextViewPhone = (TextView) view.findViewById(R.id.phone);
        mTextViewUserName = (TextView) view.findViewById(R.id.username);

        mImageViewHead = (ImageView) view.findViewById(R.id.image_title);


        mRelativeLayoutSql.setOnClickListener(this);
        mRelativeLayoutMe.setOnClickListener(this);
        mRelativeLayoutCollect.setOnClickListener(this);
        mRelativeLayoutSetting.setOnClickListener(this);
        mRelativeLayoutOpinion.setOnClickListener(this);
    }

    @Override
    protected void loadData() {


    }

    @Override
    protected void bindData() {

    }

    @Override
    public void onResume() {
        super.onResume();
        if (bitMap != null) {
            mImageViewHead.setImageBitmap(bitMap);
        }else {
            setData();
        }
    }

    private void setData() {
        KyLog.d(PreferenceUtil.getString(Constanst.PHONE));
        KyLog.d(PreferenceUtil.getString(Constanst.USER_NAME));
        KyLog.d(PreferenceUtil.getString(Constanst.COMPANY));
        KyLog.d(PreferenceUtil.getString(Constanst.IMAGE_URL));

        if (!TextUtils.isEmpty(PreferenceUtil.getString(Constanst.PHONE))) {
            mTextViewPhone.setText(PreferenceUtil.getString(Constanst.PHONE));
        }

        if (!TextUtils.isEmpty(PreferenceUtil.getString(Constanst.USER_NAME))) {
            mTextViewUserName.setText(PreferenceUtil.getString(Constanst.USER_NAME));
        }

        if (!TextUtils.isEmpty(PreferenceUtil.getString(Constanst.COMPANY))) {
            mTextViewCompany.setText(PreferenceUtil.getString(Constanst.COMPANY));
        }

        if (!TextUtils.isEmpty(PreferenceUtil.getString(Constanst.IMAGE_URL))) {
            ImageLoader.getInstance().displayImage(PreferenceUtil.getString(Constanst.IMAGE_URL), mImageViewHead);
        }else {
            mImageViewHead.setBackgroundResource(R.drawable.head2);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_collect:
                if (PreferenceUtil.getInt("type") == 1) {
                    Intent intentCollect = new Intent(getContext(), CollectionActivity.class);
                    startActivity(intentCollect);
                } else {
                    Intent intentCollect = new Intent(getContext(), CollectTravelActivity.class);
                    startActivity(intentCollect);
                }
                break;
            case R.id.rl_me:
                Intent intentMe = new Intent(getContext(), MyInformationActivity.class);
                startActivity(intentMe);
                break;
            case R.id.rl_opinion:
                Intent intentOpinion = new Intent(getContext(), FeedbackActivity.class);
                startActivity(intentOpinion);
                break;
            case R.id.rl_setting:
                Intent intentSetting = new Intent(getContext(), SettingActivity.class);
                startActivity(intentSetting);
                break;
            case R.id.rl_sql:
                if (PreferenceUtil.getInt("type") == 1) {
                    Intent intentSql = new Intent(getContext(), DataBaseActivity.class);
                    startActivity(intentSql);
                } else {
                    Intent intentSql = new Intent(getContext(), DataBaseTravelActivity.class);
                    startActivity(intentSql);
                }
                break;

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        KyLog.d(requestCode + "ss");
        if (resultCode == RESULT_OK) {
            Uri mSaveUri = Uri.fromFile(new File(getContext().getCacheDir(), "cropped_" + System.currentTimeMillis() + ".jpg"));
            uri = data.getData();
            if (uri == null) {
                return;
            }
            String cropImagePath = getRealFilePathFromUri(getContext(), mSaveUri);
            bitMap = BitmapFactory.decodeFile(cropImagePath);

        }

    }


}
