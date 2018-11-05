package com.sinochem.crude.trade.transport.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.transport.dao.AppointMapper;
import com.sinochem.crude.trade.transport.domain.Appoint;
import com.sinochem.crude.trade.transport.service.AppointService;

@Service
public class AppointServiceImpl implements AppointService {
	@Autowired
	private AppointMapper _AppointMapper;
	
	
	public AppointMapper getMapper(){
		return _AppointMapper;
	} 
	
	/**
	 * 根据对象-查询对象列表
	 */
	public List<Appoint> queryByEntitys(Appoint appoint){
		return  _AppointMapper.queryByEntitys(appoint);
	}
	
	/**
	 * 根据主键-查询对象
	 */
	public Appoint findByPrimaryKey(Long appointId){
		return  _AppointMapper.findByPrimaryKey(appointId);	
	}
	/**
	 * 根据UUID-查询对象
	 */
	public Appoint findByUuid(String uuid){
		return  _AppointMapper.findByUuid(uuid);	
	} 
	
	/**
	 * 根据主键-修改对象
	 */
	public void updateRecord(Appoint appoint) {
		 _AppointMapper.updateRecord(appoint);
	}
	
	/**
	 * 根据主键-逻辑删除对象（ AliveFlag修改为0）
	 */
	public void deleteRecordByKey(Long appointId  , Long deleteUser) {
		 _AppointMapper.deleteRecordByKey(appointId , deleteUser);
	}
	
	/**
	 * 新增
	 */
	public void insertRecord(Appoint appoint){
		 _AppointMapper.insertRecord(appoint);
	}
	
	/*
	 * 根据主键删除数据 
	public void deleteRecordByKey(Long appointId){
		 _AppointMapper.deleteRecordByKey(appointId);
	}
	*/
	/**
	 * 根据条件-查询全部
	 */
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return _AppointMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) _AppointMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	public int countRecords(Map<String, Object> map){
		return _AppointMapper.countRecords(map);
	}
	
	/**
	 * 根据条件-批量逻辑删除 (AliveFlag修改为0)
	 */
	public void deleteRecords(Map<String, Object> map){
		_AppointMapper.deleteRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据
	 */
	public void updateRecords(Map<String, Object> map) {
		_AppointMapper.updateRecords(map);
	}
	
	//**************************以下方法为开发者补充*********************************/
	
}
