package org.geojson.domain;

import java.io.Serializable;
import java.util.List;

public class PlanCurrentDataData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 395914935690187295L;

	private List<PlanData> data;
	private LocatInfo locat;
	private List<HtmlInfo> history;
	private String speed;
	
	
	
	public LocatInfo getLocat() {
		return locat;
	}
	public void setLocat(LocatInfo locat) {
		this.locat = locat;
	}
	
	
	public List<HtmlInfo> getHistory() {
		return history;
	}
	public void setHistory(List<HtmlInfo> history) {
		this.history = history;
	}
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
