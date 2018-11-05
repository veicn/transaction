package com.sinochem.crude.trade.orderexecute.remote;

import java.util.List;

/**
 * <p>船务运输相关信息接口，用于接收相关船务情报
 * 
 * @author wangxinran
 *
 */
public interface IShipTransportService {
	
	/**
	 * 租船信息同步
	 * @param shipInfo
	 * @param memberId
	 */
	public void syncShipInfo(FindedShipInfoVO shipInfo, Long memberId);
	
	/**
	 * 同步装港信息
	 * @param transportLoadLst
	 * @param memberId
	 * @return
	 */
	public void insertTransportLoading(List<TransportLoadVO> transportLoadLst, Long memberId);

	/**
	 * 同步卸港信息
	 * @param transportUnloadLst
	 * @param memberId
	 * @return
	 */
	public void insertTransportUnloading(List<TransportUnloadVO> transportUnloadLst, Long memberId);
}
