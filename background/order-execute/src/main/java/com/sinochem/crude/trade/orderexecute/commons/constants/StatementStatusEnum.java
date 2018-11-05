package com.sinochem.crude.trade.orderexecute.commons.constants;

/**
 * 结算单状态枚举
 * @author me
 *
 */
public enum StatementStatusEnum {
	
	/** 结算单 */
	ORDER_STATUS_3("statement"),
	
	/** 待提交 */
	ORDER_STATUS_ITEM_3_0("0"),
	
	/** 待确认 */
	ORDER_STATUS_ITEM_3_1("1"),
	
	/** 已确认 */
	ORDER_STATUS_ITEM_3_2("2"),
	
	/** 已驳回 */
	ORDER_STATUS_ITEM_3_3("3"),	
	
	/** 已结算 */
	ORDER_STATUS_ITEM_3_9("9");
	
	private String code;
	StatementStatusEnum(String code){
		this.code = code;
	}
	public String getCode() {
		return code;
	}
	
	/**
	 * 根据Code获取枚举
	 * 
	 * @param code
	 * @return
	 */
	public static StatementStatusEnum getByCode(String code){
		for(StatementStatusEnum statementStatusEnum : StatementStatusEnum.values()){
			if(statementStatusEnum.getCode().equals(code)){
				return statementStatusEnum;
			}
		}
		
		return null;
	}

}
