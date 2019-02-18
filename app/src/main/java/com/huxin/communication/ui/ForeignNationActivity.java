package com.huxin.communication.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.huxin.communication.ForeginNationAdapter;
import com.huxin.communication.R;
import com.huxin.communication.adpter.ProvincesTravelAdapter;
import com.huxin.communication.base.BaseActivity;
import com.huxin.communication.http.ApiModule;
import com.sky.kylog.KyLog;

public class ForeignNationActivity extends BaseActivity {
    private RecyclerView mRecyclerView;
    private ForeginNationAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_foreign_nation);

    }

    @Override
    protected void initViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView_provinces);
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        getProvinces();
    }
    public void getProvinces(){
        showProgressDialog();
        ApiModule.getInstance().getForeignNation().subscribe(foreignNationEntities -> {
            cancelProgressDialog();
            if (foreignNationEntities != null && foreignNationEntities.size() > 0) {
                LinearLayoutManager manager = new LinearLayoutManager(this);
                mAdapter = new ForeginNationAdapter(foreignNationEntities, this);
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
