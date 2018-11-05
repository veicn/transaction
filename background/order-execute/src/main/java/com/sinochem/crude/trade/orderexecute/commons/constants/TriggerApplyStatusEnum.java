package com.sinochem.crude.trade.orderexecute.commons.constants;

/**
 * 点价申请状态值集
 * @author me
 *
 */
public enum TriggerApplyStatusEnum {
	STATUS_10("10", "已申请"),
	STATUS_20("20", "未成功"),
	STATUS_30("30", "已拆单"),
	STATUS_40("40", "已撤销");
	
	TriggerApplyStatusEnum(String code, String name) {
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
	
	/**
	 * 根据Code获取枚举
	 * 
	 * @param code
	 * @return
	 */
	public static TriggerApplyStatusEnum getByCode(String code){
		for(TriggerApplyStatusEnum item : TriggerApplyStatusEnum.values()){
			if(item.getCode().equals(code)){
				return item;
			}
		}
		
		return null;
	}

}
