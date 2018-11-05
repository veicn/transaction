package com.sinochem.crude.trade.transaction.domain.po;

import com.sinochem.crude.trade.common.base.BasePO;

import java.util.Date;

/**
 * 订单，实际上是合约单，为防未来拓展，命名为Contract，并且叫法上统称为合约单
 * @author Yichen Zhao
 * date: 20180227
 */
public class ContractSheet extends BasePO {

    /**
     * 合约单编号
     */
    private String serialNumber;

    /**
     * 销售单ID，此处做数据冗余，因为销售单ID可通过报价单带出
     */
    private Long saleSheetId;

    /**
     * 销售单编号，此处做数据冗余
     */
    private String saleSheetUuid;

    /**
     * 报价单ID
     */
    private Long biddingSheetId;

    /**
     * 报价单编号，此处做数据冗余
     */
    private String biddingSheetUuid;

    /**
     * 合约单的状态
     */
    private String contractSheetStatusCode;

    public Date getContractDate() {
        return contractDate;
    }

    public void setContractDate(Date contractDate) {
        this.contractDate = contractDate;
    }

    /**
     * 合同时间
     */
    private Date contractDate;


    /**
     * 确认时间
     */
    private Date confirmedDate;

    /**
     * 完成时间
     */
    private Date completedDate;

    /**
     * 基本信息ID
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
     * 泊位信息ID
     */
    @Deprecated
    private String shipBerthCode;

    /**
     * 卖家信息ID
     */
    private Long salerInfoId;

    /**
     * 卖家名字
     */
    private String salerName;

    /**
     * 卖家ID，做数据冗余
     */
    private Long salerId;

    /**
     * 买家信息ID
     */
    private Long buyerInfoId;


    public String getSalerName() {
        return salerName;
    }

    public void setSalerName(String salerName) {
        this.salerName = salerName;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    /**
     * 买家名字
     */
    private String buyerName;

    /**
     * 买家ID，做数据冗余
     */
    private Long buyerId;

    /**
     * 其它信息ID
     */
    private Long otherInfoId;

    /**
     * COQ Refinery文件地址
     */
    private String coqRefinery;

    /**
     * COQ Refinery文件名
     */
    private String coqRefineryName;

    /**
     * COQ Shore Tanks文件地址
     */
    private String coqShoreTanks;

    /**
     * COQ Shore Tanks文件名
     */
    private String coqShoreTanksName;

    /**
     * CIQ文件地址
     */
    private String ciq;

    /**
     * CIQ文件名
     */
    private String ciqName;

    /**
     * Loading Survey Report的文件地址
     */
    private String loadingSurveyReport;

    /**
     * Loading Survey Report的文件名
     */
    private String loadingSurveyReportName;

    /** getters and setters */
    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Long getSaleSheetId() {
        return saleSheetId;
    }

    public void setSaleSheetId(Long saleSheetId) {
        this.saleSheetId = saleSheetId;
    }

    public String getSaleSheetUuid() {
        return saleSheetUuid;
    }

    public void setSaleSheetUuid(String saleSheetUuid) {
        this.saleSheetUuid = saleSheetUuid;
    }

    public Long getBiddingSheetId() {
        return biddingSheetId;
    }

    public void setBiddingSheetId(Long biddingSheetId) {
        this.biddingSheetId = biddingSheetId;
    }

    public String getBiddingSheetUuid() {
        return biddingSheetUuid;
    }

    public void setBiddingSheetUuid(String biddingSheetUuid) {
        this.biddingSheetUuid = biddingSheetUuid;
    }

    public String getContractSheetStatusCode() {
        return contractSheetStatusCode;
    }

    public void setContractSheetStatusCode(String contractSheetStatusCode) {
        this.contractSheetStatusCode = contractSheetStatusCode;
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

    public Long getSalerInfoId() {
        return salerInfoId;
    }

    public void setSalerInfoId(Long salerInfoId) {
        this.salerInfoId = salerInfoId;
    }

    public Long getBuyerInfoId() {
        return buyerInfoId;
    }

    public void setBuyerInfoId(Long buyerInfoId) {
        this.buyerInfoId = buyerInfoId;
    }

    public Long getOtherInfoId() {
        return otherInfoId;
    }

    public void setOtherInfoId(Long otherInfoId) {
        this.otherInfoId = otherInfoId;
    }

    public Date getConfirmedDate() {
        return confirmedDate;
    }

    public void setConfirmedDate(Date confirmedDate) {
        this.confirmedDate = confirmedDate;
    }

    public Date getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(Date completedDate) {
        this.completedDate = completedDate;
    }

    public Long getSalerId() {
        return salerId;
    }

    public void setSalerId(Long salerId) {
        this.salerId = salerId;
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }

    public String getCoqRefinery() {
        return coqRefinery;
    }

    public void setCoqRefinery(String coqRefinery) {
        this.coqRefinery = coqRefinery;
    }

    public String getCoqRefineryName() {
        return coqRefineryName;
    }

    public void setCoqRefineryName(String coqRefineryName) {
        this.coqRefineryName = coqRefineryName;
    }

    public String getCoqShoreTanks() {
        return coqShoreTanks;
    }

    public void setCoqShoreTanks(String coqShoreTanks) {
        this.coqShoreTanks = coqShoreTanks;
    }

    public String getCoqShoreTanksName() {
        return coqShoreTanksName;
    }

    public void setCoqShoreTanksName(String coqShoreTanksName) {
        this.coqShoreTanksName = coqShoreTanksName;
    }

    public String getCiq() {
        return ciq;
    }

    public void setCiq(String ciq) {
        this.ciq = ciq;
    }

    public String getCiqName() {
        return ciqName;
    }

    public void setCiqName(String ciqName) {
        this.ciqName = ciqName;
    }

    public String getLoadingSurveyReport() {
        return loadingSurveyReport;
    }

    public void setLoadingSurveyReport(String loadingSurveyReport) {
        this.loadingSurveyReport = loadingSurveyReport;
    }

    public String getLoadingSurveyReportName() {
        return loadingSurveyReportName;
    }

    public void setLoadingSurveyReportName(String loadingSurveyReportName) {
        this.loadingSurveyReportName = loadingSurveyReportName;
    }
}
