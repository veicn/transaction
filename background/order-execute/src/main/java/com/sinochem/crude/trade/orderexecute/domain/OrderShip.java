package com.sinochem.crude.trade.orderexecute.domain;

import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class OrderShip implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**主键id*/
	private Long orderShipId;  
	
	/**UUID*/
	private String uuid;  
	
	/**订单ID*/
	private Long orderId;  
	
	/***/
	private String orderNo;  
	
	/**代理协议号*/
	private String agentUuid;  
	
	/**运单id*/
	private Long waybillId;  
	
	/**状态*/
	private String status;  
	
	/**MMSI号*/
	private String mmsi;  
	
	/**船舶类型*/
	private String type;  
	
	/**船舶名称*/
	private String name;  
	
	/**建成日期*/
	private java.util.Date completeDate;  
	
	/**拼音简称*/
	private String pinyinSim;  
	
	/**总吨*/
	private java.math.BigDecimal allQuantity;  
	
	/**净吨*/
	private java.math.BigDecimal suttle;  
	
	/**参考载重吨*/
	private java.math.BigDecimal refQuantity;  
	
	/**货仓数量*/
	private java.math.BigDecimal warehouseQuantity;  
	
	/**材质种类*/
	private String stuffType;  
	
	/**材质涂层*/
	private String stuffCoat;  
	
	/**船舶总长（米）*/
	private java.math.BigDecimal length;  
	
	/**船宽（米）*/
	private java.math.BigDecimal wide;  
	
	/**意向运输货物*/
	private String intentGoods;  
	
	/**意向运输范围*/
	private String intentRange;  
	
	/**意向装货量（吨）*/
	private java.math.BigDecimal intentQuantity;  
	
	/**满载吃水（米）*/
	private java.math.BigDecimal draft;  
	
	/**船舶结构*/
	private String structure;  
	
	/**船籍港*/
	private String port;  
	
	/**船舶认证*/
	private String authentication;  
	
	/**加温能力（℃）*/
	private java.math.BigDecimal heat;  
	
	/**VEF*/
	private java.math.BigDecimal vef;  
	
	/**OBQ*/
	private java.math.BigDecimal obq;  
	
	/**密度*/
	private String density;  
	
	/**语言类型*/
	private String langVer;  
	
	/**当前有效状态*/
	private String aliveFlag;  
	
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
	
		/**主键id*/
	public void setOrderShipId(Long orderShipId){
		this.orderShipId=orderShipId;
	}
	/**主键id*/
	public Long getOrderShipId(){
		return this.orderShipId;
	}
	
	/**UUID*/
	public void setUuid(String uuid){
		this.uuid=uuid;
	}
	/**UUID*/
	public String getUuid(){
		return this.uuid;
	}
	
	/**订单ID*/
	public void setOrderId(Long orderId){
		this.orderId=orderId;
	}
	/**订单ID*/
	public Long getOrderId(){
		return this.orderId;
	}
	
	/***/
	public void setOrderNo(String orderNo){
		this.orderNo=orderNo;
	}
	/***/
	public String getOrderNo(){
		return this.orderNo;
	}
	
	/**代理协议号*/
	public void setAgentUuid(String agentUuid){
		this.agentUuid=agentUuid;
	}
	/**代理协议号*/
	public String getAgentUuid(){
		return this.agentUuid;
	}
	
	/**运单id*/
	public void setWaybillId(Long waybillId){
		this.waybillId=waybillId;
	}
	/**运单id*/
	public Long getWaybillId(){
		return this.waybillId;
	}
	
	/**状态*/
	public void setStatus(String status){
		this.status=status;
	}
	/**状态*/
	public String getStatus(){
		return this.status;
	}
	
	/**MMSI号*/
	public void setMmsi(String mmsi){
		this.mmsi=mmsi;
	}
	/**MMSI号*/
	public String getMmsi(){
		return this.mmsi;
	}
	
	/**船舶类型*/
	public void setType(String type){
		this.type=type;
	}
	/**船舶类型*/
	public String getType(){
		return this.type;
	}
	
	/**船舶名称*/
	public void setName(String name){
		this.name=name;
	}
	/**船舶名称*/
	public String getName(){
		return this.name;
	}
	
	/**建成日期*/
	public void setCompleteDate(java.util.Date completeDate){
		this.completeDate=completeDate;
	}
	/**建成日期*/
	public java.util.Date getCompleteDate(){
		return this.completeDate;
	}
	
	/**拼音简称*/
	public void setPinyinSim(String pinyinSim){
		this.pinyinSim=pinyinSim;
	}
	/**拼音简称*/
	public String getPinyinSim(){
		return this.pinyinSim;
	}
	
	/**总吨*/
	public void setAllQuantity(java.math.BigDecimal allQuantity){
		this.allQuantity=allQuantity;
	}
	/**总吨*/
	public java.math.BigDecimal getAllQuantity(){
		return this.allQuantity;
	}
	
	/**净吨*/
	public void setSuttle(java.math.BigDecimal suttle){
		this.suttle=suttle;
	}
	/**净吨*/
	public java.math.BigDecimal getSuttle(){
		return this.suttle;
	}
	
	/**参考载重吨*/
	public void setRefQuantity(java.math.BigDecimal refQuantity){
		this.refQuantity=refQuantity;
	}
	/**参考载重吨*/
	public java.math.BigDecimal getRefQuantity(){
		return this.refQuantity;
	}
	
	/**货仓数量*/
	public void setWarehouseQuantity(java.math.BigDecimal warehouseQuantity){
		this.warehouseQuantity=warehouseQuantity;
	}
	/**货仓数量*/
	public java.math.BigDecimal getWarehouseQuantity(){
		return this.warehouseQuantity;
	}
	
	/**材质种类*/
	public void setStuffType(String stuffType){
		this.stuffType=stuffType;
	}
	/**材质种类*/
	public String getStuffType(){
		return this.stuffType;
	}
	
	/**材质涂层*/
	public void setStuffCoat(String stuffCoat){
		this.stuffCoat=stuffCoat;
	}
	/**材质涂层*/
	public String getStuffCoat(){
		return this.stuffCoat;
	}
	
	/**船舶总长（米）*/
	public void setLength(java.math.BigDecimal length){
		this.length=length;
	}
	/**船舶总长（米）*/
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
	
	/**意向运输货物*/
	public void setIntentGoods(String intentGoods){
		this.intentGoods=intentGoods;
	}
	/**意向运输货物*/
	public String getIntentGoods(){
		return this.intentGoods;
	}
	
	/**意向运输范围*/
	public void setIntentRange(String intentRange){
		this.intentRange=intentRange;
	}
	/**意向运输范围*/
	public String getIntentRange(){
		return this.intentRange;
	}
	
	/**意向装货量（吨）*/
	public void setIntentQuantity(java.math.BigDecimal intentQuantity){
		this.intentQuantity=intentQuantity;
	}
	/**意向装货量（吨）*/
	public java.math.BigDecimal getIntentQuantity(){
		return this.intentQuantity;
	}
	
	/**满载吃水（米）*/
	public void setDraft(java.math.BigDecimal draft){
		this.draft=draft;
	}
	/**满载吃水（米）*/
	public java.math.BigDecimal getDraft(){
		return this.draft;
	}
	
	/**船舶结构*/
	public void setStructure(String structure){
		this.structure=structure;
	}
	/**船舶结构*/
	public String getStructure(){
		return this.structure;
	}
	
	/**船籍港*/
	public void setPort(String port){
		this.port=port;
	}
	/**船籍港*/
	public String getPort(){
		return this.port;
	}
	
	/**船舶认证*/
	public void setAuthentication(String authentication){
		this.authentication=authentication;
	}
	/**船舶认证*/
	public String getAuthentication(){
		return this.authentication;
	}
	
	/**加温能力（℃）*/
	public void setHeat(java.math.BigDecimal heat){
		this.heat=heat;
	}
	/**加温能力（℃）*/
	public java.math.BigDecimal getHeat(){
		return this.heat;
	}
	
	/**VEF*/
	public void setVef(java.math.BigDecimal vef){
		this.vef=vef;
	}
	/**VEF*/
	public java.math.BigDecimal getVef(){
		return this.vef;
	}
	
	/**OBQ*/
	public void setObq(java.math.BigDecimal obq){
		this.obq=obq;
	}
	/**OBQ*/
	public java.math.BigDecimal getObq(){
		return this.obq;
	}
	
	/**密度*/
	public void setDensity(String density){
		this.density=density;
	}
	/**密度*/
	public String getDensity(){
		return this.density;
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
		map.put("orderShipId",this.orderShipId);
	map.put("uuid",this.uuid);
	map.put("orderId",this.orderId);
	map.put("orderNo",this.orderNo);
	map.put("agentUuid",this.agentUuid);
	map.put("waybillId",this.waybillId);
	map.put("status",this.status);
	map.put("mmsi",this.mmsi);
	map.put("type",this.type);
	map.put("name",this.name);
	map.put("completeDate",this.completeDate);
	map.put("pinyinSim",this.pinyinSim);
	map.put("allQuantity",this.allQuantity);
	map.put("suttle",this.suttle);
	map.put("refQuantity",this.refQuantity);
	map.put("warehouseQuantity",this.warehouseQuantity);
	map.put("stuffType",this.stuffType);
	map.put("stuffCoat",this.stuffCoat);
	map.put("length",this.length);
	map.put("wide",this.wide);
	map.put("intentGoods",this.intentGoods);
	map.put("intentRange",this.intentRange);
	map.put("intentQuantity",this.intentQuantity);
	map.put("draft",this.draft);
	map.put("structure",this.structure);
	map.put("port",this.port);
	map.put("authentication",this.authentication);
	map.put("heat",this.heat);
	map.put("vef",this.vef);
	map.put("obq",this.obq);
	map.put("density",this.density);
	map.put("langVer",this.langVer);
	map.put("aliveFlag",this.aliveFlag);
	map.put("extend1",this.extend1);
	map.put("extend2",this.extend2);
	map.put("extend3",this.extend3);
	map.put("extend4",this.extend4);
	map.put("extend5",this.extend5);
	map.put("createUser",this.createUser);
	map.put("createDate",this.createDate);
	map.put("updateUser",this.updateUser);
	map.put("updateDate",this.updateDate);
	map.put("version",this.version);
			return map;
	}
}