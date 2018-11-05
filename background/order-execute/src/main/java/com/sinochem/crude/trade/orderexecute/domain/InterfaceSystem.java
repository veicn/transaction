package com.sinochem.crude.trade.orderexecute.domain;

import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class InterfaceSystem implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**主键*/
	private Long sysId;  
	
	/**系统别名*/
	private String sysName;  
	
	/**系统描述*/
	private String sysDec;  
	
	/**系统类型*/
	private String sysType;  
	
	/**关联会员ID*/
	private Long memberId;  
	
	/**关联会员*/
	private String memberName;  
	
	/**会员类型*/
	private String memberType;  
	
	/**身份识别标识*/
	private String loginName;  
	
	/**密码*/
	private String password;  
	
	/**对接地址*/
	private String sysUrl;  
	
	/**启用接口角色授权*/
	private String isGrant;  
	
	/**启用标识*/
	private String isOpen;  
	
	/**语言类型*/
	private String langVer;  
	
	/**当前有效状态*/
	private String aliveFlag;  
	
	/**创建人*/
	private Long createUser;  
	
	/**创建时间*/
	private java.util.Date createDate;  
	
	/**修改人*/
	private Long updateUser;  
	
	/**修改时间*/
	private java.util.Date updateDate;  
	
	/**版本号*/
	private Integer version;  
	
		/**主键*/
	public void setSysId(Long sysId){
		this.sysId=sysId;
	}
	/**主键*/
	public Long getSysId(){
		return this.sysId;
	}
	
	/**系统别名*/
	public void setSysName(String sysName){
		this.sysName=sysName;
	}
	/**系统别名*/
	public String getSysName(){
		return this.sysName;
	}
	
	/**系统描述*/
	public void setSysDec(String sysDec){
		this.sysDec=sysDec;
	}
	/**系统描述*/
	public String getSysDec(){
		return this.sysDec;
	}
	
	/**系统类型*/
	public void setSysType(String sysType){
		this.sysType=sysType;
	}
	/**系统类型*/
	public String getSysType(){
		return this.sysType;
	}
	
	/**关联会员ID*/
	public void setMemberId(Long memberId){
		this.memberId=memberId;
	}
	/**关联会员ID*/
	public Long getMemberId(){
		return this.memberId;
	}
	
	/**关联会员*/
	public void setMemberName(String memberName){
		this.memberName=memberName;
	}
	/**关联会员*/
	public String getMemberName(){
		return this.memberName;
	}
	
	/**会员类型*/
	public void setMemberType(String memberType){
		this.memberType=memberType;
	}
	/**会员类型*/
	public String getMemberType(){
		return this.memberType;
	}
	
	/**身份识别标识*/
	public void setLoginName(String loginName){
		this.loginName=loginName;
	}
	/**身份识别标识*/
	public String getLoginName(){
		return this.loginName;
	}
	
	/**密码*/
	public void setPassword(String password){
		this.password=password;
	}
	/**密码*/
	public String getPassword(){
		return this.password;
	}
	
	/**对接地址*/
	public void setSysUrl(String sysUrl){
		this.sysUrl=sysUrl;
	}
	/**对接地址*/
	public String getSysUrl(){
		return this.sysUrl;
	}
	
	/**启用接口角色授权*/
	public void setIsGrant(String isGrant){
		this.isGrant=isGrant;
	}
	/**启用接口角色授权*/
	public String getIsGrant(){
		return this.isGrant;
	}
	
	/**启用标识*/
	public void setIsOpen(String isOpen){
		this.isOpen=isOpen;
	}
	/**启用标识*/
	public String getIsOpen(){
		return this.isOpen;
	}
	
	/**语言类型*/
	public void setLangVer(String langVer){
		this.langVer=langVer;
	}
	/**语言类型*/
	public String getLangVer(){
		return this.langVer;
	}
	
	/**当前有效状态*/
	public void setAliveFlag(String aliveFlag){
		this.aliveFlag=aliveFlag;
	}
	/**当前有效状态*/
	public String getAliveFlag(){
		return this.aliveFlag;
	}
	
	/**创建人*/
	public void setCreateUser(Long createUser){
		this.createUser=createUser;
	}
	/**创建人*/
	public Long getCreateUser(){
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
	
	/**修改人*/
	public void setUpdateUser(Long updateUser){
		this.updateUser=updateUser;
	}
	/**修改人*/
	public Long getUpdateUser(){
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
	
	/**版本号*/
	public void setVersion(Integer version){
		this.version=version;
	}
	/**版本号*/
	public Integer getVersion(){
		return this.version;
	}
	
		
	
	public Map<String, Object> toMap() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("sysId",this.sysId);
	map.put("sysName",this.sysName);
	map.put("sysDec",this.sysDec);
	map.put("sysType",this.sysType);
	map.put("memberId",this.memberId);
	map.put("memberName",this.memberName);
	map.put("memberType",this.memberType);
	map.put("loginName",this.loginName);
	map.put("password",this.password);
	map.put("sysUrl",this.sysUrl);
	map.put("isGrant",this.isGrant);
	map.put("isOpen",this.isOpen);
	map.put("langVer",this.langVer);
	map.put("aliveFlag",this.aliveFlag);
	map.put("createUser",this.createUser);
	map.put("createDate",this.createDate);
	map.put("updateUser",this.updateUser);
	map.put("updateDate",this.updateDate);
	map.put("version",this.version);
			return map;
	}
}