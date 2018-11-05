package com.sinochem.crude.trade.wechat.domain;

import java.util.Date;

public class AccessTokenClazz {
	private int tokentype;
	private Long id;
	private String accessToken;

	public int getTokentype() {
		return tokentype;
	}

	public void setTokentype(int tokentype) {
		this.tokentype = tokentype;
	}

	private Date createDate;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	

}
