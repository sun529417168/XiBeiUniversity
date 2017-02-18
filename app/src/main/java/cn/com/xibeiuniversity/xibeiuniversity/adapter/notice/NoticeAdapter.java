package cn.com.xibeiuniversity.xibeiuniversity.adapter.notice;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.com.xibeiuniversity.xibeiuniversity.R;
import cn.com.xibeiuniversity.xibeiuniversity.activity.notice.NoticeDetailActivity;
import cn.com.xibeiuniversity.xibeiuniversity.base.MyBaseAdapter;
import cn.com.xibeiuniversity.xibeiuniversity.bean.NoticeBean;

/**
 * 文件名：NoticeAdapter
 * 描    述：通知列表的适配器
 * 作    者：stt
 * 时    间：2017.1.3
 * 版    本：V1.0.0
 */

public class NoticeAdapter extends MyBaseAdapter {
    private ArrayList<NoticeBean> list = new ArrayList<>();

    public NoticeAdapter(Context context) {
        super(context);
    }

    public NoticeAdapter(Context context, List list) {
        super(context, list);
        this.list = (ArrayList<NoticeBean>) list;
    }

    @Override
    public int getContentView() {
        return R.layout.item_notice;
    }

    @Override
    public void onInitView(View view, int position) {
        TextView number = get(view, R.id.item_notice_number);  // 编号
        TextView date = get(view, R.id.item_notice_date);  // 日期
        TextView name = get(view, R.id.item_notice_name);  // 名称
        TextView info = get(view, R.id.item_notice_info);  // 具体内容
        TextView[] views = {number, date, name, info};

        /**
         * 赋值
         */
        number.setText(list.get(position).getNumber());
        date.setText(list.get(position).getDate());
        name.setText(list.get(position).getName());
        info.setText(list.get(position).getInfo());
        if ("1".equals(list.get(position).getIsRead())){
            setTextColor(views);
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NoticeDetailActivity.class);
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
