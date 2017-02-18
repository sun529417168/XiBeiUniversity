package cn.com.xibeiuniversity.xibeiuniversity.bean.task;

import java.io.Serializable;
import java.util.List;

/**
 * 文件名：TaskBean
 * 描    述：任务列表的实体类
 * 作    者：stt
 * 时    间：2017.1.3
 * 版    本：V1.0.0
 */

public class TaskBean implements Serializable {


    /**
     * total : 24
     * rows : [{"TaskSno":"T201702151019476","TaskName":"文件保存测试","TaskType":1,"TaskTypeName":"巡逻","TaskAddr":"测试","TaskDes":"","PersonName":"刘新航","CreateDate":"/Date(-62135596800000)/","EndDate":"/Date(-62135596800000)/","TaskPriority":1,"TaskPriorityName":"高","TaskState":1,"TaskStateName":"未处理","IsImmediatelyName":"是","PersonID":14,"ID":21,"SearchSNO":"T201702151019476Issued21","StartDate":"/Date(-62135596800000)/","IsCheck":false,"ImageList":[{"FileName":"你的名字.jpg","FileUrl":"Content/FileStore/TasIssuedFile/2017/2/15/6/40127b3d-9d92-4618-8ab3-266fbd80fcd0.jpg","FileID":27,"AttachmentType":1,"SNO":"T201702151019476Issued21"},{"FileName":"数据库原理.xlsx","FileUrl":"Content/FileStore/TasIssuedFile/2017/2/15/6/06cd033f-5396-4de5-b459-991c44530bda.xlsx","FileID":28,"AttachmentType":2,"SNO":"T201702151019476Issued21"}],"TaskAssignedID":41,"CreateDateApi":"2017/2/15 10:19:47","StartDateApi":"2017/2/16 10:18:00","EndDateApi":"2017/2/17 10:18:00"},{"TaskSno":"T201702151019476","TaskName":"文件保存测试","TaskType":1,"TaskTypeName":"巡逻","TaskAddr":"测试","TaskDes":null,"PersonName":"刘新航","CreateDate":"/Date(-62135596800000)/","EndDate":"/Date(-62135596800000)/","TaskPriority":1,"TaskPriorityName":"高","TaskState":1,"TaskStateName":"未处理","IsImmediatelyName":"是","PersonID":14,"ID":21,"SearchSNO":"T201702151019476Issued21","StartDate":"/Date(-62135596800000)/","IsCheck":false,"ImageList":[{"FileName":"你的名字.jpg","FileUrl":"Content/FileStore/TasIssuedFile/2017/2/15/6/40127b3d-9d92-4618-8ab3-266fbd80fcd0.jpg","FileID":27,"AttachmentType":1,"SNO":"T201702151019476Issued21"},{"FileName":"数据库原理.xlsx","FileUrl":"Content/FileStore/TasIssuedFile/2017/2/15/6/06cd033f-5396-4de5-b459-991c44530bda.xlsx","FileID":28,"AttachmentType":2,"SNO":"T201702151019476Issued21"}],"TaskAssignedID":40,"CreateDateApi":"2017/2/15 10:19:47","StartDateApi":"2017/2/16 10:18:00","EndDateApi":"2017/2/17 10:18:00"},{"TaskSno":"T201701201626036","TaskName":"宿舍检查","TaskType":1,"TaskTypeName":"巡逻","TaskAddr":"1号宿舍楼","TaskDes":null,"PersonName":"刘新航","CreateDate":"/Date(-62135596800000)/","EndDate":"/Date(-62135596800000)/","TaskPriority":1,"TaskPriorityName":"高","TaskState":1,"TaskStateName":"未处理","IsImmediatelyName":"是","PersonID":14,"ID":20,"SearchSNO":"T201701201626036Issued20","StartDate":"/Date(-62135596800000)/","IsCheck":false,"ImageList":[{"FileName":"pic_logo_1082168b9.png","FileUrl":null,"FileID":25,"AttachmentType":1,"SNO":"T201701201626036Issued20"},{"FileName":"1.jpg","FileUrl":null,"FileID":26,"AttachmentType":1,"SNO":"T201701201626036Issued20"}],"TaskAssignedID":39,"CreateDateApi":"2017/1/20 16:26:03","StartDateApi":"2017/1/20 16:26:00","EndDateApi":"2017/1/22 16:26:00"},{"TaskSno":"T201701201626036","TaskName":"宿舍检查","TaskType":1,"TaskTypeName":"巡逻","TaskAddr":"1号宿舍楼","TaskDes":null,"PersonName":"刘新航","CreateDate":"/Date(-62135596800000)/","EndDate":"/Date(-62135596800000)/","TaskPriority":1,"TaskPriorityName":"高","TaskState":1,"TaskStateName":"未处理","IsImmediatelyName":"是","PersonID":14,"ID":20,"SearchSNO":"T201701201626036Issued20","StartDate":"/Date(-62135596800000)/","IsCheck":false,"ImageList":[{"FileName":"pic_logo_1082168b9.png","FileUrl":null,"FileID":25,"AttachmentType":1,"SNO":"T201701201626036Issued20"},{"FileName":"1.jpg","FileUrl":null,"FileID":26,"AttachmentType":1,"SNO":"T201701201626036Issued20"}],"TaskAssignedID":38,"CreateDateApi":"2017/1/20 16:26:03","StartDateApi":"2017/1/20 16:26:00","EndDateApi":"2017/1/22 16:26:00"},{"TaskSno":"T201701201051476","TaskName":"设备巡检","TaskType":2,"TaskTypeName":"巡检","TaskAddr":"崇德楼","TaskDes":"崇德楼设备巡检","PersonName":"刘新航","CreateDate":"/Date(-62135596800000)/","EndDate":"/Date(-62135596800000)/","TaskPriority":1,"TaskPriorityName":"高","TaskState":1,"TaskStateName":"未处理","IsImmediatelyName":"是","PersonID":14,"ID":19,"SearchSNO":"T201701201051476Issued19","StartDate":"/Date(-62135596800000)/","IsCheck":false,"ImageList":[{"FileName":"iPhone6 1.jpg","FileUrl":null,"FileID":20,"AttachmentType":1,"SNO":"T201701201051476Issued19"},{"FileName":"灭火器箱.jpg","FileUrl":null,"FileID":21,"AttachmentType":1,"SNO":"T201701201051476Issued19"},{"FileName":"记录日志页面总结.docx","FileUrl":null,"FileID":22,"AttachmentType":2,"SNO":"T201701201051476Issued19"}],"TaskAssignedID":37,"CreateDateApi":"2017/1/20 10:51:47","StartDateApi":"2017/1/21 10:50:00","EndDateApi":"2017/1/23 10:50:00"},{"TaskSno":"T201701201051476","TaskName":"设备巡检","TaskType":2,"TaskTypeName":"巡检","TaskAddr":"崇德楼","TaskDes":"崇德楼设备巡检","PersonName":"刘新航","CreateDate":"/Date(-62135596800000)/","EndDate":"/Date(-62135596800000)/","TaskPriority":1,"TaskPriorityName":"高","TaskState":1,"TaskStateName":"未处理","IsImmediatelyName":"是","PersonID":14,"ID":19,"SearchSNO":"T201701201051476Issued19","StartDate":"/Date(-62135596800000)/","IsCheck":false,"ImageList":[{"FileName":"iPhone6 1.jpg","FileUrl":null,"FileID":20,"AttachmentType":1,"SNO":"T201701201051476Issued19"},{"FileName":"灭火器箱.jpg","FileUrl":null,"FileID":21,"AttachmentType":1,"SNO":"T201701201051476Issued19"},{"FileName":"记录日志页面总结.docx","FileUrl":null,"FileID":22,"AttachmentType":2,"SNO":"T201701201051476Issued19"}],"TaskAssignedID":36,"CreateDateApi":"2017/1/20 10:51:47","StartDateApi":"2017/1/21 10:50:00","EndDateApi":"2017/1/23 10:50:00"},{"TaskSno":"T201701191706006","TaskName":"检查宿舍","TaskType":2,"TaskTypeName":"巡检","TaskAddr":"宿舍楼","TaskDes":null,"PersonName":"刘新航","CreateDate":"/Date(-62135596800000)/","EndDate":"/Date(-62135596800000)/","TaskPriority":1,"TaskPriorityName":"高","TaskState":1,"TaskStateName":"未处理","IsImmediatelyName":"是","PersonID":14,"ID":17,"SearchSNO":"T201701191706006Issued17","StartDate":"/Date(-62135596800000)/","IsCheck":false,"ImageList":[{"FileName":"1.jpg","FileUrl":null,"FileID":17,"AttachmentType":1,"SNO":"T201701191706006Issued17"},{"FileName":"pic_logo_1082168b9.png","FileUrl":null,"FileID":18,"AttachmentType":1,"SNO":"T201701191706006Issued17"}],"TaskAssignedID":31,"CreateDateApi":"2017/1/19 17:06:00","StartDateApi":"2017/1/20 17:04:00","EndDateApi":"2017/1/22 17:04:00"},{"TaskSno":"T201701191706006","TaskName":"检查宿舍","TaskType":2,"TaskTypeName":"巡检","TaskAddr":"宿舍楼","TaskDes":null,"PersonName":"刘新航","CreateDate":"/Date(-62135596800000)/","EndDate":"/Date(-62135596800000)/","TaskPriority":1,"TaskPriorityName":"高","TaskState":1,"TaskStateName":"未处理","IsImmediatelyName":"是","PersonID":14,"ID":17,"SearchSNO":"T201701191706006Issued17","StartDate":"/Date(-62135596800000)/","IsCheck":false,"ImageList":[{"FileName":"1.jpg","FileUrl":null,"FileID":17,"AttachmentType":1,"SNO":"T201701191706006Issued17"},{"FileName":"pic_logo_1082168b9.png","FileUrl":null,"FileID":18,"AttachmentType":1,"SNO":"T201701191706006Issued17"}],"TaskAssignedID":30,"CreateDateApi":"2017/1/19 17:06:00","StartDateApi":"2017/1/20 17:04:00","EndDateApi":"2017/1/22 17:04:00"},{"TaskSno":"T201701191546346","TaskName":"图片文件上传测试","TaskType":3,"TaskTypeName":"其他","TaskAddr":"办公室","TaskDes":"图片文件上传测试","PersonName":"刘新航","CreateDate":"/Date(-62135596800000)/","EndDate":"/Date(-62135596800000)/","TaskPriority":3,"TaskPriorityName":"低","TaskState":1,"TaskStateName":"未处理","IsImmediatelyName":"是","PersonID":14,"ID":16,"SearchSNO":"T201701191546346Issued16","StartDate":"/Date(-62135596800000)/","IsCheck":false,"ImageList":[],"TaskAssignedID":28,"CreateDateApi":"2017/1/19 15:46:34","StartDateApi":"2017/1/19 15:45:00","EndDateApi":"2017/1/19 15:46:00"},{"TaskSno":"T201701191536176","TaskName":"定期巡检","TaskType":2,"TaskTypeName":"巡检","TaskAddr":"行知楼","TaskDes":"定期巡检","PersonName":"刘新航","CreateDate":"/Date(-62135596800000)/","EndDate":"/Date(-62135596800000)/","TaskPriority":2,"TaskPriorityName":"中","TaskState":1,"TaskStateName":"未处理","IsImmediatelyName":"是","PersonID":14,"ID":15,"SearchSNO":"T201701191536176Issued15","StartDate":"/Date(-62135596800000)/","IsCheck":false,"ImageList":[{"FileName":"$$%Z9K$]}C)E2S2)F$5J_ZG.png","FileUrl":null,"FileID":11,"AttachmentType":1,"SNO":"T201701191536176Issued15"},{"FileName":"1.png","FileUrl":null,"FileID":12,"AttachmentType":1,"SNO":"T201701191536176Issued15"},{"FileName":"SVN地址.txt","FileUrl":null,"FileID":13,"AttachmentType":2,"SNO":"T201701191536176Issued15"}],"TaskAssignedID":27,"CreateDateApi":"2017/1/19 15:36:17","StartDateApi":"2017/1/20 15:35:00","EndDateApi":"2017/1/21 15:35:00"}]
     * pageIndex : 0
     * pageSize : 0
     * pages : 0
     * stateEndTime :
     */

