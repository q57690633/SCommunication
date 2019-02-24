package com.huxin.communication;

import android.app.Application;
import android.os.Environment;
import android.support.multidex.MultiDexApplication;

import com.huxin.communication.controls.Constanst;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.sky.kylog.KyLog;
import com.tencent.imsdk.TIMLogLevel;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMSdkConfig;
import com.tencent.qcloud.uikit.BaseUIKitConfigs;
import com.tencent.qcloud.uikit.TUIKit;


/**
 * Created by yangzanxiong on 2017/11/24.
 */

public class HuXinApplication extends MultiDexApplication {

    public static HuXinApplication mContext;
    public static String APP_ID;


    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
//        APP_ID = "wx90879eb54e7e597c";
//
//
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
//                .memoryCacheExtraOptions(480, 800) //即保存的每个缓存文件的最大长宽
                .threadPoolSize(3) //线程池内加载的数量
                .threadPriority(Thread.NORM_PRIORITY - 2)
                //解释：当同一个Uri获取不同大小的图片，缓存到内存时，只缓存一个。默认会缓存多个不同的大小的相同图片
                .denyCacheImageMultipleSizesInMemory()  //拒绝缓存多个图片。
                .memoryCache(new WeakMemoryCache()) //缓存策略你可以通过自己的内存缓存实现 ，这里用弱引用，缺点是太容易被回收了，不是很好！
//                .memoryCacheSi  MobSDK.init(this);ze(2 * 1024 * 1024) //设置内存缓存的大小
                .diskCacheSize(50 * 1024 * 1024) //设置磁盘缓存大小 50M
                .diskCacheFileNameGenerator(new Md5FileNameGenerator()) //将保存的时候的URI名称用MD5 加密
                .tasksProcessingOrder(QueueProcessingType.LIFO) //设置图片下载和显示的工作队列排序
                .diskCacheFileCount(100) //缓存的文件数量
//                .diskCache(new UnlimitedDiskCache(cacheDir)) //自定义缓存路径
//                .defaultDisplayImageOptions(defaultOptions) //显示图片的参数，默认：DisplayImageOptions.createSimple()
                .imageDownloader(new BaseImageDownloader(this, 5 * 1000, 30 * 1000)) // connectTimeout (5 s), readTimeout (30 s)超时时间
//                .writeDebugLogs() //打开调试日志
                .build();//开始构建


        ImageLoader.getInstance().init(config);
//
//        AccountUtil.setApiKey("App_Android");
//        AccountUtil.setTime(String.valueOf(System.currentTimeMillis()).substring(0, 10));
//
//        CrashReport.initCrashReport(getApplicationContext(), "667b6833d3", true);
////        PreferenceUtil.putString("token", "VkhBeTlEeVNJTGNWZlhLQ1N1SCsxNyt3aEpWQnU1d2R3eEc2cWtGdnYrSHZhWkNWc3NZQW9KVEtMa2EzR1ZISw==");
////        PreferenceUtil.putString("token", "VkhBeTlEeVNJTGNWZlhLQ1N1SCsxNyt3aEpWQnU1d2R3eEc2cWtGdnYrSEZuTnQwMUNkQmxOYmxuaVJEZFU4Kw==");


        //腾讯IM SDK初始化
        KyLog.d("TIM SDK init start");
        TIMSdkConfig TIMConfig = new TIMSdkConfig(Constanst.TIMSDKAPPID)
                .setAccoutType(Constanst.TIMAccountType)
                .enableLogPrint(true)              // 是否在控制台打印Log?
                .setLogLevel(TIMLogLevel.DEBUG)    // Log输出级别（debug级别会很多）
                .setLogPath(Environment.getExternalStorageDirectory().getPath() + "/TIMLog/");
        // Log文件存放在哪里？

        //初始化 SDK
        TIMManager.getInstance().init(getApplicationContext(), TIMConfig);
        TUIKit.init(this, Constanst.TIMSDKAPPID, BaseUIKitConfigs.getDefaultConfigs());
        KyLog.d("TIM SDK init end");

    }
}
