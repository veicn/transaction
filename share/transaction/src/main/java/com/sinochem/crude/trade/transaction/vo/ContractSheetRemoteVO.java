package com.sinochem.crude.trade.transaction.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class ContractSheetRemoteVO implements Serializable {
    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 订单uuid
     */
    private String uuid;
    /**
     * 订单编码
     */
    private String orderNumber;
    /**
     * 订单种类
     */
    private String tradeTerms;
    /**
     * 产品名称
     */
    private String prodoctNm;
    /**
     * 买家EPmemberID
     */
    private Long buyerId;
    /**
     * 卖家EPmemberID
     */
    private Long sellerId;
    /**
     * 数量
     */
    private BigDecimal quantity;
    /**
     * 误差范围
     */
    private Long rangeOfError;
    /**
     * 装港名
     */
    private String portOfLoading;
    /**
     *
     * 商品来源
     */
    private String productSource;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getPortOfDischarge() {
        return portOfDischarge;
    }

    public void setPortOfDischarge(String portOfDischarge) {
        this.portOfDischarge = portOfDischarge;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getTradeTerms() {
        return tradeTerms;
    }

    public void setTradeTerms(String tradeTerms) {
        this.tradeTerms = tradeTerms;
    }

    public String getProdoctNm() {
        return prodoctNm;
    }

    public void setProdoctNm(String prodoctNm) {
        this.prodoctNm = prodoctNm;
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public Long getRangeOfError() {
        return rangeOfError;
    }

    public void setRangeOfError(Long rangeOfError) {
        this.rangeOfError = rangeOfError;
    }

    public String getPortOfLoading() {
        return portOfLoading;
    }

    public void setPortOfLoading(String portOfLoading) {
        this.portOfLoading = portOfLoading;
    }

    public String getLaycanStart() {
        return laycanStart;
    }

    public void setLaycanStart(String laycanStart) {
        this.laycanStart = laycanStart;
    }

    public String getLaycanEnd() {
        return laycanEnd;
    }

    public void setLaycanEnd(String laycanEnd) {
        this.laycanEnd = laycanEnd;
    }

    /**


     * 卸港名
     */
    private String portOfDischarge;
    /**
     * 受载期开始
     */
    private String laycanStart;
    /**
     * 受载期结束
     */
    private String laycanEnd;


    public String getProductSource() {
        return productSource;
    }

    public void setProductSource(String productSource) {
        this.productSource = productSource;
    }
}
