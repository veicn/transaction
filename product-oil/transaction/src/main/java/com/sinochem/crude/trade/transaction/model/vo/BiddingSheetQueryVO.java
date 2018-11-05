package com.sinochem.crude.trade.transaction.model.vo;

import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.common.utils.DateUtil;
import com.sinochem.crude.trade.transaction.model.query.BiddingSheetQuery;

import java.io.Serializable;
import java.util.Date;

public class BiddingSheetQueryVO implements Serializable {
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
    private String gmtCreatedStartAsString;

    /**
     * 创建时间-结束
     */
    private String gmtCreatedEndAsString;

    /**
     * 报价标识
     */
    private String biddingFlag;

    public BiddingSheetQueryVO() {
    }

    public BiddingSheetQuery getBiddingSheetQuery() {

        BiddingSheetQuery biddingSheetQuery = new BiddingSheetQuery();

        if (!StringUtil.isEmpty(this.getProductCategoryCode())) {

            biddingSheetQuery.setProductCategoryCode(this.getProductCategoryCode());
        }
        if (!StringUtil.isEmpty(this.getBiddingFlag())) {

            biddingSheetQuery.setBiddingFlag(this.getBiddingFlag());
        }
        if (!StringUtil.isEmpty(this.getTradeTermCode())) {

            biddingSheetQuery.setTradeTermCode(this.getTradeTermCode());
        }
        if (!StringUtil.isEmpty(this.getBiddingSheetStatusCode())) {

            biddingSheetQuery.setBiddingSheetStatusCode(this.getBiddingSheetStatusCode());
        }
        if (!StringUtil.isEmpty(this.getSerialNumber())) {

            biddingSheetQuery.setSerialNumber(this.getSerialNumber());
        }
        if (!StringUtil.isEmpty(this.getGmtCreatedStartAsString())) {

            biddingSheetQuery.setGmtCreatedStart(DateUtil.getDate(this.getGmtCreatedStartAsString()));
        }
        if (!StringUtil.isEmpty(this.getGmtCreatedEndAsString())) {

            biddingSheetQuery.setGmtCreatedEnd(DateUtil.getDate(this.getGmtCreatedEndAsString()));
        }

        return biddingSheetQuery;
    }

    //get and set

    public Long getDemandSheetId() {
        return demandSheetId;
    }

    public void setDemandSheetId(Long demandSheetId) {
        this.demandSheetId = demandSheetId;
    }

    public Long getSaleSheetId() {
        return saleSheetId;
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

    public String getGmtCreatedStartAsString() {
        return gmtCreatedStartAsString;
    }

    public void setGmtCreatedStartAsString(String gmtCreatedStartAsString) {
        this.gmtCreatedStartAsString = gmtCreatedStartAsString;
    }

    public String getGmtCreatedEndAsString() {
        return gmtCreatedEndAsString;
    }

    public void setGmtCreatedEndAsString(String gmtCreatedEndAsString) {
        this.gmtCreatedEndAsString = gmtCreatedEndAsString;
    }

    public String getBiddingFlag() {
        return biddingFlag;
    }

    public void setBiddingFlag(String biddingFlag) {
        this.biddingFlag = biddingFlag;
    }
}
