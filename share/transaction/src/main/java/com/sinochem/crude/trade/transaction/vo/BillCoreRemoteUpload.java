package com.sinochem.crude.trade.transaction.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class BillCoreRemoteUpload implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**主键_ID*/
	private Long documentFileId;  
	
	/**UUID*/
	private String documentFileUuid;  
	
	/**订单ID*/
	private Long orderId;  
	
	/**附件种类代码*/
	private String fileTypeCode;  
	
	/**附件种类名字*/
	private String fileTypeName;  
	
	/**附件名*/
	private String fileName;  
	
	/**附件地址*/
	private String filePath;  
	
	/**附件格式*/
	private String fileFormat;  
	
	/**上传人*/
	private Long uploadPerson;  
	
	/**预留字段1*/
	private String ext1;  
	
	/**版本号*/
	private String version;  
	
	/**当前有效状态*/
	private String aliveFlag;  
	
	/**创建时间*/
	private java.util.Date createDate;  
	
	/**创建者*/
	private Long createUser;  
	
	/**更新时间*/
	private java.util.Date updateDate;  
	
	/**更新者*/
	private Long updateUser;  
	
		/**主键_ID*/
	public void setDocumentFileId(Long documentFileId){
		this.documentFileId=documentFileId;
	}
	/**主键_ID*/
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
	
	/**订单ID*/
	public void setOrderId(Long orderId){
		this.orderId=orderId;
	}
	/**订单ID*/
	public Long getOrderId(){
		return this.orderId;
	}
	
	/**附件种类代码*/
	public void setFileTypeCode(String fileTypeCode){
		this.fileTypeCode=fileTypeCode;
	}
	/**附件种类代码*/
	public String getFileTypeCode(){
		return this.fileTypeCode;
	}
	
	/**附件种类名字*/
	public void setFileTypeName(String fileTypeName){
		this.fileTypeName=fileTypeName;
	}
	/**附件种类名字*/
	public String getFileTypeName(){
		return this.fileTypeName;
	}
	
	/**附件名*/
	public void setFileName(String fileName){
		this.fileName=fileName;
	}
	/**附件名*/
	public String getFileName(){
		return this.fileName;
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
	
	/**上传人*/
	public void setUploadPerson(Long uploadPerson){
		this.uploadPerson=uploadPerson;
	}
	/**上传人*/
	public Long getUploadPerson(){
		return this.uploadPerson;
	}
	
	/**预留字段1*/
	public void setExt1(String ext1){
		this.ext1=ext1;
	}
	/**预留字段1*/
	public String getExt1(){
		return this.ext1;
	}
	
	/**版本号*/
	public void setVersion(String version){
		this.version=version;
	}
	/**版本号*/
	public String getVersion(){
		return this.version;
	}
	
	/**当前有效状态*/
	public void setAliveFlag(String aliveFlag){
		this.aliveFlag=aliveFlag;
	}
	/**当前有效状态*/
	public String getAliveFlag(){
		return this.aliveFlag;
	}
	
	/**创建时间*/
	public void setCreateDate(java.util.Date createDate){
		this.createDate=createDate;
	}
	/**创建时间*/
	public java.util.Date getCreateDate(){
		return this.createDate;
	}
	
	/**创建者*/
	public void setCreateUser(Long createUser){
		this.createUser=createUser;
	}
	/**创建者*/
	public Long getCreateUser(){
		return this.createUser;
	}
	
	/**更新时间*/
	public void setUpdateDate(java.util.Date updateDate){
		this.updateDate=updateDate;
	}
	/**更新时间*/
	public java.util.Date getUpdateDate(){
		return this.updateDate;
	}
	
	/**更新者*/
	public void setUpdateUser(Long updateUser){
		this.updateUser=updateUser;
	}
	/**更新者*/
	public Long getUpdateUser(){
		return this.updateUser;
	}
	
		
	
	public Map<String, Object> toMap() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("documentFileId",this.documentFileId);
	map.put("documentFileUuid",this.documentFileUuid);
	map.put("orderId",this.orderId);
	map.put("fileTypeCode",this.fileTypeCode);
	map.put("fileTypeName",this.fileTypeName);
	map.put("fileName",this.fileName);
	map.put("filePath",this.filePath);
	map.put("fileFormat",this.fileFormat);
	map.put("uploadPerson",this.uploadPerson);
	map.put("ext1",this.ext1);
	map.put("version",this.version);
	map.put("aliveFlag",this.aliveFlag);
	map.put("createDate",this.createDate);
	map.put("createUser",this.createUser);
	map.put("updateDate",this.updateDate);
	map.put("updateUser",this.updateUser);
			return map;
	}
	
}