package com.sinochem.crude.trade.transport.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transport.dao.BasicTariffMapper;
import com.sinochem.crude.trade.transport.domain.BasicTariff;
import com.sinochem.crude.trade.transport.query.BasicTariffQuery; 

public interface BasicTariffService {
	
	public abstract BasicTariffMapper getMapper(); 
	
	/**
	 * 根据对象-查询对象列表
	 */
	public abstract List<BasicTariff> queryByEntitys(BasicTariff basictariff);
	
	/**
	 * 根据主键-查询对象
	 */
	public abstract BasicTariff findByPrimaryKey(Long basicTariffId);
 
 	/**
	 * 根据UUID-查询对象
	 */
	public abstract BasicTariff findByUuid(String uuid);
 
	/**
	 * 根据主键-修改对象
	 */
	public abstract void updateRecord(BasicTariff basictariff);
	
	/**
	 * 根据主键-逻辑删除对象（ AliveFlag修改为0）
	 */
	public abstract void deleteRecordByKey(Long basicTariffId , Long deleteUser);
	
	/**
	 * 新增
	 */
	public abstract void insertRecord(BasicTariff basictariff);
	
	/*
	 * 根据主键删除数据
	 */
	//public abstract void deleteRecordByKey(Long basicTariffId);
	
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
	 *翻页列表 
	 */
	public abstract List<Map<String, Object>> getBasicTariffPageList(BasicTariffQuery query);

	/**
	 * 通过uuid查询对象
	 */
	public abstract void getBasicTariffByUuid(BasicTariff bt);
	
	/**
	 * 新增对象
	 */
	public abstract void saveBasicTariff(BasicTariff bt, CurrentUser user);
	
	/**
	 * 通过uuid逻辑删除对象
	 */
	public abstract void deleteBasicTariffByUuid(BasicTariff bt, CurrentUser user);
	
	/**
	 * 通过uuid修改对象
	 */
	public abstract void updateBasicTariffByUuid(BasicTariff bt, CurrentUser user);

	//查重
	public abstract BasicTariff checkBt(BasicTariff bt);

	//查询翻页列表集合
	public abstract List<BasicTariff> findBasicTariffPageList(BasicTariff beanToBean);

	//运费小工具
	public abstract Map<String, Object> fieightTools(BasicTariffQuery query);
	
	//查询现有所有港口
	public abstract Map<String, Object> findAllPortList();

	/**
	 * 分页查询列表
	 * @param query
	 * @return
	 */
	public abstract Page<Map<String, Object>> getBasicTariffPageLists(
			BasicTariffQuery query);

}
