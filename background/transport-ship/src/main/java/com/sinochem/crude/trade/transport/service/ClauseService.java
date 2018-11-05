package com.sinochem.crude.trade.transport.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transport.dao.ClauseMapper;
import com.sinochem.crude.trade.transport.domain.Clause;
import com.sinochem.crude.trade.transport.model.ClauseVO;

public interface ClauseService {
	
	public abstract ClauseMapper getMapper(); 
	
	/**
	 * 根据对象-查询对象列表
	 */
	public abstract List<Clause> queryByEntitys(Clause clause);
	
	/**
	 * 根据主键-查询对象
	 */
	public abstract Clause findByPrimaryKey(Long clauseId);
 
 	/**
	 * 根据UUID-查询对象
	 */
	public abstract Clause findByUuid(String uuid);
 
	/**
	 * 根据主键-修改对象
	 */
	public abstract void updateRecord(Clause clause);
	
	/**
	 * 根据主键-逻辑删除对象（ AliveFlag修改为0）
	 */
	public abstract void deleteRecordByKey(Long clauseId , Long deleteUser);
	
	/**
	 * 新增
	 */
	public abstract void insertRecord(Clause clause);
	
	/*
	 * 根据主键删除数据
	 */
	//public abstract void deleteRecordByKey(Long clauseId);
	
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
	 * 新增报盘
	 */
	public void saveClause(Clause vo, CurrentUser user);
	/**
	 * 撤销
	 */
	public void deleteClause(Clause vo, CurrentUser user);
	/**
	 * 报盘确认
	 */
	public abstract void affirmClause(Clause vo,CurrentUser user);
	/**
	 * 根据UUID-查询对象
	 */
	public Map<String, Object> findMapByUuid(String uuid);
	/**
	 * 查看报盘详细信息
	 */
	public Map<String, Object> findClauseDetails(ClauseVO vo, CurrentUser user);
	/**
	 * 发送报盘页面数据
	 */
	public Map<String, Object> findShipAndShipPlate(Clause vo, CurrentUser user);
	/**
	 * 查看报盘详细信息
	 */
	public Map<String, Object> findClauseDetails1(ClauseVO vo, CurrentUser user);
	/**
	 * 洽谈报盘列表
	 */
	public Page<Map<String, Object>> talkingClause(Map<String, Object> map, SimplePageInfo pageInfo);

	/**
	 * 查看报盘信息
	 */
	public abstract Clause findClauseDetail(ClauseVO vo);
}
