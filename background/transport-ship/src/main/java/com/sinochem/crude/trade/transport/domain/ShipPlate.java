package com.sinochem.crude.trade.transport.domain;

import java.io.Serializable;

import org.springframework.format.annotation.DateTimeFormat;

public class ShipPlate implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**业务唯一键*/
	private Long shipPlateId;  
	
	/**UUID*/
	private String uuid;  
	
	/**公司id*/
	private Long epMemberId;  
	
	/**船盘代码IMO*/
	private String imo;  
	
	/**状态（1已发布2洽谈中3已确认租船）*/
	private String status;  
	
	/**船舶库uuid*/
	private String sysShipUuid;  
	
	/**船舶库id*/
	private Long sysShipId;  
	
	/**船名*/
	private String name;  
	
	/**船型*/
	private String type;  
	
	/**建成年份*/
	private String completeDate;
	
	/**船东*/
	private String shipOwner;  
	
	/**载重吨*/
	private java.math.BigDecimal loadQuantity;  
	
	/**船位*/
	private String position;  
	
	/**ETA*/
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String eta;  
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String etaCabinda; 
	
	/**经纪人名称*/
	private String brokerName;  
	
	/**经纪人id*/
	private Long brokerId;  
	
	/**备注*/
	private String remark;  
	
	/**是否有效(1有效0无效)*/
	private String aliveFlag;  
	
	/**版本号*/
	private String version;  
	
	/**语言版本*/
	private String langVer;  
	
	/**创建时间*/
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date createDate;
	
	/**修改时间*/
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date updateDate;  
	
	/**有效日期*/
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date periodDate;  
	
	/**创建人*/
	private Long createUser;  
	
	/**修改人*/
	private Long updateUser;  
	
	/**扩展字段1*/
	private String ext1;  
	
	/**会员公司名称*/
	private String epMemberName;  
	
	/**船盘发布类型（1船东/船东经纪人2转租船东）*/
	private String relType;  
	
	/**船东id*/
	private Long shipOwnerId;  
	
	/**发布人邮箱*/
	private String email;  
	
	/**是否执行中（1是0否）*/
	private String leaveFlag;  
	
	/**船龄*/
	private String shipAge;  
	
	/**卸货完成时间*/
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date open;
	
	/**船盘发布时间*/
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date publishTime;
	
	/**时效*/
	private Integer period;
	
	/**会员公司名称英文*/
	private String epMemberNameEn;  
	
	/**船东名称英文*/
	private String shipOwnerEn;  
	
	/**经纪人名称英文*/
	private String brokerNameEn; 
	
	
	
		public String getEpMemberNameEn() {
		return epMemberNameEn;
	}
	public void setEpMemberNameEn(String epMemberNameEn) {
		this.epMemberNameEn = epMemberNameEn;
	}
	public String getShipOwnerEn() {
		return shipOwnerEn;
	}
	public void setShipOwnerEn(String shipOwnerEn) {
		this.shipOwnerEn = shipOwnerEn;
	}
	public String getBrokerNameEn() {
		return brokerNameEn;
	}
	public void setBrokerNameEn(String brokerNameEn) {
		this.brokerNameEn = brokerNameEn;
	}
		/**业务唯一键*/
	public void setShipPlateId(Long shipPlateId){
		this.shipPlateId=shipPlateId;
	}
	/**业务唯一键*/
	public Long getShipPlateId(){
		return this.shipPlateId;
	}
	
	/**UUID*/
	public void setUuid(String uuid){
		this.uuid=uuid;
	}
	/**UUID*/
	public String getUuid(){
		return this.uuid;
	}
	
	/**公司id*/
	public void setEpMemberId(Long epMemberId){
		this.epMemberId=epMemberId;
	}
	/**公司id*/
	public Long getEpMemberId(){
		return this.epMemberId;
	}
	
	/**船盘代码IMO*/
	public void setImo(String imo){
		this.imo=imo;
	}
	/**船盘代码IMO*/
	public String getImo(){
		return this.imo;
	}
	
	/**状态状态（1已发布2洽谈中3已确认租船）*/
	public void setStatus(String status){
		this.status=status;
	}
	/**状态状态（1已发布2洽谈中3已确认租船）*/
	public String getStatus(){
		return this.status;
	}
	
	/**船舶库uuid*/
	public void setSysShipUuid(String sysShipUuid){
		this.sysShipUuid=sysShipUuid;
	}
	/**船舶库uuid*/
	public String getSysShipUuid(){
		return this.sysShipUuid;
	}
	
	/**船舶库id*/
	public void setSysShipId(Long sysShipId){
		this.sysShipId=sysShipId;
	}
	/**船舶库id*/
	public Long getSysShipId(){
		return this.sysShipId;
	}
	
	/**船名*/
	public void setName(String name){
		this.name=name;
	}
	/**船名*/
	public String getName(){
		return this.name;
	}
	
	/**船型*/
	public void setType(String type){
		this.type=type;
	}
	/**船型*/
	public String getType(){
		return this.type;
	}
	
	/**建成年份*/
	public void setCompleteDate(String completeDate){
		this.completeDate=completeDate;
	}
	/**建成年份*/
	public String getCompleteDate(){
		return this.completeDate;
	}
	
	/**船东*/
	public void setShipOwner(String shipOwner){
		this.shipOwner=shipOwner;
	}
	/**船东*/
	public String getShipOwner(){
		return this.shipOwner;
	}
	
	/**载重吨*/
	public void setLoadQuantity(java.math.BigDecimal loadQuantity){
		this.loadQuantity=loadQuantity;
	}
	/**载重吨*/
	public java.math.BigDecimal getLoadQuantity(){
		return this.loadQuantity;
	}
	
	/**船位*/
	public void setPosition(String position){
		this.position=position;
	}
	/**船位*/
	public String getPosition(){
		return this.position;
	}
	
	//**ETA*//*
	public void setEta(String eta){
		this.eta=eta;
	}
	//**ETA*//*
	public String getEta(){
		return this.eta;
	}
	
	public String getEtaCabinda() {
		return etaCabinda;
	}
	public void setEtaCabinda(String etaCabinda) {
		this.etaCabinda = etaCabinda;
	}
	/**经纪人名称*/
	public void setBrokerName(String brokerName){
		this.brokerName=brokerName;
	}
	/**经纪人名称*/
	public String getBrokerName(){
		return this.brokerName;
	}
	
	/**经纪人id*/
	public void setBrokerId(Long brokerId){
		this.brokerId=brokerId;
	}
	/**经纪人id*/
	public Long getBrokerId(){
		return this.brokerId;
	}
	
	/**备注*/
	public void setRemark(String remark){
		this.remark=remark;
	}
	/**备注*/
	public String getRemark(){
		return this.remark;
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
	public Long getCreateUser(){
		return this.createUser;
	}
	
	/**修改人*/
	public void setUpdateUser(Long updateUser){
		this.updateUser=updateUser;
	}
	/**修改人*/
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
	
	/**会员公司名称*/
	public void setEpMemberName(String epMemberName){
		this.epMemberName=epMemberName;
	}
	/**会员公司名称*/
	public String getEpMemberName(){
		return this.epMemberName;
	}
	
	/**船盘发布类型（1船东/船东经纪人2转租船东）*/
	public void setRelType(String relType){
		this.relType=relType;
	}
	/**船盘发布类型（1船东/船东经纪人2转租船东）*/
	public String getRelType(){
		return this.relType;
	}
	
	/**船东id*/
	public void setShipOwnerId(Long shipOwnerId){
		this.shipOwnerId=shipOwnerId;
	}
	/**船东id*/
	public Long getShipOwnerId(){
		return this.shipOwnerId;
	}
	
	/**发布人邮箱*/
	public void setEmail(String email){
		this.email=email;
	}
	/**发布人邮箱*/
	public String getEmail(){
		return this.email;
	}
	
	/**是否执行中（1是0否）*/
	public void setLeaveFlag(String leaveFlag){
		this.leaveFlag=leaveFlag;
	}
	/**是否执行中（1是0否）*/
	public String getLeaveFlag(){
		return this.leaveFlag;
	}
	
	/**船龄*/
	public void setShipAge(String shipAge){
		this.shipAge=shipAge;
	}
	/**船龄*/
	public String getShipAge(){
		return this.shipAge;
	}
	
	/**卸货完成时间*/
	public java.util.Date getOpen() {
		return open;
	}
	/**卸货完成时间*/
	public void setOpen(java.util.Date open) {
		this.open = open;
	}
	
	/**船盘发布时间*/
	public java.util.Date getPublishTime() {
		return publishTime;
	}
	/**船盘发布时间*/
	public void setPublishTime(java.util.Date publishTime) {
		this.publishTime = publishTime;
	}
	
	/**有效日期*/
	public java.util.Date getPeriodDate() {
		return periodDate;
	}
	/**有效日期*/
	public void setPeriodDate(java.util.Date periodDate) {
		this.periodDate = periodDate;
	}
	/**时效*/
	public Integer getPeriod() {
		return period;
	}
	/**时效*/
	public void setPeriod(Integer period) {
		this.period = period;
	}
		
	
	/*public Map<String, Object> toMap() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("shipPlateId",this.shipPlateId);
	map.put("uuid",this.uuid);
	map.put("epMemberId",this.epMemberId);
	map.put("imo",this.imo);
	map.put("status",this.status);
	map.put("sysShipUuid",this.sysShipUuid);
	map.put("sysShipId",this.sysShipId);
	map.put("name",this.name);
	map.put("type",this.type);
	map.put("completeDate",this.completeDate);
	map.put("shipOwner",this.shipOwner);
	map.put("loadQuantity",this.loadQuantity);
	map.put("position",this.position);
	map.put("eta",this.eta);
	map.put("brokerName",this.brokerName);
	map.put("brokerId",this.brokerId);
	map.put("remark",this.remark);
	map.put("aliveFlag",this.aliveFlag);
	map.put("version",this.version);
	map.put("langVer",this.langVer);
	map.put("createDate",this.createDate);
	map.put("updateDate",this.updateDate);
	map.put("createUser",this.createUser);
	map.put("updateUser",this.updateUser);
	map.put("ext1",this.ext1);
			return map;
	}*/
}