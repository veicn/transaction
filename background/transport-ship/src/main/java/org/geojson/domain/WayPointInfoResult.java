package org.geojson.domain;

import java.io.Serializable;
import java.util.List;
/**
 * 返回轨迹信息结果
 * @author niuwk
 *
 */
public class WayPointInfoResult implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 378912843934286076L;
	private String code;
	private String message;
	private List<WayPointInfo> data;
	
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
	public List<WayPointInfo> getData() {
		return data;
	}
	public void setData(List<WayPointInfo> data) {
		this.data = data;
	}
	


}
