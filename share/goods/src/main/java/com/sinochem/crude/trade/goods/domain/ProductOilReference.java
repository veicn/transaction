package com.sinochem.crude.trade.goods.domain;

import java.util.Date;

import com.sinochem.crude.trade.goods.enums.ProductOilIndicator;

/**
 * 成品油索引
 * 
 * @author Leo
 *
 */
public class ProductOilReference extends BodyProperties<ProductOilIndicator>{

	/**
	 * 油号
	 */
	private String NO;

	/**
	 * 英文名字
	 */
	private String name;

	/**
	 * 中文名字
	 */
	private String cnName;

	/**
	 * 产地
	 */
	private Long origin;

	/**
	 * 发布的版本
	 */
	private Date version;

	public String getNO() {
		return NO;
	}

	public void setNO(String NO) {
		this.NO = NO;
	}

	public Date getVersion() {
		return version;
	}

	public void setVersion(Date version) {
		this.version = version;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCnName() {
		return cnName;
	}

	public void setCnName(String cnName) {
		this.cnName = cnName;
	}

	public Long getOrigin() {
		return origin;
	}

	public void setOrigin(Long origin) {
		this.origin = origin;
	}
}