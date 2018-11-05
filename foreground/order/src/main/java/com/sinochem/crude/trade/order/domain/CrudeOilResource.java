package com.sinochem.crude.trade.order.domain;

import com.sinochem.crude.trade.goods.domain.BodyProperties;
import com.sinochem.crude.trade.goods.enums.CrudeOilIndicator;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import java.util.Date;

/**
 * 油品参照表
 * 
 * @author Leo
 *
 */
public class CrudeOilResource extends BodyProperties<CrudeOilIndicator> {

	/**
	 * 区域
	 */
	private Long area;

	/**
	 * 英文名字
	 */
	@NotEmpty
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
	 * 物性表id
	 */
	private Long propertyId;

	/**
	 * 发布的版本
	 */
	private Date version;

	/**
	 * 备注
	 */
	private String desc;

	/**
	 * 合约id
	 */
	private Long contractId;

	public Date getVersion() {
		return version;
	}

	public void setVersion(Date version) {
		this.version = version;
	}

	public Long getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(Long propertyId) {
		this.propertyId = propertyId;
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

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Long getContractId() {
		return contractId;
	}

	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}
}
