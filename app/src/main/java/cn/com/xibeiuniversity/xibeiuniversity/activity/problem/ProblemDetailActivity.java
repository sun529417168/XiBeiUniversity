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
import cn.com.xibeiuniversity.xibeiuniversity.bean.problem.ProblemBean;
import cn.com.xibeiuniversity.xibeiuniversity.function.takephoto.app.TakePhotoActivity;
import cn.com.xibeiuniversity.xibeiuniversity.function.takephoto.compress.CompressConfig;

/**
 * 文件名：ProblemDetailActivity
 * 描    述：问题详情类
 * 作    者：stt
 * 时    间：2017.1.3
 * 版    本：V1.0.0
 */

public class ProblemDetailActivity extends TakePhotoActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private Context context;
    private TextView titleName;
    private LinearLayout back;
    private ImageView takePhoto;
    private ArrayList<String> listPath = new ArrayList<String>();
    private ArrayList<Bitmap> list = new ArrayList<Bitmap>();
    private GridView gridView;
    private TaskDetalPhotoAdapter taskDetalPhotoAdapter;
    private ProblemBean.RowsBean problemBean;
    /**
     * 编号，问题名称，状态，上报人，上报时间，处理人，处理时间
     */
    private TextView numberText, nameText, stateText, senderText, sendTimeText;
    private TextView infoEdit,describeText;

    @Override
    protected void setView() {
        setContentView(R.layout.activity_problem_detail);
    }

    @Override
    protected void setDate(Bundle savedInstanceState) {
        problemBean = (ProblemBean.RowsBean) getIntent().getSerializableExtra("problemBean");
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
        titleName.setText("问题详情");
        back = (LinearLayout) findViewById(R.id.title_back);
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(this);
        numberText = (TextView) findViewById(R.id.problem_detail_number);
        nameText = (TextView) findViewById(R.id.problem_detail_name);
        stateText = (TextView) findViewById(R.id.problem_detail_state);
        senderText = (TextView) findViewById(R.id.problem_detail_sender);
        sendTimeText = (TextView) findViewById(R.id.problem_detail_sendTime);
        infoEdit = (TextView) findViewById(R.id.problem_detail_infoEdit);
        describeText = (TextView) findViewById(R.id.problem_detail_describe);

        numberText.setText(problemBean.getProblemSno());
        nameText.setText(problemBean.getProblemTypeName());
        if ("已上报".equals(problemBean.getStateName())) {
            stateText.setText("已上报");
        }
        if ("已收到".equals(problemBean.getStateName())) {
            stateText.setText("已收到");
        }
        senderText.setText(problemBean.getReportPersonName());
        sendTimeText.setText(problemBean.getFindDateApi());
        infoEdit.setText(problemBean.getProblemDes());
        describeText.setText(problemBean.getDescribe());


        takePhoto = (ImageView) findViewById(R.id.problem_detail_takePhoto);
        takePhoto.setOnClickListener(this);
        takePhoto.setVisibility(View.INVISIBLE);
        gridView = (GridView) findViewById(R.id.problem_detail_gridView);
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
            case R.id.problem_detail_takePhoto:
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
