package com.sinochem.crude.trade.transport.model.res;


public class LoadTransit implements Comparable<LoadTransit> {

	
	/**路径*/
	private String path;
	
	/**字段名称*/
	private String name;  
	
	/**时间*/
	private java.util.Date time;

	/**时间字符串*/
	private String times;
	 
	/**1图片2视频*/
	private String type;
	
	
	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
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
	public int compareTo(LoadTransit o) {
		return o.getTime().compareTo(this.time);
	}  
}