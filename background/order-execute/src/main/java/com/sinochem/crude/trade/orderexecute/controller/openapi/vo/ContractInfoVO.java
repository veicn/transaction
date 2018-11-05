package com.sinochem.crude.trade.orderexecute.controller.openapi.vo;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;

/**
 * 
 * @author Down
 *
 */
public class ContractInfoVO implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 买方公司
	 */
	private String buyerCustomer;
	
	/**
	 * 合同名称
	 */
	private String contractName;
	
	/**
	 * 合同编号
	 */
	private String contractNo;
	
	/**
	 * 类别（1销售2运输3项目）
	 */
	private String contractType;
	
	/**
	 * 合同ID
	 */
	private String contractUuid;
	
	/**
	 * 滞期费
	 */
	private String demurrage;
	
	/**
	 * 合同附件
	 */
	private List<ContractInfoAttaVO> fileList;
	
	/**
	 * 标的物
	 */
	private String goodsName;
	
	/**
	 * GTC
	 */
	private String gtc;
	
	/**
	 * INSPECTION
	 */
	private String inspection;
	
	/**
	 * 装期
	 */
	private String layTime;
	
	/**
	 * 付款条款
	 */
	private String paymentTerm;
	
	/**
	 * 价格公式
	 */
	private String priceFormula;
	
	/**
	 * PRICE ROUNDING保留小数
	 */
	private String priceRounding;
	
	/**
	 * 计价期
	 */
	private String pricingDesc;
	
	/**
	 * 数量
	 */
	private Double quantity;
	
	/**
	 * 卖方
	 */
	private String sellerCustomer;
	
	/**
	 * 船名
	 */
	private String shipName;
	
	/**
	 * 签订日期
	 */
	private Date signDate;
	
	/**
	 * 贸易条款
	 */
	private String tradeTerm;
	
	/**
	 * 订单ID
	 */
	private String uuid;

	public String getBuyerCustomer() {
		return buyerCustomer;
	}

	public void setBuyerCustomer(String buyerCustomer) {
		this.buyerCustomer = buyerCustomer;
	}

	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	public String getContractUuid() {
		return contractUuid;
	}

	public void setContractUuid(String contractUuid) {
		this.contractUuid = contractUuid;
	}

	public String getDemurrage() {
		return demurrage;
	}

	public void setDemurrage(String demurrage) {
		this.demurrage = demurrage;
	}

	public List<ContractInfoAttaVO> getFileList() {
		return fileList;
	}

	public void setFileList(List<ContractInfoAttaVO> fileList) {
		this.fileList = fileList;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGtc() {
		return gtc;
	}

	public void setGtc(String gtc) {
		this.gtc = gtc;
	}

	public String getInspection() {
		return inspection;
	}

	public void setInspection(String inspection) {
		this.inspection = inspection;
	}

	public String getLayTime() {
		return layTime;
	}

	public void setLayTime(String layTime) {
		this.layTime = layTime;
	}

	public String getPaymentTerm() {
		return paymentTerm;
	}

	public void setPaymentTerm(String paymentTerm) {
		this.paymentTerm = paymentTerm;
	}

	public String getPriceFormula() {
		return priceFormula;
	}

	public void setPriceFormula(String priceFormula) {
		this.priceFormula = priceFormula;
	}

	public String getPriceRounding() {
		return priceRounding;
	}

	public void setPriceRounding(String priceRounding) {
		this.priceRounding = priceRounding;
	}

	public String getPricingDesc() {
		return pricingDesc;
	}

	public void setPricingDesc(String pricingDesc) {
		this.pricingDesc = pricingDesc;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public String getSellerCustomer() {
		return sellerCustomer;
	}

	public void setSellerCustomer(String sellerCustomer) {
		this.sellerCustomer = sellerCustomer;
	}

	public String getShipName() {
		return shipName;
	}

	public void setShipName(String shipName) {
		this.shipName = shipName;
	}

	public Date getSignDate() {
		return signDate;
	}

	public void setSignDate(String signDate) throws ParseException {
		this.signDate = DateUtils.parseDate(signDate, new String[]{"yyyy-MM-dd"});
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
}