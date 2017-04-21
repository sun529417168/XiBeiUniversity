package cn.com.xibeiuniversity.xibeiuniversity.adapter.task;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.xibeiuniversity.xibeiuniversity.R;
import cn.com.xibeiuniversity.xibeiuniversity.activity.task.TaskDetailActivity;
import cn.com.xibeiuniversity.xibeiuniversity.base.MyBaseAdapter;
import cn.com.xibeiuniversity.xibeiuniversity.bean.task.TaskBean;
import cn.com.xibeiuniversity.xibeiuniversity.config.UrlConfig;
import cn.com.xibeiuniversity.xibeiuniversity.okhttps.OkHttpUtils;
import cn.com.xibeiuniversity.xibeiuniversity.okhttps.callback.GenericsCallback;
import cn.com.xibeiuniversity.xibeiuniversity.okhttps.utils.JsonGenericsSerializator;
import cn.com.xibeiuniversity.xibeiuniversity.utils.GetWeek;
import cn.com.xibeiuniversity.xibeiuniversity.utils.MyUtils;
import okhttp3.Call;

/**
 * 文件名：TaskAdapter
 * 描    述：任务列表的适配器
 * 作    者：stt
 * 时    间：2017.1.3
 * 版    本：V1.0.0
 */

public class TaskAdapter extends MyBaseAdapter {
    private List<TaskBean.RowsBean> list = new ArrayList<>();

    public TaskAdapter(Context context, List list) {
        super(context, list);
        this.list = (List<TaskBean.RowsBean>) list;
    }

    @Override
    public int getContentView() {
        return R.layout.item_task;
    }

    @Override
    public void onInitView(View view, final int position) {
        final TaskBean.RowsBean rowsBean = list.get(position);
        TextView number = get(view, R.id.item_task_number);  // 编号
        TextView state = get(view, R.id.item_task_state);  // 状态
        TextView date = get(view, R.id.item_task_date);  // 日期
        ImageView imageView = get(view, R.id.item_task_image);  // 图片
        TextView name = get(view, R.id.item_task_name);  // 名称
        TextView sender = get(view, R.id.item_task_sender);  // 发送人
        RelativeLayout taskCalendarLayout=get(view,R.id.item_task_calendar);//日历布局
        LinearLayout  taskPictureLayout=get(view,R.id.item_task_picture);//图片布局
        TextView task_calendar_year_month=get(view,R.id.task_year_month);//获取年月
        TextView task_calendar_week=get(view,R.id.task_week);//获取星期
        TextView task_calendar_date=get(view,R.id.task_day);//获取日期
        final TextView info = get(view, R.id.item_task_info);  // 具体内容
        final TextView[] views = {number, date, name, sender, info};
        /**
         *删除日期中的小时
         **/
        String firstDate = rowsBean.getCreateDateApi().split(" ")[0];
        //获取年和月
        String task_text_year_month=firstDate.split("/")[0]+"年"+firstDate.split("/")[1]+"月";
        //获取日期
        String task_text_date=firstDate.split("/")[2];
        //获得星期
        String task_week = GetWeek.getWeek(firstDate);
        //获得农历日期
        String task_lunar= MyUtils.getLunar(firstDate);
        /**
         * 赋值
         */
        number.setText("T" + rowsBean.getTaskSno().substring(9));
        for (TaskBean.RowsBean.ImageListBean imageBean : rowsBean.getImageList()) {
            if (imageBean.getAttachmentType() == 1) {
                if (imageBean.getFileUrl() != null) {
                    taskCalendarLayout.setVisibility(View.GONE);
                    taskPictureLayout.setVisibility(View.VISIBLE);
                    ImageLoader.getInstance().displayImage(imageBean.getFileUrl(), imageView);
                } else {
                    taskPictureLayout.setVisibility(View.GONE);
                    taskCalendarLayout.setVisibility(View.VISIBLE);
                }
            }
        }
        if (rowsBean.getImageList().size() == 0) {
            taskPictureLayout.setVisibility(View.GONE);
            taskCalendarLayout.setVisibility(View.VISIBLE);
        }
        if (rowsBean.isIsCheck()) {
            setTextColor(views);
        }
        if (rowsBean.getTaskAssignedState() == 1) {
            state.setText("未查阅");
            state.setBackgroundResource(R.color.red);
        }
        if (rowsBean.getTaskAssignedState() == 2) {
            state.setText("处理中");
            state.setBackgroundResource(R.color.yellow);
        }
        if (rowsBean.getTaskAssignedState() == 3) {
            state.setText("已完成");
            state.setBackgroundResource(R.color.green);
        }
        if (rowsBean.getTaskAssignedState() == 4) {
            state.setText("未完成");
            state.setBackgroundResource(R.color.fenSe);
        }
        date.setText(firstDate + " " + task_week);
        task_calendar_year_month.setText(task_text_year_month);
        task_calendar_week.setText(task_week);
        task_calendar_date.setText(task_text_date);
        name.setText("名称：" + rowsBean.getTaskName());
        sender.setText("发送人：" + rowsBean.getPersonName());
        info.setText("任务内容：" + rowsBean.getTaskDes());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (false == rowsBean.isIsCheck()) {
                    setTextColor(views);
                    notifyDataSetChanged();
                }
                isCheckRequest(rowsBean.getTaskAssignedID());
                Intent intent = new Intent(context, TaskDetailActivity.class);
                intent.putExtra("taskId", rowsBean.getTaskAssignedID() + "");
                context.startActivity(intent);
            }
        });

    }
    //填充日历图片
    private void setTextColor(TextView[] views) {
        for (TextView view : views) {
            view.setTextColor(ContextCompat.getColor(context, R.color.gray));
        }
    }

    /**
     * 方法名：isCheckRequest
     * 功    能：点击修改查阅状态
     * 参    数：int id
     * 返回值：无
     */
    public static void isCheckRequest(int id) {
        Map<String, Object> params = new HashMap<>();
        try {
            params.put("TaskAssignedID", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        OkHttpUtils.post().url(UrlConfig.URL_ISCHECK).params(params).build().execute(new GenericsCallback<String>(new JsonGenericsSerializator()) {
            @Override
            public void onResponse(String response, int id) {
                Log.i("isCheck", response.toString());

            }

            @Override
            public void onError(Call call, Exception e, int id) {
                Log.i("isCheckError", e.getMessage().toString());

            }
        });
    }
}
