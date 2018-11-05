package org.geojson.domain;

import java.io.Serializable;
import java.util.List;

public class PlanData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4302653994003831999L;
	
	private String toNameEn;
	private List<PlanWayPointInfoNew> waypoint;
	private String fromNameCn;
	private String consumingTime;
	private String distance;
	private String recordTime;
	private String fromNameEn;
	private String toNameZone;
	private String fromNameZone;
	private Long startTime;
	private Long endTime;
	private String toNameCn;
	private String speed;
	
	private String dateStartStr;
	private String dateEndStr;
	private String stayTime;
	
	
	
	
	public String getStayTime() {
		return stayTime;
	}
	public void setStayTime(String stayTime) {
		this.stayTime = stayTime;
	}
	public String getDateStartStr() {
		return dateStartStr;
	}
	public void setDateStartStr(String dateStartStr) {
		this.dateStartStr = dateStartStr;
	}
	public String getDateEndStr() {
		return dateEndStr;
	}
	public void setDateEndStr(String dateEndStr) {
		this.dateEndStr = dateEndStr;
	}
	public String getFromNameZone() {
		return fromNameZone;
	}
	public void setFromNameZone(String fromNameZone) {
		this.fromNameZone = fromNameZone;
	}
	public String getToNameEn() {
		return toNameEn;
	}
	public void setToNameEn(String toNameEn) {
		this.toNameEn = toNameEn;
	}
	public List<PlanWayPointInfoNew> getWaypoint() {
		return waypoint;
	}
	public void setWaypoint(List<PlanWayPointInfoNew> waypoint) {
		this.waypoint = waypoint;
	}
	public String getFromNameCn() {
		return fromNameCn;
	}
	public void setFromNameCn(String fromNameCn) {
		this.fromNameCn = fromNameCn;
	}
	public String getConsumingTime() {
		return consumingTime;
	}
	public void setConsumingTime(String consumingTime) {
		this.consumingTime = consumingTime;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public String getRecordTime() {
		return recordTime;
	}
	public void setRecordTime(String recordTime) {
		this.recordTime = recordTime;
	}
	public String getFromNameEn() {
		return fromNameEn;
	}
	public void setFromNameEn(String fromNameEn) {
		this.fromNameEn = fromNameEn;
	}
	public String getToNameZone() {
		return toNameZone;
	}
	public void setToNameZone(String toNameZone) {
		this.toNameZone = toNameZone;
	}
	public Long getStartTime() {
		return startTime;
	}
	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}
	public Long getEndTime() {
		return endTime;
	}
	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}
	public String getToNameCn() {
		return toNameCn;
	}
	public void setToNameCn(String toNameCn) {
		this.toNameCn = toNameCn;
	}
	public String getSpeed() {
		return speed;
	}
	public void setSpeed(String speed) {
		this.speed = speed;
	}
	
	

}
