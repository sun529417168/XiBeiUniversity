package cn.com.xibeiuniversity.xibeiuniversity.activity.task;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cn.com.xibeiuniversity.xibeiuniversity.R;
import cn.com.xibeiuniversity.xibeiuniversity.activity.notice.NoticeDetailActivity;
import cn.com.xibeiuniversity.xibeiuniversity.activity.problem.ProblemDetailActivity;
import cn.com.xibeiuniversity.xibeiuniversity.base.BaseActivity;
import cn.com.xibeiuniversity.xibeiuniversity.bean.task.TaskInfoBean;

/**
 * 文件名：TaskPullDialogActivity
 * 描    述：推送任务信息的类
 * 作    者：stt
 * 时    间：2017.03.22
 * 版    本：V1.0.7
 */

public class TaskPullDialogActivity extends BaseActivity implements View.OnClickListener {
    TaskInfoBean taskInfoBean;
    private TextView titleNameText, taskNameText, taskInfoText, close, sure;
    private ImageView imageView;

    @Override
    protected void setView() {
        setContentView(R.layout.dialog_pull_task);
    }

    @Override
    protected void setDate(Bundle savedInstanceState) {
        taskInfoBean = (TaskInfoBean) this.getIntent().getSerializableExtra("taskInfoBean");
    }

    @Override
    protected void init() {
        titleNameText = (TextView) findViewById(R.id.dialog_pull_task_titleName);
        taskNameText = (TextView) findViewById(R.id.dialog_pull_task_name);
        taskInfoText = (TextView) findViewById(R.id.dialog_pull_task_info);
        close = (TextView) findViewById(R.id.dialog_pull_task_close);
        sure = (TextView) findViewById(R.id.dialog_pull_task_sure);
        titleNameText.setText(taskInfoBean.getTitle());
        taskNameText.setText(taskInfoBean.getName());
        taskInfoText.setText(taskInfoBean.getContent());
        if (taskInfoBean.getId().equals("0")) {
            sure.setVisibility(View.GONE);
            close.setOnClickListener(this);
        } else {
            sure.setVisibility(View.VISIBLE);
            close.setOnClickListener(this);
            sure.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        Intent in = null;
        switch (v.getId()) {
            case R.id.dialog_pull_task_close:
                finish();
                break;
            case R.id.dialog_pull_task_sure:
                if ("Task".equals(taskInfoBean.getType())) {
                    in = new Intent(this, TaskDetailActivity.class);
                    in.putExtra("taskId", taskInfoBean.getId());
                }
                if ("Notice".equals(taskInfoBean.getType())) {
                    in = new Intent(this, NoticeDetailActivity.class);
                    in.putExtra("noticeId", taskInfoBean.getId());
                }
                if ("problem".equals(taskInfoBean.getType())) {
                    in = new Intent(this, ProblemDetailActivity.class);
                    in.putExtra("problemId", taskInfoBean.getId());
                }

                startActivity(in);
                finish();
                break;
        }
    }
}
