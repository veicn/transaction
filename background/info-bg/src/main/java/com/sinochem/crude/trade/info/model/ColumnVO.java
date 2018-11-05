package com.sinochem.crude.trade.info.model;

import java.util.List;

import com.sinochem.crude.trade.info.domain.Info;

public class ColumnVO  {
	
	private String uuid;
	/*专栏id*/
	private String id;
	/*资讯ID*/
	private String infoId;
	/*点赞状态*/
	private String fabStatus;
	/*收藏状态*/
	private String colStatus;
	
	/*标题*/
	private String title;
	
	/*纯文本内容*/
	private String tex;
	
	private String texHtml;
	
	/*图片url*/
	private String extend1;
	
	/*专栏id*/
	private String bizId;
	
	/*发布日期*/
	private String releaseDate;
	
	/*作者*/
	private String author;
	
	/** 专栏简介 */
	private String columnBrief;
	
	/*显示版权声明*/
	private String isShowCopyright;
	
	private String columnClassifyName;
	
	private String columnTitle;
	
	/*收藏次数*/
	private Integer collectionCount;
	
	/*赞次数*/
	private Integer fabulousCount;
	
	/*浏览次数*/
	private Integer browseCount;
	
	/** 作者名 */
	private String authorName;
	
	/** 专栏封面 */
	private String columnCover;
	
	private String subStatus;
	
	private List<Info> infoList;
	
	public String getColumnTitle() {
		return columnTitle;
	}

	public void setColumnTitle(String columnTitle) {
		this.columnTitle = columnTitle;
	}

	public String getInfoId() {
		return infoId;
	}

	public void setInfoId(String infoId) {
		this.infoId = infoId;
	}

	public String getFabStatus() {
		return fabStatus;
	}

	public void setFabStatus(String fabStatus) {
		this.fabStatus = fabStatus;
	}

	public String getColStatus() {
		return colStatus;
	}

	public void setColStatus(String colStatus) {
		this.colStatus = colStatus;
	}

	public String getColumnBrief() {
		return columnBrief;
	}

	public void setColumnBrief(String columnBrief) {
		this.columnBrief = columnBrief;
	}

	public String getSubStatus() {
		return subStatus;
	}

	public void setSubStatus(String subStatus) {
		this.subStatus = subStatus;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Info> getInfoList() {
		return infoList;
	}

	public void setInfoList(List<Info> infoList) {
		this.infoList = infoList;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getColumnCover() {
		return columnCover;
	}

	public void setColumnCover(String columnCover) {
		this.columnCover = columnCover;
	}

	public String getColumnClassifyName() {
		return columnClassifyName;
	}

	public void setColumnClassifyName(String columnClassifyName) {
		this.columnClassifyName = columnClassifyName;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getIsShowCopyright() {
		return isShowCopyright;
	}

	public void setIsShowCopyright(String isShowCopyright) {
		this.isShowCopyright = isShowCopyright;
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

	public Integer getBrowseCount() {
		return browseCount;
	}

	public void setBrowseCount(Integer browseCount) {
		this.browseCount = browseCount;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTex() {
		return tex;
	}

	public void setTex(String tex) {
		this.tex = tex;
	}

	public String getTexHtml() {
		return texHtml;
	}

	public void setTexHtml(String texHtml) {
		this.texHtml = texHtml;
	}

	public String getExtend1() {
		return extend1;
	}

	public void setExtend1(String extend1) {
		this.extend1 = extend1;
	}

	public String getBizId() {
		return bizId;
	}

	public void setBizId(String bizId) {
		this.bizId = bizId;
	}
	
}