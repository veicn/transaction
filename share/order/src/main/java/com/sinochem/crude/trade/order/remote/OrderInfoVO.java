package com.sinochem.crude.trade.order.remote;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * 订单信息VO
 * @author hexinfang
 */
public class OrderInfoVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -401964042393320617L;
	
	public OrderInfoVO(){
		this.goodsInfo = new GoodsInfo();
	}
	
	/**----------------------- 订单信息 -----------------------*/
	/** 订单UUID */
	private String orderUuid;
	/** 订单号 */
	private String orderNo;
	/** 成交单号 */
	private Long orderTradeId;
	/** 买卖类型，1:采购，2：销售 */
	private String orderBuySell;
	/** 提单日 */
	private Date orderBillDate;
	/** 成交方式（1：长协；2：现货；3：临时） */
	private String orderTradeMode;
	/** 成交时间 */
	private Date orderTradeTime;
	/** 交易类型（1：招标；2：询价，3：议价） */
	private String orderTradeType;
	/** 交易大类（1：原油；2：成品油；3：化工品） */
	private String orderTradeCategory;
	/** 授信条款 */
	private String orderCreditTerm;
	/** 其他条款 */
	private String orderOtherTerm;
	/** 商检机构 */
	private String orderSurvey;
	/** 授信模式 01	企业授信
				02	信用证
				03	保证金 */
	private String orderCreditModel;
	/** 进出口配额文件 */
	private String orderQuatoFile;
	/** 订单备注 */
	private String orderRemark;
	
	/**----------------------- 买家信息 -----------------------*/
	/** 公司ID */
	private Long buyerCustomerId;
	/** 公司名称 */
	private String buyerCustomerName;
	/** 公司地址 */
	private String buyerCustomerAddress;
	/** 联系人 */
	private String buyerContactName;
	/** 联系人电话 */
	private String buyerContactNumber;
	/** EMAIL */
	private String buyerEmail;
	/** 传真 */
	private String buyerFax;
	/** 交易员ID */
	private Long buyerDealerId;
	/** 交易员名称 */
	private String buyerDealerName;
	
	
	/**----------------------- 卖家信息 -----------------------*/
	/** 公司ID */
	private Long sellerCustomerId;
	/** 公司名称 */
	private String sellerCustomerName;
	/** 公司地址 */
	private String sellerCustomerAddress;
	/** 联系人 */
	private String sellerContactName;
	/** 联系人电话 */
	private String sellerContactNumber;
	/** EMAIL */
	private String sellerEmail;
	/** 传真 */
	private String sellerFax;
	/** 交易员ID */
	private Long sellerDealerId;
	/** 交易员名称 */
	private String sellerDealerName;
	
	
	/**----------------------- 代理商信息 -----------------------*/
	/** 公司ID */
	private Long agentCustomerId;
	/** 公司名称 */
	private String agentCustomerName;
	/** 公司地址 */
	private String agentCustomerAddress;
	/** 联系人 */
	private String agentContactName;
	/** 联系人电话 */
	private String agentContactNumber;
	/** EMAIL */
	private String agentEmail;
	/** 传真 */
	private String agentFax;
	/** 交易员ID */
	private Long agentDealerId;
	/** 交易员名称 */
	private String agentDealerName;
	/** 代理方式，1：卖方代理  2：买方代理 */
	private String agentType;
	
	/**----------------------- 报价信息 -----------------------*/
	/** 贸易条款 
	 * 	FOB
		CIF
		CFR
		FCA
		DES
		DDU
		ITT
		*/
	private String offerTradeTerm;
	/** 升贴水 */
	private BigDecimal offerPremium;
	/** 计价基准 */
	private String offerBasePrice;
	/** 计价期 */
	private String offerPricingDesc;
	/** 计价公式说明 */
	private String offerPriceFormula;
	/** 计价公式 */
	private String offerPriceFormulaJson;
	/** 付款日期说明 */
	private String offerPaymentTerm;
	/** 付款日期JSON {"typeEvent":"BL","eventInclusion":"after","eventDays":"5","date":""} */
	private String offerPaymentTermJson;
	/** 船型 
	 * 01	MR
		02	PANAMAX
		03	AFRAMAX
		04	VLCC
		05	ULCC
		06	SUEZMAX
		07	HANDY
		*/
	private String offerShipModel;
	/** 付款日期 */
	private Date offerPayDate;
	/** 装货港 */
	private String offerLoadingPort;
	/** 卸货港 */
	private String offerUnloadingPort;
	/** 装期开始日期 */
	private Date offerDeliveryDateStart;
	/** 装期结束日期 */
	private Date offerDeliveryDateEnd;
	/** 到货期开始日期 */
	private Date offerDischargeDateStart;
	/** 到货期结束日期 */
	private Date offerDischargeDateEnd;
	
	/** 计价类型：
	 *  01	Average
		02	Complex
		03	Event
		04	Fixed
		05	Trigger
	 */
	private String pricingType;

	/**
	 * 允许装卸货时间
	 */
	private String loadAndDischargePermittedTimespan;
	/**
	 * 商检费分摊类型 
	 * 1-seller’s account
	 * 2-buyer’s account
	 * 3-50/50 between seller&buyer
	 * 4-shared between parties
	 * 
	 */
	private String inspectionFeeSharingType;
	/**
	 * 计量方式
	 */
	private String measureMode;
	
	/** 商品信息 */
	private GoodsInfo goodsInfo; 
	
	/** 创建人ID */
	private Long createUserId;

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

	public String getOfferPriceFormulaJson() {
		return offerPriceFormulaJson;
	}

	public void setOfferPriceFormulaJson(String offerPriceFormulaJson) {
		this.offerPriceFormulaJson = offerPriceFormulaJson;
	}

	public String getLoadAndDischargePermittedTimespan() {
		return loadAndDischargePermittedTimespan;
	}

	public void setLoadAndDischargePermittedTimespan(String loadAndDischargePermittedTimespan) {
		this.loadAndDischargePermittedTimespan = loadAndDischargePermittedTimespan;
	}

	public String getMeasureMode() {
		return measureMode;
	}

	public void setMeasureMode(String measureMode) {
		this.measureMode = measureMode;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	public String getOrderUuid() {
		return orderUuid;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public String getInspectionFeeSharingType() {
		return inspectionFeeSharingType;
	}

	public void setInspectionFeeSharingType(String inspectionFeeSharingType) {
		this.inspectionFeeSharingType = inspectionFeeSharingType;
	}

	public Long getOrderTradeId() {
		return orderTradeId;
	}

	public String getOrderBuySell() {
		return orderBuySell;
	}

	public Date getOrderBillDate() {
		return orderBillDate;
	}

	public String getOrderTradeMode() {
		return orderTradeMode;
	}

	public Date getOrderTradeTime() {
		return orderTradeTime;
	}

	public String getOrderTradeType() {
		return orderTradeType;
	}

	public String getOrderTradeCategory() {
		return orderTradeCategory;
	}

	public String getOrderCreditTerm() {
		return orderCreditTerm;
	}

	public String getOrderOtherTerm() {
		return orderOtherTerm;
	}

	public String getOrderSurvey() {
		return orderSurvey;
	}

	public String getOrderCreditModel() {
		return orderCreditModel;
	}

	public String getOrderQuatoFile() {
		return orderQuatoFile;
	}

	public String getOrderRemark() {
		return orderRemark;
	}

	public Long getBuyerCustomerId() {
		return buyerCustomerId;
	}

	public String getBuyerCustomerName() {
		return buyerCustomerName;
	}

	public String getBuyerCustomerAddress() {
		return buyerCustomerAddress;
	}

	public String getBuyerContactName() {
		return buyerContactName;
	}

	public String getBuyerContactNumber() {
		return buyerContactNumber;
	}

	public String getBuyerEmail() {
		return buyerEmail;
	}

	public String getBuyerFax() {
		return buyerFax;
	}

	public Long getBuyerDealerId() {
		return buyerDealerId;
	}

	public String getBuyerDealerName() {
		return buyerDealerName;
	}

	public Long getSellerCustomerId() {
		return sellerCustomerId;
	}

	public String getSellerCustomerName() {
		return sellerCustomerName;
	}

	public String getSellerCustomerAddress() {
		return sellerCustomerAddress;
	}

	public String getSellerContactName() {
		return sellerContactName;
	}

	public String getSellerContactNumber() {
		return sellerContactNumber;
	}

	public String getSellerEmail() {
		return sellerEmail;
	}

	public String getSellerFax() {
		return sellerFax;
	}

	public Long getSellerDealerId() {
		return sellerDealerId;
	}

	public String getSellerDealerName() {
		return sellerDealerName;
	}

	public Long getAgentCustomerId() {
		return agentCustomerId;
	}

	public String getAgentCustomerName() {
		return agentCustomerName;
	}

	public String getAgentCustomerAddress() {
		return agentCustomerAddress;
	}

	public String getAgentContactName() {
		return agentContactName;
	}

	public String getAgentContactNumber() {
		return agentContactNumber;
	}

	public String getAgentEmail() {
		return agentEmail;
	}

	public String getAgentFax() {
		return agentFax;
	}

	public Long getAgentDealerId() {
		return agentDealerId;
	}

	public String getAgentDealerName() {
		return agentDealerName;
	}

	public String getAgentType() {
		return agentType;
	}

	public String getOfferTradeTerm() {
		return offerTradeTerm;
	}

	public BigDecimal getOfferPremium() {
		return offerPremium;
	}

	public String getOfferBasePrice() {
		return offerBasePrice;
	}

	public String getOfferPricingDesc() {
		return offerPricingDesc;
	}

	public String getOfferPriceFormula() {
		return offerPriceFormula;
	}

	public String getOfferPaymentTerm() {
		return offerPaymentTerm;
	}

	public String getOfferPaymentTermJson() {
		return offerPaymentTermJson;
	}

	public String getOfferShipModel() {
		return offerShipModel;
	}

	public Date getOfferPayDate() {
		return offerPayDate;
	}

	public String getOfferLoadingPort() {
		return offerLoadingPort;
	}

	public String getOfferUnloadingPort() {
		return offerUnloadingPort;
	}

	public Date getOfferDeliveryDateStart() {
		return offerDeliveryDateStart;
	}

	public Date getOfferDeliveryDateEnd() {
		return offerDeliveryDateEnd;
	}

	public Date getOfferDischargeDateStart() {
		return offerDischargeDateStart;
	}

	public Date getOfferDischargeDateEnd() {
		return offerDischargeDateEnd;
	}

	public void setOrderUuid(String orderUuid) {
		this.orderUuid = orderUuid;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public void setOrderTradeId(Long orderTradeId) {
		this.orderTradeId = orderTradeId;
	}

	public void setOrderBuySell(String orderBuySell) {
		this.orderBuySell = orderBuySell;
	}

	public void setOrderBillDate(Date orderBillDate) {
		this.orderBillDate = orderBillDate;
	}

	public void setOrderTradeMode(String orderTradeMode) {
		this.orderTradeMode = orderTradeMode;
	}

	public void setOrderTradeTime(Date orderTradeTime) {
		this.orderTradeTime = orderTradeTime;
	}

	public void setOrderTradeType(String orderTradeType) {
		this.orderTradeType = orderTradeType;
	}

	public void setOrderTradeCategory(String orderTradeCategory) {
		this.orderTradeCategory = orderTradeCategory;
	}

	public void setOrderCreditTerm(String orderCreditTerm) {
		this.orderCreditTerm = orderCreditTerm;
	}

	public void setOrderOtherTerm(String orderOtherTerm) {
		this.orderOtherTerm = orderOtherTerm;
	}

	public void setOrderSurvey(String orderSurvey) {
		this.orderSurvey = orderSurvey;
	}

	public void setOrderCreditModel(String orderCreditModel) {
		this.orderCreditModel = orderCreditModel;
	}

	public void setOrderQuatoFile(String orderQuatoFile) {
		this.orderQuatoFile = orderQuatoFile;
	}

	public void setOrderRemark(String orderRemark) {
		this.orderRemark = orderRemark;
	}

	public void setBuyerCustomerId(Long buyerCustomerId) {
		this.buyerCustomerId = buyerCustomerId;
	}

	public void setBuyerCustomerName(String buyerCustomerName) {
		this.buyerCustomerName = buyerCustomerName;
	}

	public void setBuyerCustomerAddress(String buyerCustomerAddress) {
		this.buyerCustomerAddress = buyerCustomerAddress;
	}

	public void setBuyerContactName(String buyerContactName) {
		this.buyerContactName = buyerContactName;
	}

	public void setBuyerContactNumber(String buyerContactNumber) {
		this.buyerContactNumber = buyerContactNumber;
	}

	public void setBuyerEmail(String buyerEmail) {
		this.buyerEmail = buyerEmail;
	}

	public void setBuyerFax(String buyerFax) {
		this.buyerFax = buyerFax;
	}

	public void setBuyerDealerId(Long buyerDealerId) {
		this.buyerDealerId = buyerDealerId;
	}

	public void setBuyerDealerName(String buyerDealerName) {
		this.buyerDealerName = buyerDealerName;
	}

	public void setSellerCustomerId(Long sellerCustomerId) {
		this.sellerCustomerId = sellerCustomerId;
	}

	public void setSellerCustomerName(String sellerCustomerName) {
		this.sellerCustomerName = sellerCustomerName;
	}

	public void setSellerCustomerAddress(String sellerCustomerAddress) {
		this.sellerCustomerAddress = sellerCustomerAddress;
	}

	public void setSellerContactName(String sellerContactName) {
		this.sellerContactName = sellerContactName;
	}

	public void setSellerContactNumber(String sellerContactNumber) {
		this.sellerContactNumber = sellerContactNumber;
	}

	public void setSellerEmail(String sellerEmail) {
		this.sellerEmail = sellerEmail;
	}

	public void setSellerFax(String sellerFax) {
		this.sellerFax = sellerFax;
	}

	public void setSellerDealerId(Long sellerDealerId) {
		this.sellerDealerId = sellerDealerId;
	}

	public void setSellerDealerName(String sellerDealerName) {
		this.sellerDealerName = sellerDealerName;
	}

	public void setAgentCustomerId(Long agentCustomerId) {
		this.agentCustomerId = agentCustomerId;
	}

	public void setAgentCustomerName(String agentCustomerName) {
		this.agentCustomerName = agentCustomerName;
	}

	public void setAgentCustomerAddress(String agentCustomerAddress) {
		this.agentCustomerAddress = agentCustomerAddress;
	}

	public void setAgentContactName(String agentContactName) {
		this.agentContactName = agentContactName;
	}

	public void setAgentContactNumber(String agentContactNumber) {
		this.agentContactNumber = agentContactNumber;
	}

	public void setAgentEmail(String agentEmail) {
		this.agentEmail = agentEmail;
	}

	public void setAgentFax(String agentFax) {
		this.agentFax = agentFax;
	}

	public void setAgentDealerId(Long agentDealerId) {
		this.agentDealerId = agentDealerId;
	}

	public void setAgentDealerName(String agentDealerName) {
		this.agentDealerName = agentDealerName;
	}

	public void setAgentType(String agentType) {
		this.agentType = agentType;
	}

	public void setOfferTradeTerm(String offerTradeTerm) {
		this.offerTradeTerm = offerTradeTerm;
	}

	public void setOfferPremium(BigDecimal offerPremium) {
		this.offerPremium = offerPremium;
	}

	public void setOfferBasePrice(String offerBasePrice) {
		this.offerBasePrice = offerBasePrice;
	}

	public void setOfferPricingDesc(String offerPricingDesc) {
		this.offerPricingDesc = offerPricingDesc;
	}

	public void setOfferPriceFormula(String offerPriceFormula) {
		this.offerPriceFormula = offerPriceFormula;
	}

	public void setOfferPaymentTerm(String offerPaymentTerm) {
		this.offerPaymentTerm = offerPaymentTerm;
	}

	public void setOfferPaymentTermJson(String offerPaymentTermJson) {
		this.offerPaymentTermJson = offerPaymentTermJson;
	}

	public void setOfferShipModel(String offerShipModel) {
		this.offerShipModel = offerShipModel;
	}

	public void setOfferPayDate(Date offerPayDate) {
		this.offerPayDate = offerPayDate;
	}

	public void setOfferLoadingPort(String offerLoadingPort) {
		this.offerLoadingPort = offerLoadingPort;
	}

	public void setOfferUnloadingPort(String offerUnloadingPort) {
		this.offerUnloadingPort = offerUnloadingPort;
	}

	public void setOfferDeliveryDateStart(Date offerDeliveryDateStart) {
		this.offerDeliveryDateStart = offerDeliveryDateStart;
	}

	public void setOfferDeliveryDateEnd(Date offerDeliveryDateEnd) {
		this.offerDeliveryDateEnd = offerDeliveryDateEnd;
	}

	public void setOfferDischargeDateStart(Date offerDischargeDateStart) {
		this.offerDischargeDateStart = offerDischargeDateStart;
	}

	public void setOfferDischargeDateEnd(Date offerDischargeDateEnd) {
		this.offerDischargeDateEnd = offerDischargeDateEnd;
	}

	public GoodsInfo getGoodsInfo() {
		return goodsInfo;
	}

	public String getPricingType() {
		return pricingType;
	}

	public void setPricingType(String pricingType) {
		this.pricingType = pricingType;
	}

	/**
	 * 商品信息
	 */
	public class GoodsInfo implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1992129911692892141L;
		/** 资源ID */
		private Integer resourceId;
		/** 中文名称 */
		private String zhName;
		/** 英文名你 */
		private String enName;
		
		/** 数量 */
		private BigDecimal quantity;
		/** 数量单位 */
		private String quantityUnit;
		/** 单价 */
		private BigDecimal unitPrice;
		/** 货币单位 */
		private String currency = "USD";
		
		/** 溢短装 */
		private BigDecimal moreLess;
		/** 产地 */
		private String countryOrigin;
		/** 计量方式 */
		private String measureMode;
		/** 备注 */
		private String remark;
		
		/**规格（成品油）*/
		private String spec;
		
		public Integer getResourceId() {
			return resourceId;
		}
		public String getZhName() {
			return zhName;
		}
		public String getEnName() {
			return enName;
		}
		public BigDecimal getQuantity() {
			return quantity;
		}
		public String getQuantityUnit() {
			return quantityUnit;
		}
		public BigDecimal getMoreLess() {
			return moreLess;
		}
		public String getCountryOrigin() {
			return countryOrigin;
		}
		public String getRemark() {
			return remark;
		}
		public void setResourceId(Integer resourceId) {
			this.resourceId = resourceId;
		}
		public void setZhName(String zhName) {
			this.zhName = zhName;
		}
		public void setEnName(String enName) {
			this.enName = enName;
		}
		public void setQuantity(BigDecimal quantity) {
			this.quantity = quantity;
		}
		public void setQuantityUnit(String quantityUnit) {
			this.quantityUnit = quantityUnit;
		}
		public void setMoreLess(BigDecimal moreLess) {
			this.moreLess = moreLess;
		}
		public void setCountryOrigin(String countryOrigin) {
			this.countryOrigin = countryOrigin;
		}
		public void setRemark(String remark) {
			this.remark = remark;
		}
		public String getMeasureMode() {
			return measureMode;
		}
		public void setMeasureMode(String measureMode) {
			this.measureMode = measureMode;
		}
		public BigDecimal getUnitPrice() {
			return unitPrice;
		}
		public void setUnitPrice(BigDecimal unitPrice) {
			this.unitPrice = unitPrice;
		}
		public String getCurrency() {
			return currency;
		}
		public void setCurrency(String currency) {
			if(StringUtils.isNotBlank(currency)){
				this.currency = currency;
			}
		}
		public String getSpec() {
			return spec;
		}
		public void setSpec(String spec) {
			this.spec = spec;
		}
		
	}
	
	/**
	 * 平均计价相关参数
	 * @author me
	 */
	public class AverageParams implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 3388465583180660940L;

		/** 来源 */
		private String price_source;
		
		/** 市场 */
		private String market;
		
		/** 合约名 */
		private String period;
		
		/** 取价时间 */
		private Date h_l;
		
		/** 价格条款 */
		private String price_term;
		
		/** 均价类型  */
		private String average;
		
		/** 来源 ， 例："EXCHANGE" */
		private String shortcut;
		
		/** 计价开始日期 */
		private Date date1;
		
		/** 计价结束日期 */
		private Date date2;

		public String getPrice_source() {
			return price_source;
		}

		public void setPrice_source(String price_source) {
			this.price_source = price_source;
		}

		public String getMarket() {
			return market;
		}

		public void setMarket(String market) {
			this.market = market;
		}

		public String getPeriod() {
			return period;
		}

		public void setPeriod(String period) {
			this.period = period;
		}

		public Date getH_l() {
			return h_l;
		}

		public void setH_l(Date h_l) {
			this.h_l = h_l;
		}

		public String getPrice_term() {
			return price_term;
		}

		public void setPrice_term(String price_term) {
			this.price_term = price_term;
		}

		public String getAverage() {
			return average;
		}

		public void setAverage(String average) {
			this.average = average;
		}

		public String getShortcut() {
			return shortcut;
		}

		public void setShortcut(String shortcut) {
			this.shortcut = shortcut;
		}

		public Date getDate1() {
			return date1;
		}

		public void setDate1(Date date1) {
			this.date1 = date1;
		}

		public Date getDate2() {
			return date2;
		}

		public void setDate2(Date date2) {
			this.date2 = date2;
		}
	}
}
