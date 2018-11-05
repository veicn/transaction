package org.geojson.domain;

import java.io.Serializable;
import java.util.List;
/**
 * 返回的船舶信息结果
 * @author niuwk
 *
 */
public class ShipInfoResult implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 378912843934286076L;
	private String code;
	private String message;
	private List<ShipInfo> data;
	
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
	public List<ShipInfo> getData() {
		return data;
	}
	public void setData(List<ShipInfo> data) {
		this.data = data;
	}
	
	
	
	

}
