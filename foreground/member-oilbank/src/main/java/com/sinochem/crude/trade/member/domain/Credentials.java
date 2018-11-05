package com.sinochem.crude.trade.member.domain;

/**
 * 资质对象,不好入库，xml
 * 
 * @author 胡凌
 *
 */
public class Credentials {

	/**
	 * PK,资质编码
	 */
	private String code;

	/**
	 * 资质名称
	 */
	private String name;

	/**
	 * 资质英文名称
	 */
	private String nameEn;


	public String getUdbCode() {
		return udbCode;
	}

	public void setUdbCode(String udbCode) {
		this.udbCode = udbCode;
	}

	/**
	 * udb映射的code
	 */
	private String udbCode;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}
}
