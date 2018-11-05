package org.geojson.model;

import java.io.Serializable;
import java.util.List;

public class PlanCurrentVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7560898813968210681L;
	
	private String mmsi;
	private String speed;
	private String fromPort;
	private List<Route> route;
	
	public String getMmsi() {
		return mmsi;
	}
	public void setMmsi(String mmsi) {
		this.mmsi = mmsi;
	}
	public String getSpeed() {
		return speed;
	}
	public void setSpeed(String speed) {
		this.speed = speed;
	}
	public String getFromPort() {
		return fromPort;
	}
	public void setFromPort(String fromPort) {
		this.fromPort = fromPort;
	}
	public List<Route> getRoute() {
		return route;
	}
	public void setRoute(List<Route> route) {
		this.route = route;
	}
	

}
