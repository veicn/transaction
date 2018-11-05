package com.sinochem.crude.trade.wechat.dao;

import com.sinochem.crude.trade.wechat.domain.AccessTokenClazz;


public interface AccessTokenMapper {

	void insertAccessToken(AccessTokenClazz accessTokenClazz);

	AccessTokenClazz findAnAccessToken();

	AccessTokenClazz findvolidAccessToken(AccessTokenClazz accessTokenClazz);
	
	void deleteAccessToken();
	
}
