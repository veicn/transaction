package com.sinochem.crude.trade.info.model;

public class SubscribeInfoVO  {
	
	/*资讯uuid*/
	private String uuid;
	
	/*标题*/
	private String title;
	
	/*专栏图标*/
	private String columnCover;
	
	/*封面路径*/
	private String extend1;
	
	/*文章简介*/
	private String general;
	
	/*用户id*/
	private String subscribeUser;
	
	/**作者*/
	private String author;
	
	private String columnTitle;
	
	private String columnAuthor;
	
	private String columnId;
	
	/**收藏次数*/
	private Integer collectionCount;
	
	/**赞次数*/
	private Integer fabulousCount;
	
	/*订阅量*/
	private Integer subscribeCount;
	
	/** 发布日期 */
	private java.util.Date releaseDate;
	
	private Integer browseCount;
	
	
	public String getColumnAuthor() {
		return columnAuthor;
	}
	public void setColumnAuthor(String columnAuthor) {
		this.columnAuthor = columnAuthor;
	}
	public String getColumnId() {
		return columnId;
	}
	public void setColumnId(String columnId) {
		this.columnId = columnId;
	}
	public String getColumnTitle() {
		return columnTitle;
	}
	public void setColumnTitle(String columnTitle) {
		this.columnTitle = columnTitle;
	}
	public Integer getBrowseCount() {
		return browseCount;
	}
	public void setBrowseCount(Integer browseCount) {
		this.browseCount = browseCount;
	}
	public String getColumnCover() {
		return columnCover;
	}
	public void setColumnCover(String columnCover) {
		this.columnCover = columnCover;
	}
	public String getExtend1() {
		return extend1;
	}
	public void setExtend1(String extend1) {
		this.extend1 = extend1;
	}
	public String getGeneral() {
		return general;
	}
	public void setGeneral(String general) {
		this.general = general;
	}
	public String getSubscribeUser() {
		return subscribeUser;
	}
	public void setSubscribeUser(String subscribeUser) {
		this.subscribeUser = subscribeUser;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Integer getCollectionCount() {
		return collectionCount;
	}
	public void setCollectionCount(Integer collectionCount) {
		this.collectionCount = collectionCount;
	}
	public Integer getFabulousCount() {
		return fabulousCount;
	}
	public void setFabulousCount(Integer fabulousCount) {
		this.fabulousCount = fabulousCount;
	}
	public Integer getSubscribeCount() {
		return subscribeCount;
	}
	public void setSubscribeCount(Integer subscribeCount) {
		this.subscribeCount = subscribeCount;
	}
	public java.util.Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(java.util.Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}