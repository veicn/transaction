package com.sinochem.crude.trade.listed.domain;

import com.sinochem.crude.trade.goods.domain.BodyProperties;
import com.sinochem.crude.trade.goods.enums.CrudeOilIndicator;
import com.sinochem.crude.trade.order.remote.CrudeOilResource;

import java.util.Date;


/**
 * 油品参照表
 * 
 * @author Leo
 *
 */
public class CrudeOil extends BodyProperties<CrudeOilIndicator> {

	/**
	 * 区域
	 */
	private Long area;

	/**
	 * 字符串地区
	 */
	private String areaString;

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
     * 字符串地区
     */
    private String originName;

	/**
	 * 物性表id
	 */
	private Long propertyId;

	/**
	 * 发布的版本
	 */
	private Date version;

	/**
	 * 序号
	 */
	private Integer orderIndex;

	private Long demandId;

	/**
	 * 备注
	 */
	private String desc;

	public CrudeOilResource convertToCrudeOilResourceVO() {
		CrudeOilResource crudeOilResourceVO = new CrudeOilResource();

		crudeOilResourceVO.setId(this.getId());
		crudeOilResourceVO.setArea(this.getArea());
		crudeOilResourceVO.setName(this.getName());
		crudeOilResourceVO.setCnName(this.getCnName());
		crudeOilResourceVO.setOrigin(this.getOrigin());
		crudeOilResourceVO.setPropertyId(this.getPropertyId());
		crudeOilResourceVO.setVersion(this.getVersion());

		crudeOilResourceVO.setIndicator1Min(this.getIndicator1Min());
		crudeOilResourceVO.setIndicator1Max(this.getIndicator1Max());
		crudeOilResourceVO.setIndicator2Min(this.getIndicator2Min());
		crudeOilResourceVO.setIndicator2Max(this.getIndicator2Max());
		crudeOilResourceVO.setIndicator3Min(this.getIndicator3Min());
		crudeOilResourceVO.setIndicator3Max(this.getIndicator3Max());
		crudeOilResourceVO.setIndicator4Min(this.getIndicator4Min());
		crudeOilResourceVO.setIndicator4Max(this.getIndicator4Max());
		crudeOilResourceVO.setIndicator5Min(this.getIndicator5Min());
		crudeOilResourceVO.setIndicator5Max(this.getIndicator5Max());
		crudeOilResourceVO.setIndicator6Min(this.getIndicator6Min());
		crudeOilResourceVO.setIndicator6Max(this.getIndicator6Max());
		crudeOilResourceVO.setIndicator7Min(this.getIndicator7Min());
		crudeOilResourceVO.setIndicator7Max(this.getIndicator7Max());
		crudeOilResourceVO.setIndicator8Min(this.getIndicator8Min());
		crudeOilResourceVO.setIndicator8Max(this.getIndicator8Max());
		crudeOilResourceVO.setIndicator9Min(this.getIndicator9Min());
		crudeOilResourceVO.setIndicator9Max(this.getIndicator9Max());
		crudeOilResourceVO.setIndicator10Min(this.getIndicator10Min());
		crudeOilResourceVO.setIndicator10Max(this.getIndicator10Max());
		crudeOilResourceVO.setIndicator11Min(this.getIndicator11Min());
		crudeOilResourceVO.setIndicator11Max(this.getIndicator11Max());
		crudeOilResourceVO.setIndicator12Min(this.getIndicator12Min());
		crudeOilResourceVO.setIndicator12Max(this.getIndicator12Max());

		crudeOilResourceVO.setDesc(this.getDesc());

		return crudeOilResourceVO;
	}

	/** getters and setters */
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
	
	public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Long getDemandId() {
		return demandId;
	}

	public void setDemandId(Long demandId) {
		this.demandId = demandId;
	}

	public String getAreaString() {
		return areaString;
	}

	public void setAreaString(String areaString) {
		this.areaString = areaString;
	}

	public Integer getOrderIndex() {
		return orderIndex;
	}

	public void setOrderIndex(Integer orderIndex) {
		this.orderIndex = orderIndex;
	}
}
