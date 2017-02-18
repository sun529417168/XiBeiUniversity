package cn.com.xibeiuniversity.xibeiuniversity.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 文件名：NetWorkUtils
 * 描    述：广播监听网络变化的工具类
 * 作    者：stt
 * 时    间：2016.12.30
 * 版    本：V1.0.0
 */

public class NetWorkUtils {
    public static final int NETWORN_NONE = 0;
    public static final int NETWORN_WIFI = 1;
    public static final int NETWORN_MOBILE = 2;

    /**
     * 方法名：getNetworkState
     * 功    能：单击登录按钮的事件
     * 参    数：(Context context)
     * 返回值：int
     */
    public static int getNetworkState(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                return NETWORN_WIFI;
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                return NETWORN_MOBILE;
            }
        }
        return NETWORN_NONE;
    }

    /**
     * 方法名：openNetWork
     * 功    能：没有网络的时候打开设置
     * 参    数：(Context context)
     * 返回值：无
     */
    public static void openNetWork(final Context context) {
        Intent intent = null;
        //判断手机系统的版本  即API大于10 就是3.0或以上版本
        if (android.os.Build.VERSION.SDK_INT > 10) {
            intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
        } else {
            intent = new Intent();
            ComponentName component = new ComponentName("com.android.settings", "com.android.settings.WirelessSettings");
            intent.setComponent(component);
            intent.setAction("android.intent.action.VIEW");
        }
        context.startActivity(intent);
    }
}
