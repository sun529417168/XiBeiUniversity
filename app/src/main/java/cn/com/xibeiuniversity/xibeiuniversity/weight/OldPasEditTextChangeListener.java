package cn.com.xibeiuniversity.xibeiuniversity.weight;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;

/**
 * 文件名：OldPasEditTextChangeListener
 * 描    述：监听密码变化
 * 作    者：stt
 * 时    间：2017.2.14
 * 版    本：V1.0.0
 */

public class OldPasEditTextChangeListener implements TextWatcher {
    private Context context;

    public OldPasEditTextChangeListener(Context context) {
        this.context = context;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
