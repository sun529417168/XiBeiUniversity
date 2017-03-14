package cn.com.xibeiuniversity.xibeiuniversity.fragment.statistical;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import cn.com.xibeiuniversity.xibeiuniversity.R;
import cn.com.xibeiuniversity.xibeiuniversity.base.BaseFragment;
import cn.com.xibeiuniversity.xibeiuniversity.bean.statistical.TaskStatisticalBean;
import cn.com.xibeiuniversity.xibeiuniversity.interfaces.StatisticalInfoInterface;
import cn.com.xibeiuniversity.xibeiuniversity.utils.MyRequest;
import cn.com.xibeiuniversity.xibeiuniversity.weight.BarChartView;
import cn.com.xibeiuniversity.xibeiuniversity.weight.DoubleDatePickerDialog;

/**
 * 文件名：StatisticalNoticeFragment
 * 描    述：统计通知的fragment
 * 作    者：stt
 * 时    间：2017.3.7
 * 版    本：V1.0.3
 */

public class StatisticalNoticeFragment extends BaseFragment implements StatisticalInfoInterface, View.OnClickListener {
    private Context context;
    private TextView weekText, monthText, quarterText, halfYearText, screenText;
    private TextView[] textviews;
    private PieChart mPieChart;
    Random random;

    @Override
    protected View setView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = inflater.getContext();
        View view = inflater.inflate(R.layout.fragment_statistical_notice, null);
        return view;
    }

    @Override
    protected void setDate() {
        random = new java.util.Random();// 定义随机类
        request("", "", 1);
    }

    private void request(String startTime, String endTime, int type) {
        MyRequest.statisticalTaskRequest(context, this, 2, startTime, endTime, type);
        MyRequest.statisticalTaskRequest(context, this, 1, startTime, endTime, type);
    }

    @Override
    protected void init(View rootView) {
        weekText = (TextView) rootView.findViewById(R.id.statistical_week);
        monthText = (TextView) rootView.findViewById(R.id.statistical_month);
        quarterText = (TextView) rootView.findViewById(R.id.statistical_quarter);
        halfYearText = (TextView) rootView.findViewById(R.id.statistical_halfYear);
        screenText = (TextView) rootView.findViewById(R.id.statistical_screen);
        weekText.setOnClickListener(this);
        monthText.setOnClickListener(this);
        quarterText.setOnClickListener(this);
        halfYearText.setOnClickListener(this);
        screenText.setOnClickListener(this);
        textviews = new TextView[]{weekText, monthText, quarterText, halfYearText, screenText};
        setTextBack(weekText);
        /*****************饼状图*******************/
        //饼状图
        mPieChart = (PieChart) rootView.findViewById(R.id.statistical_notice_pieChart);
    }

    private void setTextBack(TextView view) {
        for (int i = 0; i < textviews.length; i++) {
            if (view.getId() == textviews[i].getId()) {
                textviews[i].setTextColor(ContextCompat.getColor(context, R.color.blue));
            } else {
                textviews[i].setTextColor(ContextCompat.getColor(context, R.color.black));
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.statistical_week:
                request("", "", 1);
                setTextBack(weekText);
                break;
            case R.id.statistical_month:
                request("", "", 2);
                setTextBack(monthText);
                break;
            case R.id.statistical_quarter:
                request("", "", 3);
                setTextBack(quarterText);
                break;
            case R.id.statistical_halfYear:
                request("", "", 4);
                setTextBack(halfYearText);
                break;
            case R.id.statistical_screen:
                Calendar c = Calendar.getInstance();
                new DoubleDatePickerDialog(context, 5, new DoubleDatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker startDatePicker, int startYear, int startMonthOfYear,
                                          int startDayOfMonth, DatePicker endDatePicker, int endYear, int endMonthOfYear,
                                          int endDayOfMonth) {
                        String start = startYear + "-" + startMonthOfYear + 1 + "-" + startDayOfMonth;
                        String end = endYear + "-" + endMonthOfYear + 1 + "-" + endDayOfMonth;
                        request(start, end, 0);
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE), true).show();
                setTextBack(screenText);
                break;
        }
    }

    @Override
    public void getStatisticalInfo(Object bean, int type) {
        if (type == 3) {
            ArrayList<TaskStatisticalBean.ListBean> beanList = (ArrayList<TaskStatisticalBean.ListBean>) bean;
            ArrayList<PieEntry> entries = new ArrayList<>();
            entries.add(new PieEntry(random.nextInt(10) + 2, "会议"));
            entries.add(new PieEntry(random.nextInt(10) + 2, "维修"));
            entries.add(new PieEntry(random.nextInt(10) + 2, "安全"));
            entries.add(new PieEntry(random.nextInt(10) + 2, "其他"));
            //设置数据
            initPieChart(entries);
        }
    }

    private void initPieChart(ArrayList<PieEntry> entries) {
        mPieChart.setUsePercentValues(true);
        mPieChart.getDescription().setEnabled(false);
        mPieChart.setExtraOffsets(5, 10, 5, 5);

        mPieChart.setDragDecelerationFrictionCoef(0.95f);
        /**
         * 设置饼图中心是否是空心的
         true 中间是空心的，环形图
         false 中间是实心的 饼图
         */
        mPieChart.setDrawHoleEnabled(false);
        mPieChart.setHoleColor(Color.WHITE);

        mPieChart.setTransparentCircleColor(Color.WHITE);
        mPieChart.setTransparentCircleAlpha(110);

        mPieChart.setHoleRadius(58f);
        mPieChart.setTransparentCircleRadius(61f);

        mPieChart.setDrawCenterText(false);

        mPieChart.setRotationAngle(0);
        // 触摸旋转
        mPieChart.setRotationEnabled(true);
        mPieChart.setHighlightPerTapEnabled(true);

        //变化监听
//        mPieChart.setOnChartValueSelectedListener(this);
        //设置数据
        setData(entries);

        mPieChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);

        Legend l = mPieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(2f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);
        //设置比例块换行...
        l.setWordWrapEnabled(true);
        l.setDirection(Legend.LegendDirection.LEFT_TO_RIGHT);

        // 输入标签样式
        mPieChart.setEntryLabelColor(Color.WHITE);
        mPieChart.setEntryLabelTextSize(12f);
    }

    //设置数据
    private void setData(ArrayList<PieEntry> entries) {
        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setSliceSpace(2f);
        dataSet.setSelectionShift(5f);

        //数据和颜色
        ArrayList<Integer> colors = new ArrayList<Integer>();
        colors.add(Color.parseColor("#FC585C"));
        colors.add(Color.parseColor("#4C5060"));
        colors.add(Color.parseColor("#44BCBC"));
        colors.add(Color.parseColor("#FCB45C"));
        colors.add(ColorTemplate.getHoloBlue());
        dataSet.setColors(colors);
        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        mPieChart.setData(data);
        mPieChart.highlightValues(null);
        //刷新
        mPieChart.invalidate();
    }
}
