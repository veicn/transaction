package com.sinochem.crude.trade.info.query;

import java.util.HashMap;
import java.util.Map;

import com.sinochem.crude.trade.common.QueryBase;

/**
 * 
 * @ClassName: MyInfoListQuery
 * @Description: 我的资讯列表查询
 * @author pengfl
 * @date 2017年11月17日 下午3:23:21
 *
 */
public class MyInfoListQuery extends QueryBase {

	private static final long serialVersionUID = 1L;

	private String tab; // 0:全部 1:已发布 2:未发布 3:草稿
	private String status; // 资讯状态
	private String channelM; // 主频道uuid
	private String channel; //子频道uuid

	@Override
	public Map<String, String> getParameters() {

		Map<String, String> params = new HashMap<String, String>();
		params.put("tab", this.getTab());
		params.put("status", this.getStatus());
		params.put("channelM", this.getChannelM());
		params.put("channel", this.getChannel());

		return params;
	}

	public String getTab() {
		return tab;
	}

	public void setTab(String tab) {
		this.tab = tab;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getChannelM() {
		return channelM;
	}

	public void setChannelM(String channelM) {
		this.channelM = channelM;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}
}
