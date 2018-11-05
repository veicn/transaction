package com.sinochem.crude.trade.operation.vo;

import java.io.Serializable;

public class OpeAdImage implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 图片描述 */
	private String imageDes;

	/** 图片id */
	private String imageId;

	/** 类型代码 */
	private String typeCode;

	/** 排序代码 */
	private String sortCode;

	/** 图片路径 */
	private String imageUrl;

	/** 页面代码（备用） */
	private String pageCode;

	/** 市场代码 */
	private String marketId;

	/** 语言 */
	private String langVer;

	/** 点击图片之后跳转路径 */
	private String gotoUrl;

	private String creater;

	private String updater;

	/** 广告位id */
	private String adSetId;

	/** 状态10:上架 20:下架 轮播图状态为null */
	private String status;

	/** 页面位置 */
	private String adPageSet;

	/** 页面号 */
	private String adPageNo;

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public String getUpdater() {
		return updater;
	}

	public void setUpdater(String updater) {
		this.updater = updater;
	}

	public String getImageDes() {
		return imageDes;
	}

	public void setImageDes(String imageDes) {
		this.imageDes = imageDes;
	}

	public String getGotoUrl() {
		return gotoUrl;
	}

	public void setGotoUrl(String gotoUrl) {
		this.gotoUrl = gotoUrl;
	}

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getSortCode() {
		return sortCode;
	}

	public void setSortCode(String sortCode) {
		this.sortCode = sortCode;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getPageCode() {
		return pageCode;
	}

	public void setPageCode(String pageCode) {
		this.pageCode = pageCode;
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

	public String getAdPageSet() {
		return adPageSet;
	}

	public void setAdPageSet(String adPageSet) {
		this.adPageSet = adPageSet;
	}

	public String getAdPageNo() {
		return adPageNo;
	}

	public void setAdPageNo(String adPageNo) {
		this.adPageNo = adPageNo;
	}

}
