package com.sinochem.crude.trade.orderexecute.domain;

import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class OrderContractFile implements Serializable {

	private static final long serialVersionUID = 1L;
	/**原文件名*/
	private String originalName; 
	
	/**合同附件ID*/
	private Long contractFileId;  
	
	/**合同ID*/
	private Long contractId;  
	
	/**合同附件名称*/
	private String contractFileName;  
	
	/**附件地址*/
	private String filePath;  
	
	/**上传时间*/
	private java.util.Date uploadTime;  
	
	/**上传人*/
	private Long uploadPerson;  
	
	/**状态*/
	private String fileStatus;  
	
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
	
	public String getOriginalName() {
		return originalName;
	}
	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}
	/**合同附件ID*/
	public void setContractFileId(Long contractFileId){
		this.contractFileId=contractFileId;
	}
	/**合同附件ID*/
	public Long getContractFileId(){
		return this.contractFileId;
	}
	
	/**合同ID*/
	public void setContractId(Long contractId){
		this.contractId=contractId;
	}
	/**合同ID*/
	public Long getContractId(){
		return this.contractId;
	}
	
	/**合同附件名称*/
	public void setContractFileName(String contractFileName){
		this.contractFileName=contractFileName;
	}
	/**合同附件名称*/
	public String getContractFileName(){
		return this.contractFileName;
	}
	
	/**附件地址*/
	public void setFilePath(String filePath){
		this.filePath=filePath;
	}
	/**附件地址*/
	public String getFilePath(){
		return this.filePath;
	}
	
	/**上传时间*/
	public void setUploadTime(java.util.Date uploadTime){
		this.uploadTime=uploadTime;
	}
	/**上传时间*/
	public java.util.Date getUploadTime(){
		return this.uploadTime;
	}
	
	/**上传人*/
	public void setUploadPerson(Long uploadPerson){
		this.uploadPerson=uploadPerson;
	}
	/**上传人*/
	public Long getUploadPerson(){
		return this.uploadPerson;
	}
	
	/**状态*/
	public void setFileStatus(String fileStatus){
		this.fileStatus=fileStatus;
	}
	/**状态*/
	public String getFileStatus(){
		return this.fileStatus;
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
		map.put("contractFileId",this.contractFileId);
	map.put("contractId",this.contractId);
	map.put("contractFileName",this.contractFileName);
	map.put("filePath",this.filePath);
	map.put("uploadTime",this.uploadTime);
	map.put("uploadPerson",this.uploadPerson);
	map.put("fileStatus",this.fileStatus);
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