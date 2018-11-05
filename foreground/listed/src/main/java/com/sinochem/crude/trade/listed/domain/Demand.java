package com.sinochem.crude.trade.listed.domain;



import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.sinochem.crude.trade.order.remote.DemandOrderVo;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotEmpty;

public class Demand {
	
	/**
	 * 报价状态：10：报价  20：中标  30：结束
	 */
	public final static Integer STATUS_QUOTE_10 = 10;
	
    /**
     * 主键
     */
    private Long id;

    /**
     * 数量
     */
    private Long num;

    /**
     * 溢短装
     */
    private String numfloat;

    /**
     * 贸易条款
     */
    private Integer tradeItem;
    
    /**
     * 贸易条款
     */
    private String tradeItemStr;

    /**
     * 贴水
     */
    private Long agio;

    /**
     * 计价基准
     */
    private String valuationBase;

    /**
     * 计价期类型（全月、期间、期末）
     */
    private Integer valuationProidType;
    
    /**
     * 计价期类型（全月、期间、期末）
     */
    private String valuationProidTypeStr;

	/**
     * 付款条款
     */
    private String payItem;


    /**
     * 付款日期JSON串
     */
    private String payItemJSON;

    public String getPayItemJSON() {
        return payItemJSON;
    }

    public void setPayItemJSON(String payItemJSON) {
        this.payItemJSON = payItemJSON;
    }

    /**
     * 付款日期
     */
    private String payTime;

    /**
     * 报价类型（投标、询价）
     */
    private Integer purchaseType;

    /**
     * 创建人
     */
    private Long creater;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private Long updater;

    /**
     * 更新时间
     */
    private Date updaterTime;

    /**
     * 父节点id
     */
    private Long parentId;

    /**
     * 计价公式
     */
    private String valuationFormulaJson;

    /**
     * 计价公式
     */
    private String valuationFormula;

    /**
     * 类型（D - 需求 B - 报价）
     */
    private String type;

    /**
     * 交易类型（买、卖）
     */
    private String dealType;

    /**
     * 业务类型
     */
    private String bizType;


    private String productOilId;

    /**
     * 其他条款
     */
    private String otherItem;

    /**
     * 信用条款
     */
    private Integer authItem;
    
    /**
     * 信用条款
     */
    private String authItemStr;

    /**
     * 商检机构
     */
    private String businessCheckOrg;

    /**
     * 出口配额文件
     */
    private String exportConfFile;

    /**
     * 备注
     */
    private String remark;

    /**
     * 出标时间
     */
    private Date tenderOutTime;

    /**
     * 截标时间
     */
    private Date stopBidTime;

    /**
     * 响应开始时间
     */
    private Date responseStartTime;

    /**
     * 响应结束时间
     */
    private Date responseEndTime;

    /**
     * 单据编号
     */
    private String uuid;

    /**
     * 原油油种
     */
    private String crudeOilOptions;

    /**
     * 成品油分类
     */
    private Integer productOilClassify;

    /**
     * 成品油种类
     */
    private Integer productOilKind;

    /**
     * 成品油规格
     */
    private Integer productOilSpecs;

    /**
     * 状态
     * 包括需求状态和报价状态
     * 0：删除
     * 需求状态：1：新建  2：发布  3：完成
     * 报价状态：10：报价  20：中标  30：结束
     */
    private Integer status;

    /**
     * 是否代理
     */
    private Integer isAgent;

    /**
     * 代理商
     */
    private Long agenter;

    /**
     * 出口类型
     */
    private Integer exportType;

    /**
     * 质检报告
     */
    private String qcReport;

    /**
     * 价格方式
     */
    private Integer pricingMode;

    /**
     * 价格计量单位
     */
    private Integer pricingMeasureUnit;

    /**
     * 计量方式
     */
    private Integer measureMode;

    /**
     * 价格说明
     */
    private String priceDeclare;

    /**
     * 是否密封
     */
    private Integer isSeal;

    /**
     * 计价货币
     */
    private Integer valuationCurrency;

    /**
     * 计价期开始时间
     */
    private Date valuationProidStart;

    /**
     * 计价期结束时间
     */
    private Date valuationProidEnd;

    /**
     * 单价（美元/桶）
     */
    private Integer unitPrice;

    /**
     * 采购方式
     */
    private Integer purchaseMode;

    /**
     * 发布时间
     */
    private Date pubDate;
    
    /**
     * 地区
     */
    private Integer region;

    /**
     * 前端保存更新接收数量
     */
    private String numStr;
    /**
     * 前端保存更新接收贴水
     */
    private BigDecimal agioStr;

    /**
     * 商品图片ID，多个间","分割
     */
    private String goodsImages;

    /**
     * 是否指定发布（1-是，0-否）
     */
    private Integer specified;

    /**
     * 需求发布类型，1-按油种，2-按性质
     */
    private Integer publishType;

    /**
     * 报价类型，1-意向报价，2-正式报价
     */
    private Integer biddingType;

    /**
     * 允许装卸货时间
     */
    private String loadAndDischargePermittedTimespan;

