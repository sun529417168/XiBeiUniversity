package cn.com.xibeiuniversity.xibeiuniversity.activity.task;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
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
import java.util.List;

import cn.com.xibeiuniversity.xibeiuniversity.R;
import cn.com.xibeiuniversity.xibeiuniversity.activity.DetailImageActivity;
import cn.com.xibeiuniversity.xibeiuniversity.adapter.task.TaskDetalDescribePhotoAdapter;
import cn.com.xibeiuniversity.xibeiuniversity.adapter.task.TaskDetalPhotoAdapter;
import cn.com.xibeiuniversity.xibeiuniversity.bean.task.TaskBean;
import cn.com.xibeiuniversity.xibeiuniversity.function.takephoto.app.TakePhotoActivity;
import cn.com.xibeiuniversity.xibeiuniversity.function.takephoto.compress.CompressConfig;
import cn.com.xibeiuniversity.xibeiuniversity.interfaces.SearchTypePopInterface;
import cn.com.xibeiuniversity.xibeiuniversity.utils.DownloadUtil;
import cn.com.xibeiuniversity.xibeiuniversity.utils.MyRequest;
import cn.com.xibeiuniversity.xibeiuniversity.utils.MyUtils;
import cn.com.xibeiuniversity.xibeiuniversity.utils.PopWindowUtils;
import cn.com.xibeiuniversity.xibeiuniversity.utils.ToastUtil;

/**
 * 文件名：TaskDetailActivity
 * 描    述：任务详情类
 * 作    者：stt
 * 时    间：2017.01.05
 * 版    本：V1.0.0
 */

public class TaskDetailActivity extends TakePhotoActivity implements View.OnClickListener, AdapterView.OnItemClickListener, SearchTypePopInterface {
    private Context context;
    private TextView titleName;
    private LinearLayout back;
    private TaskBean.RowsBean taskBean = new TaskBean.RowsBean();
    /**
     * 编号，名称，类型，状态，所在位置，创建人，任务优先级，下发时间，反馈时间
     */
    private TextView numberText, nameText, typeText, stateTaskText, stateReplyText, addressText, founderText, priorityText, sendTimeText, feedbackTimeText;
    private TextView fileName, filePath;
    private EditText infoEdit;
    private String info;
    private ImageView takePhoto;
    private ArrayList<String> listPath = new ArrayList<String>();
    private ArrayList<Bitmap> list = new ArrayList<Bitmap>();
    private GridView gridView, gridViewDescribe;
    private TaskDetalPhotoAdapter taskDetalPhotoAdapter;
    private TaskDetalDescribePhotoAdapter taskDetalDescribePhotoAdapter;
    private Button submitBtn;
    private RelativeLayout stateReplyLayout;
    private ArrayList<String> describeList = new ArrayList<>();
    private PopupWindow showReplyPop;
    private int feedbackState = -1;
    private List<File> listFile = new ArrayList<>();
    private File mCameraFile;
    private String path;

    @Override
    protected void setView() {
        setContentView(R.layout.activity_task_detail);
    }

