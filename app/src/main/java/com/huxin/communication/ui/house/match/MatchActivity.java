package com.huxin.communication.ui.house.match;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.huxin.communication.R;
import com.huxin.communication.adpter.MatchAdpter;
import com.huxin.communication.base.BaseActivity;
import com.huxin.communication.entity.MatchingProductEntity;
import com.huxin.communication.http.ApiModule;
import com.huxin.communication.utils.PreferenceUtil;
import com.huxin.communication.view.SpaceItemDecoration;
import com.sky.kylog.KyLog;

import java.util.List;

public class MatchActivity extends BaseActivity implements View.OnClickListener {
    private RecyclerView mRecyclerView;
    private MatchAdpter mMatchAdpter;
    private TextView mTextViewQiuGou;
    private TextView mTextViewQiuZu;
    private int productType = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_match);
    }

    @Override
    protected void initViews() {
        setToolbarCenterMode("房客匹配", MODE_BACK);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView_match);
        mTextViewQiuGou = (TextView) findViewById(R.id.qiugou_match);
        mTextViewQiuZu = (TextView) findViewById(R.id.qiuzu_match);
        mTextViewQiuGou.setOnClickListener(this);
        mTextViewQiuZu.setOnClickListener(this);

    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        initData(productType);
    }

    private void initData(int productType) {
        ApiModule.getInstance().matchingProduct(String.valueOf(PreferenceUtil.getInt(UID)), String.valueOf(productType))
                .subscribe(matchingProductEntities -> {
                    if (matchingProductEntities != null) {
                        setData(matchingProductEntities);
                    }
                }, throwable -> {
                    KyLog.d(throwable.toString());
                    cancelProgressDialog();
                    Toast.makeText(this, throwable.getMessage().toString(), Toast.LENGTH_SHORT).show();
                });
    }

    public void setData(MatchingProductEntity entity) {
        if (entity.getList()!= null && entity.getList().size() > 0) {
            mRecyclerView.setVisibility(View.VISIBLE);
            LinearLayoutManager manager = new LinearLayoutManager(this);
            mMatchAdpter = new MatchAdpter(entity.getList(), this);
            mRecyclerView.setAdapter(mMatchAdpter);
            mRecyclerView.setLayoutManager(manager);
            mRecyclerView.addItemDecoration(new SpaceItemDecoration(0, 10));
        }else {
            mRecyclerView.setVisibility(View.GONE);
            Toast.makeText(this, "数据为空", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.qiugou_match:
                mTextViewQiuZu.setBackgroundResource(R.drawable.list_divider);
                mTextViewQiuGou.setBackgroundColor(getResources().getColor(R.color.blue));
                mTextViewQiuGou.setTextColor(getResources().getColor(R.color.white));
                mTextViewQiuZu.setTextColor(getResources().getColor(R.color.register_font));
                productType = 1;
                initData(productType);
                break;
            case R.id.qiuzu_match:
                productType = 2;
                initData(productType);
                mTextViewQiuGou.setBackgroundResource(R.drawable.list_divider);
                mTextViewQiuZu.setBackgroundColor(getResources().getColor(R.color.blue));
                mTextViewQiuGou.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewQiuZu.setTextColor(getResources().getColor(R.color.white));
                break;
        }
    }
}
