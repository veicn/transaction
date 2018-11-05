package org.geojson.model;

import java.io.Serializable;

public class OppositeVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5987987424196254989L;
	
	private String portName;
	private String type;
	
	public String getPortName() {
		return portName;
	}
	public void setPortName(String portName) {
		this.portName = portName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
