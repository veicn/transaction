package com.sinochem.crude.trade.goods.domain;

import java.util.Date;

import com.sinochem.crude.trade.goods.enums.CrudeOilIndicator;

/**
 * 油品参照表
 * 
 * @author Leo
 *
 */
public class CrudeOilReference extends BodyProperties<CrudeOilIndicator> {

	/**
	 * 区域
	 */
	private Long area;

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

	public Date getVersion() {
		return version;
	}

	public void setVersion(Date version) {
		this.version = version;
	}

	public Long getArea() {
		return area;
	}

	public void setArea(Long area) {
		this.area = area;
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
