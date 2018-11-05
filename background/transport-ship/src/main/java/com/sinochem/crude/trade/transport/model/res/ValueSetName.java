package com.sinochem.crude.trade.transport.model.res;

import java.io.Serializable;

public class ValueSetName implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**中文名称*/
	private String zhName;
	
	/**英文名称*/
	private String enName;

	/**中文名称*/
	public String getZhName() {
		return zhName;
	}
	/**中文名称*/
	public void setZhName(String zhName) {
		this.zhName = zhName;
	}
	/**英文名称*/
	public String getEnName() {
		return enName;
	}
	/**英文名称*/
	public void setEnName(String enName) {
		this.enName = enName;
	}
}