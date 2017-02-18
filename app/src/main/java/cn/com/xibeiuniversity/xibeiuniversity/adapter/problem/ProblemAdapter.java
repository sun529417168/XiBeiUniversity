package cn.com.xibeiuniversity.xibeiuniversity.adapter.problem;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.com.xibeiuniversity.xibeiuniversity.R;
import cn.com.xibeiuniversity.xibeiuniversity.activity.problem.ProblemDetailActivity;
import cn.com.xibeiuniversity.xibeiuniversity.activity.task.TaskDetailActivity;
import cn.com.xibeiuniversity.xibeiuniversity.base.MyBaseAdapter;
import cn.com.xibeiuniversity.xibeiuniversity.bean.ProblemBean;

/**
 * 文件名：ProblemAdapter
 * 描    述：问题列表的适配器
 * 作    者：stt
 * 时    间：2017.1.3
 * 版    本：V1.0.0
 */

public class ProblemAdapter extends MyBaseAdapter {
    private ArrayList<ProblemBean> list = new ArrayList<>();
    public ProblemAdapter(Context context, List list) {
        super(context, list);
        this.list = (ArrayList<ProblemBean>) list;
    }

    @Override
    public int getContentView() {
        return R.layout.item_problem;
    }

    @Override
    public void onInitView(View view, final int position) {
        TextView number = get(view, R.id.item_problem_number);  // 编号
        TextView state = get(view, R.id.item_problem_state);  // 状态
        TextView date = get(view, R.id.item_problem_date);  // 日期
        ImageView imageView = get(view, R.id.item_problem_image);  // 图片
        TextView name = get(view, R.id.item_problem_name);  // 名称
        TextView sender = get(view, R.id.item_problem_sender);  // 发送人
        TextView info = get(view, R.id.item_problem_info);  // 具体内容
        TextView executor = get(view, R.id.item_problem_executor);  // 处理人
        TextView[] views = {number, date, name, sender, info,executor};

        /**
         * 赋值
         */
        number.setText(list.get(position).getNumber());
        if ("1".equals(list.get(position).getState())) {
            state.setText("已上报");
            state.setTextColor(ContextCompat.getColor(context, R.color.white));
            state.setBackgroundResource(R.color.blue);
        }
        if ("2".equals(list.get(position).getState())) {
            state.setText("已回复");
            state.setTextColor(ContextCompat.getColor(context, R.color.white));
            state.setBackgroundResource(R.color.green);
//            executor.setText("孙英媒");
        }
        imageView.setImageResource(R.mipmap.timg);
        date.setText(list.get(position).getDate());
        name.setText(list.get(position).getName());
        sender.setText(list.get(position).getSender());
        info.setText(list.get(position).getInfo());
        if ("1".equals(list.get(position).getIsRead())){
            setTextColor(views);
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProblemDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("problemBean", list.get(position));
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });


    }
    private void setTextColor(TextView[] views) {
        for (TextView view : views) {
            view.setTextColor(ContextCompat.getColor(context, R.color.gray));
        }
    }
}
