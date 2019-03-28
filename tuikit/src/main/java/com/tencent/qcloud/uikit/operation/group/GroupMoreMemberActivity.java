package com.tencent.qcloud.uikit.operation.group;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import com.sky.kylog.KyLog;
import com.tencent.qcloud.uikit.R;
import com.tencent.qcloud.uikit.adapter.GroupDeleteMemberAdapter;
import com.tencent.qcloud.uikit.adapter.GroupInfoMemberAdapter;
import com.tencent.qcloud.uikit.business.chat.view.itemdecoration.GridSpacingItemDecoration;
import com.tencent.qcloud.uikit.entity.MemberHeadUrlEntity;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class GroupMoreMemberActivity extends AppCompatActivity implements GroupDeleteMemberAdapter.DeleteListener {

    private final String TAG = "MoreMemberActivity";
    private JSONArray dataJSONArr = new JSONArray();
    private RecyclerView mRecyclerView;
    private Context mContext;
    private ArrayList<String> headUrlList = new ArrayList<>();
    private ArrayList<MemberHeadUrlEntity> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_more_member2);
        list = getIntent().getParcelableArrayListExtra("data");

        initData();
    }

    private void initView(ArrayList<MemberHeadUrlEntity> list) {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(gridLayoutManager);
//        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(20, 20));
        mRecyclerView.setAdapter(new GroupCompanyFenLeiAdapter(mContext, list));

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initData() {
        mContext = getBaseContext();
//        String data = getIntent().getStringExtra("data");
//        String[] dataArr = data.split(",");
//        for(int i = 0; i < dataArr.length; i++) {
//            headUrlList.add(dataArr[i]);
//        }
        KyLog.d(list.size() + "==data");
        KyLog.object(list);
        if (list != null && list.size() > 0) {
            initView(list);
        }

    }


    @Override
    public void click(ArrayList<String> deleteUserId) {

    }
}