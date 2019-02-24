package com.huxin.communication.ui.my.tuijian;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.huxin.communication.R;
import com.huxin.communication.adpter.PhoneRecyclerAdapter;
import com.huxin.communication.adpter.TuiJianPhoneAdapter;
import com.huxin.communication.base.BaseActivity;
import com.huxin.communication.entity.FamousEntity;
import com.huxin.communication.view.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class TuiJianActivity extends BaseActivity implements View.OnClickListener {


    private ListView mListView;
    private TuiJianPhoneAdapter mAdapter;
    private TextView mTextView;
    private RecyclerView mRecyclerView;
    private PhoneRecyclerAdapter mRecyclerAdapter;
    private List<String> list = new ArrayList<>();

    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_tui_jian);
    }

    @Override
    protected void initViews() {
        mListView = (ListView) findViewById(R.id.country_lvcountry);
        mTextView = (TextView) findViewById(R.id.tv_phone_person);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_phone);
        mImageView = (ImageView) findViewById(R.id.back);
        mTextView.setOnClickListener(this);
        mImageView.setOnClickListener(this);

    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        mAdapter = new TuiJianPhoneAdapter(this, setData());
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(TuiJianActivity.this, FriendDetailedActivity.class);
//                startActivity(intent);
            }
        });
        setRecyclerData();
    }

    private void setRecyclerData() {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerAdapter = new PhoneRecyclerAdapter(list, this);
        mRecyclerView.setAdapter(mRecyclerAdapter);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(15, 0));
    }

    private List<FamousEntity> setData() {
        List<FamousEntity> list = new ArrayList<>();
        FamousEntity famousEntity = new FamousEntity();
        famousEntity.setFirstLetter("星标朋友");
        famousEntity.setName("爱屋房产·店面经理·李宁");
        FamousEntity famousEntity1 = new FamousEntity();
        famousEntity1.setFirstLetter("星标朋友");
        famousEntity1.setName("爱屋房产·店面经理·李宁");
        FamousEntity famousEntity2 = new FamousEntity();
        famousEntity2.setFirstLetter("a");
        famousEntity2.setName("爱屋房产·店面经理·李宁");
        FamousEntity famousEntity3 = new FamousEntity();
        famousEntity3.setFirstLetter("a");
        famousEntity3.setName("爱屋房产·店面经理·李宁");
        FamousEntity famousEntity4 = new FamousEntity();
        famousEntity4.setFirstLetter("d");
        famousEntity4.setName("爱屋房产·店面经理·李宁");
        FamousEntity famousEntity5 = new FamousEntity();
        famousEntity5.setFirstLetter("c");
        famousEntity5.setName("爱屋房产·店面经理·李宁");
        FamousEntity famousEntity6 = new FamousEntity();
        famousEntity6.setFirstLetter("d");
        famousEntity6.setName("爱屋房产·店面经理·李宁");
        FamousEntity famousEntity7 = new FamousEntity();
        famousEntity7.setFirstLetter("a");
        famousEntity7.setName("爱屋房产·店面经理·李宁");
        FamousEntity famousEntity8 = new FamousEntity();
        famousEntity8.setFirstLetter("f");
        famousEntity8.setName("爱屋房产·店面经理·李宁");

        FamousEntity famousEntity9 = new FamousEntity();
        famousEntity9.setFirstLetter("h");
        famousEntity9.setName("爱屋房产·店面经理·李宁");
        FamousEntity famousEntity10 = new FamousEntity();
        famousEntity10.setFirstLetter("s");
        famousEntity10.setName("爱屋房产·店面经理·李宁");
        FamousEntity famousEntity11 = new FamousEntity();
        famousEntity11.setFirstLetter("q");
        famousEntity11.setName("爱屋房产·店面经理·李宁");
        FamousEntity famousEntity12 = new FamousEntity();
        famousEntity12.setFirstLetter("q");
        famousEntity12.setName("爱屋房产·店面经理·李宁");
        FamousEntity famousEntity13 = new FamousEntity();
        famousEntity13.setFirstLetter("f");
        famousEntity13.setName("爱屋房产·店面经理·李宁");
        list.add(famousEntity);
        list.add(famousEntity1);
        list.add(famousEntity2);
        list.add(famousEntity3);
        list.add(famousEntity4);
        list.add(famousEntity5);
        list.add(famousEntity6);
        list.add(famousEntity7);
        list.add(famousEntity8);
        list.add(famousEntity9);
        list.add(famousEntity10);
        list.add(famousEntity11);
        list.add(famousEntity12);
        list.add(famousEntity13);
        return list;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_phone_person:
//                Intent intent = new Intent(TuiJianActivity.this, AddFriendActivity.class);
//                startActivity(intent);
                break;
            case R.id.back:
                finish();
                break;
        }
    }
}
