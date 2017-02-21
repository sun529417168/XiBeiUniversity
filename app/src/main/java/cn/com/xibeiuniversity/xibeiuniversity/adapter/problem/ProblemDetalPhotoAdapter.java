package cn.com.xibeiuniversity.xibeiuniversity.adapter.problem;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import cn.com.xibeiuniversity.xibeiuniversity.R;
import cn.com.xibeiuniversity.xibeiuniversity.activity.DetailImageActivity;
import cn.com.xibeiuniversity.xibeiuniversity.base.MyBaseAdapter;

/**
 * 文件名：ProblemDetalPhotoAdapter
 * 描    述：问题详情图片的适配器
 * 作    者：stt
 * 时    间：2017.1.9
 * 版    本：V1.0.0
 */

public class ProblemDetalPhotoAdapter extends MyBaseAdapter {
    private ArrayList<String> list = new ArrayList<>();

    public ProblemDetalPhotoAdapter(Context context, List list) {
        super(context, list);
        this.list = (ArrayList<String>) list;
    }

    @Override
    public int getContentView() {
        return R.layout.item_gridview_photo;
    }

    @Override
    public void onInitView(View view, int position) {
        ImageView imageView = get(view, R.id.item_grid_image);
        ImageLoader.getInstance().displayImage( list.get(position), imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(context, DetailImageActivity.class);
                in.putExtra("type",1);
                in.putStringArrayListExtra("listPath",list );
                context.startActivity(in);
            }
        });
    }

}
