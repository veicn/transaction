package com.sinochem.crude.trade.info.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.info.dao.ColSubscribeMapper;
import com.sinochem.crude.trade.info.domain.ColSubscribe;
import com.sinochem.crude.trade.info.domain.Info;
import com.sinochem.crude.trade.member.user.CurrentUser; 

public interface ColSubscribeService {
	
	public abstract ColSubscribeMapper getMapper(); 
	
	/**
	 * 根据对象-查询对象列表
	 */
	public abstract List<ColSubscribe> queryByEntitys(ColSubscribe colsubscribe);
	
	/**
	 * 根据主键-查询对象
	 */
	public abstract ColSubscribe findByPrimaryKey(String id);
 
	/**
	 * 根据主键-修改对象
	 */
	public abstract void updateRecord(ColSubscribe colsubscribe) throws Exception;
	
	/**
	 * 根据主键-逻辑删除对象（ AliveFlag修改为0）
	 */
	public abstract void deleteRecordByKey(String id , String deleteUser) throws Exception;
	
	/**
	 * 新增
	 */
	public abstract void insertRecord(ColSubscribe colsubscribe,CurrentUser user);
	
	/*
	 * 根据主键删除数据
	 */
	//public abstract void deleteRecordByKey(String id) throws Exception;
	
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
	public abstract void deleteRecords(Map<String, Object> map) throws Exception;

	/**
	 * 根据条件-批量修改数据
	 */
	public void updateRecords(Map<String, Object> map) throws Exception;

	//**************************以下方法为开发者补充*********************************/
	
	public abstract Page<Map<String, Object>> mySubscribeQuery(Map<String, Object> map, SimplePageInfo pageInfo);

	public abstract Page<Map<String, Object>> mySubscribeInfoQuery(Map<String, Object> map,
			SimplePageInfo pageInfo);
	/**
	 * 取消订阅
	 */
	public abstract void updateByColumnId(ColSubscribe sub);

	public abstract List<Info> queryNewInfo(String id);

}
