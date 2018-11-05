package com.sinochem.crude.trade.orderexecute.dao;


public interface OrderButtonMapper {
	
	/**
	 * 判断是否租船
	 */
	public int checkLogistics(Long orderId);
	
	/**
	 * 判断是否已手动维护船盘
	 */
	public int checkRouteQuery(Long orderId);
	
	/**
	 * 判断是否已维护费用信息
	 */
	public int checkFee(Long orderId);
	
	/**
	 * 判断预估对账单已确认
	 */
	public int checkSettlementPreSell(Long orderId);
	
	/**
	 * 判断正式对账单已确认
	 */
	public int checkSettlementFinSell(Long orderId);	
	
	/**
	 * 判断正式对账单已确认
	 */
	public int checkReceiveAmount(Long orderId);	
	
	/**
	 * 已维护预估对账单-且预估对账单不是初始状态
	 */
	public int checkStatementPreBuy(Long orderId);	
	
	/**
	 * 判断已生成预估结算单
	 */
	public int checkSettlementPreBuy(Long orderId);	
	
	/**
	 * 已维护正式对账单-且正式对账单不是初始状态
	 */
	public int checkStatementFinBuy(Long orderId);	
	
	/**
	 * 已生成正式结算单
	 */
	public int checkSettlementFinBuy(Long orderId);	
	
	/**
	 * 判断计价方式是否是trigger
	 */
	public int checkIsTrigger(Long orderId);
	/**
	 * 判断数量是否相等
	 */
	public int checkIsQuantity(Long orderId);	
}
