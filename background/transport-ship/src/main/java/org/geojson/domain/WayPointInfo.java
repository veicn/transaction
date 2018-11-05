package org.geojson.domain;

import java.io.Serializable;
/**
 * 船舶轨迹信息实体
 * 
 * @author niuwk
 *
 */
public class WayPointInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9116671287657556375L;

	private String receivedTime; 
	private String lon; 
	private String lat;
	private String cog;
	
	
	public String getCog() {
		return cog;
	}
	public void setCog(String cog) {
		this.cog = cog;
	}
	public String getReceivedTime() {
		return receivedTime;
	}
	public void setReceivedTime(String receivedTime) {
		this.receivedTime = receivedTime;
	}
	public String getLon() {
		return lon;
	}
	public void setLon(String lon) {
		this.lon = lon;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	} 
	
	
	
	
	
}
