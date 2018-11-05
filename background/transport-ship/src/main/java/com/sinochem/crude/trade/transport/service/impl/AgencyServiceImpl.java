package com.sinochem.crude.trade.transport.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.transport.dao.AgencyMapper;
import com.sinochem.crude.trade.transport.domain.Agency;
import com.sinochem.crude.trade.transport.service.AgencyService;

@Service
public class AgencyServiceImpl implements AgencyService {
	@Autowired
	private AgencyMapper _AgencyMapper;
	
	
	public AgencyMapper getMapper(){
		return _AgencyMapper;
	} 
	
	/**
	 * 根据对象-查询对象列表
	 */
	public List<Agency> queryByEntitys(Agency agency){
		return  _AgencyMapper.queryByEntitys(agency);
	}
	
	/**
	 * 根据主键-查询对象
	 */
	public Agency findByPrimaryKey(Long agencyId){
		return  _AgencyMapper.findByPrimaryKey(agencyId);	
	}
	/**
	 * 根据UUID-查询对象
	 */
	public Agency findByUuid(String uuid){
		return  _AgencyMapper.findByUuid(uuid);	
	} 
	
	/**
	 * 根据主键-修改对象
	 */
	public void updateRecord(Agency agency) {
		 _AgencyMapper.updateRecord(agency);
	}
	
	/**
	 * 根据主键-逻辑删除对象（ AliveFlag修改为0）
	 */
	public void deleteRecordByKey(Long agencyId  , Long deleteUser) {
		 _AgencyMapper.deleteRecordByKey(agencyId , deleteUser);
	}
	
	/**
	 * 新增
	 */
	public void insertRecord(Agency agency){
		 _AgencyMapper.insertRecord(agency);
	}
	
	/*
	 * 根据主键删除数据 
	public void deleteRecordByKey(Long agencyId){
		 _AgencyMapper.deleteRecordByKey(agencyId);
	}
	*/
	/**
	 * 根据条件-查询全部
	 */
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return _AgencyMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) _AgencyMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	public int countRecords(Map<String, Object> map){
		return _AgencyMapper.countRecords(map);
	}
	
	/**
	 * 根据条件-批量逻辑删除 (AliveFlag修改为0)
	 */
	public void deleteRecords(Map<String, Object> map){
		_AgencyMapper.deleteRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据
	 */
	public void updateRecords(Map<String, Object> map) {
		_AgencyMapper.updateRecords(map);
	}
	
	//**************************以下方法为开发者补充*********************************/
	
}
