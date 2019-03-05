package com.huxin.communication.ui.house.phone;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.huxin.communication.R;
import com.huxin.communication.base.BaseActivity;
import com.huxin.communication.entity.PhoneSearchEntity;
import com.huxin.communication.http.ApiModule;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.sky.kylog.KyLog;

public class DetailedInformationActivity extends BaseActivity {


    private PhoneSearchEntity searchEntity;

    private TextView mTextViewUserName;
    private TextView mTextViewPhone;
    private TextView mTextViewCompany;
    private TextView mTextViewPositions;
    private ImageView mImageViewHead;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_detailed_information);
    }

    @Override
    protected void initViews() {
        setToolbarCenterMode("详细资料", MODE_BACK);
        searchEntity = getIntent().getParcelableExtra("addfriend");
        mImageViewHead = findViewById(R.id.image_head);
        mTextViewCompany = findViewById(R.id.company_name);
        mTextViewPhone = findViewById(R.id.phone);
        mTextViewPositions = findViewById(R.id.positions);
        mTextViewUserName = findViewById(R.id.username);

        findViewById(R.id.add_friend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (searchEntity.getUid() != 0){
                    addTencentFriend(String.valueOf(searchEntity.getUid()));
                }
            }
        });

    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        setData(searchEntity);

    }

    private void setData(PhoneSearchEntity entity){
        if (entity != null) {
            mTextViewUserName.setText(entity.getUsername());
            mTextViewPositions.setText(entity.getPositions());
            mTextViewPhone.setText("电话：" + entity.getPhone());
            mTextViewCompany.setText(entity.getCompanyName());
            if (TextUtils.isEmpty(entity.getHeadUrl())){
                ImageLoader.getInstance().displayImage(entity.getHeadUrl(),mImageViewHead);
            }else {
                mImageViewHead.setBackgroundResource(R.drawable.head2);
            }
        }
    }

    private void addTencentFriend(String friendId) {

        showProgressDialog();
        ApiModule.getInstance().addTencentFriend(friendId)
                .subscribe(response  -> {
                    Toast.makeText(this, response.getResultMsg() + "", Toast.LENGTH_SHORT).show();
                    cancelProgressDialog();
                }, throwable -> {
                    KyLog.d(throwable.toString());
                    cancelProgressDialog();
                    Toast.makeText(this, throwable.getMessage().toString(), Toast.LENGTH_SHORT).show();
                });
    }

}
