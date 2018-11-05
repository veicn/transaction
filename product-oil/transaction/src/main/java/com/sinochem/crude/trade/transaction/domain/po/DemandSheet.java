package com.sinochem.crude.trade.transaction.domain.po;

import com.sinochem.crude.trade.common.base.BasePO;

import java.util.Date;

/**
 * 采购需求单
 * @author Yichen Zhao
 * date: 20180225
 */
public class DemandSheet extends BasePO {

    /**
     * 销售单编号
     */
    private String serialNumber;

    /**
     * 销售单状态（SaleSheetStatusEnum）
     */
    private String demandSheetStatusCode;

    /**
     * 保存时间
     */
    private Date savedDate;

    /**
     * 发布时间
     */
    private Date releasedDate;

    /**
     * 作废时间
     */
    private Date cancelledDate;

    /**
     * 成交时间
     */
    private Date completedDate;

    /**
     * 采购类型（SaleTypeEnum）
     */
    private String demandTypeCode;

    /**
     * 商品来源（ProductSourceEnum，只有当卖家为中化新的时候才会有此选项）
     */
    private String productSourceCode;

    /**
     * 若为询价（定向），则需指定定向的企业ID，以逗号分隔
     */
    private String specifiedEnterpriseIdList;

    /**
     * 发布销售需求单的企业ID
     */
    private Long enterpriseId;

    /**
     * 商品信息ID
     */
    private Long productInfoId;

    /**
     * 价格信息ID
     */
    private Long pricingInfoId;

    /**
     * 运输信息ID
     */
    private Long transportInfoId;

    /**
     * 卖家泊位信息代码
     */
    @Deprecated
    private String shipBerthCode;

    /**
     * 买家信息ID
     */
    private Long stakeholderInfoId;

    /**
     * 其它信息ID
     */
    private Long otherInfoId;

    /** getters and setters */
    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getDemandSheetStatusCode() {
        return demandSheetStatusCode;
    }

    public void setDemandSheetStatusCode(String demandSheetStatusCode) {
        this.demandSheetStatusCode = demandSheetStatusCode;
    }

    public String getSpecifiedEnterpriseIdList() {
        return specifiedEnterpriseIdList;
    }

    public void setSpecifiedEnterpriseIdList(String specifiedEnterpriseIdList) {
        this.specifiedEnterpriseIdList = specifiedEnterpriseIdList;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Long getProductInfoId() {
        return productInfoId;
    }

    public void setProductInfoId(Long productInfoId) {
        this.productInfoId = productInfoId;
    }

    public Long getPricingInfoId() {
        return pricingInfoId;
    }

    public void setPricingInfoId(Long pricingInfoId) {
        this.pricingInfoId = pricingInfoId;
    }

    public Long getTransportInfoId() {
        return transportInfoId;
    }

    public void setTransportInfoId(Long transportInfoId) {
        this.transportInfoId = transportInfoId;
    }

    @Deprecated
    public String getShipBerthCode() {
        return shipBerthCode;
    }

    @Deprecated
    public void setShipBerthCode(String shipBerthCode) {
        this.shipBerthCode = shipBerthCode;
    }

    public Long getStakeholderInfoId() {
        return stakeholderInfoId;
    }

    public void setStakeholderInfoId(Long stakeholderInfoId) {
        this.stakeholderInfoId = stakeholderInfoId;
    }

    public Long getOtherInfoId() {
        return otherInfoId;
    }

    public void setOtherInfoId(Long otherInfoId) {
        this.otherInfoId = otherInfoId;
    }

    public Date getSavedDate() {
        return savedDate;
    }

    public void setSavedDate(Date savedDate) {
        this.savedDate = savedDate;
    }

    public Date getReleasedDate() {
        return releasedDate;
    }

    public void setReleasedDate(Date releasedDate) {
        this.releasedDate = releasedDate;
    }

    public Date getCancelledDate() {
        return cancelledDate;
    }

    public void setCancelledDate(Date cancelledDate) {
        this.cancelledDate = cancelledDate;
    }

    public Date getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(Date completedDate) {
        this.completedDate = completedDate;
    }

    public String getProductSourceCode() {
        return productSourceCode;
    }

    public void setProductSourceCode(String productSourceCode) {
        this.productSourceCode = productSourceCode;
    }

    public String getDemandTypeCode() {
        return demandTypeCode;
    }

    public void setDemandTypeCode(String demandTypeCode) {
        this.demandTypeCode = demandTypeCode;
    }
}
