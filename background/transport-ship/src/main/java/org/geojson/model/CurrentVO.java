package org.geojson.model;

import java.io.Serializable;

public class CurrentVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4745094179002727374L;
	
	private String endTime; 
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
