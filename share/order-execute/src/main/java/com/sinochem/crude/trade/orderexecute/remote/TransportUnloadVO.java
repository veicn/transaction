package com.sinochem.crude.trade.orderexecute.remote;

import java.io.Serializable;

/**
 * 卸港信息
 * @author wangxinran
 *
 */
public class TransportUnloadVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5702466247335566477L;

	/**订单编号*/
	private String orderNo;
	
	/**卸港*/
	private String unloadPort;  
	/**油种*/
	private String oilType; 
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
	
	/**卸港商检指定*/
	private String inspection;  
	
	/**卸港商检指定联系方式*/
	private String inspectionTel;  
	
	/**卸港监卸*/
	private String monitor;  
	
	/**卸港监卸联系方式*/
	private String monitorTel;  
	
	/**卸港船代*/
	private String agency;  
	
	/**联系方卸港船代联系方式*/
	private String agencyTel;
	
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

	/** 代理协议UUID */
	private String argeementUuid;

	/** 船舶类型 */
	private String type;

	/** 船舶名称 */
	private String name;
	
	public String getOilType() {
		return oilType;
	}
	public void setOilType(String oilType) {
		this.oilType = oilType;
	}
	/**订单编号*/
	public void setOrderNo(String orderNo){
		this.orderNo=orderNo;
	}
	/**订单编号*/
	public String getOrderNo(){
		return this.orderNo;
	}
	
	/**卸港*/
	public void setUnloadPort(String unloadPort){
		this.unloadPort=unloadPort;
	}
	/**卸港*/
	public String getUnloadPort(){
		return this.unloadPort;
	}
	
	/**eta*/
	public void setEta(java.util.Date eta){
		this.eta=eta;
	}
	/**eta*/
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
	
	/**卸港商检指定*/
	public void setInspection(String inspection){
		this.inspection=inspection;
	}
	/**卸港商检指定*/
	public String getInspection(){
		return this.inspection;
	}
	
	/**卸港商检指定联系方式*/
	public void setInspectionTel(String inspectionTel){
		this.inspectionTel=inspectionTel;
	}
	/**卸港商检指定联系方式*/
	public String getInspectionTel(){
		return this.inspectionTel;
	}
	
	/**卸港监卸*/
	public void setMonitor(String monitor){
		this.monitor=monitor;
	}
	/**卸港监卸*/
	public String getMonitor(){
		return this.monitor;
	}
	
	/**卸港监卸联系方式*/
	public void setMonitorTel(String monitorTel){
		this.monitorTel=monitorTel;
	}
	/**卸港监卸联系方式*/
	public String getMonitorTel(){
		return this.monitorTel;
	}
	
	/**卸港船代*/
	public void setAgency(String agency){
		this.agency=agency;
	}
	/**卸港船代*/
	public String getAgency(){
		return this.agency;
	}
	
	/**联系方卸港船代联系方式*/
	public void setAgencyTel(String agencyTel){
		this.agencyTel=agencyTel;
	}
	/**联系方卸港船代联系方式*/
	public String getAgencyTel(){
		return this.agencyTel;
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
	
	/** 代理协议UUID */
	public void setArgeementUuid(String argeementUuid) {
		this.argeementUuid = argeementUuid;
	}

	/** 代理协议UUID */
	public String getArgeementUuid() {
		return this.argeementUuid;
	}

	/** 船舶类型 */
	public void setType(String type) {
		this.type = type;
	}

	/** 船舶类型 */
	public String getType() {
		return this.type;
	}

	/** 船舶名称 */
	public void setName(String name) {
		this.name = name;
	}

	/** 船舶名称 */
	public String getName() {
		return this.name;
	}
}
