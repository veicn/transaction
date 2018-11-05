package com.sinochem.crude.trade.info.model;

import java.io.Serializable;
import java.util.Map;

public class ExternalIn implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String sysCode;
	
	private String method;
	
	private String format;
	
	private String charset;
	
	private String timestamp;
	
	private Map<String, Object> bizContent;

	public String getSysCode() {
		return sysCode;
	}

	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public Map<String, Object> getBizContent() {
		return bizContent;
	}

	public void setBizContent(Map<String, Object> bizContent) {
		this.bizContent = bizContent;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
