package com.tencent.qcloud.uikit.http;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

public interface NetWorkService {

    @FormUrlEncoded
    @POST("user/toChatPage")
    Observable<ResponseBody> toChatPage(@Field("token") String token, @Field("groupId") String groupId);

    @FormUrlEncoded
    @POST("user/addFlockMember")
    Observable<ResponseBody> addFlockMember(@Field("token") String token, @Field("groupId") String groupId, @Field("uid") String uid);

    @FormUrlEncoded
    @POST("user/deleteFlockMember")
    Observable<ResponseBody> deleteFlockMember(@Field("token") String token, @Field("groupId") String groupId, @Field("uid") String uid, @Field("friendId") String friendId);

}