    private int total;
    private int pageIndex;
    private int pageSize;
    private int pages;
    private String stateEndTime;
    private List<RowsBean> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getStateEndTime() {
        return stateEndTime;
    }

    public void setStateEndTime(String stateEndTime) {
        this.stateEndTime = stateEndTime;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean implements Serializable{
        /**
         * TaskSno : T201702151019476
         * TaskName : 文件保存测试
         * TaskType : 1
         * TaskTypeName : 巡逻
         * TaskAddr : 测试
         * TaskDes :
         * PersonName : 刘新航
         * CreateDate : /Date(-62135596800000)/
         * EndDate : /Date(-62135596800000)/
         * TaskPriority : 1
         * TaskPriorityName : 高
         * TaskState : 1
         * TaskStateName : 未处理
         * IsImmediatelyName : 是
         * PersonID : 14
         * ID : 21
         * SearchSNO : T201702151019476Issued21
         * StartDate : /Date(-62135596800000)/
         * IsCheck : false
         * ImageList : [{"FileName":"你的名字.jpg","FileUrl":"Content/FileStore/TasIssuedFile/2017/2/15/6/40127b3d-9d92-4618-8ab3-266fbd80fcd0.jpg","FileID":27,"AttachmentType":1,"SNO":"T201702151019476Issued21"},{"FileName":"数据库原理.xlsx","FileUrl":"Content/FileStore/TasIssuedFile/2017/2/15/6/06cd033f-5396-4de5-b459-991c44530bda.xlsx","FileID":28,"AttachmentType":2,"SNO":"T201702151019476Issued21"}]
         * TaskAssignedID : 41
         * CreateDateApi : 2017/2/15 10:19:47
         * StartDateApi : 2017/2/16 10:18:00
         * EndDateApi : 2017/2/17 10:18:00
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

        public List<ImageListBean> getImageList() {
            return ImageList;
        }

        public void setImageList(List<ImageListBean> ImageList) {
            this.ImageList = ImageList;
        }

        public static class ImageListBean implements Serializable{
            /**
             * FileName : 你的名字.jpg
             * FileUrl : Content/FileStore/TasIssuedFile/2017/2/15/6/40127b3d-9d92-4618-8ab3-266fbd80fcd0.jpg
             * FileID : 27
             * AttachmentType : 1
             * SNO : T201702151019476Issued21
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

            @Override
            public String toString() {
                return "ImageListBean{" +
                        "FileName='" + FileName + '\'' +
                        ", FileUrl='" + FileUrl + '\'' +
                        ", FileID=" + FileID +
                        ", AttachmentType=" + AttachmentType +
                        ", SNO='" + SNO + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "RowsBean{" +
                    "TaskSno='" + TaskSno + '\'' +
                    ", TaskName='" + TaskName + '\'' +
                    ", TaskType=" + TaskType +
                    ", TaskTypeName='" + TaskTypeName + '\'' +
                    ", TaskAddr='" + TaskAddr + '\'' +
                    ", TaskDes='" + TaskDes + '\'' +
                    ", PersonName='" + PersonName + '\'' +
                    ", CreateDate='" + CreateDate + '\'' +
                    ", EndDate='" + EndDate + '\'' +
                    ", TaskPriority=" + TaskPriority +
                    ", TaskPriorityName='" + TaskPriorityName + '\'' +
                    ", TaskState=" + TaskState +
                    ", TaskStateName='" + TaskStateName + '\'' +
                    ", IsImmediatelyName='" + IsImmediatelyName + '\'' +
                    ", PersonID=" + PersonID +
                    ", ID=" + ID +
                    ", SearchSNO='" + SearchSNO + '\'' +
                    ", StartDate='" + StartDate + '\'' +
                    ", IsCheck=" + IsCheck +
                    ", TaskAssignedID=" + TaskAssignedID +
                    ", CreateDateApi='" + CreateDateApi + '\'' +
                    ", StartDateApi='" + StartDateApi + '\'' +
                    ", EndDateApi='" + EndDateApi + '\'' +
                    ", ImageList=" + ImageList +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "TaskBean{" +
                "total=" + total +
                ", pageIndex=" + pageIndex +
                ", pageSize=" + pageSize +
                ", pages=" + pages +
                ", stateEndTime='" + stateEndTime + '\'' +
                ", rows=" + rows +
                '}';
    }
}
