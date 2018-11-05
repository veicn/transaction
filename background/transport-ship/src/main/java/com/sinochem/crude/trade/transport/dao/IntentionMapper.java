package com.sinochem.crude.trade.transport.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sinochem.crude.trade.transport.domain.Intention;
//import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface IntentionMapper {
	/**根据条件-查询全部*/
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	/**根据条件-查询记录条数*/
	public int countRecords(Map<String,Object> map);
	/**根据条件-批量逻辑删除 (AliveFlag修改为0)*/
	public void deleteRecords(Map<String,Object> map); 
	/**根据条件-批量修改数据*/
	public void updateRecords(Map<String,Object> map);
	/**根据对象-查询对象列表*/
	public List<Intention> queryByEntitys(Intention entity);
	/**根据主键-查询对象*/
	public Intention findByPrimaryKey(  @Param("intentionId")  Long intentionId);	
	/**根据UUID-查询对象*/
	public Intention findByUuid(String uuid);	
	/**根据主键-修改对象*/
	public void updateRecord(Intention entity);
	/**新增*/
	public void insertRecord(Intention entity);
	/**根据主键删除数据*/
	public void deleteRecordByKey( @Param("intentionId") Long intentionId, @Param("updateUser") Long updateUser);
	//**************************以下方法为开发者补充*********************************/
	
	/**根据条件-查询单条记录*/
	public Intention findByConditions(Map<String, Object> map);
	
	/**根据uuid-批量修改数据*/
	public void updateRecordsFn(Map<String,Object> map);
	
	/**根据条件-查询（询盘状态不为...）*/
	public List<Map<String,Object>> findIntentionListQueryRecords(Map<String,Object> map);
	/**根据船盘uuid-批量修改状态*/
	public void changeStatus(Map<String,Object> map);
	/**
	 * 根据船盘uuid查询数据
	 * @param in
	 * @return
	 */
	public Intention findByEntitys(Intention in); 
}
