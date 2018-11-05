package org.geojson.domain;

import java.io.Serializable;

/**
 * 靠港信息实体
 * 
 * @author niuwk
 *
 */
public class DockInfo implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6668557471880568749L;
	
	private String dockId; 
	private String imo; 
	private String mmsi; 
	private String shipName; 
	private String shipTypeCn; 
	private String shipTypeEn; 
	private String portNameCn; 
	private String portNameEn; 
	private String countryCn; 
	private String countryEn; 
	private String arrivalDraught; 
	private String departureDraught; 
	private String ata; 
	private String atd; 
	private String stayinport; 
	private String stayinterminal;
	
	public String getDockId() {
		return dockId;
	}
	public void setDockId(String dockId) {
		this.dockId = dockId;
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
	public String getPortNameCn() {
		return portNameCn;
	}
	public void setPortNameCn(String portNameCn) {
		this.portNameCn = portNameCn;
	}
	public String getPortNameEn() {
		return portNameEn;
	}
	public void setPortNameEn(String portNameEn) {
		this.portNameEn = portNameEn;
	}
	public String getCountryCn() {
		return countryCn;
	}
	public void setCountryCn(String countryCn) {
		this.countryCn = countryCn;
	}
	public String getCountryEn() {
		return countryEn;
	}
	public void setCountryEn(String countryEn) {
		this.countryEn = countryEn;
	}
	public String getArrivalDraught() {
		return arrivalDraught;
	}
	public void setArrivalDraught(String arrivalDraught) {
		this.arrivalDraught = arrivalDraught;
	}
	public String getDepartureDraught() {
		return departureDraught;
	}
	public void setDepartureDraught(String departureDraught) {
		this.departureDraught = departureDraught;
	}
	public String getAta() {
		return ata;
	}
	public void setAta(String ata) {
		this.ata = ata;
	}
	public String getAtd() {
		return atd;
	}
	public void setAtd(String atd) {
		this.atd = atd;
	}
	public String getStayinport() {
		return stayinport;
	}
	public void setStayinport(String stayinport) {
		this.stayinport = stayinport;
	}
	public String getStayinterminal() {
		return stayinterminal;
	}
	public void setStayinterminal(String stayinterminal) {
		this.stayinterminal = stayinterminal;
	} 
	
	
}
