package com.sinochem.crude.trade.transport.commons.constants;


/**
 * Created by x on 2017/11/7.
 */
public class Constants {
	
	/**系统异常message */
    public static final String EXCEPTION_MESSAGE = "系统异常";
    /**系统异常status */
    public static final Integer EXCEPTION_STATUS = 9999;
    /**系统异常code */
    public static final String EXCEPTION_CODE = "9999";
	/**有效状态 */
    public static final String ALIEVE_FLAG_YES = "1";
    /**无效状态 */
    public static final String ALIEVE_FLAG_NO = "0";
    /**语言类型*/
    public static final String LANG_VER = "zh";
    
    /**船盘状态 1 已发布*/
    public static final String SHIP_PLATE_STATUS_1 = "1";
    /**船盘状态 2 洽谈中*/
    public static final String SHIP_PLATE_STATUS_2 = "2";
    /**船盘状态 3 已确认租船*/
    public static final String SHIP_PLATE_STATUS_3 = "3";
    
    /**货盘状态 1 待处理*/
    public static final String PALLET_STATUS_1 = "1";
    /**货盘状态 2 处理中*/
    public static final String PALLET_STATUS_2 = "2";
    /**货盘状态 3 已报盘*/
    public static final String PALLET_STATUS_3 = "3";
    /**货盘状态 4 已确认*/
    public static final String PALLET_STATUS_4 = "4";
    /**货盘状态 5 已关闭*/
    public static final String PALLET_STATUS_5 = "5";
    /**货盘状态 6 已完成*/
    public static final String PALLET_STATUS_6 = "6";
    
