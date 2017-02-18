package cn.com.xibeiuniversity.xibeiuniversity.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;

import cn.com.xibeiuniversity.xibeiuniversity.R;
import cn.com.xibeiuniversity.xibeiuniversity.adapter.notice.NoticeAdapter;
import cn.com.xibeiuniversity.xibeiuniversity.base.BaseFragment;
import cn.com.xibeiuniversity.xibeiuniversity.bean.NoticeBean;
import cn.com.xibeiuniversity.xibeiuniversity.utils.MyUtils;

/**
 * 文件名：NoticeFragment
 * 描    述：通知界面
 * 作    者：stt
 * 时    间：2016.12.30
 * 版    本：V1.0.0
 */

public class NoticeFragment extends BaseFragment implements View.OnClickListener {
    private Context context;
    private TextView titleName;//标题名称
    /**
     * 全部，一天，一个星期，一个月
     */
    private TextView noticeAll, noticeOneDay, noticeOneWeek, noticeOneMonth;
    private TextView[] textviews;
    //刷新控件
    private PullToRefreshListView mPullRefreshListView;
    private ArrayList<NoticeBean> noticeList = new ArrayList<>();
    private NoticeAdapter noticeAdapter;

    @Override
    protected View setView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = inflater.getContext();
        View view = inflater.inflate(R.layout.fragment_notice, null);
        return view;
    }

    @Override
    protected void setDate() {

    }

    @Override
    protected void init(View rootView) {
        titleName = (TextView) rootView.findViewById(R.id.title_name);
        titleName.setText("通知公告");
        mPullRefreshListView = (PullToRefreshListView) rootView.findViewById(R.id.notice_refresh_list);
        mPullRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        noticeAll = (TextView) rootView.findViewById(R.id.notice_all);
        noticeOneDay = (TextView) rootView.findViewById(R.id.notice_oneDay);
        noticeOneWeek = (TextView) rootView.findViewById(R.id.notice_oneWeek);
        noticeOneMonth = (TextView) rootView.findViewById(R.id.notice_oneMonth);
        textviews = new TextView[]{noticeAll, noticeOneDay, noticeOneWeek, noticeOneMonth};
        setTextBack(noticeAll);
        noticeAll.setOnClickListener(this);
        noticeOneDay.setOnClickListener(this);
        noticeOneWeek.setOnClickListener(this);
        noticeOneMonth.setOnClickListener(this);
        noticeList = (ArrayList<NoticeBean>) JSON.parseArray(MyUtils.getFromAssets(context, "noticeList.txt"), NoticeBean.class);
        noticeAdapter = new NoticeAdapter(context, noticeList);
        mPullRefreshListView.setAdapter(noticeAdapter);
        mPullRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(
                    PullToRefreshBase<ListView> refreshView) {
                Log.e("TAG", "onPullDownToRefresh");
                // 这里写下拉刷新的任务
                noticeAdapter.notifyDataSetChanged();
                mPullRefreshListView.onRefreshComplete();
            }

            @Override
            public void onPullUpToRefresh(
                    PullToRefreshBase<ListView> refreshView) {
                Log.e("TAG", "onPullUpToRefresh");
                // 这里写上拉加载更多的任务
                noticeAdapter.notifyDataSetChanged();
                mPullRefreshListView.onRefreshComplete();
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.notice_all:
                setTextBack(noticeAll);
                break;
            case R.id.notice_oneDay:
                setTextBack(noticeOneDay);
                break;
            case R.id.notice_oneWeek:
                setTextBack(noticeOneWeek);
                break;
            case R.id.notice_oneMonth:
                setTextBack(noticeOneMonth);
                break;
        }
    }

    private void setTextBack(TextView view) {
        for (int i = 0; i < textviews.length; i++) {
            if (view.getId() == textviews[i].getId()) {
                textviews[i].setTextColor(ContextCompat.getColor(context, R.color.white));
                textviews[i].setBackgroundResource(R.color.blue2);
            } else {
                textviews[i].setTextColor(ContextCompat.getColor(context, R.color.blue));
                textviews[i].setBackgroundResource(R.color.white);
            }
        }
    }
}
