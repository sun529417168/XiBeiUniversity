package cn.com.xibeiuniversity.xibeiuniversity.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.com.xibeiuniversity.xibeiuniversity.R;
import cn.com.xibeiuniversity.xibeiuniversity.base.BaseActivity;

/**
 * 文件名：UpdatePasswordActivity
 * 描    述：修改密码界面
 * 作    者：stt
 * 时    间：2017.1.18
 * 版    本：V1.0.0
 */

public class UpdatePasswordActivity extends BaseActivity implements View.OnClickListener{
    private TextView titleName;
    private LinearLayout back;
    @Override
    protected void setView() {
        setContentView(R.layout.activity_update_password);
    }

    @Override
    protected void setDate(Bundle savedInstanceState) {

    }

    @Override
    protected void init() {
        titleName = (TextView) findViewById(R.id.title_name);
        titleName.setText("修改密码");
        back = (LinearLayout) findViewById(R.id.title_back);
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back:
                XiBeiApp.finishTop();
                break;
        }
    }
}
