package cn.com.xibeiuniversity.xibeiuniversity.fragment.statistical;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
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
 * 文件名：StatisticalProblemFragment
 * 描    述：统计问题的fragment
 * 作    者：stt
 * 时    间：2017.3.7
 * 版    本：V1.0.3
 */

public class StatisticalProblemFragment extends BaseFragment implements StatisticalInfoInterface, View.OnClickListener {
    private Context context;
    private PieChart mPieChartBuJian, mPieChartShiJian;
    private BarChart barChartView;
    private TextView weekText, monthText, quarterText, halfYearText, screenText;
    private TextView[] textviews;
    private int[] mBarColors = new int[]{Color.parseColor("#29B6F6"),
            Color.YELLOW, Color.parseColor("#32B16C"),
            Color.parseColor("#E9616B"), Color.parseColor("#C90110"), Color.YELLOW, Color.parseColor("#32B16C")};

    @Override
    protected View setView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = inflater.getContext();
        View view = inflater.inflate(R.layout.fragment_statistical_problem, null);
        return view;
    }

    @Override
    protected void setDate() {
        request("", "", 1);
    }

    private void request(String startTime, String endTime, int type) {
        MyRequest.statisticalProblemRequest(context, this, 0, startTime, endTime, type);
        MyRequest.statisticalProblemRequest(context, this, 1, startTime, endTime, type);
        MyRequest.statisticalProblemRequest(context, this, 2, startTime, endTime, type);
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
        /*****************柱状图*******************/
        barChartView = (BarChart) rootView.findViewById(R.id.statistical_problem_barChart);
        barChartView.setDrawBarShadow(false);
        barChartView.setDrawValueAboveBar(true);

        barChartView.getDescription().setEnabled(false);

        // 如果60多个条目显示在图表,将没有值
        barChartView.setMaxVisibleValueCount(60);

        // 扩展现在只能分别在x轴和y轴
        barChartView.setPinchZoom(false);

        barChartView.setDrawGridBackground(false);
        // mChart.setDrawYLabels(false);
        barChartView.getAxisRight().setEnabled(false); //隐藏Y轴右边轴线，此时标签数字也隐
        /*****************饼状图*******************/
        //饼状图
        mPieChartBuJian = (PieChart) rootView.findViewById(R.id.statistical_problem_buJian_pieChart);
        mPieChartShiJian = (PieChart) rootView.findViewById(R.id.statistical_problem_shiJian_pieChart);
    }

    /**
     * 柱状图数据
     */
    private void initBarChart(ArrayList<TaskStatisticalBean.ListBean> listBean) {

        XAxis xAxis = barChartView.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setLabelCount(listBean.size() > 5 ? 5 : listBean.size());
        xAxis.setValueFormatter(new CustomXValueFormatter(listBean));

        YAxis leftAxis = barChartView.getAxisLeft();
        leftAxis.setLabelCount(8, false);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)


        Legend l = barChartView.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setForm(Legend.LegendForm.SQUARE);
        l.setFormSize(9f);
        l.setTextSize(11f);
        l.setXEntrySpace(4f);

        setBarData(listBean);
        barChartView.animateY(1000);
    }

    private void setBarData(ArrayList<TaskStatisticalBean.ListBean> listBean) {
        ArrayList<BarEntry> yVals = new ArrayList<>();
        for (int i = 0; i < listBean.size(); i++) {
            yVals.add(new BarEntry(i, listBean.get(i).getValue()));
        }


        BarDataSet set1;
        if (barChartView.getData() != null && barChartView.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) barChartView.getData().getDataSetByIndex(0);
            set1.setValues(yVals);
            barChartView.getData().notifyDataChanged();
            barChartView.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yVals, "问题数量");
            set1.setColors(mBarColors);

            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);
            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
            data.setBarWidth(0.5f);
            // mChart.setVisibleXRangeMaximum(5);
            barChartView.setData(data);
            barChartView.setTouchEnabled(true);
            barChartView.setDragEnabled(true);
            barChartView.setScaleEnabled(false);
            barChartView.setVisibleXRangeMaximum(listBean.size() > 5 ? 5 : listBean.size());
            // mChart.setVisibleXRange(5,9);
        }
    }

    /**
     * 饼状图数据
     *
     * @param entries
     * @param mPieChart
     */
    private void initPieChart(ArrayList<PieEntry> entries, PieChart mPieChart) {
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
        setData(entries, mPieChart);

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

    //设置饼状图数据
    private void setData(ArrayList<PieEntry> entries, PieChart mPieChart) {
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
                // 最后一个false表示不显示日期，如果要显示日期，最后参数可以是true或者不用输入
                new DoubleDatePickerDialog(context, 5,
                        new DoubleDatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker startDatePicker, int startYear, int startMonthOfYear, int startDayOfMonth, DatePicker endDatePicker, int endYear, int endMonthOfYear, int endDayOfMonth) {
                                String start = String.format("%d-%d-%d", startYear, startMonthOfYear + 1, startDayOfMonth);
                                String end = String.format("%d-%d-%d", endYear, endMonthOfYear + 1, endDayOfMonth);
                                request(start, end, 0);
                                Log.i("calendarData", start + "====" + end);
                            }
                        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c
                        .get(Calendar.DATE), false).show();
                setTextBack(screenText);
                break;
        }
    }

    @Override
    public void getStatisticalInfo(Object bean, int type) {
        switch (type) {
            case 0:
                initBarChart((ArrayList<TaskStatisticalBean.ListBean>) bean);
                break;
            case 1:
                ArrayList<PieEntry> buEntries = new ArrayList<>();
                for (TaskStatisticalBean.ListBean taskBean : (ArrayList<TaskStatisticalBean.ListBean>) bean) {
                    if (taskBean.getValue() != 0)
                        buEntries.add(new PieEntry(taskBean.getValue() == 1 ? 1.1f : taskBean.getValue(), taskBean.getName()));
                }
                //设置数据
                initPieChart(buEntries, mPieChartBuJian);
                break;
            case 2:
                ArrayList<PieEntry> shiEntries = new ArrayList<>();
                for (TaskStatisticalBean.ListBean taskBean : (ArrayList<TaskStatisticalBean.ListBean>) bean) {
                    if (taskBean.getValue() != 0)
                        shiEntries.add(new PieEntry(taskBean.getValue() == 1 ? 1.1f : taskBean.getValue(), taskBean.getName()));
                }
                //设置数据
                initPieChart(shiEntries, mPieChartShiJian);
                break;
        }
    }

    class CustomXValueFormatter implements IAxisValueFormatter {

        private ArrayList<TaskStatisticalBean.ListBean> labels = new ArrayList<>();

        /**
         * @param labels 要显示的标签字符数组
         */

        public CustomXValueFormatter(ArrayList<TaskStatisticalBean.ListBean> labels) {
            this.labels = labels;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            int i = (int) value;
            if (i == value || (i - value) < 0.05) {
                return labels.get((int) value % labels.size()).getName();
//                return labels.get((int) value);
            } else {
                return "";
            }

        }

        @Override
        public int getDecimalDigits() {
            return 0;
        }
    }
}
