package org.geojson.domain;

import java.io.Serializable;

/**
 * 船舶信息实体
 *  
 * @author niuwk
 *
 */
public class AllShipInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5391055454679793778L;
	
	private double draught;//吃水
	private String mmsi;
	private double heading;
	private String shipTypeCn;//船型
	private String imo; 
	private double lon;//经度
	private String shipTypeEn;
	private String shipName;//船名
	private double lat;//纬度
	private double speed; //船速
	
	public double getDraught() {
		return draught;
	}
	public void setDraught(double draught) {
		this.draught = draught;
	}
	public String getMmsi() {
		return mmsi;
	}
	public void setMmsi(String mmsi) {
		this.mmsi = mmsi;
	}
	public double getHeading() {
		return heading;
	}
	public void setHeading(double heading) {
		this.heading = heading;
	}
	public String getShipTypeCn() {
		return shipTypeCn;
	}
	public void setShipTypeCn(String shipTypeCn) {
		this.shipTypeCn = shipTypeCn;
	}
	public String getImo() {
		return imo;
	}
	public void setImo(String imo) {
		this.imo = imo;
	}
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	public String getShipTypeEn() {
		return shipTypeEn;
	}
	public void setShipTypeEn(String shipTypeEn) {
		this.shipTypeEn = shipTypeEn;
	}
	public String getShipName() {
		return shipName;
	}
	public void setShipName(String shipName) {
		this.shipName = shipName;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	
	
}
