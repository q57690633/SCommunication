package com.huxin.communication.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.huxin.communication.R;
import com.huxin.communication.adpter.CityAdapter;
import com.huxin.communication.adpter.CountyAdapter;
import com.huxin.communication.base.BaseActivity;
import com.huxin.communication.controls.Constanst;
import com.huxin.communication.entity.AddressEntity;
import com.huxin.communication.entity.EvnBusCityEntity;
import com.huxin.communication.entity.EvnBusCountyEntity;
import com.huxin.communication.http.ApiModule;
import com.sky.kylog.KyLog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class CountyActivity extends BaseActivity {

    private RecyclerView mRecyclerView;
    private CountyAdapter mAdapter;
    private List<AddressEntity.ListBeanX.ListBean> list;

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
    public void onEvent(EvnBusCountyEntity entity) {
        list = entity.getmData();
        KyLog.object(list);
        if (list != null && list.size() > 0) {
            LinearLayoutManager manager = new LinearLayoutManager(this);
            mAdapter = new CountyAdapter(list, this);
            mRecyclerView.setAdapter(mAdapter);
            mRecyclerView.setLayoutManager(manager);

        }
    }


    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_county);
    }

    @Override
    protected void initViews() {
        setToolbarCenterMode("所在区县",MODE_BACK);
        mRecyclerView = (RecyclerView) findViewById(R.id.county_recycler);
        list = getIntent().getParcelableArrayListExtra(Constanst.DISTRICT);
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
    }

}
