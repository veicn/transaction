package com.sinochem.crude.trade.orderexecute.domain;

import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class OrderGoodsFile implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**ID*/
	private Long goodsFileId;  
	
	/**UUID*/
	private String goodsFileUuid;  
	
	/**商品ID*/
	private Long orderGoodsId;  
	
	/**附件名称*/
	private String fileName;  
	
	/**附件地址*/
	private String filePath;  
	
	/**附件格式*/
	private String fileFormat;  
	
	/**附件大小*/
	private Long fileSize;  
	
	/**状态*/
	private String status;  
	
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
	public void setGoodsFileId(Long goodsFileId){
		this.goodsFileId=goodsFileId;
	}
	/**ID*/
	public Long getGoodsFileId(){
		return this.goodsFileId;
	}
	
	/**UUID*/
	public void setGoodsFileUuid(String goodsFileUuid){
		this.goodsFileUuid=goodsFileUuid;
	}
	/**UUID*/
	public String getGoodsFileUuid(){
		return this.goodsFileUuid;
	}
	
	/**商品ID*/
	public void setOrderGoodsId(Long orderGoodsId){
		this.orderGoodsId=orderGoodsId;
	}
	/**商品ID*/
	public Long getOrderGoodsId(){
		return this.orderGoodsId;
	}
	
	/**附件名称*/
	public void setFileName(String fileName){
		this.fileName=fileName;
	}
	/**附件名称*/
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
	
	/**附件大小*/
	public void setFileSize(Long fileSize){
		this.fileSize=fileSize;
	}
	/**附件大小*/
	public Long getFileSize(){
		return this.fileSize;
	}
	
	/**状态*/
	public void setStatus(String status){
		this.status=status;
	}
	/**状态*/
	public String getStatus(){
		return this.status;
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
		map.put("goodsFileId",this.goodsFileId);
	map.put("goodsFileUuid",this.goodsFileUuid);
	map.put("orderGoodsId",this.orderGoodsId);
	map.put("fileName",this.fileName);
	map.put("filePath",this.filePath);
	map.put("fileFormat",this.fileFormat);
	map.put("fileSize",this.fileSize);
	map.put("status",this.status);
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