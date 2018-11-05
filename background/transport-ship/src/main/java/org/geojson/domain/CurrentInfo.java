package org.geojson.domain;

import java.io.Serializable;

/**
 * 历史航线信息结果
 * @author niuwk
 *
 */
public class CurrentInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1753781339860048707L;
	
	
	private String imo; 
	private String mmsi; 
	private double lon; 
	private String shipName; 
	private String sizeTypeEn; 
	private String fromPortCn; 
	private String sizeTypeCn; 
	private String fromPortEn; 
	private String ata; 
	private String draught; 
	private String draft; 
	private double sdwt; 
	private double lat;
	private double heading;
	
	
	public double getHeading() {
		return heading;
	}
	public void setHeading(double heading) {
		this.heading = heading;
	}
	public String getImo() {
		return imo;
	}
	public void setImo(String imo) {
		this.imo = imo;
	}
	public String getMmsi() {
		return mmsi;
	}
	public void setMmsi(String mmsi) {
		this.mmsi = mmsi;
	}
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	public String getShipName() {
		return shipName;
	}
	public void setShipName(String shipName) {
		this.shipName = shipName;
	}
	public String getSizeTypeEn() {
		return sizeTypeEn;
	}
	public void setSizeTypeEn(String sizeTypeEn) {
		this.sizeTypeEn = sizeTypeEn;
	}
	public String getFromPortCn() {
		return fromPortCn;
	}
	public void setFromPortCn(String fromPortCn) {
		this.fromPortCn = fromPortCn;
	}
	public String getSizeTypeCn() {
		return sizeTypeCn;
	}
	public void setSizeTypeCn(String sizeTypeCn) {
		this.sizeTypeCn = sizeTypeCn;
	}
	public String getFromPortEn() {
		return fromPortEn;
	}
	public void setFromPortEn(String fromPortEn) {
		this.fromPortEn = fromPortEn;
	}
	public String getAta() {
		return ata;
	}
	public void setAta(String ata) {
		this.ata = ata;
	}
	public String getDraught() {
		return draught;
	}
	public void setDraught(String draught) {
		this.draught = draught;
	}
	public String getDraft() {
		return draft;
	}
	public void setDraft(String draft) {
		this.draft = draft;
	}
	public double getSdwt() {
		return sdwt;
	}
	public void setSdwt(double sdwt) {
		this.sdwt = sdwt;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	} 
	

}
