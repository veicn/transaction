package com.sinochem.crude.trade.info.model;

import java.math.BigDecimal;

public class PriceIndexVO {

	private String uuid;
	private String tempUuid;
	private BigDecimal smeiValue;
	private BigDecimal riseFall;
	private BigDecimal riseFallRate;
	private BigDecimal open;
	private BigDecimal high;
	private BigDecimal low;
	private BigDecimal preClose;
	private BigDecimal preRiseFall;
	private BigDecimal preRiseFallRate;
	private String remark;
	
	
	public String getTempUuid() {
		return tempUuid;
	}
	public void setTempUuid(String tempUuid) {
		this.tempUuid = tempUuid;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public BigDecimal getSmeiValue() {
		return smeiValue;
	}
	public void setSmeiValue(BigDecimal smeiValue) {
		this.smeiValue = smeiValue;
	}
	public BigDecimal getRiseFall() {
		return riseFall;
	}
	public void setRiseFall(BigDecimal riseFall) {
		this.riseFall = riseFall;
	}
	public BigDecimal getRiseFallRate() {
		return riseFallRate;
	}
	public void setRiseFallRate(BigDecimal riseFallRate) {
		this.riseFallRate = riseFallRate;
	}
	public BigDecimal getOpen() {
		return open;
	}
	public void setOpen(BigDecimal open) {
		this.open = open;
	}
	public BigDecimal getHigh() {
		return high;
	}
	public void setHigh(BigDecimal high) {
		this.high = high;
	}
	public BigDecimal getLow() {
		return low;
	}
	public void setLow(BigDecimal low) {
		this.low = low;
	}
	public BigDecimal getPreClose() {
		return preClose;
	}
	public void setPreClose(BigDecimal preClose) {
		this.preClose = preClose;
	}
	public BigDecimal getPreRiseFall() {
		return preRiseFall;
	}
	public void setPreRiseFall(BigDecimal preRiseFall) {
		this.preRiseFall = preRiseFall;
	}
	public BigDecimal getPreRiseFallRate() {
		return preRiseFallRate;
	}
	public void setPreRiseFallRate(BigDecimal preRiseFallRate) {
		this.preRiseFallRate = preRiseFallRate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}