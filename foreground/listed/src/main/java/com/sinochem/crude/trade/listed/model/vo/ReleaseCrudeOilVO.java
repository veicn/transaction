package com.sinochem.crude.trade.listed.model.vo;


import java.util.Date;

/**
 * 原油大厅已发布信息Form
 */
public class ReleaseCrudeOilVO {
    /**
     * demand.id
     */
    private Long id;

    /**
     * 数量
     */
    private Long num;
    
    /**
     * 报价类型（投标、询价）
     */
    private Integer purchaseType;
    
    /**
     * 油种
     */
    private String crudeOilOptions;

    /**
     * 贸易条款
     */
    private Integer tradeItem;

    /**
     * 计价基准
     */
    private String valuationBase;

    /**
     * 计价期
     */
    private Integer valuationProidType;

    /**
     * 计价期开始时间
     */
    private Date valuationProidStart;

    /**
     * 计价期结束时间
     */
    private Date valuationProidEnd;

    /**
     * 到货开始时间
     */
    private Date dischargeStartTime;

    /**
     * 到货结束时间
     */
    private Date dischargeEndTime;

    /**
     * 卸货港
     * @return
     */
    private String dischargePort;

    /**
     * uuid
     * @return
     */
    private String uuid;

    /**
     * 付款日期
     */
    private String payItem;

    /**
     * 截标时间
     */
    private Date stopBidTime;

    /**
     * 出标时间
     */
    private Date tenderOutTime;

    /**
     * 发布时间
     */
    private Date pubDate;

    /**
     * 创建企业ID
     */
    private Long creater;

    /**
     *需求发布类型，1-按油种，2-按性质
     */
    private Integer publishType;

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCrudeOilOptions() {
        return crudeOilOptions;
    }

    public void setCrudeOilOptions(String crudeOilOptions) {
        if(crudeOilOptions != null && crudeOilOptions.length() > 0){
            this.crudeOilOptions = crudeOilOptions.substring(0,crudeOilOptions.length()-1);
        }else{
            this.crudeOilOptions = crudeOilOptions;
        }
    }

    public Integer getTradeItem() {
        return tradeItem;
    }

    public void setTradeItem(Integer tradeItem) {
        this.tradeItem = tradeItem;
    }

    public String getDischargePort() {
        return dischargePort;
    }

    public void setDischargePort(String dischargePort) {
        this.dischargePort = dischargePort;
    }

    public String getValuationBase() {
        return valuationBase;
    }

    public void setValuationBase(String valuationBase) {
        this.valuationBase = valuationBase;
    }

    public Integer getValuationProidType() {
        return valuationProidType;
    }

    public void setValuationProidType(Integer valuationProidType) {
        this.valuationProidType = valuationProidType;
    }

    public Date getDischargeStartTime() {
        return dischargeStartTime;
    }

    public void setDischargeStartTime(Date dischargeStartTime) {
        this.dischargeStartTime = dischargeStartTime;
    }

    public Date getDischargeEndTime() {
        return dischargeEndTime;
    }

    public void setDischargeEndTime(Date dischargeEndTime) {
        this.dischargeEndTime = dischargeEndTime;
    }

	public Integer getPurchaseType() {
		return purchaseType;
	}

	public void setPurchaseType(Integer purchaseType) {
		this.purchaseType = purchaseType;
	}

    public Date getValuationProidStart() {
        return valuationProidStart;
    }

    public void setValuationProidStart(Date valuationProidStart) {
        this.valuationProidStart = valuationProidStart;
    }

    public Date getValuationProidEnd() {
        return valuationProidEnd;
    }

    public void setValuationProidEnd(Date valuationProidEnd) {
        this.valuationProidEnd = valuationProidEnd;
    }

    public String getPayItem() {
        return payItem;
    }

    public void setPayItem(String payItem) {
        this.payItem = payItem;
    }

    public Date getStopBidTime() {
        return stopBidTime;
    }

    public void setStopBidTime(Date stopBidTime) {
        this.stopBidTime = stopBidTime;
    }

    public Date getTenderOutTime() {
        return tenderOutTime;
    }

    public void setTenderOutTime(Date tenderOutTime) {
        this.tenderOutTime = tenderOutTime;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public Long getCreater() {
        return creater;
    }

    public void setCreater(Long creater) {
        this.creater = creater;
    }

    public Integer getPublishType() {
        return publishType;
    }

    public void setPublishType(Integer publishType) {
        this.publishType = publishType;
    }
}
