package com.sinochem.crude.trade.member.service;

import com.sinochem.it.b2b.common.exception.BizException;

import java.util.Map;

public interface AsoSsoConfigService {

	public String getTgs(String sys);

	public String getTvs(String sys);

	public String getReturnUrl();

	/**
	 * 加密
	 */
	public String encryption(String Sys, String Time, String param) throws BizException;

	/**
	 * 解密
	 */
	public String decrypt(String str) throws BizException;

}
