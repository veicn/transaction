package com.sinochem.crude.trade.orderexecute.domain;

import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class InterfaceMember implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**主键*/
	private Long interfaceMemberId;  
	
	/**外部系统别名*/
	private String sysName;  
	
	/**用户账号/身份识别号*/
	private String loginName;  
	
	/**用户密码*/
	private String password;  
	
	/**用户角色*/
	private String memberRole;  
	
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
	public void setInterfaceMemberId(Long interfaceMemberId){
		this.interfaceMemberId=interfaceMemberId;
	}
	/**主键*/
	public Long getInterfaceMemberId(){
		return this.interfaceMemberId;
	}
	
	/**外部系统别名*/
	public void setSysName(String sysName){
		this.sysName=sysName;
	}
	/**外部系统别名*/
	public String getSysName(){
		return this.sysName;
	}
	
	/**用户账号/身份识别号*/
	public void setLoginName(String loginName){
		this.loginName=loginName;
	}
	/**用户账号/身份识别号*/
	public String getLoginName(){
		return this.loginName;
	}
	
	/**用户密码*/
	public void setPassword(String password){
		this.password=password;
	}
	/**用户密码*/
	public String getPassword(){
		return this.password;
	}
	
	/**用户角色*/
	public void setMemberRole(String memberRole){
		this.memberRole=memberRole;
	}
	/**用户角色*/
	public String getMemberRole(){
		return this.memberRole;
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
		map.put("interfaceMemberId",this.interfaceMemberId);
	map.put("sysName",this.sysName);
	map.put("loginName",this.loginName);
	map.put("password",this.password);
	map.put("memberRole",this.memberRole);
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