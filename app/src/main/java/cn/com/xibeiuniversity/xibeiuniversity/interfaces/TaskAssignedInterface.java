package cn.com.xibeiuniversity.xibeiuniversity.interfaces;

import cn.com.xibeiuniversity.xibeiuniversity.bean.task.TaskAssignedBean;
import cn.com.xibeiuniversity.xibeiuniversity.bean.task.TaskDetailBean;

/**
 * 文件名：TaskAssignedInterface
 * 描    述：获取任务反馈信息的接口
 * 作    者：stt
 * 时    间：2017.02.23
 * 版    本：V1.0.0
 */

public interface TaskAssignedInterface {
    void getTaskAssignedInfo(TaskAssignedBean taskAssignedBean);

    void getTaskDetail(TaskDetailBean taskDetailBean);
}
