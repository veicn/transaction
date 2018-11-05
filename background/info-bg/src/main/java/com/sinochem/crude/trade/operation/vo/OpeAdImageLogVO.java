package com.sinochem.crude.trade.operation.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.sinochem.crude.trade.common.SimplePageInfo;

public class OpeAdImageLogVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**主键*/
	private String adLogId;  
	
	/**广告位id*/
	private String adSetId;  
	
	/**广告图id*/
	private String adImageId;  
	
	/**市场ID*/
	private String marketId;  
	
	/**语言*/
	private String langVer;  
	
	/**删除标志*/
	private String aliveFlag;  
	
	/**访问数*/
	private Integer clickCount;  
	
	/**访问平台*/
	private String platForm;  
	
	/**访问系统*/
	private String clientOs;  
	
	/**创建人*/
	private String createUser;  
	
	/**创建时间*/
	private java.util.Date createDate;  
	
	/**更新人*/
	private String updateUser;  
	
	/**更新日期*/
	private java.util.Date updateDate;  
	
	/**扩展字段1*/
	private String extend1;  
	
	/**扩展字段2*/
	private String extend2;  
	
	/**扩展字段3*/
	private String extend3;  
	
	/**扩展字段4*/
	private String extend4;  
	
	/**扩展字段5*/
	private String extend5;  
	
	/**扩展字段6*/
	private String extend6;  
	
	/**扩展字段7*/
	private String extend7;  
	
	/**扩展字段8*/
	private String extend8;  
	
	/**扩展字段9*/
	private String extend9;  
	
	/**扩展字段10*/
	private String extend10;
	
	/**分页对象*/
	@JsonUnwrapped
	private SimplePageInfo pageInfo;

	public String getAdLogId() {
		return adLogId;
	}

	public void setAdLogId(String adLogId) {
		this.adLogId = adLogId;
	}

	public String getAdSetId() {
		return adSetId;
	}

	public void setAdSetId(String adSetId) {
		this.adSetId = adSetId;
	}

	public String getAdImageId() {
		return adImageId;
	}

	public void setAdImageId(String adImageId) {
		this.adImageId = adImageId;
	}

	public String getMarketId() {
		return marketId;
	}

	public void setMarketId(String marketId) {
		this.marketId = marketId;
	}

	public String getLangVer() {
		return langVer;
	}

	public void setLangVer(String langVer) {
		this.langVer = langVer;
	}

	public String getAliveFlag() {
		return aliveFlag;
	}

	public void setAliveFlag(String aliveFlag) {
		this.aliveFlag = aliveFlag;
	}

	public Integer getClickCount() {
		return clickCount;
	}

	public void setClickCount(Integer clickCount) {
		this.clickCount = clickCount;
	}

	public String getPlatForm() {
		return platForm;
	}

	public void setPlatForm(String platForm) {
		this.platForm = platForm;
	}

	public String getClientOs() {
		return clientOs;
	}

	public void setClientOs(String clientOs) {
		this.clientOs = clientOs;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public java.util.Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public java.util.Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(java.util.Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getExtend1() {
		return extend1;
	}

	public void setExtend1(String extend1) {
		this.extend1 = extend1;
	}

	public String getExtend2() {
		return extend2;
	}

	public void setExtend2(String extend2) {
		this.extend2 = extend2;
	}

	public String getExtend3() {
		return extend3;
	}

	public void setExtend3(String extend3) {
		this.extend3 = extend3;
	}

	public String getExtend4() {
		return extend4;
	}

	public void setExtend4(String extend4) {
		this.extend4 = extend4;
	}

	public String getExtend5() {
		return extend5;
	}

	public void setExtend5(String extend5) {
		this.extend5 = extend5;
	}

	public String getExtend6() {
		return extend6;
	}

	public void setExtend6(String extend6) {
		this.extend6 = extend6;
	}

	public String getExtend7() {
		return extend7;
	}

	public void setExtend7(String extend7) {
		this.extend7 = extend7;
	}

	public String getExtend8() {
		return extend8;
	}

	public void setExtend8(String extend8) {
		this.extend8 = extend8;
	}

	public String getExtend9() {
		return extend9;
	}

	public void setExtend9(String extend9) {
		this.extend9 = extend9;
	}

	public String getExtend10() {
		return extend10;
	}

	public void setExtend10(String extend10) {
		this.extend10 = extend10;
	}

	public SimplePageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(SimplePageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}  
	
	

}
