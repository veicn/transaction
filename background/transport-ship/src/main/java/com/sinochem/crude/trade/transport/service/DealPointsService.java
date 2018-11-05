package com.sinochem.crude.trade.transport.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transport.dao.DealPointsMapper;
import com.sinochem.crude.trade.transport.domain.DealPoints;
import com.sinochem.crude.trade.transport.model.DealPointsVO;
import com.sinochem.crude.trade.transport.query.DealPointsQuery;

public interface DealPointsService {
	
	public abstract DealPointsMapper getMapper(); 
	
	/**
	 * 根据对象-查询对象列表
	 */
	public abstract List<DealPoints> queryByEntitys(DealPoints dealpoints);
	
	/**
	 * 根据主键-查询对象
	 */
	public abstract DealPoints findByPrimaryKey(Long dealPointsId);
 
 	/**
	 * 根据UUID-查询对象
	 */
	public abstract DealPoints findByUuid(String uuid);
 
	/**
	 * 根据主键-修改对象
	 */
	public abstract void updateRecord(DealPoints dealpoints);
	
	/**
	 * 根据主键-逻辑删除对象（ AliveFlag修改为0）
	 */
	public abstract void deleteRecordByKey(Long dealPointsId , Long deleteUser);
	
	/**
	 * 新增
	 */
	public abstract void insertRecord(DealPoints dealpoints);
	
	/*
	 * 根据主键删除数据
	 */
	//public abstract void deleteRecordByKey(Long dealPointsId);
	
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
	/**成交点数 翻页列表*/
	public abstract List<Map<String, Object>> getDealPointsPageList(DealPointsQuery query);
	
	/**
	 * 通过uuid查询对象
	 */
	public abstract void getDealPointsByUuid(DealPoints dp);
	
	/**
	 * 新增对象
	 */
	public abstract void saveDealPoints(DealPoints dp, CurrentUser user);
	
	/**
	 * 通过uuid逻辑删除对象
	 */
	public abstract void deleteDealPointsByUuid(DealPoints dp, CurrentUser user);
	
	/**
	 * 通过uuid修改对象
	 */
	public abstract void updateDealPointsByUuid(DealPoints dp, CurrentUser user);
	//查重
	public abstract DealPoints checkDp(DealPointsVO dp);
	//查询翻页列表
	public abstract List<DealPoints> findDealPointsPageList();
	//查询当天成交点数，倒序
	public abstract List<DealPoints> getTodayDealPoints(String todayDate);
	//查询今年的所有成交点数，构造线形图
	public abstract List<DealPoints> getThisYearDealPoints(DealPoints dealPoints);
	
	public abstract void saveDealPoints2(DealPoints dp);
	
	//获取最新成交点数
	public abstract List<DealPoints> getNowDealPoints();
	//获取前一天
	public abstract DealPoints getDealPointsByRecode(DealPoints dealPoints2);
	//参考时间集合
	public abstract List<Date> findAllDates();

	public abstract List<Map<String, Object>> findDealPointsPageListByRecords(
			Map<String, String> map);
}
