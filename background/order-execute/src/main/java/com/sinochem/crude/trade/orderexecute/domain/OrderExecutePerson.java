package com.sinochem.crude.trade.orderexecute.domain;

import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class OrderExecutePerson implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**ID*/
	private Long executePersonId;  
	
	/**订单ID*/
	private Long orderId;  
	
	/**执行人名称*/
	private String memberName;  
	
	/**执行人ID*/
	private Long memberId;  
	
	/**联系电话*/
	private String tel;  
	
	/**邮箱*/
	private String email;  
	
	/**公司ID*/
	private Long epMemberId;  
	
	/**公司名称*/
	private String epMemberName;  
	
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
	
		/**ID*/
	public void setExecutePersonId(Long executePersonId){
		this.executePersonId=executePersonId;
	}
	/**ID*/
	public Long getExecutePersonId(){
		return this.executePersonId;
	}
	
	/**订单ID*/
	public void setOrderId(Long orderId){
		this.orderId=orderId;
	}
	/**订单ID*/
	public Long getOrderId(){
		return this.orderId;
	}
	
	/**执行人名称*/
	public void setMemberName(String memberName){
		this.memberName=memberName;
	}
	/**执行人名称*/
	public String getMemberName(){
		return this.memberName;
	}
	
	/**执行人ID*/
	public void setMemberId(Long memberId){
		this.memberId=memberId;
	}
	/**执行人ID*/
	public Long getMemberId(){
		return this.memberId;
	}
	
	/**联系电话*/
	public void setTel(String tel){
		this.tel=tel;
	}
	/**联系电话*/
	public String getTel(){
		return this.tel;
	}
	
	/**邮箱*/
	public void setEmail(String email){
		this.email=email;
	}
	/**邮箱*/
	public String getEmail(){
		return this.email;
	}
	
	/**公司ID*/
	public void setEpMemberId(Long epMemberId){
		this.epMemberId=epMemberId;
	}
	/**公司ID*/
	public Long getEpMemberId(){
		return this.epMemberId;
	}
	
	/**公司名称*/
	public void setEpMemberName(String epMemberName){
		this.epMemberName=epMemberName;
	}
	/**公司名称*/
	public String getEpMemberName(){
		return this.epMemberName;
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
		map.put("executePersonId",this.executePersonId);
	map.put("orderId",this.orderId);
	map.put("memberName",this.memberName);
	map.put("memberId",this.memberId);
	map.put("tel",this.tel);
	map.put("email",this.email);
	map.put("epMemberId",this.epMemberId);
	map.put("epMemberName",this.epMemberName);
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