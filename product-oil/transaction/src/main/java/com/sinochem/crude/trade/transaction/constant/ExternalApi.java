package com.sinochem.crude.trade.transaction.constant;

/**
 * 外部api的常量类
 * @author Yichen Zhao
 * date: 20180302
 */
public class ExternalApi {

    /* API返回状态 */
    public static final int API_STATUS_SUCCESS = 1;
    public static final int API_STATUS_ERROR = 9999;

    /* API返回字段 */
    public static final String API_STATUS = "status";
    public static final String API_DATAS = "datas";
    public static final String API_MESSAGE = "message";

    /** member模块 */
    /* 获取member信息 */
    public static final String QUERY_MEMBER_INFO = "/member/queryMemberInfo.json";

    /* 获取企业信息 */
    public static final String QUERY_ENTERPRISE_INFO = "/sys/api/enterprise.json";

    /* 获取所有企业列表 */
    public static final String QUERY_ALL_ENTERPRISES = "/centercontain/epListByName.json";
}
