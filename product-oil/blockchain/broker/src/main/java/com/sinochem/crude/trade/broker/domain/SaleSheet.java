package com.sinochem.crude.trade.broker.domain;


import java.util.Date;
import java.util.UUID;

/**
 * 销售需求单
 * @author Yichen Zhao
 * date: 20180225
 */
public class SaleSheet{
    protected Long id;
    protected String uuid = UUID.randomUUID().toString();
    protected Date gmtCreated;
    protected Date gmtModified;
    protected Long userCreated;
    protected Long userModified;
    protected Integer aliveFlag = Integer.valueOf(1);
    protected Long version = 0L;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Date getGmtCreated() {
        return this.gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public Date getGmtModified() {
        return this.gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Long getUserCreated() {
        return this.userCreated;
    }

    public void setUserCreated(Long userCreated) {
        this.userCreated = userCreated;
    }

    public Long getUserModified() {
        return this.userModified;
    }

    public void setUserModified(Long userModified) {
        this.userModified = userModified;
    }

    public Integer getAliveFlag() {
        return this.aliveFlag;
    }

    public void setAliveFlag(Integer aliveFlag) {
        this.aliveFlag = aliveFlag;
    }

    public Long getVersion() {
        return this.version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
    /**
     * 销售单编号
     */
    private String serialNumber;

    /**
     * 销售单状态（SaleSheetStatusEnum）
     */
    private String saleSheetStatusCode;

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
     * 销售类型（SaleTypeEnum）
     */
    private String saleTypeCode;

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
     * 卖家信息ID
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

    public String getSaleSheetStatusCode() {
        return saleSheetStatusCode;
    }

    public void setSaleSheetStatusCode(String saleSheetStatusCode) {
        this.saleSheetStatusCode = saleSheetStatusCode;
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

    public String getSaleTypeCode() {
        return saleTypeCode;
    }

    public void setSaleTypeCode(String saleTypeCode) {
        this.saleTypeCode = saleTypeCode;
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
}
