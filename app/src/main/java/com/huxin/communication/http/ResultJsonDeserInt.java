package com.huxin.communication.http;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.sky.kylog.KyLog;

import java.lang.reflect.Type;

public class ResultJsonDeserInt implements JsonDeserializer<ResponseInt<?>> {
    @Override
    public ResponseInt<?> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        ResponseInt response = new ResponseInt();

        if (json.isJsonObject()) {
            JsonObject jsonObject = json.getAsJsonObject();
            KyLog.d(jsonObject.get("resultMsg").getAsString());


            int code = jsonObject.get("resultCode").getAsInt();
            response.setResultCode(code);
            response.setResultMsg(jsonObject.get("resultMsg").getAsString());
            response.setData(jsonObject.get("data").getAsInt());
//            if (code != 200) {
            if (code != 0) {
                return response;
            }
//            if (response.getData() != null) {
//            Type itemType = ((ParameterizedType) typeOfT).getActualTypeArguments()[0];
//            response.setData(context.deserialize(jsonObject.get("data"), itemType));
//            }
            return response;
        }
        return response;
    }
}
