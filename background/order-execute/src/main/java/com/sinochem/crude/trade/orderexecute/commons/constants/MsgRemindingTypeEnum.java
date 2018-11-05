package com.sinochem.crude.trade.orderexecute.commons.constants;

public enum MsgRemindingTypeEnum {
	/** 站内消息 */
	MSG,
	
	/** 邮件 */
	MAIL,
	
	/** 短信 */
	SMS,
	
	/** 应用推送 */
	APP;
	
	/**
	 * 根据Code获取枚举
	 * 
	 * @param code
	 * @return
	 */
	public static MsgRemindingTypeEnum getByName(String name){
		for(MsgRemindingTypeEnum msgRemindingTypeEnum : MsgRemindingTypeEnum.values()){
			if(msgRemindingTypeEnum.name().equals(name)){
				return msgRemindingTypeEnum;
			}
		}
		
		return null;
	}

}
