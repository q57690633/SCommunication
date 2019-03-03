package com.huxin.communication.wxapi;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.huxin.communication.HuXinApplication;
import com.huxin.communication.R;
import com.huxin.communication.ui.MainActivity;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;


public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";

    private IWXAPI api;
    private static final String APP_ID = HuXinApplication.APP_ID;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_result);

        api = WXAPIFactory.createWXAPI(this, APP_ID);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onResp(BaseResp resp) {
        Log.d(TAG, "onPayFinish, errCode = " + resp.errCode);
        Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

//    public void success() {
//        Map<String, Object> map = new HashMap<>();
//        map.put("apikey", AccountUtil.getApiKey());
//        map.put("timestamp", AccountUtil.getTime());
//        map.put("token", PreferenceUtil.getString("token"));
//        ApiModule.getInstance().PartnerSucess(FileUntils.md5(FileUntils.signRequest(map)))
//                .subscribe(response -> {
//                    if (response.getStatus() == 200) {
//                        Intent intent = new Intent(this, PayMentSuccessActivity.class);
//                        startActivity(intent);
//                    }
//                }, throwable -> {
//                    KyLog.d(throwable.toString());
//                    Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
//                });
//    }
}