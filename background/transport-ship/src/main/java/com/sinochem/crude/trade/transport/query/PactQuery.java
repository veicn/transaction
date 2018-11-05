package com.sinochem.crude.trade.transport.query;

import java.util.HashMap;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;

import com.sinochem.crude.trade.common.QueryBase;
import com.sinochem.crude.trade.common.SimplePageInfo;

public class PactQuery extends QueryBase {

	private static final long serialVersionUID = 4607791991273674516L;

	/**船名*/
	private String shipName;
	
	/**公司*/
	private Long epMemberId;

	/**编号*/
	private String code;
	
	/**船合同*/
	private String uuid;

	/**船合同uuid*/
	private String shipPactUuid;
	
	/**状态*/
	private String status;
	
	/**油种*/
	private String oilType;
	
	/**船合同编号*/
	private String pactCode;
	/**租船协议编号*/
	private String agreementCode;
	/**船龄*/
	private String ageBeg;
	/**船龄*/
	private String ageEnd;
	/**租船人*/
	private String publishName;
	/**承租人*/
	private String carrierName;
	/**装期开始*/
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String laycanBeg;
	/**装期结束*/
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String laycanEnd;
	/**船东*/
	private String shipOwner;
	/**装港*/
	private String loadPort;
	/**卸港*/
	private String unloadPort;
	/**生成时间*/
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String createBeg;
	/**生成时间*/
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String createEnd;
	@Override
	public Map<String, String> getParameters() {
		Map<String, String> demo = new HashMap<String, String>();
		demo.put("shipName", shipName);
		demo.put("code", code);
		demo.put("shipPactUuid", shipPactUuid);
		demo.put("uuid", uuid);
		demo.put("status", status);
		demo.put("pactCode", pactCode);
		demo.put("agreementCode", agreementCode);
		demo.put("ageBeg", ageBeg);
		demo.put("ageEnd", ageEnd);
		demo.put("publishName", publishName);
		demo.put("carrierName", carrierName);
		demo.put("laycanBeg", laycanBeg);
		demo.put("laycanEnd", laycanEnd);
		demo.put("shipOwner", shipOwner);
		demo.put("loadPort", loadPort);
		demo.put("unloadPort", unloadPort);
		demo.put("createBeg", createBeg);
		demo.put("createEnd", createEnd);
		demo.put("oilType", oilType);
		return demo;
	}
	
	
	public String getPactCode() {
		return pactCode;
	}
	public void setPactCode(String pactCode) {
		this.pactCode = pactCode;
	}
	public String getAgreementCode() {
		return agreementCode;
	}
	public void setAgreementCode(String agreementCode) {
		this.agreementCode = agreementCode;
	}
	public String getAgeBeg() {
		return ageBeg;
	}
	public void setAgeBeg(String ageBeg) {
		this.ageBeg = ageBeg;
	}
	public String getAgeEnd() {
		return ageEnd;
	}
	public void setAgeEnd(String ageEnd) {
		this.ageEnd = ageEnd;
	}
	public String getPublishName() {
		return publishName;
	}
	public void setPublishName(String publishName) {
		this.publishName = publishName;
	}
	public String getCarrierName() {
		return carrierName;
	}
	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}
	public String getLaycanBeg() {
		return laycanBeg;
	}
	public void setLaycanBeg(String laycanBeg) {
		this.laycanBeg = laycanBeg;
	}
	public String getLaycanEnd() {
		return laycanEnd;
	}
	public void setLaycanEnd(String laycanEnd) {
		this.laycanEnd = laycanEnd;
	}
	public String getShipOwner() {
		return shipOwner;
	}
	public void setShipOwner(String shipOwner) {
		this.shipOwner = shipOwner;
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
	public String getCreateBeg() {
		return createBeg;
	}
	public void setCreateBeg(String createBeg) {
		this.createBeg = createBeg;
	}
	public String getCreateEnd() {
		return createEnd;
	}
	public void setCreateEnd(String createEnd) {
		this.createEnd = createEnd;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getShipPactUuid() {
		return shipPactUuid;
	}
	public void setShipPactUuid(String shipPactUuid) {
		this.shipPactUuid = shipPactUuid;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getShipName() {
		return shipName;
	}
	public void setShipName(String shipName) {
		this.shipName = shipName;
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
	public String getOilType() {
		return oilType;
	}
	public void setOilType(String oilType) {
		this.oilType = oilType;
	}
	
	
}
