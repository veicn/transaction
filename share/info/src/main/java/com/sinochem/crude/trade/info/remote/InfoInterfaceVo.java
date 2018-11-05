package com.sinochem.crude.trade.info.remote;

import java.io.Serializable;
import java.util.Map;

public class InfoInterfaceVo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/**对方系统代码*/
	private String sysCode;  
	
	/**业务接口名称*/
	private String method;  
	
	/**交互的数据格式(仅支持json)*/
	private String format;  
	
	/**交互的字符编码*/
	private String charset;  
	
	/**发送请求的时间*/
	private java.util.Date timeStamp;  
	
	/**业务参数*/
	private String bizContent;

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

	public java.util.Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(java.util.Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getBizContent() {
		return bizContent;
	}

	public void setBizContent(String bizContent) {
		this.bizContent = bizContent;
	}  
	
}
