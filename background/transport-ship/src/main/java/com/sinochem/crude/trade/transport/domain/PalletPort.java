package com.sinochem.crude.trade.transport.domain;

import java.io.Serializable;

public class PalletPort implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**业务唯一键*/
	private Long palletPortId;  
	
	/**UUID*/
	private String uuid;  
	
	/**货盘uuid*/
	private String palletUuid;  
	
	/**租船id*/
	private Long pallletId;  
	
	/**单位名称*/
	private String unitName;  
	
	/**装港*/
	private String loadPort;  
	
	/**卸港*/
	private String unloadPort;  
	
	/**装货区域*/
	private String loadRegion;  
	
	/**卸货区域*/
	private String unloadRegion;  
	
	/**数量（桶）*/
	private java.math.BigDecimal quantity;  
	
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
	
	/**扩展字段1*/
	private String fistLoadPort;
	
	/**油种*/
	private String oilType;
	
	/**单位名称英文*/
	private String unitNameEn;  
	
	/**装港英文*/
	private String loadPortEn;  
	
	/**装港code*/
	private String loadPortCode;  
	
	/**卸港英文*/
	private String unloadPortEn;  
	
	/**卸港code*/
	private String unloadPortCode;  
	
	/**油种英文*/
	private String oilTypeEn;  
	
	/**油种名称code*/
	private String oilTypeCode;  
	
		/**业务唯一键*/
	public void setPalletPortId(Long palletPortId){
		this.palletPortId=palletPortId;
	}
	/**业务唯一键*/
	public Long getPalletPortId(){
		return this.palletPortId;
	}
	
	/**UUID*/
	public void setUuid(String uuid){
		this.uuid=uuid;
	}
	/**UUID*/
	public String getUuid(){
		return this.uuid;
	}
	
	/**货盘uuid*/
	public void setPalletUuid(String palletUuid){
		this.palletUuid=palletUuid;
	}
	/**货盘uuid*/
	public String getPalletUuid(){
		return this.palletUuid;
	}
	
	/**租船id*/
	public void setPallletId(Long pallletId){
		this.pallletId=pallletId;
	}
	/**租船id*/
	public Long getPallletId(){
		return this.pallletId;
	}
	
	/**单位名称*/
	public void setUnitName(String unitName){
		this.unitName=unitName;
	}
	/**单位名称*/
	public String getUnitName(){
		return this.unitName;
	}
	
	/**装港*/
	public void setLoadPort(String loadPort){
		this.loadPort=loadPort;
	}
	/**装港*/
	public String getLoadPort(){
		return this.loadPort;
	}
	
	/**卸港*/
	public void setUnloadPort(String unloadPort){
		this.unloadPort=unloadPort;
	}
	/**卸港*/
	public String getUnloadPort(){
		return this.unloadPort;
	}
	
	/**装货区域*/
	public void setLoadRegion(String loadRegion){
		this.loadRegion=loadRegion;
	}
	/**装货区域*/
	public String getLoadRegion(){
		return this.loadRegion;
	}
	
	/**卸货区域*/
	public void setUnloadRegion(String unloadRegion){
		this.unloadRegion=unloadRegion;
	}
	/**卸货区域*/
	public String getUnloadRegion(){
		return this.unloadRegion;
	}
	
	/**数量（桶）*/
	public void setQuantity(java.math.BigDecimal quantity){
		this.quantity=quantity;
	}
	/**数量（桶）*/
	public java.math.BigDecimal getQuantity(){
		return this.quantity;
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
	/**第一装港*/
	public String getFistLoadPort() {
		return fistLoadPort;
	}
	/**第一装港*/
	public void setFistLoadPort(String fistLoadPort) {
		this.fistLoadPort = fistLoadPort;
	}
	/**油种*/
	public String getOilType() {
		return oilType;
	}
	/**油种*/
	public void setOilType(String oilType) {
		this.oilType = oilType;
	}
	/**单位名称英文*/
	public String getUnitNameEn() {
		return unitNameEn;
	}
	/**单位名称英文*/
	public void setUnitNameEn(String unitNameEn) {
		this.unitNameEn = unitNameEn;
	}
	/**装港英文*/
	public String getLoadPortEn() {
		return loadPortEn;
	}
	/**装港英文*/
	public void setLoadPortEn(String loadPortEn) {
		this.loadPortEn = loadPortEn;
	}
	/**装港code*/
	public String getLoadPortCode() {
		return loadPortCode;
	}
	/**装港code*/
	public void setLoadPortCode(String loadPortCode) {
		this.loadPortCode = loadPortCode;
	}
	/**卸港英文*/
	public String getUnloadPortEn() {
		return unloadPortEn;
	}
	/**卸港英文*/
	public void setUnloadPortEn(String unloadPortEn) {
		this.unloadPortEn = unloadPortEn;
	}
	/**卸港code*/
	public String getUnloadPortCode() {
		return unloadPortCode;
	}
	/**卸港code*/
	public void setUnloadPortCode(String unloadPortCode) {
		this.unloadPortCode = unloadPortCode;
	}
	/**油种英文*/
	public String getOilTypeEn() {
		return oilTypeEn;
	}
	/**油种英文*/
	public void setOilTypeEn(String oilTypeEn) {
		this.oilTypeEn = oilTypeEn;
	}
	/**油种名称code*/
	public String getOilTypeCode() {
		return oilTypeCode;
	}
	/**油种名称code*/
	public void setOilTypeCode(String oilTypeCode) {
		this.oilTypeCode = oilTypeCode;
	}
		
	
	/*public Map<String, Object> toMap() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("palletPortId",this.palletPortId);
	map.put("uuid",this.uuid);
	map.put("palletUuid",this.palletUuid);
	map.put("pallletId",this.pallletId);
	map.put("unitName",this.unitName);
	map.put("loadPort",this.loadPort);
	map.put("unloadPort",this.unloadPort);
	map.put("loadRegion",this.loadRegion);
	map.put("unloadRegion",this.unloadRegion);
	map.put("quantity",this.quantity);
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