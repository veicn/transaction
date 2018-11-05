package com.sinochem.crude.trade.transport.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transport.dao.ShipmentMapper;
import com.sinochem.crude.trade.transport.domain.Shipment;
import com.sinochem.crude.trade.transport.model.ShipmentVO;
import com.sinochem.crude.trade.transport.model.res.ShipmentDetail;
import com.sinochem.crude.trade.transport.remote.LoadGoodsVO;

public interface ShipmentService {
	
	public abstract ShipmentMapper getMapper(); 
	
	/**
	 * 根据对象-查询对象列表
	 */
	public abstract List<Shipment> queryByEntitys(Shipment shipment);
	
	/**
	 * 根据主键-查询对象
	 */
	public abstract Shipment findByPrimaryKey(Long shipmentId);
 
 	/**
	 * 根据UUID-查询对象
	 */
	public abstract Shipment findByUuid(String uuid);
 
	/**
	 * 根据主键-修改对象
	 */
	public abstract void updateRecord(Shipment shipment);
	
	/**
	 * 根据主键-逻辑删除对象（ AliveFlag修改为0）
	 */
	public abstract void deleteRecordByKey(Long shipmentId , Long deleteUser);
	
	/**
	 * 新增
	 */
	public abstract void insertRecord(Shipment shipment);
	
	/*
	 * 根据主键删除数据
	 */
	//public abstract void deleteRecordByKey(Long shipmentId);
	
	/**
	 * 根据条件-查询全部
	 */
	public abstract List<Map<String, Object>> queryRecords(Map<String, Object> map);
	
	/**
	 * 根据条件-分页查询
	 */
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo);
	
	/**
	 * 根据条件-查询记录条数
	 */
	public abstract int countRecords(Map<String, Object> map); 
	
	/**
	 * 根据条件-批量逻辑删除 (AliveFlag修改为0)
	 */
	public abstract void deleteRecords(Map<String, Object> map);

	/**
	 * 根据条件-批量修改数据
	 */
	public void updateRecords(Map<String, Object> map);


	//**************************以下方法为开发者补充*********************************/
	/**
	 * 货物装港信息新增
	 * @param vo
	 * @param user
	 */
	public abstract void saveShipment(ShipmentVO vo, CurrentUser user);

	/**
	 * 查询货物装港信息
	 * @param vo
	 * @return
	 */
	public abstract List<ShipmentDetail> findShipmentDeatil(ShipmentVO vo);

	/**
	 * 货物装港信息修改
	 * @param vo
	 * @param user
	 */
	public abstract void updateShipment(ShipmentVO vo, CurrentUser user);

	/**
	 * 删除多余船合同
	 * @param lists
	 * @param shipPactUuid
	 */
	public abstract void deletByLoadAndShipPact(
			List<Map<String, Object>> lists, String shipPactUuid);

	/**
	 * 订单同步船务货物装港信息
	 * @param list
	 * @param orderCode 
	 * @param memberId 
	 */
	public abstract void saveLoadGoods(List<LoadGoodsVO> list, String orderCode, Long memberId);


	
}
