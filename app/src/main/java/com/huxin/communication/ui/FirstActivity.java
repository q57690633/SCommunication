package com.huxin.communication.ui;

import android.content.Intent;
import android.os.Bundle;
import com.huxin.communication.R;
import com.huxin.communication.StartActivity;
import com.huxin.communication.base.BaseActivity;

public class FirstActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_first);

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void loadData(Bundle savedInstanceState) {


    }


    @Override
    protected void onResume() {
        super.onResume();
        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(FirstActivity.this, StartActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.activity_alpha_in,R.anim.activity_alpha_out);
                finish();
            }
        },3000);
    }

}
