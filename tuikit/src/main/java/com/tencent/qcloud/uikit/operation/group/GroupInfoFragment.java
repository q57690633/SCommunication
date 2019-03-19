package com.tencent.qcloud.uikit.operation.group;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMGroupManager;
import com.tencent.imsdk.TIMGroupMemberInfo;
import com.tencent.imsdk.TIMValueCallBack;
import com.tencent.imsdk.ext.group.TIMGroupManagerExt;
import com.tencent.imsdk.ext.group.TIMGroupMemberResult;
import com.tencent.qcloud.uikit.PreferenceUtil;
import com.tencent.qcloud.uikit.R;
import com.tencent.qcloud.uikit.TUIKit;
import com.tencent.qcloud.uikit.business.chat.group.model.GroupChatManager;
import com.tencent.qcloud.uikit.business.chat.group.view.GroupInfoPanel;
import com.tencent.qcloud.uikit.business.chat.group.view.widget.GroupMemberControler;
import com.tencent.qcloud.uikit.common.BaseFragment;
import com.tencent.qcloud.uikit.common.UIKitConstants;
import com.tencent.qcloud.uikit.common.component.titlebar.PageTitleBar;
import com.tencent.qcloud.uikit.http.NetWorkService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
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
    private LinearLayout mSetTopSwitchLl;
    private LinearLayout mMsgNoAlertLl;
    private LinearLayout mClearRecordLl;
    private Button mQuitBtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mBaseView = inflater.inflate(R.layout.info_fragment_group_new, container, false);
        groupId = getArguments().getString(UIKitConstants.GROUP_ID);
        Log.i(TAG, "groupId = " + groupId);
        initView();
        initData(groupId);
        return mBaseView;
    }

    private void initView() {
        mMemberRv = mBaseView.findViewById(R.id.group_member_rv);
        mAddMemberIv = mBaseView.findViewById(R.id.icon_add_iv);
        mSetTopSwitchLl = mBaseView.findViewById(R.id.set_top_ll);
        mMsgNoAlertLl = mBaseView.findViewById(R.id.msg_no_alert_ll);
        mClearRecordLl = mBaseView.findViewById(R.id.clear_record_ll);
        mQuitBtn = mBaseView.findViewById(R.id.quit_btn);
    }

    private void initData(String groupId) {
        //initGroupMember(groupId);
    }

    private void initGroupMember(String groupId) {
        String token = PreferenceUtil.getString(getActivity().getBaseContext(), "token");
        NetWorkService initGroupMember = createRetrofit().create(NetWorkService.class);
        Observable<Response> answers = initGroupMember.toChatPage(token, groupId);
        answers.map(new Func1<Response, String>() {
            @Override
            public String call(Response response) {
                try {
                    String str = response.toString();
                    Log.i(TAG, "initGroupMember str = " + str);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<String>() {
            @Override
            public void call(String s) {

            }
        });
    }

    private void inviteGroupMember(String groupId) {
        //创建待加入群组的用户列表
        ArrayList list = new ArrayList();
        String user = "";
        //添加用户
        user = "sample_user_1";
        list.add(user);
        user = "sample_user_2";
        list.add(user);
        user = "sample_user_3";
        list.add(user);
        //回调
        TIMValueCallBack<List<TIMGroupMemberResult>> cb = new TIMValueCallBack<List<TIMGroupMemberResult>>() {
            @Override
            public void onError(int code, String desc) {

            }

            @Override
            public void onSuccess(List<TIMGroupMemberResult> results) { //群组成员操作结果
                for(TIMGroupMemberResult r : results) {
                    Log.d(TAG, "result: " + r.getResult()  //操作结果:  0:添加失败；1：添加成功；2：原本是群成员
                            + " user: " + r.getUser());    //用户帐号
                }
            }
        };
        //将 list 中的用户加入群组
        TIMGroupManagerExt.getInstance().inviteGroupMember(groupId, list, cb);
    }

    private void quitGroup(String groupId) {
        TIMCallBack cb = new TIMCallBack() {
            @Override
            public void onError(int code, String desc) {
                //错误码 code 和错误描述 desc，可用于定位请求失败原因
                //错误码 code 含义请参见错误码表
            }

            @Override
            public void onSuccess() {
                Log.e(TAG, "quit group succ");
            }
        };
        TIMGroupManager.getInstance().quitGroup(groupId, cb);
    }

    private Retrofit createRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(UIKitConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

}
