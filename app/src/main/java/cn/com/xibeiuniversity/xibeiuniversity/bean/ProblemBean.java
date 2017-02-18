package cn.com.xibeiuniversity.xibeiuniversity.bean;

import java.io.Serializable;

/**
 * 文件名：ProblemBean
 * 描    述：问题实体类
 * 作    者：stt
 * 时    间：2017.1.3
 * 版    本：V1.0.0
 */

public class ProblemBean implements Serializable {

    /**
     * number : 20170103233
     * state : 1
     * date : 2017.01.03
     * name : 部件问题
     * sender : 张三丰
     * info : 在一楼二教室的灯坏了，请帮忙给换一下，如果来不及请打电话等等
     * executor : 李四
     */

    private String number;
    private String state;
    private String date;
    private String name;
    private String sender;
    private String sendTime;
    private String info;
    private String executor;
    private String executTime;
    private String isRead;

    public String getIsRead() {
        return isRead;
    }

    public void setIsRead(String isRead) {
        this.isRead = isRead;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getExecutTime() {
        return executTime;
    }

    public void setExecutTime(String executTime) {
        this.executTime = executTime;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }

    @Override
    public String toString() {
        return "ProblemBean{" +
                "number='" + number + '\'' +
                ", state='" + state + '\'' +
                ", date='" + date + '\'' +
                ", name='" + name + '\'' +
                ", sender='" + sender + '\'' +
                ", info='" + info + '\'' +
                ", executor='" + executor + '\'' +
                '}';
    }
}
