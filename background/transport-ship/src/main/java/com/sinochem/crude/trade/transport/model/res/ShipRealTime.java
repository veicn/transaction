package com.sinochem.crude.trade.transport.model.res;

import java.io.Serializable;

public class ShipRealTime implements Serializable {

	private static final long serialVersionUID = 1L;
	/**船名*/
	private String shipName;  
	
	/**装港*/
	private String loadPort;  
	
	/**装港ETA*/
	private String loadPortETA; 
	
	/**装港实际离港时间*/
	private String loadPortLEAVEDATE;  
	
	/**卸港*/
	private String unloadPort;  
	
	/**卸港ETA*/
	private String unloadPortETA; 
	
	/**卸港实际离港时间*/
	private String unloadPortLEAVEDATE;  
	
	/**实际卸货完成时间*/
	private String acFinish;
	
	
	
	public String getAcFinish() {
		return acFinish;
	}
	public void setAcFinish(String acFinish) {
		this.acFinish = acFinish;
	}
	/**第一装港*/
	public void setLoadPort(String loadPort){
		this.loadPort=loadPort;
	}
	/**第一装港*/
	public String getLoadPort(){
		return this.loadPort;
	}
	
	/**船名*/
	public void setShipName(String shipName){
		this.shipName=shipName;
	}
	/**船名*/
	public String getShipName(){
		return this.shipName;
	}
	public String getLoadPortETA() {
		return loadPortETA;
	}
	public void setLoadPortETA(String loadPortETA) {
		this.loadPortETA = loadPortETA;
	}
	public String getLoadPortLEAVEDATE() {
		return loadPortLEAVEDATE;
	}
	public void setLoadPortLEAVEDATE(String loadPortLEAVEDATE) {
		this.loadPortLEAVEDATE = loadPortLEAVEDATE;
	}
	public String getUnloadPort() {
		return unloadPort;
	}
	public void setUnloadPort(String unloadPort) {
		this.unloadPort = unloadPort;
	}
	public String getUnloadPortETA() {
		return unloadPortETA;
	}
	public void setUnloadPortETA(String unloadPortETA) {
		this.unloadPortETA = unloadPortETA;
	}
	public String getUnloadPortLEAVEDATE() {
		return unloadPortLEAVEDATE;
	}
	public void setUnloadPortLEAVEDATE(String unloadPortLEAVEDATE) {
		this.unloadPortLEAVEDATE = unloadPortLEAVEDATE;
	}
	
	
}