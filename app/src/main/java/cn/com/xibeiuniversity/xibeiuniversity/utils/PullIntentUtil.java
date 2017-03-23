package cn.com.xibeiuniversity.xibeiuniversity.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.alibaba.fastjson.JSON;
import com.alibaba.sdk.android.push.notification.CPushMessage;
import com.j256.ormlite.stmt.query.In;

import org.json.JSONException;
import org.json.JSONObject;

import cn.com.xibeiuniversity.xibeiuniversity.activity.UpdatePasswordActivity;
import cn.com.xibeiuniversity.xibeiuniversity.activity.task.TaskPullDialogActivity;
import cn.com.xibeiuniversity.xibeiuniversity.bean.task.TaskBean;

/**
 * 文件名：PullIntentUtil
 * 描    述：推送过来的信息，根据类型做跳转的工具类
 * 作    者：stt
 * 时    间：2017.3.22
 * 版    本：V1.0.7
 */

public class PullIntentUtil {
    public static void intentAvtivity(Context context, CPushMessage cPushMessage) {
        Intent intent = null;
        Bundle bundle = null;
//        try {
//            JSONObject json = null;
//            json = new JSONObject(cPushMessage.getContent());
//            switch (json.getString("messageType")) {
//                case "level_up"://等级提升（对应返回等级提升相关数据）
//                    YJLivelUpMedel livelUpMedel = JSON.parseObject(json.getString("levelUp"), YJLivelUpMedel.class);
//                    intent = new Intent(context, YJUpgradeLevelDialogActivity.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    bundle = new Bundle();
//                    bundle.putSerializable("livelUpMedel", livelUpMedel);
//                    intent.putExtras(bundle);
//                    context.startActivity(intent);
//                    break;
//
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        TaskBean taskList = JSON.parseObject(cPushMessage.getContent(), TaskBean.class);
        intent = new Intent(context, TaskPullDialogActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        bundle = new Bundle();
//        bundle.putSerializable("taskBean", taskList);
//        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
