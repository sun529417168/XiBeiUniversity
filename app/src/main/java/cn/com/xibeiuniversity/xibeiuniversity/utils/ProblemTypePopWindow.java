package cn.com.xibeiuniversity.xibeiuniversity.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;

import java.util.ArrayList;

import cn.com.xibeiuniversity.xibeiuniversity.R;
import cn.com.xibeiuniversity.xibeiuniversity.adapter.problem.PopProblemTypeLeftAdapter;
import cn.com.xibeiuniversity.xibeiuniversity.bean.problem.ProblemTypeLeft;

/**
 * 文件名：ProblemTypePopWindow
 * 描    述：弹出问题上报类型的pop
 * 作    者：stt
 * 时    间：2017.02.28
 * 版    本：V1.0.0
 */

public class ProblemTypePopWindow extends PopupWindow implements AdapterView.OnItemClickListener {
    private View conentView;
    private Context context;
    private Activity activity;
    private ArrayList<ProblemTypeLeft> leftList;
    private int layoutHight = 0;
    private PopProblemTypeLeftAdapter popProblemTypeLeftAdapter;

    public ProblemTypePopWindow(Activity activity, Context context, ArrayList<ProblemTypeLeft> leftList, int layoutHight) {
        this.context = context;
        this.activity = activity;
        this.leftList = leftList;
        this.layoutHight = layoutHight;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        conentView = inflater.inflate(R.layout.pop_problem_type, null);
        ListView listViewLeft = (ListView) conentView.findViewById(R.id.pop_problem_type_leftList);
        ListView listViewRight = (ListView) conentView.findViewById(R.id.pop_problem_type_rightList);
        popProblemTypeLeftAdapter = new PopProblemTypeLeftAdapter(context, leftList);
        listViewLeft.setAdapter(popProblemTypeLeftAdapter);
        // 设置SelectPicPopupWindow的View
        this.setContentView(conentView);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        // 刷新状态
        this.update();
        listViewLeft.setOnItemClickListener(this);
        listViewRight.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (view.getId()) {
            case R.id.pop_problem_type_leftList:

                break;
            case R.id.pop_problem_type_rightList:

                break;
        }
    }

    public void showPopupWindow(View parent) {
        if (!this.isShowing()) {
            if (Build.VERSION.SDK_INT != 24) {
                // TODO: 2016/5/17 以下拉的方式显示，并且可以设置显示的位置
                this.showAsDropDown(parent);
            } else {
                this.showAtLocation(parent, Gravity.TOP, 0, layoutHight);
            }
        } else {
            this.dismiss();
        }
    }
}
