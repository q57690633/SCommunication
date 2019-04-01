package com.huxin.communication.ui.my.setting;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.huxin.communication.R;
import com.huxin.communication.base.BaseActivity;
import com.huxin.communication.utils.PreferenceUtil;

public class NewMessageActivity extends BaseActivity {
    private CheckBox mCheckBoxZhenDong;
    private CheckBox mCheckBoxXiaoXi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_new_message);
        mCheckBoxXiaoXi = findViewById(R.id.set_souce_checkbox);
        mCheckBoxZhenDong = findViewById(R.id.set_top_checkbox);
        mCheckBoxXiaoXi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    PreferenceUtil.putInt("souce",1);
                }else {
                    PreferenceUtil.putInt("souce",0);

                }
            }
        });

        mCheckBoxZhenDong.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    PreferenceUtil.putInt("zhendong",1);
                }else {
                    PreferenceUtil.putInt("zhendong",0);

                }
            }
        });



    }

    @Override
    protected void initViews() {
        setToolbarCenterMode("新消息通知", MODE_BACK);


    }

    @Override
    protected void loadData(Bundle savedInstanceState) {

        if (PreferenceUtil.getInt("zhendong") == 1){
            mCheckBoxZhenDong.setChecked(true);
        }else {
            mCheckBoxZhenDong.setChecked(false);

        }


        if (PreferenceUtil.getInt("souce") == 1){
            mCheckBoxXiaoXi.setChecked(true);
        }else {
            mCheckBoxXiaoXi.setChecked(false);

        }


    }

}
