package com.sinochem.crude.trade.wechat.domain;

public class AccessToken {

	/**
	  * 返回码说明
	  *	返回码	说明
	  *	-1		系统繁忙，此时请开发者稍候再试
	  *	 0		请求成功
	  *	 40001	AppSecret错误或者AppSecret不属于这个公众号，请开发者确认AppSecret的正确性
	  *	 40002	请确保grant_type字段值为client_credential
	  *	 40164	调用接口的IP地址不在白名单中，请在接口IP白名单中进行设置
	  */
	/**获取到的凭证*/
	private String access_token;
	/**凭证有效时间，单位：秒*/
	private String expires_in;
	/**返回码*/
	private String errcode;
	/**说明*/
	private String errmsg;

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	private String ticket;
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public String getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}
	public String getErrcode() {
		return errcode;
	}
	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	
}
