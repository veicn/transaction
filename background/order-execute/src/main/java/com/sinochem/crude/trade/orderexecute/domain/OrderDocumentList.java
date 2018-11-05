package com.sinochem.crude.trade.orderexecute.domain;

import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class OrderDocumentList implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**ID*/
	private Long documentId;  
	
	/**交易大类（1原油；2成品油；3化工品）*/
	private String tradeCategory;  
	
	/**类型描述*/
	private String documentTypeDesc;  
	
	/**单证类型*/
	private String documentType;  
	
	/**附件名称*/
	private String fileName;  
	
	/**附件代码*/
	private String fileCode;  
	
	/**上传角色(1卖家；2买家；3贸易商)多个时逗号分割*/
	private String fromUser;  
	
	/**推送角色（1卖家；2买家；3贸易商)多个时逗号分割*/
	private String toUser;  
	
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
	public void setDocumentId(Long documentId){
		this.documentId=documentId;
	}
	/**ID*/
	public Long getDocumentId(){
		return this.documentId;
	}
	
	/**交易大类（1原油；2成品油；3化工品）*/
	public void setTradeCategory(String tradeCategory){
		this.tradeCategory=tradeCategory;
	}
	/**交易大类（1原油；2成品油；3化工品）*/
	public String getTradeCategory(){
		return this.tradeCategory;
	}
	
	/**类型描述*/
	public void setDocumentTypeDesc(String documentTypeDesc){
		this.documentTypeDesc=documentTypeDesc;
	}
	/**类型描述*/
	public String getDocumentTypeDesc(){
		return this.documentTypeDesc;
	}
	
	/**单证类型*/
	public void setDocumentType(String documentType){
		this.documentType=documentType;
	}
	/**单证类型*/
	public String getDocumentType(){
		return this.documentType;
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
	
	/**上传角色(1卖家；2买家；3贸易商)多个时逗号分割*/
	public void setFromUser(String fromUser){
		this.fromUser=fromUser;
	}
	/**上传角色(1卖家；2买家；3贸易商)多个时逗号分割*/
	public String getFromUser(){
		return this.fromUser;
	}
	
	/**推送角色（1卖家；2买家；3贸易商)多个时逗号分割*/
	public void setToUser(String toUser){
		this.toUser=toUser;
	}
	/**推送角色（1卖家；2买家；3贸易商)多个时逗号分割*/
	public String getToUser(){
		return this.toUser;
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
		map.put("documentId",this.documentId);
	map.put("tradeCategory",this.tradeCategory);
	map.put("documentTypeDesc",this.documentTypeDesc);
	map.put("documentType",this.documentType);
	map.put("fileName",this.fileName);
	map.put("fileCode",this.fileCode);
	map.put("fromUser",this.fromUser);
	map.put("toUser",this.toUser);
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