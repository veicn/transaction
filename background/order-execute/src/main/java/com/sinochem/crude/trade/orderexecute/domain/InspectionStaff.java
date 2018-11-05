package com.sinochem.crude.trade.orderexecute.domain;

import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class InspectionStaff implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**ID*/
	private Long id;  
	
	/**UUID*/
	private String uuid;  
	
	/**商检公司的id*/
	private Long epMemberId;  
	
	/**商检公司的名称*/
	private String epMemberName;  
	
	/**港口*/
	private String port;  
	
	/**联系人*/
	private String contactName;  
	
	/**联系电话*/
	private String tel;  
	
	/**商检公司ID*/
	private Long ccicId;  
	
	/**邮箱*/
	private String email;  
	
	/**语言类型*/
	private String langVer;  
	
	/**有效状态*/
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
	
		/**ID*/
	public void setId(Long id){
		this.id=id;
	}
	/**ID*/
	public Long getId(){
		return this.id;
	}
	
	/**UUID*/
	public void setUuid(String uuid){
		this.uuid=uuid;
	}
	/**UUID*/
	public String getUuid(){
		return this.uuid;
	}
	
	/**商检公司的id*/
	public void setEpMemberId(Long epMemberId){
		this.epMemberId=epMemberId;
	}
	/**商检公司的id*/
	public Long getEpMemberId(){
		return this.epMemberId;
	}
	
	/**商检公司的名称*/
	public void setEpMemberName(String epMemberName){
		this.epMemberName=epMemberName;
	}
	/**商检公司的名称*/
	public String getEpMemberName(){
		return this.epMemberName;
	}
	
	/**港口*/
	public void setPort(String port){
		this.port=port;
	}
	/**港口*/
	public String getPort(){
		return this.port;
	}
	
	/**联系人*/
	public void setContactName(String contactName){
		this.contactName=contactName;
	}
	/**联系人*/
	public String getContactName(){
		return this.contactName;
	}
	
	/**联系电话*/
	public void setTel(String tel){
		this.tel=tel;
	}
	/**联系电话*/
	public String getTel(){
		return this.tel;
	}
	
	/**商检公司ID*/
	public void setCcicId(Long ccicId){
		this.ccicId=ccicId;
	}
	/**商检公司ID*/
	public Long getCcicId(){
		return this.ccicId;
	}
	
	/**邮箱*/
	public void setEmail(String email){
		this.email=email;
	}
	/**邮箱*/
	public String getEmail(){
		return this.email;
	}
	
	/**语言类型*/
	public void setLangVer(String langVer){
		this.langVer=langVer;
	}
	/**语言类型*/
	public String getLangVer(){
		return this.langVer;
	}
	
	/**有效状态*/
	public void setAliveFlag(String aliveFlag){
		this.aliveFlag=aliveFlag;
	}
	/**有效状态*/
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
		map.put("id",this.id);
	map.put("uuid",this.uuid);
	map.put("epMemberId",this.epMemberId);
	map.put("epMemberName",this.epMemberName);
	map.put("port",this.port);
	map.put("contactName",this.contactName);
	map.put("tel",this.tel);
	map.put("ccicId",this.ccicId);
	map.put("email",this.email);
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