package com.huxin.communication.ui.cammer;

import com.lzy.imagepicker.bean.ImageItem;
import com.sky.kylog.KyLog;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.GetBuilder;
import com.zhy.http.okhttp.builder.PostFormBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class HttpUtil {

    private PostFormBuilder mPost;
    private GetBuilder mGet;

    public HttpUtil() {
        OkHttpUtils.getInstance().getOkHttpClient().newBuilder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .writeTimeout(15 * 1000L, TimeUnit.MILLISECONDS)
                .build();

        mPost = OkHttpUtils.post();
        mGet = OkHttpUtils.get();
    }

    //封装请求
    public void postRequest(String url, Map<String, String> params, MyStringCallBack callback) {
        mPost.url(url)
                .params(params)
                .build()
                .execute(callback);
    }

    //上传文件
    public void postFileRequest(String url, Map<String, String> params, ArrayList<ImageItem> pathList, MyStringCallBack callback) {
        KyLog.d("postFileRequest");
        Map<String, File> files = new HashMap<>();
        if (pathList != null && pathList.size() > 0) {
            for (int i = 0; i < pathList.size(); i++) {
//                String newPath = BitmapUtils.compressImageUpload(pathList.get(i).path);
                String[] path = pathList.get(i).path.split("/");
                String str = path[path.length - 1];
                KyLog.d(str);
                files.put(str + i, new File(pathList.get(i).path));
//                files.put(pathList.get(i).name + i, new File(newPath));

            }
        }

        KyLog.d(files + "travel");
        KyLog.d(url + "travel");

        mPost.url(url)
                .params(params)
                .files("files", files)
                .build()
                .execute(callback);
    }
}
