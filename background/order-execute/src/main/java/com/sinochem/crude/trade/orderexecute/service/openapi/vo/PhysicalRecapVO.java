package com.sinochem.crude.trade.orderexecute.service.openapi.vo;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;

import com.sinochem.crude.trade.orderexecute.service.openapi.constants.PartyTypeEnum;

/**
 * 实货录入VO
 */
public class PhysicalRecapVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/** 概况信息备注 */
	private String basic_comments;
	
	/** 主体 */
	private String booking;
	
	/** 买卖方向:Buy表示买;Sell表示卖 */
	private String buysell;
	
	/** 商品名称 */
	private String commodity;
	
	/** 对家 */
	private String counterparty;
	
	/** 信用条款 */
	private String credit_term;
	
	/** 装货时间起 */
	private String delivery_from;
	
	/** 交货条款 eg:FOB */
	private String delivery_term;
	
	/** 装货时间止 */
	private String delivery_to;
	
	/** 子业务组 */
	private String desk;
	
	/** 计算精度 */
	private String formula_precision;
	
	/** Order UUID */
	private String id;
	
	/** OrderNo */
	private String alias;
	
	/** 付款条款 */
	private PaymentTerm payment_term;
	
	/** 数量公式 */
	private transient Quantity quantityBean;
	
	/** 数量公式文本 */
	private String quantity_text;
	
	/** 数量公式类型 */
	private String quantity_type;
	
	/** 投资组合 */
	private String strategy;
	
	/** 业务组 */
	private String team;
	
	/** 交易日期 */
	private String trade_date;
	
	/** 交易员 */
	private String trader;
	
	/** 计价类型 */
	private String pricing_type;
	
	/** 计价公式 */
	private String pricing_detail;
	
	/** 交易商品大类 */
	private String tradeCategory;
	
	
	public String getPricing_detail() {
		return pricing_detail;
	}

	public void setPricing_detail(String pricing_detail) {
		this.pricing_detail = pricing_detail;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getBasic_comments() {
		return basic_comments;
	}

	public void setBasic_comments(String basic_comments) {
		this.basic_comments = basic_comments;
	}

	public String getBooking() {
		return booking;
	}

	public void setBooking(String booking) {
		this.booking = booking;
	}

	public String getBuysell() {
		return buysell;
	}

	public void setBuysell(PartyTypeEnum partyTypeEnum) {
		this.buysell = partyTypeEnum.name();
	}

	public String getCommodity() {
		return commodity;
	}

	public void setCommodity(String commodity) {
		this.commodity = commodity;
	}

	public String getCounterparty() {
		return counterparty;
	}

	public void setCounterparty(String counterparty) {
		this.counterparty = counterparty;
	}

	public String getCredit_term() {
		return credit_term;
	}

	public void setCredit_term(String credit_term) {
		this.credit_term = credit_term;
	}

	public String getDelivery_from() {
		return delivery_from;
	}

	public void setDelivery_from(Date delivery_from) {
		if(delivery_from != null){
			this.delivery_from = DateFormatUtils.format(delivery_from, "yyyy-MM-dd");
		}
	}

	public String getDelivery_term() {
		return delivery_term;
	}

	public void setDelivery_term(String delivery_term) {
		this.delivery_term = delivery_term;
	}

	public String getDelivery_to() {
		return delivery_to;
	}

	public void setDelivery_to(Date delivery_to) {
		if(delivery_to != null){
			this.delivery_to = DateFormatUtils.format(delivery_to, "yyyy-MM-dd");
		}
	}

	public String getDesk() {
		return desk;
	}

	public void setDesk(String desk) {
		this.desk = desk;
	}

	public String getFormula_precision() {
		return formula_precision;
	}

	public void setFormula_precision(String formula_precision) {
		this.formula_precision = formula_precision;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public PaymentTerm getPayment_term() {
		return payment_term;
	}

	public void setPayment_term(PaymentTerm payment_term) {
		this.payment_term = payment_term;
	}

	public String getPricing_type() {
		return pricing_type;
	}

	public String getQuantity_text() {
		return quantity_text;
	}

	public String getQuantity_type() {
		if(quantityBean != null){
			return quantityBean.getMoreOrLessSymbol().getName();
		}
		return quantity_type;
	}

	public String getStrategy() {
		return strategy;
	}

	public void setStrategy(String strategy) {
		this.strategy = strategy;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getTrade_date() {
		return trade_date;
	}

	public void setTrade_date(Date trade_date) {
		if(trade_date != null){
			this.trade_date = DateFormatUtils.format(trade_date, "yyyy-MM-dd");
		}
	}

	public String getTrader() {
		return trader;
	}

	public void setTrader(String trader) {
		this.trader = trader;
	}
	
	public Quantity getQuantityBean() {
		return quantityBean;
	}

	public void setQuantityBean(Quantity quantityBean) {
		this.quantityBean = quantityBean;
	}

	public String[] getQuantity() {
		if(this.quantityBean != null){
			return this.quantityBean.toQuantityArray();
		}
		
		return null;
	}

	public void setPricing_type(String pricing_type) {
		this.pricing_type = pricing_type;
	}

	public String getTradeCategory() {
		return tradeCategory;
	}

	public void setTradeCategory(String tradeCategory) {
		this.tradeCategory = tradeCategory;
	}
	
}