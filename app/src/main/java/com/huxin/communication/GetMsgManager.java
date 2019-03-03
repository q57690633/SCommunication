package com.huxin.communication;

import com.huxin.communication.http.service.ApiFactory;
import com.huxin.communication.http.service.BaiHangTongYeService;
import com.huxin.communication.listener.GetMessageListener;

public class GetMsgManager {
    private static GetMsgManager mGetMsgManager;
    private GetMessageListener mMessageListener;

    public static GetMsgManager instants() {

        if (mGetMsgManager == null) {
            synchronized (GetMsgManager.class) {
                if (mGetMsgManager == null) {
                    mGetMsgManager =new GetMsgManager();
                }
            }
        }
        return mGetMsgManager;
    }

    public void setmMessageListener(GetMessageListener messageListener){
        mMessageListener = messageListener;

    }


}