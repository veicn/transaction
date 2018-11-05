package com.sinochem.crude.trade.transport.model.res;

import java.io.Serializable;
import java.util.Date;

public class TransitImport implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**日期*/
	private java.util.Date dateNow;  
	
	/**时间*/
	private String timeNow;  
	
	/**位置*/
	private String position;  
	
	/**平均速度（24h）*/
	private String speedH;  
	
	/**平均速度（全程）*/
	private String speedAll;  
	
	/**RPM*/
	private String rpm;  
	
	/**卸港eta（卸港:ETA时间戳;卸港:ETA时间戳;）*/
	private String unloadEta;  
	
	/**海况*/
	private String sea;  
	
	/**明水*/
	private String water;  
	
	/**气相硫化氢*/
	private String sulfide;  
	
	/**备注*/
	private String remark;  
	
	
	/**日期*/
	public void setDateNow(java.util.Date dateNow){
		this.dateNow=dateNow;
	}
	/**日期*/
	public java.util.Date getDateNow(){
		return this.dateNow;
	}
	
	/**时间*/
	public void setTimeNow(String timeNow){
		this.timeNow=timeNow;
	}
	/**时间*/
	public String getTimeNow(){
		return this.timeNow;
	}
	
	/**位置*/
	public void setPosition(String position){
		this.position=position;
	}
	/**位置*/
	public String getPosition(){
		return this.position;
	}
	
	/**平均速度（24h）*/
	public void setSpeedH(String speedH){
		this.speedH=speedH;
	}
	/**平均速度（24h）*/
	public String getSpeedH(){
		return this.speedH;
	}
	
	/**平均速度（全程）*/
	public void setSpeedAll(String speedAll){
		this.speedAll=speedAll;
	}
	/**平均速度（全程）*/
	public String getSpeedAll(){
		return this.speedAll;
	}
	
	/**RPM*/
	public void setRpm(String rpm){
		this.rpm=rpm;
	}
	/**RPM*/
	public String getRpm(){
		return this.rpm;
	}
	
	/**卸港eta（卸港:ETA时间戳;卸港:ETA时间戳;）*/
	public void setUnloadEta(String unloadEta){
		this.unloadEta=unloadEta;
	}
	/**卸港eta（卸港:ETA时间戳;卸港:ETA时间戳;）*/
	public String getUnloadEta(){
		return this.unloadEta;
	}
	
	/**海况*/
	public void setSea(String sea){
		this.sea=sea;
	}
	/**海况*/
	public String getSea(){
		return this.sea;
	}
	
	/**明水*/
	public void setWater(String water){
		this.water=water;
	}
	/**明水*/
	public String getWater(){
		return this.water;
	}
	
	/**气相硫化氢*/
	public void setSulfide(String sulfide){
		this.sulfide=sulfide;
	}
	/**气相硫化氢*/
	public String getSulfide(){
		return this.sulfide;
	}
	
	/**备注*/
	public void setRemark(String remark){
		this.remark=remark;
	}
	/**备注*/
	public String getRemark(){
		return this.remark;
	}
	public TransitImport(Date dateNow, String timeNow, String position,
			String speedH, String speedAll, String rpm,
			String unloadEta, String sea, String water, String sulfide,
			String remark) {
		super();
		this.dateNow = dateNow;
		this.timeNow = timeNow;
		this.position = position;
		this.speedH = speedH;
		this.speedAll = speedAll;
		this.rpm = rpm;
		this.unloadEta = unloadEta;
		this.sea = sea;
		this.water = water;
		this.sulfide = sulfide;
		this.remark = remark;
	}
	
}