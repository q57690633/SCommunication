package com.huxin.communication.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.huxin.communication.R;
import com.huxin.communication.adpter.InlandSpotAdapter;
import com.huxin.communication.base.BaseActivity;
import com.huxin.communication.controls.Constanst;
import com.huxin.communication.http.ApiModule;
import com.huxin.communication.utils.PreferenceUtil;
import com.sky.kylog.KyLog;

public class InlandSpotActivity extends BaseActivity {
    private RecyclerView mRecyclerView;

    private InlandSpotAdapter mAdapter;

    private int type ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_inland_spot);
    }

    @Override
    protected void initViews() {
        setToolbarCenterMode("所选景点", MODE_BACK);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_city);
        type = getIntent().getIntExtra("type",0);
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        getInlandSpot();
    }
    public void getInlandSpot() {
        KyLog.d(PreferenceUtil.getString(Constanst.CITY_HOME_CODE));
        showProgressDialog();
        ApiModule.getInstance().getInlandSpot(PreferenceUtil.getString(Constanst.CITY_HOME_CODE)).subscribe(inlandSpotEntities -> {
            cancelProgressDialog();
            if (inlandSpotEntities != null && inlandSpotEntities.size() > 0) {
                if (type != 3) {
                    LinearLayoutManager manager = new LinearLayoutManager(this);
                    mAdapter = new InlandSpotAdapter(inlandSpotEntities, this);
                    mRecyclerView.setAdapter(mAdapter);
                    mRecyclerView.setLayoutManager(manager);
                }else {

                }

            }
        }, throwable -> {
            KyLog.d(throwable.toString());
            cancelProgressDialog();
            Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }
}
