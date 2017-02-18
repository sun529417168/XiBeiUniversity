package cn.com.xibeiuniversity.xibeiuniversity.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.com.xibeiuniversity.xibeiuniversity.R;
import cn.com.xibeiuniversity.xibeiuniversity.base.BaseFragment;
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
    // 折线图
    private LineChartView lineChart;
    String[] date = {"一周", "一月", "半年"};// X轴的标注
    int[] score = {50, 42, 90};// 图表的数据点
    int[] score2 = {44, 62, 70};// 图表的数据点
    int[] score3 = {24, 72, 30};
    private List<PointValue> mPointValues = new ArrayList<PointValue>();
    private List<PointValue> mPointValues2 = new ArrayList<PointValue>();
    private List<PointValue> mPointValues3 = new ArrayList<PointValue>();
    private List<AxisValue> mAxisXValues = new ArrayList<AxisValue>();

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
        lineChart = (LineChartView) rootView.findViewById(R.id.statistical_lineChartView);
        lineChart.setInteractive(true);
        initLineView();
    }

    private void initLineView() {
        getAxisXLables();// 获取x轴的标注
        getAxisPoints();// 获取坐标点
        initLineChart();// 初始化
    }

    private void getAxisXLables() {
        for (int i = 0; i < date.length; i++) {
            mAxisXValues.add(new AxisValue(i).setLabel(date[i]));
        }
    }

    private void getAxisPoints() {
        for (int i = 0; i < score.length; i++) {
            mPointValues.add(new PointValue(i, score[i]));
        }
        for (int i = 0; i < score2.length; i++) {
            mPointValues2.add(new PointValue(i, score2[i]));
        }
        for (int i = 0; i < score3.length; i++) {
            mPointValues3.add(new PointValue(i, score3[i]));
        }
    }

    private void initLineChart() {
        List<Line> lines = new ArrayList<Line>();

        Line line = new Line(mPointValues).setColor(Color.parseColor("#FFCD41")); // 折线的颜色（橙色）
        // 折线图上每个数据点的形状 这里是圆形 （有三种 ：ValueShape.SQUARE ValueShape.CIRCLE
        // ValueShape.DIAMOND）
        line.setShape(ValueShape.CIRCLE);
        line.setCubic(false);// 曲线是否平滑，即是曲线还是折线
        line.setFilled(false);// 是否填充曲线的面积
        line.setHasLabels(true);// 曲线的数据坐标是否加上备注
        // 点击数据坐标提示数据（设置了这个line.setHasLabels(true);就无效）
        line.setHasLabelsOnlyForSelected(false);
        line.setHasLines(true);// 是否用线显示。如果为false 则没有曲线只有点显示
        line.setHasPoints(true);// 是否显示圆点 如果为false 则没有原点只有点显示（每个数据点都是个大的圆点）
        line.setPointRadius(4);
        line.setStrokeWidth(2);


        Line line2 = new Line(mPointValues2).setColor(Color.parseColor("#ed1c24"));
        line2.setShape(ValueShape.CIRCLE);
        line2.setCubic(false);// 曲线是否平滑，即是曲线还是折线
        line2.setFilled(false);// 是否填充曲线的面积
        line2.setHasLabels(true);// 曲线的数据坐标是否加上备注
        // 点击数据坐标提示数据（设置了这个line.setHasLabels(true);就无效）
        line2.setHasLabelsOnlyForSelected(false);
        line2.setHasLines(true);// 是否用线显示。如果为false 则没有曲线只有点显示
        line2.setHasPoints(true);// 是否显示圆点 如果为false 则没有原点只有点显示（每个数据点都是个大的圆点）
        line2.setPointRadius(4);
        line2.setStrokeWidth(2);


        Line line3 = new Line(mPointValues3).setColor(Color.parseColor("#5878ED"));
        line3.setShape(ValueShape.CIRCLE);
        line3.setCubic(false);// 曲线是否平滑，即是曲线还是折线
        line3.setFilled(false);// 是否填充曲线的面积
        line3.setHasLabels(true);// 曲线的数据坐标是否加上备注
        // 点击数据坐标提示数据（设置了这个line.setHasLabels(true);就无效）
        line3.setHasLabelsOnlyForSelected(false);
        line3.setHasLines(true);// 是否用线显示。如果为false 则没有曲线只有点显示
        line3.setHasPoints(true);// 是否显示圆点 如果为false 则没有原点只有点显示（每个数据点都是个大的圆点）
        line3.setPointRadius(4);
        line3.setStrokeWidth(2);


        lines.add(line);
        lines.add(line2);
        lines.add(line3);
        LineChartData data = new LineChartData();
        data.setValueLabelBackgroundColor(Color.TRANSPARENT);//此处设置坐标点旁边的文字背景
        data.setValueLabelBackgroundEnabled(false);
        data.setValueLabelsTextColor(Color.BLACK);  //此处设置坐标点旁边的文字颜色
        data.setLines(lines);

        // 坐标轴
        Axis axisX = new Axis(); // X轴
        axisX.setHasTiltedLabels(false); // X坐标轴字体是斜的显示还是直的，true是斜的显示
        axisX.setTextColor(Color.WHITE); // 设置字体颜色
        // axisX.setName("date"); //表格名称
        axisX.setTextSize(10);// 设置字体大小
        axisX.setTextColor(Color.parseColor("#333333"));
        axisX.setLineColor(Color.parseColor("#005286"));
        axisX.setMaxLabelChars(8); // 最多几个X轴坐标，意思就是你的缩放让X轴上数据的个数7<=x<=mAxisXValues.length
        axisX.setValues(mAxisXValues); // 填充X轴的坐标名称
        data.setAxisXBottom(axisX); // x 轴在底部
        // data.setAxisXTop(axisX); //x 轴在顶部
        axisX.setHasLines(true); // x 轴分割线

        // Y轴是根据数据的大小自动设置Y轴上限(在下面我会给出固定Y轴数据个数的解决方案)
        Axis axisY = new Axis(); // Y轴
        axisY.setName("");// y轴标注
        axisY.setTextSize(10);// 设置字体大小
        axisY.setTextColor(Color.parseColor("#333333"));
        axisY.setLineColor(Color.parseColor("#005286"));
        data.setAxisYLeft(axisY); // Y轴设置在左边
        // data.setAxisYRight(axisY); //y轴设置在右边
        // 设置行为属性，支持缩放、滑动以及平移
        lineChart.setInteractive(false);
        lineChart.setZoomType(ZoomType.HORIZONTAL);
        lineChart.setMaxZoom((float) 2);// 最大方法比例
        lineChart.setContainerScrollEnabled(true,
                ContainerScrollType.HORIZONTAL);
        lineChart.setLineChartData(data);
        lineChart.setVisibility(View.VISIBLE);
        /**
         * 注：下面的7，10只是代表一个数字去类比而已
         * 当时是为了解决X轴固定数据个数。见（http://forum.xda-developers.com
         * /tools/programming/library
         * -hellocharts-charting-library-t2904456/page2）;
         */
        Viewport v = new Viewport(lineChart.getMaximumViewport());
        v.left = 0;
        v.right = 7;
        lineChart.setCurrentViewport(v);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
          mPointValues = new ArrayList<PointValue>();
          mPointValues2 = new ArrayList<PointValue>();
          mPointValues3 = new ArrayList<PointValue>();
          mAxisXValues = new ArrayList<AxisValue>();
    }

    @Override
    public void onClick(View v) {

    }
}
