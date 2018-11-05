package com.sinochem.crude.trade.wechat.service;

import com.sinochem.crude.trade.wechat.domain.AccessToken;
import com.sinochem.crude.trade.wechat.domain.AccessTokenClazz;

public interface AccessTokenService {

	void saveAccessToken(AccessToken accessToken,int type);

	AccessTokenClazz findAnAccessToken();

	void deleteAccessToken();

	String getToken(boolean regain);

	String getJSToken(boolean regain);
}
