package com.sinochem.crude.trade.transport.remote;

import java.util.List;

/**
 * @Description: 货物装卸港维护
 * @author gyy
 */
public interface IShipGoodsService {
	
	/**
	 * 保存货物装港信息
	 * @param list
	 * @param orderCode
	 * @param memberId
	 */
	public void saveLoadGoods(List<LoadGoodsVO> list , String orderCode,Long memberId);
	
	/**
	 * 保存货物卸港信息
	 * @param list
	 * @param orderCode
	 * @param memberId
	 */
	public void saveUnloadGoods(List<UnloadGoodsVO> list , String orderCode,Long memberId);
	
}
