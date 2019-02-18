package com.huxin.communication.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.huxin.communication.R;
import com.huxin.communication.adpter.CityTravelAdapter;
import com.huxin.communication.adpter.ProvincesTravelAdapter;
import com.huxin.communication.base.BaseActivity;
import com.huxin.communication.controls.Constanst;
import com.huxin.communication.http.ApiModule;
import com.sky.kylog.KyLog;

public class CityTravelActivity extends BaseActivity {

    private RecyclerView mRecyclerView;

    private String provinceCode;
    private CityTravelAdapter mAdapter;
    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_city);
    }

    @Override
    protected void initViews() {
        setToolbarCenterMode("所选地区", MODE_BACK);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_city);
        provinceCode = getIntent().getStringExtra(Constanst.PROVINCE_CODE);
        type = getIntent().getIntExtra("type",0);


    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        getInlandCity();
    }

    public void getInlandCity() {
        KyLog.d(provinceCode);
        showProgressDialog();
        ApiModule.getInstance().getInlandCity(provinceCode).subscribe(inlandCityEntities -> {
            cancelProgressDialog();
            if (inlandCityEntities != null && inlandCityEntities.size() > 0) {
                LinearLayoutManager manager = new LinearLayoutManager(this);
                mAdapter = new CityTravelAdapter(inlandCityEntities, this,type);
                mRecyclerView.setAdapter(mAdapter);
                mRecyclerView.setLayoutManager(manager);

            }
        }, throwable -> {
            KyLog.d(throwable.toString());
            cancelProgressDialog();
            Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }
}
