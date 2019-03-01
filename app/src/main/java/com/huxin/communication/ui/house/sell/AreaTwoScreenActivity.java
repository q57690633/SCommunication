package com.huxin.communication.ui.house.sell;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.huxin.communication.R;
import com.huxin.communication.adpter.AreaTwoScreenAdapter;
import com.huxin.communication.base.BaseActivity;
import com.huxin.communication.controls.Constanst;
import com.huxin.communication.entity.AreaTwoScreenEntity;
import com.huxin.communication.http.ApiModule;
import com.huxin.communication.utils.PreferenceUtil;
import com.huxin.communication.view.SpaceItemDecoration;
import com.sky.kylog.KyLog;

import java.util.List;

public class AreaTwoScreenActivity extends BaseActivity {
    private RecyclerView mRecyclerView;
    private AreaTwoScreenAdapter mAdapter;
    private int areaId ;
    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_area_two_screen);

    }

    @Override
    protected void initViews() {
        setToolbarCenterMode("",MODE_BACK);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView_areaTwo);
        areaId = getIntent().getIntExtra(Constanst.SCREEN_AREAONE_NAME,0);
        type = getIntent().getIntExtra("type",0);
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        areaTwoScreen();
    }

    private void areaTwoScreen() {
        KyLog.d(PreferenceUtil.getInt(Constanst.SCREEN_AREAONE_CODE) + "");
        showProgressDialog();
        ApiModule.getInstance().areaTwoScreen(String.valueOf(PreferenceUtil.getInt(Constanst.SCREEN_AREAONE_CODE)))
                .subscribe(areaTwoScreenEntity -> {
                    KyLog.object(areaTwoScreenEntity + "");
                    cancelProgressDialog();
                    setData(areaTwoScreenEntity);
                }, throwable -> {
                    KyLog.d(throwable.toString());
                    cancelProgressDialog();
                    Toast.makeText(this, throwable.getMessage().toString(), Toast.LENGTH_SHORT).show();
                });
    }

    private void setData(List<AreaTwoScreenEntity> list) {
        if (list != null && list.size() > 0) {
            LinearLayoutManager manager = new LinearLayoutManager(this);
            mAdapter = new AreaTwoScreenAdapter(list, AreaTwoScreenActivity.this,areaId,type);
            mRecyclerView.setAdapter(mAdapter);
            mRecyclerView.setLayoutManager(manager);
            mRecyclerView.addItemDecoration(new SpaceItemDecoration(0, 15));
        }

    }


}
