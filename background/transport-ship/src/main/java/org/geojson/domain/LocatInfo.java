package org.geojson.domain;

import java.io.Serializable;

public class LocatInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6835142512153703069L;
	
	private double lon;
	private double lat;
	private double heading;
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
	public double getHeading() {
		return heading;
	}
	public void setHeading(double heading) {
		this.heading = heading;
	}
	
	

}
