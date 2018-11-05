package com.sinochem.crude.trade.orderexecute.commons.constants;

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
    public static final String LANG_VER = "ZH-CN";
    /**船盘状态 1 已发布*/
    public static final String SHIP_PLATE_STATUS_1 = "1";
    /**船盘状态 2 已生成船合同*/
    public static final String SHIP_PLATE_STATUS_2 = "2";
    /**船盘状态 3 航次结束*/
    public static final String SHIP_PLATE_STATUS_3 = "3";
    /**船盘状态 4 已取消*/
    public static final String SHIP_PLATE_STATUS_4 = "4";
    /**货盘状态 1 已申请*/
    public static final String PALLET_STATUS_1 = "1";
    /**货盘状态 2 已生成代理协议*/
    public static final String PALLET_STATUS_2 = "2";
    /**货盘状态 3 航次结束*/
    public static final String PALLET_STATUS_3 = "3";
    /**货盘状态 4 已取消申请*/
    public static final String PALLET_STATUS_4 = "4";
    /**船合同状态 1 未匹配*/
    public static final String SHIP_PACT_STATUS_1 = "1";
    /**船合同状态 2 已匹配*/
    public static final String SHIP_PACT_STATUS_2 = "2";
    /**船合同状态 3 执行中*/
    public static final String SHIP_PACT_STATUS_3 = "3";
    /**船合同状态 4 航次结束*/
    public static final String SHIP_PACT_STATUS_4 = "4";
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
    /**船舶库状态 1 未启用*/
    public static final String SYS_SHIP_STATUS_1 = "1";
    /**船舶库状态 2 已启用*/
    public static final String SYS_SHIP_STATUS_2 = "2";
    /**船舶库状态 3 已作废*/
    public static final String SYS_SHIP_STATUS_3 = "3";
    /**协议状态 1 未匹配*/
    public static final String AGREEMENT_STATUS_1 = "1";
    /**协议状态 2 已匹配*/
    public static final String AGREEMENT_STATUS_2 = "2";
    /**协议状态 3 执行中*/
    public static final String AGREEMENT_STATUS_3 = "3";
    /**协议状态 4 航次结束*/
    public static final String AGREEMENT_STATUS_4 = "4";
    /**协议状态 5 已撤销*/
    public static final String AGREEMENT_STATUS_5 = "5";
    /**运单状态 1 未装货*/
    public static final String WAYBILL_STATUS_1 = "1";
    /**运单状态 2 已装货*/
    public static final String WAYBILL_STATUS_2 = "2";
    /**运单状态 3 已卸港*/
    public static final String WAYBILL_STATUS_3 = "3";
    /**运单状态 4 航次结束*/
    public static final String WAYBILL_STATUS_4 = "4";
    /**运单code seq*/
    public static final String WAYBILL_CODE = "WB";
    /**有订单租船 1*/
    public static final String ORDER_NO_1 = "1";
    /**无订单租船 0*/
    public static final String ORDER_NO_0 = "0";
    
    /**初始*/
    public static final String STATEMENT_STATUS_01 = "01";

    /**待确认*/
    public static final String STATEMENT_STATUS_05 = "05";

    /**已确认*/
    public static final String STATEMENT_STATUS_10 = "10";

    /**已驳回*/
    public static final String STATEMENT_STATUS_20 = "20";

    /**已结算*/
    public static final String STATEMENT_STATUS_30 = "30";
    
    /**无对接系统*/
    public static final int INTERFACE_STATUS_10 = 10;
    
    /**对接系统认证失败*/
    public static final int INTERFACE_STATUS_20 = 20;
    
    /**接口信息不存在*/
    public static final int INTERFACE_STATUS_30 = 30;
    
    /**接口返回错误*/
    public static final int INTERFACE_STATUS_40 = 40;
    
    /**对账类型  1预估 */
    public static final String STATEMENT_TYPE_S = "1";
    /**对账类型  2正式*/
    public static final String STATEMENT_TYPE_I = "2";
    
    /** 结算类型  1预估*/
    public static final String SETTLEMENT_TYPE_S = "1";
    /**结算类型  2正式*/
    public static final String SETTLEMENT_TYPE_I = "2";
    
    /**费用关联类型（0费用 ）*/
    public static final String FEE_TYPE_0 = "0";
    /**费用关联类型（1对账单）*/
    public static final String FEE_TYPE_1 = "1";
    /**费用关联类型（ 2结算单）*/
    public static final String FEE_TYPE_2 = "2";
    
    /**生成预估结算单编号后缀*/
    public static final String SETTLEMENT_FIN = "FIN";
    /**生成正式结算单编号后缀*/
    public static final String SETTLEMENT_PRE = "PRO";
    
    /**合同附件名*/
    public static final String CONTRACT_FILE_NAME = "英文合同";
    
    /**外部系统_接口调用认证*/
    public static final String INTERFACE_AUTH = "AUTH";
    /**获取登录用户信息*/
    public static final String INTERFACE_ME = "ME";
    /**实货录入和修改*/
    public static final String INTERFACE_PHYSICAL_RECAP_UPDATE = "UPDATE_RECAP";
    /**实货合同附件更新*/
    public static final String INTERFACE_PHYSICAL_RECAP_SAVE_CONTRACT = "SAVE_CONTRACT";
    /**执行修改*/
    public static final String INTERFACE_PHYSICAL_OPRETION_UPDATE = "UPDATE_OPRETION";
    /**对账信息 确认*/
    public static final String INTERFACE_UPDATE_DOC_STATUS = "UPDATE_STATUS";
    /**生成结算单*/
    public static final String INTERFACE_SETTLEMENT_SAVE  = "SETTLEMENT_SAVE";
    /**交易大类 1 原油 */
    public static final String ORDER_TRADE_CATEGORY_1 ="1";
    /**交易大类 2 成品油 */
    public static final String ORDER_TRADE_CATEGORY_2 ="2";
    /**交易大类 3 化工品 */
    public static final String ORDER_TRADE_CATEGORY_3 ="3";
    /**是否点价  0 否*/
    public static final String IS_TRIGGER_0 ="0";
    /**是否点价  1 是*/
    public static final String IS_TRIGGER_1 ="1";
    /**
     * 转月费信息  费用科目
     */
    public static final String SUBJECT_NAME_T = "月差费用";
    public static final String SUBJECT_NAME_T_EN = "spread";
    /**
     * 转月费信息  计价方式
     */
    public static final String VALUATION_MODEL_T = "2";
    /**
     * 转月费信息 是否计入结算
     */
    public static final String IS_AGENT_T = "1";
    /**
     * 转月费信息 数量
     */
    public static final int QUANTITY_T = 1;
}
