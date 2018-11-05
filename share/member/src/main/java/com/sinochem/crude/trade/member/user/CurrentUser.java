package com.sinochem.crude.trade.member.user;

import java.io.Serializable;

import com.sinochem.it.b2b.common.cache.CacheEntry;

/**
 * 如果有用户头像，默认是用户id的访问路径
 * 
 * @author Leo
 *
 */
public class CurrentUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2815179518013150214L;
	/**
	 * 登录用户的用户编号
	 */
	private Long memberId;
	/**
	 * 登录用户的企业id，因为在原油中，一定是企业
	 */
	private Long epMemberId;
	/**
	 * 登录用户的显示名称
	 */
	private String name;

	/**
	 * 用户的权限
	 */
	private String[] roles;

	public Long getMemberId() {
		return memberId;
	}

	protected void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Long getEpMemberId() {
		return epMemberId;
	}

	protected void setEpMemberId(Long epMemberId) {
		this.epMemberId = epMemberId;
	}

	public String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	public String[] getRoles() {
		return roles;
	}

	protected void setRoles(String[] roles) {
		this.roles = roles;
	}

}
