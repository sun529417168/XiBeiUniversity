package cn.com.xibeiuniversity.xibeiuniversity.application;

import android.app.Activity;
import android.os.Process;
import android.util.Log;

import com.alibaba.sdk.android.push.CloudPushService;
import com.alibaba.sdk.android.push.CommonCallback;
import com.alibaba.sdk.android.push.noonesdk.PushServiceFactory;

import java.util.LinkedList;

import cn.com.xibeiuniversity.xibeiuniversity.activity.LoginActivity;
import cn.com.xibeiuniversity.xibeiuniversity.utils.SharedUtil;

/**
 * 文件名：XibeiActivityUtil
 * 描    述：管理activity的工具类
 * 作    者：stt
 * 时    间：2016.12.30
 * 版    本：V1.0.0
 */
public class XibeiActivityUtil {

    private static LinkedList<Activity> activitys = null;
    /**
     * 程序手势是否弹出状态，true：不弹出，false：弹出
     */
    private static boolean flag = false;
    public int runFlag = 1;
    private static XibeiActivityUtil instance;
    private long clickTime = 0; //记录第一次点击的时间

    private XibeiActivityUtil() {
        activitys = new LinkedList<Activity>();
    }


    /**
     * 单例模式中获取唯一的BYApplication实例
     *
     * @return
     */
    public static XibeiActivityUtil getInstance() {
        if (null == instance) {
            instance = new XibeiActivityUtil();
        }
        return instance;

    }

    /**
     * 添加Activity到容器中
     *
     * @param activity void
     */
    public void addActivity(Activity activity) {
        if (activitys != null && activitys.size() > 0) {
            if (!activitys.contains(activity)) {
                activitys.add(activity);
            } else {
                activitys.remove(activity);
                activitys.add(activity);
            }
        } else {
            activitys.add(activity);
        }

    }

    /**
     * 提取顶部activity
     *
     * @return Activity
     */
    public Activity getTopActivity() {
        Activity aty = null;
        if (activitys != null && activitys.size() > 0) {

            aty = (Activity) activitys.get(activitys.size() - 1);

        }
        return aty;
    }

    /**
     * 遍历所有Activity并finish void
     */
    public void exit() {
        CloudPushService pushService = PushServiceFactory.getCloudPushService();
        pushService.bindAccount("null", new CommonCallback() {
            @Override
            public void onSuccess(String s) {
                Log.i("unInitUserName", "bind account success");
            }

            @Override
            public void onFailed(String errorCode, String errorMessage) {
                Log.i("unInitUserNameError", "bind account fail" + "err:" + errorCode + " - message:" + errorMessage);
            }
        });
        if (activitys != null && activitys.size() > 0) {
            for (int i = 0; i < activitys.size(); i++) {
                Activity aty = activitys.get(i);
                aty.finish();

            }
            activitys.clear();
        }
        Process.killProcess(Process.myPid());
        System.exit(0);
    }

    /**
     * 关闭其他，保留第一个
     */
    public void changeOne() {
        if (activitys != null && activitys.size() > 1) {
            for (int i = 1; i < activitys.size(); i++) {
                Activity aty = (Activity) activitys.get(i);
                aty.finish();

            }
            Activity aty = (Activity) activitys.get(0);
            activitys.clear();
            activitys.add(aty);
        }

    }

    /**
     * 保留顶部
     */
    public void changeTopOne() {
        if (activitys != null && activitys.size() > 1) {
            for (int i = activitys.size() - 2; i >= 0; i--) {
                Activity aty = (Activity) activitys.get(i);
                aty.finish();

            }
            Activity aty = (Activity) activitys.get(activitys.size() - 1);
            activitys.clear();
            activitys.add(aty);
        }
    }

    /**
     * 关闭顶部activity void
     */
    public void finishTop() {
        if (activitys != null && activitys.size() > 0) {
            Activity aty = (Activity) activitys.get(activitys.size() - 1);
            aty.finish();
            activitys.remove(activitys.size() - 1);
        } else {
            Process.killProcess(Process.myPid());
            System.exit(0);
        }


    }

    public void setFlag(boolean flag) {
        XibeiActivityUtil.flag = flag;
    }

    public boolean getFlag() {
        return flag;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return activitys.toString();
    }

}
