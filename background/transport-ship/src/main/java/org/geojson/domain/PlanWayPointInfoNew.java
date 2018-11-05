package org.geojson.domain;

import java.io.Serializable;
/**
 * 航速航线轨迹信息实体
 * 
 * @author niuwk
 *
 */
public class PlanWayPointInfoNew implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9116671287657556375L;

	private double lng; 
	private double lat;
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	
	
}
