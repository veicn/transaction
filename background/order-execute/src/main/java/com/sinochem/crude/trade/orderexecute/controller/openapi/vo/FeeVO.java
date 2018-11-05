package com.sinochem.crude.trade.orderexecute.controller.openapi.vo;

import java.io.Serializable;

/**
 * 
 * @author Down
 *
 */
public class FeeVO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 费用单价
	 */
	private Double feePrice;
	
	/**
	 * 是否计入结算单（0否1是）
	 */
	private String isAgent;
	
	/**
	 * 计价数量
	 */
	private Double quantity;
	
	/**
	 * 费用代码
	 */
	private String subjectCode;
	
	/**
	 * 费用名称
	 */
	private String subjectName;
	
	/**
	 * 单条费用合计
	 */
	private Double totalFee;
	
	/**
	 * 计价方式（1按量 2按单）
	 */
	private String valuationModel;

	public Double getFeePrice() {
		return feePrice;
	}

	public void setFeePrice(Double feePrice) {
		this.feePrice = feePrice;
	}

	public String getIsAgent() {
		return isAgent;
	}

	public void setIsAgent(String isAgent) {
		this.isAgent = isAgent;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public Double getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(Double totalFee) {
		this.totalFee = totalFee;
	}

	public String getValuationModel() {
		return valuationModel;
	}

	public void setValuationModel(String valuationModel) {
		this.valuationModel = valuationModel;
	}
}