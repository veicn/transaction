package org.geojson.domain;

import java.io.Serializable;

public class LocationDataResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6510939605707700370L;
	private String code;
	private String message;
	private LocationData data;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public LocationData getData() {
		return data;
	}
	public void setData(LocationData data) {
		this.data = data;
	}
	
	
}
