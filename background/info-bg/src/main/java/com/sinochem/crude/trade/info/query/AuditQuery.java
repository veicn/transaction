package com.sinochem.crude.trade.info.query;

import java.util.HashMap;
import java.util.Map;

import com.sinochem.crude.trade.common.QueryBase;

/**
 * 
 * @ClassName: AuditQuery
 * @Description: 审核查询
 * @author pengfl
 * @date 2017年11月17日 下午3:23:21
 *
 */
public class AuditQuery extends QueryBase {

	private static final long serialVersionUID = 1L;

	private String tab; // 0:待审核 1:已发布
	private String keyWords; // 关键词
	private String pubBeginTime; // 发布开始时间
	private String pubEndTime; // 发布结束时间
	private String status; // 资讯状态
	private String channelM; // 主频道uuid
	private String channel; //子频道uuid
	private String Type; //频道类型
//主频道类型
	@Override
	public Map<String, String> getParameters() {

		Map<String, String> params = new HashMap<String, String>();
		params.put("tab", this.getTab());
		params.put("keyWords", this.getKeyWords());
		params.put("pubBeginTime", this.getPubBeginTime());
		params.put("pubEndTime", this.getPubEndTime());
		params.put("status", this.getStatus());
		params.put("channelM", this.getChannelM());
		params.put("channel", this.getChannel());
		params.put("Type", this.getType());

		return params;
	}

	public String getTab() {
		return tab;
	}

	public void setTab(String tab) {
		this.tab = tab;
	}

	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public String getPubBeginTime() {
		return pubBeginTime;
	}

	public void setPubBeginTime(String pubBeginTime) {
		this.pubBeginTime = pubBeginTime;
	}

	public String getPubEndTime() {
		return pubEndTime;
	}

	public void setPubEndTime(String pubEndTime) {
		this.pubEndTime = pubEndTime;
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

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}


}
