package com.sinochem.crude.trade.transport.model.res;

import java.io.Serializable;
import java.util.List;

import com.sinochem.crude.trade.transport.domain.Appoint;

public class UnloadPortDetail implements Serializable {

	private static final long serialVersionUID = 1L;

	/**图片list*/
	private List<String> imgList;
	
	/**UUID*/
	private String uuid;  
	
	/**代理协议uuid*/
	private String agreementUuid;  
	
	/**代理协议编号*/
	private String agreementCode;  
	
	/**卸港*/
	private String unloadPort;  
	
	/**eta*/
	private java.util.Date eta;  
	
	/**预计靠泊时间*/
	private java.util.Date exBerth;  
	
	/**NOR递交时间*/
	private java.util.Date norDate;  
	
	/**引水上船时间*/
	private java.util.Date waterDate;  
	
	/**预计到港时间*/
	private java.util.Date exArrive;  
	
	/**起锚时间*/
	private java.util.Date atripDate;  
	
	/**靠泊完成时间*/
	private java.util.Date berthDate;  
	
	/**实际卸货开始时间*/
	private java.util.Date acStart;  
	
	/**实际卸货完成时间*/
	private java.util.Date acFinish;  
	
	/**拆管时间*/
	private java.util.Date remTubeDate;  
	
	/**预计离港时间*/
	private java.util.Date exLeave;  
	
	/**实际离港时间*/
	private java.util.Date acLeave;  
	
	/**备注*/
	private String remark;  
	
	/**协议指定*/
	private List<Appoint> list; 
	
	/**油种*/
	private String oilType;  
	
	/**附件一*/
	private String accessory1;  
	
	/**附件一路径*/
	private String accessory1Path;  

	/** 卸港英文 */
	private String unloadPortEn;

	/** 卸港code */
	private String unloadPortCode;

	/** 油种英文 */
	private String oilTypeEn;

	/** 油种名称code */
	private String oilTypeCode;
	
	
	
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
	
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public List<Appoint> getList() {
		return list;
	}
	public void setList(List<Appoint> list) {
		this.list = list;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	
	/**预计到港时间*/
	public void setExArrive(java.util.Date exArrive){
		this.exArrive=exArrive;
	}
	/**预计到港时间*/
	public java.util.Date getExArrive(){
		return this.exArrive;
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
	
	/**实际卸货开始时间*/
	public void setAcStart(java.util.Date acStart){
		this.acStart=acStart;
	}
	/**实际卸货开始时间*/
	public java.util.Date getAcStart(){
		return this.acStart;
	}
	
	/**实际卸货完成时间*/
	public void setAcFinish(java.util.Date acFinish){
		this.acFinish=acFinish;
	}
	/**实际卸货完成时间*/
	public java.util.Date getAcFinish(){
		return this.acFinish;
	}
	
	/**拆管时间*/
	public void setRemTubeDate(java.util.Date remTubeDate){
		this.remTubeDate=remTubeDate;
	}
	/**拆管时间*/
	public java.util.Date getRemTubeDate(){
		return this.remTubeDate;
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
	public String getUnloadPort() {
		return unloadPort;
	}
	public void setUnloadPort(String unloadPort) {
		this.unloadPort = unloadPort;
	}
	public java.util.Date getEta() {
		return eta;
	}
	public void setEta(java.util.Date eta) {
		this.eta = eta;
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
	
}