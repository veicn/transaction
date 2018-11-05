package com.sinochem.crude.trade.transport.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.common.utils.KeyGenUtils;
import com.sinochem.crude.trade.transport.commons.constants.Constants;
import com.sinochem.crude.trade.transport.dao.UnitRateMapper;
import com.sinochem.crude.trade.transport.domain.UnitRate;
import com.sinochem.crude.trade.transport.query.UnitRateQuery;
import com.sinochem.crude.trade.transport.service.UnitRateService;

@Service
public class UnitRateServiceImpl implements UnitRateService {
	@Autowired
	private UnitRateMapper _UnitRateMapper;
	
	
	public UnitRateMapper getMapper(){
		return _UnitRateMapper;
	} 
	
	/**
	 * 根据对象-查询对象列表
	 */
	public List<UnitRate> queryByEntitys(UnitRate unitrate){
		return  _UnitRateMapper.queryByEntitys(unitrate);
	}
	
	/**
	 * 根据主键-查询对象
	 */
	public UnitRate findByPrimaryKey(Long unitRateId){
		return  _UnitRateMapper.findByPrimaryKey(unitRateId);	
	}
	/**
	 * 根据UUID-查询对象
	 */
	public UnitRate findByUuid(String uuid){
		return  _UnitRateMapper.findByUuid(uuid);	
	} 
	
	/**
	 * 根据主键-修改对象
	 */
	public void updateRecord(UnitRate unitrate) {
		 _UnitRateMapper.updateRecord(unitrate);
	}
	
	/**
	 * 根据主键-逻辑删除对象（ AliveFlag修改为0）
	 */
	public void deleteRecordByKey(Long unitRateId  , Long deleteUser) {
		 _UnitRateMapper.deleteRecordByKey(unitRateId , deleteUser);
	}
	
	/**
	 * 新增
	 */
	public void insertRecord(UnitRate unitRate){
		 _UnitRateMapper.insertRecord(unitRate);
	}
	
	/*
	 * 根据主键删除数据 
	public void deleteRecordByKey(Long unitRateId){
		 _UnitRateMapper.deleteRecordByKey(unitRateId);
	}
	*/
	/**
	 * 根据条件-查询全部
	 */
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return _UnitRateMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) _UnitRateMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	public int countRecords(Map<String, Object> map){
		return _UnitRateMapper.countRecords(map);
	}
	
	/**
	 * 根据条件-批量逻辑删除 (AliveFlag修改为0)
	 */
	public void deleteRecords(Map<String, Object> map){
		_UnitRateMapper.deleteRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据
	 */
	public void updateRecords(Map<String, Object> map) {
		_UnitRateMapper.updateRecords(map);
	}
	
	//**************************以下方法为开发者补充*********************************/
	/**
	 * 根据Uuid-逻辑删除对象（ AliveFlag修改为0）
	 */
	public void deleteRecordByUuid(String uuid, Long deleteUser) {
		Map<String, Object> map = new HashMap<>();
		map.put("uuid", uuid);
		map.put("deleteUser", deleteUser);
		map.put("updateDate", DateTimeUtils.currentDate());
		 _UnitRateMapper.deleteRecordsByUuid(map);
	}

	@Override
	public void updateRecordByUuid(UnitRate unitRate, Long updateUser) {
		unitRate.setUpdateUser(updateUser);
		unitRate.setUpdateDate(DateTimeUtils.currentDate());
		unitRate.setVersion((Integer.parseInt(unitRate.getVersion())+1)+"");
		_UnitRateMapper.updateRecordByUuid(unitRate);
		
	}

	@Override
	public List<Map<String, Object>> getUnitPageList(UnitRateQuery query) {
		PageHelper.startPage(query.getCurrentPage(), query.getPageSize());
		// 单位名称
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("oneName", query.getOneName());
		map.put("twoName", query.getTwoName());
		
		List<Map<String, Object>> list = _UnitRateMapper.queryRecords(map);
		
		return list;
	}
	
	/**
	 * 新增
	 */
	public void insertRecord2(UnitRate unitRate, Long insertUser){
		unitRate.setUuid(KeyGenUtils.newUuid());
		unitRate.setCreateDate(DateTimeUtils.currentDate());
		unitRate.setUpdateDate(DateTimeUtils.currentDate());
		unitRate.setLangVer(Constants.LANG_VER);
		unitRate.setAliveFlag(Constants.ALIEVE_FLAG_YES);
		unitRate.setCreateUser(insertUser);
		unitRate.setVersion("1");
		 _UnitRateMapper.insertRecord(unitRate);
	}

	@Override
	public UnitRate getRateInfo(UnitRate unitRate) {
		return _UnitRateMapper.entityRecord(unitRate);
	}

	@Override
	public int getUnitRateByName(String chName) {
		Map<String, Object> map = new HashMap<>();
		map.put("chName", chName);
		return _UnitRateMapper.getUnitRateByName(map);
	}
}
