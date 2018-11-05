package com.sinochem.crude.trade.listed.model.vo.deprecate;

import java.util.ArrayList;
import java.util.List;

import com.sinochem.crude.trade.listed.constant.Constant;
import com.sinochem.crude.trade.listed.model.vo.deprecate.CrudeOilVO;
import com.sinochem.crude.trade.listed.model.vo.deprecate.DemandRelevanterVO;
import com.sinochem.it.b2b.common.utils.VisitorLocale;

/**
 * @deprecated
 * Demand类的VO
 * @author Yichen Zhao
 * date: 20180103
 */
public class DemandVO {

    /**
     * 买家
     */
    private DemandRelevanterVO buyer;

    /**
     * 代理商
     */
    private DemandRelevanterVO agent;

    /**
     * 供应商
     */
    private DemandRelevanterVO supplier;

    /**
     * 原油油种
     */
    private CrudeOilInfoVO[] crudeOilsInfoVO;
    
    private List<CrudeOilVO> crudeOils = new ArrayList<>();

    /**
     * 计价期类型
     */
    private String valuationPeriodType;

    /**
     * 计价期开始时间
     */
    private String valuationPeriodStart;

    /**
     * 计价期结束时间
     */
    private String valuationPeriodEnd;

    /**
     * 主键
     */
    private Long id;

    /**
     * 数量
     */
    private String num;

    /**
     * 数量
     */
    private String numStr;

    /**
     * 溢短装
     */
    private String numfloat;

    /**
     * 贸易条款
     */
    private String tradeItem;

    /**
     * 贴水
     */
    private String agio;

    /**
     * 计价基准
     */
    private String valuationBase;

    /**
     * 付款条款
     */
    private String payItem;
    
    /**
     * 需求发布类型，1-按油种，2-按性质
     */
    private Integer publishType;

    private final String[] typeEvents = {"BL","NOR","COD","ITT","LDR","DATE"};

    public String[] getTypeEvents() {
        return typeEvents;
    }

    private String payItemJSON;

    public String getPayItemJSON() {
        return payItemJSON;
    }

