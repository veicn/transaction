package com.sinochem.crude.trade.transport.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sinochem.crude.trade.transport.domain.Disburden;
//import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface DisburdenMapper {
	/**根据条件-查询全部*/
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	/**根据条件-查询记录条数*/
	public int countRecords(Map<String,Object> map);
	/**根据条件-批量逻辑删除 (AliveFlag修改为0)*/
	public void deleteRecords(Map<String,Object> map); 
	/**根据条件-批量修改数据*/
	public void updateRecords(Map<String,Object> map);
	/**根据对象-查询对象列表*/
	public List<Disburden> queryByEntitys(Disburden entity);
	/**根据主键-查询对象*/
	public Disburden findByPrimaryKey(  @Param("disburdenId")  Long disburdenId);	
	/**根据UUID-查询对象*/
	public Disburden findByUuid(String uuid);	
	/**根据主键-修改对象*/
	public void updateRecord(Disburden entity);
	/**新增*/
	public void insertRecord(Disburden entity);
	/**根据主键删除数据*/
	public void deleteRecordByKey( @Param("disburdenId") Long disburdenId, @Param("updateUser") Long updateUser);
	//**************************以下方法为开发者补充*********************************/
	
	/**
	 * 删除多余的货物卸港信息
	 * @param lists
	 * @param shipPactUuid
	 */
	public void deletByLoadAndShipPact(@Param("lists")List<Map<String, Object>> lists,
			@Param("shipPactUuid")String shipPactUuid);
	
}
