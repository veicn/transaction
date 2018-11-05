package com.sinochem.crude.trade.orderexecute.commons.constants;

/**
 * 物流状态枚举
 * @author me
 *
 */
public enum TransportStatusEnum {
	
	/** 物流状态 */
	ORDER_STATUS_2("delivery"),
	
	/** 未租船 */
	ORDER_STATUS_ITEM_2_0("0"),
	
	/** 已租船 */
	ORDER_STATUS_ITEM_2_1("1"),
	
	/** 已装船 */
	ORDER_STATUS_ITEM_2_2("2"),
	
	/** 已到港 */
	ORDER_STATUS_ITEM_2_3("3"),	
	
	/** 已卸港 */
	ORDER_STATUS_ITEM_2_9("9");
	
	private String code;
	TransportStatusEnum(String code){
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
	public static TransportStatusEnum getByCode(String code){
		for(TransportStatusEnum transportStatusEnum : TransportStatusEnum.values()){
			if(transportStatusEnum.getCode().equals(code)){
				return transportStatusEnum;
			}
		}
		
		return null;
	}

}
