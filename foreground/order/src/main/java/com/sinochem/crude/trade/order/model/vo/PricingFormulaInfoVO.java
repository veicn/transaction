package com.sinochem.crude.trade.order.model.vo;

import java.util.Date;

public class PricingFormulaInfoVO {
	
	/**
	 * 公式类型
	 */
	private int formulaType;

	/**
	 * 价格
	 */
	private double price;

	/**
	 * 计量单位
	 */
	private String uom;

	/**
	 * 货币
	 */
	private String currency;

	/**
	 * +/-
	 */
	private String mark;

	/**
	 * mark和premium组成升贴水
	 */
	private String premium;
	
	/**
	 * 计价期始
	 */
	private Date startDate;
	
	/**
	 * 计价期末
	 */
	private Date endDate;

	/**
	 * 事件类型
	 */
	private String eventType;
	
	/*********** 事件计价的四种指定计价期的方式 start **********/
	//以下event_days, event_days0, event_weeks, event_months
    //分别表示四种指定计价期的方式，*必须*保证这四行有且只有一行为true，其它都为false
	
    //event_days
	private boolean daysEvent;
	private String eventDaysNum1;
	private String eventDaysNum2; //0或者1
	private String eventDaysNum3;
	private String eventDaysNum4;
	private String eventDaysNum5 = "0"; //定死为0
	private String eventDaysNum6;
	private boolean deemEvent;
	private Date deemDate;

    //event_days0
	private boolean daysEvent0;
	private String eventDays0Num1;
	private String fromMark;//+或者-
	private String eventDays0Num2;
	private String eventDays0Num2Day;//business day或者calendar day
	private String toMark;//+或者-
	private String eventDays0Num3;
	private String eventDays0Num3Day;//business day或者calendar day

	//event_weeks
	private boolean weeksEvent;
	private String eventWeeksNum1;
	private String eventWeeksNum2;

	//event_months
	private boolean monthsEvent;
	private String monthMark;//+或者-
	private String eventMonthsNum1;
    
	/*********** 事件计价的四种指定计价期的方式 end **********/
    
	/*********** 整个quote代表一个市场合约 start **********/

	/**
	 * 报价商品
	 */
	private String commodity;

	/**
	 * 价格源
	 */
	private String priceSource;

	/**
	 * 市场
	 */
	private String market;

	/**
	 * 期
	 */
	private String period;

	/**
	 * H/L/C
	 */
	private String hlc;

	/*********** 整个quote代表一个市场合约 end **********/
	
	/**
	 * 价格公式
	 */
	private String formula;
	
	/**
	 * 数据结构json
	 */
	private String priceStructure;
	
	public int getFormulaType() {
		return formulaType;
	}

