package com.sinochem.crude.trade.transport.model;

import java.io.Serializable;

public class AgencyNameVO implements Serializable {

	private static final long serialVersionUID = 1L;	
	
	/**1装港船代2卸港船代*/
	private String type;
	
	private String name;
	
	private String code;
	
	private String port;
	
	private Long id;

	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}