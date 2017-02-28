package cn.com.xibeiuniversity.xibeiuniversity.activity.problem;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.xibeiuniversity.xibeiuniversity.R;
import cn.com.xibeiuniversity.xibeiuniversity.activity.DetailImageActivity;
import cn.com.xibeiuniversity.xibeiuniversity.adapter.task.TaskDetalPhotoAdapter;
import cn.com.xibeiuniversity.xibeiuniversity.function.takephoto.app.TakePhotoActivity;
import cn.com.xibeiuniversity.xibeiuniversity.function.takephoto.compress.CompressConfig;
import cn.com.xibeiuniversity.xibeiuniversity.interfaces.GetGPSInterface;
import cn.com.xibeiuniversity.xibeiuniversity.interfaces.SearchTypePopInterface;
import cn.com.xibeiuniversity.xibeiuniversity.utils.DateTimePickDialogUtil;
import cn.com.xibeiuniversity.xibeiuniversity.utils.DialogUtils;
import cn.com.xibeiuniversity.xibeiuniversity.utils.MyRequest;
import cn.com.xibeiuniversity.xibeiuniversity.utils.MyUtils;
import cn.com.xibeiuniversity.xibeiuniversity.utils.PopWindowUtils;
import cn.com.xibeiuniversity.xibeiuniversity.utils.SharedUtil;
import cn.com.xibeiuniversity.xibeiuniversity.utils.ToastUtil;

/**
 * 文件名：AddProblemActivity
 * 描    述：添加问题的类
 * 作    者：stt
 * 时    间：2017.1.6
 * 版    本：V1.0.0
 */

public class AddProblemActivity extends TakePhotoActivity implements View.OnClickListener, AdapterView.OnItemClickListener, SearchTypePopInterface, GetGPSInterface {
    private Context context;
    private TextView titleName;
    private LinearLayout back;
    private ImageView takePhoto;
    private ArrayList<String> listPath = new ArrayList<String>();
    private ArrayList<Bitmap> list = new ArrayList<Bitmap>();
    private List<File> listFile = new ArrayList<>();
    private Map<String, File> fileMap = new HashMap<>();
    private File mCameraFile;
    private GridView gridView;
    private TaskDetalPhotoAdapter taskDetalPhotoAdapter;
    private EditText nameEdit, addressEdit, inputInfoEdit;
    private RelativeLayout typeLayout, findTimeLayout;
    private TextView typeText, senderText, findTimeText, sendTimeText;
    private PopupWindow showProblemTypePop;
    private Button sendInfoBtn;
    private int problemType = 0;
    private String gps;
    private String problemTitle, address, findDate, problemDes;

    @Override
    protected void setView() {
        setContentView(R.layout.activity_add_problem);
    }

    @Override
    protected void setDate(Bundle savedInstanceState) {
        if (!MyUtils.gpsIsOPen(this)) {
            DialogUtils.initGPS(this);
        }
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
        nameEdit = (EditText) findViewById(R.id.add_problem_detail_name);
        typeLayout = (RelativeLayout) findViewById(R.id.add_problem_typeLayout);
        typeLayout.setOnClickListener(this);
        typeText = (TextView) findViewById(R.id.add_problem_type);
        addressEdit = (EditText) findViewById(R.id.add_problem_address);
        senderText = (TextView) findViewById(R.id.add_problem_sender);
        senderText.setText(SharedUtil.getString(this, "personName"));
        findTimeLayout = (RelativeLayout) findViewById(R.id.add_problem_findTimeLayout);
        findTimeLayout.setOnClickListener(this);
        findTimeText = (TextView) findViewById(R.id.add_problem_findTime);
        sendTimeText = (TextView) findViewById(R.id.add_problem_sendTime);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");//设置日期格式
        sendTimeText.setText(df.format(new Date()));
        inputInfoEdit = (EditText) findViewById(R.id.add_problem_inputInfo);
        sendInfoBtn = (Button) findViewById(R.id.add_problem_detail_button);
        sendInfoBtn.setOnClickListener(this);


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
            case R.id.add_problem_detail_takePhoto://点击拍照
                File file = new File(Environment.getExternalStorageDirectory(), "/XiBeiProblem/" + System.currentTimeMillis() + ".jpg");
                if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
                Uri imageUri = Uri.fromFile(file);
                CompressConfig compressConfig = new CompressConfig.Builder().setMaxSize(50 * 1024).setMaxPixel(700).create();//压缩方法实例化就是压缩图片，根据配置参数压缩
                getTakePhoto().onEnableCompress(compressConfig, true).onPickFromCapture(imageUri);//从相机拍取照片不裁剪
                break;
            case R.id.add_problem_typeLayout://问题类型
                showProblemTypePop = PopWindowUtils.showAddProblemTypePop(this, typeLayout);
                break;
            case R.id.add_problem_findTimeLayout://发现时间
                DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(AddProblemActivity.this, "");
                dateTimePicKDialog.dateTimePicKDialog(findTimeText);
                break;
            case R.id.add_problem_detail_button://上传数据
                if (!MyUtils.gpsIsOPen(this)) {
                    DialogUtils.initGPS(this);
                    return;
                }
                MyUtils.getLoc(this);
                problemTitle = nameEdit.getText().toString().trim();
                address = addressEdit.getText().toString().trim();
                findDate = findTimeText.getText().toString().trim();
                problemDes = inputInfoEdit.getText().toString().trim();
                if (isEmpty()) {
                    MyRequest.addProblemRequestsb(this, fileMap, problemTitle, problemType, address, gps, findDate, problemDes);//不管有没有图片
                }

                break;
        }
    }

    private boolean isEmpty() {
        if (TextUtils.isEmpty(problemTitle)) {
            ToastUtil.show(this, "请输入问题名称");
            return false;
        } else if (problemType == 0) {
            ToastUtil.show(this, "请选择问题类型");
            return false;
        } else if (TextUtils.isEmpty(address)) {
            ToastUtil.show(this, "请输入所在区域");
            return false;
        } else if (TextUtils.isEmpty(findDate)) {
            ToastUtil.show(this, "请选择发现时间");
            return false;
        } else if (TextUtils.isEmpty(problemDes)) {
            ToastUtil.show(this, "请输入问题描述");
            return false;
        } else {
            return true;
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
        Log.i("imagePaths", imagePath);
        listPath.add(imagePath);
        mCameraFile = new File(imagePath);
        fileMap.put(imagePath, mCameraFile);
        listFile.add(mCameraFile);
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

    @Override
    public void searchType(String typeName) {
        typeText.setText(typeName);
        problemType = "部件类型".equals(typeName) ? 1 : "事件类型".equals(typeName) ? 2 : 0;
        if (showProblemTypePop.isShowing()) {
            showProblemTypePop.dismiss();
        }
    }


    @Override
    public void getGPS(String longitude, String latitude) {
        gps = longitude + "," + latitude;
        Log.i("gps", gps);
    }
}
