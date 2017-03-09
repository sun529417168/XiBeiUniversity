package cn.com.xibeiuniversity.xibeiuniversity.bean.statistical;

/**
 * 文件名：TaskStatistical
 * 描    述：任务下发统计
 * 作    者：stt
 * 时    间：2017.3.9
 * 版    本：V1.0.5
 */

public class TaskStatisticalBean {
    private String name;
    private int value;
    private String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "TaskStatistical{" +
                "name='" + name + '\'' +
                ", value=" + value +
                ", code='" + code + '\'' +
                '}';
    }
}
