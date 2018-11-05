package com.sinochem.crude.trade.transport.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.common.utils.KeyGenUtils;
import com.sinochem.crude.trade.transport.commons.constants.Constants;
import com.sinochem.crude.trade.transport.dao.UnitMapper;
import com.sinochem.crude.trade.transport.domain.Unit;
import com.sinochem.crude.trade.transport.query.UnitQuery;
import com.sinochem.crude.trade.transport.service.UnitService;

@Service
public class UnitServiceImpl implements UnitService {
	@Autowired
	private UnitMapper _UnitMapper;
	
	
	
	public UnitMapper getMapper(){
		return _UnitMapper;
	} 
	
	/**
	 * 根据对象-查询对象列表
	 */
	public List<Unit> queryByEntitys(Unit unit){
		return  _UnitMapper.queryByEntitys(unit);
	}
	
	/**
	 * 根据主键-查询对象
	 */
	public Unit findByPrimaryKey(Long unitId){
		return  _UnitMapper.findByPrimaryKey(unitId);	
	}
	/**
	 * 根据UUID-查询对象
	 */
	public Unit findByUuid(String uuid){
		return  _UnitMapper.findByUuid(uuid);	
	} 
	
	/**
	 * 根据主键-修改对象
	 */
	public void updateRecord(Unit unit) {
		 _UnitMapper.updateRecord(unit);
	}
	
	/**
	 * 根据主键-逻辑删除对象（ AliveFlag修改为0）
	 */
	public void deleteRecordByKey(Long unitId  , Long deleteUser) {
		 _UnitMapper.deleteRecordByKey(unitId , deleteUser);
	}
	
	/**
	 * 新增
	 */
	public void insertRecord(Unit unit){
		 _UnitMapper.insertRecord(unit);
	}
	
	/*
	 * 根据主键删除数据 
	public void deleteRecordByKey(Long unitId){
		 _UnitMapper.deleteRecordByKey(unitId);
	}
	*/
	/**
	 * 根据条件-查询全部
	 */
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return _UnitMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) _UnitMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	public int countRecords(Map<String, Object> map){
		return _UnitMapper.countRecords(map);
	}
	
	/**
	 * 根据条件-批量逻辑删除 (AliveFlag修改为0)
	 */
	public void deleteRecords(Map<String, Object> map){
		_UnitMapper.deleteRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据
	 */
	public void updateRecords(Map<String, Object> map) {
		_UnitMapper.updateRecords(map);
	}
	
	//**************************以下方法为开发者补充*********************************/
	
	/**
	 * 新增
	 */
	public void insertRecord2(Unit unit, Long insertUser){
		unit.setUuid(KeyGenUtils.newUuid());
		unit.setCreateDate(DateTimeUtils.currentDate());
		unit.setUpdateDate(DateTimeUtils.currentDate());
		unit.setLangVer(Constants.LANG_VER);
		unit.setAliveFlag(Constants.ALIEVE_FLAG_YES);
		unit.setCreateUser(insertUser);
		unit.setVersion("1");
		 _UnitMapper.insertRecord(unit);
	}
	
	/**
	 * 根据Uuid-逻辑删除对象（ AliveFlag修改为0）
	 */
	public void deleteRecordByUuid(String uuid, Long deleteUser) {
		Map<String, Object> map = new HashMap<>();
		map.put("uuid", uuid);
		map.put("deleteUser", deleteUser);
		map.put("updateDate", DateTimeUtils.currentDate());
		 _UnitMapper.deleteRecordsByUuid(map);
	}

	@Override
	public void updateRecordByUuid(Unit unit, Long updateUser) {
		unit.setUpdateUser(updateUser);
		unit.setUpdateDate(DateTimeUtils.currentDate());
		unit.setVersion((Integer.parseInt(unit.getVersion())+1)+"");
		_UnitMapper.updateRecordByUuid(unit);
		
	}

	@Override
	public List<Map<String, Object>> getUnitPageList(UnitQuery query) {
		PageHelper.startPage(query.getCurrentPage(), query.getPageSize());
		// 单位名称
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", query.getType());
		
		List<Map<String, Object>> list = _UnitMapper.queryRecords(map);
		
		return list;
	}
	/**
	 * 跟据类型设置默认参考单位
	 */
	@Override
	public void updateDefaultUnit(Unit unit) {
		_UnitMapper.updateDefaultUnit(unit);
	}

	//查重
	@Override
	public Unit findByName(String chName) {
		return _UnitMapper.findByName(chName);
	}
	
}
