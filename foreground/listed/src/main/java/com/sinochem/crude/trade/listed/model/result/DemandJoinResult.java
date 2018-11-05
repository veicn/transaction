package com.sinochem.crude.trade.listed.model.result;

import java.util.Date;

/**
 * Created by sijliu on 28/11/2017.
 */
public class DemandJoinResult {

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
     * 付款条款
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
     * 付款日期
     */
    private String payTime;

    /**
     * 采购类型
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
    private String valuationFormula;

    /**
     * 单据类型
     */
    private String type;

    /**
     * 交易类型（投标、询价）
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
     * 成品油产地
     */
    private Integer region;

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
    private int unitPrice;

    /**
     * 采购方式
     */
    private int purchaseMode;

    /**
     * 允许装卸货时间
     */
    private String loadAndDischargePermittedTimespan;
    
    /**
     * 卸货港
     */
    private String dischargePort;

    /**
     * 装货港
     */
    private String shipmentPort;


    /**
     * 装货开始时间
     */
    private Date shipmentStartTime;

    /**
     * 装货结束时间
     */
    private Date shipmentEndTime;

    /**
     * 到货开始时间
     */
    private Date dischargeStartTime;

    /**
     * 到货结束时间
     */
    private Date dischargeEndTime;

    /**
     * 发布日期
     */
    private Date pubDate;

    /**
     * 买家
     */
    private Long beMemberId;

    private String bContacter;

    private String bFamil;

    private String bEnterpriseName;

    /**
     * 贸易商
     */
    private Long teMemberId;

    private String tContacter;

    private String tFamil;

    private String tEnterpriseName;

    /**
     * 供应商
     */
    private Long peMemberId;

    private String pContacter;

    private String pFamil;

    private String pEnterpriseName;
    
    /**
     * 需求发布类型，1-按油种，2-按性质
     */
    private Integer publishType;

    /**
     * 报价类型，1-意向报价，2-正式报价
     */
    private Integer biddingType;

    /**
     * 合约种类
     */
    private String contractKind;

    /**
     * 发布范围（0-公开发布， 1-定向发布）
     */
    private Integer specified;

    /**
     * 商检费分摊类型
     */
    private Integer inspectionFeeSharingType;

    /**
     * 是否已结标（1-已结标， 0-未结标）
     */
    private Integer stopBid;
    
    /** getters and setters */
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
        this.agio = agio;
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
        if(crudeOilOptions != null && crudeOilOptions.length() > 0 && crudeOilOptions.indexOf(",") >= 0){
            this.crudeOilOptions = crudeOilOptions.substring(0,crudeOilOptions.length()-1);
        }else{
            this.crudeOilOptions = crudeOilOptions;
        }
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

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getPurchaseMode() {
        return purchaseMode;
    }

    public void setPurchaseMode(int purchaseMode) {
        this.purchaseMode = purchaseMode;
    }

    public String getDischargePort() {
        return dischargePort;
    }

    public void setDischargePort(String dischargePort) {
        this.dischargePort = dischargePort;
    }

    public String getShipmentPort() {
        return shipmentPort;
    }

    public void setShipmentPort(String shipmentPort) {
        this.shipmentPort = shipmentPort;
    }

    public Date getShipmentStartTime() {
        return shipmentStartTime;
    }

    public void setShipmentStartTime(Date shipmentStartTime) {
        this.shipmentStartTime = shipmentStartTime;
    }

    public Date getShipmentEndTime() {
        return shipmentEndTime;
    }

    public void setShipmentEndTime(Date shipmentEndTime) {
        this.shipmentEndTime = shipmentEndTime;
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

    public Long getBeMemberId() {
        return beMemberId;
    }

    public void setBeMemberId(Long beMemberId) {
        this.beMemberId = beMemberId;
    }

    public String getbContacter() {
        return bContacter;
    }

    public void setbContacter(String bContacter) {
        this.bContacter = bContacter;
    }

    public String getbFamil() {
        return bFamil;
    }

    public void setbFamil(String bFamil) {
        this.bFamil = bFamil;
    }

    public String getbEnterpriseName() {
        return bEnterpriseName;
    }

    public void setbEnterpriseName(String bEnterpriseName) {
        this.bEnterpriseName = bEnterpriseName;
    }

    public Long getTeMemberId() {
        return teMemberId;
    }

    public void setTeMemberId(Long teMemberId) {
        this.teMemberId = teMemberId;
    }

    public String gettContacter() {
        return tContacter;
    }

    public void settContacter(String tContacter) {
        this.tContacter = tContacter;
    }

    public String gettFamil() {
        return tFamil;
    }

    public void settFamil(String tFamil) {
        this.tFamil = tFamil;
    }

    public String gettEnterpriseName() {
        return tEnterpriseName;
    }

    public void settEnterpriseName(String tEnterpriseName) {
        this.tEnterpriseName = tEnterpriseName;
    }

    public Long getPeMemberId() {
        return peMemberId;
    }

    public void setPeMemberId(Long peMemberId) {
        this.peMemberId = peMemberId;
    }

    public String getpContacter() {
        return pContacter;
    }

    public void setpContacter(String pContacter) {
        this.pContacter = pContacter;
    }

    public String getpFamil() {
        return pFamil;
    }

    public void setpFamil(String pFamil) {
        this.pFamil = pFamil;
    }

    public String getpEnterpriseName() {
        return pEnterpriseName;
    }

    public void setpEnterpriseName(String pEnterpriseName) {
        this.pEnterpriseName = pEnterpriseName;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public Integer getRegion() {
        return region;
    }

    public void setRegion(Integer region) {
        this.region = region;
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

    public String getContractKind() {
        return contractKind;
    }

    public void setContractKind(String contractKind) {
        this.contractKind = contractKind;
    }

    public Integer getSpecified() {
        return specified;
    }

    public void setSpecified(Integer specified) {
        this.specified = specified;
    }

    public Integer getInspectionFeeSharingType() {
        return inspectionFeeSharingType;
    }

    public void setInspectionFeeSharingType(Integer inspectionFeeSharingType) {
        this.inspectionFeeSharingType = inspectionFeeSharingType;
    }

	public Integer getStopBid() {
		return stopBid;
	}

	public void setStopBid(Integer stopBid) {
		this.stopBid = stopBid;
	}

	public String getLoadAndDischargePermittedTimespan() {
		return loadAndDischargePermittedTimespan;
	}

	public void setLoadAndDischargePermittedTimespan(
			String loadAndDischargePermittedTimespan) {
		this.loadAndDischargePermittedTimespan = loadAndDischargePermittedTimespan;
	}
}
