package com.sinochem.crude.trade.transport.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transport.dao.WaybillMapper;
import com.sinochem.crude.trade.transport.domain.Waybill;
import com.sinochem.crude.trade.transport.model.WaybillVO;

public interface WaybillService {
	
	public abstract WaybillMapper getMapper(); 
	
	/**
	 * 根据对象-查询对象列表
	 */
	public abstract List<Waybill> queryByEntitys(Waybill waybill);
	
	/**
	 * 根据主键-查询对象
	 */
	public abstract Waybill findByPrimaryKey(Long waybillId);
 
 	/**
	 * 根据UUID-查询对象
	 */
	public abstract Waybill findByUuid(String uuid);
 
	/**
	 * 根据主键-修改对象
	 */
	public abstract void updateRecord(Waybill waybill);
	
	/**
	 * 根据主键-逻辑删除对象（ AliveFlag修改为0）
	 */
	public abstract void deleteRecordByKey(Long waybillId , Long deleteUser);
	
	/**
	 * 新增
	 */
	public abstract void insertRecord(Waybill waybill);
	
	/*
	 * 根据主键删除数据
	 */
	//public abstract void deleteRecordByKey(Long waybillId);
	
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
	 * 生成运单
	 * @param vo
	 * @param user
	 */
	public abstract void saveWaybill(WaybillVO vo, CurrentUser user);

	/**
	 * 查询运单列表
	 * @param vo
	 * @param pageInfo
	 * @return
	 */
	public abstract Page<Map<String, Object>> queryWaybillList(WaybillVO vo,
			SimplePageInfo pageInfo);

	/**
	 * 取消协议匹配
	 * @param uuid
	 * @param user
	 */
	public abstract void cancelWaybill(String uuid, CurrentUser user);

	/**
	 * 生成运单多个协议匹配
	 * @param vo
	 * @param user
	 */
	public abstract void saveWaybillMany(WaybillVO vo, CurrentUser user);

	
}
