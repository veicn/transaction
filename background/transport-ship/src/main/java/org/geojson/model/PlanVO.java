package org.geojson.model;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class PlanVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1753051155693201352L;
	
	private String dwtMaxValue;
	private String dwtMinValue;
	private String sizeType;
	private String speed;
	private List<Route> route;
	@JSONField(serialize=false)  
	private String fromPortStartTime;//开始港口的时间
	@JSONField(serialize=false)  
	private String timeZone;//开始港口的时区
	
	
	public String getFromPortStartTime() {
		return fromPortStartTime;
	}
	public void setFromPortStartTime(String fromPortStartTime) {
		this.fromPortStartTime = fromPortStartTime;
	}
	public String getDwtMaxValue() {
		return dwtMaxValue;
	}
	
	public String getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
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
	public String getSizeType() {
		return sizeType;
	}
	public void setSizeType(String sizeType) {
		this.sizeType = sizeType;
	}
	public String getSpeed() {
		return speed;
	}
	public void setSpeed(String speed) {
		this.speed = speed;
	}
	public List<Route> getRoute() {
		return route;
	}
	public void setRoute(List<Route> route) {
		this.route = route;
	}
	
	

}