    /**船合同状态 1 未执行*/
    public static final String SHIP_PACT_STATUS_1 = "1";
    /**船合同状态 2 确认执行*/
    public static final String SHIP_PACT_STATUS_2 = "2";
    /**船合同状态 3 航次开始*/
    public static final String SHIP_PACT_STATUS_3 = "3";
    /**船合同状态 4 已装港*/
    public static final String SHIP_PACT_STATUS_4 = "4";
    /**船合同状态 5 航行中*/
    public static final String SHIP_PACT_STATUS_5 = "5";
    /**船合同状态 6 已卸港*/
    public static final String SHIP_PACT_STATUS_6 = "6";
    /**船合同状态 7 航次结束*/
    public static final String SHIP_PACT_STATUS_7 = "7";
    /**船合同状态 8 已结算*/
    public static final String SHIP_PACT_STATUS_8 = "8";
    /**船合同细分状态 1 航次未开始*/
    public static final String SHIP_PACT_DETAIL_STATUS_1 = "1";
    /**船合同细分状态 2 航次开始*/
    public static final String SHIP_PACT_DETAIL_STATUS_2 = "2";
    /**船合同细分状态 3 已装港*/
    public static final String SHIP_PACT_DETAIL_STATUS_3 = "3";
    /**船合同细分状态 4 在途*/
    public static final String SHIP_PACT_DETAIL_STATUS_4 = "4";
    /**船合同细分状态 5 已卸港*/
    public static final String SHIP_PACT_DETAIL_STATUS_5 = "5";
    /**船合同细分状态 6 航次结束*/
    public static final String SHIP_PACT_DETAIL_STATUS_6 = "6";
    /**船舶库状态 1 待审核*/
    public static final String SYS_SHIP_STATUS_1 = "1";
    /**船舶库状态 2 有效*/
    public static final String SYS_SHIP_STATUS_2 = "2";
    /**船舶库状态 3 驳回*/
    public static final String SYS_SHIP_STATUS_3 = "3";
    /**协议状态 1 未执行*/
    public static final String AGREEMENT_STATUS_1 = "1";
    /**协议状态 2 确认执行*/
    public static final String AGREEMENT_STATUS_2 = "2";
    /**协议状态 3 航次开始*/
    public static final String AGREEMENT_STATUS_3 = "3";
    /**协议状态 4 已装港*/
    public static final String AGREEMENT_STATUS_4 = "4";
    /**协议状态 5 航行中*/
    public static final String AGREEMENT_STATUS_5 = "5";
    /**协议状态 6 已卸港*/
    public static final String AGREEMENT_STATUS_6 = "6";
    /**协议状态7 航次结束*/
    public static final String AGREEMENT_STATUS_7 = "7";
    /**协议状态 8 已结算*/
    public static final String AGREEMENT_STATUS_8 = "8";
    /**运单状态 1 未装货*/
    public static final String WAYBILL_STATUS_1 = "1";
    /**运单状态 2 已装货*/
    public static final String WAYBILL_STATUS_2 = "2";
    /**运单状态 3 已卸港*/
    public static final String WAYBILL_STATUS_3 = "3";
    /**运单状态 4 航次结束*/
    public static final String WAYBILL_STATUS_4 = "4";
    /**询盘状态 1已询盘*/
    public static final String INTENTION_STATUS_1 = "1";
    /**询盘状态 2已还盘*/
    public static final String INTENTION_STATUS_2 = "2";
    /**询盘状态 3已确认租船*/
    public static final String INTENTION_STATUS_3 = "3";
    /**询盘状态 4已关闭*/
    public static final String INTENTION_STATUS_4 = "4";
    /**报盘状态 1待确认*/
    public static final String CLAUSE_STATUS_1 = "1";
    /**报盘状态 2已确认*/
    public static final String CLAUSE_STATUS_2 = "2";
    /**报盘状态 3已关闭*/
    public static final String CLAUSE_STATUS_3 = "3";
    /**运单code seq*/
    public static final String WAYBILL_CODE = "WB";
    /**油品合同号*/
    public static final String PALLET_CODE = "SQ";
    /**有订单租船 1*/
    public static final String ORDER_NO_1 = "1";
    /**无订单租船 0*/
    public static final String ORDER_NO_0 = "0";
    /**先选船盘*/
    public static final String ORDER_SHIPPALLET_1 = "1";
    /**先发需求*/
    public static final String ORDER_SHIPPALLET_0 = "0";
    /**需求类型 1租船*/
    public static final String PALLET_TYPE_1 = "1";
    /**需求类型 2拼船*/
    public static final String PALLET_TYPE_2 = "2";
    /**值集  经纪人 BROKER*/
    public static final String VALUE_SET_1 = "BROKER";
    /**值集 装港商检  LOAD_CHECK*/
    public static final String VALUE_SET_2 = "LOAD_CHECK";
    /**值集 卸港商检 UN_LOAD_CHECK*/
    public static final String VALUE_SET_3 = "UN_LOAD_CHECK";
    /**值集  卸港 UN_LOAD_PORT*/
    public static final String VALUE_SET_4 = "UN_LOAD_PORT";
    /**值集 装港 LOAD_PORT*/
    public static final String VALUE_SET_5 = "LOAD_PORT";
    /**值集 卸港船代 UN_LOAD_AGENT*/
    public static final String VALUE_SET_6 = "UN_LOAD_AGENT";
    /**值集 卸港船监 UN_LOAD_MONITOR*/
    public static final String VALUE_SET_7 = "UN_LOAD_MONITOR";
    /**值集 船东 SHIP_OWNER*/
    public static final String VALUE_SET_8 = "SHIP_OWNER";
    /**值集 船型 SHIP_TYPE*/
    public static final String VALUE_SET_9 = "SHIP_TYPE";
    /**值集 油种 OIL_TYPE*/
    public static final String VALUE_SET_10 = "OIL_TYPE";
    /**值集 装港船代 LOAD_AGENT*/
    public static final String VALUE_SET_11 = "LOAD_AGENT";
    /**值集 大洲 TIDE_CONTINENT*/
    public static final String VALUE_SET_12 = "TIDE_CONTINENT";
    /**值集 船龄 SHIP_AGE*/
    public static final String VALUE_SET_13 = "SHIP_AGE";
    /**值集 单位换算类型 UNIT_TYPE*/
    public static final String VALUE_SET_14 = "UNIT_TYPE";
    /**值集 基础运价年份BASIC_YEAR*/
    public static final String VALUE_SET_15 = "BASIC_YEAR";
    /**值集 船位SHIP_PLACE*/
    public static final String VALUE_SET_16 = "SHIP_PLACE";
    /**值集 港口TIDE_PORT*/
    public static final String VALUE_SET_17 = "TIDE_PORT";
    /**值集  指定转租船东APPOINT_SHIPPER*/
    public static final String VALUE_SET_18 = "APPOINT_SHIPPER";
    /**值集  装港数量单位：吨(中文)*/
    public static final String VALUE_SET_19 = "吨";
    /**值集  装港数量单位：吨(英文)*/
    public static final String VALUE_SET_20 = "BBL";
    /**值集  装港数量单位：桶(中文)*/
    public static final String VALUE_SET_21 = "桶";
    /**值集  装港数量单位：桶(英文)*/
    public static final String VALUE_SET_22 = "MT";
    
