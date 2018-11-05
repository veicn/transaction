package com.sinochem.crude.trade.info.model;

import java.util.List;

import com.sinochem.crude.trade.info.domain.InfoAttachment;

public class InfoVO  {
	
	private String uuid;
	private String channelUUId;
	private String channelName;
	private String channelMUUId; //主频道uuid
	private String title;
	private String displayMode; //10单图,20三图 , 30自动,40广告
	private List<InfoAttachment> attachments; //附件
	private String validBegin; //有效日期
	private String validEnd;//结束时间
	private String tagList;
	private String content;
	private String contentWithHtml;
	private String isShowCopyright; //是否显示版权
	private String isShowDisclaimer; //是否免责
	private String isShowGeneral; //是否显示导语
	private String general; //导语
	private String isShowOrigin;//是否显示来源
	private String origin; //来源
	private String probationFlag;// 1-试读 0-其他
	private String articleType; //文章类型 20
	private String file;
	private String status;
	private String releaseDate; //发布时间
	/**发布日期*/
	private java.util.Date releaseDate1; 
	private String releaseId; //发布人
	private String upfile;
	private String resufeRemark; //撤销原因
	private String stickEdntime; //置顶有效期
	private String stick; // 是否置顶
	
	
	public String getStickEdntime() {
		return stickEdntime;
	}
	public void setStickEdntime(String stickEdntime) {
		this.stickEdntime = stickEdntime;
	}
	public String getStick() {
		return stick;
	}
	public void setStick(String stick) {
		this.stick = stick;
	}
	public String getChannelMUUId() {
		return channelMUUId;
	}
	public void setChannelMUUId(String channelMUUId) {
		this.channelMUUId = channelMUUId;
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	public java.util.Date getReleaseDate1() {
		return releaseDate1;
	}
	public void setReleaseDate1(java.util.Date releaseDate1) {
		this.releaseDate1 = releaseDate1;
	}
	public String getReleaseId() {
		return releaseId;
	}
	public void setReleaseId(String releaseId) {
		this.releaseId = releaseId;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public String getValidEnd() {
		return validEnd;
	}
	public void setValidEnd(String validEnd) {
		this.validEnd = validEnd;
	}
	public String getValidBegin() {
		return validBegin;
	}
	public void setValidBegin(String validBegin) {
		this.validBegin = validBegin;
	}
	public String getProbationFlag() {
		return probationFlag;
	}
	public void setProbationFlag(String probationFlag) {
		this.probationFlag = probationFlag;
	}
	public String getArticleType() {
		return articleType;
	}
	public void setArticleType(String articleType) {
		this.articleType = articleType;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getGeneral() {
		return general;
	}
	public void setGeneral(String general) {
		this.general = general;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getIsShowGeneral() {
		return isShowGeneral;
	}
	public void setIsShowGeneral(String isShowGeneral) {
		this.isShowGeneral = isShowGeneral;
	}
	public String getIsShowOrigin() {
		return isShowOrigin;
	}
	public void setIsShowOrigin(String isShowOrigin) {
		this.isShowOrigin = isShowOrigin;
	}
	public String getIsShowCopyright() {
		return isShowCopyright;
	}
	public void setIsShowCopyright(String isShowCopyright) {
		this.isShowCopyright = isShowCopyright;
	}
	public String getIsShowDisclaimer() {
		return isShowDisclaimer;
	}
	public void setIsShowDisclaimer(String isShowDisclaimer) {
		this.isShowDisclaimer = isShowDisclaimer;
	}
	public String getChannelUUId() {
		return channelUUId;
	}
	public void setChannelUUId(String channelUUId) {
		this.channelUUId = channelUUId;
	}
	public String getTitle() {
		return title;
	}
	public String getUpfile() {
		return upfile;
	}
	public void setUpfile(String upfile) {
		this.upfile = upfile;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDisplayMode() {
		return displayMode;
	}
	public void setDisplayMode(String displayMode) {
		this.displayMode = displayMode;
	}
	public List<InfoAttachment> getAttachments() {
		return attachments;
	}
	public void setAttachments(List<InfoAttachment> attachments) {
		this.attachments = attachments;
	}
	public String getTagList() {
		return tagList;
	}
	public void setTagList(String tagList) {
		this.tagList = tagList;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getContentWithHtml() {
		return contentWithHtml;
	}
	public void setContentWithHtml(String contentWithHtml) {
		this.contentWithHtml = contentWithHtml;
	}
	public String getResufeRemark() {
		return resufeRemark;
	}
	public void setResufeRemark(String resufeRemark) {
		this.resufeRemark = resufeRemark;
	}
	
}