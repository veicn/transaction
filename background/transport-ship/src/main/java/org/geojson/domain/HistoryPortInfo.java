package org.geojson.domain;

import java.io.Serializable;

/**
 * 历史航线信息结果
 * @author niuwk
 *
 */
public class HistoryPortInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5289777534744621648L;
	
	private String vesselName; 
	private String imo; 
	private String mmsi; 
	private String sdwt; 
	private String built; 
	private String vesselSizeTypeCN; 
	private String vesselSizeTypeEN; 
	private String draft; 
	private String draughtIn; 
	private String draughtOut; 
	private String fromPortCN; 
	private String fromPortEN; 
	private String fromCounrtyCN; 
	private String fromCounrtyEN; 
	private String atd; 
	private String subPortsCN; 
	private String subPortsEN;
	private String toPortCN; 
	private String toPortEN;
	private String ata;
	private String navStatusCN;
	private String navStatusEN;
	
	public String getVesselName() {
		return vesselName;
	}
	public void setVesselName(String vesselName) {
		this.vesselName = vesselName;
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
	public String getSdwt() {
		return sdwt;
	}
	public void setSdwt(String sdwt) {
		this.sdwt = sdwt;
	}
	public String getBuilt() {
		return built;
	}
	public void setBuilt(String built) {
		this.built = built;
	}
	public String getVesselSizeTypeCN() {
		return vesselSizeTypeCN;
	}
	public void setVesselSizeTypeCN(String vesselSizeTypeCN) {
		this.vesselSizeTypeCN = vesselSizeTypeCN;
	}
	public String getVesselSizeTypeEN() {
		return vesselSizeTypeEN;
	}
	public void setVesselSizeTypeEN(String vesselSizeTypeEN) {
		this.vesselSizeTypeEN = vesselSizeTypeEN;
	}
	public String getDraft() {
		return draft;
	}
	public void setDraft(String draft) {
		this.draft = draft;
	}
	public String getDraughtIn() {
		return draughtIn;
	}
	public void setDraughtIn(String draughtIn) {
		this.draughtIn = draughtIn;
	}
	public String getDraughtOut() {
		return draughtOut;
	}
	public void setDraughtOut(String draughtOut) {
		this.draughtOut = draughtOut;
	}
	public String getFromPortCN() {
		return fromPortCN;
	}
	public void setFromPortCN(String fromPortCN) {
		this.fromPortCN = fromPortCN;
	}
	public String getFromPortEN() {
		return fromPortEN;
	}
	public void setFromPortEN(String fromPortEN) {
		this.fromPortEN = fromPortEN;
	}
	public String getFromCounrtyCN() {
		return fromCounrtyCN;
	}
	public void setFromCounrtyCN(String fromCounrtyCN) {
		this.fromCounrtyCN = fromCounrtyCN;
	}
	public String getFromCounrtyEN() {
		return fromCounrtyEN;
	}
	public void setFromCounrtyEN(String fromCounrtyEN) {
		this.fromCounrtyEN = fromCounrtyEN;
	}
	public String getAtd() {
		return atd;
	}
	public void setAtd(String atd) {
		this.atd = atd;
	}
	public String getSubPortsCN() {
		return subPortsCN;
	}
	public void setSubPortsCN(String subPortsCN) {
		this.subPortsCN = subPortsCN;
	}
	public String getSubPortsEN() {
		return subPortsEN;
	}
	public void setSubPortsEN(String subPortsEN) {
		this.subPortsEN = subPortsEN;
	}
	public String getToPortCN() {
		return toPortCN;
	}
	public void setToPortCN(String toPortCN) {
		this.toPortCN = toPortCN;
	}
	public String getToPortEN() {
		return toPortEN;
	}
	public void setToPortEN(String toPortEN) {
		this.toPortEN = toPortEN;
	}
	public String getAta() {
		return ata;
	}
	public void setAta(String ata) {
		this.ata = ata;
	}
	public String getNavStatusCN() {
		return navStatusCN;
	}
	public void setNavStatusCN(String navStatusCN) {
		this.navStatusCN = navStatusCN;
	}
	public String getNavStatusEN() {
		return navStatusEN;
	}
	public void setNavStatusEN(String navStatusEN) {
		this.navStatusEN = navStatusEN;
	}
	
	
	

}
