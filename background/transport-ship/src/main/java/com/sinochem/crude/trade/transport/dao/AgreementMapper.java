package com.sinochem.crude.trade.transport.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.sinochem.crude.trade.transport.domain.Agreement;
//import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface AgreementMapper {
	/**根据条件-查询全部*/
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	/**根据条件-查询记录条数*/
	public int countRecords(Map<String,Object> map);
	/**根据条件-批量逻辑删除 (AliveFlag修改为0)*/
	public void deleteRecords(Map<String,Object> map); 
	/**根据条件-批量修改数据*/
	public void updateRecords(Map<String,Object> map);
	/**根据对象-查询对象列表*/
	public List<Agreement> queryByEntitys(Agreement entity);
	/**根据主键-查询对象*/
	public Agreement findByPrimaryKey(  @Param("agreementId")  Long agreementId);	
	/**根据UUID-查询对象*/
	public Agreement findByUuid(String uuid);	
	/**根据主键-修改对象*/
	public void updateRecord(Agreement entity);
	/**新增*/
	public void insertRecord(Agreement entity);
	/**根据主键删除数据*/
	public void deleteRecordByKey( @Param("agreementId") Long agreementId, @Param("updateUser") Long updateUser);
	//**************************以下方法为开发者补充*********************************/
	/**根据货盘uuid查询协议*/
	public List<Agreement> queryByPalletUuid(Agreement agreement);
	/**
	 * 查询协议列表
	 * @param map
	 * @return
	 */
	public Page<Map<String, Object>> queryAgreementList(Map<String, Object> map);
	/**根据orderCode-查询对象*/
	public Agreement findByOrderCode(String uuid);
	/**
	 * 根据船合同uuid查询协议详情
	 */
	public Agreement queryByEntity(Agreement entity);	
}
