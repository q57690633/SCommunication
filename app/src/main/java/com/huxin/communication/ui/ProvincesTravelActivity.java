package com.huxin.communication.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.huxin.communication.R;
import com.huxin.communication.adpter.ProvincesTravelAdapter;
import com.huxin.communication.base.BaseActivity;
import com.huxin.communication.http.ApiModule;
import com.sky.kylog.KyLog;

public class ProvincesTravelActivity extends BaseActivity {
    private RecyclerView mRecyclerView;
    private ProvincesTravelAdapter mAdapter;
    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_provinces);
    }

    @Override
    protected void initViews() {
        setToolbarCenterMode("所选省市",MODE_BACK);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView_provinces);
        type = getIntent().getIntExtra("type",0);
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        getProvinces();
    }
    public void getProvinces(){
        showProgressDialog();
        ApiModule.getInstance().getProvinces().subscribe(provinceEntities -> {
            cancelProgressDialog();
            if (provinceEntities != null && provinceEntities.size() > 0) {
                LinearLayoutManager manager = new LinearLayoutManager(this);
                mAdapter = new ProvincesTravelAdapter(provinceEntities, this,type);
                mRecyclerView.setAdapter(mAdapter);
                mRecyclerView.setLayoutManager(manager);

            }
        },throwable -> {
            KyLog.d(throwable.toString());
            cancelProgressDialog();
            Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }
}
