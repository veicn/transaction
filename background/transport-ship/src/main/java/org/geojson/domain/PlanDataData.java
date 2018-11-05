package org.geojson.domain;

import java.io.Serializable;
import java.util.List;

public class PlanDataData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 395914935690187295L;

	private List<PlanData> data;
	private String speed;
	
	public List<PlanData> getData() {
		return data;
	}
	public void setData(List<PlanData> data) {
		this.data = data;
	}
	public String getSpeed() {
		return speed;
	}
	public void setSpeed(String speed) {
		this.speed = speed;
	}
	

}
