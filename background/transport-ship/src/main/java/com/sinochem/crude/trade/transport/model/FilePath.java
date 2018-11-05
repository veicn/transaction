package com.sinochem.crude.trade.transport.model;

import java.io.Serializable;

public class FilePath implements Serializable {

	private static final long serialVersionUID = 1L;	
	
	/**文件路径*/
	private String path;

	/**是否在线打开*/
	private Boolean isOnLine;
	
	
	

	public Boolean getIsOnLine() {
		return isOnLine;
	}

	public void setIsOnLine(Boolean isOnLine) {
		this.isOnLine = isOnLine;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	
	
}