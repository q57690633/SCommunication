package com.huxin.communication.ui.my.MyInformation;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.huxin.communication.R;
import com.huxin.communication.base.BaseActivity;

public class WorkMessageActivity extends BaseActivity {
    private TextView mTextViewWanCheng;
    private TextView mTextViewBianJi;
    private boolean isClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_work_message);
    }

    @Override
    protected void initViews() {
        setToolbarCenterMode("工作信息", MODE_BACK);
        mTextViewBianJi = (TextView) findViewById(R.id.toolbar_bianji);
        mTextViewWanCheng = (TextView) findViewById(R.id.toolbar_quxiao);
        mTextViewWanCheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mTextViewBianJi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isClicked) {
                    mTextViewWanCheng.setVisibility(View.VISIBLE);
                    mTextViewBianJi.setVisibility(View.GONE);
                    isClicked = false;
                } else {
                    mTextViewBianJi.setVisibility(View.VISIBLE);
                    mTextViewWanCheng.setVisibility(View.GONE);
                    isClicked = true;
                }
            }
        });
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        if (!isClicked) {
            mTextViewBianJi.setVisibility(View.VISIBLE);
            mTextViewWanCheng.setVisibility(View.GONE);
        }
    }

}
