package com.sinochem.crude.trade.transport.model.res;

import java.io.Serializable;

public class ShipmentDetail implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**业务唯一键*/
	private Long shipmentId;  
	
	/**UUID*/
	private String uuid;  
	
	/**船装港uuid*/
	private String loadPortUuid;  
	
	/**装港*/
	private String loadPort;  
	
	/**货盘id*/
	private Long palletId;  
	
	/**货盘uuid*/
	private String palletUuid;  
	
	/**运单uuid*/
	private String waybillUuid;  
	
	/**运单id*/
	private Long waybillId;  
	
	/**代理协议uuid*/
	private String agreementUuid;  
	
	/**代理协议code*/
	private String agreementCode;  
	
	/**代理协议id*/
	private Long agreementId;  
	
	/**公司id*/
	private Long epMemberId;  
	
	/**船合同uuid*/
	private String shipPactUuid;  
	
	/**船合同id*/
	private Long shipPactId;  
	
	/**提单日*/
	private java.util.Date blDate;  
	
	/**提单净桶*/
	private java.math.BigDecimal blNightstool;  
	
	/**提单毛桶*/
	private java.math.BigDecimal blHairBarrel;  
	
	/**提单净吨*/
	private java.math.BigDecimal blNetTonnage;  
	
	/**提单毛吨*/
	private java.math.BigDecimal blHairTonnage;  
	
	/**API*/
	private java.math.BigDecimal api;  
	
	/**水杂含量*/
	private java.math.BigDecimal waterImpCon;  
	
	/**装港明水数量*/
	private java.math.BigDecimal waterQuantity;  
	
	/**船量毛桶*/
	private java.math.BigDecimal shHairBarrel;  
	
	/**船量毛吨*/
	private java.math.BigDecimal shHairTonnage;  
	
	/**船量净桶*/
	private java.math.BigDecimal shNetBarrel;  
	
	/**船量净吨*/
	private java.math.BigDecimal shNetTonnage;  
	
	/**短量比例（桶）*/
	private java.math.BigDecimal ratioBarrel;  
	
	/**短量比例（吨）*/
	private java.math.BigDecimal ratioTonnage;  
	
	/**装船数量*/
	private java.math.BigDecimal quantity;  
	
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
	
	/**油种*/
	private String oilType;  
	
	/**是否有订单租船（1是0否）*/
	private String type;  
	
	/**Bill of Lading名称*/
	private String billOfLadingName;  
	
	/**Bill of Lading路径*/
	private String billOfLadingPath;  
	
	/**Certificate of Quantity名称*/
	private String cerOfQuantityName;  
	
	/**Certificate of Quantity路径*/
	private String cerOfQuantityPath;  
	
	/**Certificate of Quality名称*/
	private String cerOfQualityName;  
	
	/**Certificate of Quality路径*/
	private String cerOfQualityPath;  
	
	/**Certificate of Original名称*/
	private String cerOfOriginalName;  
	
	/**Certificate of Original路径*/
	private String cerOfOriginalPath;  
	
	/**Manifest名称*/
	private String manifestName;  
	
	/**Manifest路径*/
	private String manifestPath; 
	
	
	/**船量毛体积*/
	private java.math.BigDecimal shGrossCubicMeter;  
	
	/**船量净体积*/
	private java.math.BigDecimal shNetCubicMeter;  
	
	/**船量密度*/
	private java.math.BigDecimal shDensity;  
	
	/**装港英文*/
	private String loadPortEn;  
	
	/**装港code*/
	private String loadPortCode;  
	
	/**油种英文*/
	private String oilTypeEn;  
	
	/**油种名称code*/
	private String oilTypeCode;  
	
	
	
	
	
	public String getLoadPortEn() {
		return loadPortEn;
	}

	public void setLoadPortEn(String loadPortEn) {
		this.loadPortEn = loadPortEn;
	}

	public String getLoadPortCode() {
		return loadPortCode;
	}

	public void setLoadPortCode(String loadPortCode) {
		this.loadPortCode = loadPortCode;
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

	public java.math.BigDecimal getShGrossCubicMeter() {
		return shGrossCubicMeter;
	}

	public void setShGrossCubicMeter(java.math.BigDecimal shGrossCubicMeter) {
		this.shGrossCubicMeter = shGrossCubicMeter;
	}

	public java.math.BigDecimal getShNetCubicMeter() {
		return shNetCubicMeter;
	}

	public void setShNetCubicMeter(java.math.BigDecimal shNetCubicMeter) {
		this.shNetCubicMeter = shNetCubicMeter;
	}

	public java.math.BigDecimal getShDensity() {
		return shDensity;
	}

	public void setShDensity(java.math.BigDecimal shDensity) {
		this.shDensity = shDensity;
	}

	public String getBillOfLadingName() {
		return billOfLadingName;
	}

	public void setBillOfLadingName(String billOfLadingName) {
		this.billOfLadingName = billOfLadingName;
	}

	public String getBillOfLadingPath() {
		return billOfLadingPath;
	}

	public void setBillOfLadingPath(String billOfLadingPath) {
		this.billOfLadingPath = billOfLadingPath;
	}

	public String getCerOfQuantityName() {
		return cerOfQuantityName;
	}

	public void setCerOfQuantityName(String cerOfQuantityName) {
		this.cerOfQuantityName = cerOfQuantityName;
	}

	public String getCerOfQuantityPath() {
		return cerOfQuantityPath;
	}

	public void setCerOfQuantityPath(String cerOfQuantityPath) {
		this.cerOfQuantityPath = cerOfQuantityPath;
	}

	public String getCerOfQualityName() {
		return cerOfQualityName;
	}

	public void setCerOfQualityName(String cerOfQualityName) {
		this.cerOfQualityName = cerOfQualityName;
	}

	public String getCerOfQualityPath() {
		return cerOfQualityPath;
	}

	public void setCerOfQualityPath(String cerOfQualityPath) {
		this.cerOfQualityPath = cerOfQualityPath;
	}

	public String getCerOfOriginalName() {
		return cerOfOriginalName;
	}

	public void setCerOfOriginalName(String cerOfOriginalName) {
		this.cerOfOriginalName = cerOfOriginalName;
	}

	public String getCerOfOriginalPath() {
		return cerOfOriginalPath;
	}

	public void setCerOfOriginalPath(String cerOfOriginalPath) {
		this.cerOfOriginalPath = cerOfOriginalPath;
	}

	public String getManifestName() {
		return manifestName;
	}

	public void setManifestName(String manifestName) {
		this.manifestName = manifestName;
	}

	public String getManifestPath() {
		return manifestPath;
	}

	public void setManifestPath(String manifestPath) {
		this.manifestPath = manifestPath;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOilType() {
		return oilType;
	}

	public void setOilType(String oilType) {
		this.oilType = oilType;
	}
	
	
	public String getAgreementCode() {
		return agreementCode;
	}
	public void setAgreementCode(String agreementCode) {
		this.agreementCode = agreementCode;
	}
		/**业务唯一键*/
	public void setShipmentId(Long shipmentId){
		this.shipmentId=shipmentId;
	}
	/**业务唯一键*/
	public Long getShipmentId(){
		return this.shipmentId;
	}
	
	/**UUID*/
	public void setUuid(String uuid){
		this.uuid=uuid;
	}
	/**UUID*/
	public String getUuid(){
		return this.uuid;
	}
	
	/**船装港uuid*/
	public void setLoadPortUuid(String loadPortUuid){
		this.loadPortUuid=loadPortUuid;
	}
	/**船装港uuid*/
	public String getLoadPortUuid(){
		return this.loadPortUuid;
	}
	
	/**装港*/
	public void setLoadPort(String loadPort){
		this.loadPort=loadPort;
	}
	/**装港*/
	public String getLoadPort(){
		return this.loadPort;
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
	
	/**公司id*/
	public void setEpMemberId(Long epMemberId){
		this.epMemberId=epMemberId;
	}
	/**公司id*/
	public Long getEpMemberId(){
		return this.epMemberId;
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
	
	/**提单日*/
	public void setBlDate(java.util.Date blDate){
		this.blDate=blDate;
	}
	/**提单日*/
	public java.util.Date getBlDate(){
		return this.blDate;
	}
	
	/**提单净桶*/
	public void setBlNightstool(java.math.BigDecimal blNightstool){
		this.blNightstool=blNightstool;
	}
	/**提单净桶*/
	public java.math.BigDecimal getBlNightstool(){
		return this.blNightstool;
	}
	
	/**提单毛桶*/
	public void setBlHairBarrel(java.math.BigDecimal blHairBarrel){
		this.blHairBarrel=blHairBarrel;
	}
	/**提单毛桶*/
	public java.math.BigDecimal getBlHairBarrel(){
		return this.blHairBarrel;
	}
	
	/**提单净吨*/
	public void setBlNetTonnage(java.math.BigDecimal blNetTonnage){
		this.blNetTonnage=blNetTonnage;
	}
	/**提单净吨*/
	public java.math.BigDecimal getBlNetTonnage(){
		return this.blNetTonnage;
	}
	
	/**提单毛吨*/
	public void setBlHairTonnage(java.math.BigDecimal blHairTonnage){
		this.blHairTonnage=blHairTonnage;
	}
	/**提单毛吨*/
	public java.math.BigDecimal getBlHairTonnage(){
		return this.blHairTonnage;
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
	
	/**船量毛桶*/
	public void setShHairBarrel(java.math.BigDecimal shHairBarrel){
		this.shHairBarrel=shHairBarrel;
	}
	/**船量毛桶*/
	public java.math.BigDecimal getShHairBarrel(){
		return this.shHairBarrel;
	}
	
	/**船量毛吨*/
	public void setShHairTonnage(java.math.BigDecimal shHairTonnage){
		this.shHairTonnage=shHairTonnage;
	}
	/**船量毛吨*/
	public java.math.BigDecimal getShHairTonnage(){
		return this.shHairTonnage;
	}
	
	/**船量净桶*/
	public void setShNetBarrel(java.math.BigDecimal shNetBarrel){
		this.shNetBarrel=shNetBarrel;
	}
	/**船量净桶*/
	public java.math.BigDecimal getShNetBarrel(){
		return this.shNetBarrel;
	}
	
	/**船量净吨*/
	public void setShNetTonnage(java.math.BigDecimal shNetTonnage){
		this.shNetTonnage=shNetTonnage;
	}
	/**船量净吨*/
	public java.math.BigDecimal getShNetTonnage(){
		return this.shNetTonnage;
	}
	
	/**短量比例（桶）*/
	public void setRatioBarrel(java.math.BigDecimal ratioBarrel){
		this.ratioBarrel=ratioBarrel;
	}
	/**短量比例（桶）*/
	public java.math.BigDecimal getRatioBarrel(){
		return this.ratioBarrel;
	}
	
	/**短量比例（吨）*/
	public void setRatioTonnage(java.math.BigDecimal ratioTonnage){
		this.ratioTonnage=ratioTonnage;
	}
	/**短量比例（吨）*/
	public java.math.BigDecimal getRatioTonnage(){
		return this.ratioTonnage;
	}
	
	/**装船数量*/
	public void setQuantity(java.math.BigDecimal quantity){
		this.quantity=quantity;
	}
	/**装船数量*/
	public java.math.BigDecimal getQuantity(){
		return this.quantity;
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
	
		
	
	/*public Map<String, Object> toMap() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("shipmentId",this.shipmentId);
	map.put("uuid",this.uuid);
	map.put("loadPortUuid",this.loadPortUuid);
	map.put("loadPort",this.loadPort);
	map.put("palletId",this.palletId);
	map.put("palletUuid",this.palletUuid);
	map.put("waybillUuid",this.waybillUuid);
	map.put("waybillId",this.waybillId);
	map.put("agreementUuid",this.agreementUuid);
	map.put("agreementId",this.agreementId);
	map.put("epMemberId",this.epMemberId);
	map.put("shipPactUuid",this.shipPactUuid);
	map.put("shipPactId",this.shipPactId);
	map.put("blDate",this.blDate);
	map.put("blNightstool",this.blNightstool);
	map.put("blHairBarrel",this.blHairBarrel);
	map.put("blNetTonnage",this.blNetTonnage);
	map.put("blHairTonnage",this.blHairTonnage);
	map.put("api",this.api);
	map.put("waterImpCon",this.waterImpCon);
	map.put("waterQuantity",this.waterQuantity);
	map.put("shHairBarrel",this.shHairBarrel);
	map.put("shHairTonnage",this.shHairTonnage);
	map.put("shNetBarrel",this.shNetBarrel);
	map.put("shNetTonnage",this.shNetTonnage);
	map.put("ratioBarrel",this.ratioBarrel);
	map.put("ratioTonnage",this.ratioTonnage);
	map.put("quantity",this.quantity);
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