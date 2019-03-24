package com.tencent.qcloud.uikit.operation.group;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMGroupManager;
import com.tencent.imsdk.TIMGroupMemberInfo;
import com.tencent.imsdk.TIMValueCallBack;
import com.tencent.imsdk.ext.group.TIMGroupDetailInfo;
import com.tencent.imsdk.ext.group.TIMGroupManagerExt;
import com.tencent.imsdk.ext.group.TIMGroupMemberResult;
import com.tencent.qcloud.uikit.PreferenceUtil;
import com.tencent.qcloud.uikit.R;
import com.tencent.qcloud.uikit.adapter.GroupDeleteMemberAdapter;
import com.tencent.qcloud.uikit.adapter.GroupInfoMemberAdapter;
import com.tencent.qcloud.uikit.business.chat.group.view.GroupDeleteMemberActivity;
import com.tencent.qcloud.uikit.business.chat.view.itemdecoration.GridSpacingItemDecoration;
import com.tencent.qcloud.uikit.common.BaseFragment;
import com.tencent.qcloud.uikit.common.UIKitConstants;
import com.tencent.qcloud.uikit.entity.MemberHeadUrlEntity;
import com.tencent.qcloud.uikit.http.NetWorkService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by valxehuang on 2018/7/30.
 */

public class GroupInfoFragment extends BaseFragment {
    private final String TAG = "GroupInfoFragment";

    private View mBaseView;
    private String groupId;
    private RecyclerView mMemberRv;
    private ImageView mAddMemberIv;
    private ImageView mDeleteMemberIv;
    private CheckBox mSetTopCheckBox;
    private CheckBox mMsgNoAlertCheckBox;
    private LinearLayout mClearRecordLl;
    private Button mQuitBtn;
    private TextView mCheckMoreTv;
    private TextView mGroupName;


    private JSONArray groupMemberJSONArr = new JSONArray();
    private ArrayList<MemberHeadUrlEntity> memberList = new ArrayList<>();
    private ArrayList<String> headUrlList = new ArrayList<>();

    private String name;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mBaseView = inflater.inflate(R.layout.info_fragment_group_new, container, false);
        groupId = getArguments().getString(UIKitConstants.GROUP_ID);
        name = getArguments().getString(UIKitConstants.GROUP_NAME);

        initView();
        initData(groupId);
        initOnClickListener();
        return mBaseView;
    }

    private void initView() {
        mMemberRv = mBaseView.findViewById(R.id.group_member_rv);
        mAddMemberIv = mBaseView.findViewById(R.id.icon_add_iv);
        mDeleteMemberIv = mBaseView.findViewById(R.id.icon_delete_iv);
        mSetTopCheckBox = mBaseView.findViewById(R.id.set_top_checkbox);
        mMsgNoAlertCheckBox = mBaseView.findViewById(R.id.msg_no_alert_checkbox);
        mClearRecordLl = mBaseView.findViewById(R.id.clear_record_ll);
        mQuitBtn = mBaseView.findViewById(R.id.quit_btn);
        mCheckMoreTv = mBaseView.findViewById(R.id.check_more);

        mGroupName = mBaseView.findViewById(R.id.text_group);

        mBaseView.findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });

    }

    private void initData(String groupId) {

        if (!TextUtils.isEmpty(name)) {
            mGroupName.setText(name);
        }
//        initGroupMember(groupId);
    }

    private void initGroupMember(String groupId) {
        String token = PreferenceUtil.getString(getActivity().getBaseContext(), "token");
        Log.i(TAG, "token = " + token);
        Log.i(TAG, "groupId = " + groupId);
        NetWorkService initGroupMember = createRetrofit().create(NetWorkService.class);
        Observable<ResponseBody> answers = initGroupMember.toChatPage(token, groupId);
        answers.map(new Func1<ResponseBody, JSONArray>() {
            @Override
            public JSONArray call(ResponseBody responseBody) {
                JSONArray result = new JSONArray();
                try {
                    String str = responseBody.string();
                    JSONObject response = new JSONObject(str);
                    String data = response.getJSONObject("data").toString();
                    Map res = new HashMap();
                    iteraJson(data, res);
                    ArrayList<String> mapKey = new ArrayList<>();
                    Set keySet = res.keySet();
                    Iterator iterator = keySet.iterator();
                    while (iterator.hasNext()) {
                        String key = iterator.next().toString();
                        mapKey.add(key);
                    }
                    result = getResultData(mapKey, data);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return result;
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<JSONArray>() {
                    @Override
                    public void call(JSONArray jsonArray) {
                        Log.i(TAG, "jsonArray = " + jsonArray.toString());
                        groupMemberJSONArr = jsonArray;
                        ArrayList<String> list = new ArrayList<>();
                        try {
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject object = jsonArray.getJSONObject(i);
                                MemberHeadUrlEntity entity = new MemberHeadUrlEntity();
                                entity.setCompanyName(object.getString("companyName"));
                                ArrayList<String> headurllist = new ArrayList<>();
                                JSONArray data = object.getJSONArray("data");
                                for (int j = 0; j < data.length(); j++) {
                                    String headUrl = data.getJSONObject(j).getString("headUrl");
                                    headurllist.add(headUrl);
                                    list.add(headUrl);
                                }
                                entity.setHeadUrl(headurllist);
                                memberList.add(entity);
                            }
                            headUrlList = list;
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 5);
                        gridLayoutManager.setOrientation(GridLayout.VERTICAL);
                        mMemberRv.setLayoutManager(gridLayoutManager);
//                mMemberRv.addItemDecoration(new GridSpacingItemDecoration(20, 20));
                        mMemberRv.setAdapter(new GroupInfoMemberAdapter(getActivity(), list));
                    }
                });
    }

    private void initOnClickListener() {
        mAddMemberIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("com.huxin.communication.tuijian");
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.putExtra("from", "invite_group");
                startActivityForResult(intent, 20);
            }
        });
        mDeleteMemberIv.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                delMember();
            }
        });
        mSetTopCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
        mMsgNoAlertCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
        mClearRecordLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mQuitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quitGroup(groupId);
            }
        });
        mCheckMoreTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), GroupMoreMemberActivity.class);
