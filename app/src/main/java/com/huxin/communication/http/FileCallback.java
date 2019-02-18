package com.huxin.communication.http;

import android.os.Looper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by kyosky on 16/7/20.
 */
public class FileCallback implements retrofit2.Callback<ResponseBody>{
    private String mFileName;
    private String mFilePath;

    public FileCallback(String mFileName, String mFilePath) {
        this.mFileName = mFileName;
        this.mFilePath = mFilePath;
    }

    @Override
    public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
        ResponseBody body = response.body();
        File file = new File(mFilePath,mFileName);
        InputStream in = null;
        FileOutputStream fos = null;
        try {
            in = body.byteStream();
            fos = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = in.read(buffer)) != -1){
                fos.write(buffer, 0, len);
            }
            fos.flush();
            System.out.println("is main thread " + (Looper.myLooper() == Looper.getMainLooper()));
            System.out.println(Thread.currentThread());
            System.out.println("chenggong");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {

            try {
                if (in != null) {
                    in.close();
                }
                if (fos != null){
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {

    }
}
