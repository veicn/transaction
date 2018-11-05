package com.sinochem.crude.trade.transport.service;


public interface MessagePushService {
	
	/**
	 * 发送消息
	 * @param type
	 * @param id
	 * @param fromMemberId
	 * type-id==>1航次开始-船合同；2装港-船合同；3在途-船合同；4卸港-船合同；5航次结束-船合同；6上传租船协议-租船协议；7船盘失效-船盘；8询盘-询盘；9还盘-询盘；
	         10确认租船-询盘；11船盘发布人终止询盘-询盘；12询盘人终止询盘-询盘；13报盘-报盘；14确认报盘-报盘；15终止报盘-报盘；
	         16船舶审核通过-船舶；17船舶审核驳回-船舶；18指定船代装港；19指定船代卸港；20已结算-结算单
	 */
	public void messagePush(int type, Long id, Long fromMemberId);

	/**
	 * 测试发送消息
	 */
	public void testPush();
}
