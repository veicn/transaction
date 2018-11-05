package com.sinochem.crude.trade.transport.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.transport.dao.UnitRateMapper;
import com.sinochem.crude.trade.transport.domain.UnitRate;
import com.sinochem.crude.trade.transport.query.UnitRateQuery; 

public interface UnitRateService {
	
	public abstract UnitRateMapper getMapper(); 
	
	/**
	 * 根据对象-查询对象列表
	 */
	public abstract List<UnitRate> queryByEntitys(UnitRate unitrate);
	
	/**
	 * 根据主键-查询对象
	 */
	public abstract UnitRate findByPrimaryKey(Long unitRateId);
 
 	/**
	 * 根据UUID-查询对象
	 */
	public abstract UnitRate findByUuid(String uuid);
 
	/**
	 * 根据主键-修改对象
	 */
	public abstract void updateRecord(UnitRate unitrate);
	
	/**
	 * 根据主键-逻辑删除对象（ AliveFlag修改为0）
	 */
	public abstract void deleteRecordByKey(Long unitRateId , Long deleteUser);
	
	/**
	 * 新增
	 */
	public abstract void insertRecord(UnitRate unitrate);
	
	/*
	 * 根据主键删除数据
	 */
	//public abstract void deleteRecordByKey(Long unitRateId);
	
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
	 * 根据Uuid-逻辑删除对象（ AliveFlag修改为0）
	 * @param deleteUser 
	 */
	public abstract void deleteRecordByUuid(String uuid, Long deleteUser);
	//根据uuid修改对象
	public abstract void updateRecordByUuid(UnitRate unitRate, Long updateUser);
	//分页查询信息
	public abstract List<Map<String, Object>> getUnitPageList(UnitRateQuery query);
	
	/**
	 * 新增
	 */
	public abstract void insertRecord2(UnitRate unitrate, Long insertUser);
	//根据对应的名称 查找出比率信息
	public abstract UnitRate getRateInfo(UnitRate unitRate);

	//检查关联关系，如有 单位库不让删除
	public int getUnitRateByName(String chName);
}
