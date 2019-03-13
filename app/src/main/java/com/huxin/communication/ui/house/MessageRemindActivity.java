package com.huxin.communication.ui.house;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.huxin.communication.R;
import com.huxin.communication.base.BaseActivity;
import com.huxin.communication.entity.SelectMessageEntity;
import com.huxin.communication.http.ApiModule;
import com.huxin.communication.utils.PreferenceUtil;
import com.sky.kylog.KyLog;

/**
 * 消息提醒
 * Created by yangzanxiong on 2018/12/13.
 */

public class MessageRemindActivity extends BaseActivity {
    private TextView mTextViewSellGroup;
    private TextView mTextViewTopGroup;
    private TextView mTextViewRentGroup;
    private TextView mTextViewQiuZuGroup;
    private TextView mTextViewQiuGouGroup;

    private TextView mTextViewSellCompany;
    private TextView mTextViewTopCompany;
    private TextView mTextViewRentCompany;
    private TextView mTextViewQiuZuCompany;
    private TextView mTextViewQiuGouCompany;

    private TextView mTextViewSellMessage;
    private TextView mTextViewTopMessage;
    private TextView mTextViewRentMessage;
    private TextView mTextViewQiuZuMessage;
    private TextView mTextViewQiuGouMessage;


    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_message_remind);
    }

    @Override
    protected void initViews() {
        setToolbarCenterMode("消息提醒", MODE_BACK);
        mTextViewQiuGouCompany = findViewById(R.id.message_qiugou_company);
        mTextViewQiuZuCompany = findViewById(R.id.message_qiuzu_company);
        mTextViewSellCompany = findViewById(R.id.message_sell_company);
        mTextViewRentCompany = findViewById(R.id.message_rent_company);
        mTextViewTopCompany = findViewById(R.id.message_top_company);


        mTextViewQiuGouGroup = findViewById(R.id.message_qiugou_group);
        mTextViewQiuZuGroup = findViewById(R.id.message_qiuzu_group);
        mTextViewSellGroup = findViewById(R.id.message_sell_group);
        mTextViewRentGroup = findViewById(R.id.message_rent_group);
        mTextViewTopGroup = findViewById(R.id.message_top_group);


        mTextViewQiuGouMessage = findViewById(R.id.message_qiugou);
        mTextViewQiuZuMessage = findViewById(R.id.message_qiuzu);
        mTextViewSellMessage = findViewById(R.id.message_sell);
        mTextViewRentMessage = findViewById(R.id.message_rent);
        mTextViewTopMessage = findViewById(R.id.message_top);

    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        selectDataBaseFrame();
    }

    /**
     * 消息页面
     */
    private void selectDataBaseFrame() {

        showProgressDialog();
        ApiModule.getInstance().selectMessage(String.valueOf(PreferenceUtil.getInt(UID)))
                .subscribe(selectMessageEntity -> {
                    if (selectMessageEntity != null) {
                        KyLog.object(selectMessageEntity.getCompany());
//                        setData(saleOfScreeningEntities);
//                        setDuoXuanData(saleOfScreeningEntities);
                        setData(selectMessageEntity.getGroup(), selectMessageEntity.getCompany(), selectMessageEntity.getMessage());
                    }

                    cancelProgressDialog();
                }, throwable -> {
                    KyLog.d(throwable.toString());
                    cancelProgressDialog();
                    Toast.makeText(this, throwable.getMessage().toString(), Toast.LENGTH_SHORT).show();
                });
    }

    private void setData(String group, String company, String message) {

        String[] strGroup;
        String[] strCompany;
        String[] strMessage;
        if (!TextUtils.isEmpty(message)) {
            strMessage = message.split(",");
            mTextViewTopMessage.setText(strMessage[strMessage.length - 5]);
            mTextViewSellMessage.setText(strMessage[strMessage.length - 4]);
            mTextViewRentMessage.setText(strMessage[strMessage.length - 3]);
            mTextViewQiuGouMessage.setText(strMessage[strMessage.length - 2]);
            mTextViewQiuZuMessage.setText(strMessage[strMessage.length - 1]);
        }
        if (!TextUtils.isEmpty(group)) {
            strGroup = group.split(",");
            mTextViewTopGroup.setText(strGroup[strGroup.length - 5]);
            mTextViewSellGroup.setText(strGroup[strGroup.length - 4]);
            mTextViewRentGroup.setText(strGroup[strGroup.length - 3]);
            mTextViewQiuGouGroup.setText(strGroup[strGroup.length - 2]);
            mTextViewQiuZuGroup.setText(strGroup[strGroup.length - 1]);

        }
        if (!TextUtils.isEmpty(company)) {
            strCompany = company.split(",");
            mTextViewTopCompany.setText(strCompany[strCompany.length - 5]);
            mTextViewSellCompany.setText(strCompany[strCompany.length - 4]);
            mTextViewRentCompany.setText(strCompany[strCompany.length - 3]);
            mTextViewQiuGouCompany.setText(strCompany[strCompany.length - 2]);
            mTextViewQiuZuCompany.setText(strCompany[strCompany.length - 1]);
        }


    }
}