    public void setPayItemJSON(String payTtemJSON) {
        this.payItemJSON = payTtemJSON;
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
    private String createTime;

    /**
     * 更新人
     */
    private Long updater;

    /**
     * 更新时间
     */
    private String updaterTime;

    /**
     * 父节点id
     */
    private Long parentId;

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

    /**
     * 成品油Id
     */
    private String productOilId;

    /**
     * 其他条款
     */
    private String otherItem;

    /**
     * 信用条款
     */
    private String authItem;

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
    private String tenderOutTime;

    /**
     * 截标时间
     */
    private String stopBidTime;

    /**
     * 响应开始时间
     */
    private String responseStartTime;

    /**
     * 响应结束时间
     */
    private String responseEndTime;

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
    private String productOilClassify;

    /**
     * 成品油种类
     */
    private String productOilKind;

    /**
     * 成品油规格
     */
    private String productOilSpecs;

    /**
     * 状态，包括需求状态和报价状态
     */
    private Integer status;

    /**
     * 是否代理
     */
    private String isAgent;

    /**
     * 代理商
     */
    private Long agenter;

    /**
     * 出口类型
     */
    private String exportType;

    /**
     * 质检报告
     */
    private String qcReport;

    /**
     * 价格方式
     */
    private String pricingMode;

    /**
     * 价格计量单位
     */
    private String pricingMeasureUnit;

    /**
     * 计量方式
     */
    private String measureMode;

    /**
     * 价格说明
     */
    private String priceDeclare;

    /**
     * 是否密封
     */
    private String isSeal;

    /**
     * 计价货币
     */
    private String valuationCurrency;

    /**
     * 单价（美元/桶）
     */
    private String unitPrice;

    /**
     * 采购方式
     */
    private String purchaseMode;

    /**
     * 发布时间
     */
    private String pubDate;

    /**
     * 地区
     */
    private String region;

    /**
     * 商品图片ID，多个间","分割
     */
    private String goodsImages;

    /**
     * 商检费分摊类型
     */
    private String inspectionFeeSharingType;
    
    /**
     * 法律
     */
    private String law;
    
    /**
     * gtc
     */
    private String gtc;
    
    /**
     * 允许装卸货时间
     */
    private String loadAndDischargePermittedTimespan;

    /*getters and setters*/
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPurchaseType() {
        return purchaseType;
    }

    public void setPurchaseType(Integer purchaseType) {
        this.purchaseType = purchaseType;
    }

    public String getOtherItem() {
        return otherItem;
    }

    public void setOtherItem(String otherItem) {
        this.otherItem = otherItem;
    }

    public String getAuthItem() {
        return authItem;
    }

    public void setAuthItem(String authItem) {
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

    public String getProductOilClassify() {
        return productOilClassify;
    }

    public void setProductOilClassify(String productOilClassify) {
        this.productOilClassify = productOilClassify;
    }

    public String getProductOilSpecs() {
        return productOilSpecs;
    }

    public void setProductOilSpecs(String productOilSpecs) {
        this.productOilSpecs = productOilSpecs;
    }

    public String getTradeItem() {
        return tradeItem;
    }

    public void setTradeItem(String tradeItem) {
        this.tradeItem = tradeItem;
    }

    public String getPayItem() {
        return payItem;
    }

    public void setPayItem(String payItem) {
        this.payItem = payItem;
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

    public DemandRelevanterVO getAgent() {
        return agent;
    }

    public void setAgent(DemandRelevanterVO agent) {
        this.agent = agent;
    }

    public DemandRelevanterVO getSupplier() {
        return supplier;
    }

    public void setSupplier(DemandRelevanterVO supplier) {
        this.supplier = supplier;
    }

    public String getValuationCurrency() {
        return valuationCurrency;
    }

    public void setValuationCurrency(String valuationCurrency) {
        this.valuationCurrency = valuationCurrency;
    }

    public String getValuationBase() {
        return valuationBase;
    }

    public void setValuationBase(String valuationBase) {
        this.valuationBase = valuationBase;
    }

    public String getPricingMeasureUnit() {
        return pricingMeasureUnit;
    }

    public void setPricingMeasureUnit(String pricingMeasureUnit) {
        this.pricingMeasureUnit = pricingMeasureUnit;
    }

    public String getValuationPeriodType() {
        return valuationPeriodType;
    }

    public void setValuationPeriodType(String valuationPeriodType) {
        this.valuationPeriodType = valuationPeriodType;
    }

    public String getValuationPeriodStart() {
        return valuationPeriodStart;
    }

    public void setValuationPeriodStart(String valuationPeriodStart) {
        this.valuationPeriodStart = valuationPeriodStart;
    }

    public String getValuationPeriodEnd() {
        return valuationPeriodEnd;
    }

    public void setValuationPeriodEnd(String valuationPeriodEnd) {
        this.valuationPeriodEnd = valuationPeriodEnd;
    }

    public String getMeasureMode() {
        return measureMode;
    }

    public void setMeasureMode(String measureMode) {
        this.measureMode = measureMode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAgio() {
        return agio;
    }

    public void setAgio(String agio) {
        this.agio = agio;
    }

    public String getValuationFormula() {
        return valuationFormula;
    }

    public void setValuationFormula(String valuationFormula) {
        this.valuationFormula = valuationFormula;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getCreater() {
        return creater;
    }

    public void setCreater(Long creater) {
        this.creater = creater;
    }

    public DemandRelevanterVO getBuyer() {
        return buyer;
    }

    public void setBuyer(DemandRelevanterVO buyer) {
        this.buyer = buyer;
    }

    public String getProductOilKind() {
        return productOilKind;
    }

    public void setProductOilKind(String productOilKind) {
        this.productOilKind = productOilKind;
    }

	public CrudeOilInfoVO[] getCrudeOilsInfoVO() {
		return crudeOilsInfoVO;
	}

	public void setCrudeOilsInfoVO(CrudeOilInfoVO[] crudeOilsInfoVO) {
		this.crudeOilsInfoVO = crudeOilsInfoVO;
	}
	
	public void setCrudeOils(List<CrudeOilVO> crudeOils) {
        this.crudeOils = crudeOils;
    }
	
	public List<CrudeOilVO> getCrudeOils() {
        return crudeOils;
    }

	public String getCrudeOilOptions() {
		return crudeOilOptions;
	}

	public void setCrudeOilOptions(String crudeOilOptions) {
        //油种名称转换逻辑
        crudeOilOptions =
                crudeOilOptions
                        .replace("API度", VisitorLocale.getByCurrentLanguage(Constant.LISTED_0038))
                        .replace("含硫量",VisitorLocale.getByCurrentLanguage(Constant.LISTED_0035));
        this.crudeOilOptions = crudeOilOptions;
	}

	public String getNumfloat() {
		return numfloat;
	}

	public void setNumfloat(String numfloat) {
		this.numfloat = numfloat;
	}

    public String getPurchaseMode() {
        return purchaseMode;
    }

    public void setPurchaseMode(String purchaseMode) {
        this.purchaseMode = purchaseMode;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Long getUpdater() {
        return updater;
    }

    public void setUpdater(Long updater) {
        this.updater = updater;
    }

    public String getUpdaterTime() {
        return updaterTime;
    }

    public void setUpdaterTime(String updaterTime) {
        this.updaterTime = updaterTime;
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

    public String getResponseStartTime() {
        return responseStartTime;
    }

    public void setResponseStartTime(String responseStartTime) {
        this.responseStartTime = responseStartTime;
    }

    public String getResponseEndTime() {
        return responseEndTime;
    }

    public void setResponseEndTime(String responseEndTime) {
        this.responseEndTime = responseEndTime;
    }

    public String getIsAgent() {
        return isAgent;
    }

    public void setIsAgent(String isAgent) {
        this.isAgent = isAgent;
    }

    public Long getAgenter() {
        return agenter;
    }

    public void setAgenter(Long agenter) {
        this.agenter = agenter;
    }

    public String getExportType() {
        return exportType;
    }

    public void setExportType(String exportType) {
        this.exportType = exportType;
    }

    public String getQcReport() {
        return qcReport;
    }

    public void setQcReport(String qcReport) {
        this.qcReport = qcReport;
    }

    public String getPricingMode() {
        return pricingMode;
    }

    public void setPricingMode(String pricingMode) {
        this.pricingMode = pricingMode;
    }

    public String getPriceDeclare() {
        return priceDeclare;
    }

    public void setPriceDeclare(String priceDeclare) {
        this.priceDeclare = priceDeclare;
    }

    public String getIsSeal() {
        return isSeal;
    }

    public void setIsSeal(String isSeal) {
        this.isSeal = isSeal;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getGoodsImages() {
        return goodsImages;
    }

    public void setGoodsImages(String goodsImages) {
        this.goodsImages = goodsImages;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getNumStr() {
        return numStr;
    }

    public void setNumStr(String numStr) {
        this.numStr = numStr;
    }

	public String getInspectionFeeSharingType() {
		return inspectionFeeSharingType;
	}

	public void setInspectionFeeSharingType(String inspectionFeeSharingType) {
		this.inspectionFeeSharingType = inspectionFeeSharingType;
	}

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
    
	public Integer getPublishType() {
        return publishType;
    }

    public void setPublishType(Integer publishType) {
        this.publishType = publishType;
    }
    
    public String getLoadAndDischargePermittedTimespan() {
        return loadAndDischargePermittedTimespan;
    }

    public void setLoadAndDischargePermittedTimespan(String loadAndDischargePermittedTimespan) {
        this.loadAndDischargePermittedTimespan = loadAndDischargePermittedTimespan;
    }
}
