package com.sinochem.crude.trade.orderexecute.commons.constants;

/**
 * 货款状态枚举
 * @author me
 *
 */
public enum FundStatusEnum {
	
	/** 货款 */
	ORDER_STATUS_4("fund"),
	
	/** 未支付 */
	ORDER_STATUS_ITEM_4_0("0"),
	
	/** 部分支付 */
	ORDER_STATUS_ITEM_4_1("1"),
	
	/** 全部支付 */
	ORDER_STATUS_ITEM_4_9("9");
	
	private String code;
	FundStatusEnum(String code){
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
	public static FundStatusEnum getByCode(String code){
		for(FundStatusEnum fundStatusEnum : FundStatusEnum.values()){
			if(fundStatusEnum.getCode().equals(code)){
				return fundStatusEnum;
			}
		}
		
		return null;
	}

}
