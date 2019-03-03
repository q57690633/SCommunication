package com.huxin.communication.ui;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.huxin.communication.R;
import com.huxin.communication.base.BaseActivity;
import com.huxin.communication.entity.InvitationEntity;
import com.huxin.communication.http.ApiModule;
import com.sky.kylog.KyLog;

/**
 * 邀请码
 * Created by yangzanxiong on 2018/12/13.
 */

public class InvitationActivity extends BaseActivity{

    private TextView mTextViewCode;
    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_home_invitation);
    }

    @Override
    protected void initViews() {
        setToolbarCenterMode("邀请",MODE_BACK);
        mTextViewCode = (TextView) findViewById(R.id.code);

    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        initData();
    }

    private void initData(){
        showProgressDialog();
        ApiModule.getInstance().matchingProducts()
                .subscribe(responseUntil   -> {
                    cancelProgressDialog();
                        setData(responseUntil.getData());

                }, throwable -> {
                    KyLog.d(throwable.toString());
                    cancelProgressDialog();
                    Toast.makeText(this, throwable.getMessage().toString(), Toast.LENGTH_SHORT).show();
                });
    }

    private void setData(String entity){
        mTextViewCode.setText(entity);
    }
}
