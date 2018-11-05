package com.sinochem.crude.trade.orderexecute.domain;

import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class OrderDocumentFile implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**ID*/
	private Long documentFileId;  
	
	/**UUID*/
	private String documentFileUuid;  
	
	/**单证ID*/
	private Long orderDocumentId;  
	
	/**附件名称*/
	private String fileName;  
	
	/**附件代码*/
	private String fileCode;  
	
	/**文件原名*/
	private String originalName;  	
	
	/**附件地址*/
	private String filePath;  
	
	/**附件格式*/
	private String fileFormat;  
	
	/**附件大小*/
	private Long fileSize;  
	
	/**状态*/
	private String fileStatus;  
	
	/**上传时间*/
	private java.util.Date uploadTime;  
	
	/**上传人*/
	private Long uploadPerson;  
	
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
	public void setDocumentFileId(Long documentFileId){
		this.documentFileId=documentFileId;
	}
	/**ID*/
	public Long getDocumentFileId(){
		return this.documentFileId;
	}
	
	/**UUID*/
	public void setDocumentFileUuid(String documentFileUuid){
		this.documentFileUuid=documentFileUuid;
	}
	/**UUID*/
	public String getDocumentFileUuid(){
		return this.documentFileUuid;
	}
	
	/**单证ID*/
	public void setOrderDocumentId(Long orderDocumentId){
		this.orderDocumentId=orderDocumentId;
	}
	/**单证ID*/
	public Long getOrderDocumentId(){
		return this.orderDocumentId;
	}
	
	/**附件名称*/
	public void setFileName(String fileName){
		this.fileName=fileName;
	}
	/**附件名称*/
	public String getFileName(){
		return this.fileName;
	}
	
	/**附件代码*/
	public void setFileCode(String fileCode){
		this.fileCode=fileCode;
	}
	/**附件代码*/
	public String getFileCode(){
		return this.fileCode;
	}
	
	/**文件原名*/
	public String getOriginalName() {
		return originalName;
	}
	/**文件原名*/
	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}
	
	/**附件地址*/
	public void setFilePath(String filePath){
		this.filePath=filePath;
	}
	/**附件地址*/
	public String getFilePath(){
		return this.filePath;
	}
	
	/**附件格式*/
	public void setFileFormat(String fileFormat){
		this.fileFormat=fileFormat;
	}
	/**附件格式*/
	public String getFileFormat(){
		return this.fileFormat;
	}
	
	/**附件大小*/
	public void setFileSize(Long fileSize){
		this.fileSize=fileSize;
	}
	/**附件大小*/
	public Long getFileSize(){
		return this.fileSize;
	}
	
	/**状态*/
	public void setFileStatus(String fileStatus){
		this.fileStatus=fileStatus;
	}
	/**状态*/
	public String getFileStatus(){
		return this.fileStatus;
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
		map.put("documentFileId",this.documentFileId);
	map.put("documentFileUuid",this.documentFileUuid);
	map.put("orderDocumentId",this.orderDocumentId);
	map.put("fileName",this.fileName);
	map.put("fileCode",this.fileCode);
	map.put("filePath",this.filePath);
	map.put("fileFormat",this.fileFormat);
	map.put("fileSize",this.fileSize);
	map.put("fileStatus",this.fileStatus);
	map.put("uploadTime",this.uploadTime);
	map.put("uploadPerson",this.uploadPerson);
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