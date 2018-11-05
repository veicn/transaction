package com.sinochem.crude.trade.transport.remote;

import java.util.List;

/**
 * @Description: 
 * @author gyy
 */
public interface IShipService {
	
	/**
	 * 取消订单
	 * @param orderCode
	 * @param memberId
	 */
	public void cancelOrder(String orderCode,Long memberId);
	
	/**
	 * 根据船名查询imo号（资讯）
	 * @param name
	 */
	public String findImoByShipName(String name);
	
	/**
	 * 查询船舶列表（成品油）
	 */
	public List<SysShipNameAndId> findSysShipNameList();
	
	/**
	 * 根据id查询船舶（成品油）
	 * @param name
	 */
	public SysShipVO findShipById(Long shipId);
	
	/**
	 * 根据IMO和船名查询船舶库是否有船如果没有则新增（成品油） 
	 * @param imo
	 * @param name
	 */
	public void insertProductoilShip(SysShipVO vo);
	
	
}
