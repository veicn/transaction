package com.sinochem.crude.trade.transport.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.transport.dao.ConcurrentMapper;
import com.sinochem.crude.trade.transport.domain.Concurrent;
import com.sinochem.crude.trade.transport.service.ConcurrentService;

@Service
public class ConcurrentServiceImpl implements ConcurrentService {
	@Autowired
	private ConcurrentMapper _ConcurrentMapper;
	
	
	public ConcurrentMapper getMapper(){
		return _ConcurrentMapper;
	} 
	
	/**
	 * 根据对象-查询对象列表
	 */
	public List<Concurrent> queryByEntitys(Concurrent concurrent){
		return  _ConcurrentMapper.queryByEntitys(concurrent);
	}
	
	/**
	 * 根据主键-查询对象
	 */
	public Concurrent findByPrimaryKey(String id){
		return  _ConcurrentMapper.findByPrimaryKey(id);	
	}
	/**
	 * 根据UUID-查询对象
	 */
	public Concurrent findByUuid(String uuid){
		return  _ConcurrentMapper.findByUuid(uuid);	
	} 
	
	/**
	 * 根据主键-修改对象
	 */
	public void updateRecord(Concurrent concurrent) {
		 _ConcurrentMapper.updateRecord(concurrent);
	}
	
	/**
	 * 根据主键-逻辑删除对象（ AliveFlag修改为0）
	 */
	public void deleteRecordByKey(String id  , Long deleteUser) {
		 _ConcurrentMapper.deleteRecordByKey(id , deleteUser);
	}
	
	/**
	 * 新增
	 */
	public void insertRecord(Concurrent concurrent){
		 _ConcurrentMapper.insertRecord(concurrent);
	}
	
	/*
	 * 根据主键删除数据 
	 * */
	public void deleteRecordByKey(String id){
		 _ConcurrentMapper.deleteRecordByKeys(id);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return _ConcurrentMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) _ConcurrentMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	public int countRecords(Map<String, Object> map){
		return _ConcurrentMapper.countRecords(map);
	}
	
	/**
	 * 根据条件-批量逻辑删除 (AliveFlag修改为0)
	 */
	public void deleteRecords(Map<String, Object> map){
		_ConcurrentMapper.deleteRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据
	 */
	public void updateRecords(Map<String, Object> map) {
		_ConcurrentMapper.updateRecords(map);
	}
	
	//**************************以下方法为开发者补充*********************************/
	
}
