package org.geojson.domain;

import java.io.Serializable;

public class HistoryDataResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 851701189507237377L;
	private String code;
	private String message;
	private HistoryData data;
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
	public HistoryData getData() {
		return data;
	}
	public void setData(HistoryData data) {
		this.data = data;
	}
	
	
	
	
}
