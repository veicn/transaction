package com.sinochem.crude.trade.transport.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transport.dao.LoadPortMapper;
import com.sinochem.crude.trade.transport.domain.Agreement;
import com.sinochem.crude.trade.transport.domain.LoadPort;
import com.sinochem.crude.trade.transport.model.LoadPortVO;
import com.sinochem.crude.trade.transport.model.res.LoadPortRes;

public interface LoadPortService {
	
	public abstract LoadPortMapper getMapper(); 
	
	/**
	 * 根据对象-查询对象列表
	 */
	public abstract List<LoadPort> queryByEntitys(LoadPort loadport);
	
	/**
	 * 根据主键-查询对象
	 */
	public abstract LoadPort findByPrimaryKey(Long loadPortId);
 
 	/**
	 * 根据UUID-查询对象
	 */
	public abstract LoadPort findByUuid(String uuid);
 
	/**
	 * 根据主键-修改对象
	 */
	public abstract void updateRecord(LoadPort loadport);
	
	/**
	 * 根据主键-逻辑删除对象（ AliveFlag修改为0）
	 */
	public abstract void deleteRecordByKey(Long loadPortId , Long deleteUser);
	
	/**
	 * 新增
	 */
	public abstract void insertRecord(LoadPort loadport);
	
	/*
	 * 根据主键删除数据
	 */
	//public abstract void deleteRecordByKey(Long loadPortId);
	
	/**
	 * 根据条件-查询全部
	 */
	public abstract List<Map<String, Object>> queryRecords(Map<String, Object> map);
	
	/**
	 * 根据条件-分页查询
	 */
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo);
	
	/**
	 * 根据条件-查询记录条数
	 */
	public abstract int countRecords(Map<String, Object> map); 
	
	/**
	 * 根据条件-批量逻辑删除 (AliveFlag修改为0)
	 */
	public abstract void deleteRecords(Map<String, Object> map);

	/**
	 * 根据条件-批量修改数据
	 */
	public void updateRecords(Map<String, Object> map);

	//**************************以下方法为开发者补充*********************************/

	/**
	 * 新增船舶装港信息
	 * @param vo
	 * @param user 
	 */
	public abstract void saveLoadPort(LoadPortVO vo, CurrentUser user);

	/**
	 * 查询船舶装港信息
	 * @param vo
	 * @return
	 */
	public abstract LoadPortRes findLoadPortDeatil(String shipPactUuid);

	/**
	 * 修改船舶装港信息
	 * @param vo
	 * @param user
	 */
	public abstract void updateLoadPort(LoadPortVO vo, CurrentUser user);

	/**
	 * 同步订单装港信息
	 * @param agreementUuids
	 * @param memberId
	 */
	public abstract void sendLoadPort(List<Agreement> agreementUuids, Long memberId);

	
}
