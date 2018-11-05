package com.sinochem.crude.trade.orderexecute.domain;

import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class OrderFeeItem implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**ID*/
	private Long feeItemId;  
	
	/**关联ID*/
	private Long relatId;  
	
	/**费用科目名称*/
	private String subjectName;  
	
	/**费用科目代码*/
	private String subjectCode;  
	
	/**计价方式*/
	private String valuationModel;  
	
	/**计价数量*/
	private java.math.BigDecimal quantity;  
	
	/**单价*/
	private java.math.BigDecimal feePrice;  
	
	/**合计*/
	private java.math.BigDecimal totalFee;  
	
	/**是否计入结算单*/
	private String isAgent;  
	
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
	
	/**关联类型（0费用 1对账单 2结算单）*/
	private String relatType;  
	
		/**ID*/
	public void setFeeItemId(Long feeItemId){
		this.feeItemId=feeItemId;
	}
	/**ID*/
	public Long getFeeItemId(){
		return this.feeItemId;
	}
	
	/**关联ID*/
	public void setRelatId(Long relatId){
		this.relatId=relatId;
	}
	/**关联ID*/
	public Long getRelatId(){
		return this.relatId;
	}
	
	/**费用科目名称*/
	public void setSubjectName(String subjectName){
		this.subjectName=subjectName;
	}
	/**费用科目名称*/
	public String getSubjectName(){
		return this.subjectName;
	}
	
	/**费用科目代码*/
	public void setSubjectCode(String subjectCode){
		this.subjectCode=subjectCode;
	}
	/**费用科目代码*/
	public String getSubjectCode(){
		return this.subjectCode;
	}
	
	/**计价方式*/
	public void setValuationModel(String valuationModel){
		this.valuationModel=valuationModel;
	}
	/**计价方式*/
	public String getValuationModel(){
		return this.valuationModel;
	}
	
	/**计价数量*/
	public void setQuantity(java.math.BigDecimal quantity){
		this.quantity=quantity;
	}
	/**计价数量*/
	public java.math.BigDecimal getQuantity(){
		return this.quantity;
	}
	
	/**单价*/
	public void setFeePrice(java.math.BigDecimal feePrice){
		this.feePrice=feePrice;
	}
	/**单价*/
	public java.math.BigDecimal getFeePrice(){
		return this.feePrice;
	}
	
	/**合计*/
	public void setTotalFee(java.math.BigDecimal totalFee){
		this.totalFee=totalFee;
	}
	/**合计*/
	public java.math.BigDecimal getTotalFee(){
		return this.totalFee;
	}
	
	/**是否计入结算单*/
	public void setIsAgent(String isAgent){
		this.isAgent=isAgent;
	}
	/**是否计入结算单*/
	public String getIsAgent(){
		return this.isAgent;
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
	
	/**关联类型（0费用 1对账单 2结算单）*/
	public void setRelatType(String relatType){
		this.relatType=relatType;
	}
	/**关联类型（0费用 1对账单 2结算单）*/
	public String getRelatType(){
		return this.relatType;
	}
	
		
	
	public Map<String, Object> toMap() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("feeItemId",this.feeItemId);
	map.put("relatId",this.relatId);
	map.put("subjectName",this.subjectName);
	map.put("subjectCode",this.subjectCode);
	map.put("valuationModel",this.valuationModel);
	map.put("quantity",this.quantity);
	map.put("feePrice",this.feePrice);
	map.put("totalFee",this.totalFee);
	map.put("isAgent",this.isAgent);
	map.put("langVer",this.langVer);
	map.put("aliveFlag",this.aliveFlag);
	map.put("createUser",this.createUser);
	map.put("createDate",this.createDate);
	map.put("updateUser",this.updateUser);
	map.put("updateDate",this.updateDate);
	map.put("version",this.version);
	map.put("relatType",this.relatType);
			return map;
	}
}