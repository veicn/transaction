package org.geojson.domain;

import java.io.Serializable;
/**
 * 航速航线轨迹信息实体
 * 
 * @author niuwk
 *
 */
public class PlanWayPointInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9116671287657556375L;

	//private String receiveTime; 
	private double lon; 
	private double lat;
	//private String speed;
	
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	
	
	
}
