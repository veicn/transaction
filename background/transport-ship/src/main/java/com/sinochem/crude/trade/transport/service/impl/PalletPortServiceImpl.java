package com.sinochem.crude.trade.transport.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.transport.dao.PalletPortMapper;
import com.sinochem.crude.trade.transport.domain.PalletPort;
import com.sinochem.crude.trade.transport.service.PalletPortService;

@Service
public class PalletPortServiceImpl implements PalletPortService {
	@Autowired
	private PalletPortMapper _PalletPortMapper;
	
	
	public PalletPortMapper getMapper(){
		return _PalletPortMapper;
	} 
	
	/**
	 * 根据对象-查询对象列表
	 */
	public List<PalletPort> queryByEntitys(PalletPort palletport){
		return  _PalletPortMapper.queryByEntitys(palletport);
	}
	
	/**
	 * 根据主键-查询对象
	 */
	public PalletPort findByPrimaryKey(Long palletPortId){
		return  _PalletPortMapper.findByPrimaryKey(palletPortId);	
	}
	/**
	 * 根据UUID-查询对象
	 */
	public PalletPort findByUuid(String uuid){
		return  _PalletPortMapper.findByUuid(uuid);	
	} 
	
	/**
	 * 根据主键-修改对象
	 */
	public void updateRecord(PalletPort palletport) {
		 _PalletPortMapper.updateRecord(palletport);
	}
	
	/**
	 * 根据主键-逻辑删除对象（ AliveFlag修改为0）
	 */
	public void deleteRecordByKey(Long palletPortId  , Long deleteUser) {
		 _PalletPortMapper.deleteRecordByKey(palletPortId , deleteUser);
	}
	
	/**
	 * 新增
	 */
	public void insertRecord(PalletPort palletport){
		 _PalletPortMapper.insertRecord(palletport);
	}
	
	/*
	 * 根据主键删除数据 
	public void deleteRecordByKey(Long palletPortId){
		 _PalletPortMapper.deleteRecordByKey(palletPortId);
	}
	*/
	/**
	 * 根据条件-查询全部
	 */
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return _PalletPortMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) _PalletPortMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	public int countRecords(Map<String, Object> map){
		return _PalletPortMapper.countRecords(map);
	}
	
	/**
	 * 根据条件-批量逻辑删除 (AliveFlag修改为0)
	 */
	public void deleteRecords(Map<String, Object> map){
		_PalletPortMapper.deleteRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据
	 */
	public void updateRecords(Map<String, Object> map) {
		_PalletPortMapper.updateRecords(map);
	}

	
	
	
	//**************************以下方法为开发者补充*********************************/
	/**
	 * 根据uuid-修改对象
	 */
	public void update(PalletPort palletport) {
		 _PalletPortMapper.update(palletport);
	}
}
