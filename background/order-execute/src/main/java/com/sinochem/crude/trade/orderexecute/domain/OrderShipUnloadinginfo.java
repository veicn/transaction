package com.sinochem.crude.trade.orderexecute.domain;

import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class OrderShipUnloadinginfo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**业务唯一键*/
	private Long id;  
	
	/**UUID*/
	private String uuid;  
	
	/***/
	private String orderNo;  
	
	/**运单uuid*/
	private String waybillUuid;  
	
	/**卸港*/
	private String unloadPort;  
	
	/**油种*/
	private String oil;  
	
	/**eta*/
	private java.util.Date eta;  
	
	/**预计靠泊时间*/
	private java.util.Date exBerth;  
	
	/**NOR递交时间*/
	private java.util.Date norDate;  
	
	/**NOR状态*/
	private String norStatus;  
	
	/**COD*/
	private java.util.Date cod;  
	
	/**卸货完成状态*/
	private String codStatus;  
	
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
	
	/**商检公司ID*/
	private Long inspectionMemberid;  
	
	/**码头*/
	private String inspectionTerminal;  
	
	/**商检联系人*/
	private String inspectionContact;  
	
	/**商检联系人邮箱*/
	private String inspectionEmail;  
	
	/**卸港商检指定联系方式*/
	private String inspectionTel;  
	
	/**商检详情*/
	private String inspectionContent;  
	
	/**卸港监卸*/
	private String monitor;  
	
	/**卸港监卸联系方式*/
	private String monitorTel;  
	
	/**卸港船代*/
	private String agency;  
	
	/**联系方卸港船代联系方式*/
	private String agencyTel;  
	
	/**公司id*/
	private Long epMemberId;  
	
	/**卸港船毛桶*/
	private java.math.BigDecimal shipHairBar;  
	
	/**卸港船毛吨*/
	private java.math.BigDecimal shipHairTon;  
	
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
	
		/**业务唯一键*/
	public void setId(Long id){
		this.id=id;
	}
	/**业务唯一键*/
	public Long getId(){
		return this.id;
	}
	
	/**UUID*/
	public void setUuid(String uuid){
		this.uuid=uuid;
	}
	/**UUID*/
	public String getUuid(){
		return this.uuid;
	}
	
	/***/
	public void setOrderNo(String orderNo){
		this.orderNo=orderNo;
	}
	/***/
	public String getOrderNo(){
		return this.orderNo;
	}
	
	/**运单uuid*/
	public void setWaybillUuid(String waybillUuid){
		this.waybillUuid=waybillUuid;
	}
	/**运单uuid*/
	public String getWaybillUuid(){
		return this.waybillUuid;
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
	public void setOil(String oil){
		this.oil=oil;
	}
	/**油种*/
	public String getOil(){
		return this.oil;
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
	
	/**NOR状态*/
	public void setNorStatus(String norStatus){
		this.norStatus=norStatus;
	}
	/**NOR状态*/
	public String getNorStatus(){
		return this.norStatus;
	}
	
	/**COD*/
	public void setCod(java.util.Date cod){
		this.cod=cod;
	}
	/**COD*/
	public java.util.Date getCod(){
		return this.cod;
	}
	
	/**卸货完成状态*/
	public void setCodStatus(String codStatus){
		this.codStatus=codStatus;
	}
	/**卸货完成状态*/
	public String getCodStatus(){
		return this.codStatus;
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
	
	/**商检公司ID*/
	public void setInspectionMemberid(Long inspectionMemberid){
		this.inspectionMemberid=inspectionMemberid;
	}
	/**商检公司ID*/
	public Long getInspectionMemberid(){
		return this.inspectionMemberid;
	}
	
	/**码头*/
	public void setInspectionTerminal(String inspectionTerminal){
		this.inspectionTerminal=inspectionTerminal;
	}
	/**码头*/
	public String getInspectionTerminal(){
		return this.inspectionTerminal;
	}
	
	/**商检联系人*/
	public void setInspectionContact(String inspectionContact){
		this.inspectionContact=inspectionContact;
	}
	/**商检联系人*/
	public String getInspectionContact(){
		return this.inspectionContact;
	}
	
	/**商检联系人邮箱*/
	public void setInspectionEmail(String inspectionEmail){
		this.inspectionEmail=inspectionEmail;
	}
	/**商检联系人邮箱*/
	public String getInspectionEmail(){
		return this.inspectionEmail;
	}
	
	/**卸港商检指定联系方式*/
	public void setInspectionTel(String inspectionTel){
		this.inspectionTel=inspectionTel;
	}
	/**卸港商检指定联系方式*/
	public String getInspectionTel(){
		return this.inspectionTel;
	}
	
	/**商检详情*/
	public void setInspectionContent(String inspectionContent){
		this.inspectionContent=inspectionContent;
	}
	/**商检详情*/
	public String getInspectionContent(){
		return this.inspectionContent;
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
	
	/**公司id*/
	public void setEpMemberId(Long epMemberId){
		this.epMemberId=epMemberId;
	}
	/**公司id*/
	public Long getEpMemberId(){
		return this.epMemberId;
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
	
		
	
	public Map<String, Object> toMap() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id",this.id);
	map.put("uuid",this.uuid);
	map.put("orderNo",this.orderNo);
	map.put("waybillUuid",this.waybillUuid);
	map.put("unloadPort",this.unloadPort);
	map.put("oil",this.oil);
	map.put("eta",this.eta);
	map.put("exBerth",this.exBerth);
	map.put("norDate",this.norDate);
	map.put("norStatus",this.norStatus);
	map.put("cod",this.cod);
	map.put("codStatus",this.codStatus);
	map.put("waterDate",this.waterDate);
	map.put("exArrive",this.exArrive);
	map.put("atripDate",this.atripDate);
	map.put("berthDate",this.berthDate);
	map.put("acStart",this.acStart);
	map.put("acFinish",this.acFinish);
	map.put("remTubeDate",this.remTubeDate);
	map.put("exLeave",this.exLeave);
	map.put("acLeave",this.acLeave);
	map.put("inspection",this.inspection);
	map.put("inspectionMemberid",this.inspectionMemberid);
	map.put("inspectionTerminal",this.inspectionTerminal);
	map.put("inspectionContact",this.inspectionContact);
	map.put("inspectionEmail",this.inspectionEmail);
	map.put("inspectionTel",this.inspectionTel);
	map.put("inspectionContent",this.inspectionContent);
	map.put("monitor",this.monitor);
	map.put("monitorTel",this.monitorTel);
	map.put("agency",this.agency);
	map.put("agencyTel",this.agencyTel);
	map.put("epMemberId",this.epMemberId);
	map.put("shipHairBar",this.shipHairBar);
	map.put("shipHairTon",this.shipHairTon);
	map.put("shipHairBarVef",this.shipHairBarVef);
	map.put("shipHairTonVef",this.shipHairTonVef);
	map.put("api",this.api);
	map.put("waterImpCon",this.waterImpCon);
	map.put("waterQuantity",this.waterQuantity);
	map.put("shGrossCubicMeter",this.shGrossCubicMeter);
	map.put("shNetCubicMeter",this.shNetCubicMeter);
	map.put("shDensity",this.shDensity);
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
			return map;
	}
}