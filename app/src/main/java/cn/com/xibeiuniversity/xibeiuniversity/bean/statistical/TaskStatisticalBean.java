package cn.com.xibeiuniversity.xibeiuniversity.bean.statistical;

import java.util.List;

/**
 * 文件名：TaskStatistical
 * 描    述：任务下发统计
 * 作    者：stt
 * 时    间：2017.3.9
 * 版    本：V1.0.5
 */

public class TaskStatisticalBean {


    /**
     * list : [{"name":"逾期","value":1,"code":""}]
     * module :
     * StartingTime : 2017-03-06
     * endTime : 2017-03-12
     */

    private String module;
    private String StartingTime;
    private String endTime;
    private List<ListBean> list;

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getStartingTime() {
        return StartingTime;
    }

    public void setStartingTime(String StartingTime) {
        this.StartingTime = StartingTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * name : 逾期
         * value : 1
         * code :
         */

        private String name;
        private int value;
        private String code;

        public ListBean() {
        }

        public ListBean(String name, int value) {
            this.name = name;
            this.value = value;
        }

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
    }
}
