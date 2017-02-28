package cn.com.xibeiuniversity.xibeiuniversity.bean.task;

import java.io.Serializable;
import java.util.List;

/**
 * 文件名：TaskAssignedBean
 * 描    述：任务反馈信息的实体类
 * 作    者：stt
 * 时    间：2017.2.23
 * 版    本：V1.0.0
 */

public class TaskAssignedBean implements Serializable {

    /**
     * ID : 20
     * TaskSno :
     * PersonID : 14
     * PersonName : 刘新航
     * GroupId : 0
     * FeedBackDate :
     * GroupIdName :
     * FeedBackContent : android.widget.Button{b12ee6 VFED..C.. ...P.... 28,1645-1052,1755 #7f0d00cd app:id/task_detail_button}
     * IsCheck : true
     * IsCheckName :
     * FeedbackState : 0
     * FeedbackStateName :
     * IsCheckTime :
     * SearchSno :
     * ImageList : [{"FileName":"1487844214163.jpg","FileUrl":"http://123.56.96.237:8001/Content/FileStore/TaskAssignedFile/2017/2/23/1/0d465b99-d765-4bde-bdca-8919df713a7e.jpg","FileID":24,"AttachmentType":1,"SNO":"T201702231714341220"},{"FileName":"1487844225574.jpg","FileUrl":"http://123.56.96.237:8001/Content/FileStore/TaskAssignedFile/2017/2/23/1/b4edc9d8-de65-489e-a068-1b6e17496252.jpg","FileID":25,"AttachmentType":1,"SNO":"T201702231714341220"}]
     * FeedBackDateApi : 2017/2/23 18:03:53
     * IsCheckTimeApi : 2017/2/23 17:15:25
     */

    private int ID;
    private String TaskSno;
    private int PersonID;
    private String PersonName;
    private int GroupId;
    private String FeedBackDate;
    private String GroupIdName;
    private String FeedBackContent;
    private boolean IsCheck;
    private String IsCheckName;
    private int FeedbackState;
    private String FeedbackStateName;
    private String IsCheckTime;
    private String SearchSno;
    private String FeedBackDateApi;
    private String IsCheckTimeApi;
    private List<ImageListBean> ImageList;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTaskSno() {
        return TaskSno;
    }

    public void setTaskSno(String TaskSno) {
        this.TaskSno = TaskSno;
    }

    public int getPersonID() {
        return PersonID;
    }

    public void setPersonID(int PersonID) {
        this.PersonID = PersonID;
    }

    public String getPersonName() {
        return PersonName;
    }

    public void setPersonName(String PersonName) {
        this.PersonName = PersonName;
    }

    public int getGroupId() {
        return GroupId;
    }

    public void setGroupId(int GroupId) {
        this.GroupId = GroupId;
    }

    public String getFeedBackDate() {
        return FeedBackDate;
    }

    public void setFeedBackDate(String FeedBackDate) {
        this.FeedBackDate = FeedBackDate;
    }

    public String getGroupIdName() {
        return GroupIdName;
    }

    public void setGroupIdName(String GroupIdName) {
        this.GroupIdName = GroupIdName;
    }

    public String getFeedBackContent() {
        return FeedBackContent;
    }

    public void setFeedBackContent(String FeedBackContent) {
        this.FeedBackContent = FeedBackContent;
    }

    public boolean isIsCheck() {
        return IsCheck;
    }

    public void setIsCheck(boolean IsCheck) {
        this.IsCheck = IsCheck;
    }

    public String getIsCheckName() {
        return IsCheckName;
    }

    public void setIsCheckName(String IsCheckName) {
        this.IsCheckName = IsCheckName;
    }

    public int getFeedbackState() {
        return FeedbackState;
    }

    public void setFeedbackState(int FeedbackState) {
        this.FeedbackState = FeedbackState;
    }

    public String getFeedbackStateName() {
        return FeedbackStateName;
    }

    public void setFeedbackStateName(String FeedbackStateName) {
        this.FeedbackStateName = FeedbackStateName;
    }

    public String getIsCheckTime() {
        return IsCheckTime;
    }

    public void setIsCheckTime(String IsCheckTime) {
        this.IsCheckTime = IsCheckTime;
    }

    public String getSearchSno() {
        return SearchSno;
    }

    public void setSearchSno(String SearchSno) {
        this.SearchSno = SearchSno;
    }

    public String getFeedBackDateApi() {
        return FeedBackDateApi;
    }

    public void setFeedBackDateApi(String FeedBackDateApi) {
        this.FeedBackDateApi = FeedBackDateApi;
    }

    public String getIsCheckTimeApi() {
        return IsCheckTimeApi;
    }

    public void setIsCheckTimeApi(String IsCheckTimeApi) {
        this.IsCheckTimeApi = IsCheckTimeApi;
    }

    public List<ImageListBean> getImageList() {
        return ImageList;
    }

    public void setImageList(List<ImageListBean> ImageList) {
        this.ImageList = ImageList;
    }

    public static class ImageListBean {
        /**
         * FileName : 1487844214163.jpg
         * FileUrl : http://123.56.96.237:8001/Content/FileStore/TaskAssignedFile/2017/2/23/1/0d465b99-d765-4bde-bdca-8919df713a7e.jpg
         * FileID : 24
         * AttachmentType : 1
         * SNO : T201702231714341220
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
