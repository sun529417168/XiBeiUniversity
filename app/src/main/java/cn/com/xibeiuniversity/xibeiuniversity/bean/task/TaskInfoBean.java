package cn.com.xibeiuniversity.xibeiuniversity.bean.task;

import java.io.Serializable;

/**
 * Created by zhangyunlong on 2017/3/23.
 */

public class TaskInfoBean implements Serializable {


    /**
     * type : task
     * id : 1
     * name : 任务名称
     * content : 任务内容
     * title : 任务内容
     */

    private String type;
    private String id;
    private String name;
    private String content;
    private String title;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
