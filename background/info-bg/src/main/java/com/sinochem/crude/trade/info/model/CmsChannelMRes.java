package com.sinochem.crude.trade.info.model;

import java.io.Serializable;

public class CmsChannelMRes implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**主键*/
	private String channelMId;  
	
	/**32位的唯一键*/
	private String uuid;  
	
	/**主频道代码*/
	private String channelMCode;  
	
	/**主频道名称*/
	private String channelMName;  
	
	/**主频道描述*/
	private String channelMDesc;  
	
	/**序号*/
	private Integer channelMOrder;

	public final String getChannelMId() {
		return channelMId;
	}

	public final void setChannelMId(String channelMId) {
		this.channelMId = channelMId;
	}

	public final String getChannelMName() {
		return channelMName;
	}

	public final void setChannelMName(String channelMName) {
		this.channelMName = channelMName;
	}

	public final String getChannelMCode() {
		return channelMCode;
	}

	public final void setChannelMCode(String channelMCode) {
		this.channelMCode = channelMCode;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getChannelMDesc() {
		return channelMDesc;
	}

	public void setChannelMDesc(String channelMDesc) {
		this.channelMDesc = channelMDesc;
	}

	public Integer getChannelMOrder() {
		return channelMOrder;
	}

	public void setChannelMOrder(Integer channelMOrder) {
		this.channelMOrder = channelMOrder;
	}
}
