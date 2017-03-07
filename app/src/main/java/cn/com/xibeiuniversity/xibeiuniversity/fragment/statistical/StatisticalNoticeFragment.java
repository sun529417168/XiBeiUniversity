package cn.com.xibeiuniversity.xibeiuniversity.fragment.statistical;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.com.xibeiuniversity.xibeiuniversity.R;
import cn.com.xibeiuniversity.xibeiuniversity.base.BaseFragment;

/**
 * 文件名：StatisticalNoticeFragment
 * 描    述：统计通知的fragment
 * 作    者：stt
 * 时    间：2017.3.7
 * 版    本：V1.0.3
 */

public class StatisticalNoticeFragment extends BaseFragment {
    private Context context;
    @Override
    protected View setView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = inflater.getContext();
        View view = inflater.inflate(R.layout.fragment_statistical_notice, null);
        return view;
    }

    @Override
    protected void setDate() {

    }

    @Override
    protected void init(View rootView) {

    }
}
