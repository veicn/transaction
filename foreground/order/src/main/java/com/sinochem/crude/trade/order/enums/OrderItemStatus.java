package com.sinochem.crude.trade.order.enums;

public enum OrderItemStatus {
	/**
	 * 正常
	 */
	NORMAL,

	/**
	 * 关闭，指的是，订单还没有付款，客户就关闭，这个状态只受到Order的主状态的控制
	 */
	CLOSED,

	/**
	 * 取消，指这个商品不买了，注意执行的时候要判断商品是否有绑定销售，如果是绑定销售，取消的时候是取消所有的关联ietm才可以
	 */
	CANCEL,

	/**
	 * 修改，指在订单执行过程中因为特殊情况进行了修改
	 */
	MODIFY;

}
