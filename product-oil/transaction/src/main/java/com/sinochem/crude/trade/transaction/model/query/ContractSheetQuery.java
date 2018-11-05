package com.sinochem.crude.trade.transaction.model.query;

import java.util.Date;

/**
 * 合约单（订单）查询条件对象
 * @author Yichen Zhao
 * date: 20180307
 */
public class ContractSheetQuery {

    /**
     * 商品分类
     */
    private String productCategoryCode;

    public String getContractNO() {
        return contractNO;
    }

    public void setContractNO(String contractNO) {
        this.contractNO = contractNO;
    }

    /**
     * 合同编号
     */
    private String contractNO;

    public Date getContractDate() {
        return contractDate;
    }

    public void setContractDate(Date contractDate) {
        this.contractDate = contractDate;
    }

    /**
     * 合同日期
     */
    private Date contractDate;
    /**
     * 合同日期
     */
    private String contractDateStart;

    public String getContractDateStart() {
        return contractDateStart;
    }

    public void setContractDateStart(String contractDateStart) {
        this.contractDateStart = contractDateStart;
    }

    public String getContractDateEnd() {
        return contractDateEnd;
    }

    public void setContractDateEnd(String contractDateEnd) {
        this.contractDateEnd = contractDateEnd;
    }

    /**
     * 合同日期

     */
    private String contractDateEnd;
    /**
     * 合约单（订单）状态
     */
    private String contractSheetStatusCode;

    /**
     * 合约单（订单）单据号
     */
    private String serialNumber;

    /**
     * 贸易条款
     */
    private String tradeTermCode;

    /**
     * 创建时间-开始
     */
    private Date gmtCreatedStart;

    /**
     * 创建时间-结束
     */
    private Date gmtCreatedEnd;

    /**
     * 卖家ID
     */
    private Long salerId;

    /**
     * 买家ID
     */
    private Long buyerId;

    public String getDischargePort() {
        return dischargePort;
    }

    public void setDischargePort(String dischargePort) {
        this.dischargePort = dischargePort;
    }

    private String dischargePort;

    /**
     * 中化新的ID
     */
    private Long enterpriseIdSingapore;

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    /**
     * 关键字查询  微信端
     */
    private String keywords;

    /** getters and setters */
    public String getProductCategoryCode() {
        return productCategoryCode;
    }

    public void setProductCategoryCode(String productCategoryCode) {
        this.productCategoryCode = productCategoryCode;
    }

    public String getContractSheetStatusCode() {
        return contractSheetStatusCode;
    }

    public void setContractSheetStatusCode(String contractSheetStatusCode) {
        this.contractSheetStatusCode = contractSheetStatusCode;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getTradeTermCode() {
        return tradeTermCode;
    }

    public void setTradeTermCode(String tradeTermCode) {
        this.tradeTermCode = tradeTermCode;
    }

    public Date getGmtCreatedStart() {
        return gmtCreatedStart;
    }

    public void setGmtCreatedStart(Date gmtCreatedStart) {
        this.gmtCreatedStart = gmtCreatedStart;
    }

    public Date getGmtCreatedEnd() {
        return gmtCreatedEnd;
    }

    public void setGmtCreatedEnd(Date gmtCreatedEnd) {
        this.gmtCreatedEnd = gmtCreatedEnd;
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

    public Long getEnterpriseIdSingapore() {
        return enterpriseIdSingapore;
    }

    public void setEnterpriseIdSingapore(Long enterpriseIdSingapore) {
        this.enterpriseIdSingapore = enterpriseIdSingapore;
    }
}
