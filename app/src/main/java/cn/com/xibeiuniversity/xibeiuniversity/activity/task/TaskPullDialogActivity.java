package cn.com.xibeiuniversity.xibeiuniversity.activity.task;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cn.com.xibeiuniversity.xibeiuniversity.R;
import cn.com.xibeiuniversity.xibeiuniversity.base.BaseActivity;
import cn.com.xibeiuniversity.xibeiuniversity.bean.task.TaskBean;

/**
 * 文件名：TaskPullDialogActivity
 * 描    述：推送任务信息的类
 * 作    者：stt
 * 时    间：2017.03.22
 * 版    本：V1.0.7
 */

public class TaskPullDialogActivity extends BaseActivity implements View.OnClickListener {
    TaskBean taskList;
    private TextView titleNameText, taskNameText, taskInfoText, close, sure;
    private ImageView imageView;

    @Override
    protected void setView() {
        setContentView(R.layout.dialog_pull_task);
    }

    @Override
    protected void setDate(Bundle savedInstanceState) {
//        taskList = (TaskBean) this.getIntent().getSerializableExtra("taskBean");
    }

    @Override
    protected void init() {
        titleNameText = (TextView) findViewById(R.id.dialog_pull_task_titleName);
        taskNameText = (TextView) findViewById(R.id.dialog_pull_task_name);
        taskInfoText = (TextView) findViewById(R.id.dialog_pull_task_info);
        close = (TextView) findViewById(R.id.dialog_pull_task_close);
        sure = (TextView) findViewById(R.id.dialog_pull_task_sure);
        close.setOnClickListener(this);
        sure.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog_pull_task_close:
                break;
            case R.id.dialog_pull_task_sure:
                break;
        }
    }
}
