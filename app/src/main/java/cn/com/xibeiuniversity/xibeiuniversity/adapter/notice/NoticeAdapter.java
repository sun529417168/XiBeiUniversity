package cn.com.xibeiuniversity.xibeiuniversity.adapter.notice;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.xibeiuniversity.xibeiuniversity.R;
import cn.com.xibeiuniversity.xibeiuniversity.activity.notice.NoticeDetailActivity;
import cn.com.xibeiuniversity.xibeiuniversity.base.MyBaseAdapter;
import cn.com.xibeiuniversity.xibeiuniversity.bean.notice.NoticeBean;
import cn.com.xibeiuniversity.xibeiuniversity.config.UrlConfig;
import cn.com.xibeiuniversity.xibeiuniversity.okhttps.OkHttpUtils;
import cn.com.xibeiuniversity.xibeiuniversity.okhttps.callback.GenericsCallback;
import cn.com.xibeiuniversity.xibeiuniversity.okhttps.utils.JsonGenericsSerializator;
import cn.com.xibeiuniversity.xibeiuniversity.utils.GetWeek;
import okhttp3.Call;

/**
 * 文件名：NoticeAdapter
 * 描    述：通知列表的适配器
 * 作    者：stt
 * 时    间：2017.1.3
 * 版    本：V1.0.0
 */

public class NoticeAdapter extends MyBaseAdapter {
    private ArrayList<NoticeBean.RowsBean> list = new ArrayList<>();

    public NoticeAdapter(Context context) {
        super(context);
    }

    public NoticeAdapter(Context context, List list) {
        super(context, list);
        this.list = (ArrayList<NoticeBean.RowsBean>) list;
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
        ImageView imageView = get(view, R.id.item_notice_imageView);//图片
        final TextView info = get(view, R.id.item_notice_info);  // 具体内容
        final TextView[] views = {number, date, name, info};
        final NoticeBean.RowsBean bean = list.get(position);
        /**
        *删除日期中的小时
        **/
         String firstDate=bean.getApiCreateTime().split(" ")[0];
        //获得星期
         String week= GetWeek.getWeek(firstDate);
        /**
         * 赋值
         */
        number.setText(bean.getInformSno());
        date.setText(firstDate+" "+week);
        name.setText(bean.getName());
        info.setText(bean.getContentInfo());
        for (NoticeBean.RowsBean.FileListBean fileBean : bean.getFileList()) {
            if (fileBean.getAttachmentType() == 1) {
                ImageLoader.getInstance().displayImage(fileBean.getFileUrl(), imageView);
            }
        }
        if (bean.getFileList().size() == 0) {
            imageView.setImageResource(R.mipmap.login_logo);
        }
        if (bean.isIsCheck()) {
            setTextColor(views);
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (false == bean.isIsCheck()) {
                    setTextColor(views);
                    notifyDataSetChanged();
                }
                isCheckRequest(bean.getAcceptID());
                Intent intent = new Intent(context, NoticeDetailActivity.class);
                intent.putExtra("noticeId", bean.getID());
                context.startActivity(intent);
            }
        });

    }

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
            params.put("ID", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        OkHttpUtils.post().url(UrlConfig.URL_NOTICEISCHECK).params(params).build().execute(new GenericsCallback<String>(new JsonGenericsSerializator()) {
            @Override
            public void onResponse(String response, int id) {
                Log.i("noticeIsCheck", response.toString());

            }

            @Override
            public void onError(Call call, Exception e, int id) {
                Log.i("noticeIsCheckError", e.getMessage().toString());

            }
        });
    }
}
