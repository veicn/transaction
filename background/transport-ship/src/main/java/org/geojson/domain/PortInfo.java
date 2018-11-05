package org.geojson.domain;

import java.io.Serializable;

/**
 * 港口信息实体
 * 
 * @author niuwk
 *
 */
public class PortInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8835791959175633101L;
	
	private String portId;
	private String shipName;
	private String shipTypeCn;
	private String shipTypeEn;
	private String destCn;
	private String destEn;
	private String startCn;
	private String startEn;
	private String sog;
	private String draught;
	private String eta;
	private String lasttime;
	public String getPortId() {
		return portId;
	}
	public void setPortId(String portId) {
		this.portId = portId;
	}
	public String getShipName() {
		return shipName;
	}
	public void setShipName(String shipName) {
		this.shipName = shipName;
	}
	public String getShipTypeCn() {
		return shipTypeCn;
	}
	public void setShipTypeCn(String shipTypeCn) {
		this.shipTypeCn = shipTypeCn;
	}
	public String getShipTypeEn() {
		return shipTypeEn;
	}
	public void setShipTypeEn(String shipTypeEn) {
		this.shipTypeEn = shipTypeEn;
	}
	public String getDestCn() {
		return destCn;
	}
	public void setDestCn(String destCn) {
		this.destCn = destCn;
	}
	public String getDestEn() {
		return destEn;
	}
	public void setDestEn(String destEn) {
		this.destEn = destEn;
	}
	public String getStartCn() {
		return startCn;
	}
	public void setStartCn(String startCn) {
		this.startCn = startCn;
	}
	public String getStartEn() {
		return startEn;
	}
	public void setStartEn(String startEn) {
		this.startEn = startEn;
	}
	public String getSog() {
		return sog;
	}
	public void setSog(String sog) {
		this.sog = sog;
	}
	public String getDraught() {
		return draught;
	}
	public void setDraught(String draught) {
		this.draught = draught;
	}
	public String getEta() {
		return eta;
	}
	public void setEta(String eta) {
		this.eta = eta;
	}
	public String getLasttime() {
		return lasttime;
	}
	public void setLasttime(String lasttime) {
		this.lasttime = lasttime;
	}
	
	
	

	
	
	
}
