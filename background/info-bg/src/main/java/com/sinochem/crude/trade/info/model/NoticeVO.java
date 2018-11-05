package com.sinochem.crude.trade.info.model;

public class NoticeVO {

	private String title;
	private String uuid;
	private String content;
	private String contentWithHtml;
	private String validBegin; //有效日期起
	private String validEnd; //有效日期止
	private String state;
	private String releaseId; //发布人ID
	private String releaseDate; //发布日期
	private String upfile;
	
	
	
	public String getValidBegin() {
		return validBegin;
	}
	public void setValidBegin(String validBegin) {
		this.validBegin = validBegin;
	}
	public String getValidEnd() {
		return validEnd;
	}
	public void setValidEnd(String validEnd) {
		this.validEnd = validEnd;
	}
	public String getUpfile() {
		return upfile;
	}
	public void setUpfile(String upfile) {
		this.upfile = upfile;
	}
	public String getReleaseId() {
		return releaseId;
	}
	public void setReleaseId(String releaseId) {
		this.releaseId = releaseId;
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getContentWithHtml() {
		return contentWithHtml;
	}
	public void setContentWithHtml(String contentWithHtml) {
		this.contentWithHtml = contentWithHtml;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
