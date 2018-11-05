package com.sinochem.crude.trade.transport.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.transport.dao.BrokerMapper;
import com.sinochem.crude.trade.transport.domain.Broker;
import com.sinochem.crude.trade.transport.service.BrokerService;

@Service
public class BrokerServiceImpl implements BrokerService {
	@Autowired
	private BrokerMapper _BrokerMapper;
	
	
	public BrokerMapper getMapper(){
		return _BrokerMapper;
	} 
	
	/**
	 * 根据对象-查询对象列表
	 */
	public List<Broker> queryByEntitys(Broker broker){
		return  _BrokerMapper.queryByEntitys(broker);
	}
	
	/**
	 * 根据主键-查询对象
	 */
	public Broker findByPrimaryKey(Long brokerId){
		return  _BrokerMapper.findByPrimaryKey(brokerId);	
	}
	/**
	 * 根据UUID-查询对象
	 */
	public Broker findByUuid(String uuid){
		return  _BrokerMapper.findByUuid(uuid);	
	} 
	
	/**
	 * 根据主键-修改对象
	 */
	public void updateRecord(Broker broker) {
		 _BrokerMapper.updateRecord(broker);
	}
	
	/**
	 * 根据主键-逻辑删除对象（ AliveFlag修改为0）
	 */
	public void deleteRecordByKey(Long brokerId  , Long deleteUser) {
		 _BrokerMapper.deleteRecordByKey(brokerId , deleteUser);
	}
	
	/**
	 * 新增
	 */
	public void insertRecord(Broker broker){
		 _BrokerMapper.insertRecord(broker);
	}
	
	/*
	 * 根据主键删除数据 
	public void deleteRecordByKey(Long brokerId){
		 _BrokerMapper.deleteRecordByKey(brokerId);
	}
	*/
	/**
	 * 根据条件-查询全部
	 */
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return _BrokerMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) _BrokerMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	public int countRecords(Map<String, Object> map){
		return _BrokerMapper.countRecords(map);
	}
	
	/**
	 * 根据条件-批量逻辑删除 (AliveFlag修改为0)
	 */
	public void deleteRecords(Map<String, Object> map){
		_BrokerMapper.deleteRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据
	 */
	public void updateRecords(Map<String, Object> map) {
		_BrokerMapper.updateRecords(map);
	}
	
	//**************************以下方法为开发者补充*********************************/
	/**
	 * 查询经纪人信息列表
	 */
	@Override
	public List<Broker> findBrokerList(Broker vo) {
		// 查询船舶信息列表
		List<Broker> brokerList = (List<Broker>)this.queryByEntitys(vo);
		
		return brokerList;
	}
}
