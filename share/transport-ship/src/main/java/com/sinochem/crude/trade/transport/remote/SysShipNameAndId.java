package com.sinochem.crude.trade.transport.remote;

import java.io.Serializable;

public class SysShipNameAndId  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**业务唯一键*/
	private Long id;  
	
	/**船名*/
	private String name;  
	
	/**业务唯一键*/
	public Long getId() {
		return id;
	}
	/**业务唯一键*/
	public void setId(Long id) {
		this.id = id;
	}
	/**船名*/
	public void setName(String name){
		this.name=name;
	}
	/**船名*/
	public String getName(){
		return this.name;
	}
	
	
}