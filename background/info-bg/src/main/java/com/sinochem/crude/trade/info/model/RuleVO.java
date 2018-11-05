package com.sinochem.crude.trade.info.model;

import org.codehaus.jackson.annotate.JsonUnwrapped;

import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.info.domain.Rule;

public class RuleVO extends Rule {

	private static final long serialVersionUID = 1L;

	/** 频道信息 */
	private String channelUuid;

	/** 角色信息 */
	private String roleConstant;
	/**
	 * 分页对象
	 */
	@JsonUnwrapped
	private SimplePageInfo pageInfo;

	public SimplePageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(SimplePageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	public String getChannelUuid() {
		return channelUuid;
	}

	public void setChannelUuid(String channelUuid) {
		this.channelUuid = channelUuid;
	}

	public String getRoleConstant() {
		return roleConstant;
	}

	public void setRoleConstant(String roleConstant) {
		this.roleConstant = roleConstant;
	}

}