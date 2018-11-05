package com.sinochem.crude.trade.transport.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transport.dao.IntentionMapper;
import com.sinochem.crude.trade.transport.domain.Intention;
import com.sinochem.crude.trade.transport.query.IntentionQuery;

public interface IntentionService {

	public abstract IntentionMapper getMapper();

	/**
	 * 根据对象-查询对象列表
	 */
	public abstract List<Intention> queryByEntitys(Intention intention);

	/**
	 * 根据主键-查询对象
	 */
	public abstract Intention findByPrimaryKey(Long intentionId);

	/**
	 * 根据UUID-查询对象
	 */
	public abstract Intention findByUuid(String uuid);

	/**
	 * 根据主键-修改对象
	 */
	public abstract void updateRecord(Intention intention);

	/**
	 * 根据主键-逻辑删除对象（ AliveFlag修改为0）
	 */
	public abstract void deleteRecordByKey(Long intentionId, Long deleteUser);

	/**
	 * 新增
	 */
	public abstract void insertRecord(Intention intention);

	/*
	 * 根据主键删除数据
	 */
	// public abstract void deleteRecordByKey(Long intentionId);

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

	// **************************以下方法为开发者补充*********************************/
	
	/**
	 * 根据uuid-批量修改数据
	 * @param map
	 */
	public void updateRecordsFn(Map<String, Object> map);
	
	/**
	 * 添加询盘信息
	 * @param vo
	 * @param user
	 */
	public void saveIntention(Intention vo,CurrentUser user);

	/**
	 * 询盘列表（查看向我询盘信息）
	 * @param user
	 * @param query
	 * @param pageInfo
	 * @return
	 */
	public Page<Map<String, Object>> findIntentionList(CurrentUser user,IntentionQuery query,SimplePageInfo pageInfo);
	
	/**
	 * 根据条件-分页查询（查看向我询盘信息）
	 */
	public Page<Map<String, Object>> findIntentionListQueryRecords(Map<String, Object> map, SimplePageInfo pageInfo);
	
	/**
	 * 根据条件-查询全部（设置询盘状态不等于...）
	 */
	public List<Map<String, Object>> findPageListStatusEqualsNo(Map<String, Object> map);
	
	/**
	 * 询盘列表（货主/代理，租船需求管理-查看询盘信息）
	 */
	public Page<Map<String, Object>> findIntentionLists(CurrentUser user, IntentionQuery query, SimplePageInfo pageInfo);

	/**
	 * 查看询盘详细信息
	 * @param beanToBean
	 * @param user
	 * @return
	 */
	public abstract Intention findIntentionDetail(Intention vo, CurrentUser user);

	/**
	 * 修改询盘状态,终止操作
	 * @param vo
	 * @param user
	 */
	public abstract void updateIntentionStatus(Intention vo, CurrentUser user);
	
	/**
	 * 修改询盘信息
	 * @param vo
	 * @param user
	 */
	public abstract void updateIntention(Intention vo,CurrentUser user);
	
	/**
	 * 查询询船盘信息翻页列表（船东/船东经纪人）
	 * @param query
	 * @param user
	 * @return
	 */
	public abstract Page<Map<String,Object>> getIntentionPageList(IntentionQuery query, CurrentUser user);
	
	/**
	 * 查询询船盘信息翻页列表（货主/代理）
	 * @param query
	 * @param user
	 * @return
	 */
	public abstract Page<Map<String, Object>> findIntentionPageList(IntentionQuery query, CurrentUser user);
	
	/**
	 * 询盘详细信息（船盘uuid、status、船盘发布人，条件查询）
	 * @param vo
	 * @param user
	 * @return
	 */
	public abstract Map<String, List<Map<String, Object>>> findCharterShipConfirm(Intention vo,CurrentUser user);
	
	/**
	 * 修改询盘状态（确认成交）
	 * @param vo
	 * @param user
	 */
	public void updateConfirmTransaction(Intention vo, CurrentUser user);
	/**
	 * 根据船盘uuid 批量修改询盘状态
	 * @param vo
	 * @param user
	 */
	public void changeStatus(Map<String, Object> map);
}
