package com.sinochem.crude.trade.transport.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sinochem.crude.trade.transport.domain.ShipPact;
//import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface ShipPactMapper {
	/**根据条件-查询全部*/
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	/**根据条件-查询记录条数*/
	public int countRecords(Map<String,Object> map);
	/**根据条件-批量逻辑删除 (AliveFlag修改为0)*/
	public void deleteRecords(Map<String,Object> map); 
	/**根据条件-批量修改数据*/
	public void updateRecords(Map<String,Object> map);
	/**根据对象-查询对象列表*/
	public List<ShipPact> queryByEntitys(ShipPact entity);
	/**根据主键-查询对象*/
	public ShipPact findByPrimaryKey(  @Param("shipPactId")  Long shipPactId);	
	/**根据UUID-查询对象*/
	public ShipPact findByUuid(String uuid);	
	/**根据主键-修改对象*/
	public void updateRecord(ShipPact entity);
	/**新增*/
	public void insertRecord(ShipPact entity);
	/**根据主键删除数据*/
	public void deleteRecordByKey( @Param("shipPactId") Long shipPactId, @Param("updateUser") Long updateUser);
	//**************************以下方法为开发者补充*********************************/
	
	/**根据条件-查询船代*/
	public List<Map<String,Object>> queryRecordsFn(Map<String,Object> map);
	/**查询船合同列表(船东/经纪人)*/
	public List<Map<String,Object>> queryShipOwnerShipPact(Map<String,Object> map);
	/**查询船合同列表(代理根据询盘单或者船盘UUID查询合同)*/
	public ShipPact findPactByIntentionOrPlateUuid(Map<String, Object> map);
	/**查询船合同列表*/
	public List<ShipPact> selectPactByAgreementCode(Map<String, Object> map);
	/**根据条件-查询记录条数*/
	public int selectPactByAgreementCodeCount(Map<String,Object> map);
	/** 更新合同状态*/
	public abstract int updateShipPactStatus(Map<String,Object> map);
	/**
	 * 根据船盘uuid查询数据
	 * @param uuid
	 * @return
	 */
	public ShipPact findByEntitys(ShipPact pact);
	//查询船合同列表
	public List<Map<String, Object>> queryRecordsByPer(Map<String, Object> map);
	//平台查询船合同
	public List<Map<String, Object>> queryShipPactPlatform(Map<String, Object> map);
}
