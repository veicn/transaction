package com.sinochem.crude.trade.transport.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transport.dao.VoyageStartMapper;
import com.sinochem.crude.trade.transport.domain.VoyageStart;
import com.sinochem.crude.trade.transport.model.VoyageStartVO;
import com.sinochem.crude.trade.transport.model.res.VoyageStartRes;

public interface VoyageStartService {
	
	public abstract VoyageStartMapper getMapper(); 
	
	/**
	 * 根据对象-查询对象列表
	 */
	public abstract List<VoyageStart> queryByEntitys(VoyageStart voyagestart);
	
	/**
	 * 根据主键-查询对象
	 */
	public abstract VoyageStart findByPrimaryKey(Long voyageStartId);
 
 	/**
	 * 根据UUID-查询对象
	 */
	public abstract VoyageStart findByUuid(String uuid);
 
	/**
	 * 根据主键-修改对象
	 */
	public abstract void updateRecord(VoyageStart voyagestart);
	
	/**
	 * 根据主键-逻辑删除对象（ AliveFlag修改为0）
	 */
	public abstract void deleteRecordByKey(Long voyageStartId , Long deleteUser);
	
	/**
	 * 新增
	 */
	public abstract void insertRecord(VoyageStart voyagestart);
	
	/*
	 * 根据主键删除数据
	 */
	//public abstract void deleteRecordByKey(Long voyageStartId);
	
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
	 * 船航次开始信息维护
	 * @param vo
	 * @param user 
	 * @param user 
	 */
	public abstract void saveVoyageStart(VoyageStartVO vo, CurrentUser user);

	/**
	 * 查询航次开始信息
	 * @param vo
	 * @param shipPlateUuid 
	 * @param waybillUuid 
	 * @return
	 */
	public abstract VoyageStartRes findVoyageStartDetail(String shipPactUuid);

	/**
	 * 更新航次开始信息
	 * @param vo
	 * @param user
	 */
	public abstract void upateVoyageStart(VoyageStartVO vo, CurrentUser user);

	/**
	 * 校验是否维护航次开始信息
	 * @param shipPactUuid
	 */
	public abstract void checkIsEdit(String shipPactUuid);

	/**
	 * 查询航次开始油种信息
	 * @param shipPactUuid
	 * @return
	 */
	public abstract List<Map<String, Object>> findOilList(String shipPactUuid);

	/**
	 * 导入航次开始信息
	 * @param path
	 * @param uuid
	 * @param user
	 * @return 
	 */
	public abstract String imports(String path, String uuid, CurrentUser user);
}
