package cn.com.xibeiuniversity.xibeiuniversity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import cn.com.xibeiuniversity.xibeiuniversity.R;
import cn.com.xibeiuniversity.xibeiuniversity.base.BaseActivity;

/**
 * Created by zhangyunlong on 2017/3/30.
 **/

public class InputTextActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout back, save;
    private EditText editText;
    private String text, oldText;

    @Override
    protected void setView() {
        setContentView(R.layout.activity_input_text);
    }

    protected void setDate(Bundle savedInstanceState) {
        oldText="";
        if (getIntent().getStringExtra("value") != null) {
            oldText = getIntent().getStringExtra("value");
        }
    }

    protected void init() {
        back = (LinearLayout) findViewById(R.id.title_back);
        save = (LinearLayout) findViewById(R.id.title_save);
        back.setVisibility(View.VISIBLE);
        save.setVisibility(View.VISIBLE);
        editText = (EditText) findViewById(R.id.input_text);
        editText.setText(oldText);
        back.setOnClickListener(this);
        save.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.title_back:
                intent.putExtra("return_value", oldText);
                setResult(RESULT_CANCELED, intent);
                finish();
                break;
            case R.id.title_save:
                text = editText.getText().toString();
                intent.putExtra("return_value", text);
                setResult(RESULT_OK, intent);
                finish();
                break;
        }
    }
}
