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
import com.huxin.communication.adpter.ForeignCityAdapter;
import com.huxin.communication.base.BaseActivity;
import com.huxin.communication.controls.Constanst;
import com.huxin.communication.http.ApiModule;
import com.sky.kylog.KyLog;

public class ForeignCityActivity extends BaseActivity {
    private RecyclerView mRecyclerView;

    private String provinceCode;
    private ForeignCityAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_foreign_city);

    }

    @Override
    protected void initViews() {
        setToolbarCenterMode("所选国外城市", MODE_BACK);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_city);
        provinceCode = getIntent().getStringExtra(Constanst.NATION_NAME);
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        getForeignCity();
    }

    public void getForeignCity() {
        KyLog.d(provinceCode);
        showProgressDialog();
        ApiModule.getInstance().getForeignCity(provinceCode).subscribe(foreignCityEntities  -> {
            cancelProgressDialog();
            if (foreignCityEntities != null && foreignCityEntities.size() > 0) {
                LinearLayoutManager manager = new LinearLayoutManager(this);
                mAdapter = new ForeignCityAdapter(foreignCityEntities, this);
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
