package org.geojson.domain;

import java.io.Serializable;
import java.util.List;
/**
 * 返回的历史航线信息结果
 * 
 * @author niuwk
 *
 */
public class HistoryPortResult implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5279823322602521550L;
	
	private String code;
	private String message;
	private String messageCn;
	private String messageEn;
	private HtmlInfo html;
	
	private List<HistoryPortInfo> data;
	

	public HtmlInfo getHtml() {
		return html;
	}

	public void setHtml(HtmlInfo html) {
		this.html = html;
	}

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

	public List<HistoryPortInfo> getData() {
		return data;
	}

	public void setData(List<HistoryPortInfo> data) {
		this.data = data;
	}
	

}
