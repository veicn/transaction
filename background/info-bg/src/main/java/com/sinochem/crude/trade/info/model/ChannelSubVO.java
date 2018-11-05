package com.sinochem.crude.trade.info.model;

import com.sinochem.crude.trade.info.domain.ChannelSub;

public class ChannelSubVO extends ChannelSub {

	private static final long serialVersionUID = 1L;	
	
	private String channelMUuid;
	
	/**主频道代码*/
	private String channelMCode;  
	
	/**主频道名称*/
	private String channelMName;  
	
	/**频道类型*/
	private String channelMDesc;

	public String getChannelMCode() {
		return channelMCode;
	}

	public void setChannelMCode(String channelMCode) {
		this.channelMCode = channelMCode;
	}

	public String getChannelMName() {
		return channelMName;
	}

	public void setChannelMName(String channelMName) {
		this.channelMName = channelMName;
	}

	public String getChannelMUuid() {
		return channelMUuid;
	}

	public void setChannelMUuid(String channelMUuid) {
		this.channelMUuid = channelMUuid;
	}

	public String getChannelMDesc() {
		return channelMDesc;
	}

	public void setChannelMDesc(String channelMDesc) {
		this.channelMDesc = channelMDesc;
	}
}