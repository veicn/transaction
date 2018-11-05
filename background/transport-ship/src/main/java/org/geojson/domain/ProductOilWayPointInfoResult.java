package org.geojson.domain;

import java.io.Serializable;
/**
 * 返回轨迹信息结果
 * @author niuwk
 *
 */
public class ProductOilWayPointInfoResult implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4559614017278146079L;
	
	private String code;
	private String message;
	private String messageCn;
	private String messageEn;
	
	private ProductOilWayPointInfo data;

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

	public String getMessageCn() {
		return messageCn;
	}

	public void setMessageCn(String messageCn) {
		this.messageCn = messageCn;
	}

	public String getMessageEn() {
		return messageEn;
	}

	public void setMessageEn(String messageEn) {
		this.messageEn = messageEn;
	}

	public ProductOilWayPointInfo getData() {
		return data;
	}

	public void setData(ProductOilWayPointInfo data) {
		this.data = data;
	}

	
	
}
