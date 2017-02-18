package cn.com.xibeiuniversity.xibeiuniversity.interfaces;

import java.util.ArrayList;

import cn.com.xibeiuniversity.xibeiuniversity.bean.UserBean;
import cn.com.xibeiuniversity.xibeiuniversity.bean.task.TaskBean;

/**
 * 文件名：TaskListInterface
 * 描    述：任务列表的接口回调
 * 作    者：stt
 * 时    间：2017.02.13
 * 版    本：V1.0.0
 */

public interface TaskListInterface {
    void showTaskList(TaskBean taskBean);
}
