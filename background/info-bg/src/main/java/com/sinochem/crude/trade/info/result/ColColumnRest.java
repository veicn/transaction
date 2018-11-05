package com.sinochem.crude.trade.info.result;

import java.io.Serializable;

public class ColColumnRest implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	private String columnTitle;//专栏标题
	private String columnClassifyName;//专栏名称
	private String authorName;//专栏作者
	private String columnBrief;//专栏简介
	private String status;//状态
	private String id;
	private String examineNoDesc;
	private Integer articleCount;//文章数
	private Integer subscribeCount;//订阅数
	private String columnClassify;//专栏分类: 行业
	private String columnCover;//专栏封面
	
	
	public String getExamineNoDesc() {
		return examineNoDesc;
	}
	public void setExamineNoDesc(String examineNoDesc) {
		this.examineNoDesc = examineNoDesc;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getColumnTitle() {
		return columnTitle;
	}
	public void setColumnTitle(String columnTitle) {
		this.columnTitle = columnTitle;
	}
	public String getColumnClassifyName() {
		return columnClassifyName;
	}
	public void setColumnClassifyName(String columnClassifyName) {
		this.columnClassifyName = columnClassifyName;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public String getColumnBrief() {
		return columnBrief;
	}
	public void setColumnBrief(String columnBrief) {
		this.columnBrief = columnBrief;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Integer getArticleCount() {
		return articleCount;
	}
	public void setArticleCount(Integer articleCount) {
		this.articleCount = articleCount;
	}
	public Integer getSubscribeCount() {
		return subscribeCount;
	}
	public void setSubscribeCount(Integer subscribeCount) {
		this.subscribeCount = subscribeCount;
	}
	public String getColumnClassify() {
		return columnClassify;
	}
	public void setColumnClassify(String columnClassify) {
		this.columnClassify = columnClassify;
	}
	public String getColumnCover() {
		return columnCover;
	}
	public void setColumnCover(String columnCover) {
		this.columnCover = columnCover;
	}
	
}
