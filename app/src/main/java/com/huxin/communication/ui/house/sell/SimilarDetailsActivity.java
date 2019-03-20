package com.huxin.communication.ui.house.sell;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.huxin.communication.R;
import com.huxin.communication.adpter.SimilarDetailsAdapter;
import com.huxin.communication.base.BaseActivity;
import com.huxin.communication.http.ApiModule;
import com.huxin.communication.view.SpaceItemDecoration;
import com.sky.kylog.KyLog;

import java.security.Key;
import java.util.List;

/**
 * Created by yangzanxiong on 2018/12/13.
 */

public class SimilarDetailsActivity extends BaseActivity {
    private RecyclerView mRecyclerView;
    private SimilarDetailsAdapter mAdpter;
    private List<String> list;
    private String productType;
    private int id;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_similar);
    }

    @Override
    protected void initViews() {
        setToolbarCenterMode("相似房源",MODE_BACK);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_similar);

        productType = getIntent().getStringExtra("productType");
        id = getIntent().getIntExtra("pid",0);

    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
//
        KyLog.d(id + "ID");
        KyLog.d(productType + "ID");
        getSimilerData("1","1",String.valueOf(id));
    }

    private void getSimilerData(String curPage,  String productType,String id){
        showProgressDialog();
        ApiModule.getInstance().getMoreSimple(curPage,productType,id)
                .subscribe(similerEntity -> {
            cancelProgressDialog();
            if (similerEntity.getList()!= null && similerEntity.getList().size() > 0) {
                LinearLayoutManager manager = new LinearLayoutManager(this);
                mAdpter = new SimilarDetailsAdapter(similerEntity.getList(), this);
                mRecyclerView.setAdapter(mAdpter);
                mRecyclerView.setLayoutManager(manager);
            }
        },throwable -> {
            KyLog.d(throwable.getMessage());
            cancelProgressDialog();
            Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }

}
