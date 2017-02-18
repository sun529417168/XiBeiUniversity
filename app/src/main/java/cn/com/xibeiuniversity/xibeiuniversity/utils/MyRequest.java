package cn.com.xibeiuniversity.xibeiuniversity.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.xibeiuniversity.xibeiuniversity.activity.MainActivity;
import cn.com.xibeiuniversity.xibeiuniversity.bean.PersonBean;
import cn.com.xibeiuniversity.xibeiuniversity.bean.UserBean;
import cn.com.xibeiuniversity.xibeiuniversity.bean.problem.ProblemBean;
import cn.com.xibeiuniversity.xibeiuniversity.bean.task.TaskBean;
import cn.com.xibeiuniversity.xibeiuniversity.config.UrlConfig;
import cn.com.xibeiuniversity.xibeiuniversity.interfaces.LoginInterface;
import cn.com.xibeiuniversity.xibeiuniversity.interfaces.PersonInfoInterface;
import cn.com.xibeiuniversity.xibeiuniversity.interfaces.ProblemListInterface;
import cn.com.xibeiuniversity.xibeiuniversity.interfaces.TaskListInterface;
import cn.com.xibeiuniversity.xibeiuniversity.okhttps.OkHttpUtils;
import cn.com.xibeiuniversity.xibeiuniversity.okhttps.callback.GenericsCallback;
import cn.com.xibeiuniversity.xibeiuniversity.okhttps.callback.StringCallback;
import cn.com.xibeiuniversity.xibeiuniversity.okhttps.utils.JsonGenericsSerializator;
import okhttp3.Call;


/**
 * 文件名：MyRequest
 * 描    述：请求工具类
 * 作    者：stt
 * 时    间：2017.01.11
 * 版    本：V1.0.0
 */

