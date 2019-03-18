package com.huxin.communication.ui;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.view.View;
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

public class InvitationActivity extends BaseActivity {

    private TextView mTextViewCode;
    private String str;
    private ClipboardManager myClipboard;
    private ClipData myClip;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_home_invitation);
    }

    @Override
    protected void initViews() {
        setToolbarCenterMode("邀请", MODE_BACK);

        mTextViewCode = (TextView) findViewById(R.id.code);
        myClipboard = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);

        findViewById(R.id.copy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                copy();
            }
        });

    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        initData();
    }

    private void initData() {
        showProgressDialog();
        ApiModule.getInstance().matchingProducts()
                .subscribe(responseUntil -> {
                    cancelProgressDialog();
                    setData(responseUntil.getData());

                }, throwable -> {
                    KyLog.d(throwable.toString());
                    cancelProgressDialog();
                    Toast.makeText(this, throwable.getMessage().toString(), Toast.LENGTH_SHORT).show();
                });
    }

    private void setData(String entity) {
        mTextViewCode.setText(entity);
        str = entity;
    }

    public void copy() {
        myClip = ClipData.newPlainText("text", str);
        myClipboard.setPrimaryClip(myClip);
        Toast.makeText(getApplicationContext(), "复制成功", Toast.LENGTH_SHORT).show();
    }
}
