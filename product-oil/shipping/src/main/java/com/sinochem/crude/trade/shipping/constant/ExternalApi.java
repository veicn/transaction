package com.sinochem.crude.trade.shipping.constant;

/**
 * 外部api的常量类
 * @author Yichen Zhao
 * date: 20180302
 */
public class ExternalApi {

	//租船需求单状态
	/**
	 * 待生成租船协议
	 */
	public static final String DEMAND_10 = "10";
	/**
	 * 待租船协议确认
	 */
	public static final String DEMAND_20 = "20";
	/**
	 * 租船协议已确认
	 */
	public static final String DEMAND_30 = "30";
	/**
	 * 进行中
	 */
	public static final String DEMAND_40 = "40";
	/**
	 * 已完成
	 */
	public static final String DEMAND_50 = "50";
	
	
	//------------------------租船协议状态-----------------------------------
	
	/**租船协议状态
	 * 待确认
	 */
	public static final String AGREEMENT_10 = "10";
	
	/**租船协议状态
	 * 已确认
	 */
	public static final String AGREEMENT_20 = "20";
	
	/**
	 * 租船协议已确认-船舶确认单待确认
	 */
	public static final String AGREEMENT_21 = "21";
	/**
	 * 租船协议已确认-船舶确认单已确认
	 */
	public static final String AGREEMENT_22 = "22";
	
	/**租船协议状态
	 * 航次开始
	 */
	public static final String AGREEMENT_30 = "30";
	
	/**租船协议状态
	 * 已装港
	 */
	public static final String AGREEMENT_40 = "40";
	
	/**租船协议状态
	 * 在途中
	 */
	public static final String AGREEMENT_50 = "50";
	
	/**租船协议状态
	 * 已卸港
	 */
	public static final String AGREEMENT_60 = "60";
	/**租船协议状态
	 * 航次结束
	 */
	public static final String AGREEMENT_70 = "70";
	/**租船协议状态
	 * 已中止
	 */
	public static final String AGREEMENT_80 = "80";
	
	//------------------------------租船确认单状态-----------------------------------------------
	
	
	/**
	 * 租船确认单状态
	 * 待确认
	 */
	public static final String CONFIRM_10 = "10";
	
	/**
	 * 租船确认单状态
	 * 已确认
	 */
	public static final String CONFIRM_20 = "20";
	
	/**
	 * 租船确认单状态
	 * 航次开始
	 */
	public static final String CONFIRM_30 = "30";
	
	/**租船确认单状态
	 * 已装港
	 */
	public static final String CONFIRM_40 = "40";
	
	/**租船确认单状态
	 * 在途中
	 */
	public static final String CONFIRM_50 = "50";
	
	/**租船确认单状态
	 * 已卸港
	 */
	public static final String CONFIRM_60 = "60";
	/**租船确认单状态
	 * 航次结束
	 */
	public static final String CONFIRM_70 = "70";
	/**租船确认单状态
	 * 已中止
	 */
	public static final String CONFIRM_80 = "80";



	public static final String WE_CHAT_URL="/notification.json";

	/** member模块 */
    /* 获取member信息 */
	public static final String QUERY_MEMBER_INFO = "/member/queryMemberInfo.json";

	/* 获取企业信息 */
	public static final String QUERY_ENTERPRISE_INFO = "/sys/api/enterprise.json";
	/* 获取所有企业列表 */
	public static final String QUERY_ALL_ENTERPRISES = "/centercontain/epListByName.json";

	/* API返回状态 */
	public static final int API_STATUS_SUCCESS = 1;
	/* API返回字段 */
	public static final String API_STATUS = "status";
	public static final String API_DATAS = "datas";
	public static final String API_MESSAGE = "message";
}
