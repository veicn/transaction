package com.sinochem.crude.trade.orderexecute.model;

import java.io.Serializable;

/**
 * 外部系统用户VO
 * @author Administrator
 *
 */
public class UserVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**外部系统别名*/
	private String sysName; 
	
	/**
	 * 账号
	 */
	private String account;
	
	/**
	 * 密码
	 */
	private String password;

	public String getSysName() {
		return sysName;
	}

	public void setSysName(String sysName) {
		this.sysName = sysName;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}