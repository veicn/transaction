package com.sinochem.crude.trade.transaction.domain.po;

import com.sinochem.crude.trade.common.base.BasePO;

import java.util.Date;

/**
 * 报价单
 * @author Yichen Zhao
 * date: 20180225
 */
public class BiddingSheet extends BasePO {

    /**
     * 报价单编号
     */
    private String serialNumber;

    /**
     * 报价单状态
     */
    private String biddingSheetStatusCode;

    /**
     * 发布时间
     */
    private Date releasedDate;

    /**
     * 作废时间
     */
    private Date cancelledDate;

    /**
     * 中标时间
     */
    private Date wonDate;

    /**
     * 未中标时间
     */
    private Date lostDate;

    /**
     * 关联的销售需求单的ID
     */
    private Long saleSheetId;

    /**
     * 关联的销售需求单的UUID
     */
    private String saleSheetUuid;

    /**
     * 关联的采购需求单的ID
     */
    private Long demandSheetId;

    /**
     * 关联的采购需求单的UUID
     */
    private String demandSheetUuid;

    /**
     * 是销售单还是采购单的报价标示
     * 1 销售单  2 采购单
     */
    private String biddingFlag;

    /**
     * 发布报价单的企业ID
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
     * 买家泊位信息代码
     */
    @Deprecated
    private String shipBerthCode;

    /**
     * 买家信息ID
     */
    private Long stakeholderInfoId;

    /**
     * 卖家信息ID
     */
    private Long salerInfoId;



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

    public String getBiddingSheetStatusCode() {
        return biddingSheetStatusCode;
    }

    public void setBiddingSheetStatusCode(String biddingSheetStatusCode) {
        this.biddingSheetStatusCode = biddingSheetStatusCode;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Long getSaleSheetId() {
        return saleSheetId;
    }

    public void setSaleSheetId(Long saleSheetId) {
        this.saleSheetId = saleSheetId;
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

    public Date getWonDate() {
        return wonDate;
    }

    public void setWonDate(Date wonDate) {
        this.wonDate = wonDate;
    }

    public Date getLostDate() {
        return lostDate;
    }

    public void setLostDate(Date lostDate) {
        this.lostDate = lostDate;
    }

    public String getSaleSheetUuid() {
        return saleSheetUuid;
    }

    public void setSaleSheetUuid(String saleSheetUuid) {
        this.saleSheetUuid = saleSheetUuid;
    }

    public Long getDemandSheetId() {
        return demandSheetId;
    }

    public void setDemandSheetId(Long demandSheetId) {
        this.demandSheetId = demandSheetId;
    }

    public String getDemandSheetUuid() {
        return demandSheetUuid;
    }

    public void setDemandSheetUuid(String demandSheetUuid) {
        this.demandSheetUuid = demandSheetUuid;
    }

    public String getBiddingFlag() {
        return biddingFlag;
    }

    public void setBiddingFlag(String biddingFlag) {
        this.biddingFlag = biddingFlag;
    }

    public Long getSalerInfoId() {
        return salerInfoId;
    }

    public void setSalerInfoId(Long salerInfoId) {
        this.salerInfoId = salerInfoId;
    }
}