    /**
     * 合约种类
     */
    private String contractKind;

    /**
     * 商检费分摊方式
     */
    private Integer inspectionFeeSharingType;

    /**
     * 法律
     */
    private String law;

    /**
     * gtc
     */
    private String gtc;

    /**
     * 价格类型
     */
    private Integer priceType;

    public String getLaw() {
        return law;
    }

    public void setLaw(String law) {
        this.law = law;
    }

    public String getGtc() {
        return gtc;
    }

    public void setGtc(String gtc) {
        this.gtc = gtc;
    }

    /** getters and setters */
    public Integer getPurchaseMode() {
        return purchaseMode;
    }

    public void setPurchaseMode(Integer purchaseMode) {
        this.purchaseMode = purchaseMode;
    }

    public Integer getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Integer unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        if(num == null && this.getNumStr() != null)
            this.num = convertToLong(new BigDecimal(this.getNumStr()));
        else
            this.num = num;
    }

    public String getNumfloat() {
        return numfloat;
    }

    public void setNumfloat(String numfloat) {
        this.numfloat = numfloat;
    }

    public Integer getTradeItem() {
        return tradeItem;
    }

    public void setTradeItem(Integer tradeItem) {
        this.tradeItem = tradeItem;
    }

    public Long getAgio() {
        return agio;
    }

    public void setAgio(Long agio) {
        if(agio == null)
            this.agio = convertToLong(this.agioStr);
        else
            this.agio = agio;
    }

    @NotEmpty(message = "不能为空")
    public String getValuationBase() {
        return valuationBase;
    }

    public void setValuationBase(String valuationBase) {
        this.valuationBase = valuationBase;
    }

    @NotNull(message = "不能为空")
    public Integer getValuationProidType() {
        return valuationProidType;
    }

    public void setValuationProidType(Integer valuationProidType) {
        this.valuationProidType = valuationProidType;
    }

    @NotNull(message = "不能为空")
    public String getPayItem() {
        return payItem;
    }

    public void setPayItem(String payItem) {
        this.payItem = payItem;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public Integer getPurchaseType() {
        return purchaseType;
    }

    public void setPurchaseType(Integer purchaseType) {
        this.purchaseType = purchaseType;
    }

    public Long getCreater() {
        return creater;
    }

    public void setCreater(Long creater) {
        this.creater = creater;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUpdater() {
        return updater;
    }

    public void setUpdater(Long updater) {
        this.updater = updater;
    }

    public Date getUpdaterTime() {
        return updaterTime;
    }

    public void setUpdaterTime(Date updaterTime) {
        this.updaterTime = updaterTime;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getValuationFormula() {
        return valuationFormula;
    }

    public void setValuationFormula(String valuationFormula) {
        this.valuationFormula = valuationFormula;
    }

    public String getType() {
        return type;
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

    public String getProductOilId() {
        return productOilId;
    }

    public void setProductOilId(String productOilId) {
        this.productOilId = productOilId;
    }

    public String getOtherItem() {
        return otherItem;
    }

    public void setOtherItem(String otherItem) {
        this.otherItem = otherItem;
    }

    public Integer getAuthItem() {
        return authItem;
    }

    public void setAuthItem(Integer authItem) {
        this.authItem = authItem;
    }

    public String getBusinessCheckOrg() {
        return businessCheckOrg;
    }

    public void setBusinessCheckOrg(String businessCheckOrg) {
        this.businessCheckOrg = businessCheckOrg;
    }

    public String getExportConfFile() {
        return exportConfFile;
    }

    public void setExportConfFile(String exportConfFile) {
        this.exportConfFile = exportConfFile;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getTenderOutTime() {
        return tenderOutTime;
    }

    public void setTenderOutTime(Date tenderOutTime) {
        this.tenderOutTime = tenderOutTime;
    }

    public Date getStopBidTime() {
        return stopBidTime;
    }

    public void setStopBidTime(Date stopBidTime) {
        this.stopBidTime = stopBidTime;
    }

    public Date getResponseStartTime() {
        return responseStartTime;
    }

    public void setResponseStartTime(Date responseStartTime) {
        this.responseStartTime = responseStartTime;
    }

    public Date getResponseEndTime() {
        return responseEndTime;
    }

    public void setResponseEndTime(Date responseEndTime) {
        this.responseEndTime = responseEndTime;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCrudeOilOptions() {
        return crudeOilOptions;
    }

    public void setCrudeOilOptions(String crudeOilOptions) {
        this.crudeOilOptions = crudeOilOptions;
    }

    public Integer getProductOilClassify() {
        return productOilClassify;
    }

    public void setProductOilClassify(Integer productOilClassify) {
        this.productOilClassify = productOilClassify;
    }

    public Integer getProductOilKind() {
        return productOilKind;
    }

    public void setProductOilKind(Integer productOilKind) {
        this.productOilKind = productOilKind;
    }

    public Integer getProductOilSpecs() {
        return productOilSpecs;
    }

    public void setProductOilSpecs(Integer productOilSpecs) {
        this.productOilSpecs = productOilSpecs;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsAgent() {
        return isAgent;
    }

    public void setIsAgent(Integer isAgent) {
        this.isAgent = isAgent;
    }

    public Long getAgenter() {
        return agenter;
    }

    public void setAgenter(Long agenter) {
        this.agenter = agenter;
    }

    public Integer getExportType() {
        return exportType;
    }

    public void setExportType(Integer exportType) {
        this.exportType = exportType;
    }

    public String getQcReport() {
        return qcReport;
    }

    public void setQcReport(String qcReport) {
        this.qcReport = qcReport;
    }

    public Integer getPricingMode() {
        return pricingMode;
    }

    public void setPricingMode(Integer pricingMode) {
        this.pricingMode = pricingMode;
    }

    public Integer getPricingMeasureUnit() {
        return pricingMeasureUnit;
    }

    public void setPricingMeasureUnit(Integer pricingMeasureUnit) {
        this.pricingMeasureUnit = pricingMeasureUnit;
    }

    public Integer getMeasureMode() {
        return measureMode;
    }

    public void setMeasureMode(Integer measureMode) {
        this.measureMode = measureMode;
    }

    public String getPriceDeclare() {
        return priceDeclare;
    }

    public void setPriceDeclare(String priceDeclare) {
        this.priceDeclare = priceDeclare;
    }

    public Integer getIsSeal() {
        return isSeal;
    }

    public void setIsSeal(Integer isSeal) {
        this.isSeal = isSeal;
    }

    public Integer getValuationCurrency() {
        return valuationCurrency;
    }

    public void setValuationCurrency(Integer valuationCurrency) {
        this.valuationCurrency = valuationCurrency;
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

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public String getValuationFormulaJson() {
        return valuationFormulaJson;
    }

    public void setValuationFormulaJson(String valuationFormulaJson) {
        this.valuationFormulaJson = valuationFormulaJson;
    }

	public Integer getRegion() {
		return region;
	}

	public void setRegion(Integer region) {
		this.region = region;
	}

    public String getNumStr() {
        if(numStr != null){
            return numStr;
        }else {
            if(this.num != null && this.num != 0 )
                return BigDecimal.valueOf(this.num).divide(new BigDecimal(1000)).toString();
            else
                return numStr;
        }
    }

    public void setNumStr(String numStr) {
        this.numStr = numStr;
        this.setNum(StringUtils.isBlank(numStr) ? 0L : new BigDecimal(numStr.trim()).divide(new BigDecimal("1000")).longValue());
    }

    public BigDecimal getAgioStr() {
        if(agioStr != null){
            return agioStr;
        }else{
            if(this.agio != null && this.agio != 0 )
                return BigDecimal.valueOf(this.agio).divide(new BigDecimal(1000));
            else
                return agioStr;
        }
    }

    public void setAgioStr(BigDecimal agioStr) {
        this.agioStr = agioStr;
    }

    public String getGoodsImages() {
        return goodsImages;
    }

    public void setGoodsImages(String goodsImages) {
        this.goodsImages = goodsImages;
    }

    private Long convertToLong(BigDecimal b1){
        return b1 == null?null:b1.multiply(new BigDecimal(1000)).longValue();
    }

    public String getTradeItemStr() {
        return tradeItemStr;
    }

    public void setTradeItemStr(String tradeItemStr) {
        this.tradeItemStr = tradeItemStr;
    }

    public String getValuationProidTypeStr() {
        return valuationProidTypeStr;
    }

    public void setValuationProidTypeStr(String valuationProidTypeStr) {
        this.valuationProidTypeStr = valuationProidTypeStr;
    }

    public String getAuthItemStr() {
        return authItemStr;
    }

    public void setAuthItemStr(String authItemStr) {
        this.authItemStr = authItemStr;
    }

    public Integer getSpecified() {
        return specified;
    }

    public void setSpecified(Integer specified) {
        this.specified = specified;
    }

    public Integer getPublishType() {
        return publishType;
    }

    public void setPublishType(Integer publishType) {
        this.publishType = publishType;
    }

    public Integer getBiddingType() {
        return biddingType;
    }

    public void setBiddingType(Integer biddingType) {
        this.biddingType = biddingType;
    }

    public String getLoadAndDischargePermittedTimespan() {
        return loadAndDischargePermittedTimespan;
    }

    public void setLoadAndDischargePermittedTimespan(String loadAndDischargePermittedTimespan) {
        this.loadAndDischargePermittedTimespan = loadAndDischargePermittedTimespan;
    }

    public String getContractKind() {
        return contractKind;
    }

    public void setContractKind(String contractKind) {
        this.contractKind = contractKind;
    }

    public Integer getInspectionFeeSharingType() {
        return inspectionFeeSharingType;
    }

    public void setInspectionFeeSharingType(Integer inspectionFeeSharingType) {
        this.inspectionFeeSharingType = inspectionFeeSharingType;
    }

	public Integer getPriceType() {
		return priceType;
	}

	public void setPriceType(Integer priceType) {
		this.priceType = priceType;
	}
}