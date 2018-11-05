package com.sinochem.crude.trade.transport.domain;

import java.io.Serializable;

public class Agency implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**业务唯一键*/
	private Long agencyId;  
	
	/**UUID*/
	private String uuid;  
	
	/**船代类型（1装港2卸港）*/
	private String type;  
	
	/**航次开始uuid*/
	private String voyageStartUuid;  
	
	/**船合同uuid*/
	private String shipPactUuid;  
	
	/**船合同id*/
	private Long shipPactId;  
	
	/**港口*/
	private String port;  
	
	/**公司id*/
	private Long epMemberId;  
	
	/**公司名称*/
	private String epMemberName;  
	
	/**是否有效(1有效0无效)*/
	private String aliveFlag;  
	
	/**创建时间*/
	private java.util.Date createDate;  
	
	/**修改时间*/
	private java.util.Date updateDate;  
	
	/**创建人*/
	private Long createUser;  
	
	/**修改人*/
	private Long updateUser;  
	
	/**扩展字段1*/
	private String ext1;  
	
	/**扩展字段2*/
	private String ext2;  
	
	/**扩展字段3*/
	private String ext3;  
	
	/**扩展字段4*/
	private String ext4;  
	
	/**扩展字段5*/
	private String ext5;  
	
	/**扩展字段6*/
	private String ext6;  
	
	/**扩展字段7*/
	private Long ext7;  
	
	/**扩展字段8*/
	private Long ext8;  
	
	/**会员公司名称英文*/
	private String epMemberNameEn;  
	
	/**港口英文*/
	private String portEn;  
	
	/**港口code*/
	private String portCode; 
	
	public String getEpMemberNameEn() {
		return epMemberNameEn;
	}
	public void setEpMemberNameEn(String epMemberNameEn) {
		this.epMemberNameEn = epMemberNameEn;
	}
		/**业务唯一键*/
	public void setAgencyId(Long agencyId){
		this.agencyId=agencyId;
	}
	/**业务唯一键*/
	public Long getAgencyId(){
		return this.agencyId;
	}
	
	/**UUID*/
	public void setUuid(String uuid){
		this.uuid=uuid;
	}
	/**UUID*/
	public String getUuid(){
		return this.uuid;
	}
	
	/**船代类型（1装港2卸港）*/
	public void setType(String type){
		this.type=type;
	}
	/**船代类型（1装港2卸港）*/
	public String getType(){
		return this.type;
	}
	
	/**航次开始uuid*/
	public void setVoyageStartUuid(String voyageStartUuid){
		this.voyageStartUuid=voyageStartUuid;
	}
	/**航次开始uuid*/
	public String getVoyageStartUuid(){
		return this.voyageStartUuid;
	}
	
	/**船合同uuid*/
	public void setShipPactUuid(String shipPactUuid){
		this.shipPactUuid=shipPactUuid;
	}
	/**船合同uuid*/
	public String getShipPactUuid(){
		return this.shipPactUuid;
	}
	
	/**船合同id*/
	public void setShipPactId(Long shipPactId){
		this.shipPactId=shipPactId;
	}
	/**船合同id*/
	public Long getShipPactId(){
		return this.shipPactId;
	}
	
	/**港口*/
	public void setPort(String port){
		this.port=port;
	}
	/**港口*/
	public String getPort(){
		return this.port;
	}
	
	/**公司id*/
	public void setEpMemberId(Long epMemberId){
		this.epMemberId=epMemberId;
	}
	/**公司id*/
	public Long getEpMemberId(){
		return this.epMemberId;
	}
	
	/**公司名称*/
	public void setEpMemberName(String epMemberName){
		this.epMemberName=epMemberName;
	}
	/**公司名称*/
	public String getEpMemberName(){
		return this.epMemberName;
	}
	
	/**是否有效(1有效0无效)*/
	public void setAliveFlag(String aliveFlag){
		this.aliveFlag=aliveFlag;
	}
	/**是否有效(1有效0无效)*/
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
	
	/**扩展字段2*/
	public void setExt2(String ext2){
		this.ext2=ext2;
	}
	/**扩展字段2*/
	public String getExt2(){
		return this.ext2;
	}
	
	/**扩展字段3*/
	public void setExt3(String ext3){
		this.ext3=ext3;
	}
	/**扩展字段3*/
	public String getExt3(){
		return this.ext3;
	}
	
	/**扩展字段4*/
	public void setExt4(String ext4){
		this.ext4=ext4;
	}
	/**扩展字段4*/
	public String getExt4(){
		return this.ext4;
	}
	
	/**扩展字段5*/
	public void setExt5(String ext5){
		this.ext5=ext5;
	}
	/**扩展字段5*/
	public String getExt5(){
		return this.ext5;
	}
	
	/**扩展字段6*/
	public void setExt6(String ext6){
		this.ext6=ext6;
	}
	/**扩展字段6*/
	public String getExt6(){
		return this.ext6;
	}
	
	/**扩展字段7*/
	public void setExt7(Long ext7){
		this.ext7=ext7;
	}
	/**扩展字段7*/
	public Long getExt7(){
		return this.ext7;
	}
	
	/**扩展字段8*/
	public void setExt8(Long ext8){
		this.ext8=ext8;
	}
	/**扩展字段8*/
	public Long getExt8(){
		return this.ext8;
	}
	public String getPortEn() {
		return portEn;
	}
	public void setPortEn(String portEn) {
		this.portEn = portEn;
	}
	public String getPortCode() {
		return portCode;
	}
	public void setPortCode(String portCode) {
		this.portCode = portCode;
	}
	
		
	
	/*public Map<String, Object> toMap() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("agencyId",this.agencyId);
	map.put("uuid",this.uuid);
	map.put("type",this.type);
	map.put("voyageStartUuid",this.voyageStartUuid);
	map.put("shipPactUuid",this.shipPactUuid);
	map.put("shipPactId",this.shipPactId);
	map.put("port",this.port);
	map.put("epMemberId",this.epMemberId);
	map.put("epMemberName",this.epMemberName);
	map.put("aliveFlag",this.aliveFlag);
	map.put("createDate",this.createDate);
	map.put("updateDate",this.updateDate);
	map.put("createUser",this.createUser);
	map.put("updateUser",this.updateUser);
	map.put("ext1",this.ext1);
	map.put("ext2",this.ext2);
	map.put("ext3",this.ext3);
	map.put("ext4",this.ext4);
	map.put("ext5",this.ext5);
	map.put("ext6",this.ext6);
	map.put("ext7",this.ext7);
	map.put("ext8",this.ext8);
			return map;
	}*/
}