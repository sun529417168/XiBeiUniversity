package cn.com.xibeiuniversity.xibeiuniversity.config;

/**
 * 文件名：UrlConfig
 * 描    述：接口的Url地址
 * 作    者：stt
 * 时    间：2017.1.18
 * 版    本：V1.0.0
 */

public class UrlConfig {
    //    public static final String BaseUrl = "http://123.56.96.237:8001";// 正式
    public static final String BaseUrl = "http://192.168.0.90:8001";// 测试

    /**
     * 登陆接口返回token的
     */
    public static final String URL_LOGIN = BaseUrl + "/API/Login/LoginbySSO";
    /**
     * 返回人员基本信息
     */
    public static final String URL_GETPERSONINFO = BaseUrl + "/API/ApiPersonInfo/GetPersonInfo";
    /**
     * 完善人员信息接口
     */
    public static final String URL_EDITTEXTUSERINFO = BaseUrl + "/API/ApiPersonInfo/UodatePersonInfoOrAndPassWord";
    /**
     * 判断密码是否正确
     */
    public static final String URL_ISCHECKPASSWORD = BaseUrl + "/API/ApiPersonInfo/IsCheckPassWord";
    /**
     * 任务下发列表
     */
    public static final String URL_GETTASISSUEDLIST = BaseUrl + "/API/ApiTasIssued/GetTasIssuedList";
    /**
     * 点击修改查阅状态
     */
    public static final String URL_ISCHECK = BaseUrl + "/API/ApiTasIssued/IsCheckState";
    /**
     * 任务反馈信息
     */
    public static final String URL_TASKASSIGNEDINFO = BaseUrl + "/API/ApiTasIssued/ImgUpload";
    /**
     * 问题列表
     */
    public static final String URL_GETPROBLEMREPORTINFO = BaseUrl + "/API/ApiProblemReport/GetProblemReportInfo";
    /**
     * 新增问题返回信息+图片
     */
    public static final String URL_IMGUPLOAD = BaseUrl + "/API/ApiProblemReport/ImgUpload";
    /**
     * 修改密码
     */
    public static final String URL_UPDATEPASSWORD = BaseUrl + "/API/ApiPersonInfo/UpdatePassWord";
    /**
     * 获取任务反馈信息
     */
    public static final String URL_GETTASKASSIGNEDINFO = BaseUrl + "/API/ApiTasIssued/GetTaskAssignedInfo";
    /**
     * 获取一级节点信息
     */
    public static final String URL_GETBEGINNINGENTITY = BaseUrl + "/API/ApiProblemReport/GetBeginningEntity";
    /**
     * 获取二级节点信息（根据code）
     */
    public static final String URL_GETTYPELISTBYCODE = BaseUrl + "/API/ApiProblemReport/GetTypeListByCode";
    /**
     * 任务下发统计
     */
    public static final String URL_TASISSUEDDATASECTOR = BaseUrl + "/API/ApiTasIssued/DataSector";
}
