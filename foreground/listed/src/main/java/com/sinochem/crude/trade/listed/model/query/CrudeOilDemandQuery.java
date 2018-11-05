package com.sinochem.crude.trade.listed.model.query;

/**
 * @Description:
 * @Author : chenyz
 * @Date: 2017/11/26
 */
public class CrudeOilDemandQuery {
    private String purchaseType;
    private String oilType;
    private Long oilNumberMin;
    private Long oilNumberMax;
    private String crudePlace;

    private String time;
    private String orderByKeys;
    private Integer sort;

    private Integer specified;
    private Long epMemberId;
    private Integer publishType;
    private Long enterpriseId;
    private String type;
    private String dealType;

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

    public Long getOilNumberMin() {
        return oilNumberMin;
    }

    public void setOilNumberMin(Long oilNumberMin) {
        this.oilNumberMin = oilNumberMin;
    }

    public Long getOilNumberMax() {
        return oilNumberMax;
    }

    public void setOilNumberMax(Long oilNumberMax) {
        this.oilNumberMax = oilNumberMax;
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

    public Integer getSpecified() {
        return specified;
    }

    public void setSpecified(Integer specified) {
        this.specified = specified;
    }

    public Long getEpMemberId() {
        return epMemberId;
    }

    public void setEpMemberId(Long epMemberId) {
        this.epMemberId = epMemberId;
    }

    public Integer getPublishType() {
        return publishType;
    }

    public void setPublishType(Integer publishType) {
        this.publishType = publishType;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
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
}
