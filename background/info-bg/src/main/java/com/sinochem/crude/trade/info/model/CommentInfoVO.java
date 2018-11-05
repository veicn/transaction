package com.sinochem.crude.trade.info.model;

public class CommentInfoVO  {
	
	/*资讯uuid*/
	private String uuid;
	
	/*评论uuid*/
	private String comMentUuid;
	
	/*标题*/
	private String title;
	
	/*评论内容*/
	private String commentContent;
	
	/*用户id*/
	private java.lang.Long commentUserId;
	
	/** 创建时间 */
	private java.util.Date updateDate;
	
	public String getComMentUuid() {
		return comMentUuid;
	}
	public void setComMentUuid(String comMentUuid) {
		this.comMentUuid = comMentUuid;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public java.lang.Long getCommentUserId() {
		return commentUserId;
	}
	public void setCommentUserId(java.lang.Long commentUserId) {
		this.commentUserId = commentUserId;
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
	public java.util.Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(java.util.Date updateDate) {
		this.updateDate = updateDate;
	}
	
}