	public void setFormulaType(int formulaType) {
		this.formulaType = formulaType;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getPremium() {
		return premium;
	}

	public void setPremium(String premium) {
		this.premium = premium;
	}

	public String getCommodity() {
		return commodity;
	}

	public void setCommodity(String commodity) {
		this.commodity = commodity;
	}

	public String getPriceSource() {
		return priceSource;
	}

	public void setPriceSource(String priceSource) {
		this.priceSource = priceSource;
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

	public String getHlc() {
		return hlc;
	}

	public void setHlc(String hlc) {
		this.hlc = hlc;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public String getPriceStructure() {
		return priceStructure;
	}

	public void setPriceStructure(String priceStructure) {
		this.priceStructure = priceStructure;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public boolean isDaysEvent() {
		return daysEvent;
	}

	public void setDaysEvent(boolean daysEvent) {
		this.daysEvent = daysEvent;
	}

	public String getEventDaysNum1() {
		return eventDaysNum1;
	}

	public void setEventDaysNum1(String eventDaysNum1) {
		this.eventDaysNum1 = eventDaysNum1;
	}

	public String getEventDaysNum2() {
		return eventDaysNum2;
	}

	public void setEventDaysNum2(String eventDaysNum2) {
		this.eventDaysNum2 = eventDaysNum2;
	}

	public String getEventDaysNum3() {
		return eventDaysNum3;
	}

	public void setEventDaysNum3(String eventDaysNum3) {
		this.eventDaysNum3 = eventDaysNum3;
	}

	public String getEventDaysNum4() {
		return eventDaysNum4;
	}

	public void setEventDaysNum4(String eventDaysNum4) {
		this.eventDaysNum4 = eventDaysNum4;
	}

	public String getEventDaysNum5() {
		return eventDaysNum5;
	}

	public void setEventDaysNum5(String eventDaysNum5) {
		this.eventDaysNum5 = eventDaysNum5;
	}

	public String getEventDaysNum6() {
		return eventDaysNum6;
	}

	public void setEventDaysNum6(String eventDaysNum6) {
		this.eventDaysNum6 = eventDaysNum6;
	}

	public boolean isDeemEvent() {
		return deemEvent;
	}

	public void setDeemEvent(boolean deemEvent) {
		this.deemEvent = deemEvent;
	}

	public Date getDeemDate() {
		return deemDate;
	}

	public void setDeemDate(Date deemDate) {
		this.deemDate = deemDate;
	}

	public boolean isDaysEvent0() {
		return daysEvent0;
	}

	public void setDaysEvent0(boolean daysEvent0) {
		this.daysEvent0 = daysEvent0;
	}

	public String getEventDays0Num1() {
		return eventDays0Num1;
	}

	public void setEventDays0Num1(String eventDays0Num1) {
		this.eventDays0Num1 = eventDays0Num1;
	}

	public String getFromMark() {
		return fromMark;
	}

	public void setFromMark(String fromMark) {
		this.fromMark = fromMark;
	}

	public String getEventDays0Num2() {
		return eventDays0Num2;
	}

	public void setEventDays0Num2(String eventDays0Num2) {
		this.eventDays0Num2 = eventDays0Num2;
	}

	public String getEventDays0Num2Day() {
		return eventDays0Num2Day;
	}

	public void setEventDays0Num2Day(String eventDays0Num2Day) {
		this.eventDays0Num2Day = eventDays0Num2Day;
	}

	public String getToMark() {
		return toMark;
	}

	public void setToMark(String toMark) {
		this.toMark = toMark;
	}

	public String getEventDays0Num3() {
		return eventDays0Num3;
	}

	public void setEventDays0Num3(String eventDays0Num3) {
		this.eventDays0Num3 = eventDays0Num3;
	}

	public String getEventDays0Num3Day() {
		return eventDays0Num3Day;
	}

	public void setEventDays0Num3Day(String eventDays0Num3Day) {
		this.eventDays0Num3Day = eventDays0Num3Day;
	}

	public boolean isWeeksEvent() {
		return weeksEvent;
	}

	public void setWeeksEvent(boolean weeksEvent) {
		this.weeksEvent = weeksEvent;
	}

	public String getEventWeeksNum1() {
		return eventWeeksNum1;
	}

	public void setEventWeeksNum1(String eventWeeksNum1) {
		this.eventWeeksNum1 = eventWeeksNum1;
	}

	public String getEventWeeksNum2() {
		return eventWeeksNum2;
	}

	public void setEventWeeksNum2(String eventWeeksNum2) {
		this.eventWeeksNum2 = eventWeeksNum2;
	}

	public boolean isMonthsEvent() {
		return monthsEvent;
	}

	public void setMonthsEvent(boolean monthsEvent) {
		this.monthsEvent = monthsEvent;
	}

	public String getMonthMark() {
		return monthMark;
	}

	public void setMonthMark(String monthMark) {
		this.monthMark = monthMark;
	}

	public String getEventMonthsNum1() {
		return eventMonthsNum1;
	}

	public void setEventMonthsNum1(String eventMonthsNum1) {
		this.eventMonthsNum1 = eventMonthsNum1;
	}
	
}
