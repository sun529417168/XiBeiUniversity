package cn.com.xibeiuniversity.xibeiuniversity.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.com.xibeiuniversity.xibeiuniversity.R;
import cn.com.xibeiuniversity.xibeiuniversity.base.BaseFragment;
import cn.com.xibeiuniversity.xibeiuniversity.fragment.statistical.StatisticalNoticeFragment;
import cn.com.xibeiuniversity.xibeiuniversity.fragment.statistical.StatisticalProblemFragment;
import cn.com.xibeiuniversity.xibeiuniversity.fragment.statistical.StatisticalTaskFragment;
import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

/**
 * 文件名：StatisticalFragment
 * 描    述：统计界面
 * 作    者：stt
 * 时    间：2016.12.30
 * 版    本：V1.0.0
 */

public class StatisticalFragment extends BaseFragment implements View.OnClickListener {
    private Context context;
    private TextView titleName;//标题名称
    private ViewPager mPaper;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mFragments = new ArrayList<Fragment>();
    private TextView taskText, noticeText, problemText;
    private TextView[] textviews;

    @Override
    protected View setView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = inflater.getContext();
        View view = inflater.inflate(R.layout.fragment_statistical, null);
        return view;
    }

    @Override
    protected void setDate() {

    }

    @Override
    protected void init(View rootView) {
        titleName = (TextView) rootView.findViewById(R.id.title_name);
        titleName.setText("统计查询");
        taskText = (TextView) rootView.findViewById(R.id.statistical_task);
        noticeText = (TextView) rootView.findViewById(R.id.statistical_notice);
        problemText = (TextView) rootView.findViewById(R.id.statistical_problem);
        mPaper = (ViewPager) rootView.findViewById(R.id.statistical_viewPager);
        textviews = new TextView[]{taskText, noticeText, problemText};
        setTextBack(taskText);
        taskText.setOnClickListener(this);
        noticeText.setOnClickListener(this);
        problemText.setOnClickListener(this);
        StatisticalTaskFragment taskFragment = new StatisticalTaskFragment();
        StatisticalNoticeFragment noticeFragment = new StatisticalNoticeFragment();
        StatisticalProblemFragment problemFragment = new StatisticalProblemFragment();
        mFragments.add(taskFragment);
        mFragments.add(noticeFragment);
        mFragments.add(problemFragment);
        mAdapter = new FragmentPagerAdapter(getChildFragmentManager()) {

            @Override
            public int getCount() {
                return mFragments.size();
            }

            @Override
            public Fragment getItem(int arg0) {
                return mFragments.get(arg0);
            }
        };
        mPaper.setAdapter(mAdapter);
        mPaper.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            private int currentIndex;

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        setTextBack(taskText);
                        break;
                    case 1:
                        setTextBack(noticeText);
                        break;
                    case 2:
                        setTextBack(problemText);
                        break;
                }
                currentIndex = position;
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.statistical_task:
                setTextBack(taskText);
                mPaper.setCurrentItem(0);
                break;
            case R.id.statistical_notice:
                setTextBack(noticeText);
                mPaper.setCurrentItem(1);
                break;
            case R.id.statistical_problem:
                setTextBack(problemText);
                mPaper.setCurrentItem(2);
                break;
            default:
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
