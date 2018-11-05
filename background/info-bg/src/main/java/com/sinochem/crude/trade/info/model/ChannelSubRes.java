package com.sinochem.crude.trade.info.model;

import java.io.Serializable;

public class ChannelSubRes implements Serializable {

	private static final long serialVersionUID = 1L;	
	
	/**32位的唯一键*/
	private String uuid;  
	
	/**主频道id*/
	private Long channelMId;  
	
	/**频道代码*/
	private String channelCode;  
	
	/**频道名*/
	private String channelName;  
	
	/**频道描述*/
	private String channelDesc;  
	
	/**频道类型*/
	private String channelMDesc; 
	
	/**序号*/
	private Integer channelOrder;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Long getChannelMId() {
		return channelMId;
	}

	public void setChannelMId(Long channelMId) {
		this.channelMId = channelMId;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getChannelDesc() {
		return channelDesc;
	}

	public void setChannelDesc(String channelDesc) {
		this.channelDesc = channelDesc;
	}

	public Integer getChannelOrder() {
		return channelOrder;
	}

	public void setChannelOrder(Integer channelOrder) {
		this.channelOrder = channelOrder;
	}
	
	public String getChannelMDesc() {
		return channelMDesc;
	}

	public void setChannelMDesc(String channelMDesc) {
		this.channelMDesc = channelMDesc;
	}
}