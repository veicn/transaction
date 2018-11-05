package com.sinochem.crude.trade.transport.domain;

import java.io.Serializable;

public class Disburden implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**业务唯一键*/
	private Long disburdenId;  
	
	/**UUID*/
	private String uuid;  
	
	/**船卸港uuid*/
	private String unloadPortUuid;  
	
	/**卸港*/
	private String unloadPort;  
	
	/**油种*/
	private String oilType;  
	
	/**代理协议code*/
	private String agreementCode;  
	
	/**货盘id*/
	private Long palletId;  
	
	/**货盘uuid*/
	private String palletUuid;  
	
	/**运单uuid*/
	private String waybillUuid;  
	
	/**运单id*/
	private Long waybillId;  
	
	/**公司id*/
	private Long epMemberId;  
	
	/**代理协议uuid*/
	private String agreementUuid;  
	
	/**代理协议id*/
	private Long agreementId;  
	
	/**船合同uuid*/
	private String shipPactUuid;  
	
	/**船合同id*/
	private Long shipPactId;  
	
	/**卸港船毛桶*/
	private java.math.BigDecimal shipHairBar;  
	
	/**卸港船毛吨*/
	private java.math.BigDecimal shipHairTon;  
	
	/**卸港商检毛桶*/
	private java.math.BigDecimal commHairBar;  
	
	/**卸港商检毛吨*/
	private java.math.BigDecimal commHairTon;  
	
	/**卸港商检净桶*/
	private java.math.BigDecimal commCleanBar;  
	
	/**卸港商检净吨*/
	private java.math.BigDecimal commCleanTon;  
	
	/**卸港国检净桶*/
	private java.math.BigDecimal counCleanBar;  
	
	/**卸港国检净吨*/
	private java.math.BigDecimal counCleanTon;  
	
	/**卸港罐毛桶*/
	private java.math.BigDecimal potHairBar;  
	
	/**卸港罐毛吨*/
	private java.math.BigDecimal potHairTon;  
	
	/**提单短量（%毛桶）*/
	private java.math.BigDecimal blHairBarRate;  
	
	/**提单短量（%毛吨）*/
	private java.math.BigDecimal blHairTonRate;  
	
	/**国检短量（%净吨）*/
	private java.math.BigDecimal counCleanBarRate;  
	
	/**国检短量（%净桶）*/
	private java.math.BigDecimal counCleanTonRate;  
	
	/**ROB桶（Reain on   board）*/
	private java.math.BigDecimal robQuanatity;  
	
	/**备注*/
	private String remark;  
	
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
	
	/**COD*/
	private java.util.Date cod;  
	
	/**卸港船毛桶_VEF*/
	private java.math.BigDecimal shipHairBarVef;  
	
	/**卸港船毛吨_VEF*/
	private java.math.BigDecimal shipHairTonVef;  
	
	/**API*/
	private java.math.BigDecimal api;  
	
	/**水杂含量*/
	private java.math.BigDecimal waterImpCon;  
	
	/**装港明水数量*/
	private java.math.BigDecimal waterQuantity;  
	
	/**船量毛体积*/
	private java.math.BigDecimal shGrossCubicMeter;  
	
	/**船量净体积*/
	private java.math.BigDecimal shNetCubicMeter;  
	
	/**船量密度*/
	private java.math.BigDecimal shDensity;  
	
	/**附件名称*/
	private String accessoryName;  
	
	/**附件路径*/
	private String accessoryPath;  
	
	/**卸港英文*/
	private String unloadPortEn;  
	
	/**卸港code*/
	private String unloadPortCode;  
	
	/**油种英文*/
	private String oilTypeEn;  
	
	/**油种名称code*/
	private String oilTypeCode;  
	
	/**业务唯一键*/
	public void setDisburdenId(Long disburdenId){
		this.disburdenId=disburdenId;
	}
	/**业务唯一键*/
	public Long getDisburdenId(){
		return this.disburdenId;
	}
	
	/**UUID*/
	public void setUuid(String uuid){
		this.uuid=uuid;
	}
	/**UUID*/
	public String getUuid(){
		return this.uuid;
	}
	
	/**船卸港uuid*/
	public void setUnloadPortUuid(String unloadPortUuid){
		this.unloadPortUuid=unloadPortUuid;
	}
	/**船卸港uuid*/
	public String getUnloadPortUuid(){
		return this.unloadPortUuid;
	}
	
	/**卸港*/
	public void setUnloadPort(String unloadPort){
		this.unloadPort=unloadPort;
	}
	/**卸港*/
	public String getUnloadPort(){
		return this.unloadPort;
	}
	
	/**油种*/
	public void setOilType(String oilType){
		this.oilType=oilType;
	}
	/**油种*/
	public String getOilType(){
		return this.oilType;
	}
	
	/**代理协议code*/
	public void setAgreementCode(String agreementCode){
		this.agreementCode=agreementCode;
	}
	/**代理协议code*/
	public String getAgreementCode(){
		return this.agreementCode;
	}
	
	/**货盘id*/
	public void setPalletId(Long palletId){
		this.palletId=palletId;
	}
	/**货盘id*/
	public Long getPalletId(){
		return this.palletId;
	}
	
	/**货盘uuid*/
	public void setPalletUuid(String palletUuid){
		this.palletUuid=palletUuid;
	}
	/**货盘uuid*/
	public String getPalletUuid(){
		return this.palletUuid;
	}
	
	/**运单uuid*/
	public void setWaybillUuid(String waybillUuid){
		this.waybillUuid=waybillUuid;
	}
	/**运单uuid*/
	public String getWaybillUuid(){
		return this.waybillUuid;
	}
	
	/**运单id*/
	public void setWaybillId(Long waybillId){
		this.waybillId=waybillId;
	}
	/**运单id*/
	public Long getWaybillId(){
		return this.waybillId;
	}
	
	/**公司id*/
	public void setEpMemberId(Long epMemberId){
		this.epMemberId=epMemberId;
	}
	/**公司id*/
	public Long getEpMemberId(){
		return this.epMemberId;
	}
	
	/**代理协议uuid*/
	public void setAgreementUuid(String agreementUuid){
		this.agreementUuid=agreementUuid;
	}
	/**代理协议uuid*/
	public String getAgreementUuid(){
		return this.agreementUuid;
	}
	
	/**代理协议id*/
	public void setAgreementId(Long agreementId){
		this.agreementId=agreementId;
	}
	/**代理协议id*/
	public Long getAgreementId(){
		return this.agreementId;
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
	
	/**卸港船毛桶*/
	public void setShipHairBar(java.math.BigDecimal shipHairBar){
		this.shipHairBar=shipHairBar;
	}
	/**卸港船毛桶*/
	public java.math.BigDecimal getShipHairBar(){
		return this.shipHairBar;
	}
	
	/**卸港船毛吨*/
	public void setShipHairTon(java.math.BigDecimal shipHairTon){
		this.shipHairTon=shipHairTon;
	}
	/**卸港船毛吨*/
	public java.math.BigDecimal getShipHairTon(){
		return this.shipHairTon;
	}
	
	/**卸港商检毛桶*/
	public void setCommHairBar(java.math.BigDecimal commHairBar){
		this.commHairBar=commHairBar;
	}
	/**卸港商检毛桶*/
	public java.math.BigDecimal getCommHairBar(){
		return this.commHairBar;
	}
	
	/**卸港商检毛吨*/
	public void setCommHairTon(java.math.BigDecimal commHairTon){
		this.commHairTon=commHairTon;
	}
	/**卸港商检毛吨*/
	public java.math.BigDecimal getCommHairTon(){
		return this.commHairTon;
	}
	
	/**卸港商检净桶*/
	public void setCommCleanBar(java.math.BigDecimal commCleanBar){
		this.commCleanBar=commCleanBar;
	}
	/**卸港商检净桶*/
	public java.math.BigDecimal getCommCleanBar(){
		return this.commCleanBar;
	}
	
	/**卸港商检净吨*/
	public void setCommCleanTon(java.math.BigDecimal commCleanTon){
		this.commCleanTon=commCleanTon;
	}
	/**卸港商检净吨*/
	public java.math.BigDecimal getCommCleanTon(){
		return this.commCleanTon;
	}
	
	/**卸港国检净桶*/
	public void setCounCleanBar(java.math.BigDecimal counCleanBar){
		this.counCleanBar=counCleanBar;
	}
	/**卸港国检净桶*/
	public java.math.BigDecimal getCounCleanBar(){
		return this.counCleanBar;
	}
	
	/**卸港国检净吨*/
	public void setCounCleanTon(java.math.BigDecimal counCleanTon){
		this.counCleanTon=counCleanTon;
	}
	/**卸港国检净吨*/
	public java.math.BigDecimal getCounCleanTon(){
		return this.counCleanTon;
	}
	
	/**卸港罐毛桶*/
	public void setPotHairBar(java.math.BigDecimal potHairBar){
		this.potHairBar=potHairBar;
	}
	/**卸港罐毛桶*/
	public java.math.BigDecimal getPotHairBar(){
		return this.potHairBar;
	}
	
	/**卸港罐毛吨*/
	public void setPotHairTon(java.math.BigDecimal potHairTon){
		this.potHairTon=potHairTon;
	}
	/**卸港罐毛吨*/
	public java.math.BigDecimal getPotHairTon(){
		return this.potHairTon;
	}
	
	/**提单短量（%毛桶）*/
	public void setBlHairBarRate(java.math.BigDecimal blHairBarRate){
		this.blHairBarRate=blHairBarRate;
	}
	/**提单短量（%毛桶）*/
	public java.math.BigDecimal getBlHairBarRate(){
		return this.blHairBarRate;
	}
	
	/**提单短量（%毛吨）*/
	public void setBlHairTonRate(java.math.BigDecimal blHairTonRate){
		this.blHairTonRate=blHairTonRate;
	}
	/**提单短量（%毛吨）*/
	public java.math.BigDecimal getBlHairTonRate(){
		return this.blHairTonRate;
	}
	
	/**国检短量（%净吨）*/
	public void setCounCleanBarRate(java.math.BigDecimal counCleanBarRate){
		this.counCleanBarRate=counCleanBarRate;
	}
	/**国检短量（%净吨）*/
	public java.math.BigDecimal getCounCleanBarRate(){
		return this.counCleanBarRate;
	}
	
	/**国检短量（%净桶）*/
	public void setCounCleanTonRate(java.math.BigDecimal counCleanTonRate){
		this.counCleanTonRate=counCleanTonRate;
	}
	/**国检短量（%净桶）*/
	public java.math.BigDecimal getCounCleanTonRate(){
		return this.counCleanTonRate;
	}
	
	/**ROB桶（Reain on   board）*/
	public void setRobQuanatity(java.math.BigDecimal robQuanatity){
		this.robQuanatity=robQuanatity;
	}
	/**ROB桶（Reain on   board）*/
	public java.math.BigDecimal getRobQuanatity(){
		return this.robQuanatity;
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
	
	/**COD*/
	public void setCod(java.util.Date cod){
		this.cod=cod;
	}
	/**COD*/
	public java.util.Date getCod(){
		return this.cod;
	}
	
	/**卸港船毛桶_VEF*/
	public void setShipHairBarVef(java.math.BigDecimal shipHairBarVef){
		this.shipHairBarVef=shipHairBarVef;
	}
	/**卸港船毛桶_VEF*/
	public java.math.BigDecimal getShipHairBarVef(){
		return this.shipHairBarVef;
	}
	
	/**卸港船毛吨_VEF*/
	public void setShipHairTonVef(java.math.BigDecimal shipHairTonVef){
		this.shipHairTonVef=shipHairTonVef;
	}
	/**卸港船毛吨_VEF*/
	public java.math.BigDecimal getShipHairTonVef(){
		return this.shipHairTonVef;
	}
	
	/**API*/
	public void setApi(java.math.BigDecimal api){
		this.api=api;
	}
	/**API*/
	public java.math.BigDecimal getApi(){
		return this.api;
	}
	
	/**水杂含量*/
	public void setWaterImpCon(java.math.BigDecimal waterImpCon){
		this.waterImpCon=waterImpCon;
	}
	/**水杂含量*/
	public java.math.BigDecimal getWaterImpCon(){
		return this.waterImpCon;
	}
	
	/**装港明水数量*/
	public void setWaterQuantity(java.math.BigDecimal waterQuantity){
		this.waterQuantity=waterQuantity;
	}
	/**装港明水数量*/
	public java.math.BigDecimal getWaterQuantity(){
		return this.waterQuantity;
	}
	
	/**船量毛体积*/
	public void setShGrossCubicMeter(java.math.BigDecimal shGrossCubicMeter){
		this.shGrossCubicMeter=shGrossCubicMeter;
	}
	/**船量毛体积*/
	public java.math.BigDecimal getShGrossCubicMeter(){
		return this.shGrossCubicMeter;
	}
	
	/**船量净体积*/
	public void setShNetCubicMeter(java.math.BigDecimal shNetCubicMeter){
		this.shNetCubicMeter=shNetCubicMeter;
	}
	/**船量净体积*/
	public java.math.BigDecimal getShNetCubicMeter(){
		return this.shNetCubicMeter;
	}
	
	/**船量密度*/
	public void setShDensity(java.math.BigDecimal shDensity){
		this.shDensity=shDensity;
	}
	/**船量密度*/
	public java.math.BigDecimal getShDensity(){
		return this.shDensity;
	}
	
	/**附件名称*/
	public void setAccessoryName(String accessoryName){
		this.accessoryName=accessoryName;
	}
	/**附件名称*/
	public String getAccessoryName(){
		return this.accessoryName;
	}
	
	/**附件路径*/
	public void setAccessoryPath(String accessoryPath){
		this.accessoryPath=accessoryPath;
	}
	/**附件路径*/
	public String getAccessoryPath(){
		return this.accessoryPath;
	}
	public String getUnloadPortEn() {
		return unloadPortEn;
	}
	public void setUnloadPortEn(String unloadPortEn) {
		this.unloadPortEn = unloadPortEn;
	}
	public String getUnloadPortCode() {
		return unloadPortCode;
	}
	public void setUnloadPortCode(String unloadPortCode) {
		this.unloadPortCode = unloadPortCode;
	}
	public String getOilTypeEn() {
		return oilTypeEn;
	}
	public void setOilTypeEn(String oilTypeEn) {
		this.oilTypeEn = oilTypeEn;
	}
	public String getOilTypeCode() {
		return oilTypeCode;
	}
	public void setOilTypeCode(String oilTypeCode) {
		this.oilTypeCode = oilTypeCode;
	}
		
	/*public Map<String, Object> toMap() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("disburdenId",this.disburdenId);
	map.put("uuid",this.uuid);
	map.put("unloadPortUuid",this.unloadPortUuid);
	map.put("unloadPort",this.unloadPort);
	map.put("oilType",this.oilType);
	map.put("agreementCode",this.agreementCode);
	map.put("palletId",this.palletId);
	map.put("palletUuid",this.palletUuid);
	map.put("waybillUuid",this.waybillUuid);
	map.put("waybillId",this.waybillId);
	map.put("epMemberId",this.epMemberId);
	map.put("agreementUuid",this.agreementUuid);
	map.put("agreementId",this.agreementId);
	map.put("shipPactUuid",this.shipPactUuid);
	map.put("shipPactId",this.shipPactId);
	map.put("shipHairBar",this.shipHairBar);
	map.put("shipHairTon",this.shipHairTon);
	map.put("commHairBar",this.commHairBar);
	map.put("commHairTon",this.commHairTon);
	map.put("commCleanBar",this.commCleanBar);
	map.put("commCleanTon",this.commCleanTon);
	map.put("counCleanBar",this.counCleanBar);
	map.put("counCleanTon",this.counCleanTon);
	map.put("potHairBar",this.potHairBar);
	map.put("potHairTon",this.potHairTon);
	map.put("blHairBarRate",this.blHairBarRate);
	map.put("blHairTonRate",this.blHairTonRate);
	map.put("counCleanBarRate",this.counCleanBarRate);
	map.put("counCleanTonRate",this.counCleanTonRate);
	map.put("robQuanatity",this.robQuanatity);
	map.put("remark",this.remark);
	map.put("aliveFlag",this.aliveFlag);
	map.put("version",this.version);
	map.put("langVer",this.langVer);
	map.put("createDate",this.createDate);
	map.put("updateDate",this.updateDate);
	map.put("createUser",this.createUser);
	map.put("updateUser",this.updateUser);
	map.put("ext1",this.ext1);
	map.put("cod",this.cod);
	map.put("shipHairBarVef",this.shipHairBarVef);
	map.put("shipHairTonVef",this.shipHairTonVef);
	map.put("api",this.api);
	map.put("waterImpCon",this.waterImpCon);
	map.put("waterQuantity",this.waterQuantity);
	map.put("shGrossCubicMeter",this.shGrossCubicMeter);
	map.put("shNetCubicMeter",this.shNetCubicMeter);
	map.put("shDensity",this.shDensity);
	map.put("accessoryName",this.accessoryName);
	map.put("accessoryPath",this.accessoryPath);
			return map;
	}*/
}