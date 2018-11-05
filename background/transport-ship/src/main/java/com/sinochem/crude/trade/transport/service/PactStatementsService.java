package com.sinochem.crude.trade.transport.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.transport.dao.PactStatementsMapper;
import com.sinochem.crude.trade.transport.domain.PactStatements;
import com.sinochem.crude.trade.transport.query.StatementsQuery;

public interface PactStatementsService {
	
	public abstract PactStatementsMapper getMapper(); 
	
	/**
	 * 根据对象-查询对象列表
	 */
	public abstract List<PactStatements> queryByEntitys(PactStatements pactstatements);
	
	/**
	 * 根据主键-查询对象
	 */
	public abstract PactStatements findByPrimaryKey(Long statementsId);
 
 	/**
	 * 根据UUID-查询对象
	 */
	public abstract PactStatements findByUuid(String uuid);
 
	/**
	 * 根据主键-修改对象
	 */
	public abstract void updateRecord(PactStatements pactstatements);
	
	/**
	 * 根据主键-逻辑删除对象（ AliveFlag修改为0）
	 */
	public abstract void deleteRecordByKey(Long statementsId , Long deleteUser);
	
	/**
	 * 新增
	 */
	public abstract void insertRecord(PactStatements pactstatements);
	
	/*
	 * 根据主键删除数据
	 */
	//public abstract void deleteRecordByKey(Long statementsId);
	
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
	 * 通过油合同号查找对象
	 * @param pageInfo 
	 * @param query 
	 * */
	public abstract Page<Map<String, Object>> findByPactCode(Map<String, Object> map, SimplePageInfo pageInfo, StatementsQuery query);

	public abstract Page<Map<String, Object>> findDzdList(Map<String, Object> map2, SimplePageInfo pageInfo);
	
}
