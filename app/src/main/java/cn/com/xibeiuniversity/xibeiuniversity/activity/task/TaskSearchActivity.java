package cn.com.xibeiuniversity.xibeiuniversity.activity.task;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import cn.com.xibeiuniversity.xibeiuniversity.R;
import cn.com.xibeiuniversity.xibeiuniversity.base.BaseActivity;
import cn.com.xibeiuniversity.xibeiuniversity.interfaces.SearchTypePopInterface;
import cn.com.xibeiuniversity.xibeiuniversity.utils.PopWindowUtils;
import cn.com.xibeiuniversity.xibeiuniversity.utils.ToastUtil;

/**
 * 文件名：TaskSearchActivity
 * 描    述：任务搜索界面
 * 作    者：stt
 * 时    间：2017.1.10
 * 版    本：V1.0.0
 */

public class TaskSearchActivity extends BaseActivity implements View.OnClickListener, SearchTypePopInterface {

    private TextView searchText;
    private EditText inputInfo;
    private LinearLayout searchLayout;
    private TextView searchTypeText;
    private PopupWindow searchPop;


    @Override
    protected void setView() {
        setContentView(R.layout.activity_task_search);
    }

    @Override
    protected void setDate(Bundle savedInstanceState) {

    }

    @Override
    protected void init() {
        searchText = (TextView) findViewById(R.id.task_search_btn);
        searchText.setOnClickListener(this);
        inputInfo = (EditText) findViewById(R.id.task_search_inputInfo);
        searchLayout = (LinearLayout) findViewById(R.id.task_search_type_layout);
        searchTypeText = (TextView) findViewById(R.id.task_search_type);
        searchLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.task_search_btn:
                finisnActivity();
                break;
            case R.id.task_search_type_layout:
                searchPop = PopWindowUtils.showSearchTypePop(this, searchLayout);
                break;
        }
    }

    private void finisnActivity() {
        Intent intent = new Intent();
        intent.putExtra("username", inputInfo.getText().toString().trim());
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finisnActivity();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void searchType(String typeName) {
        searchTypeText.setText(typeName);
        if (searchPop.isShowing()) {
            searchPop.dismiss();
        }
    }
}
