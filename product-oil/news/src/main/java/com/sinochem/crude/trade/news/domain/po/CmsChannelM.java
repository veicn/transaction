package com.sinochem.crude.trade.news.domain.po;

import com.sinochem.crude.trade.common.base.BasePO;

/**
 * 主频道
 * @author 10907
 *
 */
public class CmsChannelM extends BasePO{

	/**
	 * 	主频道代码
	 */
	private String channelMCode;
	
	/**
	 * 主频道名称
	 */
	private String channelMName;
	
	/**
	 * 主频道类型
	 */
	private String channelMDesc;
	
	/**
	 * 序号
	 */
	private Integer channelMOrder;

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
