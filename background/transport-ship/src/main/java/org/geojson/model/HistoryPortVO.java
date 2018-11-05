package org.geojson.model;

import java.io.Serializable;

/**
 * 历史航线查询条件
 * @author niuwk
 *
 */
public class HistoryPortVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9198751846494609605L;
	
	private String endTime; 
	private String fromPort; 
	private String sizeType;
	private String startTime;
	private String timeType;
	private String toPort;
	
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
	public String getFromPort() {
		return fromPort;
	}
	public void setFromPort(String fromPort) {
		this.fromPort = fromPort;
	}
	public String getSizeType() {
		return sizeType;
	}
	public void setSizeType(String sizeType) {
		this.sizeType = sizeType;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getTimeType() {
		return timeType;
	}
	public void setTimeType(String timeType) {
		this.timeType = timeType;
	}
	public String getToPort() {
		return toPort;
	}
	public void setToPort(String toPort) {
		this.toPort = toPort;
	}
	
	
	
}
