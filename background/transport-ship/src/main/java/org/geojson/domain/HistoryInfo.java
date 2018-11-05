package org.geojson.domain;

import java.io.Serializable;

public class HistoryInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String atd;
	private String mmsi;
	private String draughtIn;
	private String counrtyCn;
	private String imo;
	private String draughtOut;
	private String shipName;
	private String portNameCn;
	private String workTimePort;
	private String portNameEn;
	private String ata;
	private String workTimeDoc;
	public String getAtd() {
		return atd;
	}
	public void setAtd(String atd) {
		this.atd = atd;
	}
	public String getMmsi() {
		return mmsi;
	}
	public void setMmsi(String mmsi) {
		this.mmsi = mmsi;
	}
	public String getDraughtIn() {
		return draughtIn;
	}
	public void setDraughtIn(String draughtIn) {
		this.draughtIn = draughtIn;
	}
	public String getCounrtyCn() {
		return counrtyCn;
	}
	public void setCounrtyCn(String counrtyCn) {
		this.counrtyCn = counrtyCn;
	}
	public String getImo() {
		return imo;
	}
	public void setImo(String imo) {
		this.imo = imo;
	}
	public String getDraughtOut() {
		return draughtOut;
	}
	public void setDraughtOut(String draughtOut) {
		this.draughtOut = draughtOut;
	}
	public String getShipName() {
		return shipName;
	}
	public void setShipName(String shipName) {
		this.shipName = shipName;
	}
	public String getPortNameCn() {
		return portNameCn;
	}
	public void setPortNameCn(String portNameCn) {
		this.portNameCn = portNameCn;
	}
	public String getWorkTimePort() {
		return workTimePort;
	}
	public void setWorkTimePort(String workTimePort) {
		this.workTimePort = workTimePort;
	}
	public String getPortNameEn() {
		return portNameEn;
	}
	public void setPortNameEn(String portNameEn) {
		this.portNameEn = portNameEn;
	}
	public String getAta() {
		return ata;
	}
	public void setAta(String ata) {
		this.ata = ata;
	}
	public String getWorkTimeDoc() {
		return workTimeDoc;
	}
	public void setWorkTimeDoc(String workTimeDoc) {
		this.workTimeDoc = workTimeDoc;
	}
	
	
	

}
