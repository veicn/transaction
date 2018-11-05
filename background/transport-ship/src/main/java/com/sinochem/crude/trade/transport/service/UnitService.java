package com.sinochem.crude.trade.transport.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.transport.dao.UnitMapper;
import com.sinochem.crude.trade.transport.domain.Unit;
import com.sinochem.crude.trade.transport.query.UnitQuery;

public interface UnitService {
	
	public abstract UnitMapper getMapper(); 
	
	/**
	 * 根据对象-查询对象列表
	 */
	public abstract List<Unit> queryByEntitys(Unit unit);
	
	/**
	 * 根据主键-查询对象
	 */
	public abstract Unit findByPrimaryKey(Long unitId);
 
 	/**
	 * 根据UUID-查询对象
	 */
	public abstract Unit findByUuid(String uuid);
 
	/**
	 * 根据主键-修改对象
	 */
	public abstract void updateRecord(Unit unit);
	
	/**
	 * 根据主键-逻辑删除对象（ AliveFlag修改为0）
	 */
	public abstract void deleteRecordByKey(Long unitId , Long deleteUser);
	
	/**
	 * 新增
	 */
	public abstract void insertRecord(Unit unit);
	
	/*
	 * 根据主键删除数据
	 */
	//public abstract void deleteRecordByKey(Long unitId);
	
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
	 * 新增
	 */
	public abstract void insertRecord2(Unit unit, Long user);
	
	/**
	 * 根据Uuid-逻辑删除对象（ AliveFlag修改为0）
	 * @param deleteUser 
	 */
	public abstract void deleteRecordByUuid(String uuid, Long deleteUser);

	public abstract void updateRecordByUuid(Unit unit, Long updateUser);
	
	/**
	 * 分页信息列表
	 * @param 
	 * @param 
	 * @return
	 */
	public abstract List<Map<String, Object>> getUnitPageList(UnitQuery query);
	
	
	public abstract void updateDefaultUnit(Unit unit);

	//检查重复
	public abstract Unit findByName(String chName);
}
