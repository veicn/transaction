package com.sinochem.crude.trade.info.domain;

import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class Comment implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**自增主键*/
	private Long id;  
	
	/**32位唯一码*/
	private String uuid;  
	
	/**评论内容*/
	private String commentContent;  
	
	/**状态*/
	private String status;  
	
	/**资讯ID*/
	private Long informationId;  
	
	/**评论人id*/
	private String commentUserId;  
	
	/**评论人是否个人（1是，0否）*/
	private String isPersonal;  
	
	/**平台  苹果交易版:app_ios_trade 安卓交易版:app_android_trade 苹果商机版:app_ios_oppor 

安卓商机版:app_android_oppor PC版:web*/
	private String platForm;  
	
	/**客户端操作系统 ios: android:*/
	private String clientOs;  
	
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
	
	/**扩展字段2*/
	private String extend2;  
	
	/**扩展字段3*/
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
	
	/**评论内容*/
	public void setCommentContent(String commentContent){
		this.commentContent=commentContent;
	}
	/**评论内容*/
	public String getCommentContent(){
		return this.commentContent;
	}
	
	/**状态*/
	public void setStatus(String status){
		this.status=status;
	}
	/**状态*/
	public String getStatus(){
		return this.status;
	}
	
	/**资讯ID*/
	public void setInformationId(Long informationId){
		this.informationId=informationId;
	}
	/**资讯ID*/
	public Long getInformationId(){
		return this.informationId;
	}
	
	/**评论人id*/
	public void setCommentUserId(String commentUserId){
		this.commentUserId=commentUserId;
	}
	/**评论人id*/
	public String getCommentUserId(){
		return this.commentUserId;
	}
	
	/**评论人是否个人（1是，0否）*/
	public void setIsPersonal(String isPersonal){
		this.isPersonal=isPersonal;
	}
	/**评论人是否个人（1是，0否）*/
	public String getIsPersonal(){
		return this.isPersonal;
	}
	
	/**平台  苹果交易版:app_ios_trade 安卓交易版:app_android_trade 苹果商机版:app_ios_oppor 

安卓商机版:app_android_oppor PC版:web*/
	public void setPlatForm(String platForm){
		this.platForm=platForm;
	}
	/**平台  苹果交易版:app_ios_trade 安卓交易版:app_android_trade 苹果商机版:app_ios_oppor 

安卓商机版:app_android_oppor PC版:web*/
	public String getPlatForm(){
		return this.platForm;
	}
	
	/**客户端操作系统 ios: android:*/
	public void setClientOs(String clientOs){
		this.clientOs=clientOs;
	}
	/**客户端操作系统 ios: android:*/
	public String getClientOs(){
		return this.clientOs;
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
	
	/**扩展字段2*/
	public void setExtend2(String extend2){
		this.extend2=extend2;
	}
	/**扩展字段2*/
	public String getExtend2(){
		return this.extend2;
	}
	
	/**扩展字段3*/
	public void setExtend3(String extend3){
		this.extend3=extend3;
	}
	/**扩展字段3*/
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
	map.put("commentContent",this.commentContent);
	map.put("status",this.status);
	map.put("informationId",this.informationId);
	map.put("commentUserId",this.commentUserId);
	map.put("isPersonal",this.isPersonal);
	map.put("platForm",this.platForm);
	map.put("clientOs",this.clientOs);
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