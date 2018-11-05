package com.sinochem.crude.trade.orderexecute.commons.constants;

/**
 * 合约信息状态值集
 * @author me
 *
 */
public enum TriggerContractStatusEnum {
	STATUS_00("00", "初始"),
//	STATUS_10("10", "已点价"),
//	STATUS_20("20", "已转月"),
//	STATUS_30("30", "已拆单"),
	STATUS_40("40", "已过期");
	
	TriggerContractStatusEnum(String code, String name) {
		this.code = code;
		this.name = name;
	}
	
	private String code;
	private String name;
	public String getCode() {
		return code;
	}
	public String getName() {
		return name;
	}
}
