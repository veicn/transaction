package com.sinochem.crude.trade.orderexecute.domain;

import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class TradeSubject implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**ID*/
	private Long tradeSubjectId;  
	
	/**产品分类(1原油、2成品油)*/
	private String productType;  
	
	/**贸易条款（FOB CFR CIF ）*/
	private String tradeTerm;  
	
	/**费用名称*/
	private String subjectName;  
	
	/**费用代码*/
	private String subjectCode;  
	
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
	public void setTradeSubjectId(Long tradeSubjectId){
		this.tradeSubjectId=tradeSubjectId;
	}
	/**ID*/
	public Long getTradeSubjectId(){
		return this.tradeSubjectId;
	}
	
	/**产品分类(1原油、2成品油)*/
	public void setProductType(String productType){
		this.productType=productType;
	}
	/**产品分类(1原油、2成品油)*/
	public String getProductType(){
		return this.productType;
	}
	
	/**贸易条款（FOB CFR CIF ）*/
	public void setTradeTerm(String tradeTerm){
		this.tradeTerm=tradeTerm;
	}
	/**贸易条款（FOB CFR CIF ）*/
	public String getTradeTerm(){
		return this.tradeTerm;
	}
	
	/**费用名称*/
	public void setSubjectName(String subjectName){
		this.subjectName=subjectName;
	}
	/**费用名称*/
	public String getSubjectName(){
		return this.subjectName;
	}
	
	/**费用代码*/
	public void setSubjectCode(String subjectCode){
		this.subjectCode=subjectCode;
	}
	/**费用代码*/
	public String getSubjectCode(){
		return this.subjectCode;
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
		map.put("tradeSubjectId",this.tradeSubjectId);
	map.put("productType",this.productType);
	map.put("tradeTerm",this.tradeTerm);
	map.put("subjectName",this.subjectName);
	map.put("subjectCode",this.subjectCode);
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