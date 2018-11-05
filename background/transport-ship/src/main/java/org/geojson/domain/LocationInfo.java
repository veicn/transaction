package org.geojson.domain;

import java.io.Serializable;


/**
 * 港口信息实体
 * @author niuwk
 *
 */
public class LocationInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6844944964337571561L;
	private String type;
	private String imo;
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
	private double heading;
	private double lat;
	private double lon;
	
	
	
	public double getHeading() {
		return heading;
	}
	public void setHeading(double heading) {
		this.heading = heading;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getImo() {
		return imo;
	}
	public void setImo(String imo) {
		this.imo = imo;
	}
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
