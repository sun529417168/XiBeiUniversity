package cn.com.xibeiuniversity.xibeiuniversity.base;

import android.annotation.SuppressLint;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import java.util.Map;

import cn.com.xibeiuniversity.xibeiuniversity.application.XibeiActivityUtil;
import cn.com.xibeiuniversity.xibeiuniversity.interfaces.TaskListInterface;
import cn.com.xibeiuniversity.xibeiuniversity.utils.MyRequest;


/**
 * 文件名：BaseFragment
 * 描    述：BaseFragment的基类，一些共同的方法写在这
 * 作    者：stt
 * 时    间：2016.12.30
 * 版    本：V1.0.0
 */
public abstract class BaseFragment extends Fragment {
    public XibeiActivityUtil YouWeiApp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        YouWeiApp = XibeiActivityUtil.getInstance();
        View rootView = setView(inflater, container, savedInstanceState);
        setDate();
        init(rootView);
        return rootView;
    }

    /**
     * 设置布局 第一步
     */
    protected abstract View setView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    /**
     * 初始化/预填充数据 第二步
     */
    protected abstract void setDate();

    /**
     * 实例化控件 第三步
     */
    protected abstract void init(View rootView);


}