    /**角色 交易管理员 trade_admin*/
    public static final String TRADE_ADMIN = "trade_admin";
    /**角色 业务操作员 trade_oper*/
    public static final String TRADE_OPER = "trade_oper";
    /**角色 租船代理 ship_agent*/
    public static final String SHIP_AGENT = "ship_agent";
    /**角色 货主 cargo_owner*/
    public static final String CARGO_OWNER = "cargo_owner";//cargo_owner
    /**角色 租船家 Charterer*/
    public static final String CHARTERER = "charterer";
    /**角色 优化人员 optimization*/
    public static final String OPTIMIZATION = "optimization";
    /**角色 企业负责人 enter_master*/
    public static final String ENTER_MASTER = "enter_master"; //enter_master
    /**角色 系统管理员 admin*/
    public static final String ADMIN = "admin";
    /**角色 内容维护人员 info_oper*/
    public static final String INFO_OPER = "info_oper";
    /**角色 企业管理员 enter_admin*/
    public static final String ENTER_ADMIN = "enter_admin";
    
    /**角色 租船代理(交易) ship_trader*/
    public static final String SHIP_TRADER = "ship_trader";
    /**角色 租船代理(执行) ship_executor*/
    public static final String SHIP_EXECUTOR = "ship_executor";
    /**角色 执行员 trade_executor*/
    public static final String TRADE_EXECUTOR = "trade_executor";
    /**角色 销售交易员    sales_trader*/
    public static final String SALES_TRADER = "sales_trader";
    /**角色 采购交易员    buy_trader*/
    public static final String BUY_TRADER = "buy_trader";
    /**角色 商检    inspection*/
    public static final String INSPECTION = "inspection";
    /**角色 装港船代    depa_port*/
    public static final String DEPA_PORT = "depa_port";
    /**角色 卸港船代    arriv_port*/
    public static final String ARRIV_PORT = "arriv_port";
    /**角色 经纪人    ship_broker*/
    public static final String SHIP_BROKER = "ship_broker";
    /**角色 船东 ship_owner*/
    public static final String SHIP_OWNER = "ship_owner";
    /**会员资质  7 转租船东*/
    public static final String MEMBER_APTITUDE_7 = "7";
    
    
    
    
    
    
    
    
    
    ///////////////////////////////////中文常量///////////////////////////////////////////////
    
