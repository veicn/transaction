package com.sinochem.crude.trade.info.domain;

import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class ShipPointInterface implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**自增主键*/
	private Long id;  
	
	/**UUID*/
	private String uuid;  
	
	/**发货区域名称*/
	private String diliveryRegion;  
	
	/**船型*/
	private String shipType;  
	
	/**点数*/
	private java.math.BigDecimal point;  
	
	/**发布日期*/
	private java.util.Date releaseDate;  
	
	/**发货港名称*/
	private String dispatchPort;  
	
	/**卸货港名称*/
	private String dischargePort;  
	
	/**预估单桶运费*/
	private java.math.BigDecimal bucketFreight;  
	
	/**备注*/
	private String remarks;  
	
	/**外部系统名称*/
	private String externalSystem;  
	
	/**接收日期*/
	private java.util.Date interfaceDate;  
	
	/**处理人*/
	private String processPerson;  
	
	/**处理时间*/
	private java.util.Date processDate;  
	
	/**状态(00：未处理，01：已启用，02：已弃用)*/
	private String status;  
	
	/**数据状态*/
	private String aliveFlag;  
	
		/**自增主键*/
	public void setId(Long id){
		this.id=id;
	}
	/**自增主键*/
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
	
	/**发货区域名称*/
	public void setDiliveryRegion(String diliveryRegion){
		this.diliveryRegion=diliveryRegion;
	}
	/**发货区域名称*/
	public String getDiliveryRegion(){
		return this.diliveryRegion;
	}
	
	/**船型*/
	public void setShipType(String shipType){
		this.shipType=shipType;
	}
	/**船型*/
	public String getShipType(){
		return this.shipType;
	}
	
	/**点数*/
	public void setPoint(java.math.BigDecimal point){
		this.point=point;
	}
	/**点数*/
	public java.math.BigDecimal getPoint(){
		return this.point;
	}
	
	/**发布日期*/
	public void setReleaseDate(java.util.Date releaseDate){
		this.releaseDate=releaseDate;
	}
	/**发布日期*/
	public java.util.Date getReleaseDate(){
		return this.releaseDate;
	}
	
	/**发货港名称*/
	public void setDispatchPort(String dispatchPort){
		this.dispatchPort=dispatchPort;
	}
	/**发货港名称*/
	public String getDispatchPort(){
		return this.dispatchPort;
	}
	
	/**卸货港名称*/
	public void setDischargePort(String dischargePort){
		this.dischargePort=dischargePort;
	}
	/**卸货港名称*/
	public String getDischargePort(){
		return this.dischargePort;
	}
	
	/**预估单桶运费*/
	public void setBucketFreight(java.math.BigDecimal bucketFreight){
		this.bucketFreight=bucketFreight;
	}
	/**预估单桶运费*/
	public java.math.BigDecimal getBucketFreight(){
		return this.bucketFreight;
	}
	
	/**备注*/
	public void setRemarks(String remarks){
		this.remarks=remarks;
	}
	/**备注*/
	public String getRemarks(){
		return this.remarks;
	}
	
	/**外部系统名称*/
	public void setExternalSystem(String externalSystem){
		this.externalSystem=externalSystem;
	}
	/**外部系统名称*/
	public String getExternalSystem(){
		return this.externalSystem;
	}
	
	/**接收日期*/
	public void setInterfaceDate(java.util.Date interfaceDate){
		this.interfaceDate=interfaceDate;
	}
	/**接收日期*/
	public java.util.Date getInterfaceDate(){
		return this.interfaceDate;
	}
	
	/**处理人*/
	public void setProcessPerson(String processPerson){
		this.processPerson=processPerson;
	}
	/**处理人*/
	public String getProcessPerson(){
		return this.processPerson;
	}
	
	/**处理时间*/
	public void setProcessDate(java.util.Date processDate){
		this.processDate=processDate;
	}
	/**处理时间*/
	public java.util.Date getProcessDate(){
		return this.processDate;
	}
	
	/**状态(00：未处理，01：已启用，02：已弃用)*/
	public void setStatus(String status){
		this.status=status;
	}
	/**状态(00：未处理，01：已启用，02：已弃用)*/
	public String getStatus(){
		return this.status;
	}
	
	/**数据状态*/
	public void setAliveFlag(String aliveFlag){
		this.aliveFlag=aliveFlag;
	}
	/**数据状态*/
	public String getAliveFlag(){
		return this.aliveFlag;
	}
	
		
	
	public Map<String, Object> toMap() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id",this.id);
	map.put("uuid",this.uuid);
	map.put("diliveryRegion",this.diliveryRegion);
	map.put("shipType",this.shipType);
	map.put("point",this.point);
	map.put("releaseDate",this.releaseDate);
	map.put("dispatchPort",this.dispatchPort);
	map.put("dischargePort",this.dischargePort);
	map.put("bucketFreight",this.bucketFreight);
	map.put("remarks",this.remarks);
	map.put("externalSystem",this.externalSystem);
	map.put("interfaceDate",this.interfaceDate);
	map.put("processPerson",this.processPerson);
	map.put("processDate",this.processDate);
	map.put("status",this.status);
	map.put("aliveFlag",this.aliveFlag);
			return map;
	}
	
}