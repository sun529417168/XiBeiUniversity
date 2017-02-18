package cn.com.xibeiuniversity.xibeiuniversity.activity.problem;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

import cn.com.xibeiuniversity.xibeiuniversity.R;
import cn.com.xibeiuniversity.xibeiuniversity.activity.task.DetailImageActivity;
import cn.com.xibeiuniversity.xibeiuniversity.adapter.task.TaskDetalPhotoAdapter;
import cn.com.xibeiuniversity.xibeiuniversity.base.BaseActivity;
import cn.com.xibeiuniversity.xibeiuniversity.function.takephoto.app.TakePhotoActivity;
import cn.com.xibeiuniversity.xibeiuniversity.function.takephoto.compress.CompressConfig;

/**
 * 文件名：AddProblemActivity
 * 描    述：添加问题的类
 * 作    者：stt
 * 时    间：2017.1.6
 * 版    本：V1.0.0
 */

public class AddProblemActivity extends TakePhotoActivity implements View.OnClickListener,AdapterView.OnItemClickListener {
    private Context context;
    private TextView titleName;
    private LinearLayout back;
    private ImageView takePhoto;
    private ArrayList<String> listPath = new ArrayList<String>();
    private ArrayList<Bitmap> list = new ArrayList<Bitmap>();
    private GridView gridView;
    private TaskDetalPhotoAdapter taskDetalPhotoAdapter;

    @Override
    protected void setView() {
        setContentView(R.layout.activity_add_problem);
    }

    @Override
    protected void setDate(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            list = savedInstanceState.getParcelableArrayList("listBitmap");
        }
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("listBitmap", list);
    }
    @Override
    protected void init() {
        titleName = (TextView) findViewById(R.id.title_name);
        titleName.setText("问题上报");
        back = (LinearLayout) findViewById(R.id.title_back);
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(this);

        takePhoto = (ImageView) findViewById(R.id.add_problem_detail_takePhoto);
        takePhoto.setOnClickListener(this);
        gridView = (GridView) findViewById(R.id.add_problem_detail_gridView);
        gridView.setOnItemClickListener(this);
        taskDetalPhotoAdapter = new TaskDetalPhotoAdapter(this, list);
        gridView.setAdapter(taskDetalPhotoAdapter);
        taskDetalPhotoAdapter.setList(list);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back:
                XiBeiApp.finishTop();
                break;
            case R.id.add_problem_detail_takePhoto:
                File file = new File(Environment.getExternalStorageDirectory(), "/sultan/" + "reported" + "pic" + System.currentTimeMillis() + ".jpg");
                if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
                Uri imageUri = Uri.fromFile(file);
                CompressConfig compressConfig = new CompressConfig.Builder().setMaxSize(50 * 1024).setMaxPixel(800).create();//压缩方法实例化就是压缩图片，根据配置参数压缩
                getTakePhoto().onEnableCompress(compressConfig, true).onPickFromCapture(imageUri);//从相机拍取照片不裁剪
                break;
        }
    }

    @Override
    public void takeCancel() {
        super.takeCancel();
    }

    @Override
    public void takeFail(String msg) {
        super.takeFail(msg);
    }

    @Override
    public void takeSuccess(String imagePath) {
        super.takeSuccess(imagePath);
        listPath.add(imagePath);
        showImg(imagePath);
    }

    private void showImg(String imagePath) {
        BitmapFactory.Options option = new BitmapFactory.Options();
        option.inSampleSize = 2;
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath, option);
        list.add(bitmap);
        taskDetalPhotoAdapter.setList(list);
        if (list.size() == 4) {
            takePhoto.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent in = new Intent(this, DetailImageActivity.class);
        in.putStringArrayListExtra("listPath", listPath);
        startActivity(in);
    }
}
