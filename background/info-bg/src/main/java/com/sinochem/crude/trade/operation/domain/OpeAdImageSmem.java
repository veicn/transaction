package com.sinochem.crude.trade.operation.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class OpeAdImageSmem implements Serializable {

	private static final long serialVersionUID = 1L;

	private String imageId;

	private String typeCode;

	private String sortCode;

	private String imageUrl;

	private String gotoUrl;

	private String imageDes;

	private String pageCode;

	private String marketId;

	private String langVer;

	private String aliveFlag;

	private String createUser;

	private java.util.Date createDate;

	private String updateUser;

	private java.util.Date updateDate;

	/** 广告位id */
	private String adSetId;

	/** 状态10:上架 20:下架 */
	private String status;

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public String getImageId() {
		return this.imageId;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getTypeCode() {
		return this.typeCode;
	}

	public void setSortCode(String sortCode) {
		this.sortCode = sortCode;
	}

	public String getSortCode() {
		return this.sortCode;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getImageUrl() {
		return this.imageUrl;
	}

	public void setGotoUrl(String gotoUrl) {
		this.gotoUrl = gotoUrl;
	}

	public String getGotoUrl() {
		return this.gotoUrl;
	}

	public void setImageDes(String imageDes) {
		this.imageDes = imageDes;
	}

	public String getImageDes() {
		return this.imageDes;
	}

	public void setPageCode(String pageCode) {
		this.pageCode = pageCode;
	}

	public String getPageCode() {
		return this.pageCode;
	}

	public void setMarketId(String marketId) {
		this.marketId = marketId;
	}

	public String getMarketId() {
		return this.marketId;
	}

	public void setLangVer(String langVer) {
		this.langVer = langVer;
	}

	public String getLangVer() {
		return this.langVer;
	}

	public void setAliveFlag(String aliveFlag) {
		this.aliveFlag = aliveFlag;
	}

	public String getAliveFlag() {
		return this.aliveFlag;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getCreateUser() {
		return this.createUser;
	}

	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}

	public java.util.Date getCreateDate() {
		return this.createDate;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public String getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateDate(java.util.Date updateDate) {
		this.updateDate = updateDate;
	}

	public java.util.Date getUpdateDate() {
		return this.updateDate;
	}

	public String getAdSetId() {
		return adSetId;
	}

	public void setAdSetId(String adSetId) {
		this.adSetId = adSetId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Map<String, Object> toMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("imageId", this.imageId);
		map.put("typeCode", this.typeCode);
		map.put("sortCode", this.sortCode);
		map.put("imageUrl", this.imageUrl);
		map.put("gotoUrl", this.gotoUrl);
		map.put("imageDes", this.imageDes);
		map.put("pageCode", this.pageCode);
		map.put("marketId", this.marketId);
		map.put("langVer", this.langVer);
		map.put("aliveFlag", this.aliveFlag);
		map.put("createUser", this.createUser);
		map.put("createDate", this.createDate);
		map.put("updateUser", this.updateUser);
		map.put("updateDate", this.updateDate);
		map.put("adSetId", this.adSetId);
		map.put("status", this.status);
		return map;
	}
}