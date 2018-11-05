package org.geojson.model;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

public class Route  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1440852328456912394L;
	
	
	private String fromPort;
	private String toPort;
	private String startTime;
	@JSONField(serialize=false) 
	private String speed; 
	@JSONField(serialize=false) 
	private String stayTime; 
	
	
	
	public String getSpeed() {
		return speed;
	}
	public void setSpeed(String speed) {
		this.speed = speed;
	}
	public String getStayTime() {
		return stayTime;
	}
	public void setStayTime(String stayTime) {
		this.stayTime = stayTime;
	}
	public String getFromPort() {
		return fromPort;
	}
	public void setFromPort(String fromPort) {
		this.fromPort = fromPort;
	}
	public String getToPort() {
		return toPort;
	}
	public void setToPort(String toPort) {
		this.toPort = toPort;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	

}
