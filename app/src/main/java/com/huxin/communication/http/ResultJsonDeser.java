package com.huxin.communication.http;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.sky.kylog.KyLog;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by yangzanxiong on 16/4/27.
 */
public class ResultJsonDeser implements JsonDeserializer<Response<?>> {

    @Override
    public Response<?> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Response response = new Response();

        if (json.isJsonObject()) {
            JsonObject jsonObject = json.getAsJsonObject();
            KyLog.d(jsonObject.get("resultMsg").getAsString());
            KyLog.d(jsonObject.get("data") + "");


            int code = jsonObject.get("resultCode").getAsInt();
            response.setResultCode(code);
            response.setResultMsg(jsonObject.get("resultMsg").getAsString());
//            if (code != 200) {
            if (code != 0) {
                return response;
            }
            KyLog.d(jsonObject.get("data").toString());
            if (jsonObject.get("data") != null && !jsonObject.get("data").toString().equals("null")) {
                KyLog.d("data");
                Type itemType = ((ParameterizedType) typeOfT).getActualTypeArguments()[0];
                response.setData(context.deserialize(jsonObject.get("data"), itemType));
            }
            return response;
        }
        return response;
    }
}
