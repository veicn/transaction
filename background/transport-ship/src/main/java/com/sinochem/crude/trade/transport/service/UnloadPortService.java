package com.sinochem.crude.trade.transport.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transport.dao.UnloadPortMapper;
import com.sinochem.crude.trade.transport.domain.Agreement;
import com.sinochem.crude.trade.transport.domain.UnloadPort;
import com.sinochem.crude.trade.transport.model.UnloadPortVO;
import com.sinochem.crude.trade.transport.model.res.UnloadPortRes;

public interface UnloadPortService {
	
	public abstract UnloadPortMapper getMapper(); 
	
	/**
	 * 根据对象-查询对象列表
	 */
	public abstract List<UnloadPort> queryByEntitys(UnloadPort unloadport);
	
	/**
	 * 根据主键-查询对象
	 */
	public abstract UnloadPort findByPrimaryKey(Long unloadPortId);
 
 	/**
	 * 根据UUID-查询对象
	 */
	public abstract UnloadPort findByUuid(String uuid);
 
	/**
	 * 根据主键-修改对象
	 */
	public abstract void updateRecord(UnloadPort unloadport);
	
	/**
	 * 根据主键-逻辑删除对象（ AliveFlag修改为0）
	 */
	public abstract void deleteRecordByKey(Long unloadPortId , Long deleteUser);
	
	/**
	 * 新增
	 */
	public abstract void insertRecord(UnloadPort unloadport);
	
	/*
	 * 根据主键删除数据
	 */
	//public abstract void deleteRecordByKey(Long unloadPortId);
	
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
	 * 保存船舶卸港信息
	 * @param vo
	 * @param user
	 */
	public abstract void saveUnloadPort(UnloadPortVO vo, CurrentUser user);
	
	
	/**
	 * 修改船舶卸港信息
	 * @param vo
	 * @param user
	 */
	public abstract void updateUnloadPort(UnloadPortVO vo, CurrentUser user);

	/**
	 * 查询船舶卸港信息
	 * @param shipPactUuid
	 * @return
	 */
	public abstract UnloadPortRes findUnloadPortDeatil(String shipPactUuid);

	/**
	 * 同步订单卸港信息
	 * @param agreementUuids
	 * @param memberId
	 */
	public abstract void sendUnloadPort(List<Agreement> agreementUuids, Long memberId);
	
	/**
	 * 导入卸港信息
	 * @param path
	 * @param uuid
	 * @param user
	 * @return 
	 */
	public abstract String imports(String path, String uuid, CurrentUser user);

}
