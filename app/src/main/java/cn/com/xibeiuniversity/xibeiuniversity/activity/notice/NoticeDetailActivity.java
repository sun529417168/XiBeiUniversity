package cn.com.xibeiuniversity.xibeiuniversity.activity.notice;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

import cn.com.xibeiuniversity.xibeiuniversity.R;
import cn.com.xibeiuniversity.xibeiuniversity.activity.task.TaskDetailActivity;
import cn.com.xibeiuniversity.xibeiuniversity.adapter.notice.NoticeDetalPhotoAdapter;
import cn.com.xibeiuniversity.xibeiuniversity.adapter.problem.ProblemDetalPhotoAdapter;
import cn.com.xibeiuniversity.xibeiuniversity.base.BaseActivity;
import cn.com.xibeiuniversity.xibeiuniversity.bean.notice.NoticeBean;
import cn.com.xibeiuniversity.xibeiuniversity.bean.notice.NoticeDetailBean;
import cn.com.xibeiuniversity.xibeiuniversity.bean.problem.ProblemBean;
import cn.com.xibeiuniversity.xibeiuniversity.bean.task.TaskBean;
import cn.com.xibeiuniversity.xibeiuniversity.interfaces.NoticeDetailInterface;
import cn.com.xibeiuniversity.xibeiuniversity.utils.DownloadUtil;
import cn.com.xibeiuniversity.xibeiuniversity.utils.MyRequest;
import cn.com.xibeiuniversity.xibeiuniversity.utils.MyUtils;
import cn.com.xibeiuniversity.xibeiuniversity.utils.ToastUtil;

/**
 * 文件名：NoticeDetailActivity
 * 描    述：通知详情类
 * 作    者：stt
 * 时    间：2017.1.6
 * 版    本：V1.0.0
 */

public class NoticeDetailActivity extends BaseActivity implements View.OnClickListener, NoticeDetailInterface {
    private Context context;
    private TextView titleName;
    private LinearLayout back;
    private TextView numberText, nameText, typeText, stateText, senderText, sendTimeText, fileNameText, filePath, infoText;
    private NoticeDetailBean rowsBean = new NoticeDetailBean();
    private String path;
    private GridView gridView;
    private ArrayList<String> describeList = new ArrayList<String>();
    private NoticeDetalPhotoAdapter noticeDetalPhotoAdapter;

    @Override
    protected void setView() {
        setContentView(R.layout.activity_notice_detail);
    }

    @Override
    protected void setDate(Bundle savedInstanceState) {
        String id = getIntent().getStringExtra("noticeId");
        MyRequest.getNoticeDetailRequest(this, id);
    }

    @Override
    protected void init() {
        titleName = (TextView) findViewById(R.id.title_name);
        titleName.setText("通告详情");
        back = (LinearLayout) findViewById(R.id.title_back);
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(this);
        numberText = (TextView) findViewById(R.id.notice_detail_number);
        nameText = (TextView) findViewById(R.id.notice_detail_name);
        typeText = (TextView) findViewById(R.id.notice_detail_type);
        stateText = (TextView) findViewById(R.id.notice_detail_state);
        senderText = (TextView) findViewById(R.id.notice_detail_sender);
        sendTimeText = (TextView) findViewById(R.id.notice_detail_sendTime);
        fileNameText = (TextView) findViewById(R.id.notice_detail_fileName);
        filePath = (TextView) findViewById(R.id.notice_detail_attachment);
        filePath.setOnClickListener(this);
        infoText = (TextView) findViewById(R.id.notice_detail_info);
        gridView = (GridView) findViewById(R.id.notice_detail_gridView_describe);

    }

    private boolean isAttachment() {
        boolean isFlag = false;
        path = Environment.getExternalStorageDirectory() + "/XiBeiDownload";
        for (NoticeDetailBean.FileListBean fileBean : rowsBean.getFileList()) {
            if (fileBean.getAttachmentType() == 2) {
                if (MyUtils.getVideoFileName(path).size() > 0) {
                    for (String fileUrl : MyUtils.getVideoFileName(path)) {
                        if (fileUrl.equals(fileBean.getFileName())) {
                            isFlag = true;
                        }
                    }
                }
            }
        }
        return isFlag;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back:
                XiBeiApp.finishTop();
                break;
            case R.id.notice_detail_attachment://下载文件
                if (isEmpty()) {
                    if (isAttachment()) {
                        for (NoticeDetailBean.FileListBean fileBean : rowsBean.getFileList()) {
                            if (fileBean.getAttachmentType() == 2) {
                                if (MyUtils.getVideoFileName(path).size() > 0) {
                                    for (String fileUrl : MyUtils.getVideoFileName(path)) {
                                        if (fileUrl.equals(fileBean.getFileName())) {
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
                                }
                            }
                        }
                    } else {
                        for (NoticeDetailBean.FileListBean fileBean : rowsBean.getFileList()) {
                            if (fileBean.getAttachmentType() == 2) {
                                DownloadUtil down = new DownloadUtil(NoticeDetailActivity.this, fileBean.getFileName(), fileBean.getFileUrl(), filePath);
                                down.showDownloadDialog();
                            }
                        }
                    }
                }
                break;
        }
    }

    private boolean isEmpty() {
        if (rowsBean.getFileList().size() == 0) {
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
    public void getNoticeDetail(NoticeDetailBean noticeDetailBean) {
        rowsBean = noticeDetailBean;
        numberText.setText(rowsBean.getInformSno());
        nameText.setText(rowsBean.getName());
        typeText.setText(rowsBean.getInformTypeName());
        stateText.setText(rowsBean.getStateName());
        senderText.setText(rowsBean.getPersonName());
        sendTimeText.setText(rowsBean.getApiCreateTime());
        filePath.setText(isAttachment() ? "打开" : "下载");
        for (NoticeDetailBean.FileListBean fileBean : rowsBean.getFileList()) {
            if (fileBean.getAttachmentType() == 2) {
                fileNameText.setText("文件(" + fileBean.getFileName() + ")");
            }
            if (fileBean.getAttachmentType() == 1) {
                describeList.add(fileBean.getFileUrl());
            }
        }
        if (TextUtils.isEmpty(fileNameText.getText().toString().trim())) {
            findViewById(R.id.notice_detail_state_fileLayout).setVisibility(View.GONE);
        }
        infoText.setText(rowsBean.getContentInfo());
        noticeDetalPhotoAdapter = new NoticeDetalPhotoAdapter(this, describeList);
        gridView.setAdapter(noticeDetalPhotoAdapter);
    }
}
