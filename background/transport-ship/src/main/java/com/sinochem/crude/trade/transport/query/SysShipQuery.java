package com.sinochem.crude.trade.transport.query;

import java.util.HashMap;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;

import com.sinochem.crude.trade.common.QueryBase;
import com.sinochem.crude.trade.common.SimplePageInfo;

public class SysShipQuery extends QueryBase {

	/**
	 * 船盘查询条件
	 */
	private static final long serialVersionUID = 1L;

	// 船名
	String name;

	// 状态
	String status;

	// 租船需求Uuid
	String palletUuid;

	// 船型
	String type;

	// 船龄范围起始
	Integer ageBeg;
	// 船龄范围结束
	Integer ageEnd;

	// 发布人
	String epMembername;

	// 船东
	String shipOwner;

	// 经纪人
	String brokerName;

	// 发布日期起始结束
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String publishBeg;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String publishEnd;

	// open 起始结束
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String openBeg;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String openEnd;

	// ETA 起始结束
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String ETABeg;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String ETAEnd;

	// 载重吨起始结束
	private java.math.BigDecimal quantityBeg;
	private java.math.BigDecimal quantityEnd;

	// 来源
	String from;

	/** 船盘发布类型（1船东/船东经纪人2转租船东3平台） */
	String relType;

	/** 船盘发布类型（1船东/船东经纪人2转租船东3平台） */
	public String getRelType() {
		return relType;
	}

	/** 船盘发布类型（1船东/船东经纪人2转租船东3平台） */
	public void setRelType(String relType) {
		this.relType = relType;
	}

	public String getPalletUuid() {
		return palletUuid;
	}

	public void setPalletUuid(String palletUuid) {
		this.palletUuid = palletUuid;
	}

	public Integer getAgeBeg() {
		return ageBeg;
	}

	public void setAgeBeg(Integer ageBeg) {
		this.ageBeg = ageBeg;
	}

	public Integer getAgeEnd() {
		return ageEnd;
	}

	public void setAgeEnd(Integer ageEnd) {
		this.ageEnd = ageEnd;
	}

	public String getEpMembername() {
		return epMembername;
	}

	public void setEpMembername(String epMembername) {
		this.epMembername = epMembername;
	}

	public String getShipOwner() {
		return shipOwner;
	}

	public void setShipOwner(String shipOwner) {
		this.shipOwner = shipOwner;
	}

	public String getBrokerName() {
		return brokerName;
	}

	public void setBrokerName(String brokerName) {
		this.brokerName = brokerName;
	}

	public String getPublishBeg() {
		return publishBeg;
	}

	public void setPublishBeg(String publishBeg) {
		this.publishBeg = publishBeg;
	}

	public String getPublishEnd() {
		return publishEnd;
	}

	public void setPublishEnd(String publishEnd) {
		this.publishEnd = publishEnd;
	}

	public String getOpenBeg() {
		return openBeg;
	}

	public void setOpenBeg(String openBeg) {
		this.openBeg = openBeg;
	}

	public String getOpenEnd() {
		return openEnd;
	}

	public void setOpenEnd(String openEnd) {
		this.openEnd = openEnd;
	}

	public String getETABeg() {
		return ETABeg;
	}

	public void setETABeg(String eTABeg) {
		ETABeg = eTABeg;
	}

	public String getETAEnd() {
		return ETAEnd;
	}

	public void setETAEnd(String eTAEnd) {
		ETAEnd = eTAEnd;
	}

	public java.math.BigDecimal getQuantityBeg() {
		return quantityBeg;
	}

	public void setQuantityBeg(java.math.BigDecimal quantityBeg) {
		this.quantityBeg = quantityBeg;
	}

	public java.math.BigDecimal getQuantityEnd() {
		return quantityEnd;
	}

	public void setQuantityEnd(java.math.BigDecimal quantityEnd) {
		this.quantityEnd = quantityEnd;
	}

	@Override
	public Map<String, String> getParameters() {
		Map<String, String> param = new HashMap<String, String>();
		
		param.put("palletUuid", getPalletUuid());
		param.put("name", getName());
		param.put("type", getType());
		param.put("brokerName", getBrokerName());
		param.put("epMembername", getEpMembername());
		param.put("shipOwner", getShipOwner());
		param.put("status", getStatus());
		param.put("relType", getRelType());
		
		if(getAgeBeg()!=null){
			param.put("ageBeg", String.valueOf(getAgeBeg()));
		}
		if(getAgeEnd()!=null){
			param.put("ageEnd", String.valueOf(getAgeEnd()));
		}
		param.put("publishBeg", getPublishBeg());
		param.put("publishEnd", getPublishEnd());
		param.put("openBeg", getOpenBeg());
		param.put("openEnd", getOpenEnd());
		param.put("ETABeg", getETABeg());
		param.put("ETAEnd", getETAEnd());
		if(getQuantityBeg()!=null){
			param.put("quantityBeg", String.valueOf(getQuantityBeg()));
		}
		if(getQuantityEnd()!=null){
			param.put("quantityEnd", String.valueOf(getQuantityEnd()));
		}
		return param;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public SimplePageInfo getPageInfo() {
		SimplePageInfo pageInfo = new SimplePageInfo();
		pageInfo.setPageNum(super.getCurrentPage());
		pageInfo.setPageSize(super.getPageSize());
		return pageInfo;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}
}
