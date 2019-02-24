package com.huxin.communication.ui.house.details;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.huxin.communication.R;
import com.huxin.communication.adpter.QIuZuDetailsAdapter;
import com.huxin.communication.base.BaseActivity;
import com.huxin.communication.entity.InformationDetailEntity;
import com.huxin.communication.http.ApiModule;
import com.huxin.communication.view.SpaceItemDecoration;
import com.sky.kylog.KyLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangzanxiong on 2018/12/13.
 */

public class QiuZuDetailsActivity extends BaseActivity {

    private int pid;
    private TextView mTextViewTitle;
    private TextView mTextViewHouseType;
    private TextView mTextViewAcreage;
    private TextView mTextViewPermit;
    private TextView mTextViewFloorAge;
    private TextView mTextViewPublicTim;
    private TextView mTextViewPublicNumber;
    private TextView mTextViewfindNumber;
    private RecyclerView mRecyclerView;
    private QIuZuDetailsAdapter mAdapter;


    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_qiugou_details);
    }

    @Override
    protected void initViews() {
        mTextViewAcreage = (TextView) findViewById(R.id.acreage_details_qiuzu);
        mTextViewTitle = (TextView) findViewById(R.id.title_details);
        mTextViewHouseType = (TextView) findViewById(R.id.houseType_details_qiuzu);
        mTextViewPermit = (TextView) findViewById(R.id.permit_details_qiuzu);
        mTextViewFloorAge = (TextView) findViewById(R.id.floorAge_details_qiuzu);
        mTextViewPublicTim = (TextView) findViewById(R.id.publicTime_details_qiuzu);
        mTextViewPublicNumber = (TextView) findViewById(R.id.publicNumber_details_qiuzu);
        mTextViewfindNumber = (TextView) findViewById(R.id.findNumber_details_qiuzu);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_qiuzu_details);


        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        initData();
    }

    public void initData() {
        pid = getIntent().getIntExtra("pid", 0);
        KyLog.d(pid + "");
        showProgressDialog();
        ApiModule.getInstance().getQiuZuInformation(String.valueOf(pid), "4")
                .subscribe(informationDetailEntity -> {
                    cancelProgressDialog();
                    if (informationDetailEntity != null) {
                        setData(informationDetailEntity);
                    }

                }, throwable -> {
                    KyLog.d(throwable.toString());
                    cancelProgressDialog();
                    Toast.makeText(this, throwable.getMessage().toString(), Toast.LENGTH_SHORT).show();
                });
    }

    private void setData(InformationDetailEntity entity) {
        GridLayoutManager manager = new GridLayoutManager(QiuZuDetailsActivity.this, 5);
        mAdapter = new QIuZuDetailsAdapter(getTableNameList(entity), this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(0, 15));


        mTextViewTitle.setText(String.valueOf(entity.getTitle()));
        mTextViewHouseType.setText(String.valueOf(entity.getHouseType()));
        mTextViewPermit.setText(String.valueOf(entity.getPermit()));
        mTextViewAcreage.setText(String.valueOf(entity.getMinAcreage() + " ~ " + entity.getMaxAcreage()));
        mTextViewFloorAge.setText(String.valueOf(entity.getFloorAge()));
        mTextViewPublicTim.setText(String.valueOf(entity.getPermit()));
        mTextViewPublicNumber.setText(String.valueOf(entity.getPublicNumber()));
        mTextViewfindNumber.setText(String.valueOf(entity.getFindNumber()));

    }

    private List<String> getTableNameList(InformationDetailEntity entity) {
        List<String> tabNameList = new ArrayList<>();
        String[] strings = entity.getTabName().split(",");
        KyLog.d(entity.getTabName());
        for (String str : strings) {
            tabNameList.add(str);
        }
        return tabNameList;
    }
}
