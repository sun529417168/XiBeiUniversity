package cn.com.xibeiuniversity.xibeiuniversity.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;

import cn.com.xibeiuniversity.xibeiuniversity.R;
import cn.com.xibeiuniversity.xibeiuniversity.adapter.notice.NoticeAdapter;
import cn.com.xibeiuniversity.xibeiuniversity.base.BaseFragment;
import cn.com.xibeiuniversity.xibeiuniversity.bean.notice.NoticeBean;
import cn.com.xibeiuniversity.xibeiuniversity.interfaces.NoticeListInterface;
import cn.com.xibeiuniversity.xibeiuniversity.utils.MyRequest;
import cn.com.xibeiuniversity.xibeiuniversity.utils.MyUtils;

/**
 * 文件名：NoticeFragment
 * 描    述：通知界面
 * 作    者：stt
 * 时    间：2016.12.30
 * 版    本：V1.0.0
 */

public class NoticeFragment extends BaseFragment implements View.OnClickListener, NoticeListInterface {
    private Context context;
    private TextView titleName;//标题名称
    /**
     * 全部，一天，一个星期，一个月
     */
    private TextView noticeAll, noticeOneDay, noticeOneWeek, noticeOneMonth;
    private TextView[] textviews;
    //刷新控件
    private PullToRefreshListView mPullRefreshListView;
    private ArrayList<NoticeBean.RowsBean> noticeList = new ArrayList<>();
    private NoticeAdapter noticeAdapter;
    private RelativeLayout nothing;
    private int timeNum = 0;

    @Override
    protected View setView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = inflater.getContext();
        View view = inflater.inflate(R.layout.fragment_notice, null);
        return view;
    }

    @Override
    protected void setDate() {
        request(timeNum);
    }

    private void request(int searchTime) {
        MyRequest.getNoticeListRequest(context, this, searchTime);
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
        nothing = (RelativeLayout) rootView.findViewById(R.id.notice_nothing);
        setTextBack(noticeAll);
        noticeAll.setOnClickListener(this);
        noticeOneDay.setOnClickListener(this);
        noticeOneWeek.setOnClickListener(this);
        noticeOneMonth.setOnClickListener(this);
        mPullRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                Log.e("TAG", "onPullDownToRefresh");
                // 这里写下拉刷新的任务
                request(timeNum);
                noticeAdapter.notifyDataSetChanged();
                mPullRefreshListView.onRefreshComplete();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                Log.e("TAG", "onPullUpToRefresh");
                // 这里写上拉加载更多的任务
                request(timeNum);
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
                timeNum = 0;
                request(timeNum);
                break;
            case R.id.notice_oneDay:
                setTextBack(noticeOneDay);
                timeNum = 1;
                request(timeNum);
                break;
            case R.id.notice_oneWeek:
                setTextBack(noticeOneWeek);
                timeNum = 2;
                request(timeNum);
                break;
            case R.id.notice_oneMonth:
                setTextBack(noticeOneMonth);
                timeNum = 3;
                request(timeNum);
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

    @Override
    public void getNoticeList(NoticeBean noticeBean) {
        if (noticeBean.getRows().size() == 0) {
            mPullRefreshListView.setVisibility(View.GONE);
            nothing.setVisibility(View.VISIBLE);
        } else {
            mPullRefreshListView.setVisibility(View.VISIBLE);
            nothing.setVisibility(View.GONE);
            noticeList = (ArrayList<NoticeBean.RowsBean>) noticeBean.getRows();
            noticeAdapter = new NoticeAdapter(context, noticeList);
            mPullRefreshListView.setAdapter(noticeAdapter);
            noticeAdapter.notifyDataSetChanged();
        }
    }
}
