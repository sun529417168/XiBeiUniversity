package cn.com.xibeiuniversity.xibeiuniversity.base;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;

import cn.com.xibeiuniversity.xibeiuniversity.application.XibeiActivityUtil;
import cn.com.xibeiuniversity.xibeiuniversity.interfaces.NetEventHandler;
import cn.com.xibeiuniversity.xibeiuniversity.service.NetBroadcastReceiver;
import cn.com.xibeiuniversity.xibeiuniversity.utils.NetWorkUtils;
import cn.com.xibeiuniversity.xibeiuniversity.utils.SharedUtil;
import cn.com.xibeiuniversity.xibeiuniversity.utils.ToastUtil;


/**
 * 文件名：BaseActivity
 * 描    述：activity的基类，一些共同的方法写在这
 * 作    者：stt
 * 时    间：2016.12.30
 * 版    本：V1.0.0
 */

public abstract class BaseActivity extends BaseCheckPermissionActivity implements NetEventHandler {
    public XibeiActivityUtil XiBeiApp;
    /**
     * Notification管理
     */
    public NotificationManager mNotificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        XiBeiApp = XibeiActivityUtil.getInstance();
        XiBeiApp.addActivity(this);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        NetBroadcastReceiver.mListeners.add(this);
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        setOnCreate(savedInstanceState);
    }

    /**
     * 方法名：getNetStart
     * 功    能：网络判断方法
     * 参    数：
     * 返回值：无
     */
    @Override
    public void onNetChange() {
        if (NetWorkUtils.getNetworkState(this) == NetWorkUtils.NETWORN_NONE) {
            ToastUtil.show(this, "无网络连接,请检查网络设置!");
            SharedUtil.setInteger(this, "network", 0);
        } else if (NetWorkUtils.getNetworkState(this) == NetWorkUtils.NETWORN_MOBILE) {
            ToastUtil.show(this, "3G/4G网络已连接!");
            SharedUtil.setInteger(this, "network", 2);
        } else if (NetWorkUtils.getNetworkState(this) == NetWorkUtils.NETWORN_WIFI) {
            ToastUtil.show(this, "WIFI已连接!");
            SharedUtil.setInteger(this, "network", 1);
        }
    }

    private void setOnCreate(Bundle savedInstanceState) {
        setView();
        setDate(savedInstanceState);
        init();
    }

    /**
     * 设置布局 第一步
     */
    protected abstract void setView();

    /**
     * 接收上级，填充数据 第二步
     */
    protected abstract void setDate(Bundle savedInstanceState);

    /**
     * 实例化控件 第三步
     */
    protected abstract void init();

    /**
     * 返回键监听
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            XiBeiApp.finishTop();
            return true;
        }
        return false;
    }

    @Override
    protected String[] getNeedPermissions() {
        return new String[0];
    }

    @Override
    protected void permissionGrantedSuccess() {
        Log.i("成功", "true");

    }

    @Override
    protected void permissionGrantedFail() {
        Log.i("失败", "false");
    }

    /**
     * 清除当前创建的通知栏
     */
    public void clearNotify(int notifyId) {
        mNotificationManager.cancel(notifyId);//删除一个特定的通知ID对应的通知
//		mNotification.cancel(getResources().getString(R.string.app_name));
    }

    /**
     * 清除所有通知栏
     */
    public void clearAllNotify() {
        mNotificationManager.cancelAll();// 删除你发的所有通知
    }

    /**
     * @获取默认的pendingIntent,为了防止2.3及以下版本报错
     * @flags属性: 在顶部常驻:Notification.FLAG_ONGOING_EVENT
     * 点击去除： Notification.FLAG_AUTO_CANCEL
     */
    public PendingIntent getDefalutIntent(int flags) {
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, new Intent(), flags);
        return pendingIntent;
    }
}
