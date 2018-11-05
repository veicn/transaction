package org.geojson.model;

import java.io.Serializable;

public class ExpectedVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3432558902904362028L;
	
	private String endTime; 
	private String naviStatus; 
	private String pageIndex;
	private String pageSize;
	private String portNameEn;
	private String startTime;
	private String sizeType;
	private String dwtMaxValue;
	private String dwtMinValue;
	
	
	public String getDwtMaxValue() {
		return dwtMaxValue;
	}
	public void setDwtMaxValue(String dwtMaxValue) {
		this.dwtMaxValue = dwtMaxValue;
	}
	public String getDwtMinValue() {
		return dwtMinValue;
	}
	public void setDwtMinValue(String dwtMinValue) {
		this.dwtMinValue = dwtMinValue;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getNaviStatus() {
		return naviStatus;
	}
	public void setNaviStatus(String naviStatus) {
		this.naviStatus = naviStatus;
	}
	public String getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(String pageIndex) {
		this.pageIndex = pageIndex;
	}
	public String getPageSize() {
		return pageSize;
	}
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	public String getPortNameEn() {
		return portNameEn;
	}
	public void setPortNameEn(String portNameEn) {
		this.portNameEn = portNameEn;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getSizeType() {
		return sizeType;
	}
	public void setSizeType(String sizeType) {
		this.sizeType = sizeType;
	}

	
}
