package com.sinochem.crude.trade.operation.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.sinochem.crude.trade.common.SimplePageInfo;

public class OpeAdSpotRentVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/**主键*/
	private String adRentId;  
	
	/**系统类型*/
	private String adSetId;  
	
	/**租用人*/
	private String adRentPerson;  
	
	/**租用人联系方式*/
	private String adRentPersonTel;  
	
	/**租用公司*/
	private String adRentCompany;  
	
	/**租用公司部门*/
	private String adRentDepartment;  
	
	/**备注*/
	private String remark;  
	
	/**租用开始日期*/
	private java.util.Date startDate;  
	
	/**租用结束日期*/
	private java.util.Date endDate;  
	
	/**状态*/
	private String status;  
	
	/**终止原因*/
	private String reason;  
	
	/**市场ID*/
	private String marketId;  
	
	/**语言*/
	private String langVer;  
	
	/**删除标志*/
	private String aliveFlag;  
	
	/**创建人*/
	private String createUser;  
	
	/**创建时间*/
	private java.util.Date createDate;  
	
	/**更新人*/
	private String updateUser;  
	
	/**更新日期*/
	private java.util.Date updateDate;  
	
	/**扩展字段1*/
	private String extend1;  
	
	/**扩展字段2*/
	private String extend2;  
	
	/**扩展字段3*/
	private String extend3;  
	
	/**扩展字段4*/
	private String extend4;  
	
	/**扩展字段5*/
	private String extend5;  
	
	/**扩展字段6*/
	private String extend6;  
	
	/**扩展字段7*/
	private String extend7;  
	
	/**扩展字段8*/
	private String extend8;  
	
	/**扩展字段9*/
	private String extend9;  
	
	/**扩展字段10*/
	private String extend10;  
	
	/**分页对象*/
	@JsonUnwrapped
	private SimplePageInfo pageInfo;
	
		/**主键*/
	public void setAdRentId(String adRentId){
		this.adRentId=adRentId;
	}
	/**主键*/
	public String getAdRentId(){
		return this.adRentId;
	}
	
	/**系统类型*/
	public void setAdSetId(String adSetId){
		this.adSetId=adSetId;
	}
	/**系统类型*/
	public String getAdSetId(){
		return this.adSetId;
	}
	
	/**租用人*/
	public void setAdRentPerson(String adRentPerson){
		this.adRentPerson=adRentPerson;
	}
	/**租用人*/
	public String getAdRentPerson(){
		return this.adRentPerson;
	}
	
	/**租用人联系方式*/
	public void setAdRentPersonTel(String adRentPersonTel){
		this.adRentPersonTel=adRentPersonTel;
	}
	/**租用人联系方式*/
	public String getAdRentPersonTel(){
		return this.adRentPersonTel;
	}
	
	/**租用公司*/
	public void setAdRentCompany(String adRentCompany){
		this.adRentCompany=adRentCompany;
	}
	/**租用公司*/
	public String getAdRentCompany(){
		return this.adRentCompany;
	}
	
	/**租用公司部门*/
	public void setAdRentDepartment(String adRentDepartment){
		this.adRentDepartment=adRentDepartment;
	}
	/**租用公司部门*/
	public String getAdRentDepartment(){
		return this.adRentDepartment;
	}
	
	/**备注*/
	public void setRemark(String remark){
		this.remark=remark;
	}
	/**备注*/
	public String getRemark(){
		return this.remark;
	}
	
	/**租用开始日期*/
	public void setStartDate(java.util.Date startDate){
		this.startDate=startDate;
	}
	/**租用开始日期*/
	public java.util.Date getStartDate(){
		return this.startDate;
	}
	
	/**租用结束日期*/
	public void setEndDate(java.util.Date endDate){
		this.endDate=endDate;
	}
	/**租用结束日期*/
	public java.util.Date getEndDate(){
		return this.endDate;
	}
	
	/**状态*/
	public void setStatus(String status){
		this.status=status;
	}
	/**状态*/
	public String getStatus(){
		return this.status;
	}
	
	/**终止原因*/
	public void setReason(String reason){
		this.reason=reason;
	}
	/**终止原因*/
	public String getReason(){
		return this.reason;
	}
	
	/**市场ID*/
	public void setMarketId(String marketId){
		this.marketId=marketId;
	}
	/**市场ID*/
	public String getMarketId(){
		return this.marketId;
	}
	
	/**语言*/
	public void setLangVer(String langVer){
		this.langVer=langVer;
	}
	/**语言*/
	public String getLangVer(){
		return this.langVer;
	}
	
	/**删除标志*/
	public void setAliveFlag(String aliveFlag){
		this.aliveFlag=aliveFlag;
	}
	/**删除标志*/
	public String getAliveFlag(){
		return this.aliveFlag;
	}
	
	/**创建人*/
	public void setCreateUser(String createUser){
		this.createUser=createUser;
	}
	/**创建人*/
	public String getCreateUser(){
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
	
	/**更新人*/
	public void setUpdateUser(String updateUser){
		this.updateUser=updateUser;
	}
	/**更新人*/
	public String getUpdateUser(){
		return this.updateUser;
	}
	
	/**更新日期*/
	public void setUpdateDate(java.util.Date updateDate){
		this.updateDate=updateDate;
	}
	/**更新日期*/
	public java.util.Date getUpdateDate(){
		return this.updateDate;
	}
	
	/**扩展字段1*/
	public void setExtend1(String extend1){
		this.extend1=extend1;
	}
	/**扩展字段1*/
	public String getExtend1(){
		return this.extend1;
	}
	
	/**扩展字段2*/
	public void setExtend2(String extend2){
		this.extend2=extend2;
	}
	/**扩展字段2*/
	public String getExtend2(){
		return this.extend2;
	}
	
	/**扩展字段3*/
	public void setExtend3(String extend3){
		this.extend3=extend3;
	}
	/**扩展字段3*/
	public String getExtend3(){
		return this.extend3;
	}
	
	/**扩展字段4*/
	public void setExtend4(String extend4){
		this.extend4=extend4;
	}
	/**扩展字段4*/
	public String getExtend4(){
		return this.extend4;
	}
	
	/**扩展字段5*/
	public void setExtend5(String extend5){
		this.extend5=extend5;
	}
	/**扩展字段5*/
	public String getExtend5(){
		return this.extend5;
	}
	
	/**扩展字段6*/
	public void setExtend6(String extend6){
		this.extend6=extend6;
	}
	/**扩展字段6*/
	public String getExtend6(){
		return this.extend6;
	}
	
	/**扩展字段7*/
	public void setExtend7(String extend7){
		this.extend7=extend7;
	}
	/**扩展字段7*/
	public String getExtend7(){
		return this.extend7;
	}
	
	/**扩展字段8*/
	public void setExtend8(String extend8){
		this.extend8=extend8;
	}
	/**扩展字段8*/
	public String getExtend8(){
		return this.extend8;
	}
	
	/**扩展字段9*/
	public void setExtend9(String extend9){
		this.extend9=extend9;
	}
	/**扩展字段9*/
	public String getExtend9(){
		return this.extend9;
	}
	
	/**扩展字段10*/
	public void setExtend10(String extend10){
		this.extend10=extend10;
	}
	/**扩展字段10*/
	public String getExtend10(){
		return this.extend10;
	}
	
	public SimplePageInfo getPageInfo() {
		return pageInfo;
	}
	public void setPageInfo(SimplePageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}
	
	
}
