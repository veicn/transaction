package com.sinochem.crude.trade.listed.model.query;

import java.util.Date;

/**
 * Created by sijliu on 18/11/2017.
 */
public class ResourceQuery {

    /**
     * ID
     */
    private Long demandId;

    /**
     * 单据类型
     */
    private String type;

    /**
     * 交易类型（买、卖）
     */
    private String dealType;

    /**
     * 业务类型（原油、成品油）
     */
    private String bizType;
    /**
     * 商品分类
     */
    private String goodsClassify;

    /**
     * 商品种类
     */
    private String goodsKind;

    /**
     * 商品规格
     */
    private String goodsSpecs;

    /**
     * 商品所在地区（成品油产地）
     */
    private String region;

    /**
     * 挂牌日期
     */
    private String listedDate;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户所在企业id
     */
    private Long userEnterpriseId;

    /**
     * 供应商企业Id
     */
    private Long providerEnterpriseId;

    /**
     * 状态
     */
    private String status;

    /**
     * 贸易条款
     */
    private String tradeItem;

    /**
     * 需求单编号
     */
    private String demandCode;

    /**
     * 数量（小）
     */
    private Integer numMin;

    /**
     * 数量（大）
     */
    private Integer numMax;

    /**
     * 原油油种
     */
    private String crudeOilOptions;

    /**
     * 类型：投标？询价
     */
    private String purchaseType;

    /**
     * 发布时间-开始
     */
    public String pubDateStart;

    /**
     * 发布时间-结束
     */
    public String pubDateEnd;

    /**
     * 排序
     */
    private String sortStr;

    /**
     * 父节点id
     */
    private Long parentId;

    /**
     * 创建时间-开始
     */
    private String createTimeStart;

    /**
     * 创建时间-结束
     */
    private String createTimeEnd;

    /**
     * 付款日期
     */
    private String payItem;

    /**
     * 付款日期JSON
     */
    private String payItemJSON;

    public String getPayItemJSON() {
        return payItemJSON;
    }

    public void setPayItemJSON(String payItemJSON) {
        this.payItemJSON = payItemJSON;
    }

    /**
     * 发布范围（1-定向发布，0-公开发布）
     */
    private Integer specified;

    /**
     * 当前登陆用户的企业memberid，区分定向发布用
     */
    private Long epMemberId;

    /**
     * 投标截止时间
     */
    private Date stopBidTimeBefore;

    /**
     * 1 - 需求
     * 2 - 资源
     */
    private Long selectCountType;

    /** getters and setters */
    public String getType() {
        return type;
    }

    public Long getSelectCountType() {
        return selectCountType;
    }

    public void setSelectCountType(Long selectCountType) {
        this.selectCountType = selectCountType;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDealType() {
        return dealType;
    }

    public void setDealType(String dealType) {
        this.dealType = dealType;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getGoodsClassify() {
        return goodsClassify;
    }

    public void setGoodsClassify(String goodsClassify) {
        this.goodsClassify = goodsClassify;
    }

    public String getGoodsKind() {
        return goodsKind;
    }

    public void setGoodsKind(String goodsKind) {
        this.goodsKind = goodsKind;
    }

    public String getGoodsSpecs() {
        return goodsSpecs;
    }

    public void setGoodsSpecs(String goodsSpecs) {
        this.goodsSpecs = goodsSpecs;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getListedDate() {
        return listedDate;
    }

    public void setListedDate(String listedDate) {
        this.listedDate = listedDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserEnterpriseId() {
        return userEnterpriseId;
    }

    public void setUserEnterpriseId(Long userEnterpriseId) {
        this.userEnterpriseId = userEnterpriseId;
    }

    public Long getProviderEnterpriseId() {
        return providerEnterpriseId;
    }

    public void setProviderEnterpriseId(Long providerEnterpriseId) {
        this.providerEnterpriseId = providerEnterpriseId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTradeItem() {
        return tradeItem;
    }

    public void setTradeItem(String tradeItem) {
        this.tradeItem = tradeItem;
    }

    public Integer getNumMin() {
        return numMin;
    }

    public void setNumMin(Integer numMin) {
        this.numMin = numMin;
    }

    public Integer getNumMax() {
        return numMax;
    }

    public void setNumMax(Integer numMax) {
        this.numMax = numMax;
    }

    public String getCrudeOilOptions() {
        return crudeOilOptions;
    }

    public void setCrudeOilOptions(String crudeOilOptions) {
        this.crudeOilOptions = crudeOilOptions;
    }

    public String getPurchaseType() {
        return purchaseType;
    }

    public void setPurchaseType(String purchaseType) {
        this.purchaseType = purchaseType;
    }

    public String getSortStr() {
        return sortStr;
    }

    public void setSortStr(String sortStr) {
        this.sortStr = sortStr;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getDemandCode() {
        return demandCode;
    }

    public void setDemandCode(String demandCode) {
        this.demandCode = demandCode;
    }

    public String getPubDateStart() {
        return pubDateStart;
    }

    public void setPubDateStart(String pubDateStart) {
        this.pubDateStart = pubDateStart;
    }

    public String getPubDateEnd() {
        return pubDateEnd;
    }

    public void setPubDateEnd(String pubDateEnd) {
        this.pubDateEnd = pubDateEnd;
    }

    public String getCreateTimeStart() {
        return createTimeStart;
    }

    public void setCreateTimeStart(String createTimeStart) {
        this.createTimeStart = createTimeStart;
    }

    public String getCreateTimeEnd() {
        return createTimeEnd;
    }

    public void setCreateTimeEnd(String createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }

    public String getPayItem() {
        return payItem;
    }

    public void setPayItem(String payItem) {
        this.payItem = payItem;
    }

    public Long getDemandId() {
        return demandId;
    }

    public void setDemandId(Long demandId) {
        this.demandId = demandId;
    }

    public Integer getSpecified() {
        return specified;
    }

    public void setSpecified(Integer specified) {
        this.specified = specified;
    }

    public Date getStopBidTimeBefore() {
        return stopBidTimeBefore;
    }

    public void setStopBidTimeBefore(Date stopBidTimeBefore) {
        this.stopBidTimeBefore = stopBidTimeBefore;
    }

    public Long getEpMemberId() {
        return epMemberId;
    }

    public void setEpMemberId(Long epMemberId) {
        this.epMemberId = epMemberId;
    }
}
