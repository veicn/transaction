package com.sinochem.crude.trade.order.remote;

/**
 * <p>
 * 订单同步相关接口，由后台实现，前台调用
 * 
 * @author hexinfang
 *
 */
public interface OrderInfoService {

	/**
	 * 创建订单，并同步到订单执行
	 * 
	 * @param vo
	 * 
	 */
	public void createOrder(OrderInfoVO vo);

	/**
	 * 取消订单
	 * @param vo
	 */
	public void cancelOrder(CancelOrderVO vo);
}
