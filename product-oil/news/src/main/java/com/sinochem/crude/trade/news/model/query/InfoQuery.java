package com.sinochem.crude.trade.news.model.query;

import java.math.BigInteger;

/**
 * 咨询查询
 * @author Yichen Zhao
 * date: 20180306
 */
public class InfoQuery {
	
	/**
	 * 标题
	 */
	private String title;
	
	/**
	 * 类别Id
	 */
	private Long channelId;

	/**
	 * 语言类型
	 */
	private String extend10;
	
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getChannelId() {
		return channelId;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	public String getExtend10() {
		return extend10;
	}

	public void setExtend10(String extend10) {
		this.extend10 = extend10;
	}

	

	
}
