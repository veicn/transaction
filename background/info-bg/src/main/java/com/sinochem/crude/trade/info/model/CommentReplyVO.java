package com.sinochem.crude.trade.info.model;

import com.sinochem.crude.trade.info.domain.CommentReply;

public class CommentReplyVO extends CommentReply {

	private static final long serialVersionUID = 1L;
	
	private String infoUuid;//资讯UUID
	private String commentUserId;//评论人id
	
	public String getInfoUuid() {
		return infoUuid;
	}
	public void setInfoUuid(String infoUuid) {
		this.infoUuid = infoUuid;
	}
	public String getCommentUserId() {
		return commentUserId;
	}
	public void setCommentUserId(String commentUserId) {
		this.commentUserId = commentUserId;
	}
	
	
}