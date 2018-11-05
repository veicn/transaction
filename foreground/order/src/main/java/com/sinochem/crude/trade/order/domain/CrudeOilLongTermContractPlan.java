package com.sinochem.crude.trade.order.domain;

import java.math.BigDecimal;

/**
 * 长约的采购细节
 * 
 * @author Leo
 *
 */
public class CrudeOilLongTermContractPlan {

	/**
	 * PK
	 */
	private Long id;

	/**
	 * 对应的长约id
	 */
	private Long contractId;

	/**
	 * 年月
	 */
	private Integer date;

	/**
	 * 数量，等于num*rate
	 */
	private Long num;

	/**
	 * 数量
	 */

	/**
	 * 数量偏差,按照百分比
	 */
	private Integer numFloat;

	/**
	 * 汇率
	 */
	private Long rate;

	/**
	 * 计量数量
	 */
	private Long meternum;

	/**
	 * 原油
	 */
	private CrudeOilResource crudeOilResource;

	/**
	 * 原油id
	 */
	private Long crudeOilId;
	private Integer dateEnd;
    
    public Integer getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Integer dateEnd) {
		this.dateEnd = dateEnd;
	}

	private String dateStr;//开始年月
    public String getDateStr() {
		return dateStr;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

	/**
     * 结束年月
     */
    private String dateStr2;
    /**
     * 计价公式
     */
    private String valuationFormula;
    
    
    public String getValuationFormula() {
		return valuationFormula;
	}

	public void setValuationFormula(String valuationFormula) {
		this.valuationFormula = valuationFormula;
	}

	public String getDateStr2() {
		return dateStr2;
	}

	public void setDateStr2(String dateStr2) {
		this.dateStr2 = dateStr2;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getContractId() {
		return contractId;
	}

	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}

	public Integer getDate() {
		return date;
	}

	public void setDate(Integer date) {
		this.date = date;
	}

	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}

	public Integer getNumFloat() {
		return numFloat;
	}

	public void setNumFloat(Integer numFloat) {
		this.numFloat = numFloat;
	}

	public Long getRate() {
		return rate;
	}

	public void setRate(Long rate) {
		this.rate = rate;
	}

	public Long getMeternum() {
		return meternum;
	}

	public void setMeternum(Long meternum) {
		this.meternum = meternum;
	}

	public CrudeOilResource getCrudeOilResource() {
		return crudeOilResource;
	}

	public void setCrudeOilResource(CrudeOilResource crudeOilResource) {
		this.crudeOilResource = crudeOilResource;
	}

	public Long getCrudeOilId() {
		return crudeOilId;
	}

	public void setCrudeOilId(Long crudeOilId) {
		this.crudeOilId = crudeOilId;
	}

	/**
	 * 数量
	 * @return
	 */
	private Long convertToLong(BigDecimal b1){
		return b1 == null?null:b1.multiply(new BigDecimal(1000)).longValue();
	}
}
