package org.geojson.domain;

import java.io.Serializable;
/**
 * 返回的靠港信息结果
 * @author niuwk
 *
 */
public class DockInfoResult implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 378912843934286076L;
	private String code;
	private String message;
	private DockInfo data;
	
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
	public DockInfo getData() {
		return data;
	}
	public void setData(DockInfo data) {
		this.data = data;
	}
	
	

}
