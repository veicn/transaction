package com.sinochem.crude.trade.operation.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class OpeAdImageSet implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 主键 */
	private String adSetId;

	/** 系统类型 */
	private String adSysType;

	/** 系统页面号 */
	private String adPageNo;

	/** 广告页面位置 */
	private String adPageSet;

	/** 广告位描述 */
	private String adSetDesc;

	/** 广告图片描述 */
	private String adImageDesc;

	/** 默认广告图片 */
	private String defaultImage;

	/** 默认广告图片链接 */
	private String defaultLinkUrl;

	/** 市场ID */
	private String marketId;

	/** 语言 */
	private String langVer;

	/** 删除标志 */
	private String aliveFlag;

	/** 创建人 */
	private String createUser;

	/** 创建时间 */
	private java.util.Date createDate;

	/** 更新人 */
	private String updateUser;

	/** 更新日期 */
	private java.util.Date updateDate;

	/** 扩展字段1 */
	private String extend1;

	/** 扩展字段2 */
	private String extend2;

	/** 扩展字段3 */
	private String extend3;

	/** 扩展字段4 */
	private String extend4;

	/** 扩展字段5 */
	private String extend5;

	/** 扩展字段6 */
	private String extend6;

	/** 扩展字段7 */
	private String extend7;

	/** 扩展字段8 */
	private String extend8;

	/** 扩展字段9 */
	private String extend9;

	/** 扩展字段10 */
	private String extend10;

	/** 主键 */
	public void setAdSetId(String adSetId) {
		this.adSetId = adSetId;
	}

	/** 主键 */
	public String getAdSetId() {
		return this.adSetId;
	}

	/** 系统类型 */
	public void setAdSysType(String adSysType) {
		this.adSysType = adSysType;
	}

	/** 系统类型 */
	public String getAdSysType() {
		return this.adSysType;
	}

	/** 系统页面号 */
	public void setAdPageNo(String adPageNo) {
		this.adPageNo = adPageNo;
	}

	/** 系统页面号 */
	public String getAdPageNo() {
		return this.adPageNo;
	}

	/** 广告页面位置 */
	public void setAdPageSet(String adPageSet) {
		this.adPageSet = adPageSet;
	}

	/** 广告页面位置 */
	public String getAdPageSet() {
		return this.adPageSet;
	}

	/** 广告位描述 */
	public void setAdSetDesc(String adSetDesc) {
		this.adSetDesc = adSetDesc;
	}

	/** 广告位描述 */
	public String getAdSetDesc() {
		return this.adSetDesc;
	}

	/** 广告图片描述 */
	public void setAdImageDesc(String adImageDesc) {
		this.adImageDesc = adImageDesc;
	}

	/** 广告图片描述 */
	public String getAdImageDesc() {
		return this.adImageDesc;
	}

	/** 默认广告图片 */
	public void setDefaultImage(String defaultImage) {
		this.defaultImage = defaultImage;
	}

	/** 默认广告图片 */
	public String getDefaultImage() {
		return this.defaultImage;
	}

	/** 默认广告图片链接 */
	public void setDefaultLinkUrl(String defaultLinkUrl) {
		this.defaultLinkUrl = defaultLinkUrl;
	}

	/** 默认广告图片链接 */
	public String getDefaultLinkUrl() {
		return this.defaultLinkUrl;
	}

	/** 市场ID */
	public void setMarketId(String marketId) {
		this.marketId = marketId;
	}

	/** 市场ID */
	public String getMarketId() {
		return this.marketId;
	}

	/** 语言 */
	public void setLangVer(String langVer) {
		this.langVer = langVer;
	}

	/** 语言 */
	public String getLangVer() {
		return this.langVer;
	}

	/** 删除标志 */
	public void setAliveFlag(String aliveFlag) {
		this.aliveFlag = aliveFlag;
	}

	/** 删除标志 */
	public String getAliveFlag() {
		return this.aliveFlag;
	}

	/** 创建人 */
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	/** 创建人 */
	public String getCreateUser() {
		return this.createUser;
	}

	/** 创建时间 */
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}

	/** 创建时间 */
	public java.util.Date getCreateDate() {
		return this.createDate;
	}

	/** 更新人 */
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	/** 更新人 */
	public String getUpdateUser() {
		return this.updateUser;
	}

	/** 更新日期 */
	public void setUpdateDate(java.util.Date updateDate) {
		this.updateDate = updateDate;
	}

	/** 更新日期 */
	public java.util.Date getUpdateDate() {
		return this.updateDate;
	}

	/** 扩展字段1 */
	public void setExtend1(String extend1) {
		this.extend1 = extend1;
	}

	/** 扩展字段1 */
	public String getExtend1() {
		return this.extend1;
	}

	/** 扩展字段2 */
	public void setExtend2(String extend2) {
		this.extend2 = extend2;
	}

	/** 扩展字段2 */
	public String getExtend2() {
		return this.extend2;
	}

	/** 扩展字段3 */
	public void setExtend3(String extend3) {
		this.extend3 = extend3;
	}

	/** 扩展字段3 */
	public String getExtend3() {
		return this.extend3;
	}

	/** 扩展字段4 */
	public void setExtend4(String extend4) {
		this.extend4 = extend4;
	}

	/** 扩展字段4 */
	public String getExtend4() {
		return this.extend4;
	}

	/** 扩展字段5 */
	public void setExtend5(String extend5) {
		this.extend5 = extend5;
	}

	/** 扩展字段5 */
	public String getExtend5() {
		return this.extend5;
	}

	/** 扩展字段6 */
	public void setExtend6(String extend6) {
		this.extend6 = extend6;
	}

	/** 扩展字段6 */
	public String getExtend6() {
		return this.extend6;
	}

	/** 扩展字段7 */
	public void setExtend7(String extend7) {
		this.extend7 = extend7;
	}

	/** 扩展字段7 */
	public String getExtend7() {
		return this.extend7;
	}

	/** 扩展字段8 */
	public void setExtend8(String extend8) {
		this.extend8 = extend8;
	}

	/** 扩展字段8 */
	public String getExtend8() {
		return this.extend8;
	}

	/** 扩展字段9 */
	public void setExtend9(String extend9) {
		this.extend9 = extend9;
	}

	/** 扩展字段9 */
	public String getExtend9() {
		return this.extend9;
	}

	/** 扩展字段10 */
	public void setExtend10(String extend10) {
		this.extend10 = extend10;
	}

	/** 扩展字段10 */
	public String getExtend10() {
		return this.extend10;
	}

	public Map<String, Object> toMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("adSetId", this.adSetId);
		map.put("adSysType", this.adSysType);
		map.put("adPageNo", this.adPageNo);
		map.put("adPageSet", this.adPageSet);
		map.put("adSetDesc", this.adSetDesc);
		map.put("adImageDesc", this.adImageDesc);
		map.put("defaultImage", this.defaultImage);
		map.put("defaultLinkUrl", this.defaultLinkUrl);
		map.put("marketId", this.marketId);
		map.put("langVer", this.langVer);
		map.put("aliveFlag", this.aliveFlag);
		map.put("createUser", this.createUser);
		map.put("createDate", this.createDate);
		map.put("updateUser", this.updateUser);
		map.put("updateDate", this.updateDate);
		map.put("extend1", this.extend1);
		map.put("extend2", this.extend2);
		map.put("extend3", this.extend3);
		map.put("extend4", this.extend4);
		map.put("extend5", this.extend5);
		map.put("extend6", this.extend6);
		map.put("extend7", this.extend7);
		map.put("extend8", this.extend8);
		map.put("extend9", this.extend9);
		map.put("extend10", this.extend10);
		return map;
	}
}