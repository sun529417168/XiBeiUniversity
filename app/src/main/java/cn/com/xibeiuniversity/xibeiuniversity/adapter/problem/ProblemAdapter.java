package cn.com.xibeiuniversity.xibeiuniversity.adapter.problem;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import cn.com.xibeiuniversity.xibeiuniversity.R;
import cn.com.xibeiuniversity.xibeiuniversity.activity.problem.ProblemDetailActivity;
import cn.com.xibeiuniversity.xibeiuniversity.base.MyBaseAdapter;
import cn.com.xibeiuniversity.xibeiuniversity.bean.problem.ProblemBean;

/**
 * 文件名：ProblemAdapter
 * 描    述：问题列表的适配器
 * 作    者：stt
 * 时    间：2017.1.3
 * 版    本：V1.0.0
 */

public class ProblemAdapter extends MyBaseAdapter {
    private ArrayList<ProblemBean.RowsBean> list = new ArrayList<>();
    public ProblemAdapter(Context context, List list) {
        super(context, list);
        this.list = (ArrayList<ProblemBean.RowsBean>) list;
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
        ProblemBean.RowsBean rowsBean = list.get(position);
        /**
         * 赋值
         */
        number.setText(rowsBean.getProblemSno());
        if ("已上报".equals(rowsBean.getStateName())) {
            state.setText("已上报");
            state.setTextColor(ContextCompat.getColor(context, R.color.white));
            state.setBackgroundResource(R.color.blue);
        }
        if ("已回复".equals(rowsBean.getStateName())) {
            state.setText("已回复");
            state.setTextColor(ContextCompat.getColor(context, R.color.white));
            state.setBackgroundResource(R.color.green);
        }
        for (ProblemBean.RowsBean.ReportAttachmentListBean imageBean : rowsBean.getReportAttachmentList()) {
            if (imageBean.getAttachmentType() == 1) {
                ImageLoader.getInstance().displayImage(imageBean.getFileUrl(), imageView);
            }
        }
        if (rowsBean.getReportAttachmentList().size()==0){
            imageView.setImageResource(R.mipmap.login_logo);
        }
        date.setText(rowsBean.getFindDateApi());
        name.setText(rowsBean.getProblemTypeName());
        sender.setText(rowsBean.getReportPersonName());
        info.setText(rowsBean.getProblemDes());
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
