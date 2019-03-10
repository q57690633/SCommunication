package com.huxin.communication.utils;

import com.huxin.communication.entity.SaleOfScreeningEntity;
import com.huxin.communication.entity.UserInfoEntity;
import com.sky.kylog.KyLog;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUitil {

    public static String getData(ArrayList<UserInfoEntity> Salelist) {
        String str = "";
        try {
//            JSONObject jsonObject1 = new JSONObject();
            List<JSONObject> jsonList = new ArrayList<>();
            if (Salelist != null && Salelist.size() > 0) {
                for (UserInfoEntity SaleEntity : Salelist) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("name", SaleEntity.getName());
                    jsonObject.put("phone", SaleEntity.getPhone());
                    jsonObject.put("imageUrl", SaleEntity.getImageHead());
                    jsonList.add(jsonObject);
                }
            }
            str = jsonList.toString();
            KyLog.i("getData str = " + str);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }
}
