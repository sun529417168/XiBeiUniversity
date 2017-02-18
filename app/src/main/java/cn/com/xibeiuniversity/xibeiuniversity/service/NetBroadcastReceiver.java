package cn.com.xibeiuniversity.xibeiuniversity.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;

import cn.com.xibeiuniversity.xibeiuniversity.application.MyApplication;
import cn.com.xibeiuniversity.xibeiuniversity.interfaces.NetEventHandler;
import cn.com.xibeiuniversity.xibeiuniversity.utils.NetWorkUtils;


/**
 * 文件名：NetBroadcastReceiver
 * 描    述：广播监听网络变化的服务
 * 作    者：stt
 * 时    间：2016.11.28
 * 版    本：V1.0.0
 */

public class NetBroadcastReceiver extends BroadcastReceiver {
    public static ArrayList<NetEventHandler> mListeners = new ArrayList<NetEventHandler>();
    private static String NET_CHANGE_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(NET_CHANGE_ACTION)) {
            MyApplication.mNetWorkState = NetWorkUtils.getNetworkState(context);
            if (mListeners.size() > 0)//
                for (NetEventHandler handler : mListeners) {
                    handler.onNetChange();
                }
        }
    }
}
