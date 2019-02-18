package com.huxin.communication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huxin.communication.R;
import com.huxin.communication.base.BaseActivity;
import com.huxin.communication.utils.PreferenceUtil;

public class RegisterOneActivity extends BaseActivity implements View.OnClickListener {

    private TextView mTextViewConfirm;
    private TextView mTextViewHouse;
    private TextView mTextViewHouseLine;
    private TextView mTextViewTravel;
    private TextView mTextViewTravelLine;
    private LinearLayout mLinearLayoutHouse;
    private LinearLayout mLinearLayoutTravel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_register_one);
    }

    @Override
    protected void initViews() {
        setToolbarCenterMode("注册", MODE_BACK);
        mTextViewConfirm = (TextView) findViewById(R.id.confirm);
        mTextViewHouse = (TextView) findViewById(R.id.tv_house);
        mTextViewHouseLine = (TextView) findViewById(R.id.tv_line_house);
        mTextViewTravel = (TextView) findViewById(R.id.tv_travel);
        mTextViewTravelLine = (TextView) findViewById(R.id.tv_line_travel);
        mLinearLayoutHouse = (LinearLayout) findViewById(R.id.house);
        mLinearLayoutTravel = (LinearLayout) findViewById(R.id.travel);
        mTextViewConfirm.setOnClickListener(this);
        mLinearLayoutHouse.setOnClickListener(this);
        mLinearLayoutTravel.setOnClickListener(this);
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        initData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.confirm:
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.house:
                mTextViewTravelLine.setVisibility(View.GONE);
                mTextViewHouseLine.setVisibility(View.VISIBLE);
                mTextViewHouseLine.setBackgroundResource(R.color.blue);
                mTextViewTravel.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewHouse.setTextColor(getResources().getColor(R.color.blue));
                PreferenceUtil.putInt("type", 1);

                break;
            case R.id.travel:
                mTextViewTravelLine.setBackgroundResource(R.color.blue);
                mTextViewTravelLine.setVisibility(View.VISIBLE);
                mTextViewHouseLine.setVisibility(View.GONE);
                mTextViewTravel.setTextColor(getResources().getColor(R.color.blue));
                mTextViewHouse.setTextColor(getResources().getColor(R.color.register_font));
                PreferenceUtil.putInt("type", 2);
                break;
        }
    }

    /**
     * 初始化数据
     */
    private void initData() {
        if (PreferenceUtil.getInt("type") != 2) {
            mTextViewTravelLine.setVisibility(View.GONE);
            mTextViewHouseLine.setVisibility(View.VISIBLE);
            mTextViewHouseLine.setBackgroundResource(R.color.blue);
            mTextViewTravel.setTextColor(getResources().getColor(R.color.register_font));
            mTextViewHouse.setTextColor(getResources().getColor(R.color.blue));
        } else {
            mTextViewTravelLine.setBackgroundResource(R.color.blue);
            mTextViewTravelLine.setVisibility(View.VISIBLE);
            mTextViewHouseLine.setVisibility(View.GONE);
            mTextViewTravel.setTextColor(getResources().getColor(R.color.blue));
            mTextViewHouse.setTextColor(getResources().getColor(R.color.register_font));
        }
    }
}
