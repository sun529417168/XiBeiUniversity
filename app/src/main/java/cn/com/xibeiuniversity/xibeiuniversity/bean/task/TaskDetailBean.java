package cn.com.xibeiuniversity.xibeiuniversity.bean.task;

import java.io.Serializable;
import java.util.List;

/**
 * 文件名：TaskDetailBean
 * 描    述：任务详情的实体类
 * 作    者：stt
 * 时    间：2017.3.23
 * 版    本：V1.0.7
 */

public class TaskDetailBean implements Serializable {

    /**
     * TaskSno : T201703081702581
     * TaskName : 监控室任务
     * TaskType : 1
     * TaskTypeName : 巡逻
     * TaskAddr : 监控室
     * TaskDes : 监控室是否干净整洁，器材是否破损，整理所有文档并。并且上传图片和文件归纳/\.,''.,*$%!优先级为重，开始时间为2017-03-08，各种加入编程符号，
     * PersonName : 张雷
     * CreateDate : /Date(-62135596800000)/
     * EndDate : /Date(-62135596800000)/
     * TaskPriority : 2
     * TaskPriorityName : 中
     * TaskState : 5
     * TaskStateName : 逾期
     * IsImmediatelyName : 是
     * PersonID : 104
     * ID : 34
     * SearchSNO : T201703081702581134
     * StartDate : /Date(-62135596800000)/
     * IsCheck : false
     * ImageList : [{"FileName":"11926768,800,450.jpg","FileUrl":"http://192.168.0.90:8001/Content/FileStore/TasIssuedFile/2017/3/8/1/7eebcd6f-00c6-41d6-a94e-48d22d1818c2.jpg","FileID":86,"AttachmentType":1,"SNO":"T201703081702581134"},{"FileName":"手机端统计.rar","FileUrl":"http://192.168.0.90:8001/Content/FileStore/TasIssuedFile/2017/3/8/1/111b90d0-97bb-43cd-ab46-b8026f6c3bb9.rar","FileID":87,"AttachmentType":2,"SNO":"T201703081702581134"}]
     * TaskAssignedID : 0
     * CreateDateApi : 2017/3/8 17:02:58
     * StartDateApi : 2017/3/8 16:59:38
     * EndDateApi : 2017/3/24 17:02:00
     * TaskAssignedState : 0
     * TaskAssignedStateName :
     */

    private String TaskSno;
    private String TaskName;
    private int TaskType;
    private String TaskTypeName;
    private String TaskAddr;
    private String TaskDes;
    private String PersonName;
    private String CreateDate;
    private String EndDate;
    private int TaskPriority;
    private String TaskPriorityName;
    private int TaskState;
    private String TaskStateName;
    private String IsImmediatelyName;
    private int PersonID;
    private int ID;
    private String SearchSNO;
    private String StartDate;
    private boolean IsCheck;
    private int TaskAssignedID;
    private String CreateDateApi;
    private String StartDateApi;
    private String EndDateApi;
    private int TaskAssignedState;
    private String TaskAssignedStateName;
    private List<ImageListBean> ImageList;

    public String getTaskSno() {
        return TaskSno;
    }

    public void setTaskSno(String TaskSno) {
        this.TaskSno = TaskSno;
    }

    public String getTaskName() {
        return TaskName;
    }

    public void setTaskName(String TaskName) {
        this.TaskName = TaskName;
    }

    public int getTaskType() {
        return TaskType;
    }

    public void setTaskType(int TaskType) {
        this.TaskType = TaskType;
    }

    public String getTaskTypeName() {
        return TaskTypeName;
    }

    public void setTaskTypeName(String TaskTypeName) {
        this.TaskTypeName = TaskTypeName;
    }

    public String getTaskAddr() {
        return TaskAddr;
    }

    public void setTaskAddr(String TaskAddr) {
        this.TaskAddr = TaskAddr;
    }

    public String getTaskDes() {
        return TaskDes;
    }

    public void setTaskDes(String TaskDes) {
        this.TaskDes = TaskDes;
    }

    public String getPersonName() {
        return PersonName;
    }