public class MyRequest {
    /**
     * 方法名：loginRequest
     * 功    能：登陆
     * 参    数：Activity activity final String username, final String password
     * 返回值：无
     */
    public static void loginRequest(final Activity activity, final String username, final String password) {
        final Dialog progDialog = DialogUtils.showWaitDialog(activity);
        final LoginInterface login = (LoginInterface) activity;
        Map<String, Object> params = new HashMap<>();
        try {
            params.put("Name", username);
            params.put("PassWord", password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        OkHttpUtils.post().url(UrlConfig.URL_LOGIN).params(params).build().execute(new GenericsCallback<String>(new JsonGenericsSerializator()) {
            @Override
            public void onResponse(String response, int id) {
                Log.i("userBean", response);
                if ("0".equals(response.replace("\"", ""))) {
                    ToastUtil.show(activity, "您输入的密码有误");
                } else if ("-1".equals(response.replace("\"", ""))) {
                    ToastUtil.show(activity, "帐号不存在");
                } else {
                    UserBean userBean = JSON.parseObject(response, UserBean.class);
                    SharedUtil.setString(activity, "userName", username);
                    SharedUtil.setString(activity, "passWord", password);
                    SharedUtil.setString(activity, "PersonID", userBean.getPersonId() + "");
                    login.login(userBean);
                }
                if (progDialog.isShowing()) {
                    progDialog.dismiss();
                }
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                ToastUtil.show(activity, "服务器有错误，请稍候再试");
                if (progDialog.isShowing()) {
                    progDialog.dismiss();
                }
            }
        });
    }

    /**
     * 方法名：personInfoRequest
     * 功    能：返回人员基本信息
     * 参    数：Activity activity final String username, final String password
     * 返回值：无
     */
    public static void personInfoRequest(final Activity activity, String personID) {
        final Dialog progDialog = DialogUtils.showWaitDialog(activity);
        final PersonInfoInterface personInfoInterface = (PersonInfoInterface) activity;
        Map<String, Object> params = new HashMap<>();
        try {
            params.put("PersonID", personID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        OkHttpUtils.post().url(UrlConfig.URL_GETPERSONINFO).params(params).build().execute(new GenericsCallback<String>(new JsonGenericsSerializator()) {
            @Override
            public void onResponse(String response, int id) {
                if (!TextUtils.isEmpty(response)) {
                    PersonBean personBean = JSON.parseObject(response, PersonBean.class);
                    personInfoInterface.getPersonInfo(personBean);
                }
                if (progDialog.isShowing()) {
                    progDialog.dismiss();
                }
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                ToastUtil.show(activity, "服务器有错误，请稍候再试");
                if (progDialog.isShowing()) {
                    progDialog.dismiss();
                }
            }
        });
    }

    /**
     * 方法名：uodatePersonInfoRequest
     * 功    能：完善个人信息
     * 参    数：Activity activity String... strings
     * 返回值：无
     */
    public static void uodatePersonInfoRequest(final Activity activity, String... strings) {

        Map<String, Object> params = new HashMap<>();
        try {
            params.put("Name", strings[0]);
            params.put("Gender", strings[1].equals("男") ? 1 : 2);
            params.put("IDCard", strings[2]);
            params.put("Phone", strings[3]);
            params.put("OldPassWord", strings[4]);
            params.put("NewPassWord", strings[5]);
            params.put("PersonID", SharedUtil.getString(activity, "PersonID"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        OkHttpUtils.post().url(UrlConfig.URL_EDITTEXTUSERINFO).params(params).build().execute(new GenericsCallback<String>(new JsonGenericsSerializator()) {
            @Override
            public void onResponse(String response, int id) {
                Log.i("addPersonInfo", response);
                if ("true".equals(response)) {
                    Intent intent = new Intent(activity, MainActivity.class);
                    activity.startActivity(intent);
                    activity.finish();
                } else if ("false".equals(response)) {
                    ToastUtil.show(activity, "修改信息失败");
                }
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                Log.i("addPersonInfoError", e.getMessage().toString());
            }
        });
    }

    /**
     * 方法名：isCheckPassWordRequest
     * 功    能：判断密码是否正确
     * 参    数：Activity activity String strings
     * 返回值：无
     */
    public static void isCheckPassWordRequest(final Activity activity, String strings) {
        Map<String, Object> params = new HashMap<>();
        try {
            params.put("PassWord", strings);
            params.put("PersonID", SharedUtil.getString(activity, "PersonID"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        OkHttpUtils.post().url(UrlConfig.URL_ISCHECKPASSWORD).params(params).build().execute(new GenericsCallback<String>(new JsonGenericsSerializator()) {
            @Override
            public void onResponse(String response, int id) {
                if ("false".equals(response)) {
                    ToastUtil.show(activity, "密码有误，请重新输入");
                }
                Log.i("isCheckPass", response);
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                Log.i("isCheckPassError", e.getMessage().toString());
            }
        });
    }


    /**
     * 方法名：tasIssuedListRequest
     * 功    能：任务列表
     * 参    数：Activity activity String... strings
     * 返回值：无
     */
    public static void tasIssuedListRequest(final Context activity, TaskListInterface myTaskListInterface, Object... strings) {
        final TaskListInterface taskListInterface = myTaskListInterface;
        final Dialog progDialog = DialogUtils.showWaitDialog(activity);
        Map<String, Object> params = new HashMap<>();
        try {
            params.put("pageindex", strings[0]);
            params.put("pagesize", strings[1]);
            params.put("state", strings[2]);
            params.put("PersonID", SharedUtil.getString(activity, "PersonID"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        OkHttpUtils.post().url(UrlConfig.URL_GETTASISSUEDLIST).params(params).build().execute(new GenericsCallback<String>(new JsonGenericsSerializator()) {
            @Override
            public void onResponse(String response, int id) {
                response = response.replace(":null,", ":\"\",");
                Log.i("taskList", response);
                TaskBean taskList = JSON.parseObject(response, TaskBean.class);
                taskListInterface.showTaskList(taskList);
                if (progDialog.isShowing()) {
                    progDialog.dismiss();
                }
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                if (progDialog.isShowing()) {
                    progDialog.dismiss();
                }
            }
        });
    }

    /**
     * 方法名：addTaskAssignedInfoRequest
     * 功    能：任务反馈
     * 参    数：Activity activity String... strings
     * 返回值：无
     */
    public static void addTaskAssignedInfoRequest(final Context activity, Object... strings) {
        final Dialog progDialog = DialogUtils.showWaitDialog(activity);
        Map<String, Object> params = new HashMap<>();
        try {
            params.put("FeedBackContent", strings[0]);
            params.put("TaskAssignedID", strings[1]);
            params.put("FeedbackState", strings[2]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        OkHttpUtils.post().url(UrlConfig.URL_TASKASSIGNEDINFO).params(params).build().execute(new StringCallback() {
            @Override
            public void onResponse(String response, int id) {
                Log.i("addTaskAssignedInfo", response.toString());
                if (progDialog.isShowing()) {
                    progDialog.dismiss();
                }
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                Log.i("addTaskAssignedInfoError", e.getMessage().toString());
                if (progDialog.isShowing()) {
                    progDialog.dismiss();
                }
            }
        });
    }

    /**
     * 方法名：filesRequest
     * 功    能：任务反馈+上传图片
     * 参    数：Context activity, Map<String, File> params
     * 返回值：无
     */
    public static void filesRequest(final Context activity, File file, Object... strings) {
        final Dialog progDialog = DialogUtils.showWaitDialog(activity);
        Map<String, Object> params = new HashMap<>();
        params.put("FeedBackContent", strings[0]);
        params.put("TaskAssignedID", strings[1]);
        params.put("FeedbackState", strings[2]);
        OkHttpUtils.post().addFile("mFile", file.getName(), file).url(UrlConfig.URL_TASKASSIGNEDINFO).params(params).build().execute(new StringCallback() {
            @Override
            public void onResponse(String response, int id) {
                Log.i("filesInfo", response.toString());
                if (progDialog.isShowing()) {
                    progDialog.dismiss();
                }
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                Log.i("filesInfoError", e.getMessage().toString());
                if (progDialog.isShowing()) {
                    progDialog.dismiss();
                }
            }
        });
    }


    /**
     * 方法名：problemListRequest
     * 功    能：问题列表
     * 参    数：Context activity, TaskListInterface myTaskListInterface, Object... strings
     * 返回值：无
     */
    public static void problemListRequest(final Context activity, ProblemListInterface mProblemListInterface, Object... strings) {
        final ProblemListInterface problemListInterface = mProblemListInterface;
        final Dialog progDialog = DialogUtils.showWaitDialog(activity);
        Map<String, Object> params = new HashMap<>();
        try {
            params.put("pageindex", strings[0]);
            params.put("SearchState", strings[1]);
            params.put("SearchProblemType", strings[2]);
            params.put("SearchDate", strings[3]);
            params.put("pagesize", "3");//每页条数
            params.put("PersonID", SharedUtil.getString(activity, "PersonID"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        OkHttpUtils.post().url(UrlConfig.URL_GETPROBLEMREPORTINFO).params(params).build().execute(new GenericsCallback<String>(new JsonGenericsSerializator()) {
            @Override
            public void onResponse(String response, int id) {
                response = response.replace(":null,", ":\"\",");
                Log.i("problemList", response);
                ProblemBean problemBean = JSON.parseObject(response, ProblemBean.class);
                problemListInterface.showTaskList(problemBean);
                if (progDialog.isShowing()) {
                    progDialog.dismiss();
                }
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                Log.i("problemListError", e.getMessage().toString());
                if ("timeout".equals(e.getMessage().toString())) {
                    ToastUtil.show(activity, "连接超时，请稍后再试");
                }
                if (progDialog.isShowing()) {
                    progDialog.dismiss();
                }
            }
        });
    }
}
