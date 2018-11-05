package com.sinochem.crude.trade.listed.model.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sinochem.crude.trade.goods.domain.BodyProperties;
import com.sinochem.crude.trade.goods.enums.CrudeOilIndicator;
import com.sinochem.crude.trade.listed.domain.CrudeOil;


/**
 * 油品参照表
 * 
 * @author Leo
 *
 */
public class CrudeOilVO extends BodyProperties<CrudeOilIndicator> implements Serializable {

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


	public CrudeOilVO() {
	}

	/**
	 * 构造函数 将领域模型转换为vo
	 * 
	 * @param demandRelevanter
	 *            需求的领域模型
	 */
	public CrudeOilVO(CrudeOil domain) {
		this.area = domain.getArea();
		this.areaString = domain.getAreaString();
		this.name = domain.getName();
		this.cnName = domain.getCnName();
		this.origin = domain.getOrigin();
		this.originName = domain.getAreaString();
		this.propertyId = domain.getPropertyId();
		this.version = domain.getVersion();
		this.orderIndex = domain.getOrderIndex();
		this.demandId = domain.getDemandId();
		this.desc = domain.getDesc();
		
	}
	
	/**
	 * 将vo转换为领域模型
	 * 
	 * @return
	 */
	public static CrudeOil convertVoToDomain(CrudeOilVO vo) {
		CrudeOil domain = new CrudeOil();
		if (vo != null) {
			domain.setArea(vo.getArea());
			domain.setAreaString(vo.getAreaString());
			domain.setName(vo.getName());
			domain.setCnName(vo.getCnName());
			domain.setOrigin(vo.getOrigin());
			domain.setOriginName(vo.getOriginName());
			domain.setPropertyId(vo.getPropertyId());
			domain.setVersion(vo.getVersion());
			domain.setOrderIndex(vo.getOrderIndex());
			domain.setDemandId(vo.getDemandId());
			domain.setDesc(vo.getDesc());
			domain.setIndicator1Min(vo.getIndicator1Min());
			domain.setIndicator2Min(vo.getIndicator2Min());
			domain.setIndicator3Min(vo.getIndicator3Min());
			domain.setIndicator4Min(vo.getIndicator4Min());
			domain.setIndicator5Min(vo.getIndicator5Min());
			domain.setIndicator6Min(vo.getIndicator6Min());
			domain.setIndicator7Min(vo.getIndicator7Min());
			domain.setIndicator8Min(vo.getIndicator8Min());
			domain.setIndicator9Min(vo.getIndicator9Min());
			domain.setIndicator10Min(vo.getIndicator10Min());
			domain.setIndicator11Min(vo.getIndicator11Min());
			domain.setIndicator12Min(vo.getIndicator12Min());
			domain.setIndicator1Max(vo.getIndicator1Max());
			domain.setIndicator2Max(vo.getIndicator2Max());
			domain.setIndicator3Max(vo.getIndicator3Max());
			domain.setIndicator4Max(vo.getIndicator4Max());
			domain.setIndicator5Max(vo.getIndicator5Max());
			domain.setIndicator6Max(vo.getIndicator6Max());
			domain.setIndicator7Max(vo.getIndicator7Max());
			domain.setIndicator8Max(vo.getIndicator8Max());
			domain.setIndicator9Max(vo.getIndicator9Max());
			domain.setIndicator10Max(vo.getIndicator10Max());
			domain.setIndicator11Max(vo.getIndicator11Max());
			domain.setIndicator12Max(vo.getIndicator12Max());
		}
		
		return domain;
	}
	
	/**
	 * 将领域模型转换为vo
	 * 
	 * @return
	 */
	public static CrudeOilVO convertDomainToVo(CrudeOil domain) {
		CrudeOilVO vo = new CrudeOilVO();
		
		if (domain != null) {
			vo.area = domain.getArea();
			vo.areaString = domain.getAreaString();
			vo.name = domain.getName();
			vo.cnName = domain.getCnName();
			vo.origin = domain.getOrigin();
			vo.originName = domain.getAreaString();
			vo.propertyId = domain.getPropertyId();
			vo.version = domain.getVersion();
			vo.orderIndex = domain.getOrderIndex();
			vo.demandId = domain.getDemandId();
			vo.desc = domain.getDesc();
			vo.setIndicator1Min(domain.getIndicator1Min());
			vo.setIndicator2Min(domain.getIndicator2Min());
			vo.setIndicator3Min(domain.getIndicator3Min());
			vo.setIndicator4Min(domain.getIndicator4Min());
			vo.setIndicator5Min(domain.getIndicator5Min());
			vo.setIndicator6Min(domain.getIndicator6Min());
			vo.setIndicator7Min(domain.getIndicator7Min());
			vo.setIndicator8Min(domain.getIndicator8Min());
			vo.setIndicator9Min(domain.getIndicator9Min());
			vo.setIndicator10Min(domain.getIndicator10Min());
			vo.setIndicator11Min(domain.getIndicator11Min());
			vo.setIndicator12Min(domain.getIndicator12Min());
			vo.setIndicator1Max(domain.getIndicator1Max());
			vo.setIndicator2Max(domain.getIndicator2Max());
			vo.setIndicator3Max(domain.getIndicator3Max());
			vo.setIndicator4Max(domain.getIndicator4Max());
			vo.setIndicator5Max(domain.getIndicator5Max());
			vo.setIndicator6Max(domain.getIndicator6Max());
			vo.setIndicator7Max(domain.getIndicator7Max());
			vo.setIndicator8Max(domain.getIndicator8Max());
			vo.setIndicator9Max(domain.getIndicator9Max());
			vo.setIndicator10Max(domain.getIndicator10Max());
			vo.setIndicator11Max(domain.getIndicator11Max());
			vo.setIndicator12Max(domain.getIndicator12Max());
		}

		return vo;
	}
	
	/**
	 * 将领域模型转换为vo(列表)
	 * 
	 * @return
	 */
	public static List<CrudeOilVO> convertDomainToVoList(List<CrudeOil> domainList) {
		
		List<CrudeOilVO> voList = new ArrayList<CrudeOilVO>();
		
		if (domainList != null && domainList.size() > 0) {
			for (int i = 0; i < domainList.size(); i++) {
				CrudeOilVO vo = new CrudeOilVO();
				vo = convertDomainToVo(domainList.get(i));
				voList.add(vo);
			}
		}

		return voList;
	}

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
