package com.sinochem.crude.trade.info.model;

import java.io.Serializable;
import java.util.Map;

public class ExternalOut implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String sysCode;
	
	private String code = "10";
	
	private String msg = "接口调用成功";
	
	private String subCode = "10";
	
	private String subMsg = "业务处理成功";
	
	private Map<String, Object> bizContent;

	public String getSysCode() {
		return sysCode;
	}

	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getSubCode() {
		return subCode;
	}

	public void setSubCode(String subCode) {
		this.subCode = subCode;
	}

	public String getSubMsg() {
		return subMsg;
	}

	public void setSubMsg(String subMsg) {
		this.subMsg = subMsg;
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
