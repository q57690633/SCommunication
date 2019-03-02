package com.huxin.communication.ui.my.feedback;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.TextView;

import com.huxin.communication.R;
import com.huxin.communication.base.BaseActivity;

public class FeedbackActivity extends BaseActivity {
    private TextView mTextViewGongNen;
    private TextView mTextViewTiJIan;
    private TextView mTextViewNeiRong;
    private TextView mTextViewBug;
    private TextView mTextViewTiJiao;




    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_feedback);
    }

    @Override
    protected void initViews() {
        setToolbarCenterMode("意见反馈",MODE_BACK);
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {

    }

}