    public void setPersonName(String PersonName) {
        this.PersonName = PersonName;
    }

    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String CreateDate) {
        this.CreateDate = CreateDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String EndDate) {
        this.EndDate = EndDate;
    }

    public int getTaskPriority() {
        return TaskPriority;
    }

    public void setTaskPriority(int TaskPriority) {
        this.TaskPriority = TaskPriority;
    }

    public String getTaskPriorityName() {
        return TaskPriorityName;
    }

    public void setTaskPriorityName(String TaskPriorityName) {
        this.TaskPriorityName = TaskPriorityName;
    }

    public int getTaskState() {
        return TaskState;
    }

    public void setTaskState(int TaskState) {
        this.TaskState = TaskState;
    }

    public String getTaskStateName() {
        return TaskStateName;
    }

    public void setTaskStateName(String TaskStateName) {
        this.TaskStateName = TaskStateName;
    }

    public String getIsImmediatelyName() {
        return IsImmediatelyName;
    }

    public void setIsImmediatelyName(String IsImmediatelyName) {
        this.IsImmediatelyName = IsImmediatelyName;
    }

    public int getPersonID() {
        return PersonID;
    }

    public void setPersonID(int PersonID) {
        this.PersonID = PersonID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getSearchSNO() {
        return SearchSNO;
    }

    public void setSearchSNO(String SearchSNO) {
        this.SearchSNO = SearchSNO;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String StartDate) {
        this.StartDate = StartDate;
    }

    public boolean isIsCheck() {
        return IsCheck;
    }

    public void setIsCheck(boolean IsCheck) {
        this.IsCheck = IsCheck;
    }

    public int getTaskAssignedID() {
        return TaskAssignedID;
    }

    public void setTaskAssignedID(int TaskAssignedID) {
        this.TaskAssignedID = TaskAssignedID;
    }

    public String getCreateDateApi() {
        return CreateDateApi;
    }

    public void setCreateDateApi(String CreateDateApi) {
        this.CreateDateApi = CreateDateApi;
    }

    public String getStartDateApi() {
        return StartDateApi;
    }

    public void setStartDateApi(String StartDateApi) {
        this.StartDateApi = StartDateApi;
    }

    public String getEndDateApi() {
        return EndDateApi;
    }

    public void setEndDateApi(String EndDateApi) {
        this.EndDateApi = EndDateApi;
    }

    public int getTaskAssignedState() {
        return TaskAssignedState;
    }

    public void setTaskAssignedState(int TaskAssignedState) {
        this.TaskAssignedState = TaskAssignedState;
    }

    public String getTaskAssignedStateName() {
        return TaskAssignedStateName;
    }

    public void setTaskAssignedStateName(String TaskAssignedStateName) {
        this.TaskAssignedStateName = TaskAssignedStateName;
    }

    public List<ImageListBean> getImageList() {
        return ImageList;
    }

    public void setImageList(List<ImageListBean> ImageList) {
        this.ImageList = ImageList;
    }

    public static class ImageListBean implements Serializable {
        /**
         * FileName : 11926768,800,450.jpg
         * FileUrl : http://192.168.0.90:8001/Content/FileStore/TasIssuedFile/2017/3/8/1/7eebcd6f-00c6-41d6-a94e-48d22d1818c2.jpg
         * FileID : 86
         * AttachmentType : 1
         * SNO : T201703081702581134
         */

        private String FileName;
        private String FileUrl;
        private int FileID;
        private int AttachmentType;
        private String SNO;

        public String getFileName() {
            return FileName;
        }

        public void setFileName(String FileName) {
            this.FileName = FileName;
        }

        public String getFileUrl() {
            return FileUrl;
        }

        public void setFileUrl(String FileUrl) {
            this.FileUrl = FileUrl;
        }

        public int getFileID() {
            return FileID;
        }

        public void setFileID(int FileID) {
            this.FileID = FileID;
        }

        public int getAttachmentType() {
            return AttachmentType;
        }

        public void setAttachmentType(int AttachmentType) {
            this.AttachmentType = AttachmentType;
        }

        public String getSNO() {
            return SNO;
        }

        public void setSNO(String SNO) {
            this.SNO = SNO;
        }
    }
}
