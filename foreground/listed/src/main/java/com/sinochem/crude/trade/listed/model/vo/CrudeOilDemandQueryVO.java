package com.sinochem.crude.trade.listed.model.vo;

import java.io.Serializable;

/**
 * 筛选FORM对象
 */
public class CrudeOilDemandQueryVO implements Serializable {
    private String purchaseType;
    private String oilType;
    private String oilNumber;
    private String crudePlace;
    private Integer publishType; //采购类型（发布类型），油种/性质
    private Long enterprise; //发布过需求的企业名称

    private String purchaseTypeName;
    private String crudePlaceName;
    private String oilTypeName;
    private String oilNumberName;
    private String publishTypeName;
    private String enterpriseName;

    private String time;
    private String orderByKeys;
    private Integer sort;
    private Integer pageSize;
    private Integer pageNum;

    private Integer specified;
    private String specifiedName;
    private Long epMemberId;
    private String type;
    private String dealType;
    /**
     * 业务类型（原油、成品油）
     */
    private String bizType;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPurchaseType() {
        return purchaseType;
    }

    public void setPurchaseType(String purchaseType) {
        this.purchaseType = purchaseType;
    }

    public String getOilType() {
        return oilType;
    }

    public void setOilType(String oilType) {
        this.oilType = oilType;
    }

    public String getOilNumber() {
        return oilNumber;
    }

    public void setOilNumber(String oilNumber) {
        this.oilNumber = oilNumber;
    }

    public String getCrudePlace() {
        return crudePlace;
    }

    public void setCrudePlace(String crudePlace) {
        this.crudePlace = crudePlace;
    }

    public String getOrderByKeys() {
        return orderByKeys;
    }

    public void setOrderByKeys(String orderByKeys) {
        this.orderByKeys = orderByKeys;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getPurchaseTypeName() {
        return purchaseTypeName;
    }

    public void setPurchaseTypeName(String purchaseTypeName) {
        this.purchaseTypeName = purchaseTypeName;
    }

    public String getCrudePlaceName() {
        return crudePlaceName;
    }

    public void setCrudePlaceName(String crudePlaceName) {
        this.crudePlaceName = crudePlaceName;
    }

    public String getOilTypeName() {
        return oilTypeName;
    }

    public void setOilTypeName(String oilTypeName) {
        this.oilTypeName = oilTypeName;
    }

    public String getOilNumberName() {
        return oilNumberName;
    }

    public void setOilNumberName(String oilNumberName) {
        this.oilNumberName = oilNumberName;
    }

    public Integer getSpecified() {
        return specified;
    }

    public void setSpecified(Integer specified) {
        this.specified = specified;
    }

    public String getSpecifiedName() {
        return specifiedName;
    }

    public void setSpecifiedName(String specifiedName) {
        this.specifiedName = specifiedName;
    }

    public Long getEpMemberId() {
        return epMemberId;
    }

    public void setEpMemberId(Long epMemberId) {
        this.epMemberId = epMemberId;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPublishType() {
        return publishType;
    }

    public void setPublishType(Integer publishType) {
        this.publishType = publishType;
    }

    public Long getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(Long enterprise) {
        this.enterprise = enterprise;
    }

    public String getPublishTypeName() {
        return publishTypeName;
    }

    public void setPublishTypeName(String publishTypeName) {
        this.publishTypeName = publishTypeName;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }
    
    public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDealType() {
		return dealType;
	}

	public void setDealType(String dealType) {
		this.dealType = dealType;
	}
	
	public String getBizType() {
		return bizType;
    }

	public void setBizType(String bizType) {
    	this.bizType = bizType;
    }
}
