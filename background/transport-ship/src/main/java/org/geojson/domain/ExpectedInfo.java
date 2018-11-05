package org.geojson.domain;

import java.io.Serializable;

/**
 * 预计到港信息实体
 * 
 * @author niuwk
 *
 */
public class ExpectedInfo  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -309607891191603410L;
	
	private String vesselSizeCn;
	private String receiveTime;
	private String vesselSizeEn;
	private String eta;
	private String draught;
	private String mmsi;
	private String draft;
	private String imo;
	private String lastPortCn;
	private String shipName;
	private String lastPortEn;
	private String speed;
	private double sdwt; 
	
	
	public String getLastPortEn() {
		return lastPortEn;
	}
	public void setLastPortEn(String lastPortEn) {
		this.lastPortEn = lastPortEn;
	}
	public double getSdwt() {
		return sdwt;
	}
	public void setSdwt(double sdwt) {
		this.sdwt = sdwt;
	}
	public String getVesselSizeCn() {
		return vesselSizeCn;
	}
	public void setVesselSizeCn(String vesselSizeCn) {
		this.vesselSizeCn = vesselSizeCn;
	}
	public String getReceiveTime() {
		return receiveTime;
	}
	public void setReceiveTime(String receiveTime) {
		this.receiveTime = receiveTime;
	}
	public String getVesselSizeEn() {
		return vesselSizeEn;
	}
	public void setVesselSizeEn(String vesselSizeEn) {
		this.vesselSizeEn = vesselSizeEn;
	}
	public String getEta() {
		return eta;
	}
	public void setEta(String eta) {
		this.eta = eta;
	}
	public String getDraught() {
		return draught;
	}
	public void setDraught(String draught) {
		this.draught = draught;
	}
	public String getMmsi() {
		return mmsi;
	}
	public void setMmsi(String mmsi) {
		this.mmsi = mmsi;
	}
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
	public String getLastPortCn() {
		return lastPortCn;
	}
	public void setLastPortCn(String lastPortCn) {
		this.lastPortCn = lastPortCn;
	}
	public String getShipName() {
		return shipName;
	}
	public void setShipName(String shipName) {
		this.shipName = shipName;
	}
	public String getSpeed() {
		return speed;
	}
	public void setSpeed(String speed) {
		this.speed = speed;
	}

	

}
