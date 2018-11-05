package com.sinochem.crude.trade.info.query;

import java.util.HashMap;
import java.util.Map;

import com.sinochem.crude.trade.common.QueryBase;

public class ChannelSubQuery extends QueryBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String channelName;
	private String channelMName;
	private String channelDesc;

	@Override
	public Map<String, String> getParameters() {
		Map<String,String> map = new HashMap<String,String>();
		map.put("channelName", getChannelName());
		map.put("channelDesc", channelDesc);
		map.put("channelMName", channelMName);
		return map;
	}

	
	public String getChannelMName() {
		return channelMName;
	}


	public void setChannelMName(String channelMName) {
		this.channelMName = channelMName;
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

	

}
