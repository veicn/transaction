package com.sinochem.crude.trade.orderexecute.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.orderexecute.model.InspectionContentForDischargeportVO;
import com.sinochem.crude.trade.orderexecute.model.InspectionContentForLoadingportVO;
import com.sinochem.crude.trade.orderexecute.model.InspectionOrderListVO;

public interface InspectionService {
	/**
	 * 查询商检订单列表
	 * @param queryParams
	 * @param pageInfo
	 * @return
	 */
	public Page<InspectionOrderListVO> queryListForPage(Map<String, Object> queryParams, SimplePageInfo pageInfo);
	
	/**
	 * 查询所有船种类
	 * @param queryParams
	 * @return
	 */
	public List<String> distinctShipForInspection(Map<String, Object> queryParams);
	
	/**
	 * 查询所有港口种类
	 * @param queryParams
	 * @return
	 */
	public List<String> distinctPortForInspection(Map<String, Object> queryParams);
	
	/**
	 * 保存装港商检详情
	 * @param id 更新主键
	 * @param contentVO 详情
	 * @return
	 */
	public int saveInspectionLoadportDetail(Long id, InspectionContentForLoadingportVO contentVO);
	
	/**
	 * 保存卸港商检详情
	 * @param id 更新主键
	 * @param contentVO 详情
	 * @return
	 */
	public int saveInspectionDischargeportDetail(Long id, InspectionContentForDischargeportVO contentVO);
	
	/**
	 * 获取商检详情
	 * @param id 装港ID
	 * @return
	 */
	public InspectionContentForLoadingportVO getInspectionLoadportDetail(Long id);
	
	/**
	 * 获取商检详情
	 * @param id 卸港ID
	 * @return
	 */
	public InspectionContentForDischargeportVO getInspectionDischargeportDetail(Long id);

}
