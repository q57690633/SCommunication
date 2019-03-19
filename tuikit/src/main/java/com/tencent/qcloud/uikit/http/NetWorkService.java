package com.tencent.qcloud.uikit.http;

import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

public interface NetWorkService {

    @FormUrlEncoded
    @POST("user/toChatPage")
    Observable<Response> toChatPage(@Field("token") String token, @Field("groupId") String groupId);

}
