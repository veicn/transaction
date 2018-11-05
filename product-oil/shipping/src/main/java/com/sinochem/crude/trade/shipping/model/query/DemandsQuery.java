package com.sinochem.crude.trade.shipping.model.query;

/**
 * 租船需求query
 *
 * @author zhaoyulong
 */
public class DemandsQuery {

    private String uuid;

    private String prodoctNm;

    private String status;

    private String createDateStart;
    private String createDateEnd;

    private String orderNumber;

    /**
     * 买家ID
     */
    private Long buyerId;

    /**
     * 卖家ID
     */
    private Long sellerId;
    /**
     * 租船代理ID
     */
    private Long charteringAgentId;

    private Long epMemberId;

    //中化新ID
    private Long singaporeId;

    /**
     * 关键字查询  微信端
     */
    private String keywords;

    public Long getSingaporeId() {
        return singaporeId;
    }

    public void setSingaporeId(Long singaporeId) {
        this.singaporeId = singaporeId;
    }

    public Long getEpMemberId() {
        return epMemberId;
    }

    public void setEpMemberId(Long epMemberId) {
        this.epMemberId = epMemberId;
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

    public Long getCharteringAgentId() {
        return charteringAgentId;
    }

    public void setCharteringAgentId(Long charteringAgentId) {
        this.charteringAgentId = charteringAgentId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getProdoctNm() {
        return prodoctNm;
    }

    public void setProdoctNm(String prodoctNm) {
        this.prodoctNm = prodoctNm;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateDateStart() {
        return createDateStart;
    }

    public void setCreateDateStart(String createDateStart) {
        this.createDateStart = createDateStart;
    }

    public String getCreateDateEnd() {
        return createDateEnd;
    }

    public void setCreateDateEnd(String createDateEnd) {
        this.createDateEnd = createDateEnd;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
}
