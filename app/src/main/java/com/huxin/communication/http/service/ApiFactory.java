package com.huxin.communication.http.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.huxin.communication.http.NetConfig;
import com.huxin.communication.http.NullStringToEmptyAdapterFactory;
import com.huxin.communication.http.Response;
import com.huxin.communication.http.ResponseInt;
import com.huxin.communication.http.ResponseUntil;
import com.huxin.communication.http.ResultJsonDeser;
import com.huxin.communication.http.ResultJsonDeserInt;
import com.huxin.communication.http.ResultJsonDeserUntil;
import com.sky.kylog.KyLog;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Api工厂类 (网络框架的搭建)
 */

public class ApiFactory {

    private static ApiFactory factory;
    private Retrofit retrofit;
    private static BaiHangTongYeService weilaiyanService;

    private ApiFactory() {
        KyLog.d(NetConfig.BASE_URL);
        //添加gson
        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory<Response>())
                .registerTypeHierarchyAdapter(Response.class, new ResultJsonDeser())
                .registerTypeHierarchyAdapter(ResponseUntil.class, new ResultJsonDeserUntil())
                .registerTypeHierarchyAdapter(ResponseInt.class, new ResultJsonDeserInt())
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .create();

        OkHttpClient.Builder client = new OkHttpClient.Builder();

        client.connectTimeout(5, TimeUnit.SECONDS);

        //加入log信息
        client.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));

        retrofit = new Retrofit.Builder()
                .client(client.build())
                .baseUrl(NetConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public static ApiFactory getFactory() {

        if (factory == null) {
            synchronized (ApiFactory.class) {
                if (factory == null) {
                    factory = new ApiFactory();
                }
            }
        }
        return factory;
    }

    public BaiHangTongYeService BaiHangTongYeService() {

        if (weilaiyanService == null) {
            synchronized (ApiFactory.class) {
                if (weilaiyanService == null) {
                    weilaiyanService = retrofit.create(BaiHangTongYeService.class);
                }
            }
        }
        return weilaiyanService;
    }

}
