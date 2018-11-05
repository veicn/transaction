package com.sinochem.crude.trade.transport.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.transport.dao.TideMapper;
import com.sinochem.crude.trade.transport.domain.Tide;
import com.sinochem.crude.trade.transport.query.TideQuery;

public interface TideService {
	
	public abstract TideMapper getMapper(); 
	
	/**
	 * 根据对象-查询对象列表
	 */
	public abstract List<Tide> queryByEntitys(Tide tide);
	
	/**
	 * 根据主键-查询对象
	 */
	public abstract Tide findByPrimaryKey(Long tideId);
 
 	/**
	 * 根据UUID-查询对象
	 */
	public abstract Tide findByUuid(String uuid);
 
	/**
	 * 根据主键-修改对象
	 */
	public abstract void updateRecord(Tide tide);
	
	/**
	 * 根据主键-逻辑删除对象（ AliveFlag修改为0）
	 */
	public abstract void deleteRecordByKey(Long tideId , Long deleteUser);
	
	/**
	 * 新增
	 */
	public abstract void insertRecord(Tide tide);
	
	/*
	 * 根据主键删除数据
	 */
	//public abstract void deleteRecordByKey(Long tideId);
	
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
		 * 新增潮汐信息
		 * @param vo
		 */
		public abstract Long saveTide(Tide vo, String uuid, Long memberId);
		
		/**
		 * 修改潮汐信息
		 * @param vo
		 */
		public abstract void updateTide(Tide vo, Long memberId);
		
		/**
		 * 删除潮汐信息
		 * @param vo
		 */
		public abstract void delTide(Tide vo, Long memberId);
		
		/**
		 * 查询潮汐信息详细
		 * @param vo
		 */
		public abstract Tide findTideDetail(Tide vo);
		
		/**
		 * 查询潮汐信息列表
		 * @param vo
		 */
		public abstract List<Tide> findTideList(Tide vo);
		
		/**
		 * 查询潮汐信息翻页列表
		 * @param vo
		 */
		public abstract Page<Tide> findTidePageList(Tide vo, SimplePageInfo pageInfo);
		
		/**
		 * 查询潮汐信息翻页列表
		 * @param vo
		 */
		public abstract List<Map<String, Object>> getTidePageList(TideQuery query);
		
		/**
		 * 根据港口、日期查询潮汐信息(前台接口)
		 * @param vo
		 */
		public abstract List<Map<String, Object>> findTideListByPortDate(Tide vo);
}
