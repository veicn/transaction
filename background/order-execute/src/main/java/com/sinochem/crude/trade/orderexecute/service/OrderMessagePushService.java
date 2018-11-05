package com.sinochem.crude.trade.orderexecute.service;

import com.sinochem.crude.trade.orderexecute.dao.OrderMessagePushMapper;

public interface OrderMessagePushService {
	
	public abstract OrderMessagePushMapper getMapper(); 
	
	/**
	 * 消息推送
	 */
	void checkDocument();
	
	/**
	 * 消息推送
	 */
	void messagePush(int level, String uuid, Long memberId, String documenType, String payDate);

}
