package com.huxin.communication.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.huxin.communication.R;
import com.huxin.communication.adpter.ForeignSpotAdapter;
import com.huxin.communication.base.BaseActivity;
import com.huxin.communication.controls.Constanst;
import com.huxin.communication.http.ApiModule;
import com.huxin.communication.utils.PreferenceUtil;
import com.sky.kylog.KyLog;

public class ForeignSpotActivity extends BaseActivity {
    private RecyclerView mRecyclerView;

    private ForeignSpotAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_foreign_spot);

    }

    @Override
    protected void initViews() {
        setToolbarCenterMode("所选景点", MODE_BACK);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_city);
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        getForeignSpot();
    }
    public void getForeignSpot() {
        KyLog.d(PreferenceUtil.getString(Constanst.CITY_NATION_NAME));
        showProgressDialog();
        ApiModule.getInstance().getForeignSpot(PreferenceUtil.getString(Constanst.CITY_NATION_NAME)).subscribe(foreignSpotEntities -> {
            cancelProgressDialog();
            if (foreignSpotEntities != null && foreignSpotEntities.size() > 0) {
                LinearLayoutManager manager = new LinearLayoutManager(this);
                mAdapter = new ForeignSpotAdapter(foreignSpotEntities, this);
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
