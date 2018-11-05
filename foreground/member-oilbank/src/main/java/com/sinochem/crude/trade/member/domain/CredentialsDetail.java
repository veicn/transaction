package com.sinochem.crude.trade.member.domain;

/**
 * 资质和角色关系表，如果一个角色需要有资质限制，存储到这个对象,不要入库，xml
 * 
 * @author 胡凌
 *
 */
public class CredentialsDetail {
	
	/**
	 * 如果一个资质要对全局进行限制，roles = "ALL";
	 */
	public static final String ALL = "ALL";

	/**
	 * 资质编码
	 */
	private String code;

	/**
	 * role，可能批量设定资质
	 */
	private String[] roles;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String[] getRoles() {
		return roles;
	}

	public void setRoles(String[] roles) {
		this.roles = roles;
	}

}
