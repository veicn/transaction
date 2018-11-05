package com.sinochem.crude.trade.shipping.enums;

/**
 * 是否在线确认
 * 
 * @author TaoZhu
 * @date 2018年3月25日下午3:59:07
 */
public enum ConfirmOnlineEnums {
	CONFIRM_ONLINE_NO("1", "否", "NO"),
	CONFIRM_ONLINE_YES("0", "是", "YES");

	/**
	 * 代码
	 */
	private String code;

	/**
	 * 中文名称
	 */
	private String zhName;

	/**
	 * 英文名称
	 */
	private String enName;

	private ConfirmOnlineEnums(String code, String zhName, String enName) {
		this.code = code;
		this.zhName = zhName;
		this.enName = enName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getZhName() {
		return zhName;
	}

	public void setZhName(String zhName) {
		this.zhName = zhName;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

}
