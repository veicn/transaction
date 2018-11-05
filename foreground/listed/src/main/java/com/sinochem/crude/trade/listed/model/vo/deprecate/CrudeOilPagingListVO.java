package com.sinochem.crude.trade.listed.model.vo.deprecate;

/**
 * 原油列表展示数据vo
 * Created by sijliu on 03/01/2018.
 */
@Deprecated
public class CrudeOilPagingListVO {

    // 主键
    private Long id;
    private String demandNo;
    // 油种
    private String oil;
    // 数量
    private String num;
    // 贸易条款
    private String tradeItem;
    // 投标截止时间
    private String stopBidTime;
    // 公布中标日期
    private String tenderOutTime;
    // 到货期
    private String arrivalDate;
    // 采购类型
    private Integer purchaseType;
    // 采购类型 内容
    private String purchaseTypeContent;
    //油种类型
    private String bizType;
    /**
     * 发布日期
     */
    private String pubDate;
    /**
     * 付款条款
     */
    private String payItem;

    /**
     * 创建人
     */
    private Long creater;

    /**
     *需求发布类型，1-按油种，2-按性质
     */
    private Integer publishType;

    public Long getCreater() {
        return creater;
    }

    public void setCreater(Long creater) {
        this.creater = creater;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOil() {
        return oil;
    }

    public void setOil(String oil) {
        this.oil = oil;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getTradeItem() {
        return tradeItem;
    }

    public void setTradeItem(String tradeItem) {
        this.tradeItem = tradeItem;
    }

    public String getStopBidTime() {
        return stopBidTime;
    }

    public void setStopBidTime(String stopBidTime) {
        this.stopBidTime = stopBidTime;
    }

    public String getTenderOutTime() {
        return tenderOutTime;
    }

    public void setTenderOutTime(String tenderOutTime) {
        this.tenderOutTime = tenderOutTime;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getDemandNo() {
        return demandNo;
    }

    public void setDemandNo(String demandNo) {
        this.demandNo = demandNo;
    }

    public Integer getPurchaseType() {
        return purchaseType;
    }

    public void setPurchaseType(Integer purchaseType) {
        this.purchaseType = purchaseType;
    }

    public String getPurchaseTypeContent() {
        return purchaseTypeContent;
    }

    public void setPurchaseTypeContent(String purchaseTypeContent) {
        this.purchaseTypeContent = purchaseTypeContent;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getPayItem() {
        return payItem;
    }

    public void setPayItem(String payItem) {
        this.payItem = payItem;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public Integer getPublishType() {
        return publishType;
    }

    public void setPublishType(Integer publishType) {
        this.publishType = publishType;
    }
}
