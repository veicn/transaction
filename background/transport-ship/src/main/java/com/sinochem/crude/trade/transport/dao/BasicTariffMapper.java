package com.sinochem.crude.trade.transport.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sinochem.crude.trade.transport.domain.BasicTariff;
//import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface BasicTariffMapper {
	/**根据条件-查询全部*/
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	/**根据条件-查询记录条数*/
	public int countRecords(Map<String,Object> map);
	/**根据条件-批量逻辑删除 (AliveFlag修改为0)*/
	public void deleteRecords(Map<String,Object> map); 
	/**根据条件-批量修改数据*/
	public void updateRecords(Map<String,Object> map);
	/**根据对象-查询对象列表*/
	public List<BasicTariff> queryByEntitys(BasicTariff entity);
	/**根据主键-查询对象*/
	public BasicTariff findByPrimaryKey(  @Param("basicTariffId")  Long basicTariffId);	
	/**根据UUID-查询对象*/
	public BasicTariff findByUuid(String uuid);	
	/**根据主键-修改对象*/
	public void updateRecord(BasicTariff entity);
	/**新增*/
	public void insertRecord(BasicTariff entity);
	/**根据主键删除数据*/
	public void deleteRecordByKey( @Param("basicTariffId") Long basicTariffId, @Param("updateUser") Long updateUser);
	//**************************以下方法为开发者补充*********************************/
	/**根据uuid删除数据*/
	public void deleteRecordsByUuid(BasicTariff bt);
	/**根据uuid修改数据*/
	public void updateRecordByUuid(BasicTariff bt);
	//查重
	public BasicTariff checkBt(Map<String, String> map);
	
	public List<Map<String, Object>> queryRecords2(Map<String, Object> map);
	
	public List<BasicTariff> queryByEntitys2(BasicTariff entity);
	
	public List<Map<String, Object>> queryRecords4(Map<String, Object> map);
	/**查询所有装港数据*/
	public List<String> findLoadList();
	/**查询所有卸港数据*/
	public List<String> findUnLoadList();
	
}
