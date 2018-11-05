package com.sinochem.crude.trade.info.domain;

import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class Info implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**自增主键*/
	private Long id;  
	
	/**32位唯一码*/
	private String uuid;  
	
	/**抓取资讯id或外部系统的资讯id*/
	private String infoGrabId;  
	
	/**频道ID*/
	private Long channelId;  
	
	/**标题*/
	private String title;  
	
	/**短标题*/
	private String shortTitle;  
	
	/**概要*/
	private String general;  
	
	/**来源*/
	private String origin;  
	
	/**来源链接*/
	private String originUrl;  
	
	/**作者*/
	private String author;  
	
	/**资讯类型 10:普通 20:精选*/
	private String informationType;  
	
	/**精选结束时间*/
	private java.util.Date selectedEndtime;  
	
	/**置顶标志(1:置顶，0：不置顶)*/
	private String stick;  
	
	/**置顶有效期*/
	private java.util.Date stickEdntime;  
	
	/**是否有标题图*/
	private String hasTitleImg;  
	
	/**是否推荐*/
	private String recommendFlag;  
	
	/**状态 00- 已撤销 10-待提交 20-待审核 29-审核驳回 30-已发布*/
	private String status;  
	
	/**浏览次数*/
	private Integer browseCount;  
	
	/**评论次数*/
	private Integer commentCount;  
	
	/**分享次数*/
	private Integer shareCount;  
	
	/**收藏次数*/
	private Integer collectionCount;  
	
	/**赞次数*/
	private Integer fabulousCount;  
	
	/**踩次数*/
	private Integer trampleCount;  
	
	/**描述*/
	private String description;  
	
	/**有效期起*/
	private java.util.Date validBegin;  
	
	/**有效期止*/
	private java.util.Date validEnd;  
	
	/**视频路径*/
	private String voidPath;  
	
	/**封面显示模式:10单图模式,20三图模式,30随机模式*/
	private String displayMode;  
	
	/**业务主键 (专栏或专题主键）*/
	private String bizId;  
	
	/**文章类型 10:专栏文章 20:普通文章 30:专题文章*/
	private String articleType;  
	
	/**试读标记 1:试读 0:其他*/
	private String probationFlag;  
	
	/**显示导语 1:显示 0:不显示*/
	private String isShowGeneral;  
	
	/**显示来源 1:显示 0:不显示*/
	private String isShowOrigin;  
	
	/**显示版权声明 1:显示 0:不显示*/
	private String isShowCopyright;  
	
	/**显示免责声明 1:显示 0:不显示*/
	private String isShowDisclaimer;  
	
	/**显示作者 1:显示 0:不显示*/
	private String isShowAuthor;  
	
	/**发布人ID*/
	private String releaseId;  
	
	/**发布日期*/
	private java.util.Date releaseDate;  
	
	/**审核人id*/
	private String reviewerId;  
	
	/**审核人*/
	private String reviewer;  
	
	/**审核时间*/
	private java.util.Date auditTime;  
	
	/**撤销人id*/
	private String revokeId;  
	
	/**撤销人*/
	private String revoker;  
	
	/**撤销时间*/
	private java.util.Date revokeTime;  
	
	/**驳回原因*/
	private String resufeRemark;  
	
	/**标签名称，多个用逗号拼接，用于openSearch查询用*/
	private String tagList;  
	
	/**创建者*/
	private String createUser;  
	
	/**创建时间*/
	private java.util.Date createDate;  
	
	/**修改者*/
	private String updateUser;  
	
	/**修改时间*/
	private java.util.Date updateDate;  
	
	/**数据状态*/
	private String aliveFlag;  
	
	/**扩展字段1*/
	private String extend1;  
	
	/**jkx抓取资讯type CRU1日报 CRUWW周报 CRUM月报2*/
	private String extend2;  
	
	/**抓取频率*/
	private String extend3;  
	
	/**扩展字段4*/
	private String extend4;  
	
	/**扩展字段5*/
	private String extend5;  
	
	/**扩展字段6*/
	private String extend6;  
	
	/**扩展字段7*/
	private String extend7;  
	
	/**扩展字段8*/
	private String extend8;  
	
	/**扩展字段9*/
	private String extend9;  
	
	/**扩展字段10*/
	private String extend10;  
	
		/**自增主键*/
	public void setId(Long id){
		this.id=id;
	}
	/**自增主键*/
	public Long getId(){
		return this.id;
	}
	
	/**32位唯一码*/
	public void setUuid(String uuid){
		this.uuid=uuid;
	}
	/**32位唯一码*/
	public String getUuid(){
		return this.uuid;
	}
	
	/**抓取资讯id或外部系统的资讯id*/
	public void setInfoGrabId(String infoGrabId){
		this.infoGrabId=infoGrabId;
	}
	/**抓取资讯id或外部系统的资讯id*/
	public String getInfoGrabId(){
		return this.infoGrabId;
	}
	
	/**频道ID*/
	public void setChannelId(Long channelId){
		this.channelId=channelId;
	}
	/**频道ID*/
	public Long getChannelId(){
		return this.channelId;
	}
	
	/**标题*/
	public void setTitle(String title){
		this.title=title;
	}
	/**标题*/
	public String getTitle(){
		return this.title;
	}
	
	/**短标题*/
	public void setShortTitle(String shortTitle){
		this.shortTitle=shortTitle;
	}
	/**短标题*/
	public String getShortTitle(){
		return this.shortTitle;
	}
	
	/**概要*/
	public void setGeneral(String general){
		this.general=general;
	}
	/**概要*/
	public String getGeneral(){
		return this.general;
	}
	
	/**来源*/
	public void setOrigin(String origin){
		this.origin=origin;
	}
	/**来源*/
	public String getOrigin(){
		return this.origin;
	}
	
	/**来源链接*/
	public void setOriginUrl(String originUrl){
		this.originUrl=originUrl;
	}
	/**来源链接*/
	public String getOriginUrl(){
		return this.originUrl;
	}
	
	/**作者*/
	public void setAuthor(String author){
		this.author=author;
	}
	/**作者*/
	public String getAuthor(){
		return this.author;
	}
	
	/**资讯类型 10:普通 20:精选*/
	public void setInformationType(String informationType){
		this.informationType=informationType;
	}
	/**资讯类型 10:普通 20:精选*/
	public String getInformationType(){
		return this.informationType;
	}
	
	/**精选结束时间*/
	public void setSelectedEndtime(java.util.Date selectedEndtime){
		this.selectedEndtime=selectedEndtime;
	}
	/**精选结束时间*/
	public java.util.Date getSelectedEndtime(){
		return this.selectedEndtime;
	}
	
	/**置顶标志(1:置顶，0：不置顶)*/
	public void setStick(String stick){
		this.stick=stick;
	}
	/**置顶标志(1:置顶，0：不置顶)*/
	public String getStick(){
		return this.stick;
	}
	
	/**置顶有效期*/
	public void setStickEdntime(java.util.Date stickEdntime){
		this.stickEdntime=stickEdntime;
	}
	/**置顶有效期*/
	public java.util.Date getStickEdntime(){
		return this.stickEdntime;
	}
	
	/**是否有标题图*/
	public void setHasTitleImg(String hasTitleImg){
		this.hasTitleImg=hasTitleImg;
	}
	/**是否有标题图*/
	public String getHasTitleImg(){
		return this.hasTitleImg;
	}
	
	/**是否推荐*/
	public void setRecommendFlag(String recommendFlag){
		this.recommendFlag=recommendFlag;
	}
	/**是否推荐*/
	public String getRecommendFlag(){
		return this.recommendFlag;
	}
	
	/**状态 00- 已撤销 10-待提交 20-待审核 29-审核驳回 30-已发布*/
	public void setStatus(String status){
		this.status=status;
	}
	/**状态 00- 已撤销 10-待提交 20-待审核 29-审核驳回 30-已发布*/
	public String getStatus(){
		return this.status;
	}
	
	/**浏览次数*/
	public void setBrowseCount(Integer browseCount){
		this.browseCount=browseCount;
	}
	/**浏览次数*/
	public Integer getBrowseCount(){
		return this.browseCount;
	}
	
	/**评论次数*/
	public void setCommentCount(Integer commentCount){
		this.commentCount=commentCount;
	}
	/**评论次数*/
	public Integer getCommentCount(){
		return this.commentCount;
	}
	
	/**分享次数*/
	public void setShareCount(Integer shareCount){
		this.shareCount=shareCount;
	}
	/**分享次数*/
	public Integer getShareCount(){
		return this.shareCount;
	}
	
	/**收藏次数*/
	public void setCollectionCount(Integer collectionCount){
		this.collectionCount=collectionCount;
	}
	/**收藏次数*/
	public Integer getCollectionCount(){
		return this.collectionCount;
	}
	
	/**赞次数*/
	public void setFabulousCount(Integer fabulousCount){
		this.fabulousCount=fabulousCount;
	}
	/**赞次数*/
	public Integer getFabulousCount(){
		return this.fabulousCount;
	}
	
	/**踩次数*/
	public void setTrampleCount(Integer trampleCount){
		this.trampleCount=trampleCount;
	}
	/**踩次数*/
	public Integer getTrampleCount(){
		return this.trampleCount;
	}
	
	/**描述*/
	public void setDescription(String description){
		this.description=description;
	}
	/**描述*/
	public String getDescription(){
		return this.description;
	}
	
	/**有效期起*/
	public void setValidBegin(java.util.Date validBegin){
		this.validBegin=validBegin;
	}
	/**有效期起*/
	public java.util.Date getValidBegin(){
		return this.validBegin;
	}
	
	/**有效期止*/
	public void setValidEnd(java.util.Date validEnd){
		this.validEnd=validEnd;
	}
	/**有效期止*/
	public java.util.Date getValidEnd(){
		return this.validEnd;
	}
	
	/**视频路径*/
	public void setVoidPath(String voidPath){
		this.voidPath=voidPath;
	}
	/**视频路径*/
	public String getVoidPath(){
		return this.voidPath;
	}
	
	/**封面显示模式:10单图模式,20三图模式,30随机模式*/
	public void setDisplayMode(String displayMode){
		this.displayMode=displayMode;
	}
	/**封面显示模式:10单图模式,20三图模式,30随机模式*/
	public String getDisplayMode(){
		return this.displayMode;
	}
	
	/**业务主键 (专栏或专题主键）*/
	public void setBizId(String bizId){
		this.bizId=bizId;
	}
	/**业务主键 (专栏或专题主键）*/
	public String getBizId(){
		return this.bizId;
	}
	
	/**文章类型 10:专栏文章 20:普通文章 30:专题文章*/
	public void setArticleType(String articleType){
		this.articleType=articleType;
	}
	/**文章类型 10:专栏文章 20:普通文章 30:专题文章*/
	public String getArticleType(){
		return this.articleType;
	}
	
	/**试读标记 1:试读 0:其他*/
	public void setProbationFlag(String probationFlag){
		this.probationFlag=probationFlag;
	}
	/**试读标记 1:试读 0:其他*/
	public String getProbationFlag(){
		return this.probationFlag;
	}
	
	/**显示导语 1:显示 0:不显示*/
	public void setIsShowGeneral(String isShowGeneral){
		this.isShowGeneral=isShowGeneral;
	}
	/**显示导语 1:显示 0:不显示*/
	public String getIsShowGeneral(){
		return this.isShowGeneral;
	}
	
	/**显示来源 1:显示 0:不显示*/
	public void setIsShowOrigin(String isShowOrigin){
		this.isShowOrigin=isShowOrigin;
	}
	/**显示来源 1:显示 0:不显示*/
	public String getIsShowOrigin(){
		return this.isShowOrigin;
	}
	
	/**显示版权声明 1:显示 0:不显示*/
	public void setIsShowCopyright(String isShowCopyright){
		this.isShowCopyright=isShowCopyright;
	}
	/**显示版权声明 1:显示 0:不显示*/
	public String getIsShowCopyright(){
		return this.isShowCopyright;
	}
	
	/**显示免责声明 1:显示 0:不显示*/
	public void setIsShowDisclaimer(String isShowDisclaimer){
		this.isShowDisclaimer=isShowDisclaimer;
	}
	/**显示免责声明 1:显示 0:不显示*/
	public String getIsShowDisclaimer(){
		return this.isShowDisclaimer;
	}
	
	/**显示作者 1:显示 0:不显示*/
	public void setIsShowAuthor(String isShowAuthor){
		this.isShowAuthor=isShowAuthor;
	}
	/**显示作者 1:显示 0:不显示*/
	public String getIsShowAuthor(){
		return this.isShowAuthor;
	}
	
	/**发布人ID*/
	public void setReleaseId(String releaseId){
		this.releaseId=releaseId;
	}
	/**发布人ID*/
	public String getReleaseId(){
		return this.releaseId;
	}
	
	/**发布日期*/
	public void setReleaseDate(java.util.Date releaseDate){
		this.releaseDate=releaseDate;
	}
	/**发布日期*/
	public java.util.Date getReleaseDate(){
		return this.releaseDate;
	}
	
	/**审核人id*/
	public void setReviewerId(String reviewerId){
		this.reviewerId=reviewerId;
	}
	/**审核人id*/
	public String getReviewerId(){
		return this.reviewerId;
	}
	
	/**审核人*/
	public void setReviewer(String reviewer){
		this.reviewer=reviewer;
	}
	/**审核人*/
	public String getReviewer(){
		return this.reviewer;
	}
	
	/**审核时间*/
	public void setAuditTime(java.util.Date auditTime){
		this.auditTime=auditTime;
	}
	/**审核时间*/
	public java.util.Date getAuditTime(){
		return this.auditTime;
	}
	
	/**撤销人id*/
	public void setRevokeId(String revokeId){
		this.revokeId=revokeId;
	}
	/**撤销人id*/
	public String getRevokeId(){
		return this.revokeId;
	}
	
	/**撤销人*/
	public void setRevoker(String revoker){
		this.revoker=revoker;
	}
	/**撤销人*/
	public String getRevoker(){
		return this.revoker;
	}
	
	/**撤销时间*/
	public void setRevokeTime(java.util.Date revokeTime){
		this.revokeTime=revokeTime;
	}
	/**撤销时间*/
	public java.util.Date getRevokeTime(){
		return this.revokeTime;
	}
	
	/**驳回原因*/
	public void setResufeRemark(String resufeRemark){
		this.resufeRemark=resufeRemark;
	}
	/**驳回原因*/
	public String getResufeRemark(){
		return this.resufeRemark;
	}
	
	/**标签名称，多个用逗号拼接，用于openSearch查询用*/
	public void setTagList(String tagList){
		this.tagList=tagList;
	}
	/**标签名称，多个用逗号拼接，用于openSearch查询用*/
	public String getTagList(){
		return this.tagList;
	}
	
	/**创建者*/
	public void setCreateUser(String createUser){
		this.createUser=createUser;
	}
	/**创建者*/
	public String getCreateUser(){
		return this.createUser;
	}
	
	/**创建时间*/
	public void setCreateDate(java.util.Date createDate){
		this.createDate=createDate;
	}
	/**创建时间*/
	public java.util.Date getCreateDate(){
		return this.createDate;
	}
	
	/**修改者*/
	public void setUpdateUser(String updateUser){
		this.updateUser=updateUser;
	}
	/**修改者*/
	public String getUpdateUser(){
		return this.updateUser;
	}
	
	/**修改时间*/
	public void setUpdateDate(java.util.Date updateDate){
		this.updateDate=updateDate;
	}
	/**修改时间*/
	public java.util.Date getUpdateDate(){
		return this.updateDate;
	}
	
	/**数据状态*/
	public void setAliveFlag(String aliveFlag){
		this.aliveFlag=aliveFlag;
	}
	/**数据状态*/
	public String getAliveFlag(){
		return this.aliveFlag;
	}
	
	/**扩展字段1*/
	public void setExtend1(String extend1){
		this.extend1=extend1;
	}
	/**扩展字段1*/
	public String getExtend1(){
		return this.extend1;
	}
	
	/**jkx抓取资讯type CRU1日报 CRUWW周报 CRUM月报2*/
	public void setExtend2(String extend2){
		this.extend2=extend2;
	}
	/**jkx抓取资讯type CRU1日报 CRUWW周报 CRUM月报2*/
	public String getExtend2(){
		return this.extend2;
	}
	
	/**抓取频率*/
	public void setExtend3(String extend3){
		this.extend3=extend3;
	}
	/**抓取频率*/
	public String getExtend3(){
		return this.extend3;
	}
	
	/**扩展字段4*/
	public void setExtend4(String extend4){
		this.extend4=extend4;
	}
	/**扩展字段4*/
	public String getExtend4(){
		return this.extend4;
	}
	
	/**扩展字段5*/
	public void setExtend5(String extend5){
		this.extend5=extend5;
	}
	/**扩展字段5*/
	public String getExtend5(){
		return this.extend5;
	}
	
	/**扩展字段6*/
	public void setExtend6(String extend6){
		this.extend6=extend6;
	}
	/**扩展字段6*/
	public String getExtend6(){
		return this.extend6;
	}
	
	/**扩展字段7*/
	public void setExtend7(String extend7){
		this.extend7=extend7;
	}
	/**扩展字段7*/
	public String getExtend7(){
		return this.extend7;
	}
	
	/**扩展字段8*/
	public void setExtend8(String extend8){
		this.extend8=extend8;
	}
	/**扩展字段8*/
	public String getExtend8(){
		return this.extend8;
	}
	
	/**扩展字段9*/
	public void setExtend9(String extend9){
		this.extend9=extend9;
	}
	/**扩展字段9*/
	public String getExtend9(){
		return this.extend9;
	}
	
	/**扩展字段10*/
	public void setExtend10(String extend10){
		this.extend10=extend10;
	}
	/**扩展字段10*/
	public String getExtend10(){
		return this.extend10;
	}
	
		
	
	public Map<String, Object> toMap() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id",this.id);
	map.put("uuid",this.uuid);
	map.put("infoGrabId",this.infoGrabId);
	map.put("channelId",this.channelId);
	map.put("title",this.title);
	map.put("shortTitle",this.shortTitle);
	map.put("general",this.general);
	map.put("origin",this.origin);
	map.put("originUrl",this.originUrl);
	map.put("author",this.author);
	map.put("informationType",this.informationType);
	map.put("selectedEndtime",this.selectedEndtime);
	map.put("stick",this.stick);
	map.put("stickEdntime",this.stickEdntime);
	map.put("hasTitleImg",this.hasTitleImg);
	map.put("recommendFlag",this.recommendFlag);
	map.put("status",this.status);
	map.put("browseCount",this.browseCount);
	map.put("commentCount",this.commentCount);
	map.put("shareCount",this.shareCount);
	map.put("collectionCount",this.collectionCount);
	map.put("fabulousCount",this.fabulousCount);
	map.put("trampleCount",this.trampleCount);
	map.put("description",this.description);
	map.put("validBegin",this.validBegin);
	map.put("validEnd",this.validEnd);
	map.put("voidPath",this.voidPath);
	map.put("displayMode",this.displayMode);
	map.put("bizId",this.bizId);
	map.put("articleType",this.articleType);
	map.put("probationFlag",this.probationFlag);
	map.put("isShowGeneral",this.isShowGeneral);
	map.put("isShowOrigin",this.isShowOrigin);
	map.put("isShowCopyright",this.isShowCopyright);
	map.put("isShowDisclaimer",this.isShowDisclaimer);
	map.put("isShowAuthor",this.isShowAuthor);
	map.put("releaseId",this.releaseId);
	map.put("releaseDate",this.releaseDate);
	map.put("reviewerId",this.reviewerId);
	map.put("reviewer",this.reviewer);
	map.put("auditTime",this.auditTime);
	map.put("revokeId",this.revokeId);
	map.put("revoker",this.revoker);
	map.put("revokeTime",this.revokeTime);
	map.put("resufeRemark",this.resufeRemark);
	map.put("tagList",this.tagList);
	map.put("createUser",this.createUser);
	map.put("createDate",this.createDate);
	map.put("updateUser",this.updateUser);
	map.put("updateDate",this.updateDate);
	map.put("aliveFlag",this.aliveFlag);
	map.put("extend1",this.extend1);
	map.put("extend2",this.extend2);
	map.put("extend3",this.extend3);
	map.put("extend4",this.extend4);
	map.put("extend5",this.extend5);
	map.put("extend6",this.extend6);
	map.put("extend7",this.extend7);
	map.put("extend8",this.extend8);
	map.put("extend9",this.extend9);
	map.put("extend10",this.extend10);
			return map;
	}
	
}