package com.sinochem.crude.trade.shipping.domain;

import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class TransitLoading implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**主键_ID*/
	private Long transitLoadingId;  
	
	/**UUID*/
	private String uuid;  
	
	/**船舶确认单ID*/
	private Long shipConfirmationSheetId;  
	
	/**日期*/
	private java.util.Date date;  
	
	/**到达港口*/
	private String loadingPort;  
	
	/**预计到港时间*/
	private java.util.Date eta;  
	
	/**预计离港时间*/
	private java.util.Date etb;  
	
	/**坐标（经纬度）*/
	private String posn;  
	
	/**海况CD*/
	private String seaId;  
	
	/**海况*/
	private String sea;  
	
	/**航速（24小时）*/
	private java.math.BigDecimal aveSpd24h;  
	
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
	
		/**主键_ID*/
	public void setTransitLoadingId(Long transitLoadingId){
		this.transitLoadingId=transitLoadingId;
	}
	/**主键_ID*/
	public Long getTransitLoadingId(){
		return this.transitLoadingId;
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
	
	/**日期*/
	public void setDate(java.util.Date date){
		this.date=date;
	}
	/**日期*/
	public java.util.Date getDate(){
		return this.date;
	}
	
	/**到达港口*/
	public void setLoadingPort(String loadingPort){
		this.loadingPort=loadingPort;
	}
	/**到达港口*/
	public String getLoadingPort(){
		return this.loadingPort;
	}
	
	/**预计到港时间*/
	public void setEta(java.util.Date eta){
		this.eta=eta;
	}
	/**预计到港时间*/
	public java.util.Date getEta(){
		return this.eta;
	}
	
	/**预计离港时间*/
	public void setEtb(java.util.Date etb){
		this.etb=etb;
	}
	/**预计离港时间*/
	public java.util.Date getEtb(){
		return this.etb;
	}
	
	/**坐标（经纬度）*/
	public void setPosn(String posn){
		this.posn=posn;
	}
	/**坐标（经纬度）*/
	public String getPosn(){
		return this.posn;
	}
	
	/**海况CD*/
	public void setSeaId(String seaId){
		this.seaId=seaId;
	}
	/**海况CD*/
	public String getSeaId(){
		return this.seaId;
	}
	
	/**海况*/
	public void setSea(String sea){
		this.sea=sea;
	}
	/**海况*/
	public String getSea(){
		return this.sea;
	}
	
	/**航速（24小时）*/
	public void setAveSpd24h(java.math.BigDecimal aveSpd24h){
		this.aveSpd24h=aveSpd24h;
	}
	/**航速（24小时）*/
	public java.math.BigDecimal getAveSpd24h(){
		return this.aveSpd24h;
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
		map.put("transitLoadingId",this.transitLoadingId);
	map.put("uuid",this.uuid);
	map.put("shipConfirmationSheetId",this.shipConfirmationSheetId);
	map.put("date",this.date);
	map.put("loadingPort",this.loadingPort);
	map.put("eta",this.eta);
	map.put("etb",this.etb);
	map.put("posn",this.posn);
	map.put("seaId",this.seaId);
	map.put("sea",this.sea);
	map.put("aveSpd24h",this.aveSpd24h);
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