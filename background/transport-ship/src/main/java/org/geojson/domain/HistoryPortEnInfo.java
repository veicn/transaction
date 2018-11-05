package org.geojson.domain;

import java.io.Serializable;

/**
 * 历史到港信息结果
 * @author niuwk
 *
 */
public class HistoryPortEnInfo implements Serializable{


	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4547390982696329895L;
	
	
	private String imo; 
	private String mmsi; 
	private String lon; 
	private String shipName; 
	private String ata; 
	private String vesselSizeCn; 
	private String atd; 
	private String vesselSizeEn; 
	private String maxDraught; 
	private String draft;
	private String draughtIn; 
	private String lastPortEn; 
	private String draughtOut; 
	private String lastPortCn; 
	private double sdwt; 
	private String portNameCn; 
	private String lat;
	private String portNameEn;
	
	
	
	public String getDraft() {
		return draft;
	}
	public void setDraft(String draft) {
		this.draft = draft;
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
	public String getLon() {
		return lon;
	}
	public void setLon(String lon) {
		this.lon = lon;
	}
	public String getShipName() {
		return shipName;
	}
	public void setShipName(String shipName) {
		this.shipName = shipName;
	}
	public String getAta() {
		return ata;
	}
	public void setAta(String ata) {
		this.ata = ata;
	}
	public String getVesselSizeCn() {
		return vesselSizeCn;
	}
	public void setVesselSizeCn(String vesselSizeCn) {
		this.vesselSizeCn = vesselSizeCn;
	}
	public String getAtd() {
		return atd;
	}
	public void setAtd(String atd) {
		this.atd = atd;
	}
	public String getVesselSizeEn() {
		return vesselSizeEn;
	}
	public void setVesselSizeEn(String vesselSizeEn) {
		this.vesselSizeEn = vesselSizeEn;
	}
	public String getMaxDraught() {
		return maxDraught;
	}
	public void setMaxDraught(String maxDraught) {
		this.maxDraught = maxDraught;
	}
	public String getDraughtIn() {
		return draughtIn;
	}
	public void setDraughtIn(String draughtIn) {
		this.draughtIn = draughtIn;
	}
	public String getLastPortEn() {
		return lastPortEn;
	}
	public void setLastPortEn(String lastPortEn) {
		this.lastPortEn = lastPortEn;
	}
	public String getDraughtOut() {
		return draughtOut;
	}
	public void setDraughtOut(String draughtOut) {
		this.draughtOut = draughtOut;
	}
	public String getLastPortCn() {
		return lastPortCn;
	}
	public void setLastPortCn(String lastPortCn) {
		this.lastPortCn = lastPortCn;
	}
	public double getSdwt() {
		return sdwt;
	}
	public void setSdwt(double sdwt) {
		this.sdwt = sdwt;
	}
	public String getPortNameCn() {
		return portNameCn;
	}
	public void setPortNameCn(String portNameCn) {
		this.portNameCn = portNameCn;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getPortNameEn() {
		return portNameEn;
	}
	public void setPortNameEn(String portNameEn) {
		this.portNameEn = portNameEn;
	} 
	
	
	
	

}
