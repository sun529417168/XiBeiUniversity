package cn.com.xibeiuniversity.xibeiuniversity.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import cn.com.xibeiuniversity.xibeiuniversity.R;
import cn.com.xibeiuniversity.xibeiuniversity.activity.problem.AddProblemActivity;
import cn.com.xibeiuniversity.xibeiuniversity.adapter.problem.ProblemAdapter;
import cn.com.xibeiuniversity.xibeiuniversity.adapter.task.TaskAdapter;
import cn.com.xibeiuniversity.xibeiuniversity.base.BaseFragment;
import cn.com.xibeiuniversity.xibeiuniversity.bean.problem.ProblemBean;
import cn.com.xibeiuniversity.xibeiuniversity.bean.task.TaskBean;
import cn.com.xibeiuniversity.xibeiuniversity.interfaces.ProblemListInterface;
import cn.com.xibeiuniversity.xibeiuniversity.interfaces.ProblemTypeInterface;
import cn.com.xibeiuniversity.xibeiuniversity.utils.MyRequest;
import cn.com.xibeiuniversity.xibeiuniversity.utils.MyUtils;
import cn.com.xibeiuniversity.xibeiuniversity.utils.PopWindowUtils;
import cn.com.xibeiuniversity.xibeiuniversity.utils.ToastUtil;

/**
 * 文件名：ProblemFragment
 * 描    述：问题上报界面
 * 作    者：stt
 * 时    间：2016.12.30
 * 版    本：V1.0.0
 */

public class ProblemFragment extends BaseFragment implements View.OnClickListener, ProblemTypeInterface, ProblemListInterface {
    private Context context;
    private TextView titleName;//标题名称
    private TextView[] textviews;
    //刷新控件
    private PullToRefreshListView mPullRefreshListView;
    private ProblemBean problemBean = new ProblemBean();
    private ProblemAdapter problemAdapter;
    private LinearLayout addProblem;
    /**
     * pop的类型，时间，状态
     */
    private LinearLayout typeLayout, timeLayout, stateLayout;
    private TextView typeText, timeText, stateText;
    private PopupWindow popupWindow;
    private int state = 0;//状态
    private int pageindex = 1;//页码数
    private List<ProblemBean.RowsBean> rowsBeanList = new ArrayList();


    @Override
    protected View setView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = inflater.getContext();
        View view = inflater.inflate(R.layout.fragment_problem, null);
        return view;
    }

    @Override
    protected void setDate() {

    }

    private void requestData(int pageindex, int state) {
        MyRequest.problemListRequest(context, this, pageindex, "2", state);
    }

    @Override
    protected void init(View rootView) {
        titleName = (TextView) rootView.findViewById(R.id.title_name);
        titleName.setText("问题上报");
        mPullRefreshListView = (PullToRefreshListView) rootView.findViewById(R.id.problem_refresh_list);
        mPullRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        addProblem = (LinearLayout) rootView.findViewById(R.id.problem_addInfo);
        addProblem.setVisibility(View.VISIBLE);
        addProblem.setOnClickListener(this);

        typeLayout = (LinearLayout) rootView.findViewById(R.id.problem_layout_type);
        timeLayout = (LinearLayout) rootView.findViewById(R.id.problem_layout_time);
        stateLayout = (LinearLayout) rootView.findViewById(R.id.problem_layout_state);
        typeText = (TextView) rootView.findViewById(R.id.problem_layout_type_text);
        timeText = (TextView) rootView.findViewById(R.id.problem_layout_time_text);
        stateText = (TextView) rootView.findViewById(R.id.problem_layout_state_text);
        typeLayout.setOnClickListener(this);
        timeLayout.setOnClickListener(this);
        stateLayout.setOnClickListener(this);

        mPullRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(
                    PullToRefreshBase<ListView> refreshView) {
                Log.e("TAG", "onPullDownToRefresh");
                // 这里写下拉刷新的任务
                problemAdapter.notifyDataSetChanged();
                mPullRefreshListView.onRefreshComplete();
            }

            @Override
            public void onPullUpToRefresh(
                    PullToRefreshBase<ListView> refreshView) {
                Log.e("TAG", "onPullUpToRefresh");
                // 这里写上拉加载更多的任务
                problemAdapter.notifyDataSetChanged();
                mPullRefreshListView.onRefreshComplete();
            }
        });
    }


    private void setTextBack(TextView view) {
        for (int i = 0; i < textviews.length; i++) {
            if (view.getId() == textviews[i].getId()) {
                textviews[i].setTextColor(ContextCompat.getColor(context, R.color.blue));
                textviews[i].setBackgroundResource(R.color.gray_3);
            } else {
                textviews[i].setTextColor(ContextCompat.getColor(context, R.color.black));
                textviews[i].setBackgroundResource(R.color.white);
            }
        }
    }

    @Override
    public void onClick(View v) {
        List list;
        switch (v.getId()) {
            case R.id.problem_addInfo:
                Intent intent = new Intent(context, AddProblemActivity.class);
                startActivity(intent);
                break;
            case R.id.problem_layout_type:
                list = new ArrayList();
                list.add("全部");
                list.add("事件类型");
                list.add("部件类型");
                popupWindow = PopWindowUtils.showProblemPop(getActivity(), (ProblemTypeInterface) this, typeLayout, list, 0);
                break;
            case R.id.problem_layout_time:
                list = new ArrayList();
                list.add("全部");
                list.add("一天");
                list.add("一周");
                list.add("一个月");
                popupWindow = PopWindowUtils.showProblemPop(getActivity(), (ProblemTypeInterface) this, typeLayout, list, 1);
                break;
            case R.id.problem_layout_state:
                list = new ArrayList();
                list.add("全部");
                list.add("已上报");
                list.add("已收到");
                popupWindow = PopWindowUtils.showProblemPop(getActivity(), (ProblemTypeInterface) this, typeLayout, list, 2);
                break;
        }
    }

    @Override
    public void getProblemType(int type, String typeName) {
        switch (type) {
            case 0:
                typeText.setText(typeName);
                break;
            case 1:
                timeText.setText(typeName);
                break;
            case 2:
                stateText.setText(typeName);
                break;
        }
        if (popupWindow.isShowing()) {
            popupWindow.dismiss();
        }
    }

    @Override
    public void showTaskList(ProblemBean problemBean) {
        if (pageindex == 1) {
            rowsBeanList = problemBean.getRows();
            problemAdapter = new ProblemAdapter(context, problemBean.getRows());
            mPullRefreshListView.setAdapter(problemAdapter);
        } else if (pageindex > 1 && problemBean.getRows().size() != 0) {
            rowsBeanList.addAll(problemBean.getRows());
            problemAdapter = new ProblemAdapter(context, problemBean.getRows());
            mPullRefreshListView.setAdapter(problemAdapter);
        } else if (pageindex > 1 && problemBean.getRows().size() == 0) {
            ToastUtil.show(context, "没有更多数据了");
        }
    }
}
