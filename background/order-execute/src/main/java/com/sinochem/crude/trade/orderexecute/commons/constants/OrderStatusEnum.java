package com.sinochem.crude.trade.orderexecute.commons.constants;

/**
 * 订单状态枚举类
 * @author hexinfang
 */
public enum OrderStatusEnum {
	/** 已成交 */
	STATUS_1("05", "已成交"),
	
	/** 已生成租船需求 */
	STATUS_1_2("09", "已生成租船需求"),
	
	/** 已租船 */
	STATUS_2("10", "已租船"),
	
	/** 已装船 */
	STATUS_3("15", "已装船"),
	
	/** 已到港 */
	STATUS_4("20", "已到港"),
	
	/** 已卸货 */
	STATUS_5("25", "已卸货"),
	
	/** 已对账 */
	STATUS_6("30", "已对账"),
	
	/** 已收款 */
	STATUS_7("35", "已收款"),
	
	/** 已取消 */
	STATUS_8("00", "已取消");
	
	
	private String code;
	private String value;
	OrderStatusEnum(String code, String value){
		this.code = code;
		this.value = value;
	}
	public String getCode() {
		return code;
	}
	public String getValue() {
		return value;
	}
	
	/**
	 * 根据Code获取枚举
	 * 
	 * @param code
	 * @return
	 */
	public static OrderStatusEnum getByCode(String code){
		for(OrderStatusEnum orderStatus : OrderStatusEnum.values()){
			if(orderStatus.getCode().equals(code)){
				return orderStatus;
			}
		}
		
		return null;
	}
}
