package com.tencent.qcloud.uikit.operation.group;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import com.tencent.qcloud.uikit.R;
import com.tencent.qcloud.uikit.adapter.GroupDeleteMemberAdapter;
import com.tencent.qcloud.uikit.adapter.GroupInfoMemberAdapter;
import com.tencent.qcloud.uikit.business.chat.view.itemdecoration.GridSpacingItemDecoration;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class GroupMoreMemberActivity extends AppCompatActivity implements GroupDeleteMemberAdapter.DeleteListener {

    private final String TAG = "MoreMemberActivity";
    private JSONArray dataJSONArr = new JSONArray();
    private RecyclerView mRecyclerView;
    private Context mContext;
    private ArrayList<String> headUrlList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_more_member2);
        initData();
        initView();
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 5);
        gridLayoutManager.setOrientation(GridLayout.VERTICAL);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(20, 20));
        mRecyclerView.setAdapter(new GroupInfoMemberAdapter(mContext, headUrlList));
    }

    private void initData() {
        mContext = getBaseContext();
        String data = getIntent().getStringExtra("data");
        String[] dataArr = data.split(",");
        for(int i = 0; i < dataArr.length; i++) {
            headUrlList.add(dataArr[i]);
        }
    }


    @Override
    public void click(ArrayList<String> deleteUserId) {

    }
}