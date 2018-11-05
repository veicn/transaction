package com.sinochem.crude.trade.orderexecute.model;

import java.io.Serializable;

/**
 * 移动端 订单详情数据
 * @author me
 *
 */
public class OrderDetailDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7660099406620778760L;
	
	/** 执行UUID */
	private String uuid;
	/** 成交单ID */
	private Long tradeId;
	/**交易大类（1原油；2成品油；3化工品）*/
	private String tradeCategory;  
	/** 贸易方式 */
	private String tradeMode;
	/** 订单状态描述 */
	private String orderStatusDesc;
	/** 订单编号 */
	private String orderNo;
	/** 创建时间 */
	private String createDate;
	/** 商品名称，油种 */
	private String goodsName;
	/** 规格 */
	private String spec;
	/** 数量 */
	private String quantity;
	/** 贸易条款 */
	private String tradeTerm;
	/** 计价方式 */
	private String priceType;
	/** 计价货币 */
	private String currency;
	/** 定价计量单位 */
	private String quantityUnit;
	/** 溢短装 */
	private String moreLess;
	/** 升贴水 */
	private String agio;
	/** 计价基准 */
	private String basicPrice;
	/** 计价公式 */
	private String priceFormula;
	/** 付款条款 */
	private String paymentTerm;
	/** 计价期 */
	private String pricingDesc;
	/** 装期 */
	private String deliveryDate;
	/** 到货期 */
	private String dischargeDate;
	/** 装船港 */
	private String loadingPort;
	/** 卸货港 */
	private String uploadingPort;
	/** 价格说明 */
	private String priceDesc;
	/** 备注 */
	private String remark;
	
	/** 提单日 */
	private String billDate;
	/** 付款日 */
	private String paymentDate;
	/** 提单量（净吨） */
	private String billWeightT;
	/** 提单量（净桶） */
	private String billWeightBbl;
	/** 结算价格 */
	private String settlementPrice;
	/** 结算量（桶） */
	private String settlementQuantityBbl;
	/** 基准价格 */
	private String basePrice;
	/** 费用合计 */
	private String totalFee;
	/** 已结算金额 */
	private String settledAmount;
	
	/** 买家公司名称 */
	private String buyerCustomerName;
	/** 买家公司地址 */
	private String buyerAddress;
	/** 买家联系人 */
	private String buyerContactName;
	/** 买家联系电话 */
	private String buyerTel;  
	/** 买家邮箱 */
	private String buyerEmail;  
	/** 买家传真 */
	private String buyerFax;
	
	/** 卖家公司名称 */
	private String sellerCustomerName;
	/** 卖家地址 */
	private String sellerAddress;
	/** 卖家联系人 */
	private String sellerContactName;
	/** 卖家联系电话 */
	private String sellerTel;
	/** 卖家邮箱 */
	private String sellerEmail;
	/** 卖家传真 */
	private String sellerFax;
	
	/** 其它条款 */
	private String otherTerm;
	/** 信用条款 */
	private String creditTerm;
	/** 商检机构 */
	private String survey;
	/** 出口配额文件 */
	private String quatoFile;
	
	
	public String getTradeCategory() {
		return tradeCategory;
	}
	public void setTradeCategory(String tradeCategory) {
		this.tradeCategory = tradeCategory;
	}
	public String getOrderStatusDesc() {
		return orderStatusDesc;
	}
	public void setOrderStatusDesc(String orderStatusDesc) {
		this.orderStatusDesc = orderStatusDesc;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getTradeTerm() {
		return tradeTerm;
	}
	public void setTradeTerm(String tradeTerm) {
		this.tradeTerm = tradeTerm;
	}
	public String getPriceType() {
		return priceType;
	}
	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getQuantityUnit() {
		return quantityUnit;
	}
	public void setQuantityUnit(String quantityUnit) {
		this.quantityUnit = quantityUnit;
	}
	public String getMoreLess() {
		return moreLess;
	}
	public void setMoreLess(String moreLess) {
		this.moreLess = moreLess;
	}
	public String getAgio() {
		return agio;
	}
	public void setAgio(String agio) {
		this.agio = agio;
	}
	public String getBasicPrice() {
		return basicPrice;
	}
	public void setBasicPrice(String basicPrice) {
		this.basicPrice = basicPrice;
	}
	public String getPriceFormula() {
		return priceFormula;
	}
	public void setPriceFormula(String priceFormula) {
		this.priceFormula = priceFormula;
	}
	public String getPaymentTerm() {
		return paymentTerm;
	}
	public void setPaymentTerm(String paymentTerm) {
		this.paymentTerm = paymentTerm;
	}
	public String getPricingDesc() {
		return pricingDesc;
	}
	public void setPricingDesc(String pricingDesc) {
		this.pricingDesc = pricingDesc;
	}
	public String getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public String getDischargeDate() {
		return dischargeDate;
	}
	public void setDischargeDate(String dischargeDate) {
		this.dischargeDate = dischargeDate;
	}
	public String getLoadingPort() {
		return loadingPort;
	}
	public void setLoadingPort(String loadingPort) {
		this.loadingPort = loadingPort;
	}
	public String getUploadingPort() {
		return uploadingPort;
	}
	public void setUploadingPort(String uploadingPort) {
		this.uploadingPort = uploadingPort;
	}
	public String getPriceDesc() {
		return priceDesc;
	}
	public void setPriceDesc(String priceDesc) {
		this.priceDesc = priceDesc;
	}
	public String getBillDate() {
		return billDate;
	}
	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}
	public String getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}
	public String getBillWeightT() {
		return billWeightT;
	}
	public void setBillWeightT(String billWeightT) {
		this.billWeightT = billWeightT;
	}
	public String getBillWeightBbl() {
		return billWeightBbl;
	}
	public void setBillWeightBbl(String billWeightBbl) {
		this.billWeightBbl = billWeightBbl;
	}
	public String getSettlementPrice() {
		return settlementPrice;
	}
	public void setSettlementPrice(String settlementPrice) {
		this.settlementPrice = settlementPrice;
	}
	public String getSettlementQuantityBbl() {
		return settlementQuantityBbl;
	}
	public void setSettlementQuantityBbl(String settlementQuantityBbl) {
		this.settlementQuantityBbl = settlementQuantityBbl;
	}
	public String getBasePrice() {
		return basePrice;
	}
	public void setBasePrice(String basePrice) {
		this.basePrice = basePrice;
	}
	public String getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}
	public String getSettledAmount() {
		return settledAmount;
	}
	public void setSettledAmount(String settledAmount) {
		this.settledAmount = settledAmount;
	}
	public String getBuyerCustomerName() {
		return buyerCustomerName;
	}
	public void setBuyerCustomerName(String buyerCustomerName) {
		this.buyerCustomerName = buyerCustomerName;
	}
	public String getBuyerContactName() {
		return buyerContactName;
	}
	public void setBuyerContactName(String buyerContactName) {
		this.buyerContactName = buyerContactName;
	}
	public String getBuyerTel() {
		return buyerTel;
	}
	public void setBuyerTel(String buyerTel) {
		this.buyerTel = buyerTel;
	}
	public String getBuyerEmail() {
		return buyerEmail;
	}
	public void setBuyerEmail(String buyerEmail) {
		this.buyerEmail = buyerEmail;
	}
	public String getBuyerFax() {
		return buyerFax;
	}
	public void setBuyerFax(String buyerFax) {
		this.buyerFax = buyerFax;
	}
	public String getSellerCustomerName() {
		return sellerCustomerName;
	}
	public void setSellerCustomerName(String sellerCustomerName) {
		this.sellerCustomerName = sellerCustomerName;
	}
	public String getSellerContactName() {
		return sellerContactName;
	}
	public void setSellerContactName(String sellerContactName) {
		this.sellerContactName = sellerContactName;
	}
	public String getSellerTel() {
		return sellerTel;
	}
	public void setSellerTel(String sellerTel) {
		this.sellerTel = sellerTel;
	}
	public String getSellerEmail() {
		return sellerEmail;
	}
	public void setSellerEmail(String sellerEmail) {
		this.sellerEmail = sellerEmail;
	}
	public String getSellerFax() {
		return sellerFax;
	}
	public void setSellerFax(String sellerFax) {
		this.sellerFax = sellerFax;
	}
	public String getOtherTerm() {
		return otherTerm;
	}
	public void setOtherTerm(String otherTerm) {
		this.otherTerm = otherTerm;
	}
	public String getCreditTerm() {
		return creditTerm;
	}
	public void setCreditTerm(String creditTerm) {
		this.creditTerm = creditTerm;
	}
	public String getSurvey() {
		return survey;
	}
	public void setSurvey(String survey) {
		this.survey = survey;
	}
	public String getQuatoFile() {
		return quatoFile;
	}
	public void setQuatoFile(String quatoFile) {
		this.quatoFile = quatoFile;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getTradeMode() {
		return tradeMode;
	}
	public void setTradeMode(String tradeMode) {
		this.tradeMode = tradeMode;
	}
	public String getBuyerAddress() {
		return buyerAddress;
	}
	public void setBuyerAddress(String buyerAddress) {
		this.buyerAddress = buyerAddress;
	}
	public String getSellerAddress() {
		return sellerAddress;
	}
	public void setSellerAddress(String sellerAddress) {
		this.sellerAddress = sellerAddress;
	}
	public Long getTradeId() {
		return tradeId;
	}
	public void setTradeId(Long tradeId) {
		this.tradeId = tradeId;
	}
	
}
