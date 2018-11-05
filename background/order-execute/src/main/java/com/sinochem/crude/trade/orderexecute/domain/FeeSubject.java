package com.sinochem.crude.trade.orderexecute.domain;

import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class FeeSubject implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**ID*/
	private Long feeSubjectId;  
	
	/**科目名称*/
	private String subjectName;  
	
	/**科目代码*/
	private String subjectCode;  
	
	/**版本号*/
	private Integer version;  
	
	/**创建人*/
	private Long createUser;  
	
	/**创建时间*/
	private java.util.Date createDate;  
	
	/**当前有效状态*/
	private String aliveFlag;  
	
	/**修改人*/
	private Long updateUser;  
	
	/**语言类型*/
	private String langVer;  
	
	/**修改时间*/
	private java.util.Date updateDate;  
	
		/**ID*/
	public void setFeeSubjectId(Long feeSubjectId){
		this.feeSubjectId=feeSubjectId;
	}
	/**ID*/
	public Long getFeeSubjectId(){
		return this.feeSubjectId;
	}
	
	/**科目名称*/
	public void setSubjectName(String subjectName){
		this.subjectName=subjectName;
	}
	/**科目名称*/
	public String getSubjectName(){
		return this.subjectName;
	}
	
	/**科目代码*/
	public void setSubjectCode(String subjectCode){
		this.subjectCode=subjectCode;
	}
	/**科目代码*/
	public String getSubjectCode(){
		return this.subjectCode;
	}
	
	/**版本号*/
	public void setVersion(Integer version){
		this.version=version;
	}
	/**版本号*/
	public Integer getVersion(){
		return this.version;
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
	
	/**当前有效状态*/
	public void setAliveFlag(String aliveFlag){
		this.aliveFlag=aliveFlag;
	}
	/**当前有效状态*/
	public String getAliveFlag(){
		return this.aliveFlag;
	}
	
	/**修改人*/
	public void setUpdateUser(Long updateUser){
		this.updateUser=updateUser;
	}
	/**修改人*/
	public Long getUpdateUser(){
		return this.updateUser;
	}
	
	/**语言类型*/
	public void setLangVer(String langVer){
		this.langVer=langVer;
	}
	/**语言类型*/
	public String getLangVer(){
		return this.langVer;
	}
	
	/**修改时间*/
	public void setUpdateDate(java.util.Date updateDate){
		this.updateDate=updateDate;
	}
	/**修改时间*/
	public java.util.Date getUpdateDate(){
		return this.updateDate;
	}
	
		
	
	public Map<String, Object> toMap() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("feeSubjectId",this.feeSubjectId);
	map.put("subjectName",this.subjectName);
	map.put("subjectCode",this.subjectCode);
	map.put("version",this.version);
	map.put("createUser",this.createUser);
	map.put("createDate",this.createDate);
	map.put("aliveFlag",this.aliveFlag);
	map.put("updateUser",this.updateUser);
	map.put("langVer",this.langVer);
	map.put("updateDate",this.updateDate);
			return map;
	}
}