package com.sinochem.crude.trade.transport.query;

import java.util.HashMap;
import java.util.Map;

import com.sinochem.crude.trade.common.QueryBase;
import com.sinochem.crude.trade.common.SimplePageInfo;

public class PalletQuery extends QueryBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 装港*/
	String loadPort;
	/** 卸港 */
	String unloadPort;
	/** 公司ID*/
	Long epMemberId;
	
	/** 油合同编号 */
	String orderPact;
	/** 油品名称 */
	String oilName;
	
	/**状态*/
	String status;
	
	/**发布人*/
	String publisher;
	/**船型*/
	String shipType;
	/**最小船龄*/
	String shipAgeBeg;
	/**最大船龄*/
	String shipAgeEnd;
	/**最小发布日期*/
	String publishTimeBeg;
	/**最大发布日期*/
	String publishTimeEnd;
	/**装期开始*/
	String layCanBeg;
	/**装期结束*/
	String layCanEnd;
	/**需求编号*/
	String palletCode;
	/**租船类型*/
	String orderNo;
	
	@Override
	public Map<String, String> getParameters() {
		Map<String, String> param = new HashMap<String, String>();
		param.put("loadPort", getLoadPort());
		param.put("unloadPort", getUnloadPort());
		param.put("orderCode", getOrderPact());
		param.put("oilName", getOilName());
		param.put("status", getStatus());
		param.put("publisher", getPublisher());
		param.put("shipType", getShipType());
		param.put("shipAgeBeg", getShipAgeBeg());
		param.put("shipAgeEnd", getShipAgeEnd());
		param.put("publishTimeBeg", getPublishTimeBeg());
		param.put("publishTimeEnd", getPublishTimeEnd());
		param.put("layCanBeg", getLayCanBeg());
		param.put("layCanEnd", getLayCanEnd());
		param.put("palletCode", getPalletCode());
		param.put("orderNo", getOrderNo());
		return param;
	}
	
	public String getOrderPact() {
		return orderPact;
	}

	public void setOrderPact(String orderPact) {
		this.orderPact = orderPact;
	}

	public String getOilName() {
		return oilName;
	}

	public void setOilName(String oilName) {
		this.oilName = oilName;
	}

	public String getLoadPort() {
		return loadPort;
	}
	
	public void setLoadPort(String loadPort) {
		this.loadPort = loadPort;
	}
	
	public String getUnloadPort() {
		return unloadPort;
	}
	
	public void setUnloadPort(String unloadPort) {
		this.unloadPort = unloadPort;
	}

	public Long getEpMemberId() {
		return epMemberId;
	}

	public void setEpMemberId(Long epMemberId) {
		this.epMemberId = epMemberId;
	}
	public SimplePageInfo getPageInfo(){
		SimplePageInfo pageInfo = new SimplePageInfo();
		pageInfo.setPageNum(super.getCurrentPage());
		pageInfo.setPageSize(super.getPageSize());
		return pageInfo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getShipType() {
		return shipType;
	}

	public void setShipType(String shipType) {
		this.shipType = shipType;
	}

	public String getShipAgeBeg() {
		return shipAgeBeg;
	}

	public void setShipAgeBeg(String shipAgeBeg) {
		this.shipAgeBeg = shipAgeBeg;
	}

	public String getShipAgeEnd() {
		return shipAgeEnd;
	}

	public void setShipAgeEnd(String shipAgeEnd) {
		this.shipAgeEnd = shipAgeEnd;
	}

	public String getPublishTimeBeg() {
		return publishTimeBeg;
	}

	public void setPublishTimeBeg(String publishTimeBeg) {
		this.publishTimeBeg = publishTimeBeg;
	}

	public String getPublishTimeEnd() {
		return publishTimeEnd;
	}

	public void setPublishTimeEnd(String publishTimeEnd) {
		this.publishTimeEnd = publishTimeEnd;
	}

	public String getLayCanBeg() {
		return layCanBeg;
	}

	public void setLayCanBeg(String layCanBeg) {
		this.layCanBeg = layCanBeg;
	}

	public String getLayCanEnd() {
		return layCanEnd;
	}

	public void setLayCanEnd(String layCanEnd) {
		this.layCanEnd = layCanEnd;
	}

	public String getPalletCode() {
		return palletCode;
	}

	public void setPalletCode(String palletCode) {
		this.palletCode = palletCode;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
}
