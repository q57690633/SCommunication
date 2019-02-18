package com.huxin.communication.ui.house.sell;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.huxin.communication.R;
import com.huxin.communication.adpter.ChuZuDetailsAdpter;
import com.huxin.communication.adpter.SimilarDetailsAdapter;
import com.huxin.communication.base.BaseActivity;
import com.huxin.communication.view.SpaceItemDecoration;

import java.util.List;

/**
 * Created by yangzanxiong on 2018/12/13.
 */

public class SimilarDetailsActivity extends BaseActivity {
    private RecyclerView mRecyclerView;
    private SimilarDetailsAdapter mAdpter;
    private List<String> list;
    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_similar);
    }

    @Override
    protected void initViews() {
        setToolbarCenterMode("相似房源",MODE_BACK);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_similar);
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mAdpter = new SimilarDetailsAdapter(list, this);
        mRecyclerView.setAdapter(mAdpter);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(0, 15));
    }
}
