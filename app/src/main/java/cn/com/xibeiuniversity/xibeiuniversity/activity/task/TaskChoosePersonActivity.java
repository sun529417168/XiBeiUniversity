package cn.com.xibeiuniversity.xibeiuniversity.activity.task;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

import cn.com.xibeiuniversity.xibeiuniversity.R;
import cn.com.xibeiuniversity.xibeiuniversity.adapter.task.TaskChoosePersonAdapter;
import cn.com.xibeiuniversity.xibeiuniversity.base.BaseActivity;
import cn.com.xibeiuniversity.xibeiuniversity.bean.task.TaskChoosePersonBean;
import cn.com.xibeiuniversity.xibeiuniversity.interfaces.ChoosePersonInterface;
import cn.com.xibeiuniversity.xibeiuniversity.utils.MyRequest;

/**
 * 文件名：TaskChoosePersonActivity
 * 描    述：选择下发人员的类
 * 作    者：stt
 * 时    间：2017.03.22
 * 版    本：V1.0.7
 */

public class TaskChoosePersonActivity extends BaseActivity implements View.OnClickListener, ChoosePersonInterface {
    private ExpandableListView explistview;
    private List<List<TaskChoosePersonBean>> childrenData = new ArrayList<>();
    private List<TaskChoosePersonBean> groupData = new ArrayList<>();
    public static TaskChoosePersonAdapter adapter;
    private TreeMap<String, TaskChoosePersonBean> parentMap = new TreeMap<>();
    private TreeMap<String, TaskChoosePersonBean> childMap = new TreeMap<>();
    private List<List<Boolean>> childCheckBox = new ArrayList<>();
    private List<Boolean> parentCheckBox = new ArrayList<>();
    private List<TaskChoosePersonBean> resultData = new ArrayList<>();
    private Button submit;
    private int parentCount = 0;
    private int childCount = 0;

    @Override
    protected void setView() {
        setContentView(R.layout.activity_task_choose_person);
    }

    @Override
    protected void setDate(Bundle savedInstanceState) {
        MyRequest.GetPersonInfoByDepartmentRequest(this);
    }

    @Override
    protected void init() {
        explistview = (ExpandableListView) findViewById(R.id.task_choose_person_expandableListView);
        submit = (Button) findViewById(R.id.choose_person_submit);
        submit.setOnClickListener(this);
    }

    @Override
    public void getPerson(ArrayList<TaskChoosePersonBean> choosePersonList) {
        parentMap = new TreeMap<>();
        childMap = new TreeMap<>();
        for (TaskChoosePersonBean bean : choosePersonList) {
            if (bean.isIsParent() == true) {
                parentMap.put(bean.getName(), bean);
            } else {
                childMap.put(bean.getName(), bean);
            }
        }
        Iterator parentit = parentMap.keySet().iterator();
        Iterator childit = childMap.keySet().iterator();
        while (parentit.hasNext()) {
            Object parentflag = parentit.next();
            groupData.add(parentCount, (TaskChoosePersonBean) parentMap.get(parentflag));
            parentCheckBox.add(false);
            List<TaskChoosePersonBean> flagList = new ArrayList<>();
            List<Boolean> checkList = new ArrayList<Boolean>();
            while (childit.hasNext()) {
                Object childflag = childit.next();
                if (parentMap.get(parentflag).getId().equals(childMap.get(childflag).getPid())) {
                    flagList.add(childCount, (TaskChoosePersonBean) childMap.get(childflag));
                    checkList.add(false);
                    childCount++;
                }
            }
            childrenData.add(flagList);
            childCheckBox.add(checkList);
            childit = childMap.keySet().iterator();
            childCount = 0;
            parentCount++;
        }
        adapter = new TaskChoosePersonAdapter(childrenData, groupData, childCheckBox, parentCheckBox, getApplicationContext(), explistview);
        explistview.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        for (int i = 0; i < childCheckBox.size(); i++) {
            for (int j = 0; j < childCheckBox.get(i).size(); j++) {
                if (childCheckBox.get(i).get(j)) {
                    resultData.add(childrenData.get(i).get(j));
                }
            }
        }
        Intent intent = new Intent();
        intent.putExtra("data", (Serializable) resultData);
        setResult(RESULT_OK, intent);
        finish();
        // Toast.makeText(this, resultData.toString(), Toast.LENGTH_SHORT).show();
    }
}
