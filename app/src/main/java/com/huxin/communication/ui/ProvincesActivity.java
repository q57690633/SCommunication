package com.huxin.communication.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.huxin.communication.R;
import com.huxin.communication.adpter.AddressProvinceAdapter;
import com.huxin.communication.base.BaseActivity;
import com.huxin.communication.controls.Constanst;
import com.huxin.communication.entity.AddressEntity;
import com.huxin.communication.entity.EvnBusEntity;
import com.sky.kylog.KyLog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

public class ProvincesActivity extends BaseActivity {

    private RecyclerView mRecyclerView;
    private ArrayList<AddressEntity> mData;
    private AddressProvinceAdapter mCityAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onEvent(EvnBusEntity entity) {
        mData = entity.getmData();
        KyLog.object(mData);
        if (mData != null && mData.size() > 0) {
            LinearLayoutManager manager = new LinearLayoutManager(this);
            mCityAdapter = new AddressProvinceAdapter(mData, this);
            mRecyclerView.setAdapter(mCityAdapter);
            mRecyclerView.setLayoutManager(manager);

        }
    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_provinces);


    }

    @Override
    protected void initViews() {
        setToolbarCenterMode("所在省市",MODE_BACK);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView_provinces);
        mData = getIntent().getParcelableArrayListExtra(Constanst.PROVINCE);

    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
//        getProvinces();

    }


    public void getProvinces(){

    }

}
