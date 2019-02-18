package com.huxin.communication.http;

import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kyosky on 16/1/13.
 */
public class RequestUtils {

    /**
     * 组合请求参数
     * @param method 请求方法名
     * @param datas 请求参数
     * @return Map<String,Object>
     */
    public static Map<String,Object> setRequestParams(String method, Map<String,Object> datas){

        if (TextUtils.isEmpty(method)){
            new Throwable("method can not be null");
        }
        Map<String,Object> params = new HashMap<>();
        params.put("method",method);
        if (datas != null) {
            params.put("parameters", datas);
        }
        return params;
    }

}
