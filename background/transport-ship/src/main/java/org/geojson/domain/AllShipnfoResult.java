package org.geojson.domain;

import java.io.Serializable;
import java.util.List;
/**
 * 返回的所有船舶信息结果
 * 
 * @author niuwk
 *
 */
public class AllShipnfoResult implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5279823322602521550L;
	
	private String code;
	private String message;
	private List<AllShipInfo> data;
	
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
	public List<AllShipInfo> getData() {
		return data;
	}
	public void setData(List<AllShipInfo> data) {
		this.data = data;
	}
	
	
	

}
