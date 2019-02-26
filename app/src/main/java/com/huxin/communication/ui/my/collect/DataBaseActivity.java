package com.huxin.communication.ui.my.collect;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.huxin.communication.R;
import com.huxin.communication.adpter.DataBaseAdapter;
import com.huxin.communication.adpter.DataBaseDuoXuanAdapter;
import com.huxin.communication.base.BaseActivity;
import com.huxin.communication.entity.PersonProductEntity;
import com.huxin.communication.http.ApiModule;
import com.huxin.communication.view.SpaceItemDecoration;
import com.sky.kylog.KyLog;

import java.util.ArrayList;
import java.util.List;

public class DataBaseActivity extends BaseActivity implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private RecyclerView mRecyclerViewDuoXuan;
    private RelativeLayout mRelativeLayoutSearch;
    private RelativeLayout mRelativeLayoutDuoxuanBtn;


    private TextView mTextViewGuanLi;
    private TextView mTextViewQuXiao;

    private DataBaseAdapter mAdpter;
    private DataBaseDuoXuanAdapter mAdpterDuoXuan;

    private List<String> list = new ArrayList<>();
    private TextView mTextViewSell;
    private TextView mTextViewQiuZhu;
    private TextView mTextViewQiuGou;
    private TextView mTextViewChuZhu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_data_base);

    }

    @Override
    protected void initViews() {
        setToolbarCenterMode("", MODE_BACK);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyler_sell);
        mRecyclerViewDuoXuan = (RecyclerView) findViewById(R.id.recyler_sell_duoxuan);
        mTextViewGuanLi = (TextView) findViewById(R.id.toolbar_right);
        mTextViewQuXiao = (TextView) findViewById(R.id.toolbar_quxiao);
        mRelativeLayoutSearch = (RelativeLayout) findViewById(R.id.search_rl);
        mRelativeLayoutDuoxuanBtn = (RelativeLayout) findViewById(R.id.btn_duoxuan);

        mTextViewChuZhu = (TextView) findViewById(R.id.chuzu);
        mTextViewQiuGou = (TextView) findViewById(R.id.qiugou);
        mTextViewQiuZhu = (TextView) findViewById(R.id.qiuzhu);
        mTextViewSell = (TextView) findViewById(R.id.sell);


        mTextViewGuanLi.setOnClickListener(this);
        mTextViewQuXiao.setOnClickListener(this);

        mTextViewChuZhu.setOnClickListener(this);
        mTextViewQiuGou.setOnClickListener(this);
        mTextViewQiuZhu.setOnClickListener(this);
        mTextViewSell.setOnClickListener(this);
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        getPersonProduct(1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_right:
                mTextViewQuXiao.setVisibility(View.VISIBLE);
                mTextViewGuanLi.setVisibility(View.GONE);
                mRecyclerViewDuoXuan.setVisibility(View.VISIBLE);
                mRecyclerView.setVisibility(View.GONE);
                mRelativeLayoutDuoxuanBtn.setVisibility(View.VISIBLE);
                break;
            case R.id.toolbar_quxiao:
                mTextViewQuXiao.setVisibility(View.GONE);
                mTextViewGuanLi.setVisibility(View.VISIBLE);
                mRecyclerViewDuoXuan.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);
                mRelativeLayoutDuoxuanBtn.setVisibility(View.GONE);
                break;
            case R.id.chuzu:
                mTextViewChuZhu.setBackgroundResource(R.drawable.biaoqian_radius_top_blue);
                mTextViewChuZhu.setTextColor(getResources().getColor(R.color.white));
                mTextViewQiuGou.setBackgroundResource(R.drawable.biaoqian_radius_top);
                mTextViewQiuGou.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewQiuZhu.setBackgroundResource(R.drawable.biaoqian_radius_top);
                mTextViewQiuZhu.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewSell.setBackgroundResource(R.drawable.biaoqian_radius_top);
                mTextViewSell.setTextColor(getResources().getColor(R.color.register_font));
                getPersonProduct(2);
                break;
            case R.id.qiugou:
                mTextViewChuZhu.setBackgroundResource(R.drawable.biaoqian_radius_top);
                mTextViewChuZhu.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewQiuGou.setBackgroundResource(R.drawable.biaoqian_radius_top_blue);
                mTextViewQiuGou.setTextColor(getResources().getColor(R.color.white));
                mTextViewQiuZhu.setBackgroundResource(R.drawable.biaoqian_radius_top);
                mTextViewQiuZhu.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewSell.setBackgroundResource(R.drawable.biaoqian_radius_top);
                mTextViewSell.setTextColor(getResources().getColor(R.color.register_font));
                getPersonProduct(3);
                break;
            case R.id.qiuzhu:
                mTextViewChuZhu.setBackgroundResource(R.drawable.biaoqian_radius_top);
                mTextViewChuZhu.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewQiuGou.setBackgroundResource(R.drawable.biaoqian_radius_top);
                mTextViewQiuGou.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewQiuZhu.setBackgroundResource(R.drawable.biaoqian_radius_top_blue);
                mTextViewQiuZhu.setTextColor(getResources().getColor(R.color.white));
                mTextViewSell.setBackgroundResource(R.drawable.biaoqian_radius_top);
                mTextViewSell.setTextColor(getResources().getColor(R.color.register_font));
                getPersonProduct(4);
                break;
            case R.id.sell:
                mTextViewChuZhu.setBackgroundResource(R.drawable.biaoqian_radius_top);
                mTextViewChuZhu.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewQiuGou.setBackgroundResource(R.drawable.biaoqian_radius_top);
                mTextViewQiuGou.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewQiuZhu.setBackgroundResource(R.drawable.biaoqian_radius_top);
                mTextViewQiuZhu.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewSell.setBackgroundResource(R.drawable.biaoqian_radius_top_blue);
                mTextViewSell.setTextColor(getResources().getColor(R.color.white));
                getPersonProduct(1);
                break;
        }
    }

    private void setDuoXuanData(PersonProductEntity entity) {
        if (entity.getList() != null && entity.getList().size() > 0) {
            LinearLayoutManager manager = new LinearLayoutManager(this);
            mAdpterDuoXuan = new DataBaseDuoXuanAdapter(entity.getList(), this);
            mRecyclerViewDuoXuan.setAdapter(mAdpterDuoXuan);
            mRecyclerViewDuoXuan.setLayoutManager(manager);
            mRecyclerViewDuoXuan.addItemDecoration(new SpaceItemDecoration(0, 15));
            mTextViewGuanLi.setVisibility(View.VISIBLE);
            mRelativeLayoutSearch.setVisibility(View.VISIBLE);
            mRelativeLayoutSearch.setVisibility(View.VISIBLE);
        }

    }

    private void setData(PersonProductEntity entity) {
        if (entity.getList() != null && entity.getList().size() > 0) {
            LinearLayoutManager manager = new LinearLayoutManager(this);
            mAdpter = new DataBaseAdapter(entity.getList(), this);
            mRecyclerView.setAdapter(mAdpter);
            mRecyclerView.setLayoutManager(manager);
            mRecyclerView.addItemDecoration(new SpaceItemDecoration(0, 15));
        }
    }

    private void getPersonProduct(int newOrOld) {
//        pid = getIntent().getStringExtra("pid");
        showProgressDialog();
        ApiModule.getInstance().getPersonProduct("", "", "", "", "",
                "", "", String.valueOf(newOrOld), "",
                "", "", "", "", "", "", "0", "1")
                .subscribe(personProductEntity -> {
                    KyLog.object(personProductEntity + "");
                    if (personProductEntity != null) {
                        setData(personProductEntity);
                        setDuoXuanData(personProductEntity);
                    }

                    cancelProgressDialog();
                }, throwable -> {
                    KyLog.d(throwable.toString());
                    cancelProgressDialog();
                    Toast.makeText(this, throwable.getMessage().toString(), Toast.LENGTH_SHORT).show();
                });
    }
}
