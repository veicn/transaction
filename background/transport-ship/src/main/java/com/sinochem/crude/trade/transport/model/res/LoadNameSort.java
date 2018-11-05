package com.sinochem.crude.trade.transport.model.res;


public class LoadNameSort implements Comparable<LoadNameSort> {

	
	/**星期*/
	private String week;
	
	/**字段名称*/
	private String name;  
	
	/**时间*/
	private java.util.Date time;

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public java.util.Date getTime() {
		return time;
	}

	public void setTime(java.util.Date time) {
		this.time = time;
	}
	@Override
	public int compareTo(LoadNameSort o) {
		return this.time.compareTo(o.getTime());
	}  
}