package com.sinochem.crude.trade.shipping.domain;

import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class VoyageStart implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**主键_ID*/
	private Long voyageStartId;  
	
	/**UUID*/
	private String uuid;  
	
	/**船舶确认单ID*/
	private Long shipConfirmationSheetId;  
	
	/**产品名称*/
	private String productNm;  
	
	/**公吨*/
	private String metricTons;  
	
	/**误差范围*/
	private String rangeOfError;  
	
	/**桶*/
	private String bbls;  
	
	/**计划装货温度 （℉）*/
	private String loadingTemp;  
	
	/**装货港吃水限制*/
	private String draftRestrictionOfLoadingPort;  
	
	/**卸货港吃水限制*/
	private String draftRestrictionOfDischargingPort;  
	
	/**文件名称*/
	private String accessoryFileNm;  
	
	/**附件地址*/
	private String accessory;  
	
	/**预留字段1*/
	private String ext1;  
	
	/**版本号*/
	private String version;  
	
	/**当前有效状态*/
	private String aliveFlag;  
	
	/**创建时间*/
	private java.util.Date createDate;  
	
	/**创建者*/
	private Long createUser;  
	
	/**更新时间*/
	private java.util.Date updateDate;  
	
	/**更新者*/
	private Long updateUser;

	private String diFileNm;

	public String getDiFileNm() {
		return diFileNm;
	}

	public void setDiFileNm(String diFileNm) {
		this.diFileNm = diFileNm;
	}

	public String getDi() {
		return di;
	}

	public void setDi(String di) {
		this.di = di;
	}

	private String di;
	
		/**主键_ID*/
	public void setVoyageStartId(Long voyageStartId){
		this.voyageStartId=voyageStartId;
	}
	/**主键_ID*/
	public Long getVoyageStartId(){
		return this.voyageStartId;
	}
	
	/**UUID*/
	public void setUuid(String uuid){
		this.uuid=uuid;
	}
	/**UUID*/
	public String getUuid(){
		return this.uuid;
	}
	
	/**船舶确认单ID*/
	public void setShipConfirmationSheetId(Long shipConfirmationSheetId){
		this.shipConfirmationSheetId=shipConfirmationSheetId;
	}
	/**船舶确认单ID*/
	public Long getShipConfirmationSheetId(){
		return this.shipConfirmationSheetId;
	}
	
	/**产品名称*/
	public void setProductNm(String productNm){
		this.productNm=productNm;
	}
	/**产品名称*/
	public String getProductNm(){
		return this.productNm;
	}
	
	/**公吨*/
	public void setMetricTons(String metricTons){
		this.metricTons=metricTons;
	}
	/**公吨*/
	public String getMetricTons(){
		return this.metricTons;
	}
	
	/**误差范围*/
	public void setRangeOfError(String rangeOfError){
		this.rangeOfError=rangeOfError;
	}
	/**误差范围*/
	public String getRangeOfError(){
		return this.rangeOfError;
	}
	
	/**桶*/
	public void setBbls(String bbls){
		this.bbls=bbls;
	}
	/**桶*/
	public String getBbls(){
		return this.bbls;
	}
	
	/**计划装货温度 （℉）*/
	public void setLoadingTemp(String loadingTemp){
		this.loadingTemp=loadingTemp;
	}
	/**计划装货温度 （℉）*/
	public String getLoadingTemp(){
		return this.loadingTemp;
	}
	
	/**装货港吃水限制*/
	public void setDraftRestrictionOfLoadingPort(String draftRestrictionOfLoadingPort){
		this.draftRestrictionOfLoadingPort=draftRestrictionOfLoadingPort;
	}
	/**装货港吃水限制*/
	public String getDraftRestrictionOfLoadingPort(){
		return this.draftRestrictionOfLoadingPort;
	}
	
	/**卸货港吃水限制*/
	public void setDraftRestrictionOfDischargingPort(String draftRestrictionOfDischargingPort){
		this.draftRestrictionOfDischargingPort=draftRestrictionOfDischargingPort;
	}
	/**卸货港吃水限制*/
	public String getDraftRestrictionOfDischargingPort(){
		return this.draftRestrictionOfDischargingPort;
	}
	
	/**文件名称*/
	public void setAccessoryFileNm(String accessoryFileNm){
		this.accessoryFileNm=accessoryFileNm;
	}
	/**文件名称*/
	public String getAccessoryFileNm(){
		return this.accessoryFileNm;
	}
	
	/**附件地址*/
	public void setAccessory(String accessory){
		this.accessory=accessory;
	}
	/**附件地址*/
	public String getAccessory(){
		return this.accessory;
	}
	
	/**预留字段1*/
	public void setExt1(String ext1){
		this.ext1=ext1;
	}
	/**预留字段1*/
	public String getExt1(){
		return this.ext1;
	}
	
	/**版本号*/
	public void setVersion(String version){
		this.version=version;
	}
	/**版本号*/
	public String getVersion(){
		return this.version;
	}
	
	/**当前有效状态*/
	public void setAliveFlag(String aliveFlag){
		this.aliveFlag=aliveFlag;
	}
	/**当前有效状态*/
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
	
	/**创建者*/
	public void setCreateUser(Long createUser){
		this.createUser=createUser;
	}
	/**创建者*/
	public Long getCreateUser(){
		return this.createUser;
	}
	
	/**更新时间*/
	public void setUpdateDate(java.util.Date updateDate){
		this.updateDate=updateDate;
	}
	/**更新时间*/
	public java.util.Date getUpdateDate(){
		return this.updateDate;
	}
	
	/**更新者*/
	public void setUpdateUser(Long updateUser){
		this.updateUser=updateUser;
	}
	/**更新者*/
	public Long getUpdateUser(){
		return this.updateUser;
	}
	
		
	
	public Map<String, Object> toMap() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("voyageStartId",this.voyageStartId);
	map.put("uuid",this.uuid);
	map.put("shipConfirmationSheetId",this.shipConfirmationSheetId);
	map.put("productNm",this.productNm);
	map.put("metricTons",this.metricTons);
	map.put("rangeOfError",this.rangeOfError);
	map.put("bbls",this.bbls);
	map.put("loadingTemp",this.loadingTemp);
	map.put("draftRestrictionOfLoadingPort",this.draftRestrictionOfLoadingPort);
	map.put("draftRestrictionOfDischargingPort",this.draftRestrictionOfDischargingPort);
	map.put("accessoryFileNm",this.accessoryFileNm);
	map.put("accessory",this.accessory);
	map.put("ext1",this.ext1);
	map.put("version",this.version);
	map.put("aliveFlag",this.aliveFlag);
	map.put("createDate",this.createDate);
	map.put("createUser",this.createUser);
	map.put("updateDate",this.updateDate);
	map.put("updateUser",this.updateUser);
			return map;
	}
	
}