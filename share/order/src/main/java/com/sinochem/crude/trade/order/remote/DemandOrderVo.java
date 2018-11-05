package com.sinochem.crude.trade.order.remote;

import com.alibaba.dubbo.common.utils.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by sijliu on 25/11/2017.
 */
public class DemandOrderVo implements Serializable{

    /**
     * 成品油
     */
    public ProductOilResource productOilResource = new ProductOilResource();

    /**
     * 原油
     */
    public CrudeOilResource crudeOilResource = new CrudeOilResource();

    /**
     * 买方
     */
    public RelevanterVO buyer = new Buyer();

    /**
     * 贸易方
     */
    public RelevanterVO trader = new Trader();

    /**
     * 供应方
     */
    public RelevanterVO provider = new Provider();

    /**
     * 运输
     */
    public Transport transport = new Transport();

    /**
     * 泊位
     */
    public List<ShipBerth> shipBerth = new ArrayList<>();

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

    public String getPayItem() {
        return payItem;
    }

    public void setPayItem(String payItem) {
        this.payItem = payItem;
    }

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
     * 计价公式json
     */
    private String valuationFormulaJson;

    /**
     * 单据类型
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
     * 其他条款
     */
    private String otherItem;

    /**
     * 授信条款
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
     * 单据编号
     */
    private String uuid;

    /**
     * 原油油种
     */
    private String crudeOilOptions;
    
    /**
     * 商检费分摊方式 
     * 1-seller’s account
	 * 2-buyer’s account
	 * 3-50/50 between seller&buyer
	 * 4-shared between parties
     */
    private Integer inspectionFeeSharingType;

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
     * 允许装卸货时间
     */
    private String loadAndDischargePermittedTimespan;

    /**
     * 合约类型
     */
    private String contractKind;

    /**
     * 法律
     */
    private String law;

    /**
     * GTC
     */
    private String gtc;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNum() {
        return num;
    }

    public Integer getInspectionFeeSharingType() {
		return inspectionFeeSharingType;
	}

	public void setInspectionFeeSharingType(Integer inspectionFeeSharingType) {
		this.inspectionFeeSharingType = inspectionFeeSharingType;
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

    public Integer getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Integer unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getPurchaseMode() {
        return purchaseMode;
    }

    public void setPurchaseMode(Integer purchaseMode) {
        this.purchaseMode = purchaseMode;
    }

    public ProductOilResource getProductOilResource() {
        return productOilResource==null?new ProductOilResource():productOilResource;
    }

    public void setProductOilResource(ProductOilResource productOilResource) {
        this.productOilResource = productOilResource;
    }

    public CrudeOilResource getCrudeOilResource() {
        return crudeOilResource==null?new CrudeOilResource():crudeOilResource;
    }

    public void setCrudeOilResource(CrudeOilResource crudeOilResource) {
        this.crudeOilResource = crudeOilResource;
    }

    public RelevanterVO getBuyer() {
        return buyer==null?new Buyer():buyer;
    }

    public void setBuyer(RelevanterVO buyer) {
        this.buyer = buyer;
    }

    public RelevanterVO getTrader() {
        return trader==null?new Trader():trader;
    }

    public void setTrader(RelevanterVO trader) {
        this.trader = trader;
    }

    public RelevanterVO getProvider() {
        return provider==null?new Provider():provider;
    }

    public void setProvider(RelevanterVO provider) {
        this.provider = provider;
    }

    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    public List<ShipBerth> getShipBerth() {
        return CollectionUtils.isEmpty(shipBerth)?new ArrayList<ShipBerth>():shipBerth;
    }

    public void setShipBerth(List<ShipBerth> shipBerth) {
        this.shipBerth = shipBerth;
    }

    public String getValuationFormulaJson() {
        return valuationFormulaJson;
    }

    public void setValuationFormulaJson(String valuationFormulaJson) {
        this.valuationFormulaJson = valuationFormulaJson;
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
}
