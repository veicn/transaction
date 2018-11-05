package org.geojson.domain;

import java.io.Serializable;
import java.util.List;

public class ProductOilWayPointInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6974278583718790327L;

	
	private List<WayPointInfo> waypoint;


	public List<WayPointInfo> getWaypoint() {
		return waypoint;
	}


	public void setWaypoint(List<WayPointInfo> waypoint) {
		this.waypoint = waypoint;
	}
	
}
