package org.geojson.domain;

import java.io.Serializable;
import java.util.List;

import org.geojson.model.OppositeVO;

public class OppositeInfoResult implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7667446933417085976L;
	
	private String code;
	private String message;
	private String messageCn;
	private String messageEn;
	
	private List<OppositeInfo> data;

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

	public List<OppositeInfo> getData() {
		return data;
	}

	public void setData(List<OppositeInfo> data) {
		this.data = data;
	}
	
}
