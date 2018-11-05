package com.sinochem.crude.trade.orderexecute.controller.openapi.vo;

import java.io.Serializable;

/**
 * 
 * @author Down
 *
 */
public class InputVO implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 来源系统代码 */
	private String sysName;
	
	/**关联会员ID*/
	private Long memberId;

	public String getSysName() {
		return sysName;
	}

	public void setSysName(String sysName) {
		this.sysName = sysName;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
}