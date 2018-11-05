package com.sinochem.crude.trade.info.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.common.utils.KeyGenUtils;
import com.sinochem.crude.trade.info.constants.Constants;
import com.sinochem.crude.trade.info.dao.ColSubscribeMapper;
import com.sinochem.crude.trade.info.dao.InfoMapper;
import com.sinochem.crude.trade.info.dao.immt.ColSubscribeImmtMapper;
import com.sinochem.crude.trade.info.domain.ColSubscribe;
import com.sinochem.crude.trade.info.domain.Info;
import com.sinochem.crude.trade.info.service.ColSubscribeService;
import com.sinochem.crude.trade.member.user.CurrentUser;

@Service
public class ColSubscribeServiceImpl implements ColSubscribeService {
	@Autowired
	private ColSubscribeMapper colSubscribeMapper;
	
	@Autowired
	private ColSubscribeImmtMapper colSubscribeImmtMapper;
	
	@Autowired
	private InfoMapper infoMapper;
	
	public ColSubscribeMapper getMapper(){
		return colSubscribeMapper;
	} 
	
	/**
	 * 根据对象-查询对象列表
	 */
	public List<ColSubscribe> queryByEntitys(ColSubscribe colsubscribe){
		return colSubscribeImmtMapper.queryByEntitys(colsubscribe);
	}
	
	/**
	 * 根据主键-查询对象
	 */
	public ColSubscribe findByPrimaryKey(String id){
		return colSubscribeImmtMapper.findByPrimaryKey(id);	
	}
	
	/**
	 * 根据主键-修改对象
	 */
	public void updateRecord(ColSubscribe colsubscribe) throws Exception {
		if ( colsubscribe.getId() == null  ) {
			throw new Exception("id 为空，不能修改 ");
		}
		colSubscribeImmtMapper.updateRecord(colsubscribe);
	}
	
	/**
	 * 根据主键-逻辑删除对象（ AliveFlag修改为0）
	 */
	public void deleteRecordByKey(String id  , String deleteUser) throws Exception {
		if (  id == null ) {
			throw new Exception("id 为空，不能删除 ");
		}
		colSubscribeImmtMapper.deleteRecordByKey(id , deleteUser);
	}
	
	/**
	 * 新增
	 */
	public void insertRecord(ColSubscribe colsubscribe,CurrentUser user){
		colsubscribe.setId(KeyGenUtils.newUuid());
		colsubscribe.setAliveFlag(Constants.ALIEVE_FLAG);
		colsubscribe.setCreateDate(DateTimeUtils.currentDate());
		colsubscribe.setCreateUser(user.getMemberId()+"");
		colSubscribeImmtMapper.insertRecord(colsubscribe);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return colSubscribeMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) colSubscribeMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	public int countRecords(Map<String, Object> map){
		return colSubscribeMapper.countRecords(map);
	}
	
	/**
	 * 根据条件-批量逻辑删除 (AliveFlag修改为0)
	 */
	public void deleteRecords(Map<String, Object> map) throws Exception {
		colSubscribeMapper.deleteRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据
	 */
	public void updateRecords(Map<String, Object> map) throws Exception {
		colSubscribeMapper.updateRecords(map);
	}
	
	//**************************以下方法为开发者补充*********************************/
	
	/*我的订阅专栏*/
	@Override
	public Page<Map<String, Object>> mySubscribeQuery(Map<String, Object> map, SimplePageInfo pageInfo) {
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return colSubscribeMapper.mySubscribeQuery(map);
	}
	
	/*我的订阅专栏文章*/
	@Override
	public Page<Map<String, Object>> mySubscribeInfoQuery(Map<String, Object> map, SimplePageInfo pageInfo) {
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return colSubscribeMapper.mySubscribeInfoQuery(map);
	}

	@Override
	public void updateByColumnId(ColSubscribe sub) {
		colSubscribeMapper.updateByColumnId(sub);
	}
	
	@Override
	public List<Info> queryNewInfo(String id) {
		return infoMapper.queryNewInfo(id);
	}
}
