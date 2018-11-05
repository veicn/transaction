package com.sinochem.crude.trade.orderexecute.domain;

import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class OrderTransport implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**ID*/
	private Long orderTransportId;  
	
	/**UUID*/
	private String orderTransportUuid;  
	
	/**ORDER_ID*/
	private Long orderId;  
	
	/**船型*/
	private String shipModel;  
	
	/**装货港*/
	private String loadingPort;  
	
	/**卸货港*/
	private String uploadingPort;  
	
	/**装期开始*/
	private java.util.Date deliveryDateStart;  
	
	/**装期结束*/
	private java.util.Date deliveryDateEnd;  
	
	/**到货开始*/
	private java.util.Date dischargeDateStart;  
	
	/**到货结束*/
	private java.util.Date dischargeDateEnd;  
	
	/**创建人*/
	private Long createUser;  
	
	/**当前有效状态*/
	private String aliveFlag;  
	
	/**语言类型*/
	private String langVer;  
	
	/**创建时间*/
	private java.util.Date createDate;  
	
	/**修改人*/
	private Long updateUser;  
	
	/**修改时间*/
	private java.util.Date updateDate;  
	
	/**版本号*/
	private Integer version;  
	
		/**ID*/
	public void setOrderTransportId(Long orderTransportId){
		this.orderTransportId=orderTransportId;
	}
	/**ID*/
	public Long getOrderTransportId(){
		return this.orderTransportId;
	}
	
	/**UUID*/
	public void setOrderTransportUuid(String orderTransportUuid){
		this.orderTransportUuid=orderTransportUuid;
	}
	/**UUID*/
	public String getOrderTransportUuid(){
		return this.orderTransportUuid;
	}
	
	/**ORDER_ID*/
	public void setOrderId(Long orderId){
		this.orderId=orderId;
	}
	/**ORDER_ID*/
	public Long getOrderId(){
		return this.orderId;
	}
	
	/**船型*/
	public void setShipModel(String shipModel){
		this.shipModel=shipModel;
	}
	/**船型*/
	public String getShipModel(){
		return this.shipModel;
	}
	
	/**装货港*/
	public void setLoadingPort(String loadingPort){
		this.loadingPort=loadingPort;
	}
	/**装货港*/
	public String getLoadingPort(){
		return this.loadingPort;
	}
	
	/**卸货港*/
	public void setUploadingPort(String uploadingPort){
		this.uploadingPort=uploadingPort;
	}
	/**卸货港*/
	public String getUploadingPort(){
		return this.uploadingPort;
	}
	
	/**装期开始*/
	public void setDeliveryDateStart(java.util.Date deliveryDateStart){
		this.deliveryDateStart=deliveryDateStart;
	}
	/**装期开始*/
	public java.util.Date getDeliveryDateStart(){
		return this.deliveryDateStart;
	}
	
	/**装期结束*/
	public void setDeliveryDateEnd(java.util.Date deliveryDateEnd){
		this.deliveryDateEnd=deliveryDateEnd;
	}
	/**装期结束*/
	public java.util.Date getDeliveryDateEnd(){
		return this.deliveryDateEnd;
	}
	
	/**到货开始*/
	public void setDischargeDateStart(java.util.Date dischargeDateStart){
		this.dischargeDateStart=dischargeDateStart;
	}
	/**到货开始*/
	public java.util.Date getDischargeDateStart(){
		return this.dischargeDateStart;
	}
	
	/**到货结束*/
	public void setDischargeDateEnd(java.util.Date dischargeDateEnd){
		this.dischargeDateEnd=dischargeDateEnd;
	}
	/**到货结束*/
	public java.util.Date getDischargeDateEnd(){
		return this.dischargeDateEnd;
	}
	
	/**创建人*/
	public void setCreateUser(Long createUser){
		this.createUser=createUser;
	}
	/**创建人*/
	public Long getCreateUser(){
		return this.createUser;
	}
	
	/**当前有效状态*/
	public void setAliveFlag(String aliveFlag){
		this.aliveFlag=aliveFlag;
	}
	/**当前有效状态*/
	public String getAliveFlag(){
		return this.aliveFlag;
	}
	
	/**语言类型*/
	public void setLangVer(String langVer){
		this.langVer=langVer;
	}
	/**语言类型*/
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
	
	/**修改人*/
	public void setUpdateUser(Long updateUser){
		this.updateUser=updateUser;
	}
	/**修改人*/
	public Long getUpdateUser(){
		return this.updateUser;
	}
	
	/**修改时间*/
	public void setUpdateDate(java.util.Date updateDate){
		this.updateDate=updateDate;
	}
	/**修改时间*/
	public java.util.Date getUpdateDate(){
		return this.updateDate;
	}
	
	/**版本号*/
	public void setVersion(Integer version){
		this.version=version;
	}
	/**版本号*/
	public Integer getVersion(){
		return this.version;
	}
	
		
	
	public Map<String, Object> toMap() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("orderTransportId",this.orderTransportId);
	map.put("orderTransportUuid",this.orderTransportUuid);
	map.put("orderId",this.orderId);
	map.put("shipModel",this.shipModel);
	map.put("loadingPort",this.loadingPort);
	map.put("uploadingPort",this.uploadingPort);
	map.put("deliveryDateStart",this.deliveryDateStart);
	map.put("deliveryDateEnd",this.deliveryDateEnd);
	map.put("dischargeDateStart",this.dischargeDateStart);
	map.put("dischargeDateEnd",this.dischargeDateEnd);
	map.put("createUser",this.createUser);
	map.put("aliveFlag",this.aliveFlag);
	map.put("langVer",this.langVer);
	map.put("createDate",this.createDate);
	map.put("updateUser",this.updateUser);
	map.put("updateDate",this.updateDate);
	map.put("version",this.version);
			return map;
	}
}