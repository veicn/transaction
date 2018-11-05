package org.geojson.model;

import java.io.Serializable;

/**
 * 封装船舶信息查询条件
 * @author niuwk
 *
 */
public class ShipVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5098764248302059731L;
	
	private String name; //船名
	private String imo; 
	private String mmsi;
	private String keyword;//多字段模糊匹配   不能精确查询时候使用 
	private String callbackparam;//JSONP回调
	private String startTime;
	private String endTime;
	
	private String port;
	private String status;
	private String type;
	private String deadWeight;
	private String naviStatus;
	private String sdwt;
	private String edwt;
	
	private int pageSize;
	private int pageIndex;
	

	public String getSdwt() {
		return sdwt;
	}
	public void setSdwt(String sdwt) {
		this.sdwt = sdwt;
	}
	public String getEdwt() {
		return edwt;
	}
	public void setEdwt(String edwt) {
		this.edwt = edwt;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDeadWeight() {
		return deadWeight;
	}
	public void setDeadWeight(String deadWeight) {
		this.deadWeight = deadWeight;
	}
	public String getNaviStatus() {
		return naviStatus;
	}
	public void setNaviStatus(String naviStatus) {
		this.naviStatus = naviStatus;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getCallbackparam() {
		return callbackparam;
	}
	public void setCallbackparam(String callbackparam) {
		this.callbackparam = callbackparam;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	
	
	
	
}
