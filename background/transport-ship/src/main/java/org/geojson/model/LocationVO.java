package org.geojson.model;

import java.io.Serializable;

/**
 * 港口信息查询条件
 * @author niuwk
 *
 */
public class LocationVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5098764248302059731L;
	
	private String portName; 
	private String naviStatus; 
	private String endTime;
	private String type;
	private String deadWeight;
	
	private int pageSize;
	private int pageIndex;
	public String getPortName() {
		return portName;
	}
	public void setPortName(String portName) {
		this.portName = portName;
	}
	public String getNaviStatus() {
		return naviStatus;
	}
	public void setNaviStatus(String naviStatus) {
		this.naviStatus = naviStatus;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
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
	
	
	
	
	
}
