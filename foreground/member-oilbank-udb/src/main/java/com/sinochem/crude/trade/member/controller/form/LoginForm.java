package com.sinochem.crude.trade.member.controller.form;

import javax.validation.constraints.NotNull;

/**
 * 用户VO对象
 */
public class LoginForm {
	/**
	 * 用户名
	 */
	@NotNull
	private String userName;
	/**
	 * 密码
	 */
	@NotNull
	private String password;

	private String newPassword;

	private String checkCode;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}
}
