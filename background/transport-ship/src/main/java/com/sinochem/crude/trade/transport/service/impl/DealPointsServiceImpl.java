package com.sinochem.crude.trade.transport.service.impl;

import java.util.Date;
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
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transport.commons.constants.Constants;
import com.sinochem.crude.trade.transport.dao.DealPointsMapper;
import com.sinochem.crude.trade.transport.domain.DealPoints;
import com.sinochem.crude.trade.transport.model.DealPointsVO;
import com.sinochem.crude.trade.transport.query.DealPointsQuery;
import com.sinochem.crude.trade.transport.service.DealPointsService;

@Service
public class DealPointsServiceImpl implements DealPointsService {
	@Autowired
	private DealPointsMapper _DealPointsMapper;
	
	
	public DealPointsMapper getMapper(){
		return _DealPointsMapper;
	} 
	
	/**
	 * 根据对象-查询对象列表
	 */
	public List<DealPoints> queryByEntitys(DealPoints dealpoints){
		return  _DealPointsMapper.queryByEntitys(dealpoints);
	}
	
	/**
	 * 根据主键-查询对象
	 */
	public DealPoints findByPrimaryKey(Long dealPointsId){
		return  _DealPointsMapper.findByPrimaryKey(dealPointsId);	
	}
	/**
	 * 根据UUID-查询对象
	 */
	public DealPoints findByUuid(String uuid){
		return  _DealPointsMapper.findByUuid(uuid);	
	} 
	
	/**
	 * 根据主键-修改对象
	 */
	public void updateRecord(DealPoints dealpoints) {
		 _DealPointsMapper.updateRecord(dealpoints);
	}
	
	/**
	 * 根据主键-逻辑删除对象（ AliveFlag修改为0）
	 */
	public void deleteRecordByKey(Long dealPointsId  , Long deleteUser) {
		 _DealPointsMapper.deleteRecordByKey(dealPointsId , deleteUser);
	}
	
	/**
	 * 新增
	 */
	public void insertRecord(DealPoints dealpoints){
		 _DealPointsMapper.insertRecord(dealpoints);
	}
	
	/*
	 * 根据主键删除数据 
	public void deleteRecordByKey(Long dealPointsId){
		 _DealPointsMapper.deleteRecordByKey(dealPointsId);
	}
	*/
	/**
	 * 根据条件-查询全部
	 */
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return _DealPointsMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) _DealPointsMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	public int countRecords(Map<String, Object> map){
		return _DealPointsMapper.countRecords(map);
	}
	
	/**
	 * 根据条件-批量逻辑删除 (AliveFlag修改为0)
	 */
	public void deleteRecords(Map<String, Object> map){
		_DealPointsMapper.deleteRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据
	 */
	public void updateRecords(Map<String, Object> map) {
		_DealPointsMapper.updateRecords(map);
	}
	
	//**************************以下方法为开发者补充*********************************/
	
	
	//翻页列表
	@Override
	public List<Map<String, Object>> getDealPointsPageList(DealPointsQuery query) {
		PageHelper.startPage(query.getCurrentPage(), query.getPageSize());
		// 单位名称
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("region", query.getRegion());		
		List<Map<String, Object>> list = _DealPointsMapper.queryRecords(map);
		
		return list;
	}

	//新增方法
	@Override
	public void saveDealPoints(DealPoints dp, CurrentUser user) {
		dp.setUuid(KeyGenUtils.newUuid());
		dp.setLangVer(Constants.LANG_VER);
		dp.setAliveFlag(Constants.ALIEVE_FLAG_YES);
		dp.setCreateDate(DateTimeUtils.currentDate());
		dp.setVersion("1");
		dp.setCreateUser(user.getMemberId());
		_DealPointsMapper.insertRecord(dp);
	}
	
	//新增方法
		@Override
		public void saveDealPoints2(DealPoints dp) {
			dp.setUuid(KeyGenUtils.newUuid());
			dp.setLangVer(Constants.LANG_VER);
			dp.setAliveFlag(Constants.ALIEVE_FLAG_YES);
			dp.setCreateDate(DateTimeUtils.currentDate());
			dp.setVersion("1");
			_DealPointsMapper.insertRecord(dp);
		}
	
	//根据uuid查询对象
	@Override
	public void getDealPointsByUuid(DealPoints dp) {
		_DealPointsMapper.findByUuid(dp.getUuid());
	}

	//根据uuid逻辑删除对象
	@Override
	public void deleteDealPointsByUuid(DealPoints dp, CurrentUser user) {
		dp.setUpdateUser(user.getMemberId());
		_DealPointsMapper.deleteRecordsByUuid(dp);
	}

	//根据uuid修改对象
	@Override
	public void updateDealPointsByUuid(DealPoints dp, CurrentUser user) {
		dp.setUpdateUser(user.getMemberId());
		dp.setVersion((Integer.parseInt(dp.getVersion())+1)+"");
		_DealPointsMapper.updateRecordByUuid(dp);
	}

	@Override
	public DealPoints checkDp(DealPointsVO dp) {
		
		return _DealPointsMapper.checkDp(dp);
	}

	@Override
	public List<DealPoints> findDealPointsPageList() {
		// 页码设定
		//PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount());
		// 查询成交点数信息
		List<DealPoints> dpList = this.queryByEntitys2();
		return dpList;
	}
	

	//查询当天成交点数，按时间倒序返回
	@Override
	public List<DealPoints> getTodayDealPoints(String todayDate) {
		return _DealPointsMapper.getTodayDealPoints(todayDate);
	}

	//查询今年的所有成交点数，构造线形图
	@Override
	public List<DealPoints> getThisYearDealPoints(DealPoints dealPoints) {
		return _DealPointsMapper.getThisYearDealPoints(dealPoints);
	}
	
	public List<DealPoints> queryByEntitys2(){
		return  _DealPointsMapper.queryByEntitys2();
	}

	@Override
	public List<DealPoints> getNowDealPoints() {
		
		return _DealPointsMapper.getNowDealPoints();
	}

	@Override
	public DealPoints getDealPointsByRecode(DealPoints dealPoints2) {
		return _DealPointsMapper.getDealPointsByRecode(dealPoints2);
	}
	//参考时间集合
	@Override
	public List<Date> findAllDates() {
		return _DealPointsMapper.findAllDates();
	}

	@Override
	public List<Map<String, Object>> findDealPointsPageListByRecords(
			Map<String, String> map) {
		return _DealPointsMapper.findDealPointsPageListByRecords(map);
	}

}
