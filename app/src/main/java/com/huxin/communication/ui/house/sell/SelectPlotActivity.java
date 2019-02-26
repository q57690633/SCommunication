package com.huxin.communication.ui.house.sell;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.huxin.communication.R;
import com.huxin.communication.adpter.SelectPlotAdapter;
import com.huxin.communication.base.BaseActivity;
import com.huxin.communication.controls.Constanst;
import com.huxin.communication.entity.SelectPlotEntity;
import com.huxin.communication.http.ApiModule;
import com.huxin.communication.utils.PreferenceUtil;
import com.huxin.communication.view.SpaceItemDecoration;
import com.sky.kylog.KyLog;

import java.util.ArrayList;
import java.util.List;

public class SelectPlotActivity extends BaseActivity {
    private RecyclerView mRecyclerView;
    private SelectPlotAdapter mAdapter;
    private int areaSecondId;
    private int areaId;
    private List<SelectPlotEntity> list;

    private EditText mEditTextSearch;

    SpaceItemDecoration  spaceItemDecoration = new SpaceItemDecoration(0, 15);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_select_plot);

    }

    @Override
    protected void initViews() {
        setToolbarCenterMode("",MODE_BACK);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView_plot);
        mEditTextSearch = (EditText) findViewById(R.id.sousuo_quyu);

        areaSecondId = getIntent().getIntExtra(Constanst.SCREEN_TWOAONE_NAME,0);
        areaId = getIntent().getIntExtra(Constanst.SCREEN_TWOAONE_ID, 0);

        findViewById(R.id.select_Determine).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.canel_select).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreferenceUtil.putString(Constanst.SCREEN_AREAONE_NAME, "");
                PreferenceUtil.putString(Constanst.SCREEN_TWOAONE_NAME, "");
                PreferenceUtil.putString(Constanst.SELECT_PLOT_NAME, "");


            }
        });
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        areaTwoScreen();

    }

    private void areaTwoScreen() {
        KyLog.d("VillageName == " + areaSecondId + "areaId == " + areaId);
        showProgressDialog();
        ApiModule.getInstance().selectPlot(String.valueOf(areaSecondId), String.valueOf(areaId))
                .subscribe(selectPlotEntities -> {
                    KyLog.object(selectPlotEntities + "");
                    mEditTextSearch.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            String search = mEditTextSearch.getText().toString().trim();
                            List<SelectPlotEntity> lists = new ArrayList<>();
                            if (selectPlotEntities != null && selectPlotEntities.size() > 0) {
                                for (SelectPlotEntity entity : selectPlotEntities) {
                                    if (entity.getVillageName().contains(search)) {
                                        KyLog.d( search);
                                        KyLog.d( entity.getVillageName());

                                        lists.add(entity);
                                    }
                                }
                                KyLog.d(lists.size() + "");
                                if (lists.size() > 0 && !TextUtils.isEmpty(search)) {
//                        mAdapter.setList(lists);
                                    setData(lists);
                                }else {
                                    lists.clear();
//                                    mAdapter.setList(lists);
                                }
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                    cancelProgressDialog();

                }, throwable -> {
                    KyLog.d(throwable.toString());
                    cancelProgressDialog();
                    Toast.makeText(this, throwable.getMessage().toString(), Toast.LENGTH_SHORT).show();
                });
    }

    private void setData(List<SelectPlotEntity> list) {
        if (list != null && list.size() > 0) {
            LinearLayoutManager manager = new LinearLayoutManager(this);
            mAdapter = new SelectPlotAdapter(list, SelectPlotActivity.this);
            mRecyclerView.setAdapter(mAdapter);
            mRecyclerView.setLayoutManager(manager);
            mRecyclerView.addItemDecoration(spaceItemDecoration);
        }

    }


}
