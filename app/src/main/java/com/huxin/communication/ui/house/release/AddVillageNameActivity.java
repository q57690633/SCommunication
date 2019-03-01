package com.huxin.communication.ui.house.release;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.huxin.communication.R;
import com.huxin.communication.base.BaseActivity;
import com.huxin.communication.controls.Constanst;
import com.huxin.communication.entity.AreaTwoScreenEntity;
import com.huxin.communication.http.ApiModule;
import com.huxin.communication.ui.house.sell.AreaOneScreenActivity;
import com.huxin.communication.ui.house.sell.AreaTwoScreenActivity;
import com.huxin.communication.utils.PreferenceUtil;
import com.sky.kylog.KyLog;

import java.time.Instant;

public class AddVillageNameActivity extends BaseActivity implements View.OnClickListener {

    private EditText mTextViewVillageName;
    private TextView mTextViewCity;
    private TextView mTextViewAreaOne;
    private TextView mTextViewAreaTwo;

    private RelativeLayout mRelativeLayoutAreaOne;
    private RelativeLayout mRelativeLayoutAreaTwo;
    private TextView mConfim;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_add_village_name);

    }

    @Override
    protected void initViews() {
        setToolbarCenterMode("添加小区", MODE_BACK);
        mTextViewVillageName = (EditText) findViewById(R.id.villageName_ed_add);

        mTextViewCity = (TextView) findViewById(R.id.city);
        mTextViewAreaOne = (TextView) findViewById(R.id.areaone);
        mTextViewAreaTwo = (TextView) findViewById(R.id.areotwo);
        mConfim = (TextView) findViewById(R.id.confirm);

        mRelativeLayoutAreaOne = (RelativeLayout) findViewById(R.id.rl_areaone);
        mRelativeLayoutAreaTwo = (RelativeLayout) findViewById(R.id.rl_areotwo);

        mRelativeLayoutAreaOne.setOnClickListener(this);
        mRelativeLayoutAreaTwo.setOnClickListener(this);
        mConfim.setOnClickListener(this);


    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        if (!TextUtils.isEmpty(PreferenceUtil.getString(Constanst.CITY_NAME))){
            mTextViewCity.setText(PreferenceUtil.getString(Constanst.CITY_NAME));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!TextUtils.isEmpty(PreferenceUtil.getString(Constanst.SCREEN_AREAONE_NAME))){
            mTextViewAreaOne.setText(PreferenceUtil.getString(Constanst.SCREEN_AREAONE_NAME));
        }
        if (!TextUtils.isEmpty(PreferenceUtil.getString(Constanst.SCREEN_TWOAONE_NAME))){
            mTextViewAreaTwo.setText(PreferenceUtil.getString(Constanst.SCREEN_TWOAONE_NAME));
        }


    }

    private void selectByLike() {
        String villageName = mTextViewVillageName.getText().toString().trim();
        if (TextUtils.isEmpty(villageName)) {
            Toast.makeText(this, "请输入小区名", Toast.LENGTH_SHORT).show();
            return;
        }
        KyLog.d(villageName);
        KyLog.d(PreferenceUtil.getString(Constanst.SCREEN_AREAONE_NAME));
        KyLog.d(PreferenceUtil.getString(Constanst.SCREEN_TWOAONE_NAME));
        KyLog.d(PreferenceUtil.getString(Constanst.CITY_NAME));
        Toast.makeText(this, PreferenceUtil.getString(Constanst.SCREEN_AREAONE_NAME) + "\n" +
                PreferenceUtil.getString(Constanst.SCREEN_TWOAONE_NAME) + "\n" +
                PreferenceUtil.getString(Constanst.CITY_NAME), Toast.LENGTH_SHORT).show();
        showProgressDialog();
        ApiModule.getInstance().addPlot(PreferenceUtil.getString(Constanst.CITY_NAME), PreferenceUtil.getString(Constanst.SCREEN_AREAONE_NAME),
                PreferenceUtil.getString(Constanst.SCREEN_TWOAONE_NAME), villageName)
                .subscribe(selectByLikeEntities -> {
                    cancelProgressDialog();
                    Toast.makeText(this, selectByLikeEntities.getResultMsg(), Toast.LENGTH_SHORT).show();
                    finish();
                }, throwable -> {
                    KyLog.d(throwable.toString());
                    cancelProgressDialog();
                    Toast.makeText(this, throwable.getMessage().toString(), Toast.LENGTH_SHORT).show();
                });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.confirm:
                selectByLike();
                break;

            case R.id.rl_areaone:
                Intent instant = new Intent(this, AreaOneScreenActivity.class);
                instant.putExtra("type", 1);
                startActivity(instant);
                break;

            case R.id.rl_areotwo:
                Intent intent = new Intent(this, AreaTwoScreenActivity.class);
                intent.putExtra("type", 1);
                startActivity(intent);
                break;
        }
    }
}
