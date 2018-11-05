package org.geojson.domain;

import java.io.Serializable;

/**
 * 船舶信息实体
 * 
 * @author niuwk
 *
 */
public class ShipInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4334850313124482491L;
	
	
	private String beam; //宽
	private String cubic; //立方
	private String mmsi; 
	private String sog; //船速
	private String lon;//经度
	private String imo; 
	private String dest; //目的地
	private String callSign; //船舶呼号
	private String vesselId;
	private String vesselSizeCn;
	private String eta;
	private String draught;//吃水
	private String receivedTime;//接收时间
	private String draft;//满载吃水
	private String sdwt;//夏季载重量
	private String lat;//纬度
	private String vesselName;//船名
	private String vesselTypeCn;//船型
	//private String vesselTypeEn;//船型
	private String loa;//全长
	private String hullType;//船体结构——船壳类型
	private String build;//建造时间
	private String hog;//船首向
	private String status_cn;//状态
	private String status_en;//状态
	private String seaAreaEn;//当前海域
	private String seaAreaCn;//当前海域
	private Flag flag;
	
	
	public Flag getFlag() {
		return flag;
	}
	public void setFlag(Flag flag) {
		this.flag = flag;
	}
	
	public String getSeaAreaEn() {
		return seaAreaEn;
	}
	public void setSeaAreaEn(String seaAreaEn) {
		this.seaAreaEn = seaAreaEn;
	}
	public String getSeaAreaCn() {
		return seaAreaCn;
	}
	public void setSeaAreaCn(String seaAreaCn) {
		this.seaAreaCn = seaAreaCn;
	}
	public String getBeam() {
		return beam;
	}
	/*public String getVesselTypeEn() {
		return vesselTypeEn;
	}
	public void setVesselTypeEn(String vesselTypeEn) {
		this.vesselTypeEn = vesselTypeEn;
	}*/
	public String getBuild() {
		return build;
	}
	public void setBuild(String build) {
		this.build = build;
	}
	public String getHog() {
		return hog;
	}
	public void setHog(String hog) {
		this.hog = hog;
	}
	
	public String getStatus_cn() {
		return status_cn;
	}
	public void setStatus_cn(String status_cn) {
		this.status_cn = status_cn;
	}
	public String getStatus_en() {
		return status_en;
	}
	public void setStatus_en(String status_en) {
		this.status_en = status_en;
	}
	public void setBeam(String beam) {
		this.beam = beam;
	}
	public String getCubic() {
		return cubic;
	}
	public void setCubic(String cubic) {
		this.cubic = cubic;
	}
	public String getMmsi() {
		return mmsi;
	}
	public void setMmsi(String mmsi) {
		this.mmsi = mmsi;
	}
	public String getSog() {
		return sog;
	}
	public void setSog(String sog) {
		this.sog = sog;
	}
	public String getLon() {
		return lon;
	}
	public void setLon(String lon) {
		this.lon = lon;
	}
	public String getImo() {
		return imo;
	}
	public void setImo(String imo) {
		this.imo = imo;
	}
	public String getDest() {
		return dest;
	}
	public void setDest(String dest) {
		this.dest = dest;
	}
	public String getCallSign() {
		return callSign;
	}
	public void setCallSign(String callSign) {
		this.callSign = callSign;
	}
	public String getVesselId() {
		return vesselId;
	}
	public void setVesselId(String vesselId) {
		this.vesselId = vesselId;
	}
	public String getVesselSizeCn() {
		return vesselSizeCn;
	}
	public void setVesselSizeCn(String vesselSizeCn) {
		this.vesselSizeCn = vesselSizeCn;
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
	public String getReceivedTime() {
		return receivedTime;
	}
	public void setReceivedTime(String receivedTime) {
		this.receivedTime = receivedTime;
	}
	public String getDraft() {
		return draft;
	}
	public void setDraft(String draft) {
		this.draft = draft;
	}
	public String getSdwt() {
		return sdwt;
	}
	public void setSdwt(String sdwt) {
		this.sdwt = sdwt;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getVesselName() {
		return vesselName;
	}
	public void setVesselName(String vesselName) {
		this.vesselName = vesselName;
	}
	public String getVesselTypeCn() {
		return vesselTypeCn;
	}
	public void setVesselTypeCn(String vesselTypeCn) {
		this.vesselTypeCn = vesselTypeCn;
	}
	public String getLoa() {
		return loa;
	}
	public void setLoa(String loa) {
		this.loa = loa;
	}
	public String getHullType() {
		return hullType;
	}
	public void setHullType(String hullType) {
		this.hullType = hullType;
	}
	
	

	
	
}
