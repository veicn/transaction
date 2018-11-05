package com.sinochem.crude.trade.orderexecute.remote;

import com.sinochem.it.b2b.common.exception.BizException;

/**
 * <p>找船相关接口，用于推送未租船订单相关信息，或许租船信息
 * 
 * @author wangxinran
 *
 */
public interface IFindShipService {

	/**
	 * 推送订单相关信息
	 * @param pageNum
	 * @param pageSize
	 * @return 订单相关信息
	 */
	public FindShipOrderResult sendOrderInfo(Integer pageNum, Integer pageSize, Long memberId);
	
	/**
	 * 推送订单详情
	 * @param orderNo
	 * @return
	 */
	public ForShipOrderInfo sendOrderDetail(String orderNo);
	
	/**
	 * 已生成租船需求
	 * @param orderNo
	 */
	public void generateCharterDemand(String orderNo) throws BizException;
	
	/**
	 * 取消租船
	 * @param orderNo
	 * @throws BizException
	 */
	public void cancelCharter(String orderNo) throws BizException;
}
