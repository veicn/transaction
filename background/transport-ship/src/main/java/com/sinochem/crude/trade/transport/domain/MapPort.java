package com.sinochem.crude.trade.transport.domain;

import java.io.Serializable;

public class MapPort implements Serializable{
	
	private String portName;
	private String portNameEn;
	private String timeZone;
	
	
	public String getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}
	public String getPortName() {
		return portName;
	}
	public void setPortName(String portName) {
		this.portName = portName;
	}
	public String getPortNameEn() {
		return portNameEn;
	}
	public void setPortNameEn(String portNameEn) {
		this.portNameEn = portNameEn;
	}
	
	

}
