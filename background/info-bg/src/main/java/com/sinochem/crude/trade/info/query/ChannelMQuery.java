package com.sinochem.crude.trade.info.query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sinochem.crude.trade.common.QueryBase;
import com.sinochem.crude.trade.info.model.ChannelMVO;

public class ChannelMQuery extends QueryBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String channelMName;
	private List<ChannelMVO> list;
	private String channelMDesc;
	
	@Override
	public Map<String, String> getParameters() {
		Map<String, String> demo = new HashMap<String, String>();
		demo.put("channelMName", channelMName);
		demo.put("channelMDesc", channelMDesc);
		return demo;
	}

	public String getChannelMName() {
		return channelMName;
	}

	public void setChannelMName(String channelMName) {
		this.channelMName = channelMName;
	}

	public String getChannelMDesc() {
		return channelMDesc;
	}

	public void setChannelMDesc(String channelMDesc) {
		this.channelMDesc = channelMDesc;
	}

	public List<ChannelMVO> getList() {
		return list;
	}

	public void setList(List<ChannelMVO> list) {
		this.list = list;
	}
	
}
