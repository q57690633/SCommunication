package com.huxin.communication.ui.house.phone;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.huxin.communication.R;
import com.huxin.communication.base.BaseActivity;
import com.huxin.communication.controls.Constanst;
import com.huxin.communication.http.ApiModule;
import com.huxin.communication.utils.PreferenceUtil;
import com.sky.kylog.KyLog;

public class AddFriendActivity extends BaseActivity implements EditText.OnEditorActionListener {

    private EditText mEditTextSearch;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_add_friend);

        findViewById(R.id.btn_invitation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddFriendActivity.this, InvitationActivity.class);
                startActivity(intent);
            }
        });
    }

    @SuppressLint("WrongViewCast")
    @Override
    protected void initViews() {
        setToolbarCenterMode("添加好友", MODE_BACK);
        mEditTextSearch = findViewById(R.id.ed_phone);
        mEditTextSearch.setOnEditorActionListener(this);
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {



    }

    private void addFlockMember(String username) {

        showProgressDialog();
        ApiModule.getInstance().selectAddressBook(username)
                .subscribe(phoneSearchEntity  -> {
                    if (phoneSearchEntity != null) {
                        Intent intent = new Intent(this,DetailedInformationActivity.class);
                        intent.putExtra("addfriend",phoneSearchEntity);
                        startActivity(intent);
                    }

                    cancelProgressDialog();
                }, throwable -> {
                    KyLog.d(throwable.toString());
                    cancelProgressDialog();
                    Toast.makeText(this, throwable.getMessage().toString(), Toast.LENGTH_SHORT).show();
                });
    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        switch(i){
            case EditorInfo.IME_ACTION_GO:
                String username = mEditTextSearch.getText().toString().trim();
                if (!TextUtils.isEmpty(username)) {
                    addFlockMember(username);
                }else {
                    Toast.makeText(AddFriendActivity.this, "请填写手机号", Toast.LENGTH_SHORT).show();
                }
               KyLog.d("Done_content: " + username );
                break;

        }

        return true;
    }
}
