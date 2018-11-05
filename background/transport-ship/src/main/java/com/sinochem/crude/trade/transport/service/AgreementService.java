package com.sinochem.crude.trade.transport.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transport.dao.AgreementMapper;
import com.sinochem.crude.trade.transport.domain.Agreement;
import com.sinochem.crude.trade.transport.model.AgreementVO;
import com.sinochem.crude.trade.transport.model.res.AgreementRes;
import com.sinochem.it.b2b.common.exception.BizException;

public interface AgreementService {
	
	public abstract AgreementMapper getMapper(); 
	
	/**
	 * 根据对象-查询对象列表
	 */
	public abstract List<Agreement> queryByEntitys(Agreement agreement);
	
	/**
	 * 根据主键-查询对象
	 */
	public abstract Agreement findByPrimaryKey(Long agreementId);
 
 	/**
	 * 根据UUID-查询对象
	 */
	public abstract Agreement findByUuid(String uuid);
 
	/**
	 * 根据主键-修改对象
	 */
	public abstract void updateRecord(Agreement agreement);
	
	/**
	 * 根据主键-逻辑删除对象（ AliveFlag修改为0）
	 */
	public abstract void deleteRecordByKey(Long agreementId , Long deleteUser);
	
	/**
	 * 新增
	 */
	public abstract void insertRecord(Agreement agreement);
	
	/*
	 * 根据主键删除数据
	 */
	//public abstract void deleteRecordByKey(Long agreementId);
	
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
	 * 生成代理协议
	 * @param vo
	 * @param user
	 */
	public abstract void saveAgreement(AgreementVO vo, CurrentUser user);

	/**
	 * 查询代理协议详情
	 * @param uuid
	 * @return
	 */
	public abstract AgreementRes findAgreementDetail(String uuid);

	/**
	 * 查询协议列表
	 * @param map
	 * @param pageInfo 
	 * @return
	 */
	public abstract Page<Map<String, Object>> queryAgreementList(Map<String, Object> map, SimplePageInfo pageInfo);

	/**
	 * 撤销代理协议
	 * @param uuid
	 * @param user
	 */
	public abstract void revokeAgreement(String uuid, CurrentUser user);

	/**
	 * 修改代理协议
	 * 
	 * @param vo
	 * @param user
	 */
	public abstract void updateAgreement(AgreementVO vo, CurrentUser user);

	/**
	 * 删除协议
	 * @param uuid
	 * @param user
	 */
	public abstract void deleteAgreement(String uuid, CurrentUser user);

	/**
	 * 查询代理协议详情(货盘uuid)
	 * @param uuid
	 * @return
	 */
	public abstract AgreementRes findAgreementDetailByPalletUuid(String uuid);
	
	/**
	 * 查询代理协议详情多个
	 * @param agreementUuids
	 * @return
	 */
	public abstract List<AgreementRes> findAgreementDetailMany(List<String> agreementUuids);

	/**
	 * 生成代理协议
	 * @param vo
	 * @param user
	 * @throws BizException
	 */
	public abstract void agreementSave(AgreementVO vo, CurrentUser user) throws BizException ;
	
	/**
	 * 根据船合同uuid查询协议详情
	 */
	public abstract Agreement findAgreementDetailByShipPact(AgreementVO vo,CurrentUser user);

	/**
	 * 平台协议列表查询
	 * @param maps
	 * @param pageInfo
	 * @return
	 */
	public abstract Page<Map<String, Object>> queryAgreementPlatform(Map<String, Object> maps, SimplePageInfo pageInfo);
	
}
