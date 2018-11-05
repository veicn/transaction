package com.sinochem.crude.trade.orderexecute.controller.openapi.vo;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;

/**
 * 
 * @author Down
 *
 */
public class StatementSheetVO extends InputVO {
	private static final long serialVersionUID = 1L;

	/**
	 * 代理协议
	 */
	private String agentContract;
	
	/**
	 * 贴水
	 */
	private java.math.BigDecimal agio;
	
	/**
	 * 基准价格
	 */
	private java.math.BigDecimal basePrice;
	
	/**
	 * 提单量（净桶）
	 */
	private java.math.BigDecimal billWeightBbl;
	
	/**
	 * 提单量（净吨）
	 */
	private java.math.BigDecimal billWeightT;
	
	/**
	 * 对账时间
	 */
	private Date checkTime;
	
	/**
	 * 联系人
	 */
	private String contactName;
	
	/**
	 * 合同号
	 */
	private String contractNo;
	
	/**
	 * 合同量
	 */
	private java.math.BigDecimal contractQuantity;
	
	/**
	 * 对账方名称
	 */
	private String customerName;
	
	/**
	 * 邮箱
	 */
	private String email;
	
	/**
	 * 费用清单
	 */
	private List<FeeVO> feeList;
	
	/**
	 * 货款金额
	 */
	private java.math.BigDecimal goodsAmount;
	
	/**
	 * 油品
	 */
	private String oil;
	
	/**
	 * paymentDate
	 */
	private Date paymentDate;
	
	/**
	 * 货款单价
	 */
	private java.math.BigDecimal price;
	
	/**
	 * 价格公式
	 */
	private String priceFormula;
	
	/**
	 * 对账类型（1预结算 2正式结算）
	 */
	private String statementType;
	
	/**
	 * 船名
	 */
	private String shipName;
	
	/**
	 * 联系电话
	 */
	private String tel;
	
	/**
	 * 结算总金额
	 */
	private java.math.BigDecimal totalAmount;
	
	/**
	 * 费用合计
	 */
	private java.math.BigDecimal totalFee;
	
	/**
	 * 贸易条款
	 */
	private String tradeTerm;
	
	/**
	 * 订单ID
	 */
	private String uuid;
	
	/**
	 * 已结算金额（正式结算单需要）
	 * @return
	 */
	private java.math.BigDecimal settledAmount;
	
	/**
	 * 结算量（桶）
	 */
	private java.math.BigDecimal settlementQuantityBbl;
	
	/**
	 * 提单日（yyyy-MM-dd）
	 */
	private String billDate;
	
	private String dataJson;

	public String getDataJson() {
		return dataJson;
	}

	public void setDataJson(String dataJson) {
		this.dataJson = dataJson;
	}

	public String getAgentContract() {
		return agentContract;
	}

	public void setAgentContract(String agentContract) {
		this.agentContract = agentContract;
	}

	public java.math.BigDecimal getAgio() {
		return agio;
	}

	public void setAgio(java.math.BigDecimal agio) {
		this.agio = agio;
	}

	public java.math.BigDecimal getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(java.math.BigDecimal basePrice) {
		this.basePrice = basePrice;
	}

	public java.math.BigDecimal getBillWeightBbl() {
		return billWeightBbl;
	}

	public void setBillWeightBbl(java.math.BigDecimal billWeightBbl) {
		this.billWeightBbl = billWeightBbl;
	}

	public java.math.BigDecimal getBillWeightT() {
		return billWeightT;
	}

	public void setBillWeightT(java.math.BigDecimal billWeightT) {
		this.billWeightT = billWeightT;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(String checkTime)  throws ParseException {
		this.checkTime = DateUtils.parseDate(checkTime, new String[]{"yyyy-MM-dd"});
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public java.math.BigDecimal getContractQuantity() {
		return contractQuantity;
	}

	public void setContractQuantity(java.math.BigDecimal contractQuantity) {
		this.contractQuantity = contractQuantity;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<FeeVO> getFeeList() {
		return feeList;
	}

	public void setFeeList(List<FeeVO> feeList) {
		this.feeList = feeList;
	}

	public java.math.BigDecimal getGoodsAmount() {
		return goodsAmount;
	}

	public void setGoodsAmount(java.math.BigDecimal goodsAmount) {
		this.goodsAmount = goodsAmount;
	}

	public String getOil() {
		return oil;
	}

	public void setOil(String oil) {
		this.oil = oil;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) throws ParseException {
		this.paymentDate = DateUtils.parseDate(paymentDate, new String[]{"yyyy-MM-dd"});
	}

	public java.math.BigDecimal getPrice() {
		return price;
	}

	public void setPrice(java.math.BigDecimal price) {
		this.price = price;
	}

	public String getPriceFormula() {
		return priceFormula;
	}

	public void setPriceFormula(String priceFormula) {
		this.priceFormula = priceFormula;
	}

	public String getShipName() {
		return shipName;
	}

	public void setShipName(String shipName) {
		this.shipName = shipName;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public java.math.BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(java.math.BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public java.math.BigDecimal getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(java.math.BigDecimal totalFee) {
		this.totalFee = totalFee;
	}

	public String getTradeTerm() {
		return tradeTerm;
	}

	public void setTradeTerm(String tradeTerm) {
		this.tradeTerm = tradeTerm;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getStatementType() {
		return statementType;
	}

	public java.math.BigDecimal getSettledAmount() {
		return settledAmount;
	}

	public java.math.BigDecimal getSettlementQuantityBbl() {
		return settlementQuantityBbl;
	}

	public void setStatementType(String statementType) {
		this.statementType = statementType;
	}

	public void setSettledAmount(java.math.BigDecimal settledAmount) {
		this.settledAmount = settledAmount;
	}

	public void setSettlementQuantityBbl(java.math.BigDecimal settlementQuantityBbl) {
		this.settlementQuantityBbl = settlementQuantityBbl;
	}

	public String getBillDate() {
		return billDate;
	}

	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}
	
}