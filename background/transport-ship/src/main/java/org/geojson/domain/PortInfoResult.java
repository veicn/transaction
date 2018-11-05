package org.geojson.domain;

import java.io.Serializable;
import java.util.List;
/**
 * 返回的靠港信息结果
 * @author niuwk
 *
 */
public class PortInfoResult implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 378912843934286076L;
	private String code;
	private String message;
	private List<PortInfo> data;
	
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
	public List<PortInfo> getData() {
		return data;
	}
	public void setData(List<PortInfo> data) {
		this.data = data;
	}
	

}
