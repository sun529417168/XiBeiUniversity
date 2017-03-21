package cn.com.xibeiuniversity.xibeiuniversity.activity.task;

import cn.com.xibeiuniversity.xibeiuniversity.R;
import cn.com.xibeiuniversity.xibeiuniversity.adapter.task.TaskChoosePersonAdapter;
import cn.com.xibeiuniversity.xibeiuniversity.base.BaseActivity;
import cn.com.xibeiuniversity.xibeiuniversity.bean.task.TaskChoosePersonBean;
import cn.com.xibeiuniversity.xibeiuniversity.interfaces.ChoosePersonInterface;
import cn.com.xibeiuniversity.xibeiuniversity.utils.MyRequest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by Administrator on 2017/3/21.
 */

public class TaskChoosePersonActivity extends BaseActivity implements View.OnClickListener, ChoosePersonInterface {
    private ExpandableListView explistview;
    private List<List<TaskChoosePersonBean>> childrenData = new ArrayList<>();
    private List<TaskChoosePersonBean> groupData = new ArrayList<>();
    public static TaskChoosePersonAdapter adapter;
    private TreeMap<String, TaskChoosePersonBean> parentMap;
    private TreeMap<String, TaskChoosePersonBean> childMap;
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
            groupData.add(parentCount, (TaskChoosePersonBean) parentflag);
            parentCheckBox.add(false);
            List<TaskChoosePersonBean> flagList = new ArrayList<>();
            List<Boolean> checkList = new ArrayList<Boolean>();
            while (childit.hasNext()) {
                Object childflag = childit.next();
                if (parentMap.get(parentflag).equals(childMap.get(childflag))) {
                    flagList.add(childCount, (TaskChoosePersonBean) childflag);
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
        Toast.makeText(this, resultData.toString(), Toast.LENGTH_SHORT).show();
    }
}