//                String data = listToString(headUrlList);
                Log.d("GroupInfoFragment", memberList.size() + "== data");
                intent.putParcelableArrayListExtra("data", memberList);
//                intent.putExtra("data",data);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume method run");
        initGroupMember(groupId);
    }

    private void inviteGroupMember(ArrayList<String> list) {
        String token = PreferenceUtil.getString(getActivity().getBaseContext(), "token");
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i) + ",");
        }
        String uid = sb.substring(0, sb.toString().length() - 1);
        Log.i(TAG, "uid = " + uid);
        NetWorkService initGroupMember = createRetrofit().create(NetWorkService.class);
        Observable<ResponseBody> answers = initGroupMember.addFlockMember(token, groupId, uid);
        answers.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ResponseBody>() {
                    @Override
                    public void call(ResponseBody responseBody) {
                        try {
                            JSONObject result = new JSONObject(responseBody.string());
                            if (0 == result.getInt("resultCode")) {
                                Toast.makeText(getActivity(), result.getString("resultMsg"), Toast.LENGTH_SHORT).show();
                                initGroupMember(groupId);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    private void quitGroup(String groupId) {
        TIMCallBack cb = new TIMCallBack() {
            @Override
            public void onError(int code, String desc) {
                Log.i(TAG, "quitGroup error code = " + code);
                Log.i(TAG, "quitGroup error desc = " + desc);
            }

            @Override
            public void onSuccess() {
                Toast.makeText(getActivity(), (R.string.exit_group), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setAction("com.huxin.communication.main");
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            }
        };
        TIMGroupManager.getInstance().quitGroup(groupId, cb);
    }

    private Retrofit createRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(UIKitConstants.BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    private void iteraJson(String str, Map res) throws JSONException {
        JSONObject jsonObject = new JSONObject(str);
        Iterator it = jsonObject.keys();
        List<String> keys = new ArrayList<String>();
        while (it.hasNext()) {
            String key = (String) it.next();
            Object value = jsonObject.get(key);
            res.put(key, value);
        }
    }

    private JSONArray getResultData(ArrayList<String> mapKey, String data) throws JSONException {
        JSONArray result = new JSONArray();
        for (int i = 0; i < mapKey.size(); i++) {
            JSONArray jsonArray = new JSONObject(data).getJSONArray(mapKey.get(i));

            JSONObject company = new JSONObject();
            JSONArray headUrlJSONArr = new JSONArray();

            for (int j = 0; j < jsonArray.length(); j++) {
                JSONObject headUrlJSONObj = new JSONObject();
                if (jsonArray.getJSONObject(j).has("headUrl")) {
                    String headUrl = jsonArray.getJSONObject(j).getString("headUrl");
                    headUrlJSONObj.put("headUrl", headUrl);
                } else {
                    headUrlJSONObj.put("headUrl", "");
                }

                if (jsonArray.getJSONObject(j).has("username")) {
                    String username = jsonArray.getJSONObject(j).getString("username");
                    headUrlJSONObj.put("username", username);
                } else {
                    headUrlJSONObj.put("username", "");
                }

                if (jsonArray.getJSONObject(j).has("positions")) {
                    String positions = jsonArray.getJSONObject(j).getString("positions");
                    headUrlJSONObj.put("positions", positions);
                } else {
                    headUrlJSONObj.put("positions", "");
                }

                if (jsonArray.getJSONObject(j).has("companyName")) {
                    String companyName = jsonArray.getJSONObject(j).getString("companyName");
                    headUrlJSONObj.put("companyName", companyName);
                } else {
                    headUrlJSONObj.put("companyName", "");
                }

                if (jsonArray.getJSONObject(j).has("industryType")) {
                    String industryType = jsonArray.getJSONObject(j).getString("industryType");
                    headUrlJSONObj.put("industryType", industryType);
                } else {
                    headUrlJSONObj.put("industryType", "");
                }

                if (jsonArray.getJSONObject(j).has("uid")) {
                    int uid = jsonArray.getJSONObject(j).getInt("uid");
                    headUrlJSONObj.put("uid", uid);
                } else {
                    headUrlJSONObj.put("uid", -100);
                }

                headUrlJSONArr.put(headUrlJSONObj);
            }
            company.put("companyName", mapKey.get(i));
            company.put("data", headUrlJSONArr);

            result.put(company);

        }
        return result;
    }

    private void getGroupMembers() {
        TIMValueCallBack<List<TIMGroupMemberInfo>> cb = new TIMValueCallBack<List<TIMGroupMemberInfo>>() {
            @Override
            public void onError(int code, String desc) {
                Log.i(TAG, "getGroupMembers error code = " + code + " desc = " + desc);
            }

            @Override
            public void onSuccess(List<TIMGroupMemberInfo> infoList) {//参数返回群组成员信息

                for (TIMGroupMemberInfo info : infoList) {
                    Log.i(TAG, "info = " + info.toString());
                }
            }
        };

        TIMGroupManagerExt.getInstance().getGroupMembers(groupId, cb);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void delMember() {
        int uid = PreferenceUtil.getInt(getContext(), "uid");
        TIMValueCallBack<List<TIMGroupDetailInfo>> cb = new TIMValueCallBack<List<TIMGroupDetailInfo>>() {
            @Override
            public void onError(int code, String desc) {
                Log.i(TAG, "getGroupInfo error code = " + code + " desc = " + desc);
            }

            @SuppressLint("NewApi")
            @Override
            public void onSuccess(List<TIMGroupDetailInfo> infoList) { //参数中返回群组信息列表
                for (TIMGroupDetailInfo info : infoList) {
                    if (uid == Integer.parseInt(info.getGroupOwner())) {
                        Intent intent = new Intent(getActivity(), GroupDeleteMemberActivity.class);
                        intent.putExtra("data", groupMemberJSONArr.toString());
                        intent.putExtra("groupId", groupId);
                        getActivity().startActivity(intent);
                    } else {
                        Toast.makeText(getContext(), getContext().getResources().getString(R.string.group_info_not_owner), Toast.LENGTH_SHORT).show();
                    }
                }

            }
        };
        getGroupInfo(cb);
    }

    private void getGroupInfo(TIMValueCallBack<List<TIMGroupDetailInfo>> cb) {
        ArrayList<String> groupList = new ArrayList<String>();
        groupList.add(groupId);
        TIMGroupManagerExt.getInstance().getGroupDetailInfo(groupList, cb);
    }

    private String listToString(List<String> list) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i != list.size()) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode == Activity.RESULT_OK && requestCode == 20) {
            String data = intent.getStringExtra("data");
            ArrayList<String> list = new ArrayList<>();
            try {
                if (null == data) {
                    return;
                }
                JSONObject dataJson = new JSONObject(data);
                for (int i = 0; i < dataJson.getJSONArray("info").length(); i++) {
                    String id = dataJson.getJSONArray("info").getString(i) + "";
                    list.add(id);
                }
                inviteGroupMember(list);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
