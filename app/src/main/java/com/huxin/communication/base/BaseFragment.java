package com.huxin.communication.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huxin.communication.utils.PreferenceUtil;
import com.sky.kylog.KyLog;

/**
 * Created by kyosky on 16/5/23.
 */
public abstract class BaseFragment extends Fragment {
    public static final String TOKEN = "token";
    public static final String IS_BINFPHONE = "is_bind_phone";
    private boolean mIsVisibleToUser;
    private boolean mIsViewInitailized;
    private boolean mIsDataInitailized;
    private boolean mIsEnableLazyLoad;
    protected ProgressDialog mProgressDialog;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        System.out.println(toString() + ": onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println(toString() + ": onCreate");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        KyLog.d("LYY : " + "setUserVisibleHint == " + isVisibleToUser);
        System.out.println(toString() + ": setUserVisibleHint:= " + isVisibleToUser);
        this.mIsVisibleToUser = isVisibleToUser;
        checkIfLoadData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        System.out.println(toString() + ": onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        System.out.println(toString() + ": onViewCreated");
        if (!mIsEnableLazyLoad) {
            initView(view);
            loadData();
        } else {
            mIsViewInitailized = true;
            initView(view);
            if (savedInstanceState != null) {
                onRestoreInstanceState(savedInstanceState);
            }
            if (!mIsDataInitailized) {
                checkIfLoadData();
            } else {
                bindData();
            }
        }
    }

    protected void enableLazyLoad() {
        mIsEnableLazyLoad = true;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        System.out.println(toString() + ": onActivityCreated");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        System.out.println(toString() + ": onSaveInstanceState");
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        mIsDataInitailized = true;
    }

    protected abstract void initView(View view);

    protected abstract void loadData();

    protected abstract void bindData();

    private void checkIfLoadData() {
        if (mIsVisibleToUser && mIsViewInitailized && !mIsDataInitailized && mIsEnableLazyLoad) {
            mIsDataInitailized = true;
            loadData();
        }
    }

    /**
     * 加载弹框
     */
    protected void showProgressDialog() {
        showProgressDialog(true);
    }

    protected void showProgressDialog(boolean isCancel) {
        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setMessage("正在加载中...");
        mProgressDialog.setCancelable(isCancel);
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();
    }

    /**
     * 取消加载弹框
     */
    protected void cancelProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        System.out.println(toString() + ": onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println(toString() + ": onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mProgressDialog != null) {
            mProgressDialog.cancel();
            mProgressDialog = null;
        }
        System.out.println(toString() + ": onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        System.out.println(toString() + ": onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mIsViewInitailized = false;
        System.out.println(toString() + ": onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println(toString() + ": onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        System.out.println(toString() + ": onDetach");
    }

//    public boolean isLogin() {
//        String token = PreferenceUtil.getString(TOKEN);
//            if (TextUtils.isEmpty(token)){
////            Intent intent = new Intent(getActivity(), LoginActivity.class);
////            startActivity(intent);
//            return false;
//        }
//        return true;
//    }


    public boolean isLogin() {
        String token = PreferenceUtil.getString(TOKEN);
        if (TextUtils.isEmpty(token)) {
//
            return false;
        }
        return true;
    }

    public boolean isBindPhone() {
        if (PreferenceUtil.getBoolean(IS_BINFPHONE)) {
            return false;
        }
        return true;
    }
}
