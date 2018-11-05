package com.sinochem.crude.trade.transport.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transport.dao.TransitMapper;
import com.sinochem.crude.trade.transport.domain.BasicTariff;
import com.sinochem.crude.trade.transport.domain.Transit;
import com.sinochem.crude.trade.transport.model.TransitVO;
import com.sinochem.crude.trade.transport.model.res.TransitRes;

public interface TransitService {
	
	public abstract TransitMapper getMapper(); 
	
	/**
	 * 根据对象-查询对象列表
	 */
	public abstract List<Transit> queryByEntitys(Transit transit);
	
	/**
	 * 根据主键-查询对象
	 */
	public abstract Transit findByPrimaryKey(Long transitId);
 
 	/**
	 * 根据UUID-查询对象
	 */
	public abstract Transit findByUuid(String uuid);
 
	/**
	 * 根据主键-修改对象
	 */
	public abstract void updateRecord(Transit transit);
	
	/**
	 * 根据主键-逻辑删除对象（ AliveFlag修改为0）
	 */
	public abstract void deleteRecordByKey(Long transitId , Long deleteUser);
	
	/**
	 * 新增
	 */
	public abstract void insertRecord(Transit transit);
	
	/*
	 * 根据主键删除数据
	 */
	//public abstract void deleteRecordByKey(Long transitId);
	
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
	 * 新增船舶在途信息
	 * @param vo
	 * @param user
	 */
	public abstract void saveTransit(TransitVO vo, CurrentUser user);

	/**
	 * 修改船舶在途信息
	 * @param vo
	 * @param user
	 */
	public abstract void updateTransit(TransitVO vo, CurrentUser user);
	
	/**
	 * 查询船舶在途信息详情
	 * @param uuid
	 * @return
	 */
	public abstract TransitRes findTransitDetail(String uuid);

	/**
	 * 查询船舶在途信息列表
	 * @param shipPactUuid
	 * @param pageInfo
	 * @return
	 */
	public abstract Page<Map<String, Object>> queryTransitList(
			String shipPactUuid, SimplePageInfo pageInfo);

	/**
	 * 查询船在途鞋卸港信息
	 * @param shipPactUuid
	 * @return
	 */
	public abstract List<Map<String, Object>> unloadList(String shipPactUuid);

	/**
	 * 删除在途信息
	 * @param uuid
	 * @param user
	 */
	public abstract void deleteTransitByUuid(String uuid, CurrentUser user);

	/**
	 * 导入在途信息
	 * @param path
	 * @param uuid
	 * @param user
	 * @return 
	 */
	public abstract String imports(String path, String uuid, CurrentUser user);

	/**
	 * 导入基础运价
	 * @param path
	 * @param uuid
	 * @param user
	 * @return 
	 */
	public abstract String importsBt(List<BasicTariff> list, CurrentUser user);

	/**
	 *  查询物流跟踪查看更多在途信息列表
	 * @param shipPactUuid
	 * @param pageInfo
	 * @return
	 */
	public abstract Page<Map<String, Object>> queryMoreTransitList(
			String shipPactUuid, SimplePageInfo pageInfo);


}
