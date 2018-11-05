package com.sinochem.crude.trade.transport.domain;

import java.io.Serializable;

public class ProductoilShip implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**业务唯一键*/
	private Long sysShipId;  
	
	/**UUID*/
	private String uuid;  
	
	/**状态（1待审核2未启用3已启用4已作废  目前创建成功默认3已启用）*/
	private String status;  
	
	/**船舶代码IMO*/
	private String imo;  
	
	/***/
	private String mmsi;  
	
	/**船舶用途类型*/
	private String useType;  
	
	/**船名*/
	private String name;  
	
	/**mmsi*/
	private String pennant;  
	
	/**船型*/
	private String type;  
	
	/**建造商*/
	private String builder;  
	
	/**船级社*/
	private String classSociety;  
	
	/**建成年份*/
	private String completeDate;  
	
	/**船体*/
	private String hull;  
	
	/**船长（米）*/
	private java.math.BigDecimal length;  
	
	/**船宽（米）*/
	private java.math.BigDecimal wide;  
	
	/**吃水（米）*/
	private java.math.BigDecimal draft;  
	
	/**载重吨*/
	private java.math.BigDecimal loadQuantity;  
	
	/**总吨*/
	private java.math.BigDecimal allQuantity;  
	
	/**载重容积*/
	private java.math.BigDecimal capacity;  
	
	/**附件*/
	private String accessory;  
	
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
	
	/**修改人*/
	private Long updateUser;  
	
	/**扩展字段1*/
	private String ext1;  
	
	/**公司id*/
	private Long epMemberId;  
	
	/**公司名称*/
	private String epMemberName;  
	
	/**发布方类型（1船东/经纪人2二船东3平台）*/
	private String publisherType;  
	
		/**业务唯一键*/
	public void setSysShipId(Long sysShipId){
		this.sysShipId=sysShipId;
	}
	/**业务唯一键*/
	public Long getSysShipId(){
		return this.sysShipId;
	}
	
	/**UUID*/
	public void setUuid(String uuid){
		this.uuid=uuid;
	}
	/**UUID*/
	public String getUuid(){
		return this.uuid;
	}
	
	/**状态（1待审核2未启用3已启用4已作废  目前创建成功默认3已启用）*/
	public void setStatus(String status){
		this.status=status;
	}
	/**状态（1待审核2未启用3已启用4已作废  目前创建成功默认3已启用）*/
	public String getStatus(){
		return this.status;
	}
	
	/**船舶代码IMO*/
	public void setImo(String imo){
		this.imo=imo;
	}
	/**船舶代码IMO*/
	public String getImo(){
		return this.imo;
	}
	
	/***/
	public void setMmsi(String mmsi){
		this.mmsi=mmsi;
	}
	/***/
	public String getMmsi(){
		return this.mmsi;
	}
	
	/**船舶用途类型*/
	public void setUseType(String useType){
		this.useType=useType;
	}
	/**船舶用途类型*/
	public String getUseType(){
		return this.useType;
	}
	
	/**船名*/
	public void setName(String name){
		this.name=name;
	}
	/**船名*/
	public String getName(){
		return this.name;
	}
	
	/**mmsi*/
	public void setPennant(String pennant){
		this.pennant=pennant;
	}
	/**mmsi*/
	public String getPennant(){
		return this.pennant;
	}
	
	/**船型*/
	public void setType(String type){
		this.type=type;
	}
	/**船型*/
	public String getType(){
		return this.type;
	}
	
	/**建造商*/
	public void setBuilder(String builder){
		this.builder=builder;
	}
	/**建造商*/
	public String getBuilder(){
		return this.builder;
	}
	
	/**船级社*/
	public void setClassSociety(String classSociety){
		this.classSociety=classSociety;
	}
	/**船级社*/
	public String getClassSociety(){
		return this.classSociety;
	}
	
	/**建成年份*/
	public void setCompleteDate(String completeDate){
		this.completeDate=completeDate;
	}
	/**建成年份*/
	public String getCompleteDate(){
		return this.completeDate;
	}
	
	/**船体*/
	public void setHull(String hull){
		this.hull=hull;
	}
	/**船体*/
	public String getHull(){
		return this.hull;
	}
	
	/**船长（米）*/
	public void setLength(java.math.BigDecimal length){
		this.length=length;
	}
	/**船长（米）*/
	public java.math.BigDecimal getLength(){
		return this.length;
	}
	
	/**船宽（米）*/
	public void setWide(java.math.BigDecimal wide){
		this.wide=wide;
	}
	/**船宽（米）*/
	public java.math.BigDecimal getWide(){
		return this.wide;
	}
	
	/**吃水（米）*/
	public void setDraft(java.math.BigDecimal draft){
		this.draft=draft;
	}
	/**吃水（米）*/
	public java.math.BigDecimal getDraft(){
		return this.draft;
	}
	
	/**载重吨*/
	public void setLoadQuantity(java.math.BigDecimal loadQuantity){
		this.loadQuantity=loadQuantity;
	}
	/**载重吨*/
	public java.math.BigDecimal getLoadQuantity(){
		return this.loadQuantity;
	}
	
	/**总吨*/
	public void setAllQuantity(java.math.BigDecimal allQuantity){
		this.allQuantity=allQuantity;
	}
	/**总吨*/
	public java.math.BigDecimal getAllQuantity(){
		return this.allQuantity;
	}
	
	/**载重容积*/
	public void setCapacity(java.math.BigDecimal capacity){
		this.capacity=capacity;
	}
	/**载重容积*/
	public java.math.BigDecimal getCapacity(){
		return this.capacity;
	}
	
	/**附件*/
	public void setAccessory(String accessory){
		this.accessory=accessory;
	}
	/**附件*/
	public String getAccessory(){
		return this.accessory;
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
	
	/**发布方类型（1船东/经纪人2二船东3平台）*/
	public void setPublisherType(String publisherType){
		this.publisherType=publisherType;
	}
	/**发布方类型（1船东/经纪人2二船东3平台）*/
	public String getPublisherType(){
		return this.publisherType;
	}
	
		
	
	/*public Map<String, Object> toMap() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("sysShipId",this.sysShipId);
	map.put("uuid",this.uuid);
	map.put("status",this.status);
	map.put("imo",this.imo);
	map.put("mmsi",this.mmsi);
	map.put("useType",this.useType);
	map.put("name",this.name);
	map.put("pennant",this.pennant);
	map.put("type",this.type);
	map.put("builder",this.builder);
	map.put("classSociety",this.classSociety);
	map.put("completeDate",this.completeDate);
	map.put("hull",this.hull);
	map.put("length",this.length);
	map.put("wide",this.wide);
	map.put("draft",this.draft);
	map.put("loadQuantity",this.loadQuantity);
	map.put("allQuantity",this.allQuantity);
	map.put("capacity",this.capacity);
	map.put("accessory",this.accessory);
	map.put("aliveFlag",this.aliveFlag);
	map.put("version",this.version);
	map.put("langVer",this.langVer);
	map.put("createDate",this.createDate);
	map.put("updateDate",this.updateDate);
	map.put("createUser",this.createUser);
	map.put("updateUser",this.updateUser);
	map.put("ext1",this.ext1);
	map.put("epMemberId",this.epMemberId);
	map.put("epMemberName",this.epMemberName);
	map.put("publisherType",this.publisherType);
			return map;
	}*/
}