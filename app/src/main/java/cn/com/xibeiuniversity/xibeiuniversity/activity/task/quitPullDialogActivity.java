package cn.com.xibeiuniversity.xibeiuniversity.activity.task;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cn.com.xibeiuniversity.xibeiuniversity.R;
import cn.com.xibeiuniversity.xibeiuniversity.base.BaseActivity;
import cn.com.xibeiuniversity.xibeiuniversity.bean.task.TaskInfoBean;

/**
 * Created by zhangyunlong on 2017/3/24.
 */

public class quitPullDialogActivity extends BaseActivity implements View.OnClickListener {
    TaskInfoBean taskInfoList;
    private TextView titleNameText, taskNameText, taskInfoText, close, sure;
    private ImageView imageView;

    @Override
    protected void setView() {
        setContentView(R.layout.dialog_pull_quit);
    }

    @Override
    protected void setDate(Bundle savedInstanceState) {
        taskInfoList = (TaskInfoBean) this.getIntent().getSerializableExtra("taskInfoBean");
    }

    @Override
    protected void init() {
        titleNameText = (TextView) findViewById(R.id.dialog_pull_quit_titleName);
        taskNameText = (TextView) findViewById(R.id.dialog_pull_quit_name);
        taskInfoText = (TextView) findViewById(R.id.dialog_pull_quit_info);
        close = (TextView) findViewById(R.id.dialog_pull_quit_close);
        titleNameText.setText(taskInfoList.getTitle());
        taskNameText.setText(taskInfoList.getName());
        taskInfoText.setText(taskInfoList.getContent());
        close.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog_pull_quit_close:
                if(taskInfoList.getType().equals("ForceQuit"))
                {
                    XiBeiApp.exit();
                    break;
                }
                else
                {
                    break;
                }

        }
    }
}
