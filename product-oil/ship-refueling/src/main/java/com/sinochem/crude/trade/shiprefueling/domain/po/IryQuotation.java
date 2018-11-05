package com.sinochem.crude.trade.shiprefueling.domain.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class IryQuotation implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 未联系 */
	public static final String STATUS_NOT_CONTRACTED = "1";
	/** 已联系 */
	public static final String STATUS_CONTRACTED = "2";
	 /** 已完成 */
	public static final String STATUS_DONE = "3";

	 /** 销售 */
	public static final String TYPE_SALE = "1";
	 /** 采购 */
	public static final String TYPE_PURCHASE = "2";

	/**业务唯一键*/
	private Long inquiryQuotationId;  
	
	/**UUID*/
	private String uuid;  
	
	/**会员公司id*/
	private Long epMemberId;  
	
	/**会员公司名称*/
	private String epMemberName;  
	
	/**公司id*/
	private Long companyId;  
	
	/**公司名称*/
	private String companyName;  
	
	/**数量*/
	private java.math.BigDecimal number;  
	
	/**议价*/
	private java.math.BigDecimal price;  
	
	/**电话*/
	private String tel;  
	
	/**联系人*/
	private String contacts;  
	
	/**是否有效(1有效0无效)*/
	private String aliveFlag;  
	
	/**版本号*/
	private String version;  
	
	/**语言版本*/
	private String langVer;  
	
	/**创建时间*/
	private java.util.Date createDate;  
	
	/**修改时间*/
	private java.util.Date updateDate;  
	
	/**创建人*/
	private Long createUser;

	/** 创建人账号*/
	private String createUserName;
	
	/**修改人*/
	private Long updateUser;  
	
	/**扩展字段1*/
	private String ext1;  
	
	/**类型(1:销售 2:采购)*/
	private String type;  
	
	/**需求ID*/
	private Long needId;  
	
	/**需求UUID*/
	private String needUuid;  
	
	/**状态(1:未联系 2:已联系3:已成交)*/
	private String status;  
	
		/**业务唯一键*/
	public void setInquiryQuotationId(Long inquiryQuotationId){
		this.inquiryQuotationId=inquiryQuotationId;
	}
	/**业务唯一键*/
	public Long getInquiryQuotationId(){
		return this.inquiryQuotationId;
	}
	
	/**UUID*/
	public void setUuid(String uuid){
		this.uuid=uuid;
	}
	/**UUID*/
	public String getUuid(){
		return this.uuid;
	}
	
	/**会员公司id*/
	public void setEpMemberId(Long epMemberId){
		this.epMemberId=epMemberId;
	}
	/**会员公司id*/
	public Long getEpMemberId(){
		return this.epMemberId;
	}
	
	/**会员公司名称*/
	public void setEpMemberName(String epMemberName){
		this.epMemberName=epMemberName;
	}
	/**会员公司名称*/
	public String getEpMemberName(){
		return this.epMemberName;
	}
	
	/**公司id*/
	public void setCompanyId(Long companyId){
		this.companyId=companyId;
	}
	/**公司id*/
	public Long getCompanyId(){
		return this.companyId;
	}
	
	/**公司名称*/
	public void setCompanyName(String companyName){
		this.companyName=companyName;
	}
	/**公司名称*/
	public String getCompanyName(){
		return this.companyName;
	}
	
	/**数量*/
	public void setNumber(java.math.BigDecimal number){
		this.number=number;
	}
	/**数量*/
	public java.math.BigDecimal getNumber(){
		return this.number;
	}
	
	/**议价*/
	public void setPrice(java.math.BigDecimal price){
		this.price=price;
	}
	/**议价*/
	public java.math.BigDecimal getPrice(){
		return this.price;
	}
	
	/**电话*/
	public void setTel(String tel){
		this.tel=tel;
	}
	/**电话*/
	public String getTel(){
		return this.tel;
	}
	
	/**联系人*/
	public void setContacts(String contacts){
		this.contacts=contacts;
	}
	/**联系人*/
	public String getContacts(){
		return this.contacts;
	}
	
	/**是否有效(1有效0无效)*/
	public void setAliveFlag(String aliveFlag){
		this.aliveFlag=aliveFlag;
	}
	/**是否有效(1有效0无效)*/
	public String getAliveFlag(){
		return this.aliveFlag;
	}
	
	/**版本号*/
	public void setVersion(String version){
		this.version=version;
	}
	/**版本号*/
	public String getVersion(){
		return this.version;
	}
	
	/**语言版本*/
	public void setLangVer(String langVer){
		this.langVer=langVer;
	}
	/**语言版本*/
	public String getLangVer(){
		return this.langVer;
	}
	
	/**创建时间*/
	public void setCreateDate(java.util.Date createDate){
		this.createDate=createDate;
	}
	/**创建时间*/
	public java.util.Date getCreateDate(){
		return this.createDate;
	}
	
	/**修改时间*/
	public void setUpdateDate(java.util.Date updateDate){
		this.updateDate=updateDate;
	}
	/**修改时间*/
	public java.util.Date getUpdateDate(){
		return this.updateDate;
	}
	
	/**创建人*/
	public void setCreateUser(Long createUser){
		this.createUser=createUser;
	}
	/**创建人*/
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	public Long getCreateUser(){
		return this.createUser;
	}

	/**创建人账号*/
	public String getCreateUserName() {
		return createUserName;
	}
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	/**修改人*/
	public void setUpdateUser(Long updateUser){
		this.updateUser=updateUser;
	}
	/**修改人*/
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	public Long getUpdateUser(){
		return this.updateUser;
	}
	
	/**扩展字段1*/
	public void setExt1(String ext1){
		this.ext1=ext1;
	}
	/**扩展字段1*/
	public String getExt1(){
		return this.ext1;
	}
	
	/**类型(1:销售 2:采购)*/
	public void setType(String type){
		this.type=type;
	}
	/**类型(1:销售 2:采购)*/
	public String getType(){
		return this.type;
	}
	
	/**需求ID*/
	public void setNeedId(Long needId){
		this.needId=needId;
	}
	/**需求ID*/
	public Long getNeedId(){
		return this.needId;
	}
	
	/**需求UUID*/
	public void setNeedUuid(String needUuid){
		this.needUuid=needUuid;
	}
	/**需求UUID*/
	public String getNeedUuid(){
		return this.needUuid;
	}
	
	/**状态(1:未联系 2:已联系3:已成交)*/
	public void setStatus(String status){
		this.status=status;
	}
	/**状态(1:未联系 2:已联系3:已成交)*/
	public String getStatus(){
		return this.status;
	}
	
		
	
	public Map<String, Object> toMap() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("inquiryQuotationId",this.inquiryQuotationId);
	map.put("uuid",this.uuid);
	map.put("epMemberId",this.epMemberId);
	map.put("epMemberName",this.epMemberName);
	map.put("companyId",this.companyId);
	map.put("companyName",this.companyName);
	map.put("number",this.number);
	map.put("price",this.price);
	map.put("tel",this.tel);
	map.put("contacts",this.contacts);
	map.put("aliveFlag",this.aliveFlag);
	map.put("version",this.version);
	map.put("langVer",this.langVer);
	map.put("createDate",this.createDate);
	map.put("updateDate",this.updateDate);
	map.put("createUser",this.createUser);
	map.put("updateUser",this.updateUser);
	map.put("ext1",this.ext1);
	map.put("type",this.type);
	map.put("needId",this.needId);
	map.put("needUuid",this.needUuid);
	map.put("status",this.status);
			return map;
	}
	
}