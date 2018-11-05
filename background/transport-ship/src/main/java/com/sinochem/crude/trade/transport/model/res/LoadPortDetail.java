package com.sinochem.crude.trade.transport.model.res;

import java.io.Serializable;
import java.util.List;

public class LoadPortDetail implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**图片list*/
	private List<String> imgList;
	
	/**装港*/
	private String loadPort;  
	
	/**ETA*/
	private java.util.Date eta;  
	
	/**预计靠泊时间*/
	private java.util.Date exBerth;  
	
	/**NOR递交时间*/
	private java.util.Date norDate;  
	
	/**引水上船时间*/
	private java.util.Date waterDate;  
	
	/**起锚时间*/
	private java.util.Date atripDate;  
	
	/**靠泊完成时间*/
	private java.util.Date berthDate;  
	
	/**实际装货开始时间*/
	private java.util.Date acStart;  
	
	/**实际装货完成时间*/
	private java.util.Date acFinish;  
	
	/**预计离港时间*/
	private java.util.Date exLeave;  
	
	/**实际离港时间*/
	private java.util.Date acLeave;  
	
	/**拆管时间*/
	private java.util.Date remTubeDate;  
	
	/**装港商检*/
	private String inspection;  
	
	/**装港船代*/
	private String agency;  
	
	/**代理协议uuid*/
	private String agreementUuid;  
	
	/**代理协议编号*/
	private String agreementCode;  
	
	/**油种*/
	private String oilType;  
	
	/**附件一*/
	private String accessory1;  
	
	/**附件一路径*/
	private String accessory1Path;  

	/** 装港英文 */
	private String loadPortEn;

	/** 装港code */
	private String loadPortCode;

	/** 油种英文 */
	private String oilTypeEn;

	/** 油种名称code */
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

	public List<String> getImgList() {
		return imgList;
	}

	public void setImgList(List<String> imgList) {
		this.imgList = imgList;
	}

	public String getAccessory1() {
		return accessory1;
	}

	public void setAccessory1(String accessory1) {
		this.accessory1 = accessory1;
	}

	public String getAccessory1Path() {
		return accessory1Path;
	}

	public void setAccessory1Path(String accessory1Path) {
		this.accessory1Path = accessory1Path;
	}

	public String getOilType() {
		return oilType;
	}

	public void setOilType(String oilType) {
		this.oilType = oilType;
	}

	public String getAgreementUuid() {
		return agreementUuid;
	}

	public void setAgreementUuid(String agreementUuid) {
		this.agreementUuid = agreementUuid;
	}

	public String getAgreementCode() {
		return agreementCode;
	}

	public void setAgreementCode(String agreementCode) {
		this.agreementCode = agreementCode;
	}

	/**装港商检*/
	public String getInspection() {
		return inspection;
	}
	
	/**装港商检*/
	public void setInspection(String inspection) {
		this.inspection = inspection;
	}
	
	/**装港船代*/
	public String getAgency() {
		return agency;
	}
	
	/**装港船代*/
	public void setAgency(String agency) {
		this.agency = agency;
	}
	/**装港*/
	public void setLoadPort(String loadPort){
		this.loadPort=loadPort;
	}
	/**装港*/
	public String getLoadPort(){
		return this.loadPort;
	}
	
	/**ETA*/
	public void setEta(java.util.Date eta){
		this.eta=eta;
	}
	/**ETA*/
	public java.util.Date getEta(){
		return this.eta;
	}
	
	/**预计靠泊时间*/
	public void setExBerth(java.util.Date exBerth){
		this.exBerth=exBerth;
	}
	/**预计靠泊时间*/
	public java.util.Date getExBerth(){
		return this.exBerth;
	}
	
	/**NOR递交时间*/
	public void setNorDate(java.util.Date norDate){
		this.norDate=norDate;
	}
	/**NOR递交时间*/
	public java.util.Date getNorDate(){
		return this.norDate;
	}
	
	/**引水上船时间*/
	public void setWaterDate(java.util.Date waterDate){
		this.waterDate=waterDate;
	}
	/**引水上船时间*/
	public java.util.Date getWaterDate(){
		return this.waterDate;
	}
	
	/**起锚时间*/
	public void setAtripDate(java.util.Date atripDate){
		this.atripDate=atripDate;
	}
	/**起锚时间*/
	public java.util.Date getAtripDate(){
		return this.atripDate;
	}
	
	/**靠泊完成时间*/
	public void setBerthDate(java.util.Date berthDate){
		this.berthDate=berthDate;
	}
	/**靠泊完成时间*/
	public java.util.Date getBerthDate(){
		return this.berthDate;
	}
	
	/**实际装货开始时间*/
	public void setAcStart(java.util.Date acStart){
		this.acStart=acStart;
	}
	/**实际装货开始时间*/
	public java.util.Date getAcStart(){
		return this.acStart;
	}
	
	/**实际装货完成时间*/
	public void setAcFinish(java.util.Date acFinish){
		this.acFinish=acFinish;
	}
	/**实际装货完成时间*/
	public java.util.Date getAcFinish(){
		return this.acFinish;
	}
	
	/**预计离港时间*/
	public void setExLeave(java.util.Date exLeave){
		this.exLeave=exLeave;
	}
	/**预计离港时间*/
	public java.util.Date getExLeave(){
		return this.exLeave;
	}
	
	/**实际离港时间*/
	public void setAcLeave(java.util.Date acLeave){
		this.acLeave=acLeave;
	}
	/**实际离港时间*/
	public java.util.Date getAcLeave(){
		return this.acLeave;
	}
	
	/**拆管时间*/
	public void setRemTubeDate(java.util.Date remTubeDate){
		this.remTubeDate=remTubeDate;
	}
	/**拆管时间*/
	public java.util.Date getRemTubeDate(){
		return this.remTubeDate;
	}
	
	
}