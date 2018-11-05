package com.sinochem.crude.trade.listed.model.vo;

import com.sinochem.crude.trade.listed.domain.DemandShipBerth;
import com.sinochem.crude.trade.listed.helper.DictUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DemandShipBerthVO {
    /**
     * ID
     */
    private Long id;

    /**
     * 泊位名称
     */
    private String berthName;

    /**
     * 泊位等级
     */
    private String berthGrade;

    /**
     * 吃水限制
     */
    private BigDecimal draftLimitation;

    /**
     * 最大载重区间
     */
    private Integer carryingCapacityMax;

    /**
     * 最小载重
     */
    private Integer carryingCapacityMin;

    /**
     * 需求ID
     */
    private Long demandId;

    /**
     * 船型
     */
    private Integer shipType;

    /**
     * 船型 内容
     */
    private String shipTypeContent;

    /**
     * 泊位说明
     */
    private String berthDesc;

    public DemandShipBerthVO () {}
    
    /**
     * 将vo转换为领域模型
     * @return
     */
    public DemandShipBerth convertVoToDomain() {
        DemandShipBerth domain = new DemandShipBerth();
        domain.setBerthDesc(this.getBerthDesc());
        if(this.getBerthGrade() != null) {
            domain.setBerthGrade(Integer.parseInt(this.getBerthGrade()));
        }
        domain.setBerthName(this.getBerthName());
        domain.setCarryingCapacityMax(this.getCarryingCapacityMax());
        domain.setCarryingCapacityMin(this.getCarryingCapacityMin());
        domain.setDemandId(this.getDemandId());
        domain.setDraftLimitation(this.getDraftLimitation());
        domain.setId(this.getId());
        domain.setShipType(this.getShipType());

        return domain;
    }
    
    /**
     * 构造函数
     * 将领域模型转换为vo
     * @param demandShipBerth 需求的领域模型
     */
    public static DemandShipBerthVO convertDomainToVo(DemandShipBerth domain) {
    	DemandShipBerthVO vo = new DemandShipBerthVO();
    	Map<Object, String> shipTypeMap =  DictUtils.getShipTypes();
    	vo.setBerthDesc(domain.getBerthDesc());
    	if(domain.getBerthGrade() != null) {
    		vo.setBerthGrade(domain.getBerthGrade().toString());
    	}
    	vo.setBerthName(domain.getBerthName());
    	vo.setCarryingCapacityMax(domain.getCarryingCapacityMax());
    	vo.setCarryingCapacityMin(domain.getCarryingCapacityMin());
    	vo.setDemandId(domain.getDemandId());
    	vo.setDraftLimitation(domain.getDraftLimitation());
    	vo.setId(domain.getId());
    	
    	vo.setShipType(domain.getShipType());
    	
    	if(domain.getShipType() != null) {
    		vo.setShipTypeContent(shipTypeMap.get(domain.getShipType()));
    	}
    	
    	return vo;
    }

    /**
     * 将vo转换为领域模型
     * @return
     */
    public static List<DemandShipBerthVO> convertListToVo(List<DemandShipBerth> domainList) {
    	if(domainList == null) {
    		return null;
    	}
    	
    	List<DemandShipBerthVO> voList = new ArrayList<DemandShipBerthVO>();
    	for(int i = 0; i < domainList.size(); i++) {
    		voList.add(convertDomainToVo(domainList.get(i)));
    	}
    	
    	return voList;
    }
    
    /**
     * 将vo转换为领域模型
     * @return
     */
    public static List<DemandShipBerth> convertListToDomain(List<DemandShipBerthVO> voList) {
    	if(voList == null) {
    		return null;
    	}
    	
    	List<DemandShipBerth> domainList = new ArrayList<DemandShipBerth>();
    	for(int i = 0; i < voList.size(); i++) {
    		domainList.add(voList.get(i).convertVoToDomain());
    	}
    	
    	return domainList;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBerthName() {
        return berthName;
    }

    public void setBerthName(String berthName) {
        this.berthName = berthName;
    }

    public String getBerthGrade() {
        return berthGrade;
    }

    public void setBerthGrade(String berthGrade) {
        this.berthGrade = berthGrade;
    }

    public BigDecimal getDraftLimitation() {
        return draftLimitation;
    }

    public void setDraftLimitation(BigDecimal draftLimitation) {
        this.draftLimitation = draftLimitation;
    }

    public Integer getCarryingCapacityMax() {
        return carryingCapacityMax;
    }

    public void setCarryingCapacityMax(Integer carryingCapacityMax) {
        this.carryingCapacityMax = carryingCapacityMax;
    }

    public Integer getCarryingCapacityMin() {
        return carryingCapacityMin;
    }

    public void setCarryingCapacityMin(Integer carryingCapacityMin) {
        this.carryingCapacityMin = carryingCapacityMin;
    }

    public Long getDemandId() {
        return demandId;
    }

    public void setDemandId(Long demandId) {
        this.demandId = demandId;
    }

    public Integer getShipType() {
        return shipType;
    }

    public void setShipType(Integer shipType) {
        this.shipType = shipType;
    }
    
    public String getShipTypeContent() {
        return shipTypeContent;
    }

    public void setShipTypeContent(String shipTypeContent) {
        this.shipTypeContent = shipTypeContent;
    }

    public String getBerthDesc() {
        return berthDesc;
    }

    public void setBerthDesc(String berthDesc) {
        this.berthDesc = berthDesc;
    }
}
