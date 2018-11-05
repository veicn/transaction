package com.sinochem.crude.trade.transport.remote;
/**
 *dubbo服务接口 成交点数服务
 */
public interface IDealPointsService {
	
	/**
	 * 成交点数（WS点）新增接口
	 * @param IDealPointsVO
	 */
	public  void saveDealPoints(IDealPointsVO dp);
	
}
