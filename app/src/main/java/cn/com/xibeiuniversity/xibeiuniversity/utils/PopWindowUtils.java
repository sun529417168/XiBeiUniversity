package cn.com.xibeiuniversity.xibeiuniversity.utils;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.List;

import cn.com.xibeiuniversity.xibeiuniversity.R;
import cn.com.xibeiuniversity.xibeiuniversity.adapter.problem.PopProblemAdapter;
import cn.com.xibeiuniversity.xibeiuniversity.interfaces.ProblemTypeInterface;
import cn.com.xibeiuniversity.xibeiuniversity.interfaces.SearchTypePopInterface;

/**
 * 文件名：PopWindowUtils
 * 描    述：popWindow工具类
 * 作    者：stt
 * 时    间：2017.01.19
 * 版    本：V1.0.0
 */

public class PopWindowUtils {

    /**
     * 方法名：showSearchTypePop
     * 功    能：弹出任务类型的pop
     * 参    数：Activity activity,View btnPopup
     * 返回值：无
     */
    public static PopupWindow showSearchTypePop(final Activity activity, View btnPopup) {
        final SearchTypePopInterface searchType = (SearchTypePopInterface) activity;
        // TODO: 2016/5/17 构建一个popupwindow的布局
        final View popupView = activity.getLayoutInflater().inflate(R.layout.pop_task_search, null);

        final TextView name = (TextView) popupView.findViewById(R.id.pop_task_search_name);
        final TextView sender = (TextView) popupView.findViewById(R.id.pop_task_search_sender);
        final TextView all = (TextView) popupView.findViewById(R.id.pop_task_search_all);
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchType.searchType(all.getText().toString());
            }
        });
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchType.searchType(name.getText().toString());
            }
        });
        sender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchType.searchType(sender.getText().toString());
            }
        });
        // TODO: 2016/5/17 创建PopupWindow对象，指定宽度和高度
        PopupWindow window = new PopupWindow(popupView, 300, 400);
        // TODO: 2016/5/17 设置背景颜色
//        window.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00000000")));
        // TODO: 2016/5/17 设置可以获取焦点
        window.setFocusable(true);
        // TODO: 2016/5/17 设置可以触摸弹出框以外的区域
        window.setOutsideTouchable(true);
        // TODO：更新popupwindow的状态
        window.update();
        // TODO: 2016/5/17 以下拉的方式显示，并且可以设置显示的位置
        window.showAsDropDown(btnPopup, 0, 20);
        return window;
    }

    /**
     * 方法名：showTaskReplyPop
     * 功    能：弹出任务回复状态的pop
     * 参    数：Activity activity,View btnPopup
     * 返回值：无
     */
    public static PopupWindow showTaskReplyPop(final Activity activity, View btnPopup) {
        final SearchTypePopInterface searchType = (SearchTypePopInterface) activity;
        // TODO: 2016/5/17 构建一个popupwindow的布局
        final View popupView = activity.getLayoutInflater().inflate(R.layout.pop_task_reply, null);

        final TextView finish = (TextView) popupView.findViewById(R.id.pop_task_reply_finish);
        final TextView unFinish = (TextView) popupView.findViewById(R.id.pop_task_reply_unFinish);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchType.searchType(finish.getText().toString());
            }
        });
        unFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchType.searchType(unFinish.getText().toString());
            }
        });
        // TODO: 2016/5/17 创建PopupWindow对象，指定宽度和高度
        PopupWindow window = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, 300);
        // TODO: 2016/5/17 设置背景颜色
//        window.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00000000")));
        // TODO: 2016/5/17 设置可以获取焦点
        window.setFocusable(true);
        // TODO: 2016/5/17 设置可以触摸弹出框以外的区域
        window.setOutsideTouchable(true);
        // TODO：更新popupwindow的状态
        window.update();
        // TODO: 2016/5/17 以下拉的方式显示，并且可以设置显示的位置
        window.showAsDropDown(btnPopup, 0, 20);
        return window;
    }

    /**
     * 方法名：showProblemPop
     * 功    能：弹出问题上报类型的pop
     * 参    数：Activity activity, View btnPopup, List groups
     * 返回值：popupWindow
     */
    public static PopupWindow showProblemPop(Activity activity, ProblemTypeInterface problemInterface, View btnPopup, final List groups, final int type) {
        final ProblemTypeInterface problemTypeInterface = problemInterface;
        LayoutInflater layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.pop_problem, null);
        ListView listView = (ListView) view.findViewById(R.id.pop_problem_list);
        // 加载数据
        PopProblemAdapter popProblemAdapter = new PopProblemAdapter(activity, groups);
        listView.setAdapter(popProblemAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                problemTypeInterface.getProblemType(type, (String) groups.get(position));
            }
        });
        // 创建一个PopuWidow对象
        WindowManager windowManager = (WindowManager) activity.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        PopupWindow popupWindow = new PopupWindow(view, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT, true);
        // TODO: 2016/5/17 设置可以获取焦点
        popupWindow.setFocusable(true);
        // TODO: 2016/5/17 设置可以触摸弹出框以外的区域
        popupWindow.setOutsideTouchable(true);
        // TODO：更新popupwindow的状态
        popupWindow.update();
        // TODO: 2016/5/17 以下拉的方式显示，并且可以设置显示的位置
        popupWindow.showAsDropDown(btnPopup);
        return popupWindow;
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public static void backgroundAlpha(Activity activity, float bgAlpha) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        activity.getWindow().setAttributes(lp);
    }
}
