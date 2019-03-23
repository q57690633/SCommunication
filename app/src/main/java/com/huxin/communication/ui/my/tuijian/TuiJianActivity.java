package com.huxin.communication.ui.my.tuijian;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.huxin.communication.R;
import com.huxin.communication.adpter.CompanyAdapter;
import com.huxin.communication.adpter.FamousAdapter;
import com.huxin.communication.adpter.GounpAdapter;
import com.huxin.communication.adpter.PhoneRecyclerAdapter;
import com.huxin.communication.adpter.StickAdapter;
import com.huxin.communication.adpter.TuiJIanGounpAdapter;
import com.huxin.communication.adpter.TuiJIanStickAdapter;
import com.huxin.communication.adpter.TuiJianPhoneAdapter;
import com.huxin.communication.adpter.TuijianCompanyAdapter;
import com.huxin.communication.base.BaseActivity;
import com.huxin.communication.entity.FamousEntity;
import com.huxin.communication.entity.UserInfoEntity;
import com.huxin.communication.http.ApiModule;
import com.huxin.communication.listener.TuiJianGounpListener;
import com.huxin.communication.listener.TuiJianPhoneListener;
import com.huxin.communication.listener.TuiJianStarPhoneListener;
import com.huxin.communication.listener.TuijianCompanyListener;
import com.huxin.communication.ui.TIMChatActivity;
import com.huxin.communication.ui.house.phone.AddFriendActivity;
import com.huxin.communication.utils.PreferenceUtil;
import com.huxin.communication.view.SpaceItemDecoration;
import com.sky.kylog.KyLog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TuiJianActivity extends BaseActivity implements View.OnClickListener, TuiJianPhoneListener, TuiJianStarPhoneListener, TuijianCompanyListener, TuiJianGounpListener {


    private ListView mListView;
    private TuiJianPhoneAdapter mAdapter;
    private TextView mTextView;
    private RecyclerView mRecyclerView;
    private PhoneRecyclerAdapter mRecyclerAdapter;
    private List<String> list = new ArrayList<>();
    private List<FamousEntity> lists = new ArrayList<>();
    private List<String> userInfo = new ArrayList<>();


    private ImageView mImageView;
    private RelativeLayout mRelativeLayoutStick;


    private RecyclerView mRecyclerViewStick;
    private RecyclerView mRecyclerViewCompany;
    private RecyclerView mRecyclerViewGroup;


    private TuiJIanStickAdapter mStickAdapter;
    private TuijianCompanyAdapter mCompanyAdapter;
    private TuiJIanGounpAdapter mGounpAdapter;


    private JSONObject resultJson = new JSONObject();
    private int targetId = 0;

    private String from = "tuijian";
    private String data = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!"".equalsIgnoreCase(getIntent().getStringExtra("from")) && null != getIntent().getStringExtra("from")) {
            from = getIntent().getStringExtra("from");
        }
        data = getIntent().getStringExtra("data");
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
        mRecyclerViewStick = findViewById(R.id.stick_recycler);
        mRecyclerViewCompany = findViewById(R.id.company_recycler);
        mRecyclerViewGroup = findViewById(R.id.group_recycler);
        mRelativeLayoutStick = findViewById(R.id.stick_rl);


        mTextView.setOnClickListener(this);
        mImageView.setOnClickListener(this);

    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        initData();
        setRecyclerData();

    }

    private void setRecyclerData() {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerAdapter = new PhoneRecyclerAdapter(list, this);
        mRecyclerView.setAdapter(mRecyclerAdapter);
        mRecyclerView.setLayoutManager(layoutManager);
//        mRecyclerView.addItemDecoration(new SpaceItemDecoration(15, 0));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_phone_person:
                if ("invite_group".equalsIgnoreCase(from)) {
                    Bundle bundle = new Bundle();
                    bundle.putString("TARGET_ID", targetId + "");
                    bundle.putString("data", resultJson.toString());
                    Intent intent = getIntent();
                    intent.putExtras(bundle);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                } else {
                    Intent intent = new Intent(TuiJianActivity.this, TIMChatActivity.class);
                    Bundle bundle = new Bundle();
                    if ("tuijian".equalsIgnoreCase(from)) {
                        data = resultJson.toString();
                    }
                    bundle.putString("data", data);
                    bundle.putString("from", from);
                    bundle.putString("TARGET_TYPE", "C2C");
                    bundle.putString("TARGET_ID", targetId + "");
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                break;
            case R.id.back:
                finish();
                break;
        }
    }

    private void initData() {
        int uid = PreferenceUtil.getInt("uid");
        String token = PreferenceUtil.getString("token");
        showProgressDialog();
        ApiModule.getInstance().addressBook(uid + "", token)
                .subscribe(AddressBookEntity -> {
                    KyLog.i("----------加载通讯录---------");
                    KyLog.object(AddressBookEntity);
                    cancelProgressDialog();
                    if (lists != null) {
                        lists.clear();
                    }

                    if (AddressBookEntity.getGroup() != null && AddressBookEntity.getGroup().size() > 0) {
                        KyLog.object(AddressBookEntity.getGroup());
                        LinearLayoutManager manager = new LinearLayoutManager(this);
                        mGounpAdapter = new TuiJIanGounpAdapter(AddressBookEntity.getGroup(), this);
                        mRecyclerViewGroup.setAdapter(mGounpAdapter);
                        mGounpAdapter.setTuiJianGounpListener(this);
                        mRecyclerViewGroup.setLayoutManager(manager);
                    }

                    List<com.huxin.communication.entity.AddressBookEntity.CompanyBean> beanList = AddressBookEntity.getCompany();

                    if (beanList != null && beanList.size() > 0) {
                        KyLog.object(AddressBookEntity.getCompany());
                        LinearLayoutManager manager = new LinearLayoutManager(this);
                        mCompanyAdapter = new TuijianCompanyAdapter(AddressBookEntity.getCompany(), this);
                        mRecyclerViewCompany.setAdapter(mCompanyAdapter);
                        mCompanyAdapter.setTuiJianPhoneListener(this);
                        mRecyclerViewCompany.setLayoutManager(manager);
                    }

                    if (AddressBookEntity.getStarList() != null && AddressBookEntity.getStarList().size() > 0) {
                        KyLog.object(AddressBookEntity.getStarList());
                        LinearLayoutManager manager = new LinearLayoutManager(this);
                        mStickAdapter = new TuiJIanStickAdapter(AddressBookEntity.getStarList(), this);
                        mRecyclerViewStick.setAdapter(mStickAdapter);
                        mRecyclerViewStick.setLayoutManager(manager);
                        mStickAdapter.setTuiJianStarListener(this);

                        mRelativeLayoutStick.setVisibility(View.VISIBLE);
                    } else {
                        mRelativeLayoutStick.setVisibility(View.GONE);

                    }

                    if (AddressBookEntity.getFriendList() != null && AddressBookEntity.getFriendList().size() > 0) {
                        KyLog.object(AddressBookEntity.getFriendList());

                        for (int i = 0; i < AddressBookEntity.getFriendList().size(); i++) {
                            FamousEntity famousEntity2 = new FamousEntity();
                            famousEntity2.setName(AddressBookEntity.getFriendList().get(i).getUsername());
                            famousEntity2.setImage(AddressBookEntity.getFriendList().get(i).getHeadUrl());
                            famousEntity2.setPhone(AddressBookEntity.getFriendList().get(i).getPhone());
                            famousEntity2.setIndustryType(AddressBookEntity.getFriendList().get(i).getIndustryType());
                            famousEntity2.setStarFriend(AddressBookEntity.getFriendList().get(i).getStarFriend());
                            famousEntity2.setId(AddressBookEntity.getFriendList().get(i).getUid());
                            famousEntity2.setType(1);
                            KyLog.object(famousEntity2);
                            lists.add(famousEntity2);
                        }
                    }

                    KyLog.object(lists);
                    if (lists.size() > 0) {
                        mAdapter = new TuiJianPhoneAdapter(TuiJianActivity.this, lists);
                        mAdapter.setTuiJianPhoneListener(this);
                        mListView.setAdapter(mAdapter);
                    }
                }, throwable -> {
                    KyLog.d(throwable.toString());
                    cancelProgressDialog();
                    Toast.makeText(this, throwable.getMessage().toString(), Toast.LENGTH_SHORT).show();
                });

    }


    @Override
    public void updateImage(String image, boolean b) {
        if (b) {
            list.add(image);
        } else {
            list.remove(image);
        }
        mRecyclerAdapter.setList(list);
    }

    @Override
    public void updateUserInfo(String userinfo, boolean b) {
        if (b) {
            userInfo.add(userinfo);
        } else {
            userInfo.remove(userinfo);
        }
        KyLog.d(userinfo);
        KyLog.object(userInfo);
        JSONArray jsonArray = new JSONArray();
        try {
            for (String info : userInfo) {
                info = info.substring(1, info.length() - 1);
                JSONObject infoJSONObj = new JSONObject(info);
                targetId = infoJSONObj.getInt("id");
                jsonArray.put(infoJSONObj);
            }
            resultJson.put("type", 3);
            resultJson.put("info", jsonArray);
            Log.i("updateUserInfo", "resultJson.toString() = " + resultJson.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void starPhone(String image, boolean b) {
        if (b) {
            if (!TextUtils.isEmpty(image)) {
                list.add(image);
            }
        } else {
            list.remove(image);
        }
        mRecyclerAdapter.setList(list);
    }

    @Override
    public void starUserInfo(String userinfo, boolean b) {
        if (b) {
            if (!TextUtils.isEmpty(userinfo)) {
                userInfo.add(userinfo);
            }
        } else {
            userInfo.remove(userinfo);
        }
        KyLog.object(userInfo);

    }

    @Override
    public void updataCompany(String image, boolean b) {
        if (b) {
            if (!TextUtils.isEmpty(image)) {
                list.add(image);
            }
        } else {
            list.remove(image);
        }
        mRecyclerAdapter.setList(list);
    }

    @Override
    public void CompanyUserInfo(String userinfo, boolean b) {
        if (b) {
            if (!TextUtils.isEmpty(userinfo)) {
                userInfo.add(userinfo);
            }
        } else {
            userInfo.remove(userinfo);
        }
        KyLog.d(userinfo);
    }

    @Override
    public void updateGounp(String image, boolean b) {
        if (b) {
            if (!TextUtils.isEmpty(image)) {
                list.add(image);
            }
        } else {
            list.remove(image);
        }
        mRecyclerAdapter.setList(list);
    }

    @Override
    public void updateUserInfoGounp(String userinfo, boolean b) {
        if (b) {
            if (!TextUtils.isEmpty(userinfo)) {
                userInfo.add(userinfo);
            }
        } else {
            userInfo.remove(userinfo);
        }
        KyLog.d(userinfo);
    }
}
