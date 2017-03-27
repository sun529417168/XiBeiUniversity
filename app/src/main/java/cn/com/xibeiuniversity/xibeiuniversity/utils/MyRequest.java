package cn.com.xibeiuniversity.xibeiuniversity.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.alibaba.fastjson.JSON;
import com.alibaba.sdk.android.push.noonesdk.PushServiceFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cn.com.xibeiuniversity.xibeiuniversity.activity.MainActivity;
import cn.com.xibeiuniversity.xibeiuniversity.adapter.problem.PopProblemTypeRightAdapter;
import cn.com.xibeiuniversity.xibeiuniversity.bean.PersonBean;
import cn.com.xibeiuniversity.xibeiuniversity.bean.UserBean;
import cn.com.xibeiuniversity.xibeiuniversity.bean.notice.NoticeBean;
import cn.com.xibeiuniversity.xibeiuniversity.bean.notice.NoticeDetailBean;
import cn.com.xibeiuniversity.xibeiuniversity.bean.problem.ProblemBean;
import cn.com.xibeiuniversity.xibeiuniversity.bean.problem.ProblemDetailBean;
import cn.com.xibeiuniversity.xibeiuniversity.bean.problem.ProblemTypeLeft;
import cn.com.xibeiuniversity.xibeiuniversity.bean.problem.ProblemTypeRight;
import cn.com.xibeiuniversity.xibeiuniversity.bean.statistical.TaskStatisticalBean;
import cn.com.xibeiuniversity.xibeiuniversity.bean.task.TaskAssignedBean;
import cn.com.xibeiuniversity.xibeiuniversity.bean.task.TaskBean;
import cn.com.xibeiuniversity.xibeiuniversity.bean.task.TaskChoosePersonBean;
import cn.com.xibeiuniversity.xibeiuniversity.bean.task.TaskDetailBean;
import cn.com.xibeiuniversity.xibeiuniversity.bean.task.TaskPriorityBean;
import cn.com.xibeiuniversity.xibeiuniversity.bean.task.TaskTypeBean;
import cn.com.xibeiuniversity.xibeiuniversity.config.UrlConfig;
import cn.com.xibeiuniversity.xibeiuniversity.interfaces.ChoosePersonInterface;
import cn.com.xibeiuniversity.xibeiuniversity.interfaces.LoginInterface;
import cn.com.xibeiuniversity.xibeiuniversity.interfaces.NoticeDetailInterface;
import cn.com.xibeiuniversity.xibeiuniversity.interfaces.NoticeListInterface;
import cn.com.xibeiuniversity.xibeiuniversity.interfaces.PersonInfoInterface;
import cn.com.xibeiuniversity.xibeiuniversity.interfaces.ProblemDetailInterface;
import cn.com.xibeiuniversity.xibeiuniversity.interfaces.ProblemListInterface;
import cn.com.xibeiuniversity.xibeiuniversity.interfaces.ProblemTypeLeftInterface;
import cn.com.xibeiuniversity.xibeiuniversity.interfaces.ProblemTypeRightInterface;
import cn.com.xibeiuniversity.xibeiuniversity.interfaces.StatisticalInfoInterface;
import cn.com.xibeiuniversity.xibeiuniversity.interfaces.TaskAssignedInterface;
import cn.com.xibeiuniversity.xibeiuniversity.interfaces.TaskListInterface;
import cn.com.xibeiuniversity.xibeiuniversity.interfaces.TaskTypeValuesInterface;
import cn.com.xibeiuniversity.xibeiuniversity.okhttps.OkHttpUtils;
import cn.com.xibeiuniversity.xibeiuniversity.okhttps.callback.GenericsCallback;
import cn.com.xibeiuniversity.xibeiuniversity.okhttps.callback.StringCallback;
import cn.com.xibeiuniversity.xibeiuniversity.okhttps.utils.JsonGenericsSerializator;
import cn.com.xibeiuniversity.xibeiuniversity.weight.AddTaskPriorityPopwindow;
import cn.com.xibeiuniversity.xibeiuniversity.weight.AddTaskTypePopwindow;
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
            params.put("Module", "XBGD");
            params.put("DeviceID", PushServiceFactory.getCloudPushService().getDeviceId());
            Log.i("loginRequestdeviceId", PushServiceFactory.getCloudPushService().getDeviceId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        OkHttpUtils.post().url(UrlConfig.URL_LOGIN).params(params).build().execute(new GenericsCallback<String>(new JsonGenericsSerializator()) {
            @Override
            public void onResponse(String response, int id) {
                if ("0".equals(response.replace("\"", ""))) {
                    ToastUtil.show(activity, "您输入的密码有误");
                } else if ("-1".equals(response.replace("\"", ""))) {
                    ToastUtil.show(activity, "帐号不存在");
                } else {
                    UserBean userBean = JSON.parseObject(response, UserBean.class);
                    SharedUtil.setString(activity, "userName", username);
                    SharedUtil.setString(activity, "passWord", password);
                    SharedUtil.setString(activity, "PersonID", userBean.getPersonId() + "");
                    SharedUtil.setString(activity, "LoginName", userBean.getLoginName());
                    SharedUtil.setString(activity, "personName", userBean.getPermissions().get(0).getUserName());
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
    public static void personInfoRequest(final Context activity, String personID) {
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
     * 方法名：personInfoRequest
     * 功    能：返回人员基本信息
     * 参    数：Activity activity final String username, final String password
     * 返回值：无
     */
    public static void personInfoRequest(final Context activity, PersonInfoInterface personInfoInterfaces, String personID) {
        final Dialog progDialog = DialogUtils.showWaitDialog(activity);
        final PersonInfoInterface personInfoInterface = personInfoInterfaces;
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
        final Dialog progDialog = DialogUtils.showWaitDialog(activity);
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
                if ("true".equals(response)) {
                    Intent intent = new Intent(activity, MainActivity.class);
                    activity.startActivity(intent);
                    activity.finish();
                } else if ("false".equals(response)) {
                    ToastUtil.show(activity, "修改信息失败");
                }
                if (progDialog.isShowing()) {
                    progDialog.dismiss();
                }
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                ToastUtil.show(activity, "连接超时，请稍候再试");
                if (progDialog.isShowing()) {
                    progDialog.dismiss();
                }
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
            }

            @Override
            public void onError(Call call, Exception e, int id) {
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
                Log.i("taskList", response);
                TaskBean taskList = JSON.parseObject(response, TaskBean.class);
                taskListInterface.showTaskList(taskList);
                if (progDialog.isShowing()) {
                    progDialog.dismiss();
                }
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                if ("timeout".equals(e.getMessage().toString())) {
                    ToastUtil.show(activity, "连接超时，请稍后再试");
                }
                if (progDialog.isShowing()) {
                    progDialog.dismiss();
                }
            }
        });
    }

    /**
     * 方法名：taskDetailRequest
     * 功    能：任务详情
     * 参    数：final Context activity, TaskListInterface myTaskListInterface, String id
     * 返回值：无
     */
    public static void taskDetailRequest(final Activity activity, String id) {
        final Dialog progDialog = DialogUtils.showWaitDialog(activity);
        final TaskAssignedInterface taskAssignedInterface = (TaskAssignedInterface) activity;
        Map<String, Object> params = new HashMap<>();
        try {
            params.put("ID", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        OkHttpUtils.post().url(UrlConfig.URL_GETTASKINFOBYID).params(params).build().execute(new GenericsCallback<String>(new JsonGenericsSerializator()) {
            @Override
            public void onResponse(String response, int id) {
                Log.i("taskDetailRequest", response);
                TaskDetailBean taskBean = JSON.parseObject(response, TaskDetailBean.class);
                taskAssignedInterface.getTaskDetail(taskBean);
                if (progDialog.isShowing()) {
                    progDialog.dismiss();
                }
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                if ("timeout".equals(e.getMessage().toString())) {
                    ToastUtil.show(activity, "连接超时，请稍后再试");
                }
                if (progDialog.isShowing()) {
                    progDialog.dismiss();
                }
            }
        });
    }

    /**
     * 方法名：taskDetailRequest
     * 功    能：任务详情+反馈
     * 参    数：final Context activity, TaskListInterface myTaskListInterface, String id
     * 返回值：无
     */
    public static void taskDetailTaskAssignedRequest(final Activity activity, String id) {
        final Dialog progDialog = DialogUtils.showWaitDialog(activity);
        final TaskAssignedInterface taskAssignedInterface = (TaskAssignedInterface) activity;
        Map<String, Object> params = new HashMap<>();
        try {
            params.put("TaskAssignedID", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        OkHttpUtils.post().url(UrlConfig.URL_GETTASKINFOANDTASKASSIGNEDINFOBYTASKASSIGNEDID).params(params).build().execute(new GenericsCallback<String>(new JsonGenericsSerializator()) {
            @Override
            public void onResponse(String response, int id) {
                Log.i("taskDetailRequest", response);
                TaskDetailBean taskBean = JSON.parseObject(response, TaskDetailBean.class);
                taskAssignedInterface.getTaskDetail(taskBean);
                if (progDialog.isShowing()) {
                    progDialog.dismiss();
                }
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                if ("timeout".equals(e.getMessage().toString())) {
                    ToastUtil.show(activity, "连接超时，请稍后再试");
                }
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
     * 方法名：filesRequest
     * 功    能：任务反馈+上传图片
     * 参    数：Context activity, Map<String, File> params
     * 返回值：无
     */
    public static void filesRequest(final Activity activity, Map<String, File> fileMap, Object... strings) {
        final Dialog progDialog = DialogUtils.showWaitDialog(activity);
        Map<String, Object> params = new HashMap<>();
        params.put("FeedBackContent", strings[0]);
        params.put("TaskAssignedID", strings[1]);
        params.put("FeedbackState", strings[2]);
        OkHttpUtils.post().files("mFile", fileMap).url(UrlConfig.URL_TASKASSIGNEDINFO).params(params).build().execute(new StringCallback() {
            @Override
            public void onResponse(String response, int id) {
                if ("true".equals(response)) {
                    ToastUtil.show(activity, "反馈成功");
                    activity.finish();
                } else {
                    ToastUtil.show(activity, "反馈失败，请稍候再试");
                }
                if (progDialog.isShowing()) {
                    progDialog.dismiss();
                }
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                ToastUtil.show(activity, "服务器异常，请稍后再试");
                if (progDialog.isShowing()) {
                    progDialog.dismiss();
                }
            }
        });
    }


    /**
     * 方法名：taskAssignedInfoRequest
     * 功    能：获取任务反馈信息
     * 参    数：Context activity, TaskListInterface myTaskListInterface, Object... strings
     * 返回值：无
     */
    public static void taskAssignedInfoRequest(final Context activity, int taskAssignedID) {
        final Dialog progDialog = DialogUtils.showWaitDialog(activity);
        final TaskAssignedInterface taskAssignedInterface = (TaskAssignedInterface) activity;
        Map<String, Object> params = new HashMap<>();
        try {
            params.put("TaskAssignedID", taskAssignedID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        OkHttpUtils.post().url(UrlConfig.URL_GETTASKASSIGNEDINFO).params(params).build().execute(new GenericsCallback<String>(new JsonGenericsSerializator()) {
            @Override
            public void onResponse(String response, int id) {
                Log.i("taskAssignedInfo", response);
                TaskAssignedBean taskAssignedBean = JSON.parseObject(response, TaskAssignedBean.class);
//                taskAssignedInterface.getTaskAssignedInfo(taskAssignedBean);
                if (progDialog.isShowing()) {
                    progDialog.dismiss();
                }
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                ToastUtil.show(activity, "连接超时，请稍后再试");
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
            params.put("pagesize", "5");//每页条数
            params.put("PersonID", SharedUtil.getString(activity, "PersonID"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        OkHttpUtils.post().url(UrlConfig.URL_GETPROBLEMREPORTINFO).params(params).build().execute(new GenericsCallback<String>(new JsonGenericsSerializator()) {
            @Override
            public void onResponse(String response, int id) {
                response = response.replace(":null,", ":\"\",");
                Log.i("liebiao", response);
                ProblemBean problemBean = JSON.parseObject(response, ProblemBean.class);
                problemListInterface.showTaskList(problemBean);
                if (progDialog.isShowing()) {
                    progDialog.dismiss();
                }
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                ToastUtil.show(activity, "连接超时，请稍后再试");
                if (progDialog.isShowing()) {
                    progDialog.dismiss();
                }
            }
        });
    }

    /**
     * 方法名：problemDetailRequest
     * 功    能：问题详情
     * 参    数：Context activity, TaskListInterface myTaskListInterface, Object... strings
     * 返回值：无
     */
    public static void problemDetailRequest(final Activity activity, String id) {
        final ProblemDetailInterface problemDetail = (ProblemDetailInterface) activity;
        final Dialog progDialog = DialogUtils.showWaitDialog(activity);
        Map<String, Object> params = new HashMap<>();
        try {
            params.put("ProblemReportID", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        OkHttpUtils.post().url(UrlConfig.URL_GETPROBLEMDETAIL).params(params).build().execute(new GenericsCallback<String>(new JsonGenericsSerializator()) {
            @Override
            public void onResponse(String response, int id) {
                Log.i("liebiao", response);
                ProblemDetailBean problemBean = JSON.parseObject(response, ProblemDetailBean.class);
                problemDetail.getProblemDetail(problemBean);
                if (progDialog.isShowing()) {
                    progDialog.dismiss();
                }
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                ToastUtil.show(activity, "连接超时，请稍后再试");
                if (progDialog.isShowing()) {
                    progDialog.dismiss();
                }
            }
        });
    }

    /**
     * 方法名：addProblemRequest
     * 功    能：上报问题+上传图片
     * 参    数：Context activity, File file, Object... strings
     * 返回值：无
     */
    public static void addProblemPicRequest(final Context activity, File file, Object... strings) {
        final Dialog progDialog = DialogUtils.showWaitDialog(activity);
        Map<String, Object> params = new HashMap<>();
        params.put("ProblemTitle", strings[0]);
        params.put("SearchProblemType", strings[1]);
        params.put("Position", strings[2]);
        params.put("GPS", strings[3]);
        params.put("FindDate", strings[4]);
        params.put("ProblemDes", strings[5]);
        params.put("ReportPerson", SharedUtil.getString(activity, "PersonID"));
        OkHttpUtils.post().addFile("mFile", file.getName(), file).url(UrlConfig.URL_IMGUPLOAD).params(params).build().execute(new StringCallback() {
            @Override
            public void onResponse(String response, int id) {
                if ("true".equals(response)) {
                    ToastUtil.show(activity, "上报成功");
                } else {
                    ToastUtil.show(activity, "上报失败，请稍候再试");
                }
                if (progDialog.isShowing()) {
                    progDialog.dismiss();
                }
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                ToastUtil.show(activity, "服务器异常，请稍后再试");
                if (progDialog.isShowing()) {
                    progDialog.dismiss();
                }
            }
        });
    }

    /**
     * 方法名：addProblemRequest
     * 功    能：上报问题没有图片
     * 参    数：Context activity, File file, Object... strings
     * 返回值：无
     */
    public static void addProblemRequest(final Context activity, Object... strings) {
        final Dialog progDialog = DialogUtils.showWaitDialog(activity);
        Map<String, Object> params = new HashMap<>();
        params.put("ProblemTitle", strings[0]);
        params.put("SearchProblemType", strings[1]);
        params.put("Position", strings[2]);
        params.put("GPS", strings[3]);
        params.put("FindDate", strings[4]);
        params.put("ProblemDes", strings[5]);
        params.put("ReportPerson", SharedUtil.getString(activity, "PersonID"));
        OkHttpUtils.post().url(UrlConfig.URL_IMGUPLOAD).params(params).build().execute(new StringCallback() {
            @Override
            public void onResponse(String response, int id) {
                if ("true".equals(response)) {
                    ToastUtil.show(activity, "上报成功");
                } else {
                    ToastUtil.show(activity, "上报失败，请稍候再试");
                }
                if (progDialog.isShowing()) {
                    progDialog.dismiss();
                }
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                ToastUtil.show(activity, "服务器异常，请稍后再试");
                if (progDialog.isShowing()) {
                    progDialog.dismiss();
                }
            }
        });
    }

    /**
     * 方法名：addProblemRequestsb
     * 功    能：上报问题最新方法不管有没有图片
     * 参    数：Context activity, Map<String,File> fileMap, Object... strings
     * 返回值：无
     */
    public static void addProblemRequestsb(final Activity activity, Map<String, File> fileMap, Object... strings) {
        final Dialog progDialog = DialogUtils.showWaitDialog(activity);
        Map<String, Object> params = new HashMap<>();
        params.put("ProblemTitle", strings[0]);
        params.put("SearchProblemType", strings[1]);
        params.put("Position", strings[2]);
        params.put("GPS", strings[3]);
        params.put("FindDate", strings[4]);
        params.put("ProblemDes", strings[5]);
        params.put("ReportPerson", SharedUtil.getString(activity, "PersonID"));
        OkHttpUtils.post().files("mFile", fileMap).url(UrlConfig.URL_IMGUPLOAD).params(params).build().execute(new StringCallback() {
            @Override
            public void onResponse(String response, int id) {
                if ("true".equals(response)) {
                    ToastUtil.show(activity, "上报成功");
                    activity.finish();
                } else {
                    ToastUtil.show(activity, "上报失败，请稍候再试");
                }
                if (progDialog.isShowing()) {
                    progDialog.dismiss();
                }
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                ToastUtil.show(activity, "服务器异常，请稍后再试");
                if (progDialog.isShowing()) {
                    progDialog.dismiss();
                }
            }
        });
    }

    /**
     * 方法名：updatePassWordRequest
     * 功    能：修改密码
     * 参    数：Activity activity final String username, final String password
     * 返回值：无
     */
    public static void updatePassWordRequest(final Activity activity, String oldPassword, String newPassword) {
        final Dialog progDialog = DialogUtils.showWaitDialog(activity);
        Map<String, Object> params = new HashMap<>();
        try {
            params.put("PersonID", SharedUtil.getString(activity, "PersonID"));
            params.put("OldPassWord", oldPassword);
            params.put("NewPassWord", newPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        OkHttpUtils.post().url(UrlConfig.URL_UPDATEPASSWORD).params(params).build().execute(new GenericsCallback<String>(new JsonGenericsSerializator()) {
            @Override
            public void onResponse(String response, int id) {
                if ("true".equals(response)) {
                    ToastUtil.show(activity, "修改成功");
                    activity.finish();
                } else {
                    ToastUtil.show(activity, "修改成功，请稍候再试");
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
     * 方法名：getProblemTypeLeft
     * 功    能：获取一级节点信息
     * 参    数：Activity activity final String username, final String password
     * 返回值：无
     */
    public static void getProblemTypeLeft(final Activity activity, ProblemTypeLeftInterface problemTypeLeftInterfaces) {
        final ProblemTypeLeftInterface problemTypeLeftInterface = problemTypeLeftInterfaces;
        OkHttpUtils.get().url(UrlConfig.URL_GETBEGINNINGENTITY).build().execute(new GenericsCallback<String>(new JsonGenericsSerializator()) {
            @Override
            public void onResponse(String response, int id) {
                ArrayList<ProblemTypeLeft> problemTypeLeftList = (ArrayList<ProblemTypeLeft>) JSON.parseArray(response, ProblemTypeLeft.class);
                problemTypeLeftInterface.getTypeLeft(problemTypeLeftList);
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                ToastUtil.show(activity, "服务器有错误，请稍候再试");
            }
        });
    }

    /**
     * 方法名：getTypeListByCodeRequest
     * 功    能：获取二级节点信息（根据code）
     * 参    数：Activity activity final String username, final String password
     * 返回值：无
     */
    public static void getTypeListByCodeRequest(final Context activity, String code, final ListView listView, final ProblemTypeRightInterface problemTypeRightInterfaces) {
        Map<String, Object> params = new HashMap<>();
        try {
            params.put("SearchProblemType", code);
        } catch (Exception e) {
            e.printStackTrace();
        }
        OkHttpUtils.post().url(UrlConfig.URL_GETTYPELISTBYCODE).params(params).build().execute(new GenericsCallback<String>(new JsonGenericsSerializator()) {
            @Override
            public void onResponse(String response, int id) {
                listView.setVisibility(View.VISIBLE);
                ArrayList<ProblemTypeRight> problemTypeRight = (ArrayList<ProblemTypeRight>) JSON.parseArray(response, ProblemTypeRight.class);
                PopProblemTypeRightAdapter problemTypeRightAdapter = new PopProblemTypeRightAdapter(activity, problemTypeRight, problemTypeRightInterfaces);
                listView.setAdapter(problemTypeRightAdapter);
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                ToastUtil.show(activity, "服务器有错误，请稍候再试");
            }
        });
    }

    /**
     * 方法名：getNoticeListRequest
     * 功    能：获取通知信息列表
     * 参    数：Context activity, NoticeListInterface noticeListInterfaces,int SearchTime
     * 返回值：无
     */
    public static void getNoticeListRequest(final Context activity, NoticeListInterface noticeListInterfaces, int searchTime) {
        Map<String, Object> params = new HashMap<>();
        final Dialog progDialog = DialogUtils.showWaitDialog(activity);
        final NoticeListInterface noticeListInterface = noticeListInterfaces;
        try {
            params.put("SearchTimeNumber", searchTime);
            params.put("PersonID", SharedUtil.getString(activity, "PersonID"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        OkHttpUtils.post().url(UrlConfig.URL_GETINFORMISSUEDINFO).params(params).build().execute(new GenericsCallback<String>(new JsonGenericsSerializator()) {
            @Override
            public void onResponse(String response, int id) {
                response = response.replace(":null,", ":\"\",");
                Log.i("noticeBean", response);
                NoticeBean noticeBean = JSON.parseObject(response, NoticeBean.class);
                noticeListInterface.getNoticeList(noticeBean);
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
     * 方法名：getNoticeDetailRequest
     * 功    能：获取通知详情
     * 参    数：final Activity activity, int searchTime
     * 返回值：无
     */
    public static void getNoticeDetailRequest(final Activity activity, String id) {
        Map<String, Object> params = new HashMap<>();
        final Dialog progDialog = DialogUtils.showWaitDialog(activity);
        final NoticeDetailInterface noticeDetailInterface = (NoticeDetailInterface) activity;
        try {
            params.put("ID", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        OkHttpUtils.post().url(UrlConfig.URL_GETINFORMISSUEDINFOBYID).params(params).build().execute(new GenericsCallback<String>(new JsonGenericsSerializator()) {
            @Override
            public void onResponse(String response, int id) {
                Log.i("noticeBean", response);
                NoticeDetailBean noticeBean = JSON.parseObject(response, NoticeDetailBean.class);
                noticeDetailInterface.getNoticeDetail(noticeBean);
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
     * 方法名：statisticalTaskRequest
     * 功    能：任务下发统计
     * 参    数：Context activity, StatisticalInfoInterface statisticalInfoInterfaces, final Object... strings
     * 返回值：无
     */
    public static void statisticalTaskRequest(final Context activity, StatisticalInfoInterface statisticalInfoInterfaces, final Object... strings) {
        Map<String, Object> params = new HashMap<>();
        final Dialog progDialog = DialogUtils.showWaitDialog(activity);
        final StatisticalInfoInterface statisticalInfoInterface = statisticalInfoInterfaces;
        try {
            params.put("dataType", strings[0]);
            params.put("StartingTime", strings[1]);
            params.put("EndTime", strings[2]);
            params.put("type", strings[3]);
            params.put("PersonId", SharedUtil.getString(activity, "PersonID"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        OkHttpUtils.post().url(UrlConfig.URL_TASISSUEDDATASECTOR).params(params).build().execute(new GenericsCallback<String>(new JsonGenericsSerializator()) {
            @Override
            public void onResponse(String response, int id) {
                TaskStatisticalBean bean = JSON.parseObject(response, TaskStatisticalBean.class);
                statisticalInfoInterface.getStatisticalInfo(bean.getList(), (Integer) strings[0]);
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
     * 方法名：statisticalNoticeRequest
     * 功    能：通知公告统计
     * 参    数：Context activity, StatisticalInfoInterface statisticalInfoInterfaces, final Object... strings
     * 返回值：无
     */
    public static void statisticalNoticeRequest(final Context activity, StatisticalInfoInterface statisticalInfoInterfaces, final Object... strings) {
        Map<String, Object> params = new HashMap<>();
        final Dialog progDialog = DialogUtils.showWaitDialog(activity);
        final StatisticalInfoInterface statisticalInfoInterface = statisticalInfoInterfaces;
        try {
            params.put("StartingTime", strings[0]);
            params.put("EndTime", strings[1]);
            params.put("type", strings[2]);
            params.put("PersonId", SharedUtil.getString(activity, "PersonID"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        OkHttpUtils.post().url(UrlConfig.URL_INFORMISSUEDDATASECTOR).params(params).build().execute(new GenericsCallback<String>(new JsonGenericsSerializator()) {
            @Override
            public void onResponse(String response, int id) {
                Log.i("statisticalNotice", response);
                TaskStatisticalBean bean = JSON.parseObject(response, TaskStatisticalBean.class);
                statisticalInfoInterface.getStatisticalInfo(bean.getList(), 0);
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
     * 方法名：statisticalProblemRequest
     * 功    能：问题统计
     * 参    数：Context activity, StatisticalInfoInterface statisticalInfoInterfaces, final Object... strings
     * 返回值：无
     */
    public static void statisticalProblemRequest(final Context activity, StatisticalInfoInterface statisticalInfoInterfaces, final Object... strings) {
        Map<String, Object> params = new HashMap<>();
        final Dialog progDialog = DialogUtils.showWaitDialog(activity);
        final StatisticalInfoInterface statisticalInfoInterface = statisticalInfoInterfaces;
        try {
            params.put("showType", strings[0]);
            params.put("StartingTime", strings[1]);
            params.put("EndTime", strings[2]);
            params.put("type", strings[3]);
            params.put("PersonId", SharedUtil.getString(activity, "PersonID"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        OkHttpUtils.post().url(UrlConfig.URL_PROBLEMREPORTDATASECTOR).params(params).build().execute(new GenericsCallback<String>(new JsonGenericsSerializator()) {
            @Override
            public void onResponse(String response, int id) {
                Log.i("statisticalProblem", response);
                TaskStatisticalBean bean = JSON.parseObject(response, TaskStatisticalBean.class);
                statisticalInfoInterface.getStatisticalInfo(bean.getList(), (Integer) strings[0]);
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
     * 方法名：getTaskTypeRequest
     * 功    能：获取任务下发类型
     * 参    数：final Activity activity, final RelativeLayout typeLayout
     * 返回值：无
     */
    public static void getTaskTypeRequest(final Activity activity, final RelativeLayout typeLayout, final int index) {
        Map<String, Object> params = new HashMap<>();
        final Dialog progDialog = DialogUtils.showWaitDialog(activity);
        final TaskTypeValuesInterface taskTypeValuesInterface = (TaskTypeValuesInterface) activity;
        OkHttpUtils.post().url(UrlConfig.URL_GETTASKTYPE).params(params).build().execute(new GenericsCallback<String>(new JsonGenericsSerializator()) {
            @Override
            public void onResponse(String response, int id) {
                ArrayList<TaskTypeBean> listBean = (ArrayList<TaskTypeBean>) JSON.parseArray(response, TaskTypeBean.class);
                AddTaskTypePopwindow mAddTaskType = new AddTaskTypePopwindow(activity, listBean);
                mAddTaskType.showAtLocation(typeLayout, Gravity.BOTTOM, 0, 0);
                mAddTaskType.setAddresskListener(new AddTaskTypePopwindow.OnAddressCListener() {
                    @Override
                    public void onClick(String name, String code) {
                        taskTypeValuesInterface.getTaskType(name, code, index);
                    }
                });
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
     * 方法名：getPriorityRequest
     * 功    能：获取优先级类型
     * 参    数：final Activity activity, final RelativeLayout typeLayout
     * 返回值：无
     */
    public static void getPriorityRequest(final Activity activity, final RelativeLayout typeLayout, final int index) {
        final Dialog progDialog = DialogUtils.showWaitDialog(activity);
        final TaskTypeValuesInterface taskTypeValuesInterface = (TaskTypeValuesInterface) activity;
        OkHttpUtils.get().url(UrlConfig.URL_GETTASKPRIORITY).build().execute(new GenericsCallback<String>(new JsonGenericsSerializator()) {
            @Override
            public void onResponse(String response, int id) {
                ArrayList<TaskPriorityBean> listBean = (ArrayList<TaskPriorityBean>) JSON.parseArray(response, TaskPriorityBean.class);
                AddTaskPriorityPopwindow mAddTaskPriority = new AddTaskPriorityPopwindow(activity, listBean);
                mAddTaskPriority.showAtLocation(typeLayout, Gravity.BOTTOM, 0, 0);
                mAddTaskPriority.setAddresskListener(new AddTaskPriorityPopwindow.OnAddressCListener() {
                    @Override
                    public void onClick(String name, String code) {
                        taskTypeValuesInterface.getTaskType(name, code, index);
                    }
                });
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
     * 方法名：GetPersonInfoByDepartmentRequest
     * 功    能：获取人员部门树状结构数据
     * 参    数：final Activity activity
     * 返回值：无
     */
    public static void GetPersonInfoByDepartmentRequest(final Activity activity) {
        final Dialog progDialog = DialogUtils.showWaitDialog(activity);
        final ChoosePersonInterface personInterface = (ChoosePersonInterface) activity;
        OkHttpUtils.get().url(UrlConfig.URL_GETPERSONINFOBYDEPARTMENT).build().execute(new GenericsCallback<String>(new JsonGenericsSerializator()) {
            @Override
            public void onResponse(String response, int id) {
                ArrayList<TaskChoosePersonBean> choosePersonList = (ArrayList<TaskChoosePersonBean>) JSON.parseArray(response, TaskChoosePersonBean.class);
                personInterface.getPerson(choosePersonList);
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
     * 方法名：addTaskRequests
     * 功    能：新增任务
     * 参    数：Context activity, Map<String,File> fileMap, Object... strings
     * 返回值：无
     */
    public static void addTaskRequests(final Activity activity, Map<String, File> fileMap, Object... strings) {
        final Dialog progDialog = DialogUtils.showWaitDialog(activity);
        Map<String, Object> params = new HashMap<>();
        params.put("AddTaskName", strings[0]);
        params.put("AddTaskType", strings[1]);
        params.put("AddTaskAddr", strings[2]);
        params.put("AddTaskPriority", strings[3]);
        params.put("AddStartDate", strings[4]);
        params.put("AddEndDate", strings[5]);
        params.put("PersonIDs", strings[6]);
        params.put("AddTaskDes", strings[7]);
        params.put("PersonID", SharedUtil.getString(activity, "PersonID"));
        OkHttpUtils.post().files("mFile", fileMap).url(UrlConfig.URL_UPLOADTASK).params(params).build().execute(new StringCallback() {
            @Override
            public void onResponse(String response, int id) {
                if ("true".equals(response)) {
                    ToastUtil.show(activity, "上报成功");
                    activity.finish();
                } else {
                    ToastUtil.show(activity, "上报失败，请稍候再试");
                }
                if (progDialog.isShowing()) {
                    progDialog.dismiss();
                }
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                Log.i("addTaskRequestsError", e.getMessage().toString());
                ToastUtil.show(activity, "服务器异常，请稍后再试");
                if (progDialog.isShowing()) {
                    progDialog.dismiss();
                }
            }
        });
    }
}