    /**附件信息新增成功*/
    public static final String message_001 = "CONSTANTS001";//附件信息新增成功
    /**船舶附件信息删除成功*/
    public static final String message_002 = "CONSTANTS002";//船舶附件信息删除成功
    /**此数据已经不存在*/
    public static final String message_003 = "CONSTANTS003";//此数据已经不存在
    /**请不要插入重复数据*/
    public static final String message_004 = "请不要插入重复数据";//请不要插入重复数据
    /**您要删除的数据已经被操作，请刷新后重试*/
    public static final String message_005 = "您要删除的数据已经被操作，请刷新后重试";//您要删除的数据已经被操作，请刷新后重试
    /**无此数据*/
    public static final String message_0061 = "此数据已经不存在";//此数据已经不存在
    /*报盘新增成功*/
    public static final String message_007 = "CONSTANTS006";//报盘新增成功
    /*确认报盘成功*/
    public static final String message_008 = "CONSTANTS007";//确认报盘成功
    /*撤销成功*/
    public static final String message_009 = "CONSTANTS008";//撤销成功
    /*您操作的数据已经发生变化，请刷新后重试*/
    public static final String message_010 = "CONSTANTS009";//您操作的数据已经发生变化，请刷新后重试
    /*询盘成功*/
    public static final String message_011 = "CONSTANTS010";//询盘成功
    /*询盘终止成功*/
    public static final String message_012 = "CONSTANTS011";//终止成功
    /*询盘信息修改成功*/
    public static final String message_013 = "CONSTANTS012";//询盘信息修改成功
    /*确认询盘成功*/
    public static final String message_014 = "CONSTANTS013";//确认询盘成功
    /*船盘信息新增成功*/
    public static final String message_015 = "CONSTANTS014";//船盘信息新增成功
    /*船盘信息修改成功*/
    public static final String message_016 = "CONSTANTS015";//船盘信息修改成功
    /*船盘撤销成功*/
    public static final String message_017 = "CONSTANTS016";//船盘撤销成功
    /*船盘发布成功*/
    public static final String message_018 = "CONSTANTS017";//船盘发布成功
    /*船盘删除成功*/
    public static final String message_019 = "CONSTANTS018";//船盘删除成功
    /*平台船舶信息新增成功*/
    public static final String message_020 = "CONSTANTS019";//船舶信息新增成功
    /*平台船舶信息修改成功*/
    public static final String message_021 = "CONSTANTS020";//船舶信息修改成功
    /*平台船舶状态修改成功*/
    public static final String message_022 = "CONSTANTS021";//船舶状态修改成功
    /*平台船舶信息删除成功*/
    public static final String message_023 = "CONSTANTS022";//船舶信息删除成功
    /*潮汐信息新增成功*/
    public static final String message_024 = "潮汐信息新增成功";
    /*潮汐信息修改成功*/
    public static final String message_025 = "潮汐信息修改成功";
    /*潮汐信息删除成功*/
    public static final String message_026 = "潮汐信息删除成功";
    /*已经存在该中文名称的单位，无法重复添加*/
    public static final String message_027 = "已经存在该中文名称的单位，无法重复添加";
    /*该对象存在关联关系，请删除关系后再操作*/
    public static final String message_028 = "该对象存在关联关系，请删除关系后再操作";
    /*您操作的数据正在被修改，请稍后再试*/
    public static final String message_029 = "您操作的数据正在被修改，请稍后再试";
    /*已经存在比例，无需重复插入*/
    public static final String message_030 = "已经存在比例，无需重复插入";
    /*您刚操作的数据已经改变，请重新操作*/
    public static final String message_031 = "您刚操作的数据已经改变，请重新操作";
    /*无此比率*/
    public static final String message_032 = "无此比率";
    /*洽谈中*/
    public static final String message_033_zh = "洽谈中";//CONSTANTS032
    /*已成交*/
    public static final String message_034_zh = "已成交";//CONSTANTS033
    /*洽谈中*/
    public static final String message_033_en = "during negotiation";//CONSTANTS032
    /*已成交*/
    public static final String message_034_en = "A deal has been concluded.";//CONSTANTS033
    
    
    
    
    
    
    
    
}
