package com.sinochem.crude.trade.transport.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transport.dao.ShipPactMapper;
import com.sinochem.crude.trade.transport.domain.ShipPact;
import com.sinochem.crude.trade.transport.model.ShipPactVO;

public interface ShipPactService {
	
	public abstract ShipPactMapper getMapper(); 
	
	/**
	 * 根据对象-查询对象列表
	 */
	public abstract List<ShipPact> queryByEntitys(ShipPact shippact);
	
	/**
	 * 根据主键-查询对象
	 */
	public abstract ShipPact findByPrimaryKey(Long shipPactId);
 
 	/**
	 * 根据UUID-查询对象
	 */
	public abstract ShipPact findByUuid(String uuid);
 
	/**
	 * 根据主键-修改对象
	 */
	public abstract void updateRecord(ShipPact shippact);
	
	/**
	 * 根据主键-逻辑删除对象（ AliveFlag修改为0）
	 */
	public abstract void deleteRecordByKey(Long shipPactId , Long deleteUser);
	
	/**
	 * 新增
	 */
	public abstract void insertRecord(ShipPact shippact);
	
	/*
	 * 根据主键删除数据
	 */
	//public abstract void deleteRecordByKey(Long shipPactId);
	
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
	 * 生成船合同
	 * @param vo
	 * @param user
	 */
	public abstract void saveShipPact(ShipPactVO vo, CurrentUser user);

	/**
	 * 查询船合同详情
	 * @param vo
	 * @return
	 */
	public abstract ShipPact findShipPactDetail(ShipPactVO vo);

	/**
	 * 查询船合同列表
	 * @param map
	 * @param pageInfo 
	 * @return
	 */
	public abstract Page<Map<String, Object>> queryShipPactList(Map<String, Object> map, SimplePageInfo pageInfo);
	
	/**
	 * 查询船合同列表
	 * @param map
	 * @param pageInfo 
	 * @return
	 */
	public abstract Page<Map<String, Object>> queryShipPactListAgency(Map<String, Object> map, SimplePageInfo pageInfo);
	
	/**
	 * 查询船合同列表（船东/经纪人）
	 * @param map
	 * @param pageInfo 
	 * @return
	 */
	public abstract Page<Map<String, Object>> queryShipOwnerShipPactList(Map<String, Object> map, SimplePageInfo pageInfo);
	
	/**
	 * 查询船合同列表（代理）
	 * @param map
	 * @param pageInfo 
	 * @return
	 */
	public abstract Page<Map<String, Object>> queryShipAgentShipPactList(Map<String, Object> map, SimplePageInfo pageInfo);
	
	/**
	 * 查询船合同列表（货主）
	 * @param map
	 * @param pageInfo 
	 * @return
	 */
	public abstract Page<Map<String, Object>> queryCargoOwnerShipPactList(Map<String, Object> map, SimplePageInfo pageInfo);

	/**
	 * 查询船合同和协议信息
	 * @param vo
	 * @return
	 */
	public abstract Map<String, Object> queryAgreementAndPact(ShipPactVO vo);

	/**
	 * 航次结束
	 * @param uuid
	 * @param user
	 */
	public abstract void finishShipPact(String uuid, CurrentUser user);

	/**
	 * 校验航次结束
	 * @param uuid
	 * @return 
	 */
	public abstract Map<String, Object> checkShipPactFinish(String uuid);

	/**
	 * 修改船合同
	 * @param vo
	 * @param user
	 */
	public abstract void updateShipPact(ShipPactVO vo, CurrentUser user);

	/**
	 * 查询值集
	 * @param vo
	 * @return
	 */
	public abstract List<Map<String, Object>> queryValueSet(ShipPactVO vo);

	/**
	 * 查询物流跟踪
	 * @param vo
	 * @param user 
	 * @return
	 */
	public abstract Map<String, Object> queryTrack(ShipPactVO vo, CurrentUser user);
	
	
	/**
	 * 根据询盘uuid或者船盘UUID查询合同信息
	 * @param shippact
	 * @return
	 */
	public ShipPact queryIntentionOrPlateUuid(ShipPactVO vo);
	/**
	 * 移动查询物流跟踪
	 * @param vo
	 * @param user 
	 * @return
	 */
	public abstract Map<String, Object> MobileQueryTrack(ShipPactVO vo, CurrentUser user);
	
	/**
	 * 查询船合同列表
	 * @param map
	 * @param pageInfo 
	 * @return
	 */
	public abstract Page<ShipPact> queryShipPactLists(Map<String, Object> map,SimplePageInfo pageInfo);
	
	/**
	 * 根据条件-查询记录条数
	 */
	public abstract int queryShipPactListCount(Map<String, Object> map); 
	
	
	/**
	 * 更新合同状态
	 * @param status
	 * @return
	 */
	public abstract void updateShipPactStatus(ShipPactVO vo, CurrentUser user);

	/**
	 * 根据船盘uuid查询数据
	 * @param pact
	 * @return
	 */
	public abstract ShipPact findByEntitys(ShipPact pact);

	/**
	 * 平台查询船合同列表
	 * @param map
	 * @param pageInfo
	 * @return
	 */
	public abstract Page<Map<String, Object>> queryShipPactPlatform(
			Map<String, Object> map, SimplePageInfo pageInfo);

	/**
	 * 值集基础运价OM使用
	 * @param vo
	 * @return
	 */
	public abstract List<Map<String, Object>> queryValueSetEn(ShipPactVO vo); 
	
}
