package cn.com.xibeiuniversity.xibeiuniversity.application;

import android.app.Application;
import android.content.Context;
import android.util.Log;


import com.alibaba.sdk.android.push.CloudPushService;
import com.alibaba.sdk.android.push.CommonCallback;
import com.alibaba.sdk.android.push.noonesdk.PushServiceFactory;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import cn.com.xibeiuniversity.xibeiuniversity.R;
import cn.com.xibeiuniversity.xibeiuniversity.utils.NetWorkUtils;


/**
 * 文件名：MyApplication
 * 描    述：初始化数据
 * 作    者：
 * 时    间：2016.12.30
 * 版    本：V1.0.0
 */

public class MyApplication extends Application {
    private static final String TAG = "Init";
    private static Context context;
    public static int mNetWorkState;
    public static ImageLoader imageLoader = ImageLoader.getInstance();

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        initCloudChannel(this);
        initData();
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .showStubImage(R.mipmap.login_logo)//加载开始默认的图片
                .showImageForEmptyUri(R.mipmap.login_logo)     //url爲空會显yg7示该图片
                .showImageOnFail(R.mipmap.image_on_fail)                //加载图片出现问题
                .cacheInMemory() // 1.8.6包使用时候，括号里面传入参数true
                .cacheOnDisc() // 1.8.6包使用时候，括号里面传入参数true
                .build();
        ImageLoaderConfiguration config2 = new ImageLoaderConfiguration.Builder(this)
                .defaultDisplayImageOptions(defaultOptions)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO).enableLogging() // Not
                .build();
        imageLoader.init(config2);

    }

    public void initData() {
        mNetWorkState = NetWorkUtils.getNetworkState(this);
    }

    public static Context getContext() {
        return context;
    }

    /**
     * 初始化云推送通道
     *
     * @param applicationContext
     */
    private void initCloudChannel(Context applicationContext) {
        PushServiceFactory.init(applicationContext);
        CloudPushService pushService = PushServiceFactory.getCloudPushService();
        pushService.register(applicationContext, new CommonCallback() {
            @Override
            public void onSuccess(String response) {
                Log.d(TAG, "init cloudchannel success");
            }

            @Override
            public void onFailed(String errorCode, String errorMessage) {
                Log.d(TAG, "init cloudchannel failed -- errorcode:" + errorCode + " -- errorMessage:" + errorMessage);
            }
        });
    }
}