    @Override
    protected void setDate(Bundle savedInstanceState) {
        context = this;
        taskBean = (TaskBean.RowsBean) getIntent().getSerializableExtra("taskBean");
        Log.i("taskBean", taskBean.toString());
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
        titleName.setText("任务详情");
        back = (LinearLayout) findViewById(R.id.title_back);
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(this);
        takePhoto = (ImageView) findViewById(R.id.task_detail_takePhoto);
        takePhoto.setOnClickListener(this);
        numberText = (TextView) findViewById(R.id.task_detail_number);//编号
        nameText = (TextView) findViewById(R.id.task_detail_name);//名称
        typeText = (TextView) findViewById(R.id.task_detail_type);//类型
        stateTaskText = (TextView) findViewById(R.id.task_detail_state);//状态
        addressText = (TextView) findViewById(R.id.task_detail_address);//所在位置
        founderText = (TextView) findViewById(R.id.task_detail_founder);//创建人
        priorityText = (TextView) findViewById(R.id.task_detail_priority);//任务优先级
        sendTimeText = (TextView) findViewById(R.id.task_detail_sendTime);//下发时间
        fileName = (TextView) findViewById(R.id.task_detail_state_fileName);//文件名称
        filePath = (TextView) findViewById(R.id.task_detail_state_file);//文件路径
        filePath.setOnClickListener(this);

        stateReplyText = (TextView) findViewById(R.id.task_detail_state_reply);//反馈状态
        stateReplyLayout = (RelativeLayout) findViewById(R.id.task_detail_state_reply_layout);//反馈状态的layout
        stateReplyLayout.setOnClickListener(this);
        feedbackTimeText = (TextView) findViewById(R.id.task_detail_feedbackTime);//反馈时间
        infoEdit = (EditText) findViewById(R.id.task_detail_info);//任务描述
        gridView = (GridView) findViewById(R.id.task_detail_gridView);
        gridView.setOnItemClickListener(this);
        taskDetalPhotoAdapter = new TaskDetalPhotoAdapter(this, list);
        gridView.setAdapter(taskDetalPhotoAdapter);
        taskDetalPhotoAdapter.setList(list);
        gridViewDescribe = (GridView) findViewById(R.id.task_detail_gridView_describe);//描述查看的图片
        for (TaskBean.RowsBean.ImageListBean imageBean : taskBean.getImageList()) {
            if (imageBean.getAttachmentType() == 1) {
                describeList.add(imageBean.getFileUrl());
            }
        }
        taskDetalDescribePhotoAdapter = new TaskDetalDescribePhotoAdapter(this, describeList);
        gridViewDescribe.setAdapter(taskDetalDescribePhotoAdapter);
        submitBtn = (Button) findViewById(R.id.task_detail_button);
        submitBtn.setOnClickListener(this);

        numberText.setText(taskBean.getTaskSno());
        nameText.setText(taskBean.getTaskName());
        typeText.setText(taskBean.getTaskTypeName());
        addressText.setText(taskBean.getTaskAddr());
        founderText.setText(taskBean.getPersonName());
        priorityText.setText(taskBean.getTaskPriorityName());
        sendTimeText.setText(taskBean.getStartDateApi());
        for (TaskBean.RowsBean.ImageListBean imageBean : taskBean.getImageList()) {
            if (imageBean.getAttachmentType() == 2) {
                fileName.setText("文件(" + imageBean.getFileName() + ")");
            }
        }

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");//设置日期格式
        feedbackTimeText.setText(df.format(new Date()));
        if ("未完成".equals(taskBean.getTaskStateName())) {
            stateTaskText.setText("未完成");
//            submitBtn.setVisibility(View.INVISIBLE);
//            takePhoto.setVisibility(View.INVISIBLE);
            infoEdit.setFocusable(false);
        }
        if ("处理中".equals(taskBean.getTaskStateName())) {
            stateTaskText.setText("处理中");
        }
        if ("未处理".equals(taskBean.getTaskStateName())) {
            stateTaskText.setText("未处理");
        }
        if ("已完成".equals(taskBean.getTaskStateName())) {
            stateTaskText.setText("已完成");
//            submitBtn.setVisibility(View.INVISIBLE);
//            takePhoto.setVisibility(View.INVISIBLE);
            infoEdit.setFocusable(false);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back:
                XiBeiApp.finishTop();
                break;
            case R.id.task_detail_takePhoto://拍照
                File file = new File(Environment.getExternalStorageDirectory(), "/XiBei/" + System.currentTimeMillis() + ".jpg");
                if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
                Uri imageUri = Uri.fromFile(file);
                CompressConfig compressConfig = new CompressConfig.Builder().setMaxSize(50 * 1024).setMaxPixel(800).create();//压缩方法实例化就是压缩图片，根据配置参数压缩
                getTakePhoto().onEnableCompress(compressConfig, true).onPickFromCapture(imageUri);//从相机拍取照片不裁剪
                break;
            case R.id.task_detail_state_reply_layout://回复状态
                if ("未完成".equals(taskBean.getTaskStateName()) || "已完成".equals(taskBean.getTaskStateName())) {
                    return;
                }
                showReplyPop = PopWindowUtils.showTaskReplyPop(this, stateReplyLayout);
                break;
            case R.id.task_detail_button:// 确认提交
                info = infoEdit.getText().toString().trim();
                if (feedbackState == -1) {
                    ToastUtil.show(context, "请选择反馈状态");
                    return;
                }
                if (TextUtils.isEmpty(info)) {
                    ToastUtil.show(context, "请输入反馈内容");
                    return;
                }
                if (listFile.size() == 0) {
                    ToastUtil.show(context, "请您拍照");
                    return;
                }
                for (File files : listFile) {
                    MyRequest.filesRequest(this, files, info, taskBean.getTaskAssignedID(), feedbackState);
                }

                break;
            case R.id.task_detail_state_file://点击下载
                int isHave = -1;
                int isAttach = -1;
                if (isEmpty()) {
                    for (TaskBean.RowsBean.ImageListBean imageBean : taskBean.getImageList()) {
                        if (imageBean.getAttachmentType() == 2) {
                            isAttach = 1;
                            if (MyUtils.getVideoFileName(path).size() > 0) {
                                for (String fileUrl : MyUtils.getVideoFileName(path)) {
                                    if (fileUrl.equals(imageBean.getFileName())) {
                                        isHave = 1;
                                        File files = new File(path + fileUrl);// 这里更改为你的名称
                                        Log.i("fileName", fileUrl + "=======" + files.getPath());
                                        Uri path = Uri.fromFile(files);
                                        Intent intent = new Intent(Intent.ACTION_VIEW);
                                        intent.setDataAndType(path, "application/msword");
                                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        try {
                                            startActivity(intent);
                                        } catch (ActivityNotFoundException e) {
                                            ToastUtil.show(context, "出现异常，请稍候再试");
                                        }
                                        break;
                                    }
                                }
                                if (isHave == -1) {
                                    DownloadUtil down = new DownloadUtil(TaskDetailActivity.this, imageBean.getFileName(), imageBean.getFileUrl());
                                    down.showDownloadDialog();
                                }
                            } else {
                                DownloadUtil down = new DownloadUtil(TaskDetailActivity.this, imageBean.getFileName(), imageBean.getFileUrl());
                                down.showDownloadDialog();
                            }
                        }
                    }
                    if (isAttach == -1) {
                        ToastUtil.show(context, "没有可下载的文件");
                    }

                }
                break;
        }
    }

    private boolean isEmpty() {
        if (taskBean.getImageList().size() == 0) {
            ToastUtil.show(context, "没有可下载的文件");
            return false;
        }
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            path = Environment.getExternalStorageDirectory() + "/XiBeiDownload";
            return true;
        } else {
            ToastUtil.show(context, "没有SD卡");
            return false;
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
        Log.i("imagePath",imagePath);
        listPath.add(imagePath);
        mCameraFile = new File(imagePath);
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
        feedbackState = "完成".equals(typeName) ? 3 : 4;
        stateReplyText.setText(typeName);
        if (showReplyPop.isShowing()) {
            showReplyPop.dismiss();
        }
    }

}
