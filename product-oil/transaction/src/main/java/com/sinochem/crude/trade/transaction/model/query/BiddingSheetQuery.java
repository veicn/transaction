package com.sinochem.crude.trade.transaction.model.query;

import java.util.Date;

/**
 * 投标单的查询条件对象
 * @author Yichen Zhao
 * date: 20180306
 */
public class BiddingSheetQuery {

    /**
     * 关联的销售需求单的ID
     */
    private Long saleSheetId;

    /**
     * 关联的采购需求单的ID
     */
    private Long demandSheetId;

    /**
     * 商品分类
     */
    private String productCategoryCode;

    /**
     * 投标单状态
     */
    private String biddingSheetStatusCode;

    /**
     * 投标单单据号
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
     * 创建企业ID
     */
    private Long enterpriseId;

    /**
     * 中化新的ID
     */
    private Long enterpriseIdSingapore;

    /**
     * 报价标识
     *
     */
    private String biddingFlag;


    /**
     * uuid
     *
     */
    private String demandSheetUuid;

    /**
     * uuid
     *
     */
    private String saleSheetUuid;


    /** getters and setters */
    public String getBiddingFlag() {
        return biddingFlag;
    }

    public void setBiddingFlag(String biddingFlag) {
        this.biddingFlag = biddingFlag;
    }

    public Long getDemandSheetId() {
        return demandSheetId;
    }

    public void setDemandSheetId(Long demandSheetId) {
        this.demandSheetId = demandSheetId;
    }

    public void setSaleSheetId(Long saleSheetId) {
        this.saleSheetId = saleSheetId;
    }

    public String getProductCategoryCode() {
        return productCategoryCode;
    }

    public void setProductCategoryCode(String productCategoryCode) {
        this.productCategoryCode = productCategoryCode;
    }

    public String getBiddingSheetStatusCode() {
        return biddingSheetStatusCode;
    }

    public void setBiddingSheetStatusCode(String biddingSheetStatusCode) {
        this.biddingSheetStatusCode = biddingSheetStatusCode;
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

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Long getSaleSheetId() {
        return saleSheetId;
    }

    public Long getEnterpriseIdSingapore() {
        return enterpriseIdSingapore;
    }

    public void setEnterpriseIdSingapore(Long enterpriseIdSingapore) {
        this.enterpriseIdSingapore = enterpriseIdSingapore;
    }

    public String getSaleSheetUuid() {
        return saleSheetUuid;
    }

    public void setSaleSheetUuid(String saleSheetUuid) {
        this.saleSheetUuid = saleSheetUuid;
    }

    public String getDemandSheetUuid() {
        return demandSheetUuid;
    }

    public void setDemandSheetUuid(String demandSheetUuid) {
        this.demandSheetUuid = demandSheetUuid;
    }


}
