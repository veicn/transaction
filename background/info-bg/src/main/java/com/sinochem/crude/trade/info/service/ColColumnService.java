package com.sinochem.crude.trade.info.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.info.dao.ColColumnMapper;
import com.sinochem.crude.trade.info.domain.ColColumn;
import com.sinochem.crude.trade.info.domain.ColSubscribe;
import com.sinochem.crude.trade.info.domain.Collection;
import com.sinochem.crude.trade.info.domain.Fabulous;
import com.sinochem.crude.trade.info.model.ColSubscribeVO;
import com.sinochem.crude.trade.info.model.ColumnDetailVO;
import com.sinochem.crude.trade.info.model.ColumnVO;
import com.sinochem.crude.trade.info.model.FabulousVO;
import com.sinochem.crude.trade.member.user.CurrentUser; 

public interface ColColumnService {
	
	public abstract ColColumnMapper getMapper(); 
	
	/**
	 * 根据对象-查询对象列表
	 */
	public abstract List<ColColumn> queryByEntitys(ColColumn colcolumn);
	
	/**
	 * 根据主键-查询对象
	 */
	public abstract ColColumn findByPrimaryKey(String id);
 
	/**
	 * 根据主键-修改对象
	 */
	public abstract void updateRecord(ColColumn colcolumn) throws Exception;
	
	/**
	 * 根据主键-逻辑删除对象（ AliveFlag修改为0）
	 */
	public abstract void deleteRecordByKey(String id , String deleteUser) throws Exception;
	
	/**
	 * 新增
	 */
	public abstract void insertRecord(ColColumn colcolumn,CurrentUser user);
	
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
	public abstract Map<String, Object> getInfoByUuid(String infoUuid);

	public abstract Result addColumnInfo(ColumnVO vo, CurrentUser user);

	public abstract Result saveColumnInfo(ColumnVO vo, CurrentUser user);

	public abstract List<Map<String, Object>> hotInfoList();

	public abstract Integer updateInfo(ColumnVO columnVO);
	
	/**
	 * 根据条件-取专栏列表
	 */
	public abstract Page<Map<String, Object>> queryColumnList(Map<String, Object> beanToMap, SimplePageInfo pageInfo);

	public abstract ColumnDetailVO findById(Map<String, Object> map);

	public abstract void updateColumnById(String columnId,String type);

	public abstract List<ColSubscribe> findSubscribeById(String id);

	public abstract List<Fabulous> findFabulousByInfoId(FabulousVO fabulousVO);

	public abstract List<Collection> findCollectionByInfoId(Collection collection);

	public abstract List<ColSubscribe> findSubscribeByColumnId(ColSubscribeVO colSubscribeVO);
}
