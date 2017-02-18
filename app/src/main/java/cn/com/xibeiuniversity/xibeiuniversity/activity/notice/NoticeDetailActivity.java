package cn.com.xibeiuniversity.xibeiuniversity.activity.notice;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.com.xibeiuniversity.xibeiuniversity.R;
import cn.com.xibeiuniversity.xibeiuniversity.base.BaseActivity;

/**
 * 文件名：NoticeDetailActivity
 * 描    述：通知详情类
 * 作    者：stt
 * 时    间：2017.1.6
 * 版    本：V1.0.0
 */

public class NoticeDetailActivity extends BaseActivity implements View.OnClickListener {
    private Context context;
    private TextView titleName;
    private LinearLayout back;

    @Override
    protected void setView() {
        setContentView(R.layout.activity_notice_detail);
    }

    @Override
    protected void setDate(Bundle savedInstanceState) {

    }

    @Override
    protected void init() {
        titleName = (TextView) findViewById(R.id.title_name);
        titleName.setText("通告详情");
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
