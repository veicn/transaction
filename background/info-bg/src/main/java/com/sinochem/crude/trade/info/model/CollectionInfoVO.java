package com.sinochem.crude.trade.info.model;

public class CollectionInfoVO  {
	
	/*资讯uuid*/
	private String uuid;
	
	/*标题*/
	private String title;
	
	private String author;
	
	private String extend1;
	
	private Integer browseCount;
	
	private Integer collectionCount;
	
	private Integer fabulousCount;
	
	private String tex;
	
	private String columnTitle;
	
	private String columnId;
	
	private String columnAuthor;
	
	private String authorId;
	
	private String columnCover;
	
	/*用户id*/
	private java.lang.Long collectionUserId;
	
	/** 创建时间 */
	private java.util.Date createDate;
	
	public String getColumnCover() {
		return columnCover;
	}
	public void setColumnCover(String columnCover) {
		this.columnCover = columnCover;
	}
	public String getAuthorId() {
		return authorId;
	}
	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}
	public String getColumnId() {
		return columnId;
	}
	public void setColumnId(String columnId) {
		this.columnId = columnId;
	}
	public String getColumnAuthor() {
		return columnAuthor;
	}
	public void setColumnAuthor(String columnAuthor) {
		this.columnAuthor = columnAuthor;
	}
	public String getColumnTitle() {
		return columnTitle;
	}
	public void setColumnTitle(String columnTitle) {
		this.columnTitle = columnTitle;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getExtend1() {
		return extend1;
	}
	public void setExtend1(String extend1) {
		this.extend1 = extend1;
	}
	public Integer getBrowseCount() {
		return browseCount;
	}
	public void setBrowseCount(Integer browseCount) {
		this.browseCount = browseCount;
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
	public String getTex() {
		return tex;
	}
	public void setTex(String tex) {
		this.tex = tex;
	}
	public java.lang.Long getCollectionUserId() {
		return collectionUserId;
	}
	public void setCollectionUserId(java.lang.Long collectionUserId) {
		this.collectionUserId = collectionUserId;
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
	public java.util.Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}
	
}