package com.sinochem.crude.trade.orderexecute.commons.constants;

/**
 * 合同状态枚举
 * @author me
 *
 */
public enum ContractStatusEnum {
	/** 合同 */
	ORDER_STATUS_1("DR"),
	
	/** 未提交 */
	ORDER_STATUS_ITEM_1_0("0"),
	
	/** 已提交 */
	ORDER_STATUS_ITEM_1_1("1"),
	
	/** 已签订 */
	ORDER_STATUS_ITEM_1_9("9");
	
	private String code;
	ContractStatusEnum(String code){
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
	public static ContractStatusEnum getByCode(String code){
		for(ContractStatusEnum contractStatusEnum : ContractStatusEnum.values()){
			if(contractStatusEnum.getCode().equals(code)){
				return contractStatusEnum;
			}
		}
		
		return null;
	}

}
