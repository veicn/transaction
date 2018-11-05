package com.sinochem.crude.trade.transport.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mysql.jdbc.StringUtils;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.common.utils.KeyGenUtils;
import com.sinochem.crude.trade.transport.commons.constants.Constants;
import com.sinochem.crude.trade.transport.commons.exceptions.TransportException;
import com.sinochem.crude.trade.transport.dao.TideMapper;
import com.sinochem.crude.trade.transport.domain.Tide;
import com.sinochem.crude.trade.transport.query.TideQuery;
import com.sinochem.crude.trade.transport.service.TideService;

@Service
public class TideServiceImpl implements TideService {
	@Autowired
	private TideMapper _TideMapper;
	
	
	public TideMapper getMapper(){
		return _TideMapper;
	} 
	
	/**
	 * 根据对象-查询对象列表
	 */
	public List<Tide> queryByEntitys(Tide tide){
		return  _TideMapper.queryByEntitys(tide);
	}
	
	/**
	 * 根据主键-查询对象
	 */
	public Tide findByPrimaryKey(Long tideId){
		return  _TideMapper.findByPrimaryKey(tideId);	
	}
	/**
	 * 根据UUID-查询对象
	 */
	public Tide findByUuid(String uuid){
		return  _TideMapper.findByUuid(uuid);	
	} 
	
	/**
	 * 根据主键-修改对象
	 */
	public void updateRecord(Tide tide) {
		 _TideMapper.updateRecord(tide);
	}
	
	/**
	 * 根据主键-逻辑删除对象（ AliveFlag修改为0）
	 */
	public void deleteRecordByKey(Long tideId  , Long deleteUser) {
		 _TideMapper.deleteRecordByKey(tideId , deleteUser);
	}
	
	/**
	 * 新增
	 */
	public void insertRecord(Tide tide){
		 _TideMapper.insertRecord(tide);
	}
	
	/*
	 * 根据主键删除数据 
	public void deleteRecordByKey(Long tideId){
		 _TideMapper.deleteRecordByKey(tideId);
	}
	*/
	/**
	 * 根据条件-查询全部
	 */
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return _TideMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) _TideMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	public int countRecords(Map<String, Object> map){
		return _TideMapper.countRecords(map);
	}
	
	/**
	 * 根据条件-批量逻辑删除 (AliveFlag修改为0)
	 */
	public void deleteRecords(Map<String, Object> map){
		_TideMapper.deleteRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据
	 */
	public void updateRecords(Map<String, Object> map) {
		_TideMapper.updateRecords(map);
	}
	
	//**************************以下方法为开发者补充*********************************/
	/**
	 * 添加潮汐信息
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Long saveTide(Tide vo, String uuid, Long memberId) {
		//校验
		vo.setUuid(KeyGenUtils.newUuid());
		vo.setCreateDate(DateTimeUtils.currentDate());
		vo.setUpdateDate(DateTimeUtils.currentDate());
		vo.setLangVer(Constants.LANG_VER);
		vo.setAliveFlag(Constants.ALIEVE_FLAG_YES);
		vo.setUpdateUser(memberId);
		vo.setCreateUser(memberId);
		
		// 时间合并
		String hour = vo.getHour();
		String minute = vo.getMinute();
		vo.setHour(hour+minute);
		vo.setMinute("");
		
		// 添加潮汐信息
		this.insertRecord(vo);
		
		// 取得新增潮汐ID
		Long tideId = vo.getTideId();
		
		return tideId;
	}
	
	/**
	 * 修改潮汐信息
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateTide(Tide vo, Long memberId) {
		// 取得uuid
		String uuid = vo.getUuid();
		
		// 潮汐uuid校验
		if (StringUtils.isNullOrEmpty(uuid)) {
			throw new TransportException(TransportException.TYPE.ITSH220);
		}
		
		//根据潮汐uuid查询潮汐信息
		Tide tide = this.findByUuid(uuid);
		
		if (tide == null){
			throw new TransportException(TransportException.TYPE.ITSH221);
		}
		
		vo.setTideId(tide.getTideId());
		vo.setUpdateDate(DateTimeUtils.currentDate());
		vo.setUpdateUser(1L);
		
		// 时间合并
		String hour = vo.getHour();
		String minute = vo.getMinute();
		vo.setHour(hour+minute);
		vo.setMinute("");
		
		// 修改潮汐信息
		this.updateRecord(vo);
	}

	/**
	 * 删除潮汐信息
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delTide(Tide vo, Long memberId) {
		
		// 取得uuid
		String uuid = vo.getUuid();
		
		// 潮汐uuid校验
		if (StringUtils.isNullOrEmpty(uuid)) {
			throw new TransportException(TransportException.TYPE.ITSH220);
		}
		
		//根据潮汐uuid查询潮汐信息
		Tide tide = this.findByUuid(uuid);
		
		if (tide == null){
			throw new TransportException(TransportException.TYPE.ITSH221);
		}
		
		// 取得潮汐主键
		Long tideId = tide.getTideId();
		
		// 删除潮汐信息
		this.deleteRecordByKey(tideId, memberId);
	}

	/**
	 * 查询潮汐信息详细
	 */
	@Override
	public Tide findTideDetail(Tide vo) {
		
		// 取得uuid
		String uuid = vo.getUuid();
		
		// 潮汐uuid校验
		if (StringUtils.isNullOrEmpty(uuid)) {
			throw new TransportException(TransportException.TYPE.ITSH220);
		}
		
		//根据潮汐uuid查询潮汐信息
		Tide tide = this.findByUuid(uuid);
		
		if (tide == null){
			throw new TransportException(TransportException.TYPE.ITSH221);
		}
		
		// 取得潮汐主键
		Long tideId = tide.getTideId();
		
		// 查询潮汐信息
		Tide tideDetail = this.findByPrimaryKey(tideId);
		
		return tideDetail;
	}

	/**
	 * 查询潮汐信息列表
	 */
	@Override
	public List<Tide> findTideList(Tide vo) {
		// 查询潮汐信息列表
		List<Tide> tideList = (List<Tide>)this.queryByEntitys(vo);
		
		return tideList;
	}
	
	/**
	 * 查询潮汐信息翻页列表
	 */
	@Override
	public Page<Tide> findTidePageList(Tide vo, SimplePageInfo pageInfo) {
		// 页码设定
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount());
		// 查询潮汐信息
		Page<Tide> tideList = (Page<Tide>)this.queryByEntitys(vo);
		
		return tideList;
	}
	
	/**
	 * 查询潮汐信息翻页列表
	 */
	@Override
	public List<Map<String, Object>> getTidePageList(TideQuery query) {
		PageHelper.startPage(query.getCurrentPage(), query.getPageSize());
		// 港口代码、日期
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("portCode", query.getPortSearch());
		map.put("date", query.getDateSearch());
		
		List<Map<String, Object>> list = this.queryRecords(map);
		
		return list;
	}
	
	/**
	 * 根据港口、日期查询潮汐信息(前台接口)
	 */
	@Override
	public List<Map<String, Object>> findTideListByPortDate(Tide vo) {
		// 查询潮汐信息列表
		List<Map<String, Object>> tideList = (List<Map<String, Object>>)_TideMapper.queryByPortDate(vo);
		
		return tideList;
	}
}
