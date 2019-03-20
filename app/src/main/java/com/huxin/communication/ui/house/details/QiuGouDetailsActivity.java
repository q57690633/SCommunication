package com.huxin.communication.ui.house.details;

import android.content.Intent;
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
import com.huxin.communication.ui.TIMChatActivity;
import com.huxin.communication.utils.DateUtil;
import com.huxin.communication.utils.PreferenceUtil;
import com.huxin.communication.view.SpaceItemDecoration;
import com.sky.kylog.KyLog;
import com.tencent.qcloud.uikit.TUIKit;
import com.tencent.qcloud.uikit.common.IUIKitCallBack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangzanxiong on 2018/12/13.
 */

public class QiuGouDetailsActivity extends BaseActivity {
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
    private InformationDetailEntity entity;
    private TextView mTextViewCampany;
    private TextView mTextViewUserName;


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

        mTextViewUserName = (TextView) findViewById(R.id.username);
        mTextViewCampany = (TextView) findViewById(R.id.campany);

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.zaixianwen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String userSig = PreferenceUtil.getString("usersig");
                if (entity != null) {
                    //KyLog.i("uid = " + mList.get(0).getUid());
                    //KyLog.i("usersig = " + userSig);
                    String userId = PreferenceUtil.getInt(UID) + "";
                    String userSig = PreferenceUtil.getString("usersig");
                    onRecvUserSig(userId, userSig, String.valueOf(entity.getUid()));
                }
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
        ApiModule.getInstance().getQiuZuInformation(String.valueOf(pid), "3")
                .subscribe(informationDetailEntity -> {
                    cancelProgressDialog();
                    entity = informationDetailEntity;
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
        if (getTableNameList(entity).size() > 0) {
            GridLayoutManager manager = new GridLayoutManager(QiuGouDetailsActivity.this, 5);
            mAdapter = new QIuZuDetailsAdapter(getTableNameList(entity), this);
            mRecyclerView.setAdapter(mAdapter);
            mRecyclerView.setLayoutManager(manager);
        }
//        mRecyclerView.addItemDecoration(new SpaceItemDecoration(0, 15));


        mTextViewTitle.setText(String.valueOf(entity.getTitle()));
        mTextViewHouseType.setText(String.valueOf(entity.getHouseType()));
        mTextViewPermit.setText(String.valueOf(entity.getPermit()));
        mTextViewFloorAge.setText(String.valueOf(entity.getFloorAge()));
        mTextViewPublicTim.setText(String.valueOf(entity.getPermit()));
        mTextViewAcreage.setText(String.valueOf(entity.getMinAcreage() + " ~ " + entity.getMaxAcreage()) + "㎡");
        mTextViewPublicNumber.setText(DateUtil.timeslashData(String.valueOf(entity.getShowTime())));
        mTextViewfindNumber.setText(String.valueOf(entity.getFindNumber())+ "次");

        mTextViewCampany.setText(entity.getUserModel().getCompanyName());
        mTextViewUserName.setText(entity.getUserModel().getUsername());

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

    private void onRecvUserSig(String userId, String userSig, String targetId) {
        TUIKit.login(userId, userSig, new IUIKitCallBack() {
            @Override
            public void onSuccess(Object data) {
                KyLog.i("imlogin onSuccess", data);
                Intent intent = new Intent(QiuGouDetailsActivity.this, TIMChatActivity.class);
                intent.putExtra("TARGET_ID", targetId);
                startActivity(intent);
            }
            @Override
            public void onError(String module, int errCode, String errMsg) {
                Toast.makeText(QiuGouDetailsActivity.this, "用户Id == " + userId + " \n"+"imlogin fail" + errMsg
                        + " \n"+"imlogin fail" + userSig, Toast.LENGTH_SHORT).show();
                KyLog.e("imlogin fail", errMsg);
            }
        });
    }
}
