package com.sinochem.crude.trade.listed.model.vo.deprecate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sinochem.crude.trade.listed.domain.CrudeOil;


/**
 * 油品参照表-APP专用
 * 
 * @author Leo
 *
 */
@Deprecated
public class CrudeOilVO implements Serializable {

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

	/**
	 * 指标项（值*100）
	 */
	private String indicator1Min;
	/**
	 * 指标项（值*100）
	 */
	private String indicator1Max;
	/**
	 * 指标项（值*100）
	 */
	private String indicator2Min;
	/**
	 * 指标项（值*100）
	 */
	private String indicator2Max;
	/**
	 * 指标项（值*100）
	 */
	private String indicator3Min;
	/**
	 * 指标项（值*100）
	 */
	private String indicator3Max;
	/**
	 * 指标项（值*100）
	 */
	private String indicator4Min;
	/**
	 * 指标项（值*100）
	 */
	private String indicator4Max;
	/**
	 * 指标项（值*100）
	 */
	private String indicator5Min;
	/**
	 * 指标项（值*100）
	 */
	private String indicator5Max;
	/**
	 * 指标项（值*100）
	 */
	private String indicator6Min;
	/**
	 * 指标项（值*100）
	 */
	private String indicator6Max;
	/**
	 * 指标项（值*100）
	 */
	private String indicator7Min;
	/**
	 * 指标项（值*100）
	 */
	private String indicator7Max;
	/**
	 * 指标项（值*100）
	 */
	private String indicator8Min;
	/**
	 * 指标项（值*100）
	 */
	private String indicator8Max;
	/**
	 * 指标项（值*100）
	 */
	private String indicator9Min;
	/**
	 * 指标项（值*100）
	 */
	private String indicator9Max;
	/**
	 * 指标项（值*100）
	 */
	private String indicator10Min;
	/**
	 * 指标项（值*100）
	 */
	private String indicator10Max;
	/**
	 * 指标项（值*100）
	 */
	private String indicator11Min;
	/**
	 * 指标项（值*100）
	 */
	private String indicator11Max;
	/**
	 * 指标项（值*100）
	 */
	private String indicator12Min;
	/**
	 * 指标项（值*100）
	 */
	private String indicator12Max;

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
	 * 将领域模型转换为vo
	 * 
	 * @return
	 */
	public static CrudeOilVO convertDomainToVo(CrudeOil domain) {
		double division = 100.00;
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
			vo.setIndicator1Min(String.valueOf(domain.getIndicator1Min() / division));
			vo.setIndicator2Min(String.valueOf(domain.getIndicator2Min() / division));
			vo.setIndicator3Min(String.valueOf(domain.getIndicator3Min() / division));
			vo.setIndicator4Min(String.valueOf(domain.getIndicator4Min() / division));
			vo.setIndicator5Min(String.valueOf(domain.getIndicator5Min() / division));
			vo.setIndicator6Min(String.valueOf(domain.getIndicator6Min() / division));
			vo.setIndicator7Min(String.valueOf(domain.getIndicator7Min() / division));
			vo.setIndicator8Min(String.valueOf(domain.getIndicator8Min() / division));
			vo.setIndicator9Min(String.valueOf(domain.getIndicator9Min() / division));
			vo.setIndicator10Min(String.valueOf(domain.getIndicator10Min() / division));
			vo.setIndicator11Min(String.valueOf(domain.getIndicator11Min() / division));
			vo.setIndicator12Min(String.valueOf(domain.getIndicator12Min() / division));
			vo.setIndicator1Max(String.valueOf(domain.getIndicator1Max() / division));
			vo.setIndicator2Max(String.valueOf(domain.getIndicator2Max() / division));
			vo.setIndicator3Max(String.valueOf(domain.getIndicator3Max() / division));
			vo.setIndicator4Max(String.valueOf(domain.getIndicator4Max() / division));
			vo.setIndicator5Max(String.valueOf(domain.getIndicator5Max() / division));
			vo.setIndicator6Max(String.valueOf(domain.getIndicator6Max() / division));
			vo.setIndicator7Max(String.valueOf(domain.getIndicator7Max() / division));
			vo.setIndicator8Max(String.valueOf(domain.getIndicator8Max() / division));
			vo.setIndicator9Max(String.valueOf(domain.getIndicator9Max() / division));
			vo.setIndicator10Max(String.valueOf(domain.getIndicator10Max() / division));
			vo.setIndicator11Max(String.valueOf(domain.getIndicator11Max() / division));
			vo.setIndicator12Max(String.valueOf(domain.getIndicator12Max() / division));
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

	public String getIndicator1Min() {
		return indicator1Min;
	}

	public void setIndicator1Min(String indicator1Min) {
		this.indicator1Min = indicator1Min;
	}

	public String getIndicator1Max() {
		return indicator1Max;
	}

	public void setIndicator1Max(String indicator1Max) {
		this.indicator1Max = indicator1Max;
	}

	public String getIndicator2Min() {
		return indicator2Min;
	}

	public void setIndicator2Min(String indicator2Min) {
		this.indicator2Min = indicator2Min;
	}

	public String getIndicator2Max() {
		return indicator2Max;
	}

	public void setIndicator2Max(String indicator2Max) {
		this.indicator2Max = indicator2Max;
	}

	public String getIndicator3Min() {
		return indicator3Min;
	}

	public void setIndicator3Min(String indicator3Min) {
		this.indicator3Min = indicator3Min;
	}

	public String getIndicator3Max() {
		return indicator3Max;
	}

	public void setIndicator3Max(String indicator3Max) {
		this.indicator3Max = indicator3Max;
	}

	public String getIndicator4Min() {
		return indicator4Min;
	}

	public void setIndicator4Min(String indicator4Min) {
		this.indicator4Min = indicator4Min;
	}

	public String getIndicator4Max() {
		return indicator4Max;
	}

	public void setIndicator4Max(String indicator4Max) {
		this.indicator4Max = indicator4Max;
	}

	public String getIndicator5Min() {
		return indicator5Min;
	}

	public void setIndicator5Min(String indicator5Min) {
		this.indicator5Min = indicator5Min;
	}

	public String getIndicator5Max() {
		return indicator5Max;
	}

	public void setIndicator5Max(String indicator5Max) {
		this.indicator5Max = indicator5Max;
	}

	public String getIndicator6Min() {
		return indicator6Min;
	}

	public void setIndicator6Min(String indicator6Min) {
		this.indicator6Min = indicator6Min;
	}

	public String getIndicator6Max() {
		return indicator6Max;
	}

	public void setIndicator6Max(String indicator6Max) {
		this.indicator6Max = indicator6Max;
	}

	public String getIndicator7Min() {
		return indicator7Min;
	}

	public void setIndicator7Min(String indicator7Min) {
		this.indicator7Min = indicator7Min;
	}

	public String getIndicator7Max() {
		return indicator7Max;
	}

	public void setIndicator7Max(String indicator7Max) {
		this.indicator7Max = indicator7Max;
	}

	public String getIndicator8Min() {
		return indicator8Min;
	}

	public void setIndicator8Min(String indicator8Min) {
		this.indicator8Min = indicator8Min;
	}

	public String getIndicator8Max() {
		return indicator8Max;
	}

	public void setIndicator8Max(String indicator8Max) {
		this.indicator8Max = indicator8Max;
	}

	public String getIndicator9Min() {
		return indicator9Min;
	}

	public void setIndicator9Min(String indicator9Min) {
		this.indicator9Min = indicator9Min;
	}

	public String getIndicator9Max() {
		return indicator9Max;
	}

	public void setIndicator9Max(String indicator9Max) {
		this.indicator9Max = indicator9Max;
	}

	public String getIndicator10Min() {
		return indicator10Min;
	}

	public void setIndicator10Min(String indicator10Min) {
		this.indicator10Min = indicator10Min;
	}

	public String getIndicator10Max() {
		return indicator10Max;
	}

	public void setIndicator10Max(String indicator10Max) {
		this.indicator10Max = indicator10Max;
	}

	public String getIndicator11Min() {
		return indicator11Min;
	}

	public void setIndicator11Min(String indicator11Min) {
		this.indicator11Min = indicator11Min;
	}

	public String getIndicator11Max() {
		return indicator11Max;
	}

	public void setIndicator11Max(String indicator11Max) {
		this.indicator11Max = indicator11Max;
	}

	public String getIndicator12Min() {
		return indicator12Min;
	}

	public void setIndicator12Min(String indicator12Min) {
		this.indicator12Min = indicator12Min;
	}

	public String getIndicator12Max() {
		return indicator12Max;
	}

	public void setIndicator12Max(String indicator12Max) {
		this.indicator12Max = indicator12Max;
	}
	
	
}
