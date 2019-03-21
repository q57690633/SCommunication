package com.tencent.qcloud.uikit.business.chat.group.view;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tencent.imsdk.TIMValueCallBack;
import com.tencent.imsdk.ext.group.TIMGroupManagerExt;
import com.tencent.imsdk.ext.group.TIMGroupMemberResult;
import com.tencent.qcloud.uikit.R;
import com.tencent.qcloud.uikit.adapter.GroupDeleteMemberAdapter;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class GroupDeleteMemberActivity extends AppCompatActivity implements GroupDeleteMemberAdapter.DeleteListener {

    private final String TAG = "DeleteMemberActivity";
    private Context mContext;
    private RecyclerView mDeleteMemberRv;
    private String groupId = "";
    private JSONArray result = new JSONArray();

    private ArrayList<String> deleteUserId = new ArrayList<>();

    private ImageView backIv;
    private TextView deleteTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_delete_member);
        backIv = (ImageView) findViewById(R.id.back_iv);
        deleteTv = (TextView) findViewById(R.id.delete_tv);
        mContext = getBaseContext();
        initOnClickListener();
        mDeleteMemberRv = (RecyclerView) findViewById(R.id.delete_member_rv);
        JSONArray dataJSONArr = new JSONArray();
        try {
            dataJSONArr = new JSONArray(getIntent().getStringExtra("data"));
            groupId = getIntent().getStringExtra("groupId");
            analysisData(dataJSONArr);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayout.VERTICAL);
        mDeleteMemberRv.setLayoutManager(manager);
        mDeleteMemberRv.setAdapter(new GroupDeleteMemberAdapter(mContext, result, this));
    }

    private void analysisData(JSONArray jsonArray) throws JSONException {
        for(int i = 0; i < jsonArray.length(); i++) {
            JSONArray data = jsonArray.getJSONObject(i).getJSONArray("data");
            for(int j = 0; j < data.length(); j++) {
                result.put(data.getJSONObject(j));
            }
        }
    }

    private void initOnClickListener() {
        backIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        deleteTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteGroupMember();
            }
        });
    }

    private void deleteGroupMember() {
        TIMGroupManagerExt.DeleteMemberParam param = new TIMGroupManagerExt.DeleteMemberParam(groupId, deleteUserId);
        param.setReason("");

        TIMGroupManagerExt.getInstance().deleteGroupMember(param, new TIMValueCallBack<List<TIMGroupMemberResult>>() {
            @Override
            public void onError(int code, String desc) {
                Log.i(TAG, "deleteGroupMember onErr. code: " + code + " errmsg: " + desc);
            }

            @Override
            public void onSuccess(List<TIMGroupMemberResult> results) { //群组成员操作结果
                for(TIMGroupMemberResult r : results) {
                    Log.i(TAG, "result: " + r.getResult()  //操作结果:  0：删除失败；1：删除成功；2：不是群组成员
                            + " user: " + r.getUser());    //用户帐号
                }
            }
        });
    }

    @Override
    public void click(ArrayList<String> deleteUserId) {
        this.deleteUserId = deleteUserId;
    }

}
