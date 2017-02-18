package cn.com.xibeiuniversity.xibeiuniversity.bean;

import java.io.Serializable;

/**
 * 文件名：NoticeBean
 * 描    述：通知实体类
 * 作    者：stt
 * 时    间：2017.1.6
 * 版    本：V1.0.0
 */

public class NoticeBean implements Serializable {

    /**
     * number : 20170103233
     * date : 2017.01.03
     * name : 安全管理
     * info : 在一楼二教室的灯坏了，请帮忙给换一下，如果来不及请打电话等等
     */

    private String number;
    private String date;
    private String name;
    private String isRead;
    private String info;

    public String getIsRead() {
        return isRead;
    }

    public void setIsRead(String isRead) {
        this.isRead = isRead;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "NoticeBean{" +
                "number='" + number + '\'' +
                ", date='" + date + '\'' +
                ", name='" + name + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
