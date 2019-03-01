package com.huxin.communication.ui.house.sell;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.huxin.communication.R;
import com.huxin.communication.adpter.AreaOneScreenAdapter;
import com.huxin.communication.base.BaseActivity;
import com.huxin.communication.controls.Constanst;
import com.huxin.communication.entity.AreaOneScreenEntity;
import com.huxin.communication.http.ApiModule;
import com.huxin.communication.utils.PreferenceUtil;
import com.huxin.communication.view.SpaceItemDecoration;
import com.sky.kylog.KyLog;

import java.util.List;

public class AreaOneScreenActivity extends BaseActivity {
    private RecyclerView mRecyclerView;
    private AreaOneScreenAdapter mAdapter;
    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_area_one_screen);

    }

    @Override
    protected void initViews() {
        setToolbarCenterMode("",MODE_BACK);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView_areaOne);
        type = getIntent().getIntExtra("type",0);

    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        areaOneScreen();
    }

    private void areaOneScreen() {
        KyLog.d(PreferenceUtil.getString(Constanst.CITY_NAME));
        showProgressDialog();
        ApiModule.getInstance().areaOneScreen(PreferenceUtil.getString(Constanst.CITY_NAME))
                .subscribe(areaOneScreenEntity -> {
                    KyLog.object(areaOneScreenEntity + "");
                    cancelProgressDialog();
                    setData(areaOneScreenEntity);
                }, throwable -> {
                    KyLog.d(throwable.toString());
                    cancelProgressDialog();
                    Toast.makeText(this, throwable.getMessage().toString(), Toast.LENGTH_SHORT).show();
                });
    }

    private void setData(List<AreaOneScreenEntity> list) {
        if (list != null && list.size() > 0) {
            LinearLayoutManager manager = new LinearLayoutManager(this);
            mAdapter = new AreaOneScreenAdapter(list, AreaOneScreenActivity.this,type);
            mRecyclerView.setAdapter(mAdapter);
            mRecyclerView.setLayoutManager(manager);
            mRecyclerView.addItemDecoration(new SpaceItemDecoration(0, 15));
        }

    }


}